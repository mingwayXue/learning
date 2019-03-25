package com.xue.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by mingway on Date:2018-08-23 15:11.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
//指定索引名，类型名，分片数，副本数
@Document(indexName = "es_demo", type = "student", shards = 3, replicas = 0)
public class Student {

	@Id
	private Long id;

	@Field
	private String name;

	@Field
	private Integer age;

	@Field
	private String remark;

}
