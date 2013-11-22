package com.xhsoft.framework.uam.entity;

import java.util.List;

import com.xhsoft.framework.base.entity.AbstractEntity;

public class Res extends AbstractEntity {
	
	private String uri; //·��
	private String type;//����
	private String remark; // ������Ϣ
	private Integer status; // ״̬ 1.�� 2.���� 3.ɾ�� 4.�쳣
	
	private List<Role> roles; // Ȩ�޼���

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
