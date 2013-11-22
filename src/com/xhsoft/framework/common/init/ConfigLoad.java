/*
 * $RCSfile: ConfigLoad,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.init;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.InputStream;

/**
* <p>Title:ConfigLoad</p>
* <p>Description:* 加载Config.xml配置文件中的值，赋给Config的变量。 解析系统初始配置文件Config.xml，格式： <config> <config
* name="LOGON_ACTION_NAME" value="wewe"/> <config name="PAGE_SIZE" value="3"/></p>
* <p>Copyright:Copyright (C) 2011</p>
* @author wenzhi
* @date 2011-12-28
*/
@SuppressWarnings("unchecked")
public class ConfigLoad extends DefaultHandler
{

	private static Log log = LogFactory.getLog(ConfigLoad.class);
	
	HashMap configMap = new HashMap();

	public ConfigLoad()
	{
	}

	/**
	 * <p>Description:在元属开始事件中进行的工作</p>
	 * @param namespaceURI	
	 * @param localName  
	 * @param rawName  
	 * @param attrs  
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 * @exception SAXException
	 */
	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attrs) throws SAXException 
	{
		/** 解析config节点*/
		if (rawName.equals("config")) { 
			String name = attrs.getValue("name");
			String value = attrs.getValue("value");
			if (name == null || value == null) {
				throw new SAXException("xml format error");
			}
			configMap.put(name, value);
		}
	}

	/**
	 * <p>Description:在元属结束事件中进行的工作</p>
	 * @param uri	
	 * @param local  
	 * @param rawName  
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 * @exception SAXException
	 */
	public void endElement(String uri, String local, String rawName)
			throws SAXException
	{
	}

	/**
	 * <p>Description:完成对xmlString的解析工作</p>
	 * @param strFilePath	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public void parser(String strFilePath) 
	{
		try {
			/**从类路径读取配置文件。*/
			InputStream stream = this.getClass().getResourceAsStream(
					strFilePath);
			if (stream == null) {
				log.warn(strFilePath + " not found");
			}
			/**创建一个SAXParser解析器对象，并进行解析。*/
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			saxParser.parse(stream, this);
			/** 为全局变量赋值。*/
			this.loadConfig(this.configMap);
		} catch (Exception ex) {
			log.error("加载 " + strFilePath + " 失败! 使用默认的配置.");
			ex.printStackTrace();
		}
	}

	/**
	 * <p>Description:把MDGLConfig.xml配置文件中的值，赋给Config的变量.</p>
	 * @param configMap	
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
	public void loadConfig(HashMap configMap) 
	{
		try {
			Config.WEB_REAL_PATH = (String) configMap
					.get("WEB_REAL_PATH");
		} catch (Exception e) {
			log.error("为系统配置参数赋值时出错！");
			e.printStackTrace();
		}
	}

}
