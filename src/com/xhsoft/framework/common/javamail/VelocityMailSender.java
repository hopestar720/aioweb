package com.xhsoft.framework.common.javamail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

public class VelocityMailSender {
	
	private JavaMailSender mailSender;
	private VelocityConfigurer velocityConfigurer;
	
	private String getMailText(){
		return "";
	}

}
