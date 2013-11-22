/*
 * $RCSfile: LogExceptionAdvice,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.interceptor.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * <p>Title:LogExceptionAdvice</p>
 * <p>Description:组织机构实体类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class LogExceptionAdvice implements ThrowsAdvice 
{

	private static final Log logger = LogFactory.getLog(LogExceptionAdvice.class);
	
	public LogExceptionAdvice(){}
	
	/**
	 * <p>Description:实现接口方法，进行日志操作</p>
	 * @param throwable	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 * @exception Exception
	 */
	public void afterThrowing(Throwable throwable)
	{
		 String s1 = (new StringBuilder()).append("===>执行方法抛出异常：").append(throwable).toString();
		 logger.debug(s1);
		 throwable.printStackTrace();
	}
}
