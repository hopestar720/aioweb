/*
 * $RCSfile: ConcurrentModifyException,v $$
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
 * <p>Title:ConcurrentModifyException</p>
 * <p>Description:版本控制并发修改异常</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class ConcurrentModifyException extends BaseRuntimeException
{
	
	private static final long serialVersionUID = 4522426262571303585L;

	public ConcurrentModifyException()
	{
		super();
	}
}
