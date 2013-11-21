package com.xhsoft.framework.uam.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.xhsoft.framework.base.entity.AbstractEntity;
import com.xhsoft.framework.security.model.GrantedResource;
import com.xhsoft.framework.security.model.UserDetails;

public class User extends AbstractEntity implements UserDetails {
	
	private String username;
	private String password;
	private Integer status;
	
	private List<Group> groups; // 所属分组
	
	private Collection<? extends GrantedAuthority> authorities; // 权限集合
	private Collection<? extends GrantedResource> resources; // 资源集合

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Collection<? extends GrantedResource> getResources() {
		return resources;
	}

	public void setResources(Collection<? extends GrantedResource> resources) {
		this.resources = resources;
	}


}
