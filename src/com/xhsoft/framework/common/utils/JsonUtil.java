package com.xhsoft.framework.common.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

/**
 * <p>Title: JsonUtil.java</p> 
 * <p>Description: Json转换工具类 依赖包json-lib</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-25
 */
public class JsonUtil {
	private static final String SUM = "sum";
	private static final String ROOT = "root";
	private static final String HEADER="header";
	
	private static final String SUCCESS = "success";
	private static final String FALIURE = "faliure";
	
	public static  String convertToJson(int sum,Object data){
		JSONObject json = new JSONObject();
		JSONArray root = JSONArray.fromObject(data, getJsonConfig());
		json.put(SUM, sum==0?root.size():sum);
		json.put(ROOT, root);
		return json.toString();
	}
	
	public static String convertToJson(Object data){
		if(data.getClass().isArray()||data instanceof Collection<?>){
			return JSONArray.fromObject(data, getJsonConfig()).toString();
		}else{
			return JSONObject.fromObject(data, getJsonConfig()).toString();
		}
	}
	
	public static String convertMsgToJson(String msg,int msgType){
		JSONObject json = new JSONObject();
		json.put("type",msgType);
		json.put("text", msg);
		return json.toString();
	}
	
	private static JsonConfig getJsonConfig(){
		JsonConfig jc = new JsonConfig();
		jc.setJsonPropertyFilter(new IgnoreNullPropertyFilter());
		jc.registerJsonValueProcessor(Date.class, new TimestampToJsonValueProcessor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		//jc.registerJsonBeanProcessor(target, jsonBeanProcessor);
		//jc.setJsonBeanProcessorMatcher(jsonBeanProcessorMatcher);
		return jc;
	}
	
	private static JSONObject getJsonObject(int sum,Object data,Map<String,String> map,List<String> fieldFilter,Map<String,String> header){
		JSONObject json = new JSONObject();
		//加入列头部分
		if(header!=null){
			//json.put(HEADER, value)
		}
		JSONArray root = JSONArray.fromObject(data,getJsonConfig());
		json.put(SUM, sum==0?root.size():sum);
		json.put(ROOT, data);
		return json;
	}
	
	
	
}

/**
 * <p>Title: JsonUtil.java</p> 
 * <p>Description: 日期格式化转换器内部类</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-25
 */
class TimestampToJsonValueProcessor implements JsonValueProcessor{
	
	private SimpleDateFormat sf;
	
	public TimestampToJsonValueProcessor(SimpleDateFormat sf){
		this.sf = sf;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return sf.format(value);
	}
	
}

/**
 * <p>Title: JsonUtil.java</p> 
 * <p>Description: 过滤空属性内部类</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-25
 */
class IgnoreNullPropertyFilter implements PropertyFilter {

	@Override
	public boolean apply(Object source, String name, Object value) {
		if(value == null){
			return true;
		}
		return false;
	}
}

/**
 * <p>Title: JsonUtil.java</p> 
 * <p>Description: Hibernate实体过滤</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-25
 */
class HibernateJsonBeanProcessor implements JsonBeanProcessor{

	@Override
	public JSONObject processBean(Object value, JsonConfig config) {
		
		return null;
	}
	
}
