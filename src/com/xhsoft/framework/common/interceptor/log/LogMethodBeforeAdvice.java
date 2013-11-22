/*
 * $RCSfile: LogMethodBeforeAdvice,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.interceptor.log;

import java.lang.reflect.Method;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * <p>Title:LogMethodBeforeAdvice</p>
 * <p>Description:组织机构实体类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class LogMethodBeforeAdvice implements MethodBeforeAdvice 
{
	
	private static final Log logger = LogFactory.getLog(LogMethodBeforeAdvice.class);
	public LogMethodBeforeAdvice(){}
	
	/**
	 * <p>Description: 实现接口方法，进行日志操作</p>
	 * @param method	
	 * @param aobj   
	 * @param obj   
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception Exception
	 */
	public void before(Method method, Object[] aobj, Object obj)
			throws Throwable
	{
		
		StringBuilder strLog = new StringBuilder();
		String s1 = strLog.append("====>调用方法前：").append(obj.getClass().getName()).append(".").append(method.getName()).append("  参数为:(").toString();
		if(aobj != null){
			for(int i=0;i<aobj.length;i++)
			{
				try {
					JSONArray jsonarray = JSONArray.fromObject(aobj[i]);
					s1 = strLog.append(jsonarray).toString();
				} catch (Exception e) {
					// TODO: handle exception
					s1 = strLog.append(aobj[i]).toString();
				}
				if(i != aobj.length - 1){
					s1 = strLog.append(",").toString();
				}
			}
			s1 = strLog.append(")").toString();
		}
		
		 String s2 = obj.getClass().getName();
		 if (s2 != null && s2.length() > 1)
			 s2 = s2.substring(0, 1);
		 if (!"$".equalsIgnoreCase(s2))
		 	logger.debug(s1);
	}

}
