package com.xue.jfinal.controller;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingway on Date:2019-04-13 9:13.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
@RequestMapping("/user")
public class TestController {

	@GetMapping("/test01")
	public Object test01(){

		Record  record = Db.findFirst("select * from user limit 1");
		return Ret.ok().set("data", record).set("msg", "success");
	}
}
