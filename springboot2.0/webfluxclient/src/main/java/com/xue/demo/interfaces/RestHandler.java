package com.xue.demo.interfaces;

import com.xue.demo.domain.MethodInfo;
import com.xue.demo.domain.ServerInfo;

/** rest请求调用handler
 * Created by mingway on Date:2018-12-03 15:43.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public interface RestHandler {

	/**
	 * 初始化服务器信息
	 * @param serverInfo
	 */
	void init(ServerInfo serverInfo);

	/**
	 * 调用rest请求，返回接口
	 * @param methodInfo
	 * @return
	 */
	Object invokeRest(MethodInfo methodInfo);
}
