/*
 * $RCSfile: AppContext,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.util.HashMap;
import java.util.Map;

public class AppContext 
{
	public static final String FACILITY = "FACILITY";
	private static Map<String, String> params = new HashMap<String, String>();
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	public static void addParam(String key, String value) 
	{
		params.put(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @return String
	 */
	public static String getParam(String key) 
	{
		return params.get(key);
	}
	
	/**
	 * 
	 * @return  String
	 */
	public static String getFacility() 
	{
		return getParam(FACILITY);
	}
}
