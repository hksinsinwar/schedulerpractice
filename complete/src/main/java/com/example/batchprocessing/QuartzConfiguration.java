package com.example.batchprocessing;

import org.asynchttpclient.AsyncHttpClient;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batchprocessing.model.HttpJob;

@Configuration
public class QuartzConfiguration {
	

	@Bean
	public JobDetail jobDetail() {
		
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("HTTP_URL", "www.news.google.com");
		//jobDataMap.put("AsyncHttpClient", AsyncHttpClient);
	    return JobBuilder.newJob().ofType(HttpJob.class)
	      .storeDurably()
	      .withIdentity("Qrtz_Job_Detail")  
	      .withDescription("Invoke Sample Job service...").setJobData(jobDataMap)
	      .build();
	}

	@Bean
	public Trigger trigger(JobDetail job) {
	    return TriggerBuilder.newTrigger().forJob(job)
	      .withIdentity("Qrtz_Trigger")
	      .withDescription("Sample trigger")
	      .withSchedule(CronScheduleBuilder.cronSchedule("1/10 * * * * ?"))
	      .build();
	}

	
}
