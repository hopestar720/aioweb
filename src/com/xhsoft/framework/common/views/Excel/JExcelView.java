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

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

/**
 * TODO
 *
 * @author Admin
 * @version 1.0.0
 * @since   2012-11-30
 */
public class JExcelView extends AbstractJExcelView {

	protected void buildExcelDocument(Map model,
	        WritableWorkbook wb,
	        HttpServletRequest request,
	        HttpServletResponse response)
	    throws Exception {
				
	        WritableSheet sheet = wb.createSheet("Spring", 0);

	        sheet.addCell(new Label(0, 0, "Spring-Excel test"));
			
	        List words = (List) model.get("wordList");
	        for (int i = 0; i < words.size(); i++) {
	            sheet.addCell(new Label(2+i, 0, (String) words.get(i)));
	        }
	    }

}
