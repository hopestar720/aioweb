/*
 * $RCSfile: ApplicationContextListener,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xhsoft.framework.base.service.AppServiceHelper;
import com.xhsoft.framework.common.utils.AppContext;

public class ApplicationContextListener implements ServletContextListener
{
	
	private static final Log logger = LogFactory.getLog(ApplicationContextListener.class);

	public void contextInitialized(ServletContextEvent event) 
    {
        try {
        	ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
            ServletContext sc = event.getServletContext();
        	String appName=sc.getServletContextName();
        	logger.info("["+appName+"] init context ...");
        	AppServiceHelper.setApplicationContext(applicationContext);
        	logger.info(" - Put Spring Context to AppServiceHelper");
            String facility = sc.getInitParameter("facility");
            AppContext.addParam(AppContext.FACILITY, facility);
            logger.info(" - Put FACILITY["+facility+"] to AppContext");
            logger.info("["+appName+"] init context[done]");
        } catch (Exception e) {
            logger.error("error detail:",e);
        }
    }

    public void contextDestroyed(ServletContextEvent arg0)
    {

    }
}
