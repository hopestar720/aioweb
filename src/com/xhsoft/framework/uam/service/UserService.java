package com.xhsoft.framework.uam.service;

import java.util.List;

import com.xhsoft.framework.base.service.BaseService;
import com.xhsoft.framework.uam.entity.User;

public interface UserService extends BaseService<User> {

	List<User> findUserByName(String username);

}
