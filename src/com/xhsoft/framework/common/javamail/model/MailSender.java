package com.xhsoft.framework.common.javamail.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class MailSender implements Serializable{
	
	/** host */
	private String host;
	/** prot */
	private String port;
	/** isDebug */
	private boolean isDebug;
	/** isAllowReadSocketInfo */
	private boolean  isAllowReadSocketInfo;
	/** address */
	private String address;
	
	public static Properties getProperties(){
		Properties prop = new Properties();
		InputStream inStream = MailSender.class.getResourceAsStream("mail.properties");
		try {
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public boolean isDebug() {
		return isDebug;
	}
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
	public boolean isAllowReadSocketInfo() {
		return isAllowReadSocketInfo;
	}
	public void setAllowReadSocketInfo(boolean isAllowReadSocketInfo) {
		this.isAllowReadSocketInfo = isAllowReadSocketInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}