package com.xue.jfinal;

import com.xue.jfinal.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class Jdemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(Jdemo01Application.class, args);
	}


	@Bean
	public static final User userDao() {
		return new User();
	}
}
