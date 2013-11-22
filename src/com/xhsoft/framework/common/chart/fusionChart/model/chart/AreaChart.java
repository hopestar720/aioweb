package com.xhsoft.framework.common.chart.fusionChart.model.chart;

public class AreaChart extends Chart{

	/**===============================
	 * Functional Attributes
	 ================================*/
	private String showYAxisValues;
	private String showLimits;
	private String showDivLineValues;
	private String yAxisValuesStep;
	private String adjustDiv;
	private String useEllipsesWhenOverflow;
	private String labelStep;
	private String yAxisMinValue;
	private String yAxisMaxValue;
	private String setAdaptiveYMin;
	
	private String rotateValues;
	private String rotateYAxisName;
	private String yAxisNameWidth;
	private String labelDisplay;
	private String rotateLabels;
	private String slantLabels;
	private String staggerLines;
	private String centerYaxisName;
	
	/**===============================
	 * Chart Titles and Axis Names
	 ================================*/
	private String xAxisName;
	private String yAxisName;
	
	/**===============================
	 * Chart Cosmetics
	 ================================*/
	private String showVLineLabelBorder;
	
	private String canvasBgColor;
	private String canvasBgAlpha;
	
	private String canvasBgRatio;
	private String canvasBgAngle;
	private String canvasBorderColor;
	private String canvasBorderThickness;
	private String canvasBorderAlpha;
	
	/**========================
	 * Data Plot Cosmetics
	 ==========================*/
	private String plotBorderThickness;
	
	private String plotBorderDashed;
	private String plotBorderDashLen;
	private String plotBorderDashGap;
	private String plotFillAngle;
	private String plotFillRatio;
	private String plotGradientColor;
	
	private String plotFillColor;
	
	/**==============================
	 * Divisional Lines & Grids
	 ===============================*/
	private String numDivLines;
	private String divLineColor;
	private String divLineThickness;
	private String divLineAlpha;
	private String divLineIsDashed;
	private String divLineDashLen;
	private String divLineDashGap;
	private String zeroPlaneColor;
	private String zeroPlaneAlpha;
	private String showZeroPlaneValue;
	
	private String zeroPlaneThickness;
	
	private String showAlternateHGridColor;
	private String alternateHGridColor;
	private String alternateHGridAlpha;
	private String showAlternateVGridColor;
	private String alternateVGridColor;
	private String alternateVGridAlpha;
	
	private String numVDivLines;
	private String vDivLineColor;
	private String vDivLineThickness;
	private String vDivLineAlpha;
	private String vDivLineIsDashed;
	private String vDivLineDashLen;
	private String vDivLineDashGap;
	
	/**============================
	 * Anchors
	 =============================*/
	//Line2D and Area2D
	private String drawAnchors;
	private String anchorSides;
	private String anchorRadius;
	private String anchorBorderColor;
	private String anchorBorderThickness;
	private String anchorBgColor;
	private String anchorAlpha;
	private String anchorBgAlpha;
	
	/**============================
	 * Number Formatting
	 =============================*/
	private String forceYAxisValueDecimals;
	private String yAxisValueDecimals;
	
	/**============================
	 * Font Properties
	 =============================*/
	private String outCnvBaseFont;
	private String outCnvBaseFontSize;
	private String outCnvBaseFontColor;
	
