/*
 * $RCSfile: ServiceException,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.exception;

/**
 * <p>Title:ServiceException</p>
 * <p>Description:ServiceException</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class ServiceException extends BaseRuntimeException 
{
	private static final long serialVersionUID = 8175534653136933753L;

	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(String message,Exception e){
		super(message,e);
	}
}
