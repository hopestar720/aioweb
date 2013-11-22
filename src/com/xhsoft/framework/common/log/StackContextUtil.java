/*
 * $RCSfile: StackContextUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2010-12-09  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;

/**
 * <p>Title:StackContextUtil</p>
 * <p>Description:Utilites for get the caller method name and class name</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Wanggq
 * @date 2010-12-09
 */
public class StackContextUtil 
{
	/**
	 * <p>Description:get the caller method  name</p>
	 * @param backLevel
	 * @return String
	 * @author wgq
	 * @version 1.0
	 */
    static public String getCallerMethodName(int backLevel)
    {
        String callerMethodName = "";
        
        try {
            Throwable ts = new Throwable();
        
            StackTraceElement[] stackElement = ts.getStackTrace();
        
             callerMethodName =  stackElement[backLevel + 1].getMethodName();
        
        }catch (Exception ex){
            callerMethodName = "";
        }        
        
        return callerMethodName;
    }

    /**
	 * <p>Description:get the caller class  name</p>
	 * @param backLevel
	 * @return String
	 * @author wgq
	 * @version 1.0
	 */
    static public String getCallerClassName(int backLevel)
    {
        String callerClassName = "";
        
        try {
           Throwable ts = new Throwable();
        
           StackTraceElement[] stackElement = ts.getStackTrace();
           
           callerClassName = stackElement[backLevel + 1].getClassName();
        }catch (Exception ex){
            callerClassName = "";
        }
        
        return callerClassName;
    }
    
    /**
	 * <p>Description:get the caller class  name</p>
	 * @param backLevel
	 * @return int
	 * @author wgq
	 * @version 1.0
	 */
    static public int getCallerLineNumber(int backLevel)
    {
        int callerLineNumber = -1;
        
        try {
           Throwable ts = new Throwable();
        
           StackTraceElement[] stackElement = ts.getStackTrace();
           
           callerLineNumber = stackElement[backLevel + 1].getLineNumber();
        } catch (Exception ex){
        	callerLineNumber = -1;
        }
        
        return callerLineNumber;
    }
} 