/*
 * $RCSfile: GlobalRuntimeException,v $$
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
 * <p>Title:GlobalRuntimeException</p>
 * <p>Description: 全局的运行异常定义
 	      对于没有明确含义的运行异常可以统一使用该异常，必须在构造函数提供异常描述的代码和参数</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class GlobalRuntimeException extends BaseRuntimeException
{
	private static final long serialVersionUID = -4617230219334405927L;
	
	public GlobalRuntimeException(String message,Exception e)
	{
		super(message,e);
	}
	
	public GlobalRuntimeException(String code,String[] params)
	{
		super(code,params);
	}
}
