package com.xhsoft.framework.common.chart.fusionChart.utils;

import com.xhsoft.framework.common.chart.fusionChart.model.chart.ColumnChart;
import com.xhsoft.framework.common.chart.fusionChart.model.set.ColumnData;

public class ColumnChartUtil extends FusionChartUtil<ColumnChart, ColumnData>{
	
	private String xAxisName;
	private String yAxisName;
	
	public void setChart() throws Exception{
		super.setChart();
		super.getChart().setxAxisName(xAxisName);
		super.getChart().setyAxisName(yAxisName);
	}
	
	public String getxAxisName() {
		return xAxisName;
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	public String getyAxisName() {
		return yAxisName;
	}
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	
}
