package com.xue.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**	webflux测试
 * Created by mingway on Date:2018-11-30 16:55.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@RestController
public class TestDemo {

	@GetMapping("1")
	public String get1() {
		log.info("get1 start");
		String result = creatStr();
		log.info("get1 end");
		return result;
	}

	/**
	 * Mono返回0-1个元素
	 * @return
	 */
	@GetMapping("2")
	public Mono<String> get2() {
		log.info("get2 start");

		//惰性求值
		Mono<String> result = Mono.fromSupplier(() -> creatStr());

		log.info("get2 end");
		return result;
	}

	/**
	 * Flux返回0-N个元素
	 * @return
	 */
	@GetMapping(value = "3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	//返回的形式
	public Flux<String> get3() {
		log.info("get3 start");

		//惰性求值
		Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "flux data--" + i;
		}));

		log.info("get3 end");
		return result;
	}

	private String creatStr() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "do something";
	}
}
