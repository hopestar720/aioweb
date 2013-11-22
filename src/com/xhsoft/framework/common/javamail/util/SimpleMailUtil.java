package com.xhsoft.framework.common.javamail.util;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.xhsoft.framework.common.javamail.model.MailInfo;
import com.xhsoft.framework.common.javamail.model.MailServer;

public class SimpleMailUtil {
	
	protected final static int TYPE_HTML = 1;
	protected final static int TYPE_TEXT = 2;
	
	/** mailServer */
	private MailServer mailServer;
	
	public void sendMail(List<String> lsTo,List<String> lsCc,MailInfo mailInfo,int mailType){
		if(mailType == TYPE_TEXT){
			try {
				sendTextMail(lsTo,lsCc,mailInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(mailType == TYPE_HTML){
			try {
				sendHtmlMail(lsTo,lsCc,mailInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void sendTextMail(final List<String> lsTo, final List<String> lsCc,MailInfo mailInfo)throws Exception {

		if (!isReady(mailInfo)) {
			return;
		}
		MailAuthenticator authenticator = null;
		Properties props = mailServer.getProperties();

		if (mailServer.getIsValidate()==0) {
			authenticator = new MailAuthenticator(mailServer.getUsername(),
					mailServer.getPassword());
		}
		Session mailSession = Session.getDefaultInstance(props, authenticator);
		Message mailMessage = new MimeMessage(mailSession);
			Address fromAddress = new InternetAddress(mailServer.getFromAddress());
			mailMessage.setFrom(fromAddress);
			
			Address[] toAddresses = null;
			if (lsTo != null && lsTo.size() > 0) {
				toAddresses = new InternetAddress[lsTo.size()];
				for (int i = 0; i < lsTo.size(); i++) {
					toAddresses[i] = new InternetAddress(lsTo.get(i));
				}
				mailMessage.setRecipients(Message.RecipientType.TO, toAddresses);
			}

			Address[] ccAddresses = null;
			if (lsCc != null && lsCc.size() > 0) {
				ccAddresses = new Address[lsCc.size()];
				for (int i = 0; i < lsCc.size(); i++) {
					ccAddresses[i] = new InternetAddress(lsCc.get(i));
				}
				mailMessage.setRecipients(Message.RecipientType.CC, ccAddresses);
			}

			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());

			String context = mailInfo.getContent();
			mailMessage.setText(context);

			Transport.send(mailMessage);

	}

	private void sendHtmlMail(final List<String> lsTo, final List<String> lsCc,MailInfo mailInfo) throws Exception{

		if (!isReady(mailInfo)) {
			return;
		}
		MailAuthenticator authenticator = null;
		Properties props = mailServer.getProperties();

		if (mailServer.getIsValidate()==0) {
			authenticator = new MailAuthenticator(mailServer.getUsername(),
					mailServer.getPassword());
		}
		Session mailSession = Session.getDefaultInstance(props, authenticator);
		Message mailMessage = new MimeMessage(mailSession);
			Address fromAddress = new InternetAddress(mailServer.getFromAddress());
			mailMessage.setFrom(fromAddress);

			Address[] toAddresses = null;
			if (lsTo != null && lsTo.size() > 0) {
				toAddresses = new InternetAddress[lsTo.size()];
				for (int i = 0; i < lsTo.size(); i++) {
					toAddresses[i] = new InternetAddress(lsTo.get(i));
				}
				mailMessage.setRecipients(Message.RecipientType.TO, toAddresses);
			}

			Address[] ccAddresses = null;
			if (lsCc != null && lsCc.size() > 0) {
				ccAddresses = new Address[lsCc.size()];
				for (int i = 0; i < lsCc.size(); i++) {
					ccAddresses[i] = new InternetAddress(lsCc.get(i));
				}
				mailMessage.setRecipients(Message.RecipientType.CC, ccAddresses);
			}

			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());

			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);

			Transport.send(mailMessage);

	}

	private boolean isReady(MailInfo mainInfo) {

		String host = mailServer.getHost();
		String port = mailServer.getPort();
		String from = mailServer.getFromAddress();

		return isEmpty(host,port,from);

	}
	
	private boolean isEmpty(String ... args)
    {
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

}
