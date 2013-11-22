package com.xhsoft.framework.common.chart.fusionChart.model;

import java.util.List;

import com.xhsoft.framework.common.chart.fusionChart.model.chart.Chart;
import com.xhsoft.framework.common.chart.fusionChart.model.line.TrendLine;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Data;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Styles;

public class SingleChart<T extends Chart,K extends Data> {
	
	private T chart;
	private List<K> data;
	private TrendLine trendlines;
	private Styles styles;
	
	public T getChart() {
		return chart;
	}
	public void setChart(T chart) {
		this.chart = chart;
	}
	public List<K> getData() {
		return data;
	}
	public void setData(List<K> data) {
		this.data = data;
	}
	public TrendLine getTrendlines() {
		return trendlines;
	}
	public void setTrendlines(TrendLine trendlines) {
		this.trendlines = trendlines;
	}
	public Styles getStyles() {
		return styles;
	}
	public void setStyles(Styles styles) {
		this.styles = styles;
	}
	
	
}
