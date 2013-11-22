package com.xhsoft.framework.uam.service;

import java.util.List;

import com.xhsoft.framework.base.service.IBaseService;
import com.xhsoft.framework.uam.entity.User;

public interface IUserService extends IBaseService<User> {

	List<User> findUserByName(String username);

}
