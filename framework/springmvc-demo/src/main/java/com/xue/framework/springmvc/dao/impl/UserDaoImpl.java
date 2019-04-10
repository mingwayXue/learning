package com.xue.framework.springmvc.dao.impl;

import com.xue.framework.springmvc.annotation.Repository;
import com.xue.framework.springmvc.dao.UserDao;

/**
 * Created by mingway on Date:2019-04-10 17:32.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Override
	public void insert() {
		System.out.println("execute userDao insert...");
	}
}
