/*
 * $RCSfile: ValidationException,v $$
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
 * <p>Title:ValidationException</p>
 * <p>Description: 验证失败异常</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class ValidationException extends BaseCheckException 
{
	private static final long serialVersionUID = -2395839426297884233L;

	public ValidationException(String code,String[] params)
	{
		super(code,params);
	}
}
