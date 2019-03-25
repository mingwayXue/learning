package com.xue.demo.handlers;

import com.xue.demo.domain.User;
import com.xue.demo.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**	webflux使用Router Functions，类似于springmvc的controller，还需要配置routers,类似于springmvc的dispatcher
 * 		都是输入一个ServerRequest，返回一个ServerResponse
 * Created by mingway on Date:2018-12-03 9:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Component
public class UserHandler {

	private UserRepository userRepository;

	public UserHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 查找所有用户
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse> getAllUser(ServerRequest serverRequest) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userRepository.findAll(), User.class);
	}

	/**
	 * 创建用户
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
		Mono<User> userMono = serverRequest.bodyToMono(User.class);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userRepository.saveAll(userMono), User.class);
	}

	/**
	 * 根据id删除用户
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");

		return this.userRepository.findById(id).
				flatMap(uer -> this.userRepository.delete(uer).
						then(ServerResponse.ok().build())).
				switchIfEmpty(ServerResponse.notFound().build());
	}
}
