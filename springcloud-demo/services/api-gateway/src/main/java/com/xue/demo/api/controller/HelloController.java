package com.xue.demo.api.controller;

import com.xue.demo.api.common.RestResponse;
import com.xue.demo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mingway on 2018/9/2.
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @RequestMapping("/index1")
    public List<ServiceInstance> getRegister(){
        return discoveryClient.getInstances("user");        //对应user服务的spring-application-name
    }

    @RequestMapping("/test")
    public RestResponse<String> getusername(Long id) {
        return RestResponse.success(userService.getusername(id));
    }
}
