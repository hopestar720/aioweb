package com.xhsoft.framework.security.interceptor;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器JS脚本安全
 * @author hopestar720@126.com
 * @since 2013年11月22日
 */
@SuppressWarnings("serial")
public class SecurityInterceptor implements Interceptor {

	private static Pattern scriptPattern = Pattern.compile("script",
			Pattern.CASE_INSENSITIVE);

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> parameters = invocation.getInvocationContext()
				.getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				// 遍历参数
				String[] values = (String[]) value;
				for (int i = 0, len = values.length; i < len; i++) {
					// 过滤参数值带script字符
					values[i] = scriptPattern.matcher(values[i]).replaceAll(
							"&#x73;cript");
					values[i] = StringUtils.replace(values[i], "<", "&lt;");
					values[i] = StringUtils.replace(values[i], ">", "&gt;");
					values[i] = values[i].trim(); // 去除字符头尾空格
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