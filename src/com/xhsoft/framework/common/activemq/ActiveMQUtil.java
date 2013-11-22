package com.xhsoft.framework.common.activemq;

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

public class ActiveMQUtil {
	
	private static ActiveMQUtil instance;
	
	
	// ConnectionFactory ：连接工厂，JMS 用它创建连接
    private ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
    private Connection connection = null;
    // Session： 一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination;
    // MessageProducer：消息发送者
    private MessageProducer producer;
    // 消费者，消息接收者
    private MessageConsumer consumer;
    
    private ActiveMQUtil(){
    	connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
    	try {
    		// 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    
    public synchronized static ActiveMQUtil getInstance(){
    	if(instance == null){
    		instance = new ActiveMQUtil();
    	}
    	return instance;
    }
    
    public void sendMessage(String message){
    	try {
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
		    TextMessage txtMessage = session.createTextMessage(message);
		    // 发送消息到目的地方
		    System.out.println("发送消息：" + "ActiveMq 发送的消息-" + message);
			   
			producer.send(txtMessage);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    
    public void receviveMessage(){
    	try {
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			while (true) {
			    //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
			    TextMessage message = (TextMessage) consumer.receive(100000);
			    if (null != message) {
			        System.out.println("收到消息" + message.getText());
			    } else {
			        break;
			    }
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }

}
