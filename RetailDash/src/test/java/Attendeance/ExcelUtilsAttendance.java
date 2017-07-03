package Attendeance;
import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

//import config.Constants;

import sun.util.logging.resources.logging;

public class ExcelUtilsAttendance {
	//THIS IS FOR FILE WITH EXTENSION .xls
		public static HSSFWorkbook ExcelWBook;
		public static HSSFSheet ExcelWSheet;
		public static HSSFRow Row;
		public static HSSFCell Cell;
		
		//THIS IS FOR FILE WITH EXTENSION .xlsx
		//public static XSSFWorkbook ExcelWBook;
		//public static XSSFSheet ExcelWSheet;
		//public static XSSFRow Row;
		//public static XSSFCell Cell;
	
	//connecting to excel file
	public static void setExelFile (String path) throws Exception {
		try {
			FileInputStream excelFile = new FileInputStream(path);
			ExcelWBook = new HSSFWorkbook(excelFile);
		} catch (Exception e) {
			throw (e);
		}
	}
	
	//getting Column string data
	public static String getCellStringData (int rowNum, int colNum , String sheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
			
		} catch (Exception e) {
			return "";		
		}
	}
	
	//getting column numeric data
	public static int getCellNumericData (int rowNum, int colNum, String sheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			int cellData = (int) Cell.getNumericCellValue();
			return cellData;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	//get number of row present in sheet
	public static int getRowcount (String sheetName) throws Exception {
		//System.out.println(sheetName);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			int rowCount = ExcelWSheet.getLastRowNum()+1;
			return rowCount;
		} catch (Exception e) {
			throw (e);
		}
	}
	
	//get number of column present in row
	public static int getColCount (String sheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		int colCount=0;
		try {
			Row = ExcelWSheet.getRow(0);
			colCount = Row.getPhysicalNumberOfCells();
		} catch (Exception e) {
			// TODO: handle exception
			throw (e);
		}
		return colCount;
	}
	
	//get start row number of the particular test case
	public static int getTestStartRow (String testCaseName, int colNum , String sheetName) throws Exception {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			int sheetTotalRowCount = ExcelUtilsAttendance.getRowcount(sheetName);
			for(i=0; i< sheetTotalRowCount; i++)
			{
				if(ExcelUtilsAttendance.getCellStringData(i, colNum, sheetName).equalsIgnoreCase(testCaseName)) 
					{
						break;
					}
			}
		} catch (Exception e) {
			throw (e);
		}
		return i;
	}
	
	//get number of test steps of particular test case
	public static int getTotalTestSteps (String sheetName, String testCaseID, int testCaseStart) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			for (int i = testCaseStart; i <= ExcelUtilsAttendance.getRowcount(sheetName); i++) 
			{
				if(!testCaseID.equals(ExcelUtilsAttendance.getCellStringData(i, ConstantsAttendance.Col_TestCaseID, sheetName)))
				{
					int number = i;
					return number;
				}
			}
			int numner = ExcelWSheet.getLastRowNum()+1;
			return numner;
			
		} catch (Exception e) {
			throw (e);
		}
	}
	
	//writing result in report file
	public static void setCellData (String result, int rowNum, int colNum, String sheetName, String fileName) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum , Row.RETURN_BLANK_AS_NULL);
			CellStyle style = ExcelWBook.createCellStyle();
			HSSFFont font = ExcelWBook.createFont();
	
			if(result=="PASS") {
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());  //FOR BACKGOUND COLOR
				//font.setColor(IndexedColors.BLACK.getIndex());                   //FOR TEXT COLOR
			}	
			else {
				if (result=="FAIL") {
					style.setFillForegroundColor(IndexedColors.RED.getIndex());  //FOR BACKGOUND COLOR
					//font.setColor(IndexedColors.BLACK.getIndex());                   //FOR TEXT COLOR
				}
				else {
					style.setFillForegroundColor(IndexedColors.WHITE.getIndex());  //FOR BACKGOUND COLOR
					//font.setColor(IndexedColors.BLACK.getIndex());
				}
			}
			font.setColor(IndexedColors.BLACK.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);  //FOR BACKGOUND COLOR
			style.setFont(font);                                 //FOR TEXT COLOR
			
			
			if(Cell == null )
			{
				Cell = Row.createCell(colNum);
				Cell.setCellStyle(style);
				Cell.setCellValue(result);
				
			}
			else {
				Cell.setCellStyle(style);
				Cell.setCellValue(result);			
			}
			
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ExcelWBook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
}
