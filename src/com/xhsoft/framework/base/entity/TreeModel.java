package com.xhsoft.framework.base.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@MappedSuperclass
public class TreeModel extends AbstractEntity {
	private boolean expanded;
	private boolean leaf;
	private int width = 0;
	@Transient
	private boolean last;
	@Transient
	private boolean first;
	@Transient
	private String[] rows;
	@Transient
	private String checked;
	@Transient
	private int level;
	@Transient
	private List<? extends TreeModel> child = new ArrayList<TreeModel>();

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String[] getRows() {
		return rows;
	}

	public void setRows(String[] rows) {
		this.rows = rows;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<?> getChild() {
		return child;
	}

	public void setChild(List<? extends TreeModel> child) {
		this.child = child;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
