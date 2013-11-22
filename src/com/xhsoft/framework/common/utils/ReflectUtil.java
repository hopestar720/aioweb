/*
 * $RCSfile: ReflectUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title:ReflectUtil</p>
 * <p>Description:ReflectUtil</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class ReflectUtil 
{
	private static final Log logger = LogFactory.getLog(ReflectUtil.class);

	/**
	 * <p>Description:setFieldValue</p>
	 * @param target
	 * @param fname
	 * @param ftype
	 * @param fvalue
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public static void setFieldValue(Object target, String fname, Class ftype,Object fvalue) 
	{
		if (target == null
				|| fname == null
				|| "".equals(fname)
				|| (fvalue != null && !ftype
						.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		
		Class clazz = target.getClass();
		
		try {
			Method method = clazz.getDeclaredMethod("set"
					+ Character.toUpperCase(fname.charAt(0))
					+ fname.substring(1), ftype);
			
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			
			method.invoke(target, fvalue);

		} catch (Exception me) {
			try {
				Field field = clazz.getDeclaredField(fname);
				
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				
				field.set(target, fvalue);
			} catch (Exception fe) {
				
				if (logger.isDebugEnabled()) {
					logger.debug(fe);
				}
			}
		}
	}
	
	/**
	 * <p>Description:用Reflection机制得到所有属性的Map形式</p>
	 * @param target
	 * @return Map<String,Object>
	 * @author wanggq
	 * @since  2009-9-9
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getMapFieldData(Object target)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		Class clazz=target.getClass();
		Field[] fields=clazz.getDeclaredFields();
		Method[] methods=clazz.getDeclaredMethods();
		for(Field field:fields)
		{
			String fieldName=field.getName();
			
			if("messageTypeId".equals(fieldName)){
			    continue;
			}
			
			String getMethod="get"+StringUtils.capitalize(fieldName);
			for(Method method:methods)
			{
				if(method.getName().equals(getMethod)){
					
					try {
						Object ret=method.invoke(target, null);
						map.put(fieldName, ret);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		return map;
	}
}
