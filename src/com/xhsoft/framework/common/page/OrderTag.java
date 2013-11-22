/*
 * $RCSfile: OrderTag,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.NoSuchMessageException;

/**
 * <p>Title:OrderTag</p>
 * <p>Description:OrderTag</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class OrderTag extends BodyTagSupport 
{

	private static final long serialVersionUID = -2586874404915700305L;
	private static final String ORDER = "qm.order";

	/** 排序项目的字段名称 */
	private String field;
	/** 排序项目的抬头信息 */
	private String title;
	/** AJAX操作的目标DIV名称 */
	private String targets;

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * <p>Description:标签核心方法</p>
	 * @return int
	 * @author wenzhi
	 * @version 1.0
	 * @exception Exception
	 */
	@Override
	public int doStartTag() throws JspException
	{
		/** 获取request*/
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String descMessage = "descMessage";
		String  ascMessage = "ascMessage";
		String realTitle = this.getTitle();
		try {
		} catch (NoSuchMessageException e1) {
		}

		String sortType = "";
		String image = "";
		int cnt=0;

		String order = request.getParameter(ORDER);
		if (StringUtils.isNotEmpty(order)) {
			/** 将当前排序属性和当前标签属性比较*/
			String[] orderItems = order.split(",");
			for (String orderItem : orderItems)
			{
				cnt++;
				List<String> list = new ArrayList<String>();
				String[] items = orderItem.split(" ");
				for (String item : items) {
					if (item.length() > 0) {
						list.add(item);
					}
				}
				
				/**找到当前项目*/
				if(list.get(0).equalsIgnoreCase(field)){
					sortType=list.get(1);
					/** 动态生成显示排序状态的图片*/
					image = "<img border='0' src='" + request.getContextPath()
							+ "/images/table/sort_" + sortType
							+ ".gif' align='absmiddle' title='"
							+ ("asc".equals(sortType) ? ascMessage : descMessage)
							+ "'/>"+"<span class='orderCount'>"+cnt+"</span>";
					break;
				}
				
			}
		}

		try {
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			String reverseSortType = null;
			/** 交换排序类型*/
			if ("".equals(sortType)) {
				reverseSortType = "asc";
			}else if ("asc".equals(sortType)) {
				reverseSortType = "desc";
			}else if ("desc".equals(sortType)) {
				reverseSortType = "na";
			}
			String titleMessage = "";
			sb.append("<a href=\"javascript:setOrder('" + field + "','"
					+ reverseSortType + "','" + targets + "');\" title=\""
					+ titleMessage + "\">");
			sb.append(realTitle);
			sb.append("</a>");
			sb.append(image);
			out.print(sb.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public static void main(String[] args) 
	{
		String[] items = "    A    B    ".split(" ");
		for (String item : items) {
			System.out.println("item=" + item);
		}
	}
}
