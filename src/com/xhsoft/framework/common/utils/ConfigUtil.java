package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: ConfigUtil.java</p> 
 * <p>Description: 配置文件读取工具类</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-26
 */
public class ConfigUtil {
	
	//单例对象
	private static ConfigUtil instance = null;
	//配置文件对象
	private static Properties property;
	
	private ConfigUtil(){
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("top.properties");
		try {
			property = new Properties();
			property.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ConfigUtil(String prop){
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(prop);
		try {
			property = new Properties();
			property.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConfigUtil getInstance(){
		if(instance == null){
			instance = new ConfigUtil();
		}
		return instance;
	}
	
	public static synchronized ConfigUtil getInstance(String propName){
		if(instance == null){
			instance = new ConfigUtil(propName);
		}
		return instance;
	}
	
	public String getValueByKey(String key){
		String value = "";
		if(property.get(key)!=null){
			value = property.getProperty(key).toString();
		}
		return value;
	}

}
