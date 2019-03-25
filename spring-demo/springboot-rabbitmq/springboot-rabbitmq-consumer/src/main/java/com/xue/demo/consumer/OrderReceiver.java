package com.xue.demo.consumer;

import com.rabbitmq.client.Channel;
import com.xue.demo.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mingway on Date:2019-03-13 17:52.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Component
public class OrderReceiver {

	/**
	 * 消息监听器
	 * @param order	消息体（使用@Payload注解表示）
	 * @param headers	消息头（使用@Headers注解表示）
	 * @param channel
	 */
	@RabbitListener(bindings = @QueueBinding(
				value = @Queue(value = "order-queue", durable = "true"),
				exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
				key = "order.*"
			)
	)
	@RabbitHandler
	public void onOrderMsg(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		System.out.println("--------------收到消息，开始消费--------------");
		System.out.println("订单ID： " + order.getId());

		Long delivery_tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

		//消费完消息，手工确认
		channel.basicAck(delivery_tag, false);
	}

}
