package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {
	
	//读取Excel文件内容
	public static void read(InputStream inStream){
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int sheet_num = wb.getNumberOfSheets();
		if(sheet_num <= 0){
			return;
		}
		for(int i = 0;i<sheet_num;i++){
			Sheet sheet = wb.getSheetAt(i);
		}
	}
	//判断Excel版本
	//读取Excel模板
	//判断单元格数据类型
	//写入Excel文件
	//设置单元格样式
	//设置Excel标题

}
