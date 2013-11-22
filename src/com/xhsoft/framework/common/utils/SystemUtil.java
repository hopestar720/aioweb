package com.xhsoft.framework.common.utils;

import java.io.File;

/**
 * 功能描述： 系统所用的工具类
 * 
 * @version 2.0
 * @author daixl
 */
public class SystemUtil {

	/** The System property key for the user home directory.*/
	private static final String USER_HOME_KEY = "user.home";

	/**The System property key for the user directory.*/
	private static final String USER_DIR_KEY = "user.dir";

	/**The System property key for the Java IO temporary directory.*/
	private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";

	/**The System property key for the Java home directory.*/
	private static final String JAVA_HOME_KEY = "java.home";

	/** 文件的编码常量 */
	public static final String FILE_ENCODING = getSystemProperty("file.encoding");

	/** 文件的分隔符常量*/
	public static final String FILE_SEPARATOR = getSystemProperty("file.separator");

	/** JAVA的CLASS_PATH路径常量 */
	public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");

	/** JAVA_HOME路径常量 */
	public static final String JAVA_HOME = getSystemProperty(JAVA_HOME_KEY);

	/** JAVA临时目录常量  */
	public static final String JAVA_IO_TMPDIR = getSystemProperty(JAVA_IO_TMPDIR_KEY);

	/** JAVA版本常量  */
	public static final String JAVA_VERSION = getSystemProperty("java.version");

	/** 用户目录常量  */
	public static final String USER_DIR = getSystemProperty(USER_DIR_KEY);

	/** 用户主目录   */
	public static final String USER_HOME = getSystemProperty(USER_HOME_KEY);

	/** 语言常量  */
	public static final String USER_LANGUAGE = getSystemProperty("user.language");

	/** JAVA修该版本常量 */
	public static final String JAVA_VERSION_TRIMMED = getJavaVersionTrimmed();

	/** JAVA1.1版本 */
	public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");

	/** JAVA1.2版本 */
	public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");

	/** JAVA1.3版本 */
	public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");

	/** JAVA1.4版本 */
	public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");

	/** JAVA1.5版本  */
	public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");

	/** JAVA1.6版本  */
	public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");

	/** JAVA1.7版本 */
	public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");

	/**
	 * 功能描述： 根据JAVA_HOME获得java文件
	 *
	 * @return File 返回文件对象
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static File getJavaHome() {
		return new File(System.getProperty(JAVA_HOME_KEY));
	}

	/**
	 * 功能描述： 根据JAVA的临时目录获得java文件
	 *
	 * @return File 返回文件对象
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static File getJavaIoTmpDir() {
		return new File(System.getProperty(JAVA_IO_TMPDIR_KEY));
	}

	/**
	 * 功能描述： 根据一个java版本号，返回一个布尔值
	 *
	 * @param versionPrefix   java版本的字符串版本号
	 * @return File 返回boolean值
	 * @author daixl
	 * @since 2011.02.23
	 */
	private static boolean getJavaVersionMatches(String versionPrefix) {
		return isJavaVersionMatch(JAVA_VERSION_TRIMMED, versionPrefix);
	}

	/**
	 * 功能描述： 返回JAVA版本号
	 *
	 * @return String 返回JAVA版本号，字符串表示形式
	 * @author daixl
	 * @since 2011.02.23
	 */
	private static String getJavaVersionTrimmed() {
		if (JAVA_VERSION != null) {
			for (int i = 0; i < JAVA_VERSION.length(); i++) {
				char ch = JAVA_VERSION.charAt(i);
				if (ch >= '0' && ch <= '9') {
					return JAVA_VERSION.substring(i);
				}
			}
		}
		return null;
	}

	/**
	 * 功能描述： 根据一个字符串标示，返回一个配置文件中的字符串值
	 *
	 * @param property  字符串标示
	 * @return String 返回一个配置文件中的字符串值
	 * @author daixl
	 * @since 2011.02.23
	 */
	private static String getSystemProperty(String property) {
		try {
			return System.getProperty(property);
		} catch (SecurityException ex) {
			// we are not allowed to look at this property
			System.err.println("Caught a SecurityException reading the system property '"
					+ property
					+ "'; the SystemUtils property value will default to null.");
			return null;
		}
	}

	/**
	 * 功能描述： 从指定目录中返回一个文件对象
	 *
	 * @return File 返回一个文件对象
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static File getUserDir() {
		return new File(System.getProperty(USER_DIR_KEY));
	}

	/**
	 * 功能描述： 从指定目录中返回一个文件对象
	 *
	 * @return File 返回一个文件对象
	 * @author daixl
	 * @since 2011.02.23
	 */
	public static File getUserHome() {
		return new File(System.getProperty(USER_HOME_KEY));
	}

	/**
	 * 功能描述： 从指定目录中返回一个文件对象
	 *
	 * @param 字符串的版本号
	 * @param 字符串的版本分隔符
	 * @return boolean 返回一个布尔对象
	 * @author daixl
	 * @since 2011.02.23
	 */
	static boolean isJavaVersionMatch(String version,
			String versionPrefix) {
		if (version == null) {
			return false;
		}
		return version.startsWith(versionPrefix);
	}

	public SystemUtil() {
		super();
	}
}
