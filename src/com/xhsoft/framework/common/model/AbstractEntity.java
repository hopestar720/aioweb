package com.xhsoft.framework.common.model;

import java.io.Serializable;
import java.util.Date;

public class AbstractEntity implements Serializable {
	
	
	/** sid */
	private long sid;
	/** createBy */
	private String createBy;
	/** updateBy */
	private String updateBy;
	/** createDt */
	private Date createDt;
	/** updateDt */
	private Date updateDt;
	/** version */
	private int version;
	
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}
