package com.xue.demo.routers;

import com.xue.demo.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/** Router Functions中router，类似于springmvc中的dispatcher
 * Created by mingway on Date:2018-12-03 10:25.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Configuration
public class AllRouters {

	/**
	 * 编写路由
	 * @param userHandler
	 * @return
	 */
	@Bean
	RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
		return RouterFunctions.nest(
				RequestPredicates.path("/users"),		//前缀
				RouterFunctions.route(RequestPredicates.GET("/"), userHandler::getAllUser).	//方法类型、方法前缀及对应方法，这里为获取所有用户
						andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::createUser).	//创建用户
						andRoute(RequestPredicates.DELETE("/{id}"), userHandler::deleteById)	//删除用户

		);
	}

}
