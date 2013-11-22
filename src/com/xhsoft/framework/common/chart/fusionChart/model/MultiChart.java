package com.xhsoft.framework.common.chart.fusionChart.model;

import java.util.List;

import com.xhsoft.framework.common.chart.fusionChart.model.chart.Chart;
import com.xhsoft.framework.common.chart.fusionChart.model.line.TrendLine;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Categories;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Data;
import com.xhsoft.framework.common.chart.fusionChart.model.set.DataSet;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Styles;

public class MultiChart<T extends Chart,K extends Data> {
	
	private T chart;
	private List<Categories> categories;
	private List<DataSet<K>> dataset;
	private TrendLine trendlines;
	private Styles styles;
	
	public T getChart() {
		return chart;
	}
	public void setChart(T chart) {
		this.chart = chart;
	}
	public List<Categories> getCategories() {
		return categories;
	}
	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}
	public List<DataSet<K>> getDataset() {
		return dataset;
	}
	public void setDataset(List<DataSet<K>> dataset) {
		this.dataset = dataset;
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
