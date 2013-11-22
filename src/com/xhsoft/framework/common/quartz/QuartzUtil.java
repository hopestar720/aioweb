package com.xhsoft.framework.common.quartz;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
	
	private static final Log logger = LogFactory.getLog(QuartzUtil.class);
	
	private static QuartzUtil instance = null;
	
	private static Scheduler scheduler = null;
	
	private QuartzUtil()
	{
		if(scheduler == null){
			try {
				scheduler = new StdSchedulerFactory().getDefaultScheduler();
			} catch (SchedulerException e) {
				logger.error("获取调度器实例发生异常："+e.getMessage());
			}
		}
	}
	
	public static synchronized QuartzUtil getInstance()
	{
		if(instance == null){
			instance = new QuartzUtil();
		}
		return instance;
	}
	
	public Scheduler getSchedulerInstance(){
		return scheduler;
	}
	
	/**
	 * <p>Description:启动调度器</p>
	 * @return true:启动成功|false:启动失败
	 * @author lijw
	 * @since 2013-5-31
	 */
	public boolean startup(){
		try {
			if(scheduler.isStarted()){
				return true;
			}else{
				scheduler.start();
				return true;
			}
		} catch (SchedulerException e) {
			logger.error("调度器启动异常："+e.getMessage());
			return false;
		}
	}
	
	/**
	 * <p>Description:关闭调度器</p>
	 * @return true:关闭成功|false:关闭失败
	 * @author lijw
	 * @since 2013-5-31
	 */
	public boolean shutdown(){
		try {
			if(scheduler.isShutdown()){
				return true;
			}else{
				scheduler.shutdown();
				return true;
			}
		} catch (SchedulerException e) {
			logger.error("调度器关闭异常："+e.getMessage());
			return false;
		}
	}
	
	/**
	 * <p>Description:判断调度器状态</p>
	 * @return 0：已关闭|1：已启动|-1：状态异常，可能未初始化
	 * @author 李降伟
	 * @since 2013-5-31
	 */
	public int getState(){
		try {
			if(scheduler.isStarted()){
				return 1;	//已启动
			}else if(scheduler.isShutdown()){
				return 0;	//已关闭
			}else{
				return -1;	//未实现化
			}
		} catch (SchedulerException e) {
			logger.error("调度器状态获取异常："+e.getMessage());
			return -1;	//异常
		}
	}
	
	public boolean addJob(JobDetail jobDetail){
		try {
			scheduler.addJob(jobDetail , true);
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器添加JOB异常："+e.getMessage());
			return false;
		}
	}
	
	public boolean removeJob(JobKey jobKey){
		boolean flag = false;
		try {
			if(scheduler.checkExists(jobKey)){
				flag =  scheduler.deleteJob(jobKey);
			}
		} catch (SchedulerException e) {
			logger.error("调度器删除JOB异常："+e.getMessage());
		}
		return flag;
	}
	
	public boolean removeAllJobs(List<JobKey> jobKeys){
		boolean flag = false;
		try {
			flag = scheduler.deleteJobs(jobKeys);
		} catch (SchedulerException e) {
			logger.error("调度器批量删除JOB异常："+e.getMessage());
		}
		return flag;
	}
	
	public boolean pauseJob(JobKey jobKey){
		try {
			if(scheduler.checkExists(jobKey)){
				scheduler.pauseJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器暂停JOB异常："+e.getMessage());
			return false;
		}
	}
	
	public boolean pauseAllJobs(){
		try {
			scheduler.pauseAll();
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器暂停所有JOB异常："+e.getMessage());
			return false;
		}
	}
	
	public boolean resumeJob(JobKey jobKey){
		try {
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器恢复JOB异常："+e.getMessage());
			return false;
		}
	}
	
	public boolean resumeAllJobs(){
		try {
			scheduler.resumeAll();
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器恢复所有JOB异常："+e.getMessage());
			return false;
		}
	}
	
	public boolean pasuseTrigger(TriggerKey triggerKey){
		try {
			scheduler.pauseTrigger(triggerKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("调度器暂停Trigger异常："+e.getMessage());
			return false;
		}
	}
	
	public Date scheduleJob(Trigger trigger){
		try {
			return scheduler.scheduleJob(trigger);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public boolean scheduleJobs(Map<JobDetail, Set<? extends Trigger>> mapJob){
		try {
			scheduler.scheduleJobs(mapJob, true);
			return true;
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public boolean unscheduleJob(TriggerKey triggerKey){
		boolean flag = false;
		try {
			if(scheduler.checkExists(triggerKey)){
				flag = scheduler.unscheduleJob(triggerKey);
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	public boolean unscheduleJobs(List<TriggerKey> lsTriggerkey){
		boolean flag = false;
		try {
			flag = scheduler.unscheduleJobs(lsTriggerkey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	
	
}
