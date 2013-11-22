package com.xhsoft.framework.common.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 功能描述： 时间格式化的类，各种形式的时间。
 * 
 * @version 2.0
 * @author daixl
 */
public class DateFormatUtil {

	/** 年月日时分秒  */
	public static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

	/** 年月日时分秒毫秒*/
	public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ssZZ");

	/** 年月日 */
	public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");

	/** 年月日，毫秒 */
	public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");

	/** 时分秒 */
	public static final FastDateFormat ISO_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");

	/** 时分秒，毫秒 */
	public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");

	/** 时分秒 */
	public static final FastDateFormat ISO_TIME_NO_T_FORMAT = FastDateFormat.getInstance("HH:mm:ss");

	/** 时分秒，毫秒 */
	public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");

	/** 国际化的年月日，时分秒 */
	public static final FastDateFormat SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z",
			Locale.US);

	/**
	 * 构造函数
	 */
	public DateFormatUtil() {
		super();
	}

	/**
	 * 功能描述： 根据毫秒数，和时间格式，得到一个时间字符串
	 *
	 * @param millis long类型的毫秒数
	 * @param pattern 字符串的时间格式
	 * @return String  时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(long millis,
			String pattern) {
		return format(new Date(millis), pattern, null, null);
	}

	/**
	 * 功能描述： 根据Date对象，和时间格式，得到一个时间字符串
	 *
	 * @param date 一个Date对象
	 * @param pattern 字符串的时间格式
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Date date,
			String pattern) {
		return format(date, pattern, null, null);
	}

	/**
	 * 功能描述： 根据Calendar对象，和时间格式，得到一个时间字符串
	 *
	 * @param calendar 一个Calendar对象
	 * @param pattern 字符串的时间格式
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Calendar calendar,
			String pattern) {
		return format(calendar, pattern, null, null);
	}

	/**
	 * 功能描述： 根据毫秒数，和时间格式，得到一个时间字符串
	 *
	 * @param millis long类型的毫秒数
	 * @param pattern 字符串的时间格式
	 * @param timeZone TimeZone对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(long millis,
			String pattern,
			TimeZone timeZone) {
		return format(new Date(millis), pattern, timeZone, null);
	}

	/**
	 * 功能描述： 根据 Date 对象，和时间格式，得到一个时间字符串
	 *
	 * @param date 一个Date对象
	 * @param pattern 字符串的时间格式
	 * @param timeZone TimeZone对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Date date,
			String pattern,
			TimeZone timeZone) {
		return format(date, pattern, timeZone, null);
	}

	/**
	 * 功能描述： 根据Calendar对象，和时间格式，得到一个时间字符串
	 *
	 * @param calendar 一个Calendar对象
	 * @param pattern 字符串的时间格式
	 * @param timeZone TimeZone对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Calendar calendar,
			String pattern,
			TimeZone timeZone) {
		return format(calendar, pattern, timeZone, null);
	}

	/**
	 * 功能描述： 根据毫秒数，和时间格式，得到一个时间字符串
	 *
	 * @param millis long类型的毫秒数
	 * @param pattern 字符串的时间格式
	 * @param locale locale对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(long millis,
			String pattern,
			Locale locale) {
		return format(new Date(millis), pattern, null, locale);
	}

	/**
	 * 功能描述： 根据 Date 对象，和时间格式，得到一个时间字符串
	 *
	 * @param millis long类型的毫秒数
	 * @param pattern 字符串的时间格式
	 * @param locale Locale对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Date date,
			String pattern,
			Locale locale) {
		return format(date, pattern, null, locale);
	}

	/**
	 * 功能描述： 根据 Calendar 对象，和时间格式，得到一个时间字符串
	 *
	 * @param calendar Calendar对象
	 * @param pattern 字符串的时间格式
	 * @param locale Locale对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Calendar calendar,
			String pattern,
			Locale locale) {
		return format(calendar, pattern, null, locale);
	}

	/**
	 * 功能描述： 根据毫秒数，和时间格式，得到一个时间字符串
	 *
	 * @param millis long类型的毫秒数
	 * @param pattern 字符串的时间格式
	 * @param locale Locale对象
	 * @param timeZone TimeZone对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(long millis,
			String pattern,
			TimeZone timeZone,
			Locale locale) {
		return format(new Date(millis), pattern, timeZone, locale);
	}

	/**
	 * 功能描述： 根据 Date 对象，和时间格式，得到一个时间字符串
	 *
	 * @param date Date对象
	 * @param pattern 字符串的时间格式
	 * @param timeZone TimeZone对象
	 * @param locale Locale对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Date date,
			String pattern,
			TimeZone timeZone,
			Locale locale) {
		FastDateFormat df = FastDateFormat.getInstance(pattern,
				timeZone,
				locale);
		return df.format(date);
	}

	/**
	 * 功能描述： 根据 Date 对象，和时间格式，得到一个时间字符串
	 *
	 * @param Calendar calendar对象
	 * @param pattern 字符串的时间格式
	 * @param timeZone TimeZone对象
	 * @param locale Locale对象
	 * @return String 时间字符串
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static String format(Calendar calendar,
			String pattern,
			TimeZone timeZone,
			Locale locale) {
		FastDateFormat df = FastDateFormat.getInstance(pattern,
				timeZone,
				locale);
		return df.format(calendar);
	}

}
