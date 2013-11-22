package com.xhsoft.framework.common.chart.fusionChart.model.chart;

public class ColumnChart extends Chart {

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
	
	private String placeValuesInside;
	private String showShadow;
	
	//Column3D
	private String maxColWidth;
	private String use3DLighting;
	
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
	
	//Column2D
	private String canvasBgRatio;
	private String canvasBgAngle;
	private String canvasBorderColor;
	private String canvasBorderThickness;
	private String canvasBorderAlpha;
	//Column3D
	private String canvasBaseColor;
	private String showCanvasBg ;
	private String showCanvasBase;
	private String canvasBaseDepth;
	private String canvasBgDepth;
	
	/**========================
	 * Data Plot Cosmetics
	 ==========================*/
	private String plotBorderThickness;
	
	//Column2D
	private String plotBorderDashed;
	private String plotBorderDashLen;
	private String plotBorderDashGap;
	private String plotFillAngle;
	private String plotFillRatio;
	private String plotGradientColor;
	private String useRoundEdges;
	
	//Column3D
	private String overlapColumns;
	
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
	//Column2D
	private String zeroPlaneThickness;
	private String showAlternateHGridColor;
	private String alternateHGridColor;
	private String alternateHGridAlpha;
	//Column3D
	private String zeroPlaneShowBorder;
	private String zeroPlaneBorderColor;
	
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
	
	/**===========================
	 * Tool-tip
	 ============================*/
	private String showToolTip;
	private String toolTipBgColor;
	private String toolTipBorderColor;
	private String toolTipSepChar;
	private String showToolTipShadow;
	
	/**=====================================
	 * Chart Padding & Margins
	 ======================================*/
	private String xAxisNamePadding;
	private String yAxisNamePadding;
	private String yAxisValuesPadding;
	private String labelPadding;
	private String valuePadding;
	private String plotSpacePercent;
	private String canvasLeftMargin;
	private String canvasRightMargin;
	private String canvasTopMargin;
	private String canvasBottomMargin;
	
	
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
	public String getPlaceValuesInside() {
		return placeValuesInside;
	}
	public void setPlaceValuesInside(String placeValuesInside) {
		this.placeValuesInside = placeValuesInside;
	}
	public String getShowShadow() {
		return showShadow;
	}
	public void setShowShadow(String showShadow) {
		this.showShadow = showShadow;
	}
	public String getMaxColWidth() {
		return maxColWidth;
	}
	public void setMaxColWidth(String maxColWidth) {
		this.maxColWidth = maxColWidth;
	}
	public String getUse3DLighting() {
		return use3DLighting;
	}
	public void setUse3DLighting(String use3dLighting) {
		use3DLighting = use3dLighting;
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
	public String getCanvasBaseColor() {
		return canvasBaseColor;
	}
	public void setCanvasBaseColor(String canvasBaseColor) {
		this.canvasBaseColor = canvasBaseColor;
	}
	public String getShowCanvasBg() {
		return showCanvasBg;
	}
	public void setShowCanvasBg(String showCanvasBg) {
		this.showCanvasBg = showCanvasBg;
	}
	public String getShowCanvasBase() {
		return showCanvasBase;
	}
	public void setShowCanvasBase(String showCanvasBase) {
		this.showCanvasBase = showCanvasBase;
	}
	public String getCanvasBaseDepth() {
		return canvasBaseDepth;
	}
	public void setCanvasBaseDepth(String canvasBaseDepth) {
		this.canvasBaseDepth = canvasBaseDepth;
	}
	public String getCanvasBgDepth() {
		return canvasBgDepth;
	}
	public void setCanvasBgDepth(String canvasBgDepth) {
		this.canvasBgDepth = canvasBgDepth;
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
	public String getUseRoundEdges() {
		return useRoundEdges;
	}
	public void setUseRoundEdges(String useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
	}
	public String getOverlapColumns() {
		return overlapColumns;
	}
	public void setOverlapColumns(String overlapColumns) {
		this.overlapColumns = overlapColumns;
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
	public String getZeroPlaneShowBorder() {
		return zeroPlaneShowBorder;
	}
	public void setZeroPlaneShowBorder(String zeroPlaneShowBorder) {
		this.zeroPlaneShowBorder = zeroPlaneShowBorder;
	}
	public String getZeroPlaneBorderColor() {
		return zeroPlaneBorderColor;
	}
	public void setZeroPlaneBorderColor(String zeroPlaneBorderColor) {
		this.zeroPlaneBorderColor = zeroPlaneBorderColor;
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
	public String getShowToolTip() {
		return showToolTip;
	}
	public void setShowToolTip(String showToolTip) {
		this.showToolTip = showToolTip;
	}
	public String getToolTipBgColor() {
		return toolTipBgColor;
	}
	public void setToolTipBgColor(String toolTipBgColor) {
		this.toolTipBgColor = toolTipBgColor;
	}
	public String getToolTipBorderColor() {
		return toolTipBorderColor;
	}
	public void setToolTipBorderColor(String toolTipBorderColor) {
		this.toolTipBorderColor = toolTipBorderColor;
	}
	public String getToolTipSepChar() {
		return toolTipSepChar;
	}
	public void setToolTipSepChar(String toolTipSepChar) {
		this.toolTipSepChar = toolTipSepChar;
	}
	public String getShowToolTipShadow() {
		return showToolTipShadow;
	}
	public void setShowToolTipShadow(String showToolTipShadow) {
		this.showToolTipShadow = showToolTipShadow;
	}
	public String getxAxisNamePadding() {
		return xAxisNamePadding;
	}
	public void setxAxisNamePadding(String xAxisNamePadding) {
		this.xAxisNamePadding = xAxisNamePadding;
	}
	public String getyAxisNamePadding() {
		return yAxisNamePadding;
	}
	public void setyAxisNamePadding(String yAxisNamePadding) {
		this.yAxisNamePadding = yAxisNamePadding;
	}
	public String getyAxisValuesPadding() {
		return yAxisValuesPadding;
	}
	public void setyAxisValuesPadding(String yAxisValuesPadding) {
		this.yAxisValuesPadding = yAxisValuesPadding;
	}
	public String getLabelPadding() {
		return labelPadding;
	}
	public void setLabelPadding(String labelPadding) {
		this.labelPadding = labelPadding;
	}
	public String getValuePadding() {
		return valuePadding;
	}
	public void setValuePadding(String valuePadding) {
		this.valuePadding = valuePadding;
	}
	public String getPlotSpacePercent() {
		return plotSpacePercent;
	}
	public void setPlotSpacePercent(String plotSpacePercent) {
		this.plotSpacePercent = plotSpacePercent;
	}
	public String getCanvasLeftMargin() {
		return canvasLeftMargin;
	}
	public void setCanvasLeftMargin(String canvasLeftMargin) {
		this.canvasLeftMargin = canvasLeftMargin;
	}
	public String getCanvasRightMargin() {
		return canvasRightMargin;
	}
	public void setCanvasRightMargin(String canvasRightMargin) {
		this.canvasRightMargin = canvasRightMargin;
	}
	public String getCanvasTopMargin() {
		return canvasTopMargin;
	}
	public void setCanvasTopMargin(String canvasTopMargin) {
		this.canvasTopMargin = canvasTopMargin;
	}
	public String getCanvasBottomMargin() {
		return canvasBottomMargin;
	}
	public void setCanvasBottomMargin(String canvasBottomMargin) {
		this.canvasBottomMargin = canvasBottomMargin;
	}
}
