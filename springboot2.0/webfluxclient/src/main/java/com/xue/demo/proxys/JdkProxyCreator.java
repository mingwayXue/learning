package com.xue.demo.proxys;

import com.xue.demo.annotation.ApiServer;
import com.xue.demo.domain.MethodInfo;
import com.xue.demo.domain.ServerInfo;
import com.xue.demo.interfaces.ProxyCreator;
import com.xue.demo.interfaces.RestHandler;
import com.xue.demo.resthandlers.WebClientRestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**	使用Jdk动态代理
 * Created by mingway on Date:2018-12-03 12:04.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class JdkProxyCreator implements ProxyCreator {

	@Override
	public Object creatObject(Class<?> type) {
		log.info("type: " + type);

		//根据接口获取Api服务器信息
		ServerInfo serverInfo = extractServerInfo(type);
		log.info("serverInfo: " + serverInfo);

		//给每个代理类都有一个实现类
		RestHandler handler = new WebClientRestHandler();
		//初始化服务器信息（初始化webclient）
		handler.init(serverInfo);

		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//根据方法和参数得到调用信息
				MethodInfo methodInfo = extractMethodInfo(method, args);
				log.info("methodInfo: " + methodInfo);
				//调用rest
				return handler.invokeRest(methodInfo);
			}

			/**
			 * 根据方法和参数获取调用信息
			 * @param method
			 * @param args
			 * @return
			 */
			private MethodInfo extractMethodInfo(Method method, Object[] args) {
				MethodInfo methodInfo = new MethodInfo();

				//得到请求的url和参数
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					//GET
					if (annotation instanceof GetMapping) {
						GetMapping a = (GetMapping) annotation;

						methodInfo.setUrl(a.value()[0]);	//获取url
						methodInfo.setMethod(HttpMethod.GET);		//获取请求类型
					}
					//POST
					else if (annotation instanceof PostMapping) {
						PostMapping a = (PostMapping) annotation;

						methodInfo.setUrl(a.value()[0]);	//获取url
						methodInfo.setMethod(HttpMethod.POST);		//获取请求类型
					}
					//DELETE
					else if (annotation instanceof DeleteMapping) {
						DeleteMapping a = (DeleteMapping) annotation;

						methodInfo.setUrl(a.value()[0]);	//获取url
						methodInfo.setMethod(HttpMethod.DELETE);		//获取请求类型
					}
				}

				//得到参数和body
				Parameter[] parameters = method.getParameters();
				//参数和值对应的map
				Map<String, Object> map = new LinkedHashMap<>();
				methodInfo.setParams(map);

				for (int i = 0; i < parameters.length; i++) {
					//是否带PathVariable
					PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);
					if (annoPath != null) {
						map.put(annoPath.value(), args[i]);
					}

					//是否带RequestBody
					RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);
					if (annoBody != null) {
						methodInfo.setBody((Mono<?>) args[i]);

						//设置请求body的类型
						methodInfo.setBodyElementType(extractElementType(parameters[i].getParameterizedType()));
					}
				}

				//提取返回对象信息
				extractReturnInfo(method, methodInfo);

				return methodInfo;
			}

			/**
			 * 提取返回对象信息
			 * @param method
			 * @param methodInfo
			 */
			private void extractReturnInfo(Method method, MethodInfo methodInfo) {
				//返回flux还是mono
				//isAssignableFrom判断类型是否某个的子类
				//instanceof判断实例是否某个子类
				boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
				methodInfo.setReturnFlux(isFlux);

				//得到对象的实际返回类型
				Class<?> elementType = extractElementType(method.getGenericReturnType());
				methodInfo.setReturnElementType(elementType);
			}

			/**
			 * 得到对象的实际返回类型
			 * @param genericReturnType
			 * @return
			 */
			private Class<?> extractElementType(Type genericReturnType) {
				Type[] actualTypeArgs = ((ParameterizedType) genericReturnType).getActualTypeArguments();
				return (Class<?>) actualTypeArgs[0];
			}
		});
	}


	/**
	 * 提取服务器信息
	 * @param type
	 * @return
	 */
	private ServerInfo extractServerInfo(Class<?> type) {
		ServerInfo serverInfo = new ServerInfo();
		ApiServer apiServer = type.getAnnotation(ApiServer.class);
		serverInfo.setUrl(apiServer.value());
		return serverInfo;
	}
}
