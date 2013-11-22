package com.xhsoft.framework.common.chart.fusionChart.model.chart;

public class PieChart extends Chart{

	/**===============================
	 * Functional Attributes
	 ================================*/
	private String showZeroPies;
	private String showPercentValues;
	private String showPercentInToolTip;
	private String labelSepChar;
	
	/**========================
	 * Data Plot Cosmetics
	 ==========================*/
	private String plotBorderThickness;
	private String use3DLighting;
	
	/**==============================
	 * Pie / Doughnut Properties
	 ===============================*/
	//Pie2D
	private String radius3D;
	private String slicingDistance;
	private String pieRadius;
	private String startingAngle;
	private String enableRotation;
	
	//Pie3D
	private String pieInnerFaceAlpha;
	private String pieOuterFaceAlpha;
	private String pieYScale;
	private String pieSliceDepth;
	
	/**=============================
	 * Smart Labels & Lines
	 ==============================*/
	private String enableSmartLabels;
	private String skipOverlapLabels;
	private String isSmartLineSlanted;
	private String smartLineColor;
	private String smartLineThickness;
	private String smartLineAlpha;
	private String labelDistance;
	private String smartLabelClearance;
	private String manageLabelOverflow;
	private String useEllipsesWhenOverflow;
	
	/**==========================
	 * Legend Properties
	 ===========================*/
	private String showLegend; 
	private String legendPosition;
	private String legendCaption; 
	private String legendIconScale; 
	private String legendBgColor; 
	private String legendBgAlpha; 
	private String legendBorderColor; 
	private String legendBorderThickness; 
	private String legendBorderAlpha; 
	private String legendShadow; 
	private String legendAllowDrag; 
	private String legendScrollBgColor; 
	private String legendScrollBarColor; 
	private String legendScrollBtnColor; 
	private String reverseLegend; 
	private String interactiveLegend; 
	private String legendNumColumns; 
	private String minimiseWrappingInLegend;
	
