package com.xhsoft.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.xhsoft.framework.security.exception.MethodErrorException;
import com.xhsoft.framework.security.exception.ValidateCodeException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	private String timeoutUrl;

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		if (ex instanceof UsernameNotFoundException) {
			// 账号错误
			response.getWriter().print(ex.getMessage());
		} else if (ex instanceof BadCredentialsException) {
			// 密码错误
			response.getWriter().print("用户/密码错误,请重新输入!");
		} else if (ex instanceof ValidateCodeException) {
			// 验证码错误
			response.getWriter().print(ex.getMessage());
		} else if (ex instanceof MethodErrorException) {
			// 请求方法错误
			response.getWriter().print(ex.getMessage());
		} else if (ex instanceof SessionAuthenticationException) {
			// 登陆超时错误
			response.sendRedirect(request.getContextPath()
					+ "/static/html/error/timeout.html");
		} else {
			// 未知异常错误
			response.getWriter().print("未知异常错误,请联系相关技术人员!");
		}
	}

	public String getTimeoutUrl() {
		return timeoutUrl;
	}

	public void setTimeoutUrl(String timeoutUrl) {
		this.timeoutUrl = timeoutUrl;
	}

}