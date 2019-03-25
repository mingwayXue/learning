package com.xue.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mingway on Date:2018-12-03 12:05.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
@Builder	//对外保持private setter，而对属性的赋值采用Builder的方式
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {

	/**
	 * 服务器url
	 */
	private String url;
}
