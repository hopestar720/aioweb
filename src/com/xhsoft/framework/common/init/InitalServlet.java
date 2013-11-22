/*
 * $RCSfile: InitalServlet,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.init;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

/**
 * <p>Title:InitalServlet</p>
 * <p>Description:初始化Servlet，装载通用数据</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class InitalServlet extends HttpServlet
{

	private static final long serialVersionUID = -5182203793586401236L;

	/**
	 * <p>Description:初始化系统： 1、读取配置文件。</p>
	 * @param config	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public void init(ServletConfig config)
	{
		loadSysConfig();
	}

	/**
	 * <p>Description:加载Config.xml配置文件。</p>
	 * @param config	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	private void loadSysConfig()
	{
		ConfigLoad load = new ConfigLoad();
		load.parser("/config.xml");
	}

}
