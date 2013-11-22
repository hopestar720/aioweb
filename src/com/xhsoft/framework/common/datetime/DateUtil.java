/*
 * Copyright (C) 2013 xhsoft.com, Inc. All rights reserved.
 *
 * This software is the proprietary information of xhsoft, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.datetime;

import java.util.Calendar;

/**
 * <p>Title: DateUtil.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @since 2013-3-5
 */
public class DateUtil {
	
	
	//获取当前日期前一天
	public static Calendar getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal;
	}
	//获取当前日期前一个月
	public static Calendar getLashMonth(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal;
	}
	
	//获取当前日期前一周
	public static Calendar getLastWeek(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		return cal;
	}
	
	//获取当前日期前一周星期一日期
	public static Calendar getFirstWeek(){
		Calendar cal = Calendar.getInstance();
		int days = cal.get(Calendar.DAY_OF_WEEK)-2;
		System.out.println(days);
		cal.add(Calendar.DATE, -days);
		cal.add(Calendar.WEEK_OF_YEAR,-1 );
		return cal;
	}
	
	//获取当前日期前一月月初日期
	public static Calendar getFirstMonth(){
		Calendar cal = Calendar.getInstance();
		int days = cal.get(Calendar.DAY_OF_MONTH)-1;
		cal.add(Calendar.DATE,-days);
		cal.add(Calendar.MONTH, -1);
		return cal;
	}
}
