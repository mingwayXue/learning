package com.xue.jfinal.service;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xue.jfinal.annotation.JFinalTransactional;
import org.springframework.stereotype.Service;

/**
 * Created by mingway on Date:2019-04-13 11:57.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
public class TestService {

	@JFinalTransactional
	public Object testTran() {
		Record record = new Record();
		record.set("id", 10);
		record.set("name", "小英子");
		Db.save("user", record);
//		if (true) {
//			throw new RuntimeException("test");
//		}
		return Ret.by("msg", "success");
	}
}
