package com.xhsoft.framework.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	//InputStream in = lnew BufferedInputStream(new FileInputStream(name));
	//ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault());
	//InputStream in = new BufferedInputStream(new FileInputStream(name));
	//InputStream in = JProperties.class.getResourceAsStream(name);
	// InputStream in = JProperties.class.getClassLoader().getResourceAsStream(name);
	//InputStream in = ClassLoader.getSystemResourceAsStream(name);
	//InputStream in = javax.servlet.ServletContext.getResourceAsStream(path);
	private Properties property = new Properties();
	
	public  PropertiesUtil(){
		try {
			property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  PropertiesUtil(String propName){
		try {
			property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PropertiesUtil(InputStream inStream){
		try {
			property.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getValueByKey(String key){
		return property.getProperty(key);
	}
	
	public void setValueByKey(String key,String value){
		property.setProperty(key, value);
		save();
	}
	
	public void addKeyValue(String key,String value){
		property.put(key, value);
		save();
	}
	
	
	public Map<Object,Object> getMapPropData(){
		Map<Object,Object> mapData = new HashMap<Object,Object>();
		Iterator<Entry<Object,Object>> it = property.entrySet().iterator();
		while(it.hasNext()){
			Entry<Object,Object> entry = it.next();
			mapData.put(entry.getKey(), entry.getValue());
		}
		return mapData;
	}
	
	public  Map<String,String> getStringMapData(){
		Map<String,String> mapData = new HashMap<String,String>();
		Set<String> setPropName = property.stringPropertyNames();
		for(String propName:setPropName){
			mapData.put(propName, property.getProperty(propName));
		}
		return mapData;
	}
	
	public void convertXmlToProp(String filePath){
		try {
			OutputStream os = new FileOutputStream(filePath);
			String comment = "convert from property tool at "+new Date();
			String encoding = "utf-8";
			property.storeToXML(os, comment, encoding );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save(){
		OutputStream outStream = null;
		String comments = this.getComments();
		try {
			property.store(outStream, comments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveAs(OutputStream outStream){
		String comments = this.getComments();
		try {
			property.store(outStream, comments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void list(PrintStream ps){
		property.list(ps);
	}
	
	private String getComments(){
		StringBuilder sb = new StringBuilder();
		sb.append("##------------------------##");
		sb.append("##Powerd By Xhsoft ##");
		sb.append("##Created At "+new Date()+"##");
		sb.append("##------------------------##");
		return sb.toString();
	}
	
}
