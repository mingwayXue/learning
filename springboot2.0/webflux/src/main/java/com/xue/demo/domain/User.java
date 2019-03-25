package com.xue.demo.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by mingway on Date:2018-12-01 9:53.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Document(collection = "user")	//对应mongodb里面的user表
@Data
public class User {

	@Id
	private String id;

	private String name;

	@Range(max = 30, min = 10, message = "年龄不能超过30岁，不能少于10岁")
	private int age;

}
