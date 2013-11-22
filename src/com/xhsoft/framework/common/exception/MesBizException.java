/*
 * $RCSfile: MesBizException,v $$
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
 * <p>Title:MesBizException</p>
 * <p>Description:MesBizException</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public interface MesBizException 
{
	public String getCode();

	public String[] getParams();
}
