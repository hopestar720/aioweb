package com.xhsoft.framework.common.chart.fusionChart.model.set;

public class PieData extends Data{
	
	private String borderColor;
	private String isSliced;
	
	//Pie2D
	private String alpha;
	private String dashed;
	private String borderAlpha;
	
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getIsSliced() {
		return isSliced;
	}
	public void setIsSliced(String isSliced) {
		this.isSliced = isSliced;
	}
	public String getAlpha() {
		return alpha;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	public String getDashed() {
		return dashed;
	}
	public void setDashed(String dashed) {
		this.dashed = dashed;
	}
	public String getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(String borderAlpha) {
		this.borderAlpha = borderAlpha;
	}

}
