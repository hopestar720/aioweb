package com.xhsoft.framework.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}

}
