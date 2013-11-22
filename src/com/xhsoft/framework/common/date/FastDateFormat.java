package com.xhsoft.framework.common.date;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 功能描述：为得到各种格式化数据，而封装的辅助工具类. 
 *
 * @author daixl
 * @since 2011.02.23
 */
public class FastDateFormat extends Format {
	/**
	 * Required for serialization support.
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * FULL locale dependent date or time style.
	 */
	public static final int FULL = DateFormat.FULL;

	/**
	 * LONG locale dependent date or time style.
	 */
	public static final int LONG = DateFormat.LONG;

	/**
	 * MEDIUM locale dependent date or time style.
	 */
	public static final int MEDIUM = DateFormat.MEDIUM;

	/**
	 * SHORT locale dependent date or time style.
	 */
	public static final int SHORT = DateFormat.SHORT;

	/** The c default pattern. */
	private static String cDefaultPattern; // lazily initialised by getInstance()

	/** The Constant cInstanceCache. */
	private static final Map<FastDateFormat, FastDateFormat> cInstanceCache = new HashMap<FastDateFormat, FastDateFormat>(7);

	/** The Constant cDateInstanceCache. */
	private static final Map<Object, FastDateFormat> cDateInstanceCache = new HashMap<Object, FastDateFormat>(7);

	/** The Constant cTimeInstanceCache. */
	private static final Map<Object, FastDateFormat> cTimeInstanceCache = new HashMap<Object, FastDateFormat>(7);

	/** The Constant cDateTimeInstanceCache. */
	private static final Map<Object, FastDateFormat> cDateTimeInstanceCache = new HashMap<Object, FastDateFormat>(7);

	/** The Constant cTimeZoneDisplayCache. */
	private static final Map<Object, String> cTimeZoneDisplayCache = new HashMap<Object, String>(7);

	/**
	 * The pattern.
	 */
	private final String mPattern;

	/**
	 * The time zone.
	 */
	private final TimeZone mTimeZone;

	/**
	 * Whether the time zone overrides any on Calendars.
	 */
	private final boolean mTimeZoneForced;

	/**
	 * The locale.
	 */
	private final Locale mLocale;

	/**
	 * Whether the locale overrides the default.
	 */
	private final boolean mLocaleForced;

	/**
	 * The parsed rules.
	 */
	private transient Rule[] mRules;

	/**
	 * The estimated maximum length.
	 */
	private transient int mMaxLengthEstimate;

	/**
	 * Gets the single instance of FastDateFormat.
	 *
	 * @return single instance of FastDateFormat
	 */
	public static FastDateFormat getInstance() {
		return getInstance(getDefaultPattern(), null, null);
	}

	/**
	 * Gets the single instance of FastDateFormat.
	 *
	 * @param pattern the pattern
	 * @return single instance of FastDateFormat
	 */
	public static FastDateFormat getInstance(String pattern) {
		return getInstance(pattern, null, null);
	}

	/**
	 * Gets the single instance of FastDateFormat.
	 *
	 * @param pattern the pattern
	 * @param timeZone the time zone
	 * @return single instance of FastDateFormat
	 */
	public static FastDateFormat getInstance(String pattern,
			TimeZone timeZone) {
		return getInstance(pattern, timeZone, null);
	}

	/**
	 * Gets the single instance of FastDateFormat.
	 *
	 * @param pattern the pattern
	 * @param locale the locale
	 * @return single instance of FastDateFormat
	 */
	public static FastDateFormat getInstance(String pattern,
			Locale locale) {
		return getInstance(pattern, null, locale);
	}

	/**
	 * Gets the single instance of FastDateFormat.
	 *
	 * @param pattern the pattern
	 * @param timeZone the time zone
	 * @param locale the locale
	 * @return single instance of FastDateFormat
	 */
	public static synchronized FastDateFormat getInstance(String pattern,
			TimeZone timeZone,
			Locale locale) {
		FastDateFormat emptyFormat = new FastDateFormat(pattern,
				timeZone,
				locale);
		FastDateFormat format = cInstanceCache.get(emptyFormat);
		if (format == null) {
			format = emptyFormat;
			format.init(); // convert shell format into usable one
			cInstanceCache.put(format, format); // this is OK!
		}
		return format;
	}

	/**
	 * Gets the date instance.
	 *
	 * @param style the style
	 * @return the date instance
	 */
	public static FastDateFormat getDateInstance(int style) {
		return getDateInstance(style, null, null);
	}

