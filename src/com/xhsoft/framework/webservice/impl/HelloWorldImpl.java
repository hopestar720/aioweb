package com.xhsoft.framework.webservice.impl;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xhsoft.framework.webservice.HelloWorld;

@WebService
public class HelloWorldImpl implements HelloWorld {

private static final Log logger = LogFactory.getLog(HelloWorldImpl.class);
	
	public String sayHello(String name){
		logger.info("hello:"+name);
		return name;
	}
}
