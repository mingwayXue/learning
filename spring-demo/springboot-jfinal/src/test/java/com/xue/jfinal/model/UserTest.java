package com.xue.jfinal.model;

import com.alibaba.fastjson.JSON;
import com.xue.jfinal.Jdemo01ApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2019-04-13 15:51.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class UserTest extends Jdemo01ApplicationTests {

	@Autowired
	private User userDao;

	@Test
	public void test01() {
		List<User> users = new ArrayList<>();
//		users = userDao.find("select * from user");
//		log.info(JSON.toJSONString(users));
		User user = userDao.findFirst("select * from user limit 1");
		log.info("user:" + user);
	}
}