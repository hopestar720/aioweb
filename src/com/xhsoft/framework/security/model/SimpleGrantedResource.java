package com.xhsoft.framework.security.model;

import org.springframework.util.Assert;

/**
 * 授权资源实现类
 * @author hopestar720@126.com
 * @since 2013年11月21日
 */
public class SimpleGrantedResource implements GrantedResource{

	private final String resource;
	
	public SimpleGrantedResource(String resource) {
		
		Assert.hasText(resource,
				"A granted resource textual representation is required");
		this.resource = resource;
		
	}
	
	@Override
	public String getResource() {
		return resource;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof SimpleGrantedResource)
			return resource.equals(((SimpleGrantedResource) obj).resource);
		else
			return false;
	}

	public int hashCode() {
		return resource.hashCode();
	}

	public String toString() {
		return resource;
	}

}
