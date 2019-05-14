package com.xue.jfinal.service;

import com.xue.jfinal.Jdemo01ApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2019-04-13 12:15.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class TestServiceTest extends Jdemo01ApplicationTests {

	@Autowired
	private TestService service;

	@Test
	public void testTran() throws Exception {
		service.testTran();
	}

}