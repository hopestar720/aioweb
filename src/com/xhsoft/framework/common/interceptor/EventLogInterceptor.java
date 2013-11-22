/*
 * $RCSfile: EventLogInterceptor,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.interceptor;

import org.springframework.core.Ordered;

/**
* <p>Title:EventLogInterceptor</p>
* <p>Description:EventLogInterceptor</p>
* <p>Copyright:Copyright (C) 2011</p>
* @author wenzhi
* @date 2011-12-28
*/
public class EventLogInterceptor implements Ordered 
{

	public int getOrder() 
	{
		return 0;
	}

}
