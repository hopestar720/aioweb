package com.xhsoft.framework.uam.service.impl;

import java.util.List;

import com.xhsoft.framework.uam.entity.User;
import com.xhsoft.framework.uam.service.UserService;

public class UserServiceImpl<T extends User> implements UserService {

	@Override
	public List<User> findUserByName(String username) {
		return null;
	}

}
