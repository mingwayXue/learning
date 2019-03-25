package com.xue.demo.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2		//添加@EnableSwagger2注解，生成文档
public class HouseSvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseSvrApplication.class, args);
	}
}
