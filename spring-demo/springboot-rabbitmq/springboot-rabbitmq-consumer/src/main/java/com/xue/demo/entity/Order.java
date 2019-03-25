package com.xue.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mingway on Date:2019-03-13 17:06.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
public class Order implements Serializable {

	private String id;

	private String name;

	private String messageId;

	public Order() {

	}

	public Order(String id, String name, String messageId) {
		this.id = id;
		this.name = name;
		this.messageId = messageId;
	}
}
