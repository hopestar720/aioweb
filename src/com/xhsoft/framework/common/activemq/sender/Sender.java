package com.xhsoft.framework.common.activemq.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

public class Sender {
	
	private JmsTemplate jmsTemplate;
	private Destination destination;
	
	public void recive(){
		while(true){
			try {
				TextMessage txtMessage = (TextMessage)jmsTemplate.receive(destination);
				if(txtMessage != null){
					System.out.println("ActiveMQ recive message :"+txtMessage.getText());
				}else{
					break;
				}
			} catch (JmsException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
