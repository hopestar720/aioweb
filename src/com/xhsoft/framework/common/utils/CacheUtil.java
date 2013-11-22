package com.xhsoft.framework.common.utils;

import java.util.Map;
import java.util.TreeMap;

public class CacheUtil {

	//字典表数据缓存
	
	private static final Map<String, Object> MAP = new TreeMap<String, Object>();
	
	public static Map<String,Object> getCache(String index){
		if(MAP==null){
			 
		}
		return MAP;
	}
}
