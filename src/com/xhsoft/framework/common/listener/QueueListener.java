/*
 * $RCSfile: QueueListener,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 * <p>Title:QueueListener</p>
 * <p>Description:消息接收监听器</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class QueueListener implements MessageListener
{
	/**
	 * <p>Description:onMessage</p>
	 * @param message	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public void onMessage(Message message) 
	{
		if(message instanceof ActiveMQObjectMessage)
		{
			try {
				ActiveMQObjectMessage activeMessage = (ActiveMQObjectMessage) message;
				System.out.println("监听："+activeMessage.getMessageId()+" - "+activeMessage.getObject());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		
		if(message instanceof ActiveMQMapMessage){
			try
        	{
        		ActiveMQMapMessage activeMessage = (ActiveMQMapMessage)message;
        		System.out.println("监听："+activeMessage.getMessageId()+" - "+activeMessage.getObject("messageBody"));
        	}
        	catch (final Exception e)
        	{
        		e.printStackTrace();
        	}
		}
	}
}
