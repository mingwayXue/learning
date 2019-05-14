package com.xue.scala.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 数据库元数据
 * Created by mingway on Date:2019-05-14 15:46.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
@Entity
@Table
public class MetaDatabase {

	// id
	@Id
	@GeneratedValue
	private  Integer id;

	// 数据库名称
	private String name;

	// 数据库存放的文件系统地址
	private String location;
}
