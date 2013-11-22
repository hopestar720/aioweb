package com.xhsoft.framework.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	
	public static final String REG_MAIL = "";
	public static final String REG_PHONE = "";
	public static final String REG_MOBILE = "";
	public static final String REG_HTTP_URL = "";
	public static final String REG_WIN_PATH = "";
	public static final String REG_UNIX_PATH = "";
	public static final String REG_ID = "";
	public static final String REG_ONLY_CH = "";
	public static final String REG_NO_SPECIAL_CHAR = "";
	public static final String REG_NO_BLANK = "";
	public static final String REG_ONLY_EN_OR_NUM = "";
	
	public static boolean isMatch(String value,String rule){
		boolean result = false;
		Pattern pat = Pattern.compile(rule);
		Matcher mac = pat.matcher(value);
		result = mac.matches();
		return result;
	}
	
	public static boolean isMatchAll(String value,String ...rules){
		boolean result = false;
		for(String rule:rules){
			Pattern pat = Pattern.compile(rule);
			Matcher mac = pat.matcher(value);
			result = mac.matches();
			if(result==false){
				return false;
			}
		}
		return result;
	}
	
	public static String replace(){
		String result = "";
		return result;
	}

}
