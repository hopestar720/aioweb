/*
 * $RCSfile: TreeNode,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.model;

/**
 * <p>Title:TreeNode</p>
 * <p>Description:TreeNode</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class TreeNode 
{
	/**树的节点id*/
	private Long id;	
	/**树的节点name*/
	private String name;	
	/**节点文字*/
	private String text;
	/**链接地址*/
	private String href;
	/**链接指向*/
	private String hrefTarget;
	/**是否为叶子节点*/
	private boolean leaf;
	/**节点样式，folder为文件夹，file 为文件图标*/
	private String cls;		
	/**check是否选中 */
	private boolean checked;
	/**是否展开 */
    private boolean expandable; 
    /**描述信息*/
    private String description; 

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getCls() {
		return cls;
	}
	
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String getHrefTarget() {
		return hrefTarget;
	}
	
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	
	public boolean isExpandable() {
		return expandable;
	}
	
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
