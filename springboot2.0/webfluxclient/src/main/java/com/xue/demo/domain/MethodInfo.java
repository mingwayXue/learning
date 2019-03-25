package com.xue.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Created by mingway on Date:2018-12-03 12:05.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Data
@Builder	//对外保持private setter，而对属性的赋值采用Builder的方式
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

	/**
	 * 请求的url
	 */
	private String url;

	/**
	 * 请求的方法
	 */
	private HttpMethod method;

	/**
	 * 请求参数(url)
	 */
	private Map<String, Object> params;

	/**
	 * 请求body
	 */
	private Mono body;

	/**
	 * 返回时mono还是flux
	 */
	private boolean returnFlux;

	/**
	 * 返回对象类型
	 */
	private Class<?> returnElementType;

	/**
	 * 返回请秋body的类型
	 */
	private Class<?> bodyElementType;
}
