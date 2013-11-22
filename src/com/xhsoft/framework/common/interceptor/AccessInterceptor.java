/*
 * $RCSfile: AccessInterceptor,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

//import com.xhsoft.framework.system.purview.action.LoginUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
* <p>Title:AccessInterceptor</p>
* <p>Description:AccessInterceptor</p>
* <p>Copyright:Copyright (C) 2011</p>
* @author wenzhi
* @date 2011-12-28
*/
public class AccessInterceptor extends AbstractInterceptor 
{

	private static final long serialVersionUID = 4902659316661434770L;

	private static final Log logger = LogFactory.getLog(AccessInterceptor.class);

	/**
	 * <p>Description:定义开发模式参数，便于开发期间调试，后期可以去掉</p>
	 * @param entRegNO	
	 * @param proptity   
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 * @exception Exception
	 */
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		logger.debug("start access privilege check...");
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = request.getServletPath();
		String url = uri;
		String queryString = request.getQueryString();
		
		if (StringUtils.isNotEmpty(queryString)) {
			url = uri + "?" + queryString;
		}
		logger.debug(url);
		if (url.startsWith("/admin/purview/userinfo/login.action") || url.startsWith("/admin/purview/userinfo/logout.action")) {
			logger.debug("login/logout request,pass request");
			return invocation.invoke();
		} else {
			/*if (!LoginUtil.isLogin(request,"login")) {//没有检测到登录信息	
				logger.debug("not login request uri=" + url);
				logger.debug("not login access,take to login tip page");
				return "invalidLogin";
			}else {
				*//**有登录信息，进一步检测访问权限*//*
				logger.debug("have login access,pass request");
				return invocation.invoke();
			}*/
			
			return invocation.invoke();
		}
	}
}
