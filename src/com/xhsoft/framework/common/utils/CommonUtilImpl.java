package com.xhsoft.framework.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 基本方法工具类实现类
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
public class CommonUtilImpl implements CommonUtil {

	public String getClientIP() {
		return overshot(Utils.ContextUtil.getRequest());
	}

	public String getClientIP(HttpServletRequest request) {
		return overshot(request);
	}

	/**
	 * 获取客户IP地址
	 * 
	 * @param request
	 * @return String
	 */
	private String overshot(HttpServletRequest request) {
		String ip = null;
		ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			// = 15
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	public Map<String, Object> toMap(Object[] keys, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == keys || null == values || keys.length <= 0
				|| values.length <= 0 || keys.length != values.length)
			return map;
		for (int i = 0, len = values.length; i < len; i++)
			map.put(keys[i].toString(), values[i]);
		return map;
	}

	public Map<String, Object> toMap(Map<String, Object> map, Object[] keys,
			Object[] values) {
		if (null == keys || null == values || keys.length <= 0
				|| values.length <= 0 || keys.length != values.length)
			return map;
		for (int i = 0, len = values.length; i < len; i++)
			map.put(keys[i].toString(), values[i]);
		return map;
	}

	public Map<String, Object> toMap(Map<String, Object> map, Object key,
			Object value) {
		if (null == key || null == value || "".equals(key))
			return map;
		map.put(key.toString(), value);
		return map;
	}

	public Map<String, Object> toMap(Object key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == key || null == value || "".equals(key))
			return map;
		map.put(key.toString(), value);
		return map;
	}

	public String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String toEncoding(String string, String oldEncode, String newEncode) {
		if (null == oldEncode || "".equals(oldEncode))
			oldEncode = "ISO8859_1";
		try {
			return new String(string.getBytes(oldEncode), newEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

}
