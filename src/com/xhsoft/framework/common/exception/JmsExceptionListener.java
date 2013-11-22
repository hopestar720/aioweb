/*
 * $RCSfile: JmsExceptionListener,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.exception;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;

/**
 * <p>Title:JmsExceptionListener</p>
 * <p>Description:消息服务器连接工厂的异常监听</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class JmsExceptionListener implements ExceptionListener
{
	private static Logger log = Logger.getLogger(JmsExceptionListener.class);
	
    public void onException( final JMSException e )
    {
    	log.info("出错了:");
    	/**调试打印全部信息*/
        e.printStackTrace();
    }
}
