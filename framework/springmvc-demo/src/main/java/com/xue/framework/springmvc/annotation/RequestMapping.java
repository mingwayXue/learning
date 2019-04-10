package com.xue.framework.springmvc.annotation;

import java.lang.annotation.*;

/**
 * Created by mingway on Date:2019-04-10 11:55.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value();
}
