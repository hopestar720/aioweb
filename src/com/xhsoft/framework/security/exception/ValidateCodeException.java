package com.xhsoft.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 * @author hopestar720@126.com
 * @since 2013年11月22日
 */
public class ValidateCodeException  extends AuthenticationException{

	public ValidateCodeException(String msg) {
		super(msg);
	}

	public ValidateCodeException(String msg, Throwable t) {
		super(msg, t);
	}
}
