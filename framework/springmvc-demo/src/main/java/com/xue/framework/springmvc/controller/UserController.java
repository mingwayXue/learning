package com.xue.framework.springmvc.controller;

import com.xue.framework.springmvc.annotation.Controller;
import com.xue.framework.springmvc.annotation.Qualifier;
import com.xue.framework.springmvc.annotation.RequestMapping;
import com.xue.framework.springmvc.service.UserService;

/**
 * Created by mingway on Date:2019-04-10 17:27.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Qualifier("userServiceImpl")
	private UserService userService;

	@RequestMapping("/insert")
	public void insert() {
		userService.insert();
	}

}