	/**
	 * Gets the date instance.
	 *
	 * @param style the style
	 * @param locale the locale
	 * @return the date instance
	 */
	public static FastDateFormat getDateInstance(int style,
			Locale locale) {
		return getDateInstance(style, null, locale);
	}

	/**
	 * Gets the date instance.
	 *
	 * @param style the style
	 * @param timeZone the time zone
	 * @return the date instance
	 */
	public static FastDateFormat getDateInstance(int style,
			TimeZone timeZone) {
		return getDateInstance(style, timeZone, null);
	}

	/**
	 * Gets the date instance.
	 *
	 * @param style the style
	 * @param timeZone the time zone
	 * @param locale the locale
	 * @return the date instance
	 */
	public static synchronized FastDateFormat getDateInstance(int style,
			TimeZone timeZone,
			Locale locale) {
		Object key = Integer.valueOf(style);
		if (timeZone != null) {
			key = new Pair(key, timeZone);
		}

		if (locale == null) {
			locale = Locale.getDefault();
		}

		key = new Pair(key, locale);

		FastDateFormat format = cDateInstanceCache.get(key);
		if (format == null) {
			try {
				SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance(style,
						locale);
				String pattern = formatter.toPattern();
				format = getInstance(pattern, timeZone, locale);
				cDateInstanceCache.put(key, format);

			} catch (ClassCastException ex) {
				throw new IllegalArgumentException("No date pattern for locale: "
						+ locale);
			}
		}
		return format;
	}

	/**
	 * Gets the time instance.
	 *
	 * @param style the style
	 * @return the time instance
	 */
	public static FastDateFormat getTimeInstance(int style) {
		return getTimeInstance(style, null, null);
	}

	/**
	 * Gets the time instance.
	 *
	 * @param style the style
	 * @param locale the locale
	 * @return the time instance
	 */
	public static FastDateFormat getTimeInstance(int style,
			Locale locale) {
		return getTimeInstance(style, null, locale);
	}

	/**
	 * Gets the time instance.
	 *
	 * @param style the style
	 * @param timeZone the time zone
	 * @return the time instance
	 */
	public static FastDateFormat getTimeInstance(int style,
			TimeZone timeZone) {
		return getTimeInstance(style, timeZone, null);
	}

	/**
	 * Gets the time instance.
	 *
	 * @param style the style
	 * @param timeZone the time zone
	 * @param locale the locale
	 * @return the time instance
	 */
	public static synchronized FastDateFormat getTimeInstance(int style,
			TimeZone timeZone,
			Locale locale) {
		Object key = Integer.valueOf(style);
		if (timeZone != null) {
			key = new Pair(key, timeZone);
		}
		if (locale != null) {
			key = new Pair(key, locale);
		}

		FastDateFormat format = cTimeInstanceCache.get(key);
		if (format == null) {
			if (locale == null) {
				locale = Locale.getDefault();
			}

			try {
				SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getTimeInstance(style,
						locale);
				String pattern = formatter.toPattern();
				format = getInstance(pattern, timeZone, locale);
				cTimeInstanceCache.put(key, format);

			} catch (ClassCastException ex) {
				throw new IllegalArgumentException("No date pattern for locale: "
						+ locale);
			}
		}
		return format;
	}

	/**
	 * Gets the date time instance.
	 *
	 * @param dateStyle the date style
	 * @param timeStyle the time style
	 * @return the date time instance
	 */
	public static FastDateFormat getDateTimeInstance(int dateStyle,
			int timeStyle) {
		return getDateTimeInstance(dateStyle, timeStyle, null, null);
	}

	/**
	 * Gets the date time instance.
	 *
	 * @param dateStyle the date style
	 * @param timeStyle the time style
	 * @param locale the locale
	 * @return the date time instance
	 */
	public static FastDateFormat getDateTimeInstance(int dateStyle,
			int timeStyle,
			Locale locale) {
		return getDateTimeInstance(dateStyle, timeStyle, null, locale);
	}

	/**
	 * Gets the date time instance.
	 *
	 * @param dateStyle the date style
	 * @param timeStyle the time style
	 * @param timeZone the time zone
	 * @return the date time instance
	 */
	public static FastDateFormat getDateTimeInstance(int dateStyle,
			int timeStyle,
			TimeZone timeZone) {
		return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
	}

