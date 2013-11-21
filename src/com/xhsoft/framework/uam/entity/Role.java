package com.xhsoft.framework.uam.entity;

import java.util.List;

import com.xhsoft.framework.base.entity.AbstractEntity;

public class Role extends AbstractEntity {
	
	private String name; // 角色名称
	private String remark; // 描述信息
	private Integer status; // 状态 1.正常 2.冻结 3.删除 4.异常
	
	private List<Resource> resources; // 资源自合

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
