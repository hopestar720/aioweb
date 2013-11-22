/*
 * $RCSfile: BaseCheckException,v $$
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
 * <p>Title:BaseCheckException</p>
 * <p>Description:检测类型异常基础类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class BaseCheckException extends 
			 Exception implements MesBizException
{
	private static final long serialVersionUID = -6358717097463016627L;

	private String code;

	private String[] params;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
	
	/**默认构造参数*/
	public BaseCheckException() 
	{
		super();
	}
	 
	/**
	 * <p>Description:提供错误消息和异常构造参数</p>
	 * @param message	
	 * @param e   
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public BaseCheckException(String message, Exception e) 
	{
		super(message, e);
	}

	/**
	 * <p>Description:只有错误消息，没有传递异常</p>
	 * @param code	
	 * @param params  
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public BaseCheckException(String code,String[] params) 
	{
		super(code);
		this.setCode(code);
		this.setParams(params);
	}
}
