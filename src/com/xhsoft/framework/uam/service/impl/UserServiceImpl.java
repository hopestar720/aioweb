package com.xhsoft.framework.uam.service.impl;

import java.util.List;

import com.xhsoft.framework.base.service.impl.BaseServiceImpl;
import com.xhsoft.framework.uam.entity.User;
import com.xhsoft.framework.uam.service.IUserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Override
	public List<User> findUserByName(String username) {
		return null;
	}

}
