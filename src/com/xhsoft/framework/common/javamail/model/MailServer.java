package com.xhsoft.framework.common.javamail.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailServer {

	/** host */
	private String host;
	/** prot */
	private String port;
	/** isValidate */
	private Integer isValidate;
	/** username */
	private String username;
	/** password */
	private String password;
	/** fromAddress */
	private String fromAddress;
	
	
	public Properties getProperties(){
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


	public Integer getIsValidate() {
		return isValidate;
	}


	public void setIsValidate(Integer isValidate) {
		this.isValidate = isValidate;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
}
