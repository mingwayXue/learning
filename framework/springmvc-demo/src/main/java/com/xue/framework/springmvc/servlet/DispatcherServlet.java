package com.xue.framework.springmvc.servlet;

import com.xue.framework.springmvc.annotation.*;
import com.xue.framework.springmvc.controller.UserController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingway on Date:2019-04-10 12:05.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1, initParams = {
		@WebInitParam(name = "base-package", value = "com.xue.framework.springmvc")
})
public class DispatcherServlet extends HttpServlet {

	// 扫描的基包
	private String basePackage = "";

	// 基包下面所有包限定类名
	private List<String> packageNames = new ArrayList<String>();

	// 注解实例化信息	注解名:实例化对象
	private Map<String, Object> instanceMap = new HashMap<String, Object>();

	// 带包限定名称:注解上的名称
	private Map<String, String> nameMap = new HashMap<String, String>();

	// url地址和执行方法的映射关系
	private Map<String, Method> urlMethodMap = new HashMap<String, Method>();

	// 方法与限定类名映射关系（主要是通过方法找到该方法的对象，然后利用反射执行该方法）
	private Map<Method, String> methodPackageMap = new HashMap<Method, String>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		basePackage = config.getInitParameter("base-package");

		try{
			// 1、扫描基包，获取全部带包路径限定名
			scanBasePackage(basePackage);

			// 2、把@Controller/@Service/@Repository中的类实例放入map中，其KEY为注解上的value
			instance(packageNames);

			// 3、Ioc注入Bean
			springIoc();

			// 4、处理url与方法的映射关系
			handlerUrlMethodMap();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri =req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = uri.replaceAll(contextPath, "");

		// 通过path找到method
		Method method = urlMethodMap.get(path);
		if (method != null) {
			String packageName = methodPackageMap.get(method);
			String controllerName = nameMap.get(packageName);

			// 获取Controller对象，使用反射执行
			UserController userController = (UserController) instanceMap.get(controllerName);
			try {
				method.setAccessible(true);
				method.invoke(userController);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * url与method的映射关系
	 * @throws ClassNotFoundException
	 */
	private void handlerUrlMethodMap() throws ClassNotFoundException {
		if (packageNames.size() < 1) {
			return;
		}

		for (String string : packageNames) {
			Class c = Class.forName(string);
			if (c.isAnnotationPresent(Controller.class)) {
				Method[] methods = c.getMethods();
				StringBuffer baseUrl = new StringBuffer();
				if (c.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
					baseUrl.append(requestMapping.value());
				}
				for (Method method : methods) {
					if (method.isAnnotationPresent(RequestMapping.class)) {
						RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
						baseUrl.append(requestMapping.value());
						urlMethodMap.put(baseUrl.toString(), method);
						methodPackageMap.put(method, string);
					}
				}
			}
		}
	}

	/**
	 * 依赖注入
	 * @throws IllegalAccessException
	 */
	private void springIoc() throws IllegalAccessException {
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();

			for (Field field : fields) {
				if (field.isAnnotationPresent(Qualifier.class)) {
					String name = field.getAnnotation(Qualifier.class).value();
					field.setAccessible(true);
					field.set(entry.getValue(), instanceMap.get(name));
				}
			}
		}
	}

	/**
	 * 实例化
	 * @param packageNames
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		if (packageNames.size() < 1) {
			return;
		}

		for (String string : packageNames) {
			Class c = Class.forName(string);
			if (c.isAnnotationPresent(Controller.class)) {
				Controller controller = (Controller) c.getAnnotation(Controller.class);
				String controllerName = controller.value();

				instanceMap.put(controllerName, c.newInstance());
				nameMap.put(string, controllerName);
				System.out.println("Controller:" + string + ", value:" + controller.value());
			} else if (c.isAnnotationPresent(Service.class)) {
				Service service = (Service) c.getAnnotation(Service.class);
				String serviceName = service.value();

				instanceMap.put(serviceName, c.newInstance());
				nameMap.put(string, serviceName);
				System.out.println("Service:" + string + ", value:" + service.value());
			} else if (c.isAnnotationPresent(Repository.class)) {
				Repository repository = (Repository) c.getAnnotation(Repository.class);
				String repositoryName = repository.value();

				instanceMap.put(repositoryName, c.newInstance());
				nameMap.put(string, repositoryName);
				System.out.println("Repository:" + string + ", value:" + repository.value());
			}
		}
	}

	/**
	 * 扫描包
	 * @param basePackage
	 */
	private void scanBasePackage(String basePackage) {
		URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));	// 将包名中的"."转换成"/"
		File basePackageFile = new File(url.getPath());
		System.out.println("scan:" + basePackageFile);
		File[] childFiles = basePackageFile.listFiles();
		for (File file : childFiles) {
			if (file.isDirectory()) {	// 如果是目录，则递归调用该方法
				scanBasePackage(basePackage + "." + file.getName());
			} else if (file.isFile()) {
				packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);	// 此处需要去除.class的后缀
			}
		}
	}
}
