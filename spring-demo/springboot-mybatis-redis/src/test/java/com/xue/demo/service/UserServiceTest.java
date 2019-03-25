package com.xue.demo.service;

import com.xue.demo.SpringMybatisRedisApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-08-20 9:41.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class UserServiceTest extends SpringMybatisRedisApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void findById() throws Exception {
		log.info("---------user_info:{}---------- ", userService.findById(1));
	}

}