package com.xue.demo.quartz.job;

import java.io.Serializable;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 获取Job的相关信息
 */
@Slf4j
public class ContextJob implements Job,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("---------------ContextJob Starting---------------------------");
		 System.out.println("Hello!  NewJob is executing."+new Date() );

		 //取得job详情  
         JobDetail jobDetail = context.getJobDetail();

         // 取得job名称  
         String jobName = jobDetail.getClass().getName();  
         log.info("Name: " + jobDetail.getClass().getSimpleName());

         //取得job的类  
         log.info("Job Class: " + jobDetail.getJobClass());

         //取得job开始时间  
         log.info(jobName + " fired at " + context.getFireTime());

         //取得job下次触发时间  
         log.info("Next fire time " + context.getNextFireTime());  
         
         JobDataMap dataMap =  jobDetail.getJobDataMap();
         
         log.info("itstyle: " + dataMap.getString("itstyle")); 
         log.info("blog: " + dataMap.getString("blog"));
         
	}
}
