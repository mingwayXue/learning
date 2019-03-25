package com.xue.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching			//当使用spring的默认配置redis时，需要该注解，自行使用注解配置redis时，可加，可不加
public class SpringbootMybatisRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRedisApplication.class, args);
	}
}
