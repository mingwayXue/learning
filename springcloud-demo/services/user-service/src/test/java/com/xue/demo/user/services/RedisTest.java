package com.xue.demo.user.services;

import com.xue.demo.user.UserServiceApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by mingway on Date:2018-10-15 15:23.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class RedisTest extends UserServiceApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test01() {
		redisTemplate.opsForValue().set("k01", "v01");
		log.info("Redis Test: k01={}", redisTemplate.opsForValue().get("k01"));

	}
}
