/*
 * $RCSfile: GlobalServiceException,v $$
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
 * <p>Title:GlobalServiceException</p>
 * <p>Description: 全局的业务异常定义。
 	      对于没有明确含义的异常可以统一使用该异常，必须在构造函数提供异常描述的代码和参数</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class GlobalServiceException extends BaseRuntimeException 
{
	private static final long serialVersionUID = -7331132754092523144L;

	public GlobalServiceException(String code,String[] params)
	{
		super(code,params);
	}
}
