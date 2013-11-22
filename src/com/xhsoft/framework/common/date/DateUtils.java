package com.xhsoft.framework.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * 功能描述： 日期工具类
 * 
 * @version 2.0
 * @author daixl
 */
public class DateUtils {

	/** 常量空值 */
	public static final String EMPTY = ""; // 空值

	/** 时间格式数组 */
	public static final String[] DATE_PATTERN = { "yyyy", "yyyy-MM",
			"yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm",
			"yyyy-MM-dd HH:mm:ss" };

	/**
	 * 功能描述：比较２个Date类型的时间是否是同一天
	 *
	 * @param date1   第一个Date对象，
	 * @param date2   第二个Date对象
	 * @return boolean 布尔值
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static boolean isSameDay(Date date1,
			Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * 功能描述：比较２个Calendar类型的时间是否是同一天
	 *
	 * @param cal1    第一个Calendar对象，
	 * @param cal2    第二个Calendar对象，
	 * @return boolean 布尔值
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static boolean isSameDay(Calendar cal1,
			Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * 功能描述：比较２个Date类型的时间是否是同一个对象
	 *
	 * @param date1   第一个Date对象，
	 * @param date2   第二个Date对象
	 * @return boolean 布尔值
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static boolean isSameInstant(Date date1,
			Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return date1.getTime() == date2.getTime();
	}

	/**
	 * 功能描述：比较２个Calendar类型的是否是同一个对象
	 *
	 * @param cal1    第一个Calendar对象，
	 * @param cal2    第二个Calendar对象，
	 * @return boolean 布尔值
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static boolean isSameInstant(Calendar cal1,
			Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的年
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addYears(Date date,
			int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的月
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addMonths(Date date,
			int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的星期
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addWeeks(Date date,
			int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的天
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addDays(Date date,
			int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的小时
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addHours(Date date,
			int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的分钟数
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addMinutes(Date date,
			int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的秒数
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addSeconds(Date date,
			int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，增加它的毫秒数
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addMilliseconds(Date date,
			int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	/**
	 * 功能描述：给定一个日期和2个数值，返回计算后的日期
	 *
	 * @param date Date对象
	 * @param calendarField int类型
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	private static Date add(Date date,
			int calendarField,
			int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的年份
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setYears(Date date,
			int amount) {
		return set(date, Calendar.YEAR, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的月份
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setMonths(Date date,
			int amount) {
		return set(date, Calendar.MONTH, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的天
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setDays(Date date,
			int amount) {
		return set(date, Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的下小时
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setHours(Date date,
			int amount) {
		return set(date, Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的分钟
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setMinutes(Date date,
			int amount) {
		return set(date, Calendar.MINUTE, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的秒
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setSeconds(Date date,
			int amount) {
		return set(date, Calendar.SECOND, amount);
	}

	/**
	 * 功能描述：给定一个日期和一个数值，修改它的毫秒
	 *
	 * @param date Date对象
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date setMilliseconds(Date date,
			int amount) {
		return set(date, Calendar.MILLISECOND, amount);
	}

	/**
	 * 功能描述：给定一个日期和2个数值，返回修改后的Date
	 *
	 * @param date Date对象
	 * @param calendarField int类型
	 * @param amount int类型
	 * @return Date Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	private static Date set(Date date,
			int calendarField,
			int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setLenient(false);
		c.setTime(date);
		c.set(calendarField, amount);
		return c.getTime();
	}

	/**
	 * 功能描述：给定一个Date日期，返回一个Calendar对象
	 * 
	 * @param date Date对象
	 * @return Calendar Calendar对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Calendar toCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/** 默认的日期格式. */
	private static String defaultDatePattern = null;

	/** 时间格式. */
	private static String timePattern = "HH:mm";

