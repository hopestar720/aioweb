/*
 * $RCSfile: Constant,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.constant;

/**
 * <p>Title:Constant</p>
 * <p>Description:song项目中所有的常量信息.</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class Constant
{

	/** 分页大小*/
	public static final int PAGE_SIZE = 20;
	/** 页面domain主键参数名*/
	public static final String PARAM_PK = "sid";
	/** session username **/
	public final static String USERNAME_KEY = "username";
	public final static Long SUPER_ADMIN_ID = 1L;
	/** struts mapping **/
	public final static String SUCCESS_KEY = "success";
	public final static String PWD_KEY = "toUpdate";
	public final static String FAILURE_KEY = "failure";
	public final static String LOGOUT_KEY = "logout";
	public final static String INIT_KEY = "init";
	public final static String INDEX_KEY = "index";
	public final static String LIST_KEY = "list";
	public final static String ADD_KEY = "add";
	public final static String EDIT_KEY = "edit";
	public final static String INPUT_KEY = "input";

	/**查询条件**/
	//按降序排列
	public final static String QUREY_DESC = "_desc";
	//按升序排列
	public final static String QUERY_ASC = "_asc";
	//模糊查询
	public final static String QUERY_LIKE = "_like";
	/**非空查询*/
	public final static String QUERY_NONULL = "_nonull";
	/**不等于查询*/
	public final static String QUERY_NOEQ = "_noeq";
	/**大于等于查询*/
	public final static String QUERY_GTEQ = "_gteq";
	/**小于等于查询*/
	public final static String QUERY_LSEQ = "_lseq";
	/**In*/
	public final static String QUERY_IN = "_in";
	/**MinVal*/
	public final static String QUERY_MINVAL = "_minval";
	/**MaxVal*/
	public final static String QUERY_MAXVAL = "_maxval";
	/**OrFirst*/
	public final static String QUERY_ORFIR = "_orfir";
	/**OrSecond*/
	public final static String QUERY_ORSEC = "_orsce";
	/**分组*/
	public final static String GROUP_FIELD = "_group";
	/**多排序字段间的连接符  先按该字符串前面的进行排序再按它后面的进行排序*/
	public final static String ORDER_JOIN = "_join_";
	/**_isdate*/
	public final static String QUERY_ISDATE = "_isdate";
}
