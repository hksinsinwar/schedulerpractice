package com.example.batchprocessing.config;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MainBatchConfigurer implements BatchConfigurer {
    @Autowired
    private ExecutionContextDao mongoExecutionContextDao;
    @Autowired
    private JobExecutionDao mongoJobExecutionDao;
    @Autowired
    private JobInstanceDao mongoJobInstanceDao;
    @Autowired
    private StepExecutionDao mongoStepExecutionDao;
    @Override
    @Bean
    public JobRepository getJobRepository() {
        return new SimpleJobRepository(
          mongoJobInstanceDao, 
          mongoJobExecutionDao, 
          mongoStepExecutionDao, 
          mongoExecutionContextDao
        );
    }
    @Override
    @Bean
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }
    @Override
    @Bean
    public SimpleJobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
    @Override
    @Bean
    public JobExplorer getJobExplorer() {
        return new SimpleJobExplorer(
          mongoJobInstanceDao, 
          mongoJobExecutionDao, 
          mongoStepExecutionDao, 
          mongoExecutionContextDao
        );
    }
    
    @Bean
	public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
		return new JobBuilderFactory(getJobRepository());
	}
	
	@Bean
	public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilderFactory(getJobRepository(), getTransactionManager());
	}
}

