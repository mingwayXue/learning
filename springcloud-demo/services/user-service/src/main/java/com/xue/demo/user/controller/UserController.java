package com.xue.demo.user.controller;

import com.xue.demo.user.common.RestResponse;
import com.xue.demo.user.exception.IllegalParamsException;
import com.xue.demo.user.exception.UserException;
import com.xue.demo.user.model.User;
import com.xue.demo.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mingway on 2018/9/2.
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /*-------------------查询------------------*/
    @RequestMapping("getId")
    public RestResponse<User> getUserById(Long id){
        User user = userService.getUserById(id);
        return RestResponse.success(user);
    }

    @RequestMapping("getList")
    public RestResponse<List<User>> getUserList(@RequestBody User user) {
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }

    /*-------------------注册------------------*/
    @RequestMapping("add")
    public RestResponse<User> add(@RequestBody User user) {
        userService.addAccount(user, user.getEnableUrl());
        return RestResponse.success();
    }

    @RequestMapping("enable")
    public RestResponse<User> enable(String key) {
        userService.enable(key);
        return RestResponse.success();
    }

    /*-------------------登录和鉴权------------------*/
    @RequestMapping("auth")
    public RestResponse<User> auth(@RequestBody User user) {
        User finalUser = userService.auth(user.getEmail(), user.getPasswd());
        return RestResponse.success(finalUser);
    }

    @RequestMapping("get")
    public RestResponse<User> getUserByToken(String token) {
        User finalUser = userService.getLoginedUserByToken(token);
        return RestResponse.success(finalUser);
    }

    @RequestMapping("logout")
    public RestResponse<Object> logout(String token) {
        userService.invalidate(token);
        return RestResponse.success();
    }



    /*-------------------测试------------------*/
//    @RequestMapping("/getusername")
//    public RestResponse<String> getusername(Long id) {
//        log.info("Incoming Request");
//        if (id == null) {
//            throw new IllegalParamsException(IllegalParamsException.Type.WRONG_TYPE, "类型错误！");
//        }
//        return RestResponse.success("user-username");
//    }
}
