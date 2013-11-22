/*
 * %W% %E%
 *
 * Copyright (c) 2012, My Team and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.views.Excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * TODO
 * 
 * @author Admin
 * @version 1.0.0
 * @since 2012-11-30
 */
public class ExcelView extends AbstractExcelView {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.web.servlet.view.document.AbstractExcelView#
	 * buildExcelDocument(java.util.Map,
	 * org.apache.poi.hssf.usermodel.HSSFWorkbook,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HSSFSheet sheet;
		HSSFRow sheetRow;
		HSSFCell cell;

		// Go to the first sheet
		// getSheetAt: only if wb is created from an existing document
		// sheet = wb.getSheetAt(0);
		sheet = wb.createSheet("Spring");
		sheet.setDefaultColumnWidth((short) 12);

		// write a text at A1
		cell = getCell(sheet, 0, 0);
		setText(cell, "Spring-Excel test");

		List words = (List) model.get("wordList");
		for (int i = 0; i < words.size(); i++) {
			cell = getCell(sheet, 2 + i, 0);
			setText(cell, (String) words.get(i));

		}
	}

}
