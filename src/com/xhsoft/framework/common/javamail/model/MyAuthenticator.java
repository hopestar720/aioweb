package com.xhsoft.framework.common.javamail.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	private String username;
	private String password;
	
	public MyAuthenticator(String username,String passowrd){
		this.username = username;
		this.password = passowrd;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(username,password);
	}
	
	
	
}
