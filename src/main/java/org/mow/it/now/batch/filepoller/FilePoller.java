package org.mow.it.now.batch.filepoller;

import jakarta.annotation.PostConstruct;
import org.mow.it.now.batch.job.MowSimulationJobLauncher;
import org.mow.it.now.batch.job.MowSimulationJobParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class FilePoller {
    private static final Logger logger = LoggerFactory.getLogger(FilePoller.class);
    private final MowSimulationJobLauncher mowSimulationJobLauncher;
    private final DoneDirectory doneDirectory;
    private final InputDirectory inputDirectory;
    private final OutputDirectory outputDirectory;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    public FilePoller(MowSimulationJobLauncher mowSimulationJobLauncher, DoneDirectory doneDirectory, InputDirectory inputDirectory, OutputDirectory outputDirectory, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.mowSimulationJobLauncher = mowSimulationJobLauncher;
        this.doneDirectory = doneDirectory;
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @PostConstruct
    public void start() {
        threadPoolTaskScheduler.scheduleWithFixedDelay(this::poll, Duration.of(1, ChronoUnit.SECONDS));
    }

    public void poll() {
        try (Stream<Path> filesToPoll = Files.list(Paths.get(inputDirectory.name()))) {
            List<JobExecution> executingJobs = new ArrayList<>();
            filesToPoll.map(path -> new MowSimulationJobParameter(path.toString(), outputDirectory.name() + "result"))
                       .map(mowSimulationJobLauncher::launch)
                       .forEach(result -> result.fold(executingJobs::add, jobParameter -> handleFailedExecution(jobParameter.input())));

            do {
                Thread.sleep(500);
                List<JobExecution> terminated = new ArrayList<>();
                executingJobs.stream()
                             .filter(Predicate.not(JobExecution::isRunning))
                             .forEach(jobExecution -> {
                                 terminated.add(jobExecution);
                                 if (jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                                     handleSuccessfullExecution(jobExecution);
                                 } else {
                                     JobParameters jobParameters = jobExecution.getJobParameters();
                                     String input = jobParameters.getString("input");
                                     handleFailedExecution(input);
                                 }
                             });
                executingJobs.removeAll(terminated);
            } while (!executingJobs.isEmpty());
        } catch (Exception e) {
            logger.error("An error has occured during file polling", e);
        } finally {
            threadPoolTaskScheduler.scheduleWithFixedDelay(this::poll, Duration.of(1, ChronoUnit.SECONDS));
        }
    }

    private void handleFailedExecution(String inputFileOnError) {
        try {
            Path source = Paths.get(inputFileOnError);
            Path target = Paths.get(doneDirectory.name() + source.getFileName() + "_ERROR");
            Files.move(source, target);
        } catch (IOException e) {
            throw new FileMovingException("Unable to move " + inputFileOnError, e);
        }
    }

    private void handleSuccessfullExecution(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        String input = jobParameters.getString("input");
        try {
            Path source = Paths.get(input);
            Path target = Paths.get(doneDirectory.name() + source.getFileName() + "_" + jobParameters.getLong("run.id"));
            Files.move(source, target);
        } catch (IOException e) {
            throw new FileMovingException("Unable to move " + input, e);
        }
    }
}
