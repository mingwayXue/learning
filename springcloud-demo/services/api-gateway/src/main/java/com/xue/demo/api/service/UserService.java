package com.xue.demo.api.service;

import com.xue.demo.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mingway on 2018/9/2.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getusername(Long id) {
//        return userDao.getusername(id);
		return null;
    };
}
