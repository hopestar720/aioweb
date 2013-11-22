package com.xhsoft.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 请求方法错误异常
 * @author hopestar720@126.com
 * @since 2013年11月22日
 */
public class MethodErrorException extends AuthenticationException{

	public MethodErrorException(String msg) {
		super(msg);
	}

	public MethodErrorException(String msg, Throwable t) {
		super(msg, t);
	}
}
