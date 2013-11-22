package com.xhsoft.framework.common.chart.fusionChart.utils.theme;

import com.xhsoft.framework.common.chart.fusionChart.model.style.ColorCons;
import com.xhsoft.framework.common.chart.fusionChart.model.style.Definition;
import com.xhsoft.framework.common.chart.fusionChart.model.style.StyleTypeCons;

public class ColumnStyle {
	
	public static Definition getDefinition(){
		Definition definition = new Definition();
		definition.setName("ColumnAdmin");
		definition.setType(StyleTypeCons.FONT);
		//definition.setAlpha(ColorCons.CANVAS_ALPHA);
		//definition.setBgColor(ColorCons.CUSTOM_CANVAS_COLOR);
		definition.setBorderColor(ColorCons.COLOR_RED);
		definition.setItalic("1");
		return definition;
	}

}
