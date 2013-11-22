/*
 * $RCSfile: LogInitializeServlet,v $$
 * $Revision: 1.0  $
 * $Date: 2010-12-09  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * <p>Title:LogInitializeServlet</p>
 * <p>Description:LogInitializeServlet</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Wanggq
 * @date 2010-12-09
 */
public class LogInitializeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 4709565751021033046L;
	
	/**
	 * @return void
	 * @see javax.servlet.GenericServlet#init()
	 * @exception ServletException
	 */
	@Override
	public void init() throws ServletException 
	{		
		System.out.println("SYSTEM is initializing the LOGGER configuration...");
		//Logger.init();
		Logger.logInfo("LOG4J was initialized!");
		
		try{
		Logger.logInfo("Logger is ready on machine " + java.net.InetAddress.getLocalHost().getHostAddress());
		}catch(java.net.UnknownHostException uhe){
			uhe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @return void
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() 
	{		
		System.out.println("SYSTEM is unbounding the LOGGER ...");
	}	
}
