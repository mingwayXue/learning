package com.xue.demo;

import com.xue.demo.interfaces.ProxyCreator;
import com.xue.demo.proxys.JdkProxyCreator;
import com.xue.demo.user.IUserApi;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

@SuppressWarnings("ALL")
@SpringBootApplication
public class WebfluxclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxclientApplication.class, args);
	}

	/**
	 * 创建Jdk动态代理工具类
	 * @return
	 */
	@Bean
	public ProxyCreator jdkProxyCreator() {
		return new JdkProxyCreator();
	}

	/**
	 * Spring中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean，即FactoryBean，此处为FactoryBean（其返回的对象不是指定类的一个实例，而是是该工厂Bean的getObject方法所返回的对象）
	 * @param proxyCreator
	 * @return
	 */
	@Bean
	public FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
		return new FactoryBean<IUserApi>() {
			//返回代理类对象
			@Nullable
			@Override
			public IUserApi getObject() throws Exception {
				return (IUserApi) proxyCreator.creatObject(this.getObjectType());
			}

			//返回对象类型
			@Nullable
			@Override
			public Class<?> getObjectType() {
				return IUserApi.class;
			}
		};
	}
}
