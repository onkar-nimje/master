package utility;
import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import config.Constants;

import sun.util.logging.resources.logging;

public class ExcelUtils {
	public static HSSFWorkbook ExcelWBook;
	public static HSSFSheet ExcelWSheet;
	public static HSSFRow Row;
	public static HSSFCell Cell;
	
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
		System.out.println(sheetName);
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
			int sheetTotalRowCount = ExcelUtils.getRowcount(sheetName);
			for(i=0; i< sheetTotalRowCount; i++)
			{
				if(ExcelUtils.getCellStringData(i, colNum, sheetName).equalsIgnoreCase(testCaseName)) 
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
			for (int i = testCaseStart; i <= ExcelUtils.getRowcount(sheetName); i++) 
			{
				if(!testCaseID.equals(ExcelUtils.getCellStringData(i, Constants.Col_TestCaseID, sheetName)))
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
			if(Cell == null )
			{
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			}
			else {
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
