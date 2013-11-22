package com.xhsoft.framework.security.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器 - 去除页面参数字符串两端的空格
 * @author hopestar720@126.com
 * @since 2013年11月22日
 */
@SuppressWarnings("serial")
public class TrimInterceptor implements Interceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> parameters = invocation.getInvocationContext()
				.getParameters();
		// 遍历参数
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (int i = 0, len = values.length; i < len; i++) {
					String vl = values[i];
					if (null != vl && !"".equals(vl))
						values[i] = vl.trim();
				}
				parameters.put(key, values);
			}
		}
		return invocation.invoke();
	}

	public void destroy() {
	}

	public void init() {
	}

}