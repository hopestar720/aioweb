package com.xhsoft.framework.security.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xhsoft.framework.security.model.GrantedResource;
import com.xhsoft.framework.security.model.SimpleGrantedResource;
import com.xhsoft.framework.uam.entity.Group;
import com.xhsoft.framework.uam.entity.Resource;
import com.xhsoft.framework.uam.entity.Role;
import com.xhsoft.framework.uam.entity.User;
import com.xhsoft.framework.uam.service.RoleService;
import com.xhsoft.framework.uam.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserService userService;
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<User> users = this.getUserService().findUserByName(username);
		
		if(users == null|| users.isEmpty()){
			throw new UsernameNotFoundException("用户不存在");
		}
		
		User user = users.get(0);
		return init(user);
	}
	
	private User init(User user){
		List<Group> groups = user.getGroups();
		if(groups == null || groups.isEmpty()){
			throw new UsernameNotFoundException("用户未分配组");
		}
		
		// 分组权限集合
		Set<Role> roles = new HashSet<Role>();
		for (Group group : groups) {
			roles.addAll(group.getRoles());
		}
		
		// 角色权限集合
		Collection<GrantedAuthority> gaRoles = new HashSet<GrantedAuthority>();
		Collection<GrantedResource> gaResources = new HashSet<GrantedResource>();
		
		for (Role role : roles) {
			gaRoles.add(new SimpleGrantedAuthority(role.getName()));
			role = this.getRoleService().findById(role);
			if (role.getResources() != null && !role.getResources().isEmpty())
				for (Resource resource : role.getResources()) { // 取出角色所拥有的资源
					gaResources.add(new SimpleGrantedResource(resource.getUri()));
				}
		}

		if (gaRoles == null || gaRoles.isEmpty())
			throw new UsernameNotFoundException("权限不足!");

		user.setAuthorities(gaRoles);
		user.setResources(gaResources);
		
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
