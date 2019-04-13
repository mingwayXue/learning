package com.xue.jfinal.annotation;

import java.lang.annotation.*;

/** JFinal的事务注解
 * Created by mingway on Date:2019-04-13 12:09.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JFinalTransactional {

}
