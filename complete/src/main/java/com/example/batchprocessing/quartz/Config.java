package com.example.batchprocessing.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Scheduler getScheduler() throws SchedulerException {
		SchedulerFactory schFactory = new StdSchedulerFactory("quartz.properties");
		return schFactory.getScheduler();
	}

//	
}
