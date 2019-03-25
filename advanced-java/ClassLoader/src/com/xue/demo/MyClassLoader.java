package com.xue.demo;

import java.io.*;

/** 自定义类加载器
 * Created by mingway on Date:2018-12-20 17:07.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class MyClassLoader extends ClassLoader {

	private String path;	//加载类路径

	private String name;	//类加载器名称

	public MyClassLoader(String path, String name) {
		super();		//指定父类加载器为系统启动类加载器BootstrapClassLoader
		this.path = path;
		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String path, String name) {
		super(parent);		//显式指定父类加载器
		this.path = path;
		this.name = name;
	}

	/**
	 * 加载自定义的类，通过这个自定义的ClassLoader
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = readClassFileToByteArray(name);
		return this.defineClass(name, data, 0, data.length);
	}

	/** com.xue.demo -->> F:/xue/java/demo/com/xue/demo/Demo.class
	 * 获取.class文件的字节数组
	 * @param name
	 * @return
	 */
	private byte[] readClassFileToByteArray(String name) {
		InputStream is = null;
		byte[] returnData = null;
		name = name.replaceAll("\\.", "/");
		String filePath = this.path + name + ".class";
		File file = new File(filePath);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(file);
			int tmp = 0;
			while ((tmp = is.read()) != -1) {
				os.write(tmp);
			}
			returnData = os.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnData;
	}

	@Override
	public String toString() {
		return "MyClassLoader{" +
				"name='" + name + '\'' +
				'}';
	}
}
