package com.xhsoft.framework.common.javamail;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerMailSender {

	private static final String ENCODING = "utf-8";
	private JavaMailSender mailSender;
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	private void sendMail(){
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(msg,true,ENCODING);
			helper.setFrom("");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	private String getMailText(Object model,String ftlName){
		String content = "";
		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ftlName,ENCODING);
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}
