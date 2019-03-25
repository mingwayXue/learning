package com.xue.demo.test;

import com.xue.demo.SpringbootQuartzApplicationTests;
import com.xue.demo.quartz.job.TestJob;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mingway on Date:2018-08-28 11:26.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class QuartzTestDemo extends SpringbootQuartzApplicationTests {

	@Autowired
	private Scheduler scheduler;

	/**
	 * 新增(更新)一个Job
	 */
	@Test
	public void addJob() throws ClassNotFoundException {

		log.info("-------------新增job-----------------");

		//任务的执行类
		Class cls = Class.forName("com.xue.demo.quartz.job.TestJob");

		//指定job的执行类，job名，组名，描述信息
		JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity("test01", "test").withDescription("this is job description!").build();

		//触发的时间点(cron 表达式)
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

		//指定触发器trigger的名称，组名
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger01","test").startNow().withSchedule(cronScheduleBuilder).build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除一个任务
	 */
	@Test
	public void removeJob() {
		log.info("------------移除job--------------");
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger01","test");

		try {
			// 停止触发器
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			scheduler.unscheduleJob(triggerKey);
			// 删除任务
			scheduler.deleteJob(JobKey.jobKey("test01","test"));

		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 立即触发任务
	 */
	@Test
	public void startJob() {
		log.info("------------立即触发job--------------");
		JobKey jobKey = JobKey.jobKey("test01","test");
		try {
			scheduler.triggerJob(jobKey);
			Thread.sleep(10000);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止和恢复任务
	 */
	@Test
	public void pauseAndResumeJob() {
		log.info("------------立即停止job--------------");
		JobKey jobKey = JobKey.jobKey("test01","test");
		try {
			scheduler.pauseJob(jobKey);
//			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}
