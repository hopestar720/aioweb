/*
 * $RCSfile: ReportUtils,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * <p>Title:ReportUtils</p>
 * <p>Description:Jasper数据导出工具类</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author zhousc
 * @since 2011
 */
public class ReportUtils 
{
	/** MS Office Word .doc(NOT Implament) */
	public final static int WORD_DOC = 2;
	/** MS Office Word .docx */
	public final static int WORD_DOCX = 1;
	/** Adobe Reader .pdf */
	public final static int PDF = 20;
	/** MS Office Excel .xlsx */
	public final static int EXCEL_XLSX = 10;
	/** MS Office Excel .xls */
	public final static int EXCEL_XLS = 11;
	
	/** WINDOWS Wordpad .rtf */
	public final static int RTF = 30;
	public final static int CSV = 31;
	public final static int TXT = 32;
	public final static int OPEN_ODS = 40;
	public final static int OPEN_ODT = 41;
	public final static int GRAPHICS_2D = 50;
	public final static int HTML = 60;
	public final static int XHTML = 61;

	/**
	 * <p>Description:导出数据报表</p>
	 * @param   templatePath
	 * @param   exportFormat
	 * @param   dataList
	 * @param   parameters
	 * @param   out
	 * @return void
	 * @version 1.0
	 * @exception JRException,IOException
	 */
	public static void reportDatas(String templatePath, int exportFormat, 
			                                             Collection<?> dataList, Map<String, Object> parameters,
			                                             OutputStream out) throws JRException, IOException 
	{
		File templateFile = new File(templatePath);
		
		if (!templateFile.isFile() || !templateFile.exists()) {
			throw new IOException("jasper template not exist");
		}
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		JasperReport jasperReport = null;
		
		if (templateFile.getName().endsWith(".jasper")) {
			jasperReport = (JasperReport) JRLoader.loadObject(templateFile);
		} else if (templateFile.getName().endsWith(".jrxml")) {
			jasperReport = JasperCompileManager.compileReport(templatePath);
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		JRExporter exporter = getExporterByFormat(exportFormat);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		exporter.exportReport();
		out.flush();
		out.close(); 	
	}

	/**
	 * <p>Description:getExporterByFormat</p>
	 * @param   exportFormat
	 * @return JRExporter
	 * @version 1.0
	 * @exception JRException
	 */
	protected static JRExporter getExporterByFormat(int exportFormat) throws JRException 
	{
		JRExporter exporter = null;
		switch (exportFormat) 
		{
			
			case WORD_DOCX: 
			{
				exporter = new JRDocxExporter();
				break;
			}
			
			case WORD_DOC: 
			{
				exporter = new JRDocxExporter();
				break;
			}
			
			case EXCEL_XLS: 
			{
				exporter = new JRXlsExporter();
				break;
			}
			
			case EXCEL_XLSX: 
			{
				exporter = new JRXlsxExporter();
				break;
			}
			
			case CSV: 
			{
				exporter = new JRCsvExporter();
				break;
			}
			
			case GRAPHICS_2D: 
			{
				exporter = new JRGraphics2DExporter();
				break;
			}
			
			case RTF: 
			{
				exporter = new JRRtfExporter();
				break;
			}
			
			case HTML: 
			{
				exporter = new JRHtmlExporter();
				break;
			}
			
			case XHTML: 
			{
				exporter = new JRXhtmlExporter();
				break;
			}
			
			case OPEN_ODS: 
			{
				exporter = new JROdsExporter();
				break;
			}
			
			case OPEN_ODT: 
			{
				exporter = new JROdtExporter();
				break;
			}
			
			case PDF: 
			{
				exporter = new JRPdfExporter();
				break;
			}
			
			case TXT: 
			{
				exporter = new JRTextExporter();
				break;
			}
			
			default: 
			{
				throw new JRException("Unsupport export format.");
			}
		}
		
		return exporter;
	}
	
	/**
	 * <p>Description:导出数据报表</p>
	 * @param templatePath
	 * @param   exportFormat
	 * @param dataList
	 * @param parameters
	 * @param pdfPath
	 * @return void
	 * @version 1.0
	 * @exception JRException,IOException
	 */
	public static void writeDatas(String templatePath, int exportFormat, Collection<?> dataList, 
			                                           Map<String, Object> parameters,String pdfPath) throws JRException, IOException 
	{
		File templateFile = new File(templatePath);
		
		if (!templateFile.isFile() || !templateFile.exists()) {
			throw new IOException("jasper template not exist");
		}
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		JasperReport jasperReport = null;
		
		if (templateFile.getName().endsWith(".jasper")) {
			jasperReport = (JasperReport) JRLoader.loadObject(templateFile);
		} else if (templateFile.getName().endsWith(".jrxml")) {
			jasperReport = JasperCompileManager.compileReport(templatePath);
		}
		
		byte[] bytes =
			JasperRunManager.runReportToPdf(jasperReport,parameters,dataSource);
		FileOutputStream out1 = new FileOutputStream(new File(pdfPath));   
		out1.write(bytes);
	}
}
