/*
 * $RCSfile: Page,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.page;

import java.util.List;

/**
 * <p>Title:Page</p>
 * <p>Description:分布</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class Page<T>
{
	
	public static final String PAGE_NO="qm.pn";
	public static final String PAGE_LIMIT="qm.limit";

	/** 一页显示的记录数*/
	private int limit = 10;
	/** 记录总数*/
	private int totalRows;
	/** 当前页码*/
	private int pageNo;
	/** 总页数*/
	private int totalPages;
	/** 结果集存放List*/
	private List<T> resultList;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/** 计算总页数*/
	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getOffset() {
		return (pageNo - 1) * limit;
	}

	/**
	 * <p>Description:getEndIndex</p>
	 * @return int
	 * @author wenzhi
	 * @version 1.0
	 */
	public int getEndIndex()
	{
		if (getOffset() + limit > totalRows) {
			return totalRows;
		} else {
			return getOffset() + limit;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
