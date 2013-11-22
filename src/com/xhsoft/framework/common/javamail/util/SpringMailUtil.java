package com.xhsoft.framework.common.javamail.util;

import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.xhsoft.framework.common.javamail.model.MailInfo;

public class SpringMailUtil{
	
	private JavaMailSender mailSender;
	private MailInfo mailInfo;
	
	public void sendMail(List<String> lsTo,List<String> lsCc,MailInfo mailInfo){
		try {
			sendJavaMail(lsTo,lsCc,mailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendJavaMail(final List<String> lsTo,final List<String> lsCc,final MailInfo mailInfo)throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			public void prepare(MimeMessage mm) throws Exception {
				Address[] toAddresses = null;
				Address[] ccAddresses = null;
				if(lsTo!=null&&lsTo.size()>0){
					toAddresses = new InternetAddress[lsTo.size()];
					mm.setRecipients(Message.RecipientType.TO, toAddresses);
				}
				if(lsCc!=null&&lsCc.size()>0){
					ccAddresses = new InternetAddress[lsCc.size()];
					mm.setRecipients(Message.RecipientType.CC, ccAddresses);
				}
				mm.setSubject(mailInfo.getSubject(), "utf-8");
				mm.setText(mailInfo.getContent());
			}
			
		};
		this.mailSender.send(preparator);
	}


	public void setMailInfo(MailInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	public MailInfo getMailInfo() {
		return mailInfo;
	}

}
