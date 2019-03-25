package com.xue.demo.service;

import com.xue.demo.dao.UserDao;
import com.xue.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by mingway on Date:2018-08-20 9:38.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
@CacheConfig(cacheNames="user_test")	//指定一个redis的namaespace，以下的所有操作，都是在user_test之下
public class UserService {

	@Autowired
	private UserDao userDao;

	@Cacheable
	public User findById(Integer id) {
		System.out.println("----测试---：是否获取了缓存中的数据，是，则不出现");
		return userDao.findUserById(id);
	}

}
