package com.example.batchprocessing.model;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.util.HttpConstants;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.example.batchprocessing.async.http.NettyHttpConfiguration;

public class HttpJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getMergedJobDataMap();
		String url = (String) dataMap.get("HTTP_URL");
		AsyncHttpClient AsyncHttpClient = NettyHttpConfiguration.asyncHttpClient;
		Request getRequest = new RequestBuilder(HttpConstants.Methods.GET)
				  .setUrl(url)
				  .build();
		//AsyncHttpClient.executeRequest(request, handler)
		System.out.println("AsyncRequest Build");
	}

}
