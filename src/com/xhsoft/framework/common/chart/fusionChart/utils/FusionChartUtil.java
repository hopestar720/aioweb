package com.xhsoft.framework.common.chart.fusionChart.utils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xhsoft.framework.common.chart.fusionChart.model.MultiChart;
import com.xhsoft.framework.common.chart.fusionChart.model.SingleChart;
import com.xhsoft.framework.common.chart.fusionChart.model.chart.Chart;
import com.xhsoft.framework.common.chart.fusionChart.model.line.Line;
import com.xhsoft.framework.common.chart.fusionChart.model.line.TrendLine;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Categories;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Category;
import com.xhsoft.framework.common.chart.fusionChart.model.set.Data;
import com.xhsoft.framework.common.chart.fusionChart.model.set.DataSet;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Application;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Definition;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Styles;
import com.xhsoft.framework.common.utils.JsonUtil;

public class FusionChartUtil<T extends Chart, K extends Data> {

	private String caption;
	private String subCaption;

	private boolean showTrendLine;

	private List<Categories> lsCategories;
	private List<DataSet<K>> lsMultiData;
	private List<K> singleData;
	private TrendLine trendlines;

	private T chart;
	private Styles styles;

	public void setChart() throws Exception {
		Class<T> chartClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		chart = chartClass.newInstance();
		chart.setCaption(caption);
		chart.setSubCaption(subCaption);
	}
	
	public T getChart(){
		return chart;
	}

	public void setMultiData(List<List<String>> lsLabels,
			Map<String, List<String>> mapValues) throws Exception {
		lsCategories = new ArrayList<Categories>();
		for (List<String> lsLabel : lsLabels) {
			Categories categories = new Categories();
			List<Category> lsCategory = new ArrayList<Category>();
			for (String label : lsLabel) {
				Category category = new Category();
				category.setLabel(label);
				lsCategory.add(category);
			}
			categories.setCategory(lsCategory);
			lsCategories.add(categories);
		}

		lsMultiData = new ArrayList<DataSet<K>>();
		for (Map.Entry<String, List<String>> entity : mapValues.entrySet()) {
			DataSet<K> multiData = new DataSet<K>();
			List<K> lsData = new ArrayList<K>();
			for (String value : entity.getValue()) {
				Class<K> dataClass = (Class<K>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[1];
				K data = dataClass.newInstance();
				data.setValue(value);
				lsData.add(data);
			}
			multiData.setSeriesname(entity.getKey());
			multiData.setData(lsData);
			lsMultiData.add(multiData);
		}
	}

	public void setSingleData(Map<String, String> mapData) throws Exception {
		singleData = new ArrayList<K>();
		for (Map.Entry<String, String> entity : mapData.entrySet()) {
			Class<K> dataClass = (Class<K>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[1];
			K data = dataClass.newInstance();
			data.setLabel(entity.getKey());
			data.setValue(entity.getValue());
			singleData.add(data);
		}
	}

	public void setTrendLine(String startValue) {
		trendlines = new TrendLine();
		List<Line> lsLine = new ArrayList<Line>();
		Line line = new Line();
		line.setStartValue(startValue);
		lsLine.add(line);
		trendlines.setLine(lsLine);
	}

	public void setStyles(Map<String, Definition> mapStyle) {
		styles = new Styles();
		List<Definition> lsDefinition = new ArrayList<Definition>();
		List<Application> lsApplication = new ArrayList<Application>();
		for (Map.Entry<String, Definition> entity : mapStyle.entrySet()) {
			Definition definition = entity.getValue();
			lsDefinition.add(definition);
			Application application = new Application();
			application.setStyles(definition.getName());
			application.setToobject(entity.getKey());
			lsApplication.add(application);
		}
		styles.setDefinition(lsDefinition);
		styles.setApplication(lsApplication);
	}

	public String toJson(boolean isMulti) throws Exception {
		String json = "";
		if (isMulti) {
			MultiChart<T, K> mc = new MultiChart<T, K>();
			mc.setChart(chart);
			if (lsCategories != null) {
				mc.setCategories(lsCategories);
			}
			if (lsMultiData != null) {
				mc.setDataset(lsMultiData);
			}
			if (showTrendLine && trendlines != null) {
				mc.setTrendlines(trendlines);
			}
			if (styles != null) {
				mc.setStyles(styles);
			}
			//转换Json串
			json = JsonUtil.convertToJson(mc);

		} else {
			SingleChart<T, K> sc = new SingleChart<T, K>();
			sc.setChart(chart);
			if (singleData != null) {
				sc.setData(singleData);
			}

			if (showTrendLine && trendlines != null) {
				sc.setTrendlines(trendlines);
			}
			if (styles != null) {
				sc.setStyles(styles);
			}
			//转换Json串
			json = JsonUtil.convertToJson(sc);
		}

		return json;
	}

	public void setShowTrendLine(boolean showTrendLine) {
		this.showTrendLine = showTrendLine;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}
}
