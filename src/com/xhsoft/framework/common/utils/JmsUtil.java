package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsUtil {

	private static JmsUtil instance = null;
	private static String brokerUrl = null;
	private static ConnectionFactory connectionFactory = null;
	private static Connection connection = null;

	private JmsUtil() {
		Properties p = new Properties();
		InputStream in = JmsUtil.class.getResourceAsStream("/jms.properties");
		try {
			p.load(in);
			String ip = p.getProperty("message.ip");
			String port = p.getProperty("message.port");
			brokerUrl = "tcp://" + ip + ":" + port;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JmsUtil(String ip,String port){
		brokerUrl = "tcp://" + ip + ":" + port;
	}
	
	public synchronized static JmsUtil getInstance(){
		if(instance == null){
			instance = new JmsUtil();
		}
		return instance;
	}
	
	public synchronized static JmsUtil getInstance(String ip,String port){
		if(instance == null){
			instance = new JmsUtil(ip,port);
		}
		return instance;
	}
	
	public static void destory(){
		
	}
	
	public ConnectionFactory getConnectionFactory(){
		if(connectionFactory == null){
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
		}
		return connectionFactory;
	}

	
	public Connection getConnection() {
		if (connection == null) {
			try {
				connection = getConnectionFactory().createConnection();
				connection.start();
			} catch (Exception e) {
				throw new RuntimeException(e.getLocalizedMessage());
			}

		}
		return connection;

	}

	public void pushMessage(String identyCode, String message) {
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			connection = getConnection();
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(identyCode);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMessage = session.createTextMessage(message);
			producer.send(textMessage);
			// session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				try {
					producer.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			try {
				if (null != session)
					session.close();
			} catch (Throwable ignore) {
			}
		}
	}

	public String popMessage(String identyCode) {

		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		String message = "";
		try {
			connection = getConnection();
			// connection.start();
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(identyCode);
			consumer = session.createConsumer(destination);
			for (int m = 0; m < 10; m++) {
				TextMessage arg0 = (TextMessage) consumer.receive(1l);
				if (arg0 != null) {
					String ss = arg0.getText();
					message = message + ss + "<br>";
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != consumer)
					consumer.close();
			} catch (Throwable ignore) {
			}

			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}

		}
		return message;
	}
	
}
