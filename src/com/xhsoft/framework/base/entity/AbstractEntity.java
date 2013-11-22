package com.xhsoft.framework.base.entity;

import java.io.Serializable;
import java.util.Date;

public class AbstractEntity implements Serializable {
	
	/** 序列主键 */
	private Long sid;
	/** 乐观锁版本 */
	private Integer version;
	/** 创建者(登录帐号) */
	private String createBy;
	/** 创建时间 */
	private Date createDt;
	/** 最后更新者(登录帐号) */
	private String updateBy;
	/** 最后更新时间 */
	private Date updateDt;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
}
