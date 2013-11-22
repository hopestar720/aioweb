/*
 * $RCSfile: AbsentParamtersException,v $$
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
 * <p>Title:AbsentParamtersException</p>
 * <p>Description:缺少程序运行的关键参数错误</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class AbsentParamtersException extends BaseRuntimeException
{
	
	private static final long serialVersionUID = -6183685706081486007L;

	/**@param param 参数名*/
	public AbsentParamtersException(String param){
		super("global.error.code.absent.paramters",new String[]{param});
	}
}
