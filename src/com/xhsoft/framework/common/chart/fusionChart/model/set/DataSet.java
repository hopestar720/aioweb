package com.xhsoft.framework.common.chart.fusionChart.model.set;

import java.util.List;

public class DataSet<T extends Data> {
	
	private String seriesname;
	private List<T> data;
	
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}
