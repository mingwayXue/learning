package com.xue.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mingway on Date:2018-08-18 11:11.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
public class User implements Serializable {
	private Integer id;

	private String name;
}
