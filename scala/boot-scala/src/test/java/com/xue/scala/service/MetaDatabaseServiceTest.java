package com.xue.scala.service;

import com.xue.scala.domain.MetaDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2019-05-14 15:53.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaDatabaseServiceTest {

	@Autowired
	private MetaDatabaseService metaDatabaseService;

	@Test
	public void save() throws Exception {
		MetaDatabase metaDatabase = new MetaDatabase();
		metaDatabase.setName("defult");
		metaDatabase.setLocation("hdfs://hadoop000:8020/user/hive/warehouse");

		metaDatabaseService.save(metaDatabase);
	}

	@Test
	public void query() throws Exception {
		Iterable<MetaDatabase> metaDatabases = metaDatabaseService.query();
		for (MetaDatabase metaDatabase : metaDatabases) {
			System.out.println(metaDatabase.getLocation());
		}
	}

}