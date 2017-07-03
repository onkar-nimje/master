package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWbook;
	private static HSSFCell Cell; 
	private static HSSFRow Row;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void setExcelFile(String Path, String SheetName) throws Exception
	{
		try{
			FileInputStream ExcelFile= new FileInputStream(Path);
			ExcelWbook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWbook.getSheet(SheetName);
			
		}
		catch(Exception e){
			throw(e);
		}
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception
	{
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData=Cell.getStringCellValue();
			return CellData;
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
	
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception
	{
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum,Row.RETURN_BLANK_AS_NULL);
			
			if(Cell == null)
			{
				Cell = Row.createCell(ColNum);
			    Cell.setCellValue(Result);
			}
			
			else
			{
				Cell.setCellValue(Result);
			}
				
			
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			
		} catch (Exception e) {
			throw(e);
		}
	}

}
