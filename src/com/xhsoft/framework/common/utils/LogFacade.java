package com.xhsoft.framework.common.utils;

import java.util.HashMap;
import java.util.Map;

public class LogFacade {
	private static Map<String, Object> LoggerMap = new HashMap<String, Object>();

	/**
	 * Gets the log java.
	 *
	 * @param name the name
	 * @return the log java
	 */
	public static java.util.logging.Logger getLogJava(String name) {
		return java.util.logging.Logger.getLogger(name);
	}

	/**
	 * Gets the log4j.
	 *
	 * @param clazz the clazz
	 * @return the log4j
	 */
	public static org.apache.log4j.Logger getLog4j(Class clazz) {
		return org.apache.log4j.Logger.getLogger(clazz);
	}

	public static org.apache.log4j.Logger getLog4j(String clazz) {
		if (!LoggerMap.containsKey(clazz)) {
			LoggerMap.put(clazz, org.apache.log4j.Logger.getLogger(clazz));
		}
		return (org.apache.log4j.Logger) LoggerMap.get(clazz);
	}

	/**
	 * Gets the log common.
	 *
	 * @param clazz the clazz
	 * @return the log common
	 */
	public static org.apache.commons.logging.Log getLogCommon(Class clazz) {
		return org.apache.commons.logging.LogFactory.getLog(clazz);
	}

	public static void debug(String loggerName,
			String message) {
		getLog4j(loggerName).debug(message);
	}

	public static void info(String loggerName,
			String message) {
		getLog4j(loggerName).info(message);
	}

	public static void error(String loggerName,
			String message) {
		getLog4j(loggerName).error(message);
	}

	public static void warn(String loggerName,
			String message) {
		getLog4j(loggerName).warn(message);
	}
}
