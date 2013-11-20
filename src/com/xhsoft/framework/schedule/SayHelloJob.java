package com.xhsoft.framework.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SayHelloJob extends QuartzJobBean {
	
	private static final Log logger = LogFactory.getLog(SayHelloJob.class);

	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		logger.info("hello my first job !");
	}

}
