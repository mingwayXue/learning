package com.xue.demo.dao;

import com.xue.demo.SpringMybatisRedisApplicationTests;
import com.xue.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-08-18 11:14.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class UserDaoTest extends SpringMybatisRedisApplicationTests{

	@Autowired
	private UserDao userDao;

	@Test
	public void findUserById() throws Exception {
		User user = userDao.findUserById(1);
		log.info("user = {}", user);
	}

	@Test
	public void findId() throws Exception {
		User user = userDao.findId(2);
		log.info("user = {}", user);
	}

	@Test
	public void findAll() throws Exception {
		List<User> userList = userDao.findAll();
		log.info("users = {}", userList);
	}

}