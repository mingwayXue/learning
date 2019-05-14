package com.xue.jfinal.config;

import com.alibaba.druid.wall.WallFilter;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.xue.jfinal.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/**
 * Created by mingway on Date:2019-04-13 8:59.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Configuration
@Slf4j
public class ActiveRecordPluginConfig{
	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;

	@Bean
	public ActiveRecordPlugin ininitActiveRecordPlugin(){
		DruidPlugin druidPlugin = new DruidPlugin(url,username,password);
		// 加强数据库安全
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType("mysql");
		druidPlugin.addFilter(wallFilter);
		// 添加 StatFilter 才会有统计数据
		// druidPlugin.addFilter(new StatFilter());
		// 必须手动调用start
		druidPlugin.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setTransactionLevel(Connection.TRANSACTION_REPEATABLE_READ);		//设置事务级别
		arp.setShowSql(true);
		arp.getEngine().setSourceFactory(new ClassPathSourceFactory());

		// 使用Model时必须添加该映射关系
		arp.addMapping("user", "id", User.class);
//		arp.addSqlTemplate("/sql/all_sqls.sql");	// 这里可以是用jfinal的sql模板
		// 必须手动调用start
		arp.start();
		log.info("----------------------------- 初始化ActiveRecordPlugin---------------");
		return arp;
	}
}
