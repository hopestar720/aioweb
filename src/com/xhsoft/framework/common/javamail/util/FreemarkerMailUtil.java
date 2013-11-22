package com.xhsoft.framework.common.javamail.util;

import java.io.File;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xhsoft.framework.common.javamail.model.MailInfo;

import freemarker.template.Template;

public class FreemarkerMailUtil {
	
	/** mailSender */
	private JavaMailSender mailSender;
	/** freemarkerConfigurer */
	private FreeMarkerConfigurer freemarkerConfigurer;
	
	public void sendMail(List<String> to,List<String> cc,MailInfo mailInfo,String templateName){
		try {
			sendTemplateMail(to,cc,mailInfo,templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendTemplateMail(final List<String> to,final List<String> cc,final MailInfo mailInfo,final String templateName)throws Exception{
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messager = new MimeMessageHelper(mimeMessage);
				InternetAddress []toAddresses = null;
				if (to != null && to.size() > 0) {
					toAddresses = new InternetAddress[to.size()];
					for (int i = 0; i < to.size(); i++) {
						toAddresses[i] = new InternetAddress(to.get(i));
					}
					messager.setTo(toAddresses);
				}
				
				InternetAddress []ccAddresses = null;
				if (cc != null && cc.size() > 0) {
					ccAddresses = new InternetAddress[cc.size()];
					for (int i = 0; i < cc.size(); i++) {
						ccAddresses[i] = new InternetAddress(cc.get(i));
					}
					messager.setCc(ccAddresses);
				}
				
				messager.setSubject(mailInfo.getSubject());
				
				Template template = freemarkerConfigurer.getConfiguration().getTemplate(templateName);
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailInfo);
				messager.setText(text);
				
				String strInner = mailInfo.getInner();
				if(!isEmpty(strInner)){
					String []inners = strInner.split(";");
					for(int i = 0;i<inners.length;i++){
						messager.addInline(Integer.toString(i), new FileSystemResource(new File(inners[i].toString())));
					}
				}
				
				String strAttachments = mailInfo.getAttachments();
				if(!isEmpty(strAttachments)){
					String attachments[] = strAttachments.split(";");
					for(int i = 0;i<attachments.length;i++){
						String []array = attachments[i].toString().split("/");
						messager.addAttachment(array[array.length-1].toString(), new FileSystemResource(new File(attachments[i].toString())));
					}
				}
			}
		};
		mailSender.send(preparator);
	}
	
	private boolean isEmpty(String... args){
		if(args != null && args.length > 0){
            for(String s : args)
            {
                if(s == null || s.trim().length() == 0 ){
                    return true;
                }
            }
            
            return false;
        }
        
        return true;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @param freemarkerConfigurer the freemarkerConfigurer to set
	 */
	public void setFreemarkerConfigurer(FreeMarkerConfigurer freemarkerConfigurer) {
		this.freemarkerConfigurer = freemarkerConfigurer;
	}
}