	public String getShowYAxisValues() {
		return showYAxisValues;
	}
	public void setShowYAxisValues(String showYAxisValues) {
		this.showYAxisValues = showYAxisValues;
	}
	public String getShowLimits() {
		return showLimits;
	}
	public void setShowLimits(String showLimits) {
		this.showLimits = showLimits;
	}
	public String getShowDivLineValues() {
		return showDivLineValues;
	}
	public void setShowDivLineValues(String showDivLineValues) {
		this.showDivLineValues = showDivLineValues;
	}
	public String getyAxisValuesStep() {
		return yAxisValuesStep;
	}
	public void setyAxisValuesStep(String yAxisValuesStep) {
		this.yAxisValuesStep = yAxisValuesStep;
	}
	public String getAdjustDiv() {
		return adjustDiv;
	}
	public void setAdjustDiv(String adjustDiv) {
		this.adjustDiv = adjustDiv;
	}
	public String getUseEllipsesWhenOverflow() {
		return useEllipsesWhenOverflow;
	}
	public void setUseEllipsesWhenOverflow(String useEllipsesWhenOverflow) {
		this.useEllipsesWhenOverflow = useEllipsesWhenOverflow;
	}
	public String getLabelStep() {
		return labelStep;
	}
	public void setLabelStep(String labelStep) {
		this.labelStep = labelStep;
	}
	public String getyAxisMinValue() {
		return yAxisMinValue;
	}
	public void setyAxisMinValue(String yAxisMinValue) {
		this.yAxisMinValue = yAxisMinValue;
	}
	public String getyAxisMaxValue() {
		return yAxisMaxValue;
	}
	public void setyAxisMaxValue(String yAxisMaxValue) {
		this.yAxisMaxValue = yAxisMaxValue;
	}
	public String getSetAdaptiveYMin() {
		return setAdaptiveYMin;
	}
	public void setSetAdaptiveYMin(String setAdaptiveYMin) {
		this.setAdaptiveYMin = setAdaptiveYMin;
	}
	public String getRotateValues() {
		return rotateValues;
	}
	public void setRotateValues(String rotateValues) {
		this.rotateValues = rotateValues;
	}
	public String getRotateYAxisName() {
		return rotateYAxisName;
	}
	public void setRotateYAxisName(String rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}
	public String getyAxisNameWidth() {
		return yAxisNameWidth;
	}
	public void setyAxisNameWidth(String yAxisNameWidth) {
		this.yAxisNameWidth = yAxisNameWidth;
	}
	public String getLabelDisplay() {
		return labelDisplay;
	}
	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}
	public String getRotateLabels() {
		return rotateLabels;
	}
	public void setRotateLabels(String rotateLabels) {
		this.rotateLabels = rotateLabels;
	}
	public String getSlantLabels() {
		return slantLabels;
	}
	public void setSlantLabels(String slantLabels) {
		this.slantLabels = slantLabels;
	}
	public String getStaggerLines() {
		return staggerLines;
	}
	public void setStaggerLines(String staggerLines) {
		this.staggerLines = staggerLines;
	}
	public String getCenterYaxisName() {
		return centerYaxisName;
	}
	public void setCenterYaxisName(String centerYaxisName) {
		this.centerYaxisName = centerYaxisName;
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
	public String getShowVLineLabelBorder() {
		return showVLineLabelBorder;
	}
	public void setShowVLineLabelBorder(String showVLineLabelBorder) {
		this.showVLineLabelBorder = showVLineLabelBorder;
	}
	public String getCanvasBgColor() {
		return canvasBgColor;
	}
	public void setCanvasBgColor(String canvasBgColor) {
		this.canvasBgColor = canvasBgColor;
	}
	public String getCanvasBgAlpha() {
		return canvasBgAlpha;
	}
	public void setCanvasBgAlpha(String canvasBgAlpha) {
		this.canvasBgAlpha = canvasBgAlpha;
	}
	public String getCanvasBgRatio() {
		return canvasBgRatio;
	}
	public void setCanvasBgRatio(String canvasBgRatio) {
		this.canvasBgRatio = canvasBgRatio;
	}
	public String getCanvasBgAngle() {
		return canvasBgAngle;
	}
	public void setCanvasBgAngle(String canvasBgAngle) {
		this.canvasBgAngle = canvasBgAngle;
	}
	public String getCanvasBorderColor() {
		return canvasBorderColor;
	}
	public void setCanvasBorderColor(String canvasBorderColor) {
		this.canvasBorderColor = canvasBorderColor;
	}
	public String getCanvasBorderThickness() {
		return canvasBorderThickness;
	}
	public void setCanvasBorderThickness(String canvasBorderThickness) {
		this.canvasBorderThickness = canvasBorderThickness;
	}
	public String getCanvasBorderAlpha() {
		return canvasBorderAlpha;
	}
	public void setCanvasBorderAlpha(String canvasBorderAlpha) {
		this.canvasBorderAlpha = canvasBorderAlpha;
	}
	public String getPlotBorderThickness() {
		return plotBorderThickness;
	}
	public void setPlotBorderThickness(String plotBorderThickness) {
		this.plotBorderThickness = plotBorderThickness;
	}
	public String getPlotBorderDashed() {
		return plotBorderDashed;
	}
	public void setPlotBorderDashed(String plotBorderDashed) {
		this.plotBorderDashed = plotBorderDashed;
	}
	public String getPlotBorderDashLen() {
		return plotBorderDashLen;
	}
	public void setPlotBorderDashLen(String plotBorderDashLen) {
		this.plotBorderDashLen = plotBorderDashLen;
	}
	public String getPlotBorderDashGap() {
		return plotBorderDashGap;
	}
	public void setPlotBorderDashGap(String plotBorderDashGap) {
		this.plotBorderDashGap = plotBorderDashGap;
	}
	public String getPlotFillAngle() {
		return plotFillAngle;
	}
	public void setPlotFillAngle(String plotFillAngle) {
		this.plotFillAngle = plotFillAngle;
	}
	public String getPlotFillRatio() {
		return plotFillRatio;
	}
	public void setPlotFillRatio(String plotFillRatio) {
		this.plotFillRatio = plotFillRatio;
	}
	public String getPlotGradientColor() {
		return plotGradientColor;
	}
	public void setPlotGradientColor(String plotGradientColor) {
		this.plotGradientColor = plotGradientColor;
	}
	public String getPlotFillColor() {
		return plotFillColor;
	}
	public void setPlotFillColor(String plotFillColor) {
		this.plotFillColor = plotFillColor;
	}
	public String getNumDivLines() {
		return numDivLines;
	}
	public void setNumDivLines(String numDivLines) {
		this.numDivLines = numDivLines;
	}
	public String getDivLineColor() {
		return divLineColor;
	}
	public void setDivLineColor(String divLineColor) {
		this.divLineColor = divLineColor;
	}
	public String getDivLineThickness() {
		return divLineThickness;
	}
	public void setDivLineThickness(String divLineThickness) {
		this.divLineThickness = divLineThickness;
	}
	public String getDivLineAlpha() {
		return divLineAlpha;
	}
	public void setDivLineAlpha(String divLineAlpha) {
		this.divLineAlpha = divLineAlpha;
	}
	public String getDivLineIsDashed() {
		return divLineIsDashed;
	}
	public void setDivLineIsDashed(String divLineIsDashed) {
		this.divLineIsDashed = divLineIsDashed;
	}
	public String getDivLineDashLen() {
		return divLineDashLen;
	}
	public void setDivLineDashLen(String divLineDashLen) {
		this.divLineDashLen = divLineDashLen;
	}
	public String getDivLineDashGap() {
		return divLineDashGap;
	}
	public void setDivLineDashGap(String divLineDashGap) {
		this.divLineDashGap = divLineDashGap;
	}
	public String getZeroPlaneColor() {
		return zeroPlaneColor;
	}
	public void setZeroPlaneColor(String zeroPlaneColor) {
		this.zeroPlaneColor = zeroPlaneColor;
	}
	public String getZeroPlaneAlpha() {
		return zeroPlaneAlpha;
	}
	public void setZeroPlaneAlpha(String zeroPlaneAlpha) {
		this.zeroPlaneAlpha = zeroPlaneAlpha;
	}
	public String getShowZeroPlaneValue() {
		return showZeroPlaneValue;
	}
	public void setShowZeroPlaneValue(String showZeroPlaneValue) {
		this.showZeroPlaneValue = showZeroPlaneValue;
	}
	public String getZeroPlaneThickness() {
		return zeroPlaneThickness;
	}
	public void setZeroPlaneThickness(String zeroPlaneThickness) {
		this.zeroPlaneThickness = zeroPlaneThickness;
	}
	public String getShowAlternateHGridColor() {
		return showAlternateHGridColor;
	}
	public void setShowAlternateHGridColor(String showAlternateHGridColor) {
		this.showAlternateHGridColor = showAlternateHGridColor;
	}
	public String getAlternateHGridColor() {
		return alternateHGridColor;
	}
	public void setAlternateHGridColor(String alternateHGridColor) {
		this.alternateHGridColor = alternateHGridColor;
	}
	public String getAlternateHGridAlpha() {
		return alternateHGridAlpha;
	}
	public void setAlternateHGridAlpha(String alternateHGridAlpha) {
		this.alternateHGridAlpha = alternateHGridAlpha;
	}
	public String getShowAlternateVGridColor() {
		return showAlternateVGridColor;
	}
	public void setShowAlternateVGridColor(String showAlternateVGridColor) {
		this.showAlternateVGridColor = showAlternateVGridColor;
	}
	public String getAlternateVGridColor() {
		return alternateVGridColor;
	}
	public void setAlternateVGridColor(String alternateVGridColor) {
		this.alternateVGridColor = alternateVGridColor;
	}
	public String getAlternateVGridAlpha() {
		return alternateVGridAlpha;
	}
	public void setAlternateVGridAlpha(String alternateVGridAlpha) {
		this.alternateVGridAlpha = alternateVGridAlpha;
	}
	public String getNumVDivLines() {
		return numVDivLines;
	}
	public void setNumVDivLines(String numVDivLines) {
		this.numVDivLines = numVDivLines;
	}
	public String getvDivLineColor() {
		return vDivLineColor;
	}
	public void setvDivLineColor(String vDivLineColor) {
		this.vDivLineColor = vDivLineColor;
	}
	public String getvDivLineThickness() {
		return vDivLineThickness;
	}
	public void setvDivLineThickness(String vDivLineThickness) {
		this.vDivLineThickness = vDivLineThickness;
	}
	public String getvDivLineAlpha() {
		return vDivLineAlpha;
	}
	public void setvDivLineAlpha(String vDivLineAlpha) {
		this.vDivLineAlpha = vDivLineAlpha;
	}
	public String getvDivLineIsDashed() {
		return vDivLineIsDashed;
	}
	public void setvDivLineIsDashed(String vDivLineIsDashed) {
		this.vDivLineIsDashed = vDivLineIsDashed;
	}
	public String getvDivLineDashLen() {
		return vDivLineDashLen;
	}
	public void setvDivLineDashLen(String vDivLineDashLen) {
		this.vDivLineDashLen = vDivLineDashLen;
	}
	public String getvDivLineDashGap() {
		return vDivLineDashGap;
	}
	public void setvDivLineDashGap(String vDivLineDashGap) {
		this.vDivLineDashGap = vDivLineDashGap;
	}
	public String getDrawAnchors() {
		return drawAnchors;
	}
	public void setDrawAnchors(String drawAnchors) {
		this.drawAnchors = drawAnchors;
	}
	public String getAnchorSides() {
		return anchorSides;
	}
	public void setAnchorSides(String anchorSides) {
		this.anchorSides = anchorSides;
	}
	public String getAnchorRadius() {
		return anchorRadius;
	}
	public void setAnchorRadius(String anchorRadius) {
		this.anchorRadius = anchorRadius;
	}
	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}
	public void setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
	}
	public String getAnchorBorderThickness() {
		return anchorBorderThickness;
	}
	public void setAnchorBorderThickness(String anchorBorderThickness) {
		this.anchorBorderThickness = anchorBorderThickness;
	}
	public String getAnchorBgColor() {
		return anchorBgColor;
	}
	public void setAnchorBgColor(String anchorBgColor) {
		this.anchorBgColor = anchorBgColor;
	}
	public String getAnchorAlpha() {
		return anchorAlpha;
	}
	public void setAnchorAlpha(String anchorAlpha) {
		this.anchorAlpha = anchorAlpha;
	}
	public String getAnchorBgAlpha() {
		return anchorBgAlpha;
	}
	public void setAnchorBgAlpha(String anchorBgAlpha) {
		this.anchorBgAlpha = anchorBgAlpha;
	}
	public String getForceYAxisValueDecimals() {
		return forceYAxisValueDecimals;
	}
	public void setForceYAxisValueDecimals(String forceYAxisValueDecimals) {
		this.forceYAxisValueDecimals = forceYAxisValueDecimals;
	}
	public String getyAxisValueDecimals() {
		return yAxisValueDecimals;
	}
	public void setyAxisValueDecimals(String yAxisValueDecimals) {
		this.yAxisValueDecimals = yAxisValueDecimals;
	}
	public String getOutCnvBaseFont() {
		return outCnvBaseFont;
	}
	public void setOutCnvBaseFont(String outCnvBaseFont) {
		this.outCnvBaseFont = outCnvBaseFont;
	}
	public String getOutCnvBaseFontSize() {
		return outCnvBaseFontSize;
	}
	public void setOutCnvBaseFontSize(String outCnvBaseFontSize) {
		this.outCnvBaseFontSize = outCnvBaseFontSize;
	}
	public String getOutCnvBaseFontColor() {
		return outCnvBaseFontColor;
	}
	public void setOutCnvBaseFontColor(String outCnvBaseFontColor) {
		this.outCnvBaseFontColor = outCnvBaseFontColor;
	}
	
}
