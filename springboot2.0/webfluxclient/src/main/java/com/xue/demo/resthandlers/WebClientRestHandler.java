package com.xue.demo.resthandlers;

import com.xue.demo.domain.MethodInfo;
import com.xue.demo.domain.ServerInfo;
import com.xue.demo.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by mingway on Date:2018-12-03 16:27.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class WebClientRestHandler implements RestHandler {
	private WebClient client;

	/**
	 * 初始化webclient
	 * @param serverInfo
	 */
	@Override
	public void init(ServerInfo serverInfo) {
		this.client = WebClient.create(serverInfo.getUrl());
	}

	/**
	 * 处理rest请求
	 * @param methodInfo
	 * @return
	 */
	@Override
	public Object invokeRest(MethodInfo methodInfo) {
		Object result = null;	//返回结果

		WebClient.RequestBodySpec accept = this.client
				//请求方法
				.method(methodInfo.getMethod())
				//请求的url和参数
				.uri(methodInfo.getUrl(), methodInfo.getParams())
				//请求格式
				.accept(MediaType.APPLICATION_JSON);

		//发出请求
		//判断是否带了body
		WebClient.ResponseSpec retrieve = null;
		if (methodInfo.getBody() != null) {
			retrieve = accept.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
		} else {
			retrieve = accept.retrieve();
		}

		//异常处理
		retrieve.onStatus(status -> status.value() == 404, response -> Mono.just(new RuntimeException("Not Found")));

		//处理body
		if (methodInfo.isReturnFlux()) {
			result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
		} else {
			result = retrieve.bodyToMono(methodInfo.getReturnElementType());
		}

		return result;
	}
}
