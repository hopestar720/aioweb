package com.xhsoft.framework.uam.entity;

import java.util.List;

import com.xhsoft.framework.base.entity.AbstractEntity;

public class Role extends AbstractEntity {
	
	private String name; // ��ɫ���
	private String remark; // ������Ϣ
	private Integer status; // ״̬ 1.�� 2.���� 3.ɾ�� 4.�쳣
	
	private List<Res> resources; // ��Դ�Ժ�

	public List<Res> getResources() {
		return resources;
	}

	public void setResources(List<Res> resources) {
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
