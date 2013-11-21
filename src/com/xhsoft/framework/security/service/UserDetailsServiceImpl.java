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
			throw new UsernameNotFoundException("�û�������");
		}
		
		User user = users.get(0);
		return init(user);
	}
	
	private User init(User user){
		List<Group> groups = user.getGroups();
		if(groups == null || groups.isEmpty()){
			throw new UsernameNotFoundException("�û�δ������");
		}
		
		// ����Ȩ�޼���
		Set<Role> roles = new HashSet<Role>();
		for (Group group : groups) {
			roles.addAll(group.getRoles());
		}
		
		// ��ɫȨ�޼���
		Collection<GrantedAuthority> gaRoles = new HashSet<GrantedAuthority>();
		Collection<GrantedResource> gaResources = new HashSet<GrantedResource>();
		
		for (Role role : roles) {
			gaRoles.add(new SimpleGrantedAuthority(role.getName()));
			role = this.getRoleService().findById(role);
			if (role.getResources() != null && !role.getResources().isEmpty())
				for (Resource resource : role.getResources()) { // ȡ����ɫ��ӵ�е���Դ
					gaResources.add(new SimpleGrantedResource(resource.getUri()));
				}
		}

		if (gaRoles == null || gaRoles.isEmpty())
			throw new UsernameNotFoundException("Ȩ�޲���!");

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
