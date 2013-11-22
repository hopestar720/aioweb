/**
 * Copyright (c) 2012, xhsoft and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.javamail.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * TODO
 * 
 * @author lijw
 * @since 2012-11-13
 */
public class MailAuthenticator extends Authenticator {

	String username = null;
	String password = null;

	public MailAuthenticator() {
	}

	public MailAuthenticator(String username, String password) {
		this.password = password;
		this.username = username;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
