package com.xhsoft.framework.uam.service;

import com.xhsoft.framework.base.service.BaseService;
import com.xhsoft.framework.uam.entity.Role;

public interface RoleService extends BaseService<Role> {

	Role findById(Role role);

}
