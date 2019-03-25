package com.xue.demo.user;

import com.xue.demo.annotation.ApiServer;
import com.xue.demo.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by mingway on Date:2018-12-03 11:20.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ApiServer("http://127.0.0.1:8080/users")
public interface IUserApi {

	@GetMapping("/")
	Flux<User> getAllUser();

	@GetMapping("/{id}")
	Mono<User> getUserById(@PathVariable String id);

	@DeleteMapping("/{id}")
	Mono<User> deleteById(@PathVariable String id);

	@PostMapping("/")
	Mono<User> createUser(@RequestBody Mono<User> user);

}
