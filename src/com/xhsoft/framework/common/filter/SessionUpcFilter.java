/*
 * $RCSfile: SessionUpcFilter,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionUpcFilter implements Filter 
{

	private static final Log logger = LogFactory.getLog(SessionUpcFilter.class);
	@SuppressWarnings("unused")
	private String fileResource;
	
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.fileResource = filterConfig.getInitParameter("fileResource");
	}
	
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = ((HttpServletRequest)req);		
		HttpSession session = (request).getSession();
		//UserInfo user=(UserInfo)session.getAttribute(SessionCons.CURRENT_ACCOUNT);
		
        try {
        	/*if(user!=null){
    			LoginUtil.setLoginUser(request, user);
    		}*/
        	
			HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Pragma", "No-cache");
            httpResponse.setHeader("Cache-Control", "no-cache");
            httpResponse.setDateHeader("Expires", 0);
			chain.doFilter(request, response);
        }catch(IOException ex){
        	logger.error(request.getRemoteAddr() + " io error : " + ex);
        }
        catch(Exception de){
        	de.printStackTrace();
        } 
        logger.debug("User Filter end!");
	}
	
	public boolean handleResource(String[] resources, String context, String url, String method){
		if(resources == null){
			return true;
		}
		
		for(String resource: resources)
		{
			if(resource != null){
				resource = resource.trim();
			}
			if(url != null && url.contains(context+resource) && !"".equals(resource)){
				return true;
			}
		}
		return false;
	}
	
	public void destroy() 
	{
		this.fileResource = null;
	}
}
