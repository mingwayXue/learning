package com.xue.demo.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommentSvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentSvrApplication.class, args);
	}
}
