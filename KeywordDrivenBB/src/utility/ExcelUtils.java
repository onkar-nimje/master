package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import config.Constants;

public class ExcelUtils {
	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFRow Row;
	private static HSSFCell Cell;
	
	
	 //This method is to set the File path and to open the Excel file
    //Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path) throws Exception
	{
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			
		} catch (Exception e) {
			 throw (e);
		}
		
		
	}

	
	//This method is to read the test data from the Excel cell
    //In this we are passing parameters/arguments as Row Num and Col Num
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception
	{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {		
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);	
			String CellData ;
			CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
		
		
	}
	
	public static int getCellNumData(int RowNum, int ColNum, String SheetName) throws Exception
	{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {		
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);	
			int CellData;		
			CellData =  (int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			return 0;
		}	
	}
	
	
	//This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName)
	{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			int number = ExcelWSheet.getLastRowNum()+1;
			//System.out.println("total row of the sheet:::" +number);
			return number;
		} catch (Exception e) {
			throw(e);
		}
	}
	
	//////////////////
	public static int getColCount(String SheetName)
	{
		ExcelWSheet =ExcelWBook.getSheet(SheetName);
		int number=0;
		try {
			Row = ExcelWSheet.getRow(0);
			 number=Row.getPhysicalNumberOfCells();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return number;
	}
	//////////////////
	
	//This method is to get the start Row number of the test case
	//This methods takes three arguments(Test Case name , Column Number & Sheet name)
	public static int getRowContains(String sTestCaseName, int ColNum,String SheetName) throws Exception
	{
		int i;
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for(i=0; i < rowCount; i++)
			{
				if(ExcelUtils.getCellData(i, ColNum, SheetName).equalsIgnoreCase(sTestCaseName))
				{
					break;
				}
			}
		} catch (Exception e) {
			throw(e);
		}
		
		return i;
	}
	
	//This method is to get the count of the test steps of test case
	//This method takes three arguments (Sheet name, Test Case Id & Test case row number)
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception
	{
		for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++)
		{
			if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
			{
				int number =i;
				System.out.println("return of number INSIDE loop:::"+number);
				return number;
				
			}	
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number=ExcelWSheet.getLastRowNum()+1;
		System.out.println("return of number OUTSIDE loop:::"+number);
		return number;
	}
	
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName, String FileName) throws Exception
	{
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum,  Row.RETURN_BLANK_AS_NULL);
		//	Cell = Row.getCell(ColNum);
			if(Cell == null)
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			}
			else
			{
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			//FileOutputStream fileout = new FileOutputStream(Constants.Path_TestData);
			//FileOutputStream fileout = new FileOutputStream(Constants.Path_TestData+Constants.File_TestFlowData);
			//FileOutputStream fileout = new FileOutputStream(Constants.Path_TestData+SheetName+".xls");
			FileOutputStream fileout = new FileOutputStream(FileName);
			ExcelWBook.write(fileout);
			//fileout.flush();
			fileout.close();
			//ExcelWBook = new HSSFWorkbook(new FileInputStream(Constants.Path_TestData));
			//ExcelWBook = new HSSFWorkbook(new FileInputStream(Constants.Path_TestData+Constants.File_TestFlowData));
			//ExcelWBook = new HSSFWorkbook(new FileInputStream(Constants.Path_TestData+SheetName+".xls"));
			ExcelWBook = new HSSFWorkbook(new FileInputStream(FileName));
		} catch (Exception e) {
			throw(e);
		}
	}
	
}
