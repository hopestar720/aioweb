/*
 * $RCSfile: LogMethodAfterAdvice,v $$
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
import org.springframework.aop.AfterReturningAdvice;

/**
 * <p>Title:LogMethodAfterAdvice</p>
 * <p>Description:组织机构实体类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class LogMethodAfterAdvice implements AfterReturningAdvice 
{
	private static final Log logger = LogFactory.getLog(LogMethodAfterAdvice.class);

	public LogMethodAfterAdvice() 
	{
	}

	/**
	 * <p>Description:afterReturning</p>
	 * @param obj	 
	 * @param aobj  
	 * @param obj1   
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception Throwable
	 */
	public void afterReturning(Object obj, Method method, Object aobj[],
			Object obj1) throws Throwable
		{
		StringBuilder strLog = new StringBuilder();
		String s1 = strLog.append("===>执行方法后：").append(obj1.getClass().getName()).append(".").append(method.getName()).append("(").toString();
		if (aobj != null) {
			for (int i = 0; i < aobj.length; i++) 
			{
				try {
					JSONArray jsonarray1 = JSONArray.fromObject(aobj[i]);
					s1 = strLog.append(jsonarray1).toString();
				} catch (Exception exception1) {
					s1 = strLog.append(aobj[i]).toString();
				}
				if (i != aobj.length - 1)
					s1 = strLog.append(",").toString();
			}
			s1 = strLog.append(") ").toString();
		}
		try {
			JSONArray jsonarray = JSONArray.fromObject(obj);
			String s3 = " ";
			if (obj != null)
				s3 = (new StringBuilder()).append(s3).append(
						obj.getClass().getName()).append(" ").toString();
			s1 = strLog.append("\nDEBUG:===>方法返回值：").append(s3).append(jsonarray).toString();
		} catch (Exception exception) {
			s1 = strLog.append("\nDEBUG:===>方法返回值：").append(obj).toString();
		}
		String s2 = obj1.getClass().getName();
		if (s2 != null && s2.length() > 1)
			s2 = s2.substring(0, 1);
		if (!"$".equalsIgnoreCase(s2))
			logger.debug(s1);
	}
	
}
