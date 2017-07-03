package utility;

import java.io.FileInputStream;

import org.apache.bcel.classfile.Constant;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import config.Constants;

public class ExcelUtils {
	
	private static HSSFWorkbook ExcelWBook;
	private static HSSFSheet ExcelWSheet;
	private static HSSFRow Row;
	private static HSSFCell Cell;

	public static void setExcelFile(String Path) throws Exception
	{
		FileInputStream ExcelFile=new FileInputStream(Path);
		ExcelWBook = new HSSFWorkbook(ExcelFile);
		
	}
	
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception
	{
		ExcelWSheet= ExcelWBook.getSheet(SheetName);
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData= Cell.getStringCellValue();
			return CellData;
		}
		catch (Exception e){
            return"";
          }		
	}
	
	//This method is to get the row count used of the excel sheet
    public static int getRowCount(String SheetName){
    ExcelWSheet = ExcelWBook.getSheet(SheetName);
    int number=ExcelWSheet.getLastRowNum()+1;
    return number;
    }

    
  //This method is to get the Row number of the test case
    //This methods takes three arguments(Test Case name , Column Number & Sheet name)
            public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
            int i; 
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int rowCount = ExcelUtils.getRowCount(SheetName);
            for (i=0 ; i<rowCount; i++){
            if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
            break;
            }
            }
            return i;
            }
            
       public static int  getTestStepsCount(String SheetName, String sTestCaseID, int iTestcaseStart) throws Exception
            {
            	for(int i=iTestcaseStart; i<=ExcelUtils.getRowCount(SheetName); i++)
            	{
            		if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
            		{
            		int number = i;
                    return number;
            		}
                }
                    
                 ExcelWSheet = ExcelWBook.getSheet(SheetName);
                    int number=ExcelWSheet.getLastRowNum()+1;
                    return number;
             }
}
