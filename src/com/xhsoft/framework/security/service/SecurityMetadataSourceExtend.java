package com.xhsoft.framework.security.service;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public interface SecurityMetadataSourceExtend extends
		FilterInvocationSecurityMetadataSource {
	
	/** ��ʼ������ */
	public void init();

	/** ������Դ */
	public void load();

	/** ˢ����Դ */
	public void expireNow();

}