	public String getShowZeroPies() {
		return showZeroPies;
	}
	public void setShowZeroPies(String showZeroPies) {
		this.showZeroPies = showZeroPies;
	}
	public String getShowPercentValues() {
		return showPercentValues;
	}
	public void setShowPercentValues(String showPercentValues) {
		this.showPercentValues = showPercentValues;
	}
	public String getShowPercentInToolTip() {
		return showPercentInToolTip;
	}
	public void setShowPercentInToolTip(String showPercentInToolTip) {
		this.showPercentInToolTip = showPercentInToolTip;
	}
	public String getLabelSepChar() {
		return labelSepChar;
	}
	public void setLabelSepChar(String labelSepChar) {
		this.labelSepChar = labelSepChar;
	}
	public String getPlotBorderThickness() {
		return plotBorderThickness;
	}
	public void setPlotBorderThickness(String plotBorderThickness) {
		this.plotBorderThickness = plotBorderThickness;
	}
	public String getUse3DLighting() {
		return use3DLighting;
	}
	public void setUse3DLighting(String use3dLighting) {
		use3DLighting = use3dLighting;
	}
	public String getRadius3D() {
		return radius3D;
	}
	public void setRadius3D(String radius3d) {
		radius3D = radius3d;
	}
	public String getSlicingDistance() {
		return slicingDistance;
	}
	public void setSlicingDistance(String slicingDistance) {
		this.slicingDistance = slicingDistance;
	}
	public String getPieRadius() {
		return pieRadius;
	}
	public void setPieRadius(String pieRadius) {
		this.pieRadius = pieRadius;
	}
	public String getStartingAngle() {
		return startingAngle;
	}
	public void setStartingAngle(String startingAngle) {
		this.startingAngle = startingAngle;
	}
	public String getEnableRotation() {
		return enableRotation;
	}
	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}
	public String getPieInnerFaceAlpha() {
		return pieInnerFaceAlpha;
	}
	public void setPieInnerFaceAlpha(String pieInnerFaceAlpha) {
		this.pieInnerFaceAlpha = pieInnerFaceAlpha;
	}
	public String getPieOuterFaceAlpha() {
		return pieOuterFaceAlpha;
	}
	public void setPieOuterFaceAlpha(String pieOuterFaceAlpha) {
		this.pieOuterFaceAlpha = pieOuterFaceAlpha;
	}
	public String getPieYScale() {
		return pieYScale;
	}
	public void setPieYScale(String pieYScale) {
		this.pieYScale = pieYScale;
	}
	public String getPieSliceDepth() {
		return pieSliceDepth;
	}
	public void setPieSliceDepth(String pieSliceDepth) {
		this.pieSliceDepth = pieSliceDepth;
	}
	public String getEnableSmartLabels() {
		return enableSmartLabels;
	}
	public void setEnableSmartLabels(String enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}
	public String getSkipOverlapLabels() {
		return skipOverlapLabels;
	}
	public void setSkipOverlapLabels(String skipOverlapLabels) {
		this.skipOverlapLabels = skipOverlapLabels;
	}
	public String getIsSmartLineSlanted() {
		return isSmartLineSlanted;
	}
	public void setIsSmartLineSlanted(String isSmartLineSlanted) {
		this.isSmartLineSlanted = isSmartLineSlanted;
	}
	public String getSmartLineColor() {
		return smartLineColor;
	}
	public void setSmartLineColor(String smartLineColor) {
		this.smartLineColor = smartLineColor;
	}
	public String getSmartLineThickness() {
		return smartLineThickness;
	}
	public void setSmartLineThickness(String smartLineThickness) {
		this.smartLineThickness = smartLineThickness;
	}
	public String getSmartLineAlpha() {
		return smartLineAlpha;
	}
	public void setSmartLineAlpha(String smartLineAlpha) {
		this.smartLineAlpha = smartLineAlpha;
	}
	public String getLabelDistance() {
		return labelDistance;
	}
	public void setLabelDistance(String labelDistance) {
		this.labelDistance = labelDistance;
	}
	public String getSmartLabelClearance() {
		return smartLabelClearance;
	}
	public void setSmartLabelClearance(String smartLabelClearance) {
		this.smartLabelClearance = smartLabelClearance;
	}
	public String getManageLabelOverflow() {
		return manageLabelOverflow;
	}
	public void setManageLabelOverflow(String manageLabelOverflow) {
		this.manageLabelOverflow = manageLabelOverflow;
	}
	public String getUseEllipsesWhenOverflow() {
		return useEllipsesWhenOverflow;
	}
	public void setUseEllipsesWhenOverflow(String useEllipsesWhenOverflow) {
		this.useEllipsesWhenOverflow = useEllipsesWhenOverflow;
	}
	public String getShowLegend() {
		return showLegend;
	}
	public void setShowLegend(String showLegend) {
		this.showLegend = showLegend;
	}
	public String getLegendPosition() {
		return legendPosition;
	}
	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}
	public String getLegendCaption() {
		return legendCaption;
	}
	public void setLegendCaption(String legendCaption) {
		this.legendCaption = legendCaption;
	}
	public String getLegendIconScale() {
		return legendIconScale;
	}
	public void setLegendIconScale(String legendIconScale) {
		this.legendIconScale = legendIconScale;
	}
	public String getLegendBgColor() {
		return legendBgColor;
	}
	public void setLegendBgColor(String legendBgColor) {
		this.legendBgColor = legendBgColor;
	}
	public String getLegendBgAlpha() {
		return legendBgAlpha;
	}
	public void setLegendBgAlpha(String legendBgAlpha) {
		this.legendBgAlpha = legendBgAlpha;
	}
	public String getLegendBorderColor() {
		return legendBorderColor;
	}
	public void setLegendBorderColor(String legendBorderColor) {
		this.legendBorderColor = legendBorderColor;
	}
	public String getLegendBorderThickness() {
		return legendBorderThickness;
	}
	public void setLegendBorderThickness(String legendBorderThickness) {
		this.legendBorderThickness = legendBorderThickness;
	}
	public String getLegendBorderAlpha() {
		return legendBorderAlpha;
	}
	public void setLegendBorderAlpha(String legendBorderAlpha) {
		this.legendBorderAlpha = legendBorderAlpha;
	}
	public String getLegendShadow() {
		return legendShadow;
	}
	public void setLegendShadow(String legendShadow) {
		this.legendShadow = legendShadow;
	}
	public String getLegendAllowDrag() {
		return legendAllowDrag;
	}
	public void setLegendAllowDrag(String legendAllowDrag) {
		this.legendAllowDrag = legendAllowDrag;
	}
	public String getLegendScrollBgColor() {
		return legendScrollBgColor;
	}
	public void setLegendScrollBgColor(String legendScrollBgColor) {
		this.legendScrollBgColor = legendScrollBgColor;
	}
	public String getLegendScrollBarColor() {
		return legendScrollBarColor;
	}
	public void setLegendScrollBarColor(String legendScrollBarColor) {
		this.legendScrollBarColor = legendScrollBarColor;
	}
	public String getLegendScrollBtnColor() {
		return legendScrollBtnColor;
	}
	public void setLegendScrollBtnColor(String legendScrollBtnColor) {
		this.legendScrollBtnColor = legendScrollBtnColor;
	}
	public String getReverseLegend() {
		return reverseLegend;
	}
	public void setReverseLegend(String reverseLegend) {
		this.reverseLegend = reverseLegend;
	}
	public String getInteractiveLegend() {
		return interactiveLegend;
	}
	public void setInteractiveLegend(String interactiveLegend) {
		this.interactiveLegend = interactiveLegend;
	}
	public String getLegendNumColumns() {
		return legendNumColumns;
	}
	public void setLegendNumColumns(String legendNumColumns) {
		this.legendNumColumns = legendNumColumns;
	}
	public String getMinimiseWrappingInLegend() {
		return minimiseWrappingInLegend;
	}
	public void setMinimiseWrappingInLegend(String minimiseWrappingInLegend) {
		this.minimiseWrappingInLegend = minimiseWrappingInLegend;
	}
	
}
