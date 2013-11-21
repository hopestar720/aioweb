package com.xhsoft.framework.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.xhsoft.framework.base.entity.AbstractEntity;

public class BaseAction<T  extends AbstractEntity> extends ActionSupport implements ModelDriven<T>,
		Preparable {
	
	private static final Log logger = LogFactory.getLog(BaseAction.class);

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public T getModel() {
		return null;
	}

}
