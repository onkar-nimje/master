package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class readWriteFile {

	/**
	 * @param args
	 */
	public static HSSFWorkbook ExcelWBook;
	public static HSSFSheet ExcelWSheet;
	public static HSSFRow Row;
	public static HSSFCell Cell;
	
	public static void main(String[] args) throws Exception {
		
		//readWriteFile test= new readWriteFile();
		
		
		connectFile("D://workspaces//Selenuim//BBQA2//src//config//Login.xls");
		ExcelWSheet = ExcelWBook.getSheet("Objects");
		int totalRow = ExcelWSheet.getLastRowNum();
		System.out.println("Total row::"+totalRow);
		
		File file = new File("D://workspaces//Selenuim//BBQA2//src//config//OR");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		
		
		
		//FileWriter fw = new FileWriter("D://workspaces//Selenuim//BBQA2//src//config//OR",true);
		System.out.println("connected to OR");
		BufferedWriter bw = new BufferedWriter(fw);
		//PrintWriter pw = new PrintWriter(bw);
		
		for(int i=1; i<=totalRow; i++) {
			try {
				String objectNameValue = null;
				String objectName= getObjectName("Objects", i, 0);
				System.out.println(objectName);
				String objectValue = getObjectName("Objects", i, 1);
				System.out.println(objectValue);
				objectNameValue = objectName+"="+objectValue;
				System.out.println("objectNameValue:::"+objectNameValue);
				//FileInputStream orFile= new FileInputStream("D://workspaces//Selenuim//BBQA2//src//config//OR");
				//File file = new File("D://workspaces//Selenuim//BBQA2//src//config//OR");
				
				bw.write(objectNameValue);
				bw.write("\r\n");
				//pw.println(objectNameValue);
				//pw.close();
				System.out.println("written in OR file");
				
				
			} catch (Exception e) {
				throw (e);
			}
			
		}
		bw.close();
		//pw.close();
		
		


	}
	public static void connectFile(String path) throws Exception {
		try {
			FileInputStream file = new FileInputStream(path);
			ExcelWBook = new HSSFWorkbook(file);
			System.out.println("successfully connected");
		} catch (Exception e) {
			throw (e);
		}	
	}
	
	public static String getObjectName(String sheetName, int row, int col) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		String objectName = null;
		try {
			Cell = ExcelWSheet.getRow(row).getCell(col);
			objectName = Cell.getStringCellValue();
			return objectName;
			
		} catch (Exception e) {
			throw(e);
		}
	}
	


}
