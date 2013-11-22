/*
 * $RCSfile: BaseRuntimeException,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.exception;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title:BaseRuntimeException</p>
 * <p>Description:运行时类型异常基础类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class BaseRuntimeException extends 
			 RuntimeException implements MesBizException 
{
	
	private static final long serialVersionUID = 5806782400259918073L;
	private String code;
	private String[] params;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
	
	public BaseRuntimeException(){
		 super();
	}
	
	public BaseRuntimeException(String message,Exception e){
		super(message,e);
	}
	
	public BaseRuntimeException(String message){
		super(message);
	}
	
	public BaseRuntimeException(Exception e){
		super(e);
	}
	
	/**
	 * <p>Description:BaseRuntimeException</p>
	 * @return code
	 * @return params
	 * @author wenzhi
	 * @version 1.0
	 */
	public BaseRuntimeException(String code,String[] params)
	{
		super(code);
		this.setCode(code);
		this.setParams(params);
	}
	
	/**
	 * <p>Description:getMessage</p>
	 * @return String
	 * @author wenzhi
	 * @version 1.0
	 */
    public String getMessage()
    {
        if(code==null||code.length()==0){
            return super.getMessage();
        }
        String i18n = "";
        if(StringUtils.isNotEmpty(i18n)){
            return i18n;
        }
        String paramsStr="NA";
        if(params!=null){
            paramsStr=StringUtils.join(params,",");
        }
        String codeMessage="code:"+code+";parameters:"+paramsStr;
        return codeMessage;
    }
}