	/**
	 * Gets the date time instance.
	 *
	 * @param dateStyle the date style
	 * @param timeStyle the time style
	 * @param timeZone the time zone
	 * @param locale the locale
	 * @return the date time instance
	 */
	public static synchronized FastDateFormat getDateTimeInstance(int dateStyle,
			int timeStyle,
			TimeZone timeZone,
			Locale locale) {

		Object key = new Pair(Integer.valueOf(dateStyle),
				Integer.valueOf(timeStyle));
		if (timeZone != null) {
			key = new Pair(key, timeZone);
		}
		if (locale == null) {
			locale = Locale.getDefault();
		}
		key = new Pair(key, locale);

		FastDateFormat format = cDateTimeInstanceCache.get(key);
		if (format == null) {
			try {
				SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateTimeInstance(dateStyle,
						timeStyle,
						locale);
				String pattern = formatter.toPattern();
				format = getInstance(pattern, timeZone, locale);
				cDateTimeInstanceCache.put(key, format);

			} catch (ClassCastException ex) {
				throw new IllegalArgumentException("No date time pattern for locale: "
						+ locale);
			}
		}
		return format;
	}

	/**
	 * Gets the time zone display.
	 *
	 * @param tz the tz
	 * @param daylight the daylight
	 * @param style the style
	 * @param locale the locale
	 * @return the time zone display
	 */
	static synchronized String getTimeZoneDisplay(TimeZone tz,
			boolean daylight,
			int style,
			Locale locale) {
		Object key = new TimeZoneDisplayKey(tz, daylight, style, locale);
		String value = cTimeZoneDisplayCache.get(key);
		if (value == null) {
			value = tz.getDisplayName(daylight, style, locale);
			cTimeZoneDisplayCache.put(key, value);
		}
		return value;
	}

	/**
	 * <p>Gets the default pattern.</p>
	 * 
	 * @return the default pattern
	 */
	private static synchronized String getDefaultPattern() {
		if (cDefaultPattern == null) {
			cDefaultPattern = new SimpleDateFormat().toPattern();
		}
		return cDefaultPattern;
	}

