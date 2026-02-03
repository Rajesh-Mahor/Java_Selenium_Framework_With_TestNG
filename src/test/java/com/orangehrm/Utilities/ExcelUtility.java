package com.orangehrm.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static File file;
	public static XSSFWorkbook workbook;
	public static FileInputStream fis;
	public static XSSFSheet sheet;
	
	
	public static int getRowCount(String fileName, String sheetName) throws IOException {

		file = new File(fileName);
		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		return rowCount;
	}

	public static int getCellCount(String fileName, String sheetName) throws IOException {

		file = new File(fileName);
		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int cellCount = sheet.getRow(0).getLastCellNum();
		workbook.close();
		return cellCount;
	}
	
	public static String getCellValue(String fileName, String sheetName,int rowNo, int cellNo) throws IOException {

		file = new File(fileName);
		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		String cellValue = sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
		workbook.close();
		return cellValue;
	}
	
		
}

