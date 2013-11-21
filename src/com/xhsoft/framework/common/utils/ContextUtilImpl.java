package com.xhsoft.framework.common.utils;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
/**
 * WebContext工具类实现类（STRUTS2实现方式）
 * @author hopestar720@126.com
 * @since 2013年11月21日
 */
public class ContextUtilImpl extends ContextUtil {

	public HttpServletRequest requestHook() {
		// 调用父类钩子传入Struts2的request实例(可返回其他框架的该实例)
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse responseHook() {
		// 调用父类钩子传入Struts2的response实例(可返回其他框架的该实例)
		return ServletActionContext.getResponse();
	}

	public ServletContext contextHook() {
		// 调用父类钩子传入Struts2的context实例(可返回其他框架的该实例)
		return ServletActionContext.getServletContext();
	}

	public String JSONHook(Object object) {
		// 调用父类钩子传入Struts2的JSONUtil工具返回JSON字符(可使用其他框架的JSON工具返回结果)
		try {
			return JSONUtil.serialize(object);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void setLocale(Locale locale) {
		// 重载设置本地语言方法实现多国语言
		ServletActionContext.getContext().setLocale(locale);
	}

}
