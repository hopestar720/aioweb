package com.xhsoft.framework.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SayHelloMethodJob {
	
	private static final Log logger = LogFactory.getLog(SayHelloMethodJob.class);
	
	public void sayHello(String user){
		logger.info("hello,"+user);
	}

}
