package com.xhsoft.framework.common.mailserver;

public class MailServer {
	
	/** Ĭ�Ϸ����� */
	private String defaultFrom;
	/** ������ */
	private String host;
	/** �˿� */
	private Integer port;
	/** �û��� */
	private String user;
	/** ���� */
	private String password;
	
	
	public String getDefaultFrom() {
		return defaultFrom;
	}
	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
