package com.xue.demo.api;

import com.xue.demo.api.config.NewRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ribonn开启负载均衡的两种方式：
 * 1、spring-cloud-starter-netflix-eureka-client包已默认集成ribbon，只需要在RestTemplate添加@LoadBalance注解即可
 * 2、添加pom配置：spring-cloud-starter-netflix-ribbon；在启动主程序上添加@RibbonClient注解；在配置文件上添加listOfServers属性
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker		//添加hystrix熔断器
//@RibbonClient(name="user",configuration=NewRuleConfig.class)	//自定义客户端负载均衡策略（表示使用服务名user时，使用配置NewRuleConfig）
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
