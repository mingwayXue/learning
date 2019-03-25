package com.xue.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Created by mingway on Date:2018-08-20 9:21.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	//缓存管理器
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisCacheManager cacheManager = RedisCacheManager.builder(factory).build();
		return cacheManager;
	}
	//自定义缓存key生成策略，可按需自行配置
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator(){
			@Override
			public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for(Object obj:params){
					sb.append(obj.toString());
				}
				System.out.println("调用Redis生成key："+ sb.toString());
				return sb.toString();
			}
		};
	}
}
