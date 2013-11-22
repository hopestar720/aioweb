package com.xhsoft.framework.security.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xhsoft.framework.common.utils.Utils;

/**
 * 
 * @author shadow
 * @token检测(防止表单重复提交)
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
@SuppressWarnings("serial")
public class TokenInterceptor extends AbstractInterceptor {

	public final static String TOKEN_NAME = "token";

	private List<String> beforeMethods;
	private List<String> afterMethods;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Assert.notNull(beforeMethods);
		Assert.notNull(afterMethods);
		String method = actionInvocation.getProxy().getMethod();
		for (String before : beforeMethods) {
			if (before.startsWith(method)) {
				// 过滤方法遇到xx方法开头的就生成出一个TOKEN令牌保存到session中
				Utils.ContextUtil.getSession().setAttribute(TOKEN_NAME,
						Utils.CommonUtil.getUUID());
				return actionInvocation.invoke();
			}
		}
		for (String after : afterMethods) {
			if (after.startsWith(method)) {
				// 过滤方法遇到xx开头的方法就获取session的TOKEN令牌,比较页面获取过来的TOKEN值
				String token = (String) Utils.ContextUtil.getSession()
						.getAttribute(TOKEN_NAME);
				Map<String, Object> parameters = actionInvocation
						.getInvocationContext().getParameters();
				for (String key : parameters.keySet()) {
					if (key.equals(TOKEN_NAME)) {
						Object value = parameters.get(key);
						if (value instanceof String[]) {
							String token_value = ((String[]) value)[0];
							if (token_value.equals(token)) {
								Utils.ContextUtil.getSession()
										.setAttribute(TOKEN_NAME, null);
								return actionInvocation.invoke();
							}
						}
					}
				}
				return TOKEN_NAME;
			}
		}
		return actionInvocation.invoke();
	}

	public void setBeforeMethods(List<String> beforeMethods) {
		this.beforeMethods = beforeMethods;
	}

	public void setAfterMethods(List<String> afterMethods) {
		this.afterMethods = afterMethods;
	}

}
