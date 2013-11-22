/*
 * $RCSfile: CookieUtils,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:CookieUtils</p>
 * <p>Description:CookieUtils</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class CookieUtils 
{
	private CookieUtils() {
	}

	/**
	 * <p>Description:获取指定名称的Cookie</p>
	 * @param request
	 * @param name
	 * @return Cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) 
	{
		Cookie cookie = new Cookie("", "");

		Cookie[] cookies = request.getCookies();

		if ((cookies == null) || (name == null) || (name.length() == 0)) {
			return cookie;
		}

		for (int i = 0; i < cookies.length; i++) 
		{
			if (!cookies[i].getName().equals(name)) {
				continue;
			}

			cookie = cookies[i];

			break;
		}

		return cookie;
	}

	/**
	 * @param request
	 * @param name
	 * @return String
	 */
	public static String getCookieValue(HttpServletRequest request, String name) 
	{
		try {
			return URLDecoder.decode(getCookie(request, name).getValue(), "utf-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>Description:删除指定的Cookie</p>
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletResponse response, Cookie cookie) 
	{
		if (cookie != null) {
			cookie.setMaxAge(0);
		
			cookie.setPath("/");
//			cookie.setDomain("h365.cc");
			response.addCookie(cookie);
		}
	}

	/**
	 *<p>Description: 删除指定名称的Cookie</p>
	 * @param response
	 * @param name
	 */
	public static void deleteCookie(HttpServletResponse response, String name) 
	{
		Cookie cookie = new Cookie(name, "");
		
		cookie.setPath("/");
//		cookie.setDomain("h365.cc");
		deleteCookie(response, cookie);
	}

	/**
	 * <p>Description:设置无限期的Cookie</p>
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) 
	{
		setCookie(response, name, value, Integer.MAX_VALUE);
	}

	/**
	 *<p>Description: 设定指定有效的Cookie</p>
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) 
	{
		if (value == null) {
			value = "";
		}

		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));

			cookie.setMaxAge(maxAge);
			cookie.setPath("/");
//			cookie.setDomain("h365.cc");
			response.addCookie(cookie);
		} catch (Exception e) {
		}
	}
}
