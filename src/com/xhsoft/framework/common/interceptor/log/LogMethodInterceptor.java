/*
 * $RCSfile: LogMethodInterceptor,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.interceptor.log;

import net.sf.json.JSONArray;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title:LogMethodInterceptor</p>
 * <p>Description:LogMethodInterceptor</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
@SuppressWarnings("unchecked")
public class LogMethodInterceptor implements MethodInterceptor
{
	private static final Log logger = LogFactory.getLog(LogMethodBeforeAdvice.class);
	public LogMethodInterceptor() {}
	
	/**
	 * <p>Description:实现接口方法，进行日志操作</p>
	 * @param methodinvocation	
	 * @return Object
	 * @author wenzhi
	 * @version 1.0
	 * @exception Throwable
	 */
	public Object invoke(MethodInvocation methodinvocation) throws Throwable
	{
		String s1 = methodinvocation.getMethod().getName();
        Class aclass[] = methodinvocation.getMethod().getParameterTypes();
        Object aobj[] = methodinvocation.getArguments();
        @SuppressWarnings("unused")
		Class class1 = methodinvocation.getMethod().getReturnType();
        Class class2 = methodinvocation.getThis().getClass();
        String s2 = (new StringBuilder()).append("===>执行方法：").append(class2.getName()).append(".").append(s1).append("(").toString();
        if (aclass != null)
        {
            for (int i = 0; i < aclass.length; i++)
            {
                String s4 = aclass[i].getName();
                int j = s4.lastIndexOf(".");
                if (j > 0)
                    s4 = s4.substring(j + 1);
                s2 = (new StringBuilder()).append(s2).append(s4).append(" ").toString();
                try
                {
                    JSONArray jsonarray = JSONArray.fromObject(aobj[i]);
                    s2 = (new StringBuilder()).append(s2).append(jsonarray).toString();
                }
                catch (Exception exception)
                {
                    s2 = (new StringBuilder()).append(s2).append(aobj[i]).toString();
                }
                if (i != aclass.length - 1)
                    s2 = (new StringBuilder()).append(s2).append(",").toString();
            }

        }
        s2 = (new StringBuilder()).append(s2).append(") ").toString();
        String s3 = class2.getName();
        if (s3 != null && s3.length() > 1)
            s3 = s3.substring(0, 1);
        if (!"$".equalsIgnoreCase(s3))
            logger.debug(s2);
        return methodinvocation.proceed();
    }
}
