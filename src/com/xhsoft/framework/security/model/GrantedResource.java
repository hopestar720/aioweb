package com.xhsoft.framework.security.model;

import java.io.Serializable;

/**
 * ��Ȩ��Դ�ӿ�
 * @author hopestar720@126.com
 * @since 2013��11��21��
 */
public interface GrantedResource extends Serializable{

	public abstract String getResource();
}