	/**
	 * Instantiates a new fast date format.
	 *
	 * @param pattern the pattern
	 * @param timeZone the time zone
	 * @param locale the locale
	 */
	protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale) {
		super();
		if (pattern == null) {
			throw new IllegalArgumentException("The pattern must not be null");
		}
		mPattern = pattern;

		mTimeZoneForced = (timeZone != null);
		if (timeZone == null) {
			timeZone = TimeZone.getDefault();
		}
		mTimeZone = timeZone;

		mLocaleForced = (locale != null);
		if (locale == null) {
			locale = Locale.getDefault();
		}
		mLocale = locale;
	}

	/**
	 * Inits the.
	 */
	protected void init() {
		List<Rule> rulesList = parsePattern();
		mRules = rulesList.toArray(new Rule[rulesList.size()]);

		int len = 0;
		for (int i = mRules.length; --i >= 0;) {
			len += mRules[i].estimateLength();
		}

		mMaxLengthEstimate = len;
	}

	/**
	 * Parses the pattern.
	 *
	 * @return the list
	 */
	protected List<Rule> parsePattern() {
		DateFormatSymbols symbols = new DateFormatSymbols(mLocale);
		List<Rule> rules = new ArrayList<Rule>();

		String[] ERAs = symbols.getEras();
		String[] months = symbols.getMonths();
		String[] shortMonths = symbols.getShortMonths();
		String[] weekdays = symbols.getWeekdays();
		String[] shortWeekdays = symbols.getShortWeekdays();
		String[] AmPmStrings = symbols.getAmPmStrings();

		int length = mPattern.length();
		int[] indexRef = new int[1];

		for (int i = 0; i < length; i++) {
			indexRef[0] = i;
			String token = parseToken(mPattern, indexRef);
			i = indexRef[0];

			int tokenLen = token.length();
			if (tokenLen == 0) {
				break;
			}

			Rule rule;
			char c = token.charAt(0);

			switch (c) {
				case 'G': // era designator (text)
					rule = new TextField(Calendar.ERA, ERAs);
					break;
				case 'y': // year (number)
					if (tokenLen >= 4) {
						rule = selectNumberRule(Calendar.YEAR, tokenLen);
					} else {
						rule = TwoDigitYearField.INSTANCE;
					}
					break;
				case 'M': // month in year (text and number)
					if (tokenLen >= 4) {
						rule = new TextField(Calendar.MONTH, months);
					} else if (tokenLen == 3) {
						rule = new TextField(Calendar.MONTH, shortMonths);
					} else if (tokenLen == 2) {
						rule = TwoDigitMonthField.INSTANCE;
					} else {
						rule = UnpaddedMonthField.INSTANCE;
					}
					break;
				case 'd': // day in month (number)
					rule = selectNumberRule(Calendar.DAY_OF_MONTH, tokenLen);
					break;
				case 'h': // hour in am/pm (number, 1..12)
					rule = new TwelveHourField(selectNumberRule(Calendar.HOUR,
							tokenLen));
					break;
				case 'H': // hour in day (number, 0..23)
					rule = selectNumberRule(Calendar.HOUR_OF_DAY, tokenLen);
					break;
				case 'm': // minute in hour (number)
					rule = selectNumberRule(Calendar.MINUTE, tokenLen);
					break;
				case 's': // second in minute (number)
					rule = selectNumberRule(Calendar.SECOND, tokenLen);
					break;
				case 'S': // millisecond (number)
					rule = selectNumberRule(Calendar.MILLISECOND, tokenLen);
					break;
				case 'E': // day in week (text)
					rule = new TextField(Calendar.DAY_OF_WEEK,
							tokenLen < 4 ? shortWeekdays : weekdays);
					break;
				case 'D': // day in year (number)
					rule = selectNumberRule(Calendar.DAY_OF_YEAR, tokenLen);
					break;
				case 'F': // day of week in month (number)
					rule = selectNumberRule(Calendar.DAY_OF_WEEK_IN_MONTH,
							tokenLen);
					break;
				case 'w': // week in year (number)
					rule = selectNumberRule(Calendar.WEEK_OF_YEAR, tokenLen);
					break;
				case 'W': // week in month (number)
					rule = selectNumberRule(Calendar.WEEK_OF_MONTH, tokenLen);
					break;
				case 'a': // am/pm marker (text)
					rule = new TextField(Calendar.AM_PM, AmPmStrings);
					break;
				case 'k': // hour in day (1..24)
					rule = new TwentyFourHourField(selectNumberRule(Calendar.HOUR_OF_DAY,
							tokenLen));
					break;
				case 'K': // hour in am/pm (0..11)
					rule = selectNumberRule(Calendar.HOUR, tokenLen);
					break;
				case 'z': // time zone (text)
					if (tokenLen >= 4) {
						rule = new TimeZoneNameRule(mTimeZone,
								mTimeZoneForced,
								mLocale,
								TimeZone.LONG);
					} else {
						rule = new TimeZoneNameRule(mTimeZone,
								mTimeZoneForced,
								mLocale,
								TimeZone.SHORT);
					}
					break;
				case 'Z': // time zone (value)
					if (tokenLen == 1) {
						rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
					} else {
						rule = TimeZoneNumberRule.INSTANCE_COLON;
					}
					break;
				case '\'': // literal text
					String sub = token.substring(1);
					if (sub.length() == 1) {
						rule = new CharacterLiteral(sub.charAt(0));
					} else {
						rule = new StringLiteral(sub);
					}
					break;
				default:
					throw new IllegalArgumentException("Illegal pattern component: "
							+ token);
			}

			rules.add(rule);
		}

		return rules;
	}

	/**
	 * Parses the token.
	 *
	 * @param pattern the pattern
	 * @param indexRef the index ref
	 * @return the string
	 */
	protected String parseToken(String pattern,
			int[] indexRef) {
		StringBuilder buf = new StringBuilder();

		int i = indexRef[0];
		int length = pattern.length();

		char c = pattern.charAt(i);
		if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
			// Scan a run of the same character, which indicates a time
			// pattern.
			buf.append(c);

			while (i + 1 < length) {
				char peek = pattern.charAt(i + 1);
				if (peek == c) {
					buf.append(c);
					i++;
				} else {
					break;
				}
			}
		} else {
			// This will identify token as text.
			buf.append('\'');

			boolean inLiteral = false;

			for (; i < length; i++) {
				c = pattern.charAt(i);

				if (c == '\'') {
					if (i + 1 < length && pattern.charAt(i + 1) == '\'') {
						// '' is treated as escaped '
						i++;
						buf.append(c);
					} else {
						inLiteral = !inLiteral;
					}
				} else if (!inLiteral
						&& (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) {
					i--;
					break;
				} else {
					buf.append(c);
				}
			}
		}

		indexRef[0] = i;
		return buf.toString();
	}

	/**
	 * Select number rule.
	 *
	 * @param field the field
	 * @param padding the padding
	 * @return the number rule
	 */
	protected NumberRule selectNumberRule(int field,
			int padding) {
		switch (padding) {
			case 1:
				return new UnpaddedNumberField(field);
			case 2:
				return new TwoDigitNumberField(field);
			default:
				return new PaddedNumberField(field, padding);
		}
	}

	@Override
	public StringBuffer format(Object obj,
			StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj instanceof Date) {
			return format((Date) obj, toAppendTo);
		} else if (obj instanceof Calendar) {
			return format((Calendar) obj, toAppendTo);
		} else if (obj instanceof Long) {
			return format(((Long) obj).longValue(), toAppendTo);
		} else {
			throw new IllegalArgumentException("Unknown class: "
					+ (obj == null ? "<null>" : obj.getClass().getName()));
		}
	}

	/**
	 * Format.
	 *
	 * @param millis the millis
	 * @return the string
	 */
	public String format(long millis) {
		return format(new Date(millis));
	}

	/**
	 * Format.
	 *
	 * @param date the date
	 * @return the string
	 */
	public String format(Date date) {
		Calendar c = new GregorianCalendar(mTimeZone);
		c.setTime(date);
		return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
	}

	/**
	 * Format.
	 *
	 * @param calendar the calendar
	 * @return the string
	 */
	public String format(Calendar calendar) {
		return format(calendar, new StringBuffer(mMaxLengthEstimate)).toString();
	}

	/**
	 * Format.
	 *
	 * @param millis the millis
	 * @param buf the buf
	 * @return the string buffer
	 */
	public StringBuffer format(long millis,
			StringBuffer buf) {
		return format(new Date(millis), buf);
	}

	/**
	 * Format.
	 *
	 * @param date the date
	 * @param buf the buf
	 * @return the string buffer
	 */
	public StringBuffer format(Date date,
			StringBuffer buf) {
		Calendar c = new GregorianCalendar(mTimeZone);
		c.setTime(date);
		return applyRules(c, buf);
	}

	/**
	 * Format.
	 *
	 * @param calendar the calendar
	 * @param buf the buf
	 * @return the string buffer
	 */
	public StringBuffer format(Calendar calendar,
			StringBuffer buf) {
		if (mTimeZoneForced) {
			calendar.getTimeInMillis(); /// LANG-538
			calendar = (Calendar) calendar.clone();
			calendar.setTimeZone(mTimeZone);
		}
		return applyRules(calendar, buf);
	}

	/**
	 * Apply rules.
	 *
	 * @param calendar the calendar
	 * @param buf the buf
	 * @return the string buffer
	 */
	protected StringBuffer applyRules(Calendar calendar,
			StringBuffer buf) {
		Rule[] rules = mRules;
		int len = mRules.length;
		for (int i = 0; i < len; i++) {
			rules[i].appendTo(buf, calendar);
		}
		return buf;
	}

	@Override
	public Object parseObject(String source,
			ParsePosition pos) {
		pos.setIndex(0);
		pos.setErrorIndex(0);
		return null;
	}

	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	public String getPattern() {
		return mPattern;
	}

	/**
	 * Gets the time zone.
	 *
	 * @return the time zone
	 */
	public TimeZone getTimeZone() {
		return mTimeZone;
	}

	/**
	 * Gets the time zone overrides calendar.
	 *
	 * @return the time zone overrides calendar
	 */
	public boolean getTimeZoneOverridesCalendar() {
		return mTimeZoneForced;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public Locale getLocale() {
		return mLocale;
	}

	/**
	 * Gets the max length estimate.
	 *
	 * @return the max length estimate
	 */
	public int getMaxLengthEstimate() {
		return mMaxLengthEstimate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FastDateFormat == false) {
			return false;
		}
		FastDateFormat other = (FastDateFormat) obj;
		if ((mPattern == other.mPattern || mPattern.equals(other.mPattern))
				&& (mTimeZone == other.mTimeZone || mTimeZone.equals(other.mTimeZone))
				&& (mLocale == other.mLocale || mLocale.equals(other.mLocale))
				&& (mTimeZoneForced == other.mTimeZoneForced)
				&& (mLocaleForced == other.mLocaleForced)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int total = 0;
		total += mPattern.hashCode();
		total += mTimeZone.hashCode();
		total += (mTimeZoneForced ? 1 : 0);
		total += mLocale.hashCode();
		total += (mLocaleForced ? 1 : 0);
		return total;
	}

	@Override
	public String toString() {
		return "FastDateFormat[" + mPattern + "]";
	}

	/**
	 * Read object.
	 *
	 * @param in the in
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		init();
	}

	/**
	 * The Interface Rule.
	 */
	private interface Rule {

		/**
		 * Estimate length.
		 *
		 * @return the int
		 */
		int estimateLength();

		/**
		 * Append to.
		 *
		 * @param buffer the buffer
		 * @param calendar the calendar
		 */
		void appendTo(StringBuffer buffer,
				Calendar calendar);
	}

	/**
	 * The Interface NumberRule.
	 */
	private interface NumberRule extends Rule {

		/**
		 * Append to.
		 *
		 * @param buffer the buffer
		 * @param value the value
		 */
		void appendTo(StringBuffer buffer,
				int value);
	}

	/**
	 * The Class CharacterLiteral.
	 */
	private static class CharacterLiteral implements Rule {

		/** The m value. */
		private final char mValue;

		/**
		 * Instantiates a new character literal.
		 *
		 * @param value the value
		 */
		CharacterLiteral(char value) {
			mValue = value;
		}

		public int estimateLength() {
			return 1;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			buffer.append(mValue);
		}
	}

	/**
	 * The Class StringLiteral.
	 */
	private static class StringLiteral implements Rule {

		/** The m value. */
		private final String mValue;

		/**
		 * Instantiates a new string literal.
		 *
		 * @param value the value
		 */
		StringLiteral(String value) {
			mValue = value;
		}

		public int estimateLength() {
			return mValue.length();
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			buffer.append(mValue);
		}
	}

	/**
	 * The Class TextField.
	 */
	private static class TextField implements Rule {

		/** The m field. */
		private final int mField;

		/** The m values. */
		private final String[] mValues;

		/**
		 * Instantiates a new text field.
		 *
		 * @param field the field
		 * @param values the values
		 */
		TextField(int field, String[] values) {
			mField = field;
			mValues = values;
		}

		public int estimateLength() {
			int max = 0;
			for (int i = mValues.length; --i >= 0;) {
				int len = mValues[i].length();
				if (len > max) {
					max = len;
				}
			}
			return max;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			buffer.append(mValues[calendar.get(mField)]);
		}
	}

	/**
	 * The Class UnpaddedNumberField.
	 */
	private static class UnpaddedNumberField implements NumberRule {

		/** The m field. */
		private final int mField;

		/**
		 * Instantiates a new unpadded number field.
		 *
		 * @param field the field
		 */
		UnpaddedNumberField(int field) {
			mField = field;
		}

		public int estimateLength() {
			return 4;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(mField));
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			if (value < 10) {
				buffer.append((char) (value + '0'));
			} else if (value < 100) {
				buffer.append((char) (value / 10 + '0'));
				buffer.append((char) (value % 10 + '0'));
			} else {
				buffer.append(Integer.toString(value));
			}
		}
	}

	/**
	 * The Class UnpaddedMonthField.
	 */
	private static class UnpaddedMonthField implements NumberRule {

		/** The Constant INSTANCE. */
		static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

		/**
		 * Instantiates a new unpadded month field.
		 */
		UnpaddedMonthField() {
			super();
		}

		public int estimateLength() {
			return 2;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(Calendar.MONTH) + 1);
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			if (value < 10) {
				buffer.append((char) (value + '0'));
			} else {
				buffer.append((char) (value / 10 + '0'));
				buffer.append((char) (value % 10 + '0'));
			}
		}
	}

	/**
	 * The Class PaddedNumberField.
	 */
	private static class PaddedNumberField implements NumberRule {

		/** The m field. */
		private final int mField;

		/** The m size. */
		private final int mSize;

		/**
		 * Instantiates a new padded number field.
		 *
		 * @param field the field
		 * @param size the size
		 */
		PaddedNumberField(int field, int size) {
			if (size < 3) {
				throw new IllegalArgumentException();
			}
			mField = field;
			mSize = size;
		}

		public int estimateLength() {
			return 4;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(mField));
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			if (value < 100) {
				for (int i = mSize; --i >= 2;) {
					buffer.append('0');
				}
				buffer.append((char) (value / 10 + '0'));
				buffer.append((char) (value % 10 + '0'));
			} else {
				int digits;
				if (value < 1000) {
					digits = 3;
				} else {
					if (!(value > -1)) {
						throw new IllegalArgumentException(String.format("Negative values should not be possible",
								Long.valueOf(value)));
					}
					digits = Integer.toString(value).length();
				}
				for (int i = mSize; --i >= digits;) {
					buffer.append('0');
				}
				buffer.append(Integer.toString(value));
			}
		}
	}

	/**
	 * The Class TwoDigitNumberField.
	 */
	private static class TwoDigitNumberField implements NumberRule {

		/** The m field. */
		private final int mField;

		/**
		 * Instantiates a new two digit number field.
		 *
		 * @param field the field
		 */
		TwoDigitNumberField(int field) {
			mField = field;
		}

		public int estimateLength() {
			return 2;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(mField));
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			if (value < 100) {
				buffer.append((char) (value / 10 + '0'));
				buffer.append((char) (value % 10 + '0'));
			} else {
				buffer.append(Integer.toString(value));
			}
		}
	}

	/**
	 * The Class TwoDigitYearField.
	 */
	private static class TwoDigitYearField implements NumberRule {

		/** The Constant INSTANCE. */
		static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

		/**
		 * Instantiates a new two digit year field.
		 */
		TwoDigitYearField() {
			super();
		}

		public int estimateLength() {
			return 2;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(Calendar.YEAR) % 100);
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			buffer.append((char) (value / 10 + '0'));
			buffer.append((char) (value % 10 + '0'));
		}
	}

	/**
	 * The Class TwoDigitMonthField.
	 */
	private static class TwoDigitMonthField implements NumberRule {

		/** The Constant INSTANCE. */
		static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

		/**
		 * Instantiates a new two digit month field.
		 */
		TwoDigitMonthField() {
			super();
		}

		public int estimateLength() {
			return 2;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			appendTo(buffer, calendar.get(Calendar.MONTH) + 1);
		}

		public final void appendTo(StringBuffer buffer,
				int value) {
			buffer.append((char) (value / 10 + '0'));
			buffer.append((char) (value % 10 + '0'));
		}
	}

	/**
	 * The Class TwelveHourField.
	 */
	private static class TwelveHourField implements NumberRule {

		/** The m rule. */
		private final NumberRule mRule;

		/**
		 * Instantiates a new twelve hour field.
		 *
		 * @param rule the rule
		 */
		TwelveHourField(NumberRule rule) {
			mRule = rule;
		}

		public int estimateLength() {
			return mRule.estimateLength();
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			int value = calendar.get(Calendar.HOUR);
			if (value == 0) {
				value = calendar.getLeastMaximum(Calendar.HOUR) + 1;
			}
			mRule.appendTo(buffer, value);
		}

		public void appendTo(StringBuffer buffer,
				int value) {
			mRule.appendTo(buffer, value);
		}
	}

	/**
	 * The Class TwentyFourHourField.
	 */
	private static class TwentyFourHourField implements NumberRule {

		/** The m rule. */
		private final NumberRule mRule;

		/**
		 * Instantiates a new twenty four hour field.
		 *
		 * @param rule the rule
		 */
		TwentyFourHourField(NumberRule rule) {
			mRule = rule;
		}

		public int estimateLength() {
			return mRule.estimateLength();
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			int value = calendar.get(Calendar.HOUR_OF_DAY);
			if (value == 0) {
				value = calendar.getMaximum(Calendar.HOUR_OF_DAY) + 1;
			}
			mRule.appendTo(buffer, value);
		}

		public void appendTo(StringBuffer buffer,
				int value) {
			mRule.appendTo(buffer, value);
		}
	}

	/**
	 * The Class TimeZoneNameRule.
	 */
	private static class TimeZoneNameRule implements Rule {

		/** The m time zone. */
		private final TimeZone mTimeZone;

		/** The m time zone forced. */
		private final boolean mTimeZoneForced;

		/** The m locale. */
		private final Locale mLocale;

		/** The m style. */
		private final int mStyle;

		/** The m standard. */
		private final String mStandard;

		/** The m daylight. */
		private final String mDaylight;

		/**
		 * Constructs an instance of <code>TimeZoneNameRule</code> with the specified properties.
		 * 
		 * @param timeZone the time zone
		 * @param timeZoneForced if <code>true</code> the time zone is forced into standard and daylight
		 * @param locale the locale
		 * @param style the style
		 */
		TimeZoneNameRule(TimeZone timeZone,
				boolean timeZoneForced,
				Locale locale,
				int style) {
			mTimeZone = timeZone;
			mTimeZoneForced = timeZoneForced;
			mLocale = locale;
			mStyle = style;

			if (timeZoneForced) {
				mStandard = getTimeZoneDisplay(timeZone, false, style, locale);
				mDaylight = getTimeZoneDisplay(timeZone, true, style, locale);
			} else {
				mStandard = null;
				mDaylight = null;
			}
		}

		public int estimateLength() {
			if (mTimeZoneForced) {
				return Math.max(mStandard.length(), mDaylight.length());
			} else if (mStyle == TimeZone.SHORT) {
				return 4;
			} else {
				return 40;
			}
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			if (mTimeZoneForced) {
				if (mTimeZone.useDaylightTime()
						&& calendar.get(Calendar.DST_OFFSET) != 0) {
					buffer.append(mDaylight);
				} else {
					buffer.append(mStandard);
				}
			} else {
				TimeZone timeZone = calendar.getTimeZone();
				if (timeZone.useDaylightTime()
						&& calendar.get(Calendar.DST_OFFSET) != 0) {
					buffer.append(getTimeZoneDisplay(timeZone,
							true,
							mStyle,
							mLocale));
				} else {
					buffer.append(getTimeZoneDisplay(timeZone,
							false,
							mStyle,
							mLocale));
				}
			}
		}
	}

	/**
	 * The Class TimeZoneNumberRule.
	 */
	private static class TimeZoneNumberRule implements Rule {

		/** The Constant INSTANCE_COLON. */
		static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);

		/** The Constant INSTANCE_NO_COLON. */
		static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);

		/** The m colon. */
		final boolean mColon;

		/**
		 * Instantiates a new time zone number rule.
		 *
		 * @param colon the colon
		 */
		TimeZoneNumberRule(boolean colon) {
			mColon = colon;
		}

		public int estimateLength() {
			return 5;
		}

		public void appendTo(StringBuffer buffer,
				Calendar calendar) {
			int offset = calendar.get(Calendar.ZONE_OFFSET)
					+ calendar.get(Calendar.DST_OFFSET);

			if (offset < 0) {
				buffer.append('-');
				offset = -offset;
			} else {
				buffer.append('+');
			}

			int hours = offset / (60 * 60 * 1000);
			buffer.append((char) (hours / 10 + '0'));
			buffer.append((char) (hours % 10 + '0'));

			if (mColon) {
				buffer.append(':');
			}

			int minutes = offset / (60 * 1000) - 60 * hours;
			buffer.append((char) (minutes / 10 + '0'));
			buffer.append((char) (minutes % 10 + '0'));
		}
	}

	/**
	 * The Class TimeZoneDisplayKey.
	 */
	private static class TimeZoneDisplayKey {

		/** The m time zone. */
		private final TimeZone mTimeZone;

		/** The m style. */
		private final int mStyle;

		/** The m locale. */
		private final Locale mLocale;

		/**
		 * Constructs an instance of <code>TimeZoneDisplayKey</code> with the specified properties.
		 *  
		 * @param timeZone the time zone
		 * @param daylight adjust the style for daylight saving time if <code>true</code>
		 * @param style the timezone style
		 * @param locale the timezone locale
		 */
		TimeZoneDisplayKey(TimeZone timeZone,
				boolean daylight,
				int style,
				Locale locale) {
			mTimeZone = timeZone;
			if (daylight) {
				style |= 0x80000000;
			}
			mStyle = style;
			mLocale = locale;
		}

		@Override
		public int hashCode() {
			return mStyle * 31 + mLocale.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj instanceof TimeZoneDisplayKey) {
				TimeZoneDisplayKey other = (TimeZoneDisplayKey) obj;
				return mTimeZone.equals(other.mTimeZone)
						&& mStyle == other.mStyle
						&& mLocale.equals(other.mLocale);
			}
			return false;
		}
	}

	/**
	 * The Class Pair.
	 */
	private static class Pair {

		/** The m obj1. */
		private final Object mObj1;

		/** The m obj2. */
		private final Object mObj2;

		/**
		 * Instantiates a new pair.
		 *
		 * @param obj1 the obj1
		 * @param obj2 the obj2
		 */
		public Pair(Object obj1, Object obj2) {
			mObj1 = obj1;
			mObj2 = obj2;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Pair)) {
				return false;
			}

			Pair key = (Pair) obj;

			return (mObj1 == null ? key.mObj1 == null : mObj1.equals(key.mObj1))
					&& (mObj2 == null ? key.mObj2 == null
							: mObj2.equals(key.mObj2));
		}

		@Override
		public int hashCode() {
			return (mObj1 == null ? 0 : mObj1.hashCode())
					+ (mObj2 == null ? 0 : mObj2.hashCode());
		}

		@Override
		public String toString() {
			return "[" + mObj1 + ':' + mObj2 + ']';
		}
	}

}
