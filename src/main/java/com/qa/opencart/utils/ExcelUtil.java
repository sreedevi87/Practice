package com.qa.opencart.utils;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.opencart.constants.AppConstants;

public class ExcelUtil {
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getDataFromSheet(String sheetName) {
		Object data[][] = null;
		FileInputStream fi;
		try {
			fi = new FileInputStream(AppConstants.TEST_DATA_FILE_PATH);
			book = WorkbookFactory.create(fi);
			sheet=book.getSheet(sheetName.trim());
			data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++)
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();	
				}
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return data;
	}
}