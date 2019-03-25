package com.xue.demo.producer;

import com.xue.demo.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mingway on Date:2019-03-13 17:09.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Component
public class OrderSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMsg(Order order) {
		CorrelationData correlationData = new CorrelationData();
		correlationData.setId(order.getMessageId());

		rabbitTemplate.convertAndSend("order-exchange"	//交换器
				,"order.abcd" //路由键
				, order //传输的对象，消息体对象
				, correlationData);	//correlationData表示消息唯一ID
	}
}
