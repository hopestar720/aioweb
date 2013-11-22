package com.xhsoft.framework.common.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QuartzListener implements ServletContextListener {

	private static final Log logger = LogFactory.getLog(QuartzListener.class);
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		QuartzUtil.getInstance().startup();

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		QuartzUtil.getInstance().shutdown();

	}

}
