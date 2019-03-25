package com.xue.demo;

/**
 * Created by mingway on Date:2018-12-20 17:37.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		MyClassLoader loader = new MyClassLoader("F:/xue/","mingway");
		Class<?> c = loader.loadClass("Demo");	//获取Class对象
		c.newInstance();	//创建对象实例
	}
}