	/**
	 * 功能描述：返回一个字符串
	 * 
	 * @return String String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";

		return defaultDatePattern;
	}

	/**
	 * 功能描述：返回一个字符串
	 * 
	 * @return String String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static String getDateTimePattern() {
		return DateUtils.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * 功能描述：给定一个Date对象，返回一个字符串对象。
	 * 
	 * @param aDate Date对象
	 * @return String  返回一个字符串
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 功能描述：给定2个字符串对象，返回一个Date对象。
	 * 
	 * @param aMask 字符串对象
	 * @param strDate  字符串对象
	 * @return Date  返回一个Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static final Date convertStringToDate(String aMask,
			String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * 功能描述：给定一个Date对象，返回一个Stirng对象。
	 * 
	 * @param theTime 字符串对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * 功能描述：的到当天的一个Calender对象
	 * 
	 * @return Calender  返回一个Calender对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * 功能描述：给定一个字符串，和一个Date对象，返回一个字符串
	 * 
	 * @param aMash 字符串
	 * @param  aDate Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static final String getDateTime(String aMask,
			Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			System.out.println("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 功能描述：把一个日期对象转换成字符串
	 * 
	 * @param aDate  Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 功能描述：把一个String类型转换成Date类型
	 * 
	 * @param strDate  一个字符串对象
	 * @return Date  返回一个Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {
			
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			System.out.println("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 功能描述：给定一个日期，获得它的年
	 * 
	 * @param date  一个Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static int getYear(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.YEAR);
	}

	/**
	 * 功能描述：给定一个日期，获得它的月
	 * 
	 * @param date  一个Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static int getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return (cal.get(GregorianCalendar.MONTH) + 1);
	}

	/**
	 * 功能描述：给定一个日期，获得它的天
	 * 
	 * @param date  一个Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	   
   /**  
    * 功能描述：  得到当前时间点
    * @param
    * @return: int
    * @author: guojiyong
    * @version: 2.0 
    */     
	public static int getHour(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
	    c.setTime(date);
	    return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：给定一个日期和一个年的数值，添加到这个日期年中，并返回
	 * 
	 * @param date  一个Date对象
	 * @param years int类型
	 * @return Date  返回一个Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addYear(Date date,
			int years) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 功能描述：给定一个日期和一个月的数值，添加到这个日期月中，并返回
	 * 
	 * @param date  一个Date对象
	 * @param months int类型
	 * @return Date  返回一个Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addMonth(Date date,
			int months) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 功能描述：给定一个日期和一个天的数值，添加到这个日期的天中，并返回
	 * 
	 * @param date  一个Date对象
	 * @param months int类型
	 * @return Date  返回一个Date对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static Date addDay(Date date,
			int months) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DAY_OF_YEAR, months);
		return cal.getTime();
	}

	/**
	 * 功能描述：给定2个Date日期，比较2个日期的大小，获得一个间隔的日期字符串
	 * 
	 * @param date1   第一个Date对象
	 * @param date2   第一个Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	public static String getShort(Date date1,
			Date date2) {
		if (date1 == null || date2 == null) {
			return null;
		}
		if (date1.before(date2)) {
			return getMonthYear(date1) + "~" + getMonthYear(date2);
		}
		return getMonthYear(date2) + "~" + getMonthYear(date1);
	}

	/**
	 * 功能描述：给定1个date日期，获得它的年月的字符串
	 * 
	 * @param date   第一个Date对象
	 * @return String  返回一个String对象
	 * @author daixl
	 * @since 2011.02.24
	 */
	private static String getMonthYear(Date date) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM");
		return formate.format(date);
	}

	/**
	 * 功能描述：给定一个日期，格式化日期；将Date日期转换为pattern格式的字符串型返回，例：2006-09-08
	 *　
	 * @param date　　Date对象
	 * @param pattern 字符串格式化
	 * @return  String　　一个字符串
	 * @author daixl
	 * @since  2011.02.23
	 */
	public static String formatDate(Date date,
			String pattern) {
		if (date == null) {
			System.out.println("待转换的日期不能为null.");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 功能描述：给定一个日期，判断它的星期，是否周末
	 *　
	 * @param date　　Date对象
	 * @return  boolean　　布尔值
	 * @author daixl
	 * @since  2011.02.23
	 */
	public static boolean isSundayOrSaturday(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int day = cal.get(GregorianCalendar.DAY_OF_WEEK);
		if (GregorianCalendar.SATURDAY == day
				|| GregorianCalendar.SUNDAY == day) {
			return true;
		}
		return false;
	}

	/**
	 * 功能描述：给定一个日期和一个整形数值，的到给日期的前一天。
	 *　
	 * @param date　　Date对象
	 * @param n 整形数值
	 * @return  Date　　一个Date对象
	 * @author daixl
	 * @since  2011.02.23
	 */
	public static Date getDateAfterDays(Date date,
			int n) {
		Date rtnDate = new Date();
		rtnDate.setTime(date.getTime() + Long.parseLong("86400000") * n);
		return rtnDate;
	}

	/**
	 * 功能描述：给定字符串，转换成匹配格式的Date对象
	 *　
	 * @param dateStr　　一个String对象
	 * @return  Date　　一个Date对象
	 * @author daixl
	 * @since  2011.02.23
	 */
	public static Date getDate(String dateStr) {
		if (isBlank(dateStr))
			return null;
		dateStr = dateStr.trim();
		SimpleDateFormat df = null;
		if (dateStr.length() > 19) {
			dateStr = dateStr.substring(0, 19);
		}

		if (dateStr.matches("[\\d]{4}")) {
			df = new SimpleDateFormat("yyyy");
		} else if (dateStr.matches("[\\d]{4}-[\\d]{1,2}")) {
			df = new SimpleDateFormat("yyyy-MM");
		} else if (dateStr.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}")) {
			df = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dateStr.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}[\\s]{1,}[\\d]{1,2}")) {
			df = new SimpleDateFormat("yyyy-MM-dd HH");
		} else if (dateStr.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}[\\s]{1,}[\\d]{1,2}:[\\d]{1,2}")) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if (dateStr.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}[\\s]{1,}[\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}")) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (dateStr.matches("[\\d]{1,2}-[\\d]{1,2}[\\s]{1,}[\\d]{1,2}")) {
			df = new SimpleDateFormat("MM-dd HH");
		}else {
			return null;
		}
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能描述：给定Date对象，转换成指定格式的字符串日期
	 *
	 * @param date 一个Date对象，
	 * @return : String ex: 2009-06-09
	 * @author : daixl
	 * @since  :  2011.02.23
	 */
	public static String getYmdStr(Date date) {
		if (date == null)
			return EMPTY;
		return formatDate(date, DATE_PATTERN[2]);
	}

	/**
	 * 功能描述： 将Date日期转换为带时分秒的字符串型返回
	 *
	 * @param date the date
	 * @return : String, ex: 2009-06-09 02:04:05
	 * @author : daixl
	 * @since  :  2011.02.23
	 */
	public static String getYmdhisStr(Date date) {
		if (date == null)
			return EMPTY;
		return formatDate(date, DATE_PATTERN[5]);
	}

	/**
	 * 功能描述：给定一个日期和一个整形数值，的到给日期的前一天。
	 *　
	 * @param date　　Date对象
	 * @param n 整形数值
	 * @return  Date　　一个Date对象
	 * @author daixl
	 * @since  2011.02.23
	 */
	public static Date getDateByDateAndHour(Date date,
			int n) {
		return new Date(date.getTime() + n * 3600000);
	}
	
	private static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0
				|| "null".equalsIgnoreCase(str)
				|| "undefined".equalsIgnoreCase(str);
	}

}
