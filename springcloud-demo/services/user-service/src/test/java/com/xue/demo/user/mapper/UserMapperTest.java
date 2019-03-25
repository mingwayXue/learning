package com.xue.demo.user.mapper;

import com.xue.demo.user.UserServiceApplicationTests;
import com.xue.demo.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-10-15 16:02.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class UserMapperTest extends UserServiceApplicationTests{

	@Autowired
	private UserMapper userMapper;

	@Test
	public void selectById() throws Exception {
		User user = userMapper.selectById(42L);
		log.info("USER = {}", user.getName());
	}

}