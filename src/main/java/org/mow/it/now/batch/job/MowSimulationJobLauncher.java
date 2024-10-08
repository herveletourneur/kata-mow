package org.mow.it.now.batch.job;

import org.mow.it.now.common.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Component
public class MowSimulationJobLauncher {
    private static final Logger logger = LoggerFactory.getLogger(MowSimulationJobLauncher.class);

    private final Job mowJob;
    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;

    public MowSimulationJobLauncher(Job mowJob, JobExplorer jobExplorer, JobLauncher jobLauncher) {
        this.mowJob = mowJob;
        this.jobExplorer = jobExplorer;
        this.jobLauncher = jobLauncher;
    }


    public Either<JobExecution, MowSimulationJobParameter> launch(MowSimulationJobParameter mowSimulationJobParameter) {

        try {
            JobParameters jobParameters = new JobParametersBuilder(jobExplorer)
                    .getNextJobParameters(mowJob)
                    .addString("input", mowSimulationJobParameter.input())
                    .addString("output", mowSimulationJobParameter.output())
                    .toJobParameters();
            return Either.ofLeft(jobLauncher.run(mowJob, jobParameters));
        } catch (JobExecutionException e) {
            logger.error("An error has occured during processing of batch with parameter {}", mowSimulationJobParameter, e);
            return Either.ofRight(mowSimulationJobParameter);
        }
    }
}
