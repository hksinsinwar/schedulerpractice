package com.example.batchprocessing.async.http;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyHttpConfiguration {

	public static AsyncHttpClient asyncHttpClient = SingletonHolder.instance;

	private static class SingletonHolder {
		public static final AsyncHttpClient instance = asyncHttpClient();

		private static AsyncHttpClient asyncHttpClient() {
			DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config();
			clientBuilder.setConnectTimeout(10000);
			clientBuilder.setConnectionTtl(20000);
			return Dsl.asyncHttpClient(clientBuilder);
		}
	}

}
