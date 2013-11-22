package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.ObjectName;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.web.RemoteJMXBrokerFacade;
import org.apache.activemq.web.config.SystemPropertiesConfiguration;

public class JmxUtil {
	
	private static JmxUtil instance = null;
	private static SystemPropertiesConfiguration  configuration = null;
	private static RemoteJMXBrokerFacade remoteJMXconnection = null;

	private JmxUtil(){
		Properties p = new Properties();
		InputStream inStream = JmxUtil.class.getResourceAsStream("jmx.properties");
		try {
			p.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		configuration = new SystemPropertiesConfiguration();
		System.setProperty("webconsole.jmx.url",p.getProperty("webconsole.jmx.url"));
		System.setProperty("webconsole.jmx.user",p.getProperty("webconsole.jmx.user"));
		System.setProperty("webconsole.jmx.password",p.getProperty("webconsole.jmx.password"));
	}
	
	private JmxUtil(String url,String user,String password){
		configuration = new SystemPropertiesConfiguration();
		System.setProperty("webconsole.jmx.url",url);
		System.setProperty("webconsole.jmx.user",user);
		System.setProperty("webconsole.jmx.password",password);
	}
	
	public synchronized static JmxUtil getInstance(String url,String user,String password){
		if(instance == null){
			instance = new JmxUtil(url,user,password);
		}
		return instance;
	}
	
	public synchronized static JmxUtil getInstance(){
		if(instance == null){
			instance = new JmxUtil();
		}
		return instance;
	}
	
	
	
	public RemoteJMXBrokerFacade getJmxConnection() {
		if (remoteJMXconnection == null) {
			try {
				remoteJMXconnection = new RemoteJMXBrokerFacade();
				remoteJMXconnection.setConfiguration(configuration);
			} catch (Exception e) {
				throw new RuntimeException(e.getLocalizedMessage());
			}
		}
		return remoteJMXconnection;
	}
	
	
	/**
	 * 删除单个mq队列
	 * @param queueName 队列号
	 * @return
	 */
	public boolean removeQueue(String queueName) {
		boolean result = true;
		RemoteJMXBrokerFacade createConnector =getJmxConnection();
		BrokerViewMBean brokerAdmin = null;
		try {
			brokerAdmin = createConnector.getBrokerAdmin();
			
			brokerAdmin.removeQueue(queueName);
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	
	
	/**
	 * 删除mq需要删除的全部队列
	 * @return
	 */
	public boolean removeQueue() {
		boolean result = true;
		RemoteJMXBrokerFacade createConnector =getJmxConnection();
		BrokerViewMBean brokerAdmin = null;
		try {
			brokerAdmin = createConnector.getBrokerAdmin();
			ObjectName[] o=brokerAdmin.getQueues();
			for(ObjectName objn:o){
				//on.getCanonicalName()中的值类似
				//org.apache.activemq:BrokerName=localhost,Destination=39697,Type=Queue
				//从中 拿到 类似 39697 和 39697-state的队列			
				String canonicalName=objn.getCanonicalName();
				if(canonicalName!=null&&canonicalName!=""){
					String [] cn=canonicalName.split(",");
					for(String desQu:cn){
						if(desQu.contains("Destination")){
							
							String desQuD=desQu.substring(desQu.indexOf("=")+1);
							
							if(!desQuD.equals("")){//可能出现空队列
								//截取第一个字符如果是数字就删除这个队列，因为要删的类似39697或者39697-state
								String firstStr=desQuD.substring(0, 1);
								Pattern pattern = Pattern.compile("[0-9]+");
								Matcher matcher = pattern.matcher((CharSequence) firstStr);  
								boolean isNum = matcher.matches(); 
								
								if(isNum){//是数字的为删除为要删除队列
									System.out.println("要删除的队列:"+desQuD);
									brokerAdmin.removeQueue(desQuD);
								}else if(firstStr.equals("-")){//队列为“-state”也删除
									System.out.println("要删除的队列:"+desQuD);
									brokerAdmin.removeQueue(desQuD);
								}else{//不需删除的队列
									System.out.println("不需删除的队列"+desQuD);
								}
							}else{
								System.out.println("要删除的队列:"+desQuD);
								brokerAdmin.removeQueue(desQuD);
							}
						}
					}
				}
				
			}
			
			//查看剩余队列
			ObjectName[] oName=brokerAdmin.getQueues();
			for(ObjectName objN:oName){
				//on.getCanonicalName()中的值类似
				//org.apache.activemq:BrokerName=localhost,Destination=39697,Type=Queue
				//从中 拿到 类似 39697 和 39697-state的队列
				String canonicalName=objN.getCanonicalName();
				if(canonicalName!=null&&canonicalName!=""){
					System.out.println("删除后剩余队列"+canonicalName);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}
}
