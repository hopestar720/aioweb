package com.xhsoft.framework.common.javamail.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.xhsoft.framework.common.javamail.model.Contact;
import com.xhsoft.framework.common.javamail.model.MailInfo;
import com.xhsoft.framework.common.javamail.model.MailSender;
import com.xhsoft.framework.common.javamail.model.MyAuthenticator;

public class MailUtil {
	
	protected final static int TYPE_HTML = 1;
	protected final static int TYPE_TEXT = 2;
	
	public static Session getSession(boolean...isDebug){
		Properties props = new Properties();
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
		if(inStream == null){
			return null;
		}
		try {
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String isAuth = props.getProperty("mail.smtp.auth");
		Session session = null;
		try {
			if("true".equals(isAuth)){
				MyAuthenticator auth = new MyAuthenticator("ljw101952@126.com", "netease720.");
				session = Session.getDefaultInstance(props, auth);
			}else{
				session = Session.getDefaultInstance(props);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isDebug.length>0){
			session.setDebug(isDebug[0]);
		}
		return session;
	}
	
	public static Properties setProps(Map<String,String> map){
		Properties props = new Properties();
		for(String key : map.keySet()){
			props.put(key, map.get(key));
		}
		return props;
	}
	
	public static void sendMail(MailSender sender,MailInfo mailInfo,Session session ,List<Contact> lsTo,List<Contact> lsCc,List<Contact> lsBcc){
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(sender.getAddress()));
			//msg.setReplyTo(new InternetAddress(sender.getAddress()));
			if(lsTo == null){
				return;
			}
			//设置收件人地址
			msg.setRecipients(RecipientType.TO,getAddresses(lsTo));
			if(lsCc != null){
				//设置收件人抄送地址
				msg.setRecipients(RecipientType.CC, getAddresses(lsCc));
			}
			
			if(lsBcc != null){
				//设置收件人暗送地址
				msg.setRecipients(RecipientType.BCC, getAddresses(lsBcc));
			}
			msg.setSubject(mailInfo.getSubject());
			
			int mailType = TYPE_TEXT;
			if(mailInfo.getMailType() != null){
				mailType = mailInfo.getMailType();
			}
			switch(mailType){
			case TYPE_HTML:
				msg.setContent(getMultipart(mailInfo));
				break;
			case TYPE_TEXT:
				msg.setText(mailInfo.getContent());
				break;
				default:
					break;
			}
			
			msg.setSentDate(new Date());
			
			Transport trans = session.getTransport();
			try {
				trans.connect();
				trans.sendMessage(msg,getAddresses(lsTo));
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				trans.close();
			}
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void storeMail(){
		
	}
	
	protected static Multipart getMultipart(MailInfo mailInfo){
		Multipart multipart = new MimeMultipart();
		BodyPart html = new MimeBodyPart();
		try {
			html.setContent(mailInfo.getContent(),"text/html; charset=utf-8");
			multipart.addBodyPart(html);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return multipart;
	}
	
	public static Address[] getAddresses(List<Contact> lsContact){
		Address []address = new InternetAddress[lsContact.size()];
		for(int i = 0;i<lsContact.size();i++){
			try {
				address[i] = new InternetAddress(lsContact.get(i).getEmail());
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	
	

}
