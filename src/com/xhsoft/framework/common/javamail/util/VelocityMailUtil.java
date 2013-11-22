package com.xhsoft.framework.common.javamail.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.xhsoft.framework.common.javamail.model.MailInfo;


public class VelocityMailUtil {
	
	/** mailSender */
	private JavaMailSender mailSender;
	/** velocityEngine */
	private VelocityEngine velocityEngine;
	
	public void sendMail(List<String> to,List<String> cc,MailInfo mailInfo,String template){
		sendTemplateMail(to,cc,mailInfo,template);
	}
	
	private void sendTemplateMail(final List<String> to, final List<String> cc,final MailInfo mailInfo,final String template){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messager = new MimeMessageHelper(mimeMessage,true);
				//messager.setFrom(mailSender.);
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
				Map<String,Object> model = new HashMap<String,Object>();
				model.put("mailInfo", mailInfo);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model );
				messager.setText(text,true);
				if(!isEmpty(mailInfo.getInner())){
					String inners[] = mailInfo.getInner().split(";");
					for(int i = 0;i<inners.length;i++){
						messager.addInline(Integer.toString(i), new FileSystemResource(new File(inners[i].toString())));
					}
				}
				if(!isEmpty(mailInfo.getAttachments())){
					String attachments[] = mailInfo.getAttachments().split(";");
					for(int i = 0;i<attachments.length;i++){
						String []array = attachments[i].toString().split("/");
						messager.addAttachment(array[array.length-1].toString(), new FileSystemResource(new File(attachments[i].toString())));
					}
				}
			}
		};
		try {
			this.mailSender.send(preparator);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}
