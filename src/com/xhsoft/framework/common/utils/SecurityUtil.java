package com.xhsoft.framework.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

	public final static String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	public SecurityContext getSecurityContext() {
		return (SecurityContext) Utils.ContextUtil.getSession()
				.getAttribute(SPRING_SECURITY_CONTEXT);
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取SECURITY3的认证对象
	 * 
	 * @return UserDetails
	 */
	public UserDetails getUserDetails() {
		return (UserDetails) getAuthentication().getPrincipal();
	}

}