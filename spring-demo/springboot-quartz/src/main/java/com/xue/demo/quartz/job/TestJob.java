package com.xue.demo.quartz.job;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 实现序列化接口
 */
public class TestJob implements  Job,Serializable {

	private static final long serialVersionUID = 1L;
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("任务执行成功--TestJob");
	}
}
