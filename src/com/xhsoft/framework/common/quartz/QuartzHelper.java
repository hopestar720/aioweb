package com.xhsoft.framework.common.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzHelper {
	
private static final Log logger = LogFactory.getLog(QuartzUtil.class);
	
	private static QuartzHelper instance = null;
	
	private static Scheduler scheduler = null;
	
	private QuartzHelper()
	{
		if(scheduler == null){
			try {
				scheduler = new StdSchedulerFactory().getDefaultScheduler();
			} catch (SchedulerException e) {
				logger.error("获取调度器实例发生异常："+e.getMessage());
			}
		}
	}
	
	public static synchronized QuartzHelper getInstance()
	{
		if(instance == null){
			instance = new QuartzHelper();
		}
		return instance;
	}

}
