package com.xue.demo.controller;

import com.xue.demo.domain.User;
import com.xue.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Created by mingway on Date:2018-12-01 10:04.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
@RequestMapping("user")
public class UserController {

//	注入dao
//	@Autowired
	private final UserRepository repository;

	//官方推荐使用构造器注入的方式
	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * 以数组形式一次性返回
	 * @return
	 */
	@GetMapping("/")
	public Flux<User> getAll() {
		return repository.findAll();
	}

	/**
	 * 以SSE形式多次返回数据
	 * @return
	 */
	@GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	//以流的方式输出（SSE--send stream event）
	public Flux<User> streamGetAll() {
		return repository.findAll();
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public Mono<User> createUser(@Valid @RequestBody User user) {
		return repository.save(user);		//jpa里面的新增和更新都是使用save；当存在id时为修改，当不存在id时，为新增
	}

	/**
	 * 删除用户，成功返回200，失败返回404
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id) {
		return this.repository.findById(id)
				//当需要操作数据，并返回一个Mono时，使用flatMap
				//当不需要操作数据，只是转换数据时，使用map
				.flatMap(user -> this.repository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 更新数据
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/{id}")
	public Mono<ResponseEntity<User>> updateById(@PathVariable("id") String id, @RequestBody User user) {
		return this.repository.findById(id).
				//操作数据
				flatMap(u -> {
					u.setAge(user.getAge());
					u.setName(user.getName());
					return repository.save(u);
				})
				//转换数据
				.map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 根据id查找user信息
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Mono<ResponseEntity<User>> findById(@PathVariable("id") String id) {
		return this.repository.findById(id)
				.map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/age/{start}/{end}")
	public Flux<User> findByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
		return this.repository.findByAgeBetween(start, end);
	}
}
