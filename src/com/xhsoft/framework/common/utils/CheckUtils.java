/*
 * $RCSfile: CheckUtils,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.util.Random;

/**
 * <p>Title:CheckUtils</p>
 * <p>Description:检测对象工具类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author syf
 * @since 2011
 */
public class CheckUtils 
{
	/**
	 * <p>Description:判断 o 是否为null或者 ""</p>
	 * @param o
	 * @return null 或者"" TRUE  否则FALSE
	 */
	public static boolean isNUll(Object o) 
	{
		if (null == o || o.equals("") || o.toString().trim().equals(""))
			return true;
		else
			return false;
	}
	
	/**
	 * <p>Description:产生随机数</p>
	 * @param 随机数长度
	 * @return String
	 */
	public static String getCharacterAndNumber(int length)  
	{
		String val = ""; 
		Random random = new Random(); 
		for(int i = 0; i < length; i++) 
		{           
			/* 输出字母还是数字 */
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
			/* 字符串 */
			if("char".equalsIgnoreCase(charOrNum)){                 
				/* 取得大写字母还是小写字母 */
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;             
				val += (char) (choice + random.nextInt(26));    
				/*数字*/
			} else if("num".equalsIgnoreCase(charOrNum)) {                
				val += String.valueOf(random.nextInt(10));      
			}        
		}                     
		return val;   
	}  	
}
