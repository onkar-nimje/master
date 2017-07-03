package executionEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import utility.ExcelUtils;
import config.Constants;



import config.ActionKeywords;

public class DriverScript {


	//public static ActionKeywords actionKeywords;
	public static Properties OR;
	public static TestFlow testFlow;
	//public static Method method[];
	
	
	public static String pageObjectFile;
	//public static boolean bResult;
	public static String sTestCaseID;
	public static String sRunModeTestCaseScenario;
	
	public DriverScript () throws Exception{
		//actionKeywords = new ActionKeywords();
		testFlow = new TestFlow();
		System.out.println("in constructor of DriverScript");
		//method = actionKeywords.getClass().getMethods();
	}
	
	public static void main(String[] args) throws Exception {
		
		pageObjectFile= Constants.Path_DataFileLocation+Constants.File_PageObject; //this is to get the page object file where we store the object and their values.
		ExcelUtils.setExelFile(pageObjectFile);		
		int totalObjectsRow = ExcelUtils.getRowcount(Constants.Sheet_ObjectNameValue); //this is to get total number of object/properties present in EXL file
		
		String Path_OR=Constants.Path_OR;
		File file = new File(Path_OR); //this is where we need to copy all object and their value to OR property file
		FileWriter filewrite = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufferWrite = new BufferedWriter(filewrite);
		for(int i=1; i<=totalObjectsRow; i++) {  //here we copied all object name and value into OR property file
			try {
				String objectNameValuePair = null;
				String ObjectSheet = Constants.Sheet_ObjectNameValue;
				String objectName= ExcelUtils.getCellStringData(i, 0, ObjectSheet);//getObjectName("Objects", i, 0);
				//System.out.println(objectName);
				String objectValue = ExcelUtils.getCellStringData(i, 1, ObjectSheet);//getObjectName("Objects", i, 1);
				//System.out.println(objectValue);
				objectNameValuePair = objectName+"="+objectValue;
				//System.out.println("objectNameValue:::"+objectNameValuePair);				
				bufferWrite.write(objectNameValuePair);
				bufferWrite.write("\r\n");				
				//System.out.println("written in OR file");				
			} catch (Exception e) {
				throw (e);
			}
			
		}
		bufferWrite.close();// here closing connection from Object name and value XLS file. Since no need to use now.
		
		FileInputStream file_OR = new FileInputStream(Path_OR);
		//Creating an Object of properties
		OR = new Properties(System.getProperties());
		//Loading all the properties from Object Repository property file in to OR object
		OR.load(file_OR);
		

	
		DOMConfigurator.configure("log4j.xml");
		
		DriverScript driverScript =  new DriverScript();
		TestFlow.execute_TestCase();
		
		
	}

	
	

}
