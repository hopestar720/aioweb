package com.xhsoft.framework.security.model;

import java.io.Serializable;

/**
 * 授权资源接口
 * @author hopestar720@126.com
 * @since 2013年11月21日
 */
public interface GrantedResource extends Serializable{

	public abstract String getResource();
}
