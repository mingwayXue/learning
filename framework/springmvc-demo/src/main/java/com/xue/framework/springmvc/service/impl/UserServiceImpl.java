package com.xue.framework.springmvc.service.impl;

import com.xue.framework.springmvc.annotation.Qualifier;
import com.xue.framework.springmvc.annotation.Service;
import com.xue.framework.springmvc.dao.UserDao;
import com.xue.framework.springmvc.service.UserService;

/**
 * Created by mingway on Date:2019-04-10 17:30.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Qualifier("userDaoImpl")
	private UserDao userDao;

	@Override
	public void insert() {
		System.out.println("userService start insert ...");
		userDao.insert();
		System.out.println("userService end insert ...");
	}
}
