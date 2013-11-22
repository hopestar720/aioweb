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
import com.xhsoft.framework.uam.entity.Res;
import com.xhsoft.framework.uam.entity.Role;
import com.xhsoft.framework.uam.entity.User;
import com.xhsoft.framework.uam.service.IRoleService;
import com.xhsoft.framework.uam.service.IUserService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private IUserService userService;
	private IRoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<User> users = this.getUserService().findUserByName(username);
		
		if(users == null|| users.isEmpty()){
			throw new UsernameNotFoundException("用户/密码错误,请重新输入!");
		}
		
		User user = users.get(0);
		return init(user);
	}
	
	/** 初始化 */
	private User init(User user){
		List<Group> groups = user.getGroups();
		if(groups == null || groups.isEmpty()){
			throw new UsernameNotFoundException("尚未分配权限组!");
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
			role = this.getRoleService().findByPrimaryKey(role.getSid());
			if (role.getResources() != null && !role.getResources().isEmpty())
				for (Res resource : role.getResources()) {// 取出角色所拥有的资源
					gaResources.add(new SimpleGrantedResource(resource.getUri()));
				}
		}

		if (gaRoles == null || gaRoles.isEmpty())
			throw new UsernameNotFoundException("权限不足!");

		user.setAuthorities(gaRoles);
		user.setResources(gaResources);
		
		return user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

}
