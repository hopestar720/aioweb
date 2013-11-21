package com.xhsoft.framework.uam.entity;

import java.util.List;

import com.xhsoft.framework.base.entity.AbstractEntity;

public class Resource extends AbstractEntity {
	
	private String uri; //路径
	private String type;//类型
	private String remark; // 描述信息
	private Integer status; // 状态 1.正常 2.冻结 3.删除 4.异常
	
	private List<Role> roles; // 权限集合

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
