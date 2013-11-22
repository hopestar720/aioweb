/**
 * Copyright (c) 2012, xhsoft and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>Title: ExcelHandle.java</p> 
 * <p>Description: Excel文件处理工具类 依赖包poi/poi-ooxml</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-26
 */
public class ExcelHandle {
	private String fileName;
	private String fileType;

	private static final String EXCEL_FORMAT_03 = "xls";
	private static final String EXCEL_FORMAT_07 = "xlsx";
	private static final String ILLEGAL_FORMAT = "E0001";

	private HSSFWorkbook xls;
	private XSSFWorkbook xlsx;

	/**
	 * 功能描述：通过文件名构造对象
	 * @param fileName
	 */
	public ExcelHandle(String fileName) {
		this.fileName = fileName;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			fileType = "";
			e.printStackTrace();
			return;
		} catch (Exception e) {
			fileType = "";
			e.printStackTrace();
			return;
		}
		
		fileType = getFormat(fileName);
		
		if(fileType == "" ||fileType == null){
			return;
		}

		try {
			if (fileType.equalsIgnoreCase(EXCEL_FORMAT_03)) {
				setXls(new HSSFWorkbook(inStream));
			} else if (fileType.equalsIgnoreCase(EXCEL_FORMAT_07)) {
				setXlsx(new XSSFWorkbook(inStream));
			} else {
				fileType = ILLEGAL_FORMAT;
				// throws CustomFileException(ExcelHandle.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：获取文件的格式
	 * @params {:,:}
	 * @return String
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	private String getFormat(String fileName) {
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (fileType.equalsIgnoreCase(EXCEL_FORMAT_03)) {
			return EXCEL_FORMAT_03;
		} else if (fileType.equalsIgnoreCase(EXCEL_FORMAT_07)) {
			return EXCEL_FORMAT_07;
		} else {
			return ILLEGAL_FORMAT;
		}
	}

	/**
	 * 功能描述：读取支持的Excel文件内容
	 * @params {:,:}
	 * @return void
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	public void readExcel() {
		if (fileType == "") {
			System.out.println("文件未找到！");
			return;
		} else if (fileType == ILLEGAL_FORMAT) {
			System.out.println("格式不合法，非Excel文件！");
			return;
		}
		if (fileType.equalsIgnoreCase(EXCEL_FORMAT_03)) {
			readXLS();
		} else if (fileType.equalsIgnoreCase(EXCEL_FORMAT_07)) {
			readXLSX();
		} else {
			// throws CustomFileException(ExcelHandle.class);
		}

	}

	/**
	 * 功能描述：读取后缀为.xls文件的内容
	 * @params {:,:}
	 * @return void
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	private void readXLS() {
		for (int sheetNum = 0; sheetNum < xls.getNumberOfSheets(); sheetNum++) {
			HSSFSheet xls_sheet = xls.getSheetAt(sheetNum);
			if (xls_sheet == null) {
				continue;
			}
			// 遍历Sheet的第一行
			for (int rowNum = 0; rowNum <= xls_sheet.getLastRowNum(); rowNum++) {
				HSSFRow xls_row = xls_sheet.getRow(rowNum);
				if (xls_row == null) {
					continue;
				}
				// 遍历当前行的第一个单元格
				for (int cellNum = 0; cellNum < xls_row.getLastCellNum(); cellNum++) {
					HSSFCell xls_cell = xls_row.getCell(cellNum);
					if (xls_cell == null) {
						continue;
					}
					System.out.print("   " + getCellValue(xls_cell));
				}
				System.out.println();
			}
		}
	}

	/**
	 * 功能描述：读取后缀为.xlsx文件的内容
	 * @params {:,:}
	 * @return void
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	private void readXLSX() {
		Iterator<XSSFSheet> sheets = xlsx.iterator();
		//迭代遍历所有Sheet
		while (sheets.hasNext()) {
			XSSFSheet sheet = sheets.next();
			Iterator<Row> rows = sheet.rowIterator();
			//迭代遍历当前Sheet中所有row
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				//迭代遍历当前row中的所有cell
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					System.out.print("  "+getCellValue(cell));
				}
				System.out.println();
			}
		}
	}
	
	public void writeXLS(){}

	/**
	 * 功能描述：获取后缀为.xls文件单元格中的值
	 * @params {:,:}
	 * @return String
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	private String getCellValue(HSSFCell xls_cell) {
		String value = "";

		switch (xls_cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(xls_cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			String.valueOf(xls_cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			value = String.valueOf(xls_cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = String.valueOf(xls_cell.getStringCellValue());
			break;
		default:
			break;
		}

		return value;
	}
	
	/**
	 * 功能描述：读取后缀为.xlsx文件单元格的值
	 * @params {:,:}
	 * @return String
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	private String getCellValue(XSSFCell xls_cell) {
		String value = "";

		switch (xls_cell.getCellType()) {
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(xls_cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			String.valueOf(xls_cell.getCellFormula());
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			value = String.valueOf(xls_cell.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_STRING:
			value = String.valueOf(xls_cell.getStringCellValue());
			break;
		default:
			break;
		}

		return value;
	}
	
	public void setDocInfo(HSSFWorkbook xls){
		xls.createInformationProperties();	//必须执行此语句
		SummaryInformation si = xls.getSummaryInformation();
		//si.setTitle(title);
		//si.setSubject(subject);
		
		//si.setComments(comments);
		
		//si.setAuthor(author);
		//si.setApplicationName(appName);
		
	}
	
	public void setDocInfo(XSSFWorkbook xlsx){
		POIXMLProperties.CoreProperties props = xlsx.getProperties().getCoreProperties();
		
		//props.setTitle(title);
		//props.setSubjectProperty(subject);
		//props.setCategory(category);
		//props.setDescription(desc);
		
		//props.setCreator(author);
		
		
	}

	public HSSFWorkbook getXls() {
		return xls;
	}

	public void setXls(HSSFWorkbook xls) {
		this.xls = xls;
	}

	public XSSFWorkbook getXlsx() {
		return xlsx;
	}

	public void setXlsx(XSSFWorkbook xlsx) {
		this.xlsx = xlsx;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}

}
