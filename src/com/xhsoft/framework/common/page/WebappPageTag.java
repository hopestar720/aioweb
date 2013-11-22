/*
 * $RCSfile: WebappPageTag,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title:WebappPageTag</p>
 * <p>Description:组织机构实体类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class WebappPageTag extends BodyTagSupport 
{
	
	private static final long serialVersionUID = -4247571474868206610L;
	private static final Log logger = LogFactory.getLog(PageTag.class);

	/** 页面跳转链接显示个数 */
	private static int MAX_SHOW_NUMBER = 6; // summation of preList + floList
	/** 下拉框列表 */
	private static String INIT_PAGE_SIZE_LIST = "10,20,50,100,200";
	/** 每页显示下拉框项目列表 */
	private String pageSizeList = null;
	
	/**要设置的 pageSizeList。*/
	public void setPageSizeList(String pageSizeList) {
		this.pageSizeList = pageSizeList;
	}

	/** AJAX操作的目标DIV名称 */
	private String targets;

	public void setTargets(String targets) {
		this.targets = targets;
	}
	/***链接地址**/
	private String link;

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * <p>Description: 标签核心方法</p>
	 * @return int
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException
	{
		/**获取request*/
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Page page = (Page) request.getAttribute("page");

		String pageURL=(String)request.getAttribute("pageURL");
		if(pageURL.indexOf("?")!=-1){
			link = pageURL + "&";
		}else{
			link = pageURL + "?";
		}
		if(link.indexOf("qm.pn")!=-1){
			link = link.substring(0, link.indexOf("qm.pn"));
		}
		
		/** 从request中获取需要的值，前提要求Action方法中必须在request中设置相应的属性值*/
		int pageNo = page.getPageNo();
		@SuppressWarnings("unused")
		int limit = page.getLimit();
		int totalPages = page.getTotalPages();
		JspWriter out = pageContext.getOut();
		
		try {
			out.print("<div class=\"np\">");
			printOutFront(pageNo, request);
			printOutBack(pageNo, totalPages, request);
			printOutEnd();
			@SuppressWarnings("unused")
			int pn=0;
			if(pageNo>0&&totalPages>0){
				pn=pageNo;
			}
			@SuppressWarnings("unused")
			int start=0;
			if(pageNo>0&&totalPages>0){
				start=page.getOffset() + 1;
			}
			out.print("共"+totalPages+"页</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}
	
	/**
	 * <p>Description: build up goto text area and print</p>
	 * @param request	
	 * @param pageNo 
	 * @param totalPages 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	@SuppressWarnings("unused")
	private void buildGoto(HttpServletRequest request, int pageNo,int totalPages)
			throws JspException 
	{
		try {
			JspWriter out = pageContext.getOut();
			if (StringUtils.isEmpty(pageSizeList)) {
				pageSizeList = INIT_PAGE_SIZE_LIST;
			}
			String[] pageSizes = pageSizeList.split(",");
			String rand=UUID.randomUUID().toString();
			String outString = "<input name=\"gotoText"+rand+"\" type=\"text\"  size=\"5\"  value=\""+pageNo+"\" onkeydown=\"return gotoPage(this.value,'"+pageNo+"','"+totalPages+"','"+targets+"');\">"+"<input type=\"button\" name=\"go\" value=\"GO\" class=\"button2\" onclick=\"return gotoPageDirect(dojo.byId('gotoText"+rand+"').value,'"+pageNo+"','"+totalPages+"','"+targets+"');\">";
			out.print(outString);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * <p>Description: build up selecter and print</p>
	 * @param request	
	 * @param limit 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	@SuppressWarnings("unused")
	private void buildSelecter(HttpServletRequest request, int limit)
			throws JspException 
	{
		try {
			JspWriter out = pageContext.getOut();
			if (StringUtils.isEmpty(pageSizeList)) {
				pageSizeList = INIT_PAGE_SIZE_LIST;
			}
			String[] pageSizes = pageSizeList.split(",");
			String outString = "每页<select name=\"pageSizeSelect\" onchange=\"setLimit(this.value,'"+targets+"')\">";
		
			for (int i = 0; i < pageSizes.length; i++) 
			{
				if (limit==Integer.parseInt(pageSizes[i])) {
					outString += "<option selected  value=\"" + pageSizes[i]
							+ "\">" + pageSizes[i] + "</option>";
				} else {
					outString += "<option value=\"" + pageSizes[i] + "\">"
							+ pageSizes[i] + "</option>";
				}
			}
			
			outString += "</select>";
			out.print(outString);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * <p>Description: buildRefresh</p>
	 * @param request	
	 * @param pageNo 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	@SuppressWarnings("unused")
	private void buildRefresh(HttpServletRequest request,int pageNo) throws JspException
	{
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer outString = new StringBuffer(50);
			outString.append("<a href=\"javascript:setPage('"+ pageNo +"','"+targets+"');\">");
			outString.append("</a>");
			out.print(outString.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * <p>Description: build jsp page and print out</p>
	 * @param request	
	 * @param pageNo 
	 * @param totalPages 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void buildPager(HttpServletRequest request, int pageNo,
			int totalPages) throws JspException 
	{

		logger.debug("pageNo=" + pageNo);
		logger.debug("totalPages=" + totalPages);

		Map map = getPreFloList(pageNo, totalPages);
		List preList = (List) map.get("preList");
		List floList = (List) map.get("floList");

		if (preList != null && preList.size() > 0) {
			for (int i = 0; i < preList.size(); i++) 
			{
				printOutPages(request, "pre", (String) preList.get(i));
			}
		}
		printOutPages(request, "now", String.valueOf(pageNo));
		if (floList != null && floList.size() > 0) {
			for (int i = 0; i < floList.size(); i++)
			{
				printOutPages(request, "pre", (String) floList.get(i));
			}
		}

	}

	/**
	 * <p>Description: get pre and flo list</p>
	 * @param pageNo 
	 * @param totalPages 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private Map getPreFloList(int pageNo, int totalPages) 
	{
		 /** summation of preList + floList sizes*/
		int maxShow = MAX_SHOW_NUMBER;
		List preList = new ArrayList();
		List floList = new ArrayList();
		List bakPreList = new ArrayList();
		List bakFloList = new ArrayList();
		int i = 0;
		
		for (int j = pageNo - 1; j > 0 && i < maxShow; j--, i++)
		{
			bakPreList.add(String.valueOf(j));
		}
		
		i = 0;
		for (int j = pageNo + 1; j <= totalPages && i < maxShow; j++, i++) 
		{
			bakFloList.add(String.valueOf(j));
		}
		
		int preLength;
		int floLength;
		if (bakPreList.size() >= maxShow / 2) 
		{
			if (bakFloList.size() >= maxShow / 2) {
				preLength = maxShow / 2;
				floLength = maxShow / 2;
			} else {
				floLength = bakFloList.size();
				if (bakPreList.size() >= (maxShow - floLength)) {
					preLength = maxShow - floLength;
				} else {
					preLength = bakPreList.size();
				}
			}
		} else {
			if (bakFloList.size() >= maxShow / 2) {
				preLength = bakPreList.size();
				if (bakFloList.size() >= (maxShow - preLength)) {
					floLength = maxShow - preLength;
				} else {
					floLength = bakFloList.size();
				}
			} else {
				preLength = bakPreList.size();
				floLength = bakFloList.size();
			}
		}
		
		for (int j = 0; j < floLength; j++)
		{
			floList.add(bakFloList.get(j).toString());
		}
		for (int j = preLength - 1; j >= 0; j--) 
		{
			preList.add(bakPreList.get(j).toString());
		}
		
		Map map = new HashMap();
		map.put("preList", preList);
		map.put("floList", floList);
		return map;

	}

	/**
	 * <p>Description: print info to jsp page</p>
	 * @param request	
	 * @param mothod 
	 * @param page 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	private void printOutPages(HttpServletRequest request, String mothod,
			String page) throws JspException 
	{
		if (mothod.endsWith("now")) {
			try {
				JspWriter out = pageContext.getOut();
				String outString = "&nbsp;<span><font class='currentNum'>["
						+ page + "]</font></span>";
				out.print(outString);
			} catch (Exception e) {
				throw new JspException(e);
			}
		} else {
			try {
				JspWriter out = pageContext.getOut();
				String outString = "&nbsp;" + "<a href=\"javascript:setPage('"
						+ page + "','" + targets + "');\">"
						+ "" + page + "</a>";
				out.print(outString);
			} catch (Exception e) {
				throw new JspException(e);
			}
		}
	}

	/**
	 * <p>Description:组装标签前部分</p>
	 * @param  pageNo	
	 * @param request 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	private void printOutFront(int pageNo, HttpServletRequest request)
			throws JspException 
	{

		try {
			String first = "第一页";
			String prev = "上一页";
			JspWriter out = pageContext.getOut(); 
			String outString = "";
			if (pageNo == 1) {
				outString = ""
						+ " "
						+ first
						+ "&nbsp;|&nbsp;"
						+ " "
						+ prev + "&nbsp;|&nbsp;";
			} else {
				Integer prePage = new Integer(pageNo == 1 ? pageNo : pageNo - 1);
				outString = ""
						+ "<a href=\""
						+ link
						+ "qm.pn="
						+ 1
						+ "\">"
						+ first
						+ "&nbsp;</a>|"
						+ "<a href=\""
						+ link
						+ "qm.pn="
						+ prePage
						+ "\">"
						+ prev + "&nbsp;</a>|";
			}
			out.print(outString);
		} catch (Exception e) {
			throw new JspException(e);
		}

	}

	/**
	 * <p>Description:组装标签后部分</p>
	 * @param  pageNo	
	 * @param totalPages 
	 * @param request 
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	private void printOutBack(int pageNo, int totalPages,
			HttpServletRequest request) throws JspException
	{
		try {
			Integer nextPage = new Integer(pageNo == totalPages ? totalPages : pageNo + 1);
			String next = "下一页";
			String last = "最后一页";
			JspWriter out = pageContext.getOut();
			String outString = "";
			
			if (pageNo == totalPages||totalPages==0||totalPages==1) {
				outString = " "

						+ next
						+ "&nbsp;|&nbsp;"
						+ " "
						+ last
						+ "&nbsp;|&nbsp;";
			} else {
				outString = " "
						+ "<a href=\""
						+ link
						+ "qm.pn="
						+ nextPage
						+ "\">"
						+ next
						+ "&nbsp;</a>|"
						+ "<a href=\""
						+ link
						+ "qm.pn="
						+ totalPages
						+ "\">"
						+ last
						+ "&nbsp;</a>|&nbsp;&nbsp;";
			}
			out.print(outString);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}
	
	/**
	 * <p>Description: 组装标签结束标记分</p>
	 * @return void
	 * @author wenzhi
	 * @version 1.0
	 * @exception JspException
	 */
	private void printOutEnd() throws JspException
	{
		try {
			JspWriter out = pageContext.getOut();
			String outString = "";
			out.print(outString);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}
}
