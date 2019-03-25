package com.xue.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by mingway on Date:2018-12-01 9:53.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String id;

	private String name;

	private int age;

}
