package com.xhsoft.framework.security.model;

import java.util.Collection;

public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

	public abstract Collection getResources();
}
