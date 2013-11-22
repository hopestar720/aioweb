package com.xhsoft.framework.security.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.xhsoft.framework.common.utils.Utils;
import com.xhsoft.framework.uam.entity.User;
import com.xhsoft.framework.uam.service.IUserService;


public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Log logger = LogFactory.getLog(LoginSuccessHandler.class);

	private IUserService userService;

	private String indexUrl; // 登陆成功跳转路径

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		User user = (User) Utils.SecurityUtil.getUserDetails();
		if (logger.isDebugEnabled())
			logger.debug("信息: ["
					+ user.getUsername()
					+ "] "
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()) + " 登录成功");
		response.setContentType("text/plain");
		response.getWriter().print("success");
	}
}
