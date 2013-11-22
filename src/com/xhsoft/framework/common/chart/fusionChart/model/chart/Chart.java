package com.xhsoft.framework.common.chart.fusionChart.model.chart;

public class Chart {
	
	/**
	 * Column2D and Pie2D and Line2D and Bar2D and Area2D and Doughnut2D
	 * Column3D and Pie3D and Doughnut3D
	 */
	
	/**===============================
	 * Functional Attributes
	 ================================*/
	private String animation;
	private String palette;
	private String paletteColors;
	private String showAboutMenuItem;
	private String aboutMenuItemLabel;
	private String aboutMenuItemLink;
	private String showLabels;
	private String clickURL;
	private String showValues;
	private String defaultAnimation;
	
	/**===============================
	 * Chart Titles and Axis Names
	 ================================*/
	private String caption;
	private String subCaption;
	
	/**===============================
	 * Chart Cosmetics
	 ================================*/
	private String bgColor;
	private String bgAlpha;
	private String bgRatio;
	private String bgAngle;
	private String bgImage;
	private String bgImageAlpha;
	private String bgImageDisplayMode;
	private String bgImageVAlign;
	private String bgImageHAlign;
	private String bgImageScale;
	private String showBorder;
	private String borderColor;
	private String borderThickness;
	private String borderAlpha; 
	private String logoURL;
	private String logoPosition;
	private String logoAlpha; 
	private String logoScale; 
	private String logoLink;
	
	/**========================
	 * Data Plot Cosmetics
	 ==========================*/
	private String showPlotBorder;
	private String plotBorderColor;
	private String plotBorderAlpha;
	private String plotFillAlpha;
	
	/**============================
	 * Number Formatting
	 =============================*/
	private String formatNumber;
	private String formatNumberScale;
	private String defaultNumberScale;
	private String numberScaleUnit;
	private String numberScaleValue;
	private String scaleRecursively;
	private String maxScaleRecursion;
	private String scaleSeparator;
	private String numberPrefix;
	private String numberSuffix ;
	private String decimalSeparator;
	private String thousandSeparator;
	private String thousandSeparatorPosition;
	private String inDecimalSeparator;
	private String inThousandSeparator;
	private String decimals;
	private String forceDecimals;
	
	/**============================
	 * Font Properties
	 =============================*/
	private String baseFont;
	private String baseFontSize;
	private String baseFontColor;
	
	/**=====================================
	 * Chart Padding & Margins
	 ======================================*/
	private String captionPadding;
	private String chartLeftMargin;
	private String chartRightMargin;
	private String chartTopMargin;
	private String chartBottomMargin;
	
