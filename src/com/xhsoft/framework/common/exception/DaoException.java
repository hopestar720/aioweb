/*
 * $RCSfile: DaoException,v $$
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
 * <p>Title:DaoException</p>
 * <p>Description: 数据层运行异常定义</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class DaoException extends BaseRuntimeException 
{
	private static final long serialVersionUID = -5803265594743554136L;
	
	/**
	 * <p>Description:DaoException</p>
	 * @param code	 
	 * @param params  
	 * @param  e   
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 * @exception Exception
	 */
	public DaoException(String code,String[] params,Exception e) {
		super(code,e);
		this.setCode(code);
		this.setParams(params);
	}
}
