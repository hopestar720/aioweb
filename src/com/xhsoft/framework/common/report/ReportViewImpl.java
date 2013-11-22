package com.xhsoft.framework.common.report;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportViewImpl 
{

	@SuppressWarnings("unchecked")
	public static void showPDFReport(String reportPath, Collection reportData, Map rptParameters,HttpServletResponse response)
	{
		/** 判断路径是否正确*/
		File reportFile = new File(reportPath);
		if (!reportFile.exists()) {
			/**项目中需要错误处理部分*/
			return;
		}

		/** 数据集做为数据源*/
		JRDataSource dataSource = new JRBeanCollectionDataSource(reportData);

		try {
			/**加载编译好的模板*/
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			
			byte[] bytes =
				JasperRunManager.runReportToPdf(jasperReport,rptParameters,dataSource);
			
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close(); 	
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void showPDFToStream(String reportPath, Collection reportData, Map rptParameters, OutputStream outStream)
	{
		/** 判断路径是否正确*/
		File reportFile = new File(reportPath);
		if (!reportFile.exists()) {
			return;
		}
		/** 数据集做为数据源*/
		JRDataSource dataSource = new JRBeanCollectionDataSource(reportData);

		/** 加载编译好的模板*/
		JasperReport jasperReport;
		try {
			jasperReport = JasperCompileManager.compileReport(reportPath);
			
			/** 数据集填充数据*/
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, rptParameters, dataSource);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
			exporter.exportReport();
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public void showExcelReport(String reportPath, List reportData, Map rptParameters)
	{
		/**判断路径是否正确*/
		File reportFile = new File(reportPath);
		if (!reportFile.exists()) {
			/** 项目中需要错误处理部分*/
			return;
		}

		/** 数据集做为数据源*/
		JRDataSource dataSource = new JRBeanCollectionDataSource(reportData);

		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

			
			JasperPrint jasperList = JasperFillManager.fillReport(jasperReport, rptParameters, dataSource);
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			/** 生成Excel文件类*/
			JRXlsExporter exporter = new JRXlsExporter(); 

		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}
}
