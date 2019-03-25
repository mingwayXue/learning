package com.xue.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**	服务器相关信息
 * Created by mingway on Date:2018-12-03 11:25.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Target(ElementType.TYPE)	//注解加的位置
@Retention(RetentionPolicy.RUNTIME)		//运行时注解（保留至运行时）
public @interface ApiServer {
	String value() default "";
}
