package com.xue.scala.controller;

import com.xue.scala.domain.MetaDatabase;
import com.xue.scala.service.MetaDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingway on Date:2019-05-14 16:02.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
@RequestMapping("/meta/database")
public class MetaDatabaseController {

	@Autowired
	private MetaDatabaseService metaDatabaseService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(@ModelAttribute MetaDatabase metaDatabase) {
		metaDatabaseService.save(metaDatabase);
		return "success";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<MetaDatabase> query() {
		return metaDatabaseService.query();
	}
}
