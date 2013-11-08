package com.xhsoft.framework.common.mailserver;

public class MailServer {
	
	/** 默认发件人 */
	private String defaultFrom;
	/** 服务器 */
	private String host;
	/** 端口 */
	private Integer port;
	/** 用户名 */
	private String user;
	/** 密码 */
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
