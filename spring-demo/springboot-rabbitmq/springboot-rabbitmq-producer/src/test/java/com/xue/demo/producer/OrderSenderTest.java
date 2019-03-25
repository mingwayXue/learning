package com.xue.demo.producer;

import com.xue.demo.SpringbootRabbitmqProducerApplicationTests;
import com.xue.demo.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2019-03-13 17:26.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class OrderSenderTest extends SpringbootRabbitmqProducerApplicationTests{

	@Autowired
	private OrderSender orderSender;

	@Test
	public void sendMsg() throws Exception {
		Order order = new Order();
		order.setId("20190313001");
		order.setName("测试12312");
		order.setMessageId(UUID.randomUUID().toString());
		orderSender.sendMsg(order);
	}

}