package com.xue.demo.user.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.http.client.HttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 两种方式使用RestTemplate，一种加@LoadBalanced负载均衡，一种直连
 */
@Configuration
public class RestAutoConfig {

	public static class RestTemplateConfig {

		/**
		 * 如果没有添加@LoadBalanced注解，则在使用RestTemplate进行远程通信时，必须使用ip:port方式，添加之后，则可以将其转换成服务名的方式
		 * @param httpclient
		 * @return
		 */
		@Bean
		@LoadBalanced //spring 对restTemplate bean进行定制，加入loadbalance拦截器进行ip:port的替换
		RestTemplate lbRestTemplate(HttpClient httpclient) {
			RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
		    template.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("utf-8")));
		    template.getMessageConverters().add(1,new FastJsonHttpMessageConvert5());
		    return template;
		}
		
		@Bean
		RestTemplate directRestTemplate(HttpClient httpclient) {
			RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
		    template.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("utf-8")));
		    template.getMessageConverters().add(1,new FastJsonHttpMessageConvert5());
		    return template;
		}
		
		 public static class FastJsonHttpMessageConvert5 extends FastJsonHttpMessageConverter4{
	          
	          static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	          
	          public FastJsonHttpMessageConvert5(){
	            setDefaultCharset(DEFAULT_CHARSET);
	            setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,new MediaType("application","*+json")));
	          }

	        }
	}

}
