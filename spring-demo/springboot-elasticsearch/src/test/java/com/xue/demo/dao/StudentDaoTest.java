package com.xue.demo.dao;

import com.xue.demo.SpringbootElasticsearchApplicationTests;
import com.xue.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-08-23 15:24.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class StudentDaoTest extends SpringbootElasticsearchApplicationTests{

	@Autowired
	private StudentDao dao;

	@Test
	public void save(){
		Student student = new Student();
		student.setId(2L);
		student.setAge(11);
		student.setName("李四");
		dao.save(student);
	}

	@Test
	public void queryById(){
		Student student = dao.queryStudentById(1L);
		log.info("student = {}", student);
	}

}