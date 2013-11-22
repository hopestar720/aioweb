/*
 * Copyright (C) 2013 xhsoft.com, Inc. All rights reserved.
 *
 * This software is the proprietary information of xhsoft, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.chart.fusionChart.model.set;

/**
 * <p>Title: BarData.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @since 2013-3-24
 */
public class BarData extends Data{
	
	private String alpha;
	private String showValue;
	private String dashed;
	
	public String getAlpha() {
		return alpha;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	public String getShowValue() {
		return showValue;
	}
	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}
	public String getDashed() {
		return dashed;
	}
	public void setDashed(String dashed) {
		this.dashed = dashed;
	}
	
}
