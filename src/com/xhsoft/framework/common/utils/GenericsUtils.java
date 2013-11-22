/*
 * $RCSfile: GenericsUtils,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>Title:GenericsUtils</p>
 * <p>Description: Provides a helper that locates the declarated generics type of a class.</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class GenericsUtils 
{
    /**
     * Locates the first generic declaration on a class.
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>null</code> if cannot be determined
     */
    @SuppressWarnings("unchecked")
	public static Class getGenericClass(Class clazz) 
    {
        return getGenericClass(clazz, 0);
    }

    /**
     * Locates  generic declaration by index on a class.
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    @SuppressWarnings("unchecked")
	public static Class getGenericClass(Class clazz, int index) 
    {
        Type genType = clazz.getGenericSuperclass();

        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

            if ((params != null) && (params.length >= (index - 1))) {
                return (Class) params[index];
            }
        }
        
        return null;
    }
    
    /**
     * 获取泛型T的类型
     *
     * @param method getter method
     */
	@SuppressWarnings("unchecked")
	public static Class getGenericReturnClass(Method method)
	{
		Class returnClazz = method.getReturnType();
		Type genType = method.getGenericReturnType();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)  
					.getActualTypeArguments();

			if ((params != null) && (params.length >= 0)) {
				returnClazz = (Class) params[0];
			}
		}
		return returnClazz;
	}
}
