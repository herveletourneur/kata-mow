package org.mow.it.now.batch;

import org.mow.it.now.batch.filepoller.DoneDirectory;
import org.mow.it.now.batch.filepoller.InputDirectory;
import org.mow.it.now.batch.filepoller.OutputDirectory;
import org.mow.it.now.batch.job.MowJob;
import org.mow.it.now.batch.step.GardenLineCallbackHandler;
import org.mow.it.now.batch.step.ProgrammedMowFieldSetMapper;
import org.mow.it.now.batch.step.SimulationProcessor;
import org.mow.it.now.core.FinalMowPosition;
import org.mow.it.now.core.ProgrammedMow;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
public class MowBatchConfiguration {
    private static final String MOW_JOB_NAME = "MowItNowSimulationJob";

    @Value("${filepoller.input}")
    private String inputDirectory;

    @Value("${filepoller.output}")
    private String outputDirectory;

    @Value("${filepoller.done}")
    private String doneDirectory;

    @Bean
    public Job mowJob(JobRepository jobRepository, Step step) {
        return new JobBuilder(MOW_JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                                                          .flow(step)
                                                          .end()
                                                          .build();
    }

    @Bean
    public MowJob mowJobName() {
        return new MowJob(MOW_JOB_NAME);
    }

    @Bean
    public Step mowSimultationStep(JobRepository jobRepository,
                                   SimulationProcessor simulationProcessor,
                                   ItemWriter<FinalMowPosition> simulationWriter,
                                   ItemReader<ProgrammedMow> simulationReader,
                                   PlatformTransactionManager transactionManager) {
        return new StepBuilder("MowSimulationStep", jobRepository)
                .<ProgrammedMow, FinalMowPosition>chunk(1, transactionManager)
                .reader(simulationReader)
                .processor(simulationProcessor)
                .writer(simulationWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ProgrammedMow> fileReader(@Value("#{stepExecution}") StepExecution stepExecution) {
        String inputPath = Objects.requireNonNull(stepExecution.getJobParameters().getString("input"));
        return new FlatFileItemReaderBuilder<ProgrammedMow>()
                .name("MowSimulationReader")
                .linesToSkip(1)
                .resource(new PathResource(inputPath))
                .skippedLinesCallback(new GardenLineCallbackHandler(stepExecution, " "))
                .lineTokenizer(lineTokenizer())
                .fieldSetMapper(new ProgrammedMowFieldSetMapper(stepExecution))
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<FinalMowPosition> fileWriter(@Value("#{jobParameters}") Map<String, Object> jobParameters) {
        String output = Objects.requireNonNull(jobParameters.get("output")) + "_" + Objects.requireNonNull(jobParameters.get("run.id"));
        return new FlatFileItemWriterBuilder<FinalMowPosition>()
                .name("MowSimulationResultWriter")
                .resource(new PathResource(output))
                .lineAggregator(FinalMowPosition::position)
                .build();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(jobLauncherTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public ThreadPoolTaskExecutor jobLauncherTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setThreadNamePrefix("JobLauncherThreadExecutor");
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler filePollerTaskExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        return scheduler;
    }


    @Bean
    public LineTokenizer lineTokenizer() {
        return new DelimitedLineTokenizer(" ");
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public OutputDirectory outputDirectory() {
        return new OutputDirectory(outputDirectory);
    }

    @Bean
    public InputDirectory inputDirectory() {
        return new InputDirectory(inputDirectory);
    }

    @Bean
    public DoneDirectory doneDirectory() {
        return new DoneDirectory(doneDirectory);
    }
}
