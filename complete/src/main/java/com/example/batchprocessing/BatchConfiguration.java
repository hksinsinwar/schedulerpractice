package com.example.batchprocessing;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

// tag::setup[]
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    // end::setup[]

    // tag::readerwriterprocessor[]
    
    @Autowired
    Scheduler quartzScheduler;
    
    @Autowired
    JobDetail jobDetail;
    
    @Autowired
    Trigger trgger;
    
    
    
    File f;
    @PostConstruct
    public void init() throws IOException, SchedulerException{
    	f = new ClassPathResource("out.txt").getFile();
    	quartzScheduler.scheduleJob(jobDetail, trgger);
    }
    
    
    @Bean
    public FlatFileItemReader<Person> reader() {
    	FlatFileItemReader<Person> ffR = new FlatFileItemReader<Person>();
    	ffR.setResource(new ClassPathResource("sample-data.csv"));
    	ffR.setName("flatFileItemReader");
    	ffR.setLineMapper(new LineMapper<Person>() {
			@Override
			public Person mapLine(String line, int lineNumber) throws Exception {
				String[] values=line.split(",");
				return new Person(values[0], values[1]);
			}
		});;
        return ffR; 
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public BatchFileWriter<Person> writer() {
    return new BatchFileWriter<Person>(f);
       
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(BatchFileWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
            .<Person, Person> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
    // end::jobstep[]
}
