package com.xue.demo.controller;

import com.xue.demo.domain.User;
import com.xue.demo.user.IUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by mingway on Date:2018-12-03 11:37.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
public class TestController {

	@Autowired
	private IUserApi userApi;		//这里的API名称需要跟驱动类下的自定义bean一样

	@GetMapping("/")
	public void test() {
//		//测试信息提取--此时不会发出请求，但是会进入创建的代理类
//		userApi.getAllUser();
//		userApi.getUserById("123");
//		userApi.deleteById("123");
//		userApi.createUser(Mono.just(User.builder().name("123").age(12).build()));

		//直接调用接口，实现调用rest接口的效果
		Flux<User> userFlux = userApi.getAllUser();
		userFlux.subscribe(System.out::println);
	}

}