	public String getAnimation() {
		return animation;
	}
	public void setAnimation(String animation) {
		this.animation = animation;
	}
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}
	public String getPaletteColors() {
		return paletteColors;
	}
	public void setPaletteColors(String paletteColors) {
		this.paletteColors = paletteColors;
	}
	public String getShowAboutMenuItem() {
		return showAboutMenuItem;
	}
	public void setShowAboutMenuItem(String showAboutMenuItem) {
		this.showAboutMenuItem = showAboutMenuItem;
	}
	public String getAboutMenuItemLabel() {
		return aboutMenuItemLabel;
	}
	public void setAboutMenuItemLabel(String aboutMenuItemLabel) {
		this.aboutMenuItemLabel = aboutMenuItemLabel;
	}
	public String getAboutMenuItemLink() {
		return aboutMenuItemLink;
	}
	public void setAboutMenuItemLink(String aboutMenuItemLink) {
		this.aboutMenuItemLink = aboutMenuItemLink;
	}
	public String getShowLabels() {
		return showLabels;
	}
	public void setShowLabels(String showLabels) {
		this.showLabels = showLabels;
	}
	public String getClickURL() {
		return clickURL;
	}
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	public String getShowValues() {
		return showValues;
	}
	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}
	public String getDefaultAnimation() {
		return defaultAnimation;
	}
	public void setDefaultAnimation(String defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSubCaption() {
		return subCaption;
	}
	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getBgAlpha() {
		return bgAlpha;
	}
	public void setBgAlpha(String bgAlpha) {
		this.bgAlpha = bgAlpha;
	}
	public String getBgRatio() {
		return bgRatio;
	}
	public void setBgRatio(String bgRatio) {
		this.bgRatio = bgRatio;
	}
	public String getBgAngle() {
		return bgAngle;
	}
	public void setBgAngle(String bgAngle) {
		this.bgAngle = bgAngle;
	}
	public String getBgImage() {
		return bgImage;
	}
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	public String getBgImageAlpha() {
		return bgImageAlpha;
	}
	public void setBgImageAlpha(String bgImageAlpha) {
		this.bgImageAlpha = bgImageAlpha;
	}
	public String getBgImageDisplayMode() {
		return bgImageDisplayMode;
	}
	public void setBgImageDisplayMode(String bgImageDisplayMode) {
		this.bgImageDisplayMode = bgImageDisplayMode;
	}
	public String getBgImageVAlign() {
		return bgImageVAlign;
	}
	public void setBgImageVAlign(String bgImageVAlign) {
		this.bgImageVAlign = bgImageVAlign;
	}
	public String getBgImageHAlign() {
		return bgImageHAlign;
	}
	public void setBgImageHAlign(String bgImageHAlign) {
		this.bgImageHAlign = bgImageHAlign;
	}
	public String getBgImageScale() {
		return bgImageScale;
	}
	public void setBgImageScale(String bgImageScale) {
		this.bgImageScale = bgImageScale;
	}
	public String getShowBorder() {
		return showBorder;
	}
	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getBorderThickness() {
		return borderThickness;
	}
	public void setBorderThickness(String borderThickness) {
		this.borderThickness = borderThickness;
	}
	public String getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(String borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	public String getLogoPosition() {
		return logoPosition;
	}
	public void setLogoPosition(String logoPosition) {
		this.logoPosition = logoPosition;
	}
	public String getLogoAlpha() {
		return logoAlpha;
	}
	public void setLogoAlpha(String logoAlpha) {
		this.logoAlpha = logoAlpha;
	}
	public String getLogoScale() {
		return logoScale;
	}
	public void setLogoScale(String logoScale) {
		this.logoScale = logoScale;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
	public String getShowPlotBorder() {
		return showPlotBorder;
	}
	public void setShowPlotBorder(String showPlotBorder) {
		this.showPlotBorder = showPlotBorder;
	}
	public String getPlotBorderColor() {
		return plotBorderColor;
	}
	public void setPlotBorderColor(String plotBorderColor) {
		this.plotBorderColor = plotBorderColor;
	}
	public String getPlotBorderAlpha() {
		return plotBorderAlpha;
	}
	public void setPlotBorderAlpha(String plotBorderAlpha) {
		this.plotBorderAlpha = plotBorderAlpha;
	}
	public String getPlotFillAlpha() {
		return plotFillAlpha;
	}
	public void setPlotFillAlpha(String plotFillAlpha) {
		this.plotFillAlpha = plotFillAlpha;
	}
	public String getFormatNumber() {
		return formatNumber;
	}
	public void setFormatNumber(String formatNumber) {
		this.formatNumber = formatNumber;
	}
	public String getFormatNumberScale() {
		return formatNumberScale;
	}
	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}
	public String getDefaultNumberScale() {
		return defaultNumberScale;
	}
	public void setDefaultNumberScale(String defaultNumberScale) {
		this.defaultNumberScale = defaultNumberScale;
	}
	public String getNumberScaleUnit() {
		return numberScaleUnit;
	}
	public void setNumberScaleUnit(String numberScaleUnit) {
		this.numberScaleUnit = numberScaleUnit;
	}
	public String getNumberScaleValue() {
		return numberScaleValue;
	}
	public void setNumberScaleValue(String numberScaleValue) {
		this.numberScaleValue = numberScaleValue;
	}
	public String getScaleRecursively() {
		return scaleRecursively;
	}
	public void setScaleRecursively(String scaleRecursively) {
		this.scaleRecursively = scaleRecursively;
	}
	public String getMaxScaleRecursion() {
		return maxScaleRecursion;
	}
	public void setMaxScaleRecursion(String maxScaleRecursion) {
		this.maxScaleRecursion = maxScaleRecursion;
	}
	public String getScaleSeparator() {
		return scaleSeparator;
	}
	public void setScaleSeparator(String scaleSeparator) {
		this.scaleSeparator = scaleSeparator;
	}
	public String getNumberPrefix() {
		return numberPrefix;
	}
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}
	public String getNumberSuffix() {
		return numberSuffix;
	}
	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}
	public String getDecimalSeparator() {
		return decimalSeparator;
	}
	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}
	public String getThousandSeparator() {
		return thousandSeparator;
	}
	public void setThousandSeparator(String thousandSeparator) {
		this.thousandSeparator = thousandSeparator;
	}
	public String getThousandSeparatorPosition() {
		return thousandSeparatorPosition;
	}
	public void setThousandSeparatorPosition(String thousandSeparatorPosition) {
		this.thousandSeparatorPosition = thousandSeparatorPosition;
	}
	public String getInDecimalSeparator() {
		return inDecimalSeparator;
	}
	public void setInDecimalSeparator(String inDecimalSeparator) {
		this.inDecimalSeparator = inDecimalSeparator;
	}
	public String getInThousandSeparator() {
		return inThousandSeparator;
	}
	public void setInThousandSeparator(String inThousandSeparator) {
		this.inThousandSeparator = inThousandSeparator;
	}
	public String getDecimals() {
		return decimals;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public String getForceDecimals() {
		return forceDecimals;
	}
	public void setForceDecimals(String forceDecimals) {
		this.forceDecimals = forceDecimals;
	}
	public String getBaseFont() {
		return baseFont;
	}
	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}
	public String getBaseFontSize() {
		return baseFontSize;
	}
	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}
	public String getBaseFontColor() {
		return baseFontColor;
	}
	public void setBaseFontColor(String baseFontColor) {
		this.baseFontColor = baseFontColor;
	}
	public String getCaptionPadding() {
		return captionPadding;
	}
	public void setCaptionPadding(String captionPadding) {
		this.captionPadding = captionPadding;
	}
	public String getChartLeftMargin() {
		return chartLeftMargin;
	}
	public void setChartLeftMargin(String chartLeftMargin) {
		this.chartLeftMargin = chartLeftMargin;
	}
	public String getChartRightMargin() {
		return chartRightMargin;
	}
	public void setChartRightMargin(String chartRightMargin) {
		this.chartRightMargin = chartRightMargin;
	}
	public String getChartTopMargin() {
		return chartTopMargin;
	}
	public void setChartTopMargin(String chartTopMargin) {
		this.chartTopMargin = chartTopMargin;
	}
	public String getChartBottomMargin() {
		return chartBottomMargin;
	}
	public void setChartBottomMargin(String chartBottomMargin) {
		this.chartBottomMargin = chartBottomMargin;
	}
	
	
}
