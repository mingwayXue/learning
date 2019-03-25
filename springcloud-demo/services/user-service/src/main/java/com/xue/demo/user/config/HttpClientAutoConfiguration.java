package com.xue.demo.user.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {

	private final HttpClientProperties properties;

	public HttpClientAutoConfiguration(HttpClientProperties properties){
		this.properties = properties;
	}

	/**
	 * 在使用logbook日志系统时，需要添加对应的拦截器，并且，在配置文件添加日志级别
	 */
	@Autowired
	private LogbookHttpRequestInterceptor logbookHttpRequestInterceptor;

	@Autowired
	private LogbookHttpResponseInterceptor logbookHttpResponseInterceptor;

	/**
	 * httpclient bean 的定义
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(properties.getConnectTimeOut())
				.setSocketTimeout(properties.getSocketTimeOut()).build();// 构建requestConfig
		//设置httpClient属性
		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(properties.getAgent())
				.setMaxConnPerRoute(properties.getMaxConnPerRoute())
				.setMaxConnTotal(properties.getMaxConnTotaol())
				.addInterceptorFirst(logbookHttpRequestInterceptor)		//添加相关拦截器
				.addInterceptorFirst(logbookHttpResponseInterceptor)
				.build();
		return client;
	}
}
