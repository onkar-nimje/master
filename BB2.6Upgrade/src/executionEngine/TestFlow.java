package executionEngine;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import config.ActionKeywords;


import config.Constants;

public class TestFlow {
	public static ActionKeywords actionKeywords;
	//public static Properties OR;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sTestFlowID;
	public static String sRunMode; 
	public static String sData;
	public static String sTransitionRow;
	public static int iData;
	public static boolean bResult;
	public static String sObjectType;
	public static String Sheet_TestSteps;
	public static String Sheet_Data;
	public static String Main_FlowFile;
	public static String FlowFile;
	public static String sActionKeyword;
	public static String sPageObject;
	public static String sPageObjectDataSheet;
	public static String mainFlowFile;
	public static String automationStartTime;
	
	public static WebDriver driver;
	

	public  TestFlow () {
		actionKeywords = new ActionKeywords(driver);		
		method = actionKeywords.getClass().getMethods();
		System.out.println("i m in constructor of TESTFLow");
		automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
		}
	
	public static void execute_TestCase() throws Exception {
		
		mainFlowFile = Constants.Path_DataFileLocation+Constants.File_TestFlowData;
		ExcelUtils.setExelFile(mainFlowFile);
		System.out.println("i m in accesing::"+mainFlowFile);
		//This will return total number of test flows mentioned in test flow sheet
		
		int iTotaltestFlows = ExcelUtils.getRowcount(Constants.Sheet_TestFlows);
		int totalflows=iTotaltestFlows-1;
		System.out.println("Total numner of flows ::"+totalflows);
		for(int iTestFlow=1; iTestFlow<=iTotaltestFlows; iTestFlow++) {
			
			//-------------
			ExcelUtils.setExelFile(mainFlowFile);
			//------------
			System.out.println("i m in accesing2::"+mainFlowFile);
			
			sTestFlowID=ExcelUtils.getCellStringData(iTestFlow, Constants.Col_FlowID,Constants.Sheet_TestFlows);
			sRunMode = ExcelUtils.getCellStringData(iTestFlow, Constants.Col_RunMode,Constants.Sheet_TestFlows);
			System.out.println("Main file::"+mainFlowFile);
			if(sRunMode.equalsIgnoreCase("Yes")) //here checking Runmode of flow eg. Login
			{
				
				
				//-------------------now connecting to TEST CASE file--------------//
				
				FlowFile = Constants.Path_DataFileLocation+sTestFlowID+".xls";
                ExcelUtils.setExelFile(FlowFile);
                System.out.println("inside particular flow file1:::"+FlowFile);
                //------------
                
                
				//This will return the total number of test scenario mentioned in the Test cases sheet
				int iTotalTestCases = ExcelUtils.getRowcount(sTestFlowID);
				System.out.println("Total number of test caes present in sheet::"+(iTotalTestCases-1));
				//This loop will execute number of times equal to Total number of test cases
				for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++)
				{
					bResult = true;
					//This is to get the Test case name from the Test Cases sheet
					sTestCaseID = ExcelUtils.getCellStringData(iTestcase, Constants.Col_TestCaseID, sTestFlowID);
				//	System.out.println("current running test case::: "+sTestCaseID);
					//This is to get the value of the Run Mode column for the current test case
					sRunMode = ExcelUtils.getCellStringData(iTestcase, Constants.Col_RunMode,sTestFlowID);
					
					//////////this is data row for test scenarios NEW CODE
					sTransitionRow = ExcelUtils.getCellStringData(iTestcase, Constants.Col_TestData,sTestFlowID);
					System.out.println("Transition::"+sTransitionRow);
					//////////////////NEW CODE
					
					
					System.out.println(" running mode of current test case::: "+sRunMode);
					//This is the condition statement on RunMode value
					
					if (sRunMode.equalsIgnoreCase("Yes")){ //here checking Runmode of each iteration/scenario eg. Login_01
						
						System.out.println("Current running test case::: "+sTestCaseID);
						//Here we get the sheet name/path from where we read steps to execute the flow
						Sheet_TestSteps=""+sTestFlowID+"_TestSteps";
						//Only if the value of Run Mode is 'Yes', this part of code will execute
						iTestStep = ExcelUtils.getTestStartRow(sTestCaseID, Constants.Col_TestCaseID, Sheet_TestSteps); //start of the test step for the test case
						System.out.println("First row number of test step::: "+iTestStep);
						
						iTestLastStep = ExcelUtils.getTotalTestSteps(Sheet_TestSteps, sTestCaseID, iTestStep); //end of the test step for the test case
						System.out.println("Last row number of test step::: "+iTestLastStep);
						
						Log.startTestCase(sTestCaseID, sTransitionRow );
						//This loop will execute number of times equal to Total number of test steps
						//Setting the value of bResult variable to 'true' before starting every test step
						bResult=true;
						int count=0;
						for (;iTestStep<iTestLastStep;iTestStep++)
						{   //this loop is for test steps
							count=count+1;
				    		sActionKeyword = ExcelUtils.getCellStringData(iTestStep, Constants.Col_ActionKeyword, Sheet_TestSteps);
				    		System.out.println("sActionKeyword::"+sActionKeyword);
				    		sPageObject = ExcelUtils.getCellStringData(iTestStep, Constants.Col_PageObject, Sheet_TestSteps);
				    	    System.out.println("sPageObject"+sPageObject);
				    		sData = ExcelUtils.getCellStringData(iTestStep, Constants.Col_DataSet, Sheet_TestSteps);
				    		iData = ExcelUtils.getCellNumericData(iTestStep, Constants.Col_DataSet, Sheet_TestSteps);
				    		sObjectType = ExcelUtils.getCellStringData(iTestStep, Constants.Col_ObjectType, Sheet_TestSteps);
				    		System.out.println("sData:"+sData);
				    		System.out.println("iData:"+iData);
				    		System.out.println("sObjectType:"+sObjectType);
				    		
				    		
				    		//////////////HERE CODE IS FOR DATA FILE
				    		if(sData.equalsIgnoreCase("DATA_FILE"))
				    		{
				    			System.out.println("THIS IS FOR input");
				    			Sheet_Data=""+sTestFlowID+"_Data";
				    			//total number of transition
				    		//	int iTotalNumberOfTransitionRow=ExcelUtils.getRowCount(Sheet_Data);
				    			
				    			//row of DATA for insertion
				    			
				    			int iTotalNumberOfFieldColumn=ExcelUtils.getColCount(Sheet_Data);
				    			System.out.println("total column::"+iTotalNumberOfFieldColumn);
				    		//	for(int i=1;i<=iTotalNumberOfTransitionRow;i++)
				    		//	{
				    				//for(int j=1;j<=iTotalNumberOffieldColumn;i++)
				    			for(int j=1;j<iTotalNumberOfFieldColumn-1;j++)
				    				{
				    					//sActionKeyword="input";
				    				    int iDataRow=ExcelUtils.getTestStartRow(sTransitionRow, 0,Sheet_Data);//this will return row number from test case data sheet in which transition name present 
				    				    sPageObjectDataSheet=ExcelUtils.getCellStringData(0,j,Sheet_Data); //here we pick up PAGE OBJECT from data sheet
				    				   // System.out.println("sPageObject:"+sPageObject);
				    				   // System.out.println("sPageObjectDataSheet:"+sPageObjectDataSheet);
				    				  //  if(sPageObject==sPageObjectDataSheet)
				    				  //  {
				    				        sPageObject=sPageObjectDataSheet;
				    				    	sData=ExcelUtils.getCellStringData(iDataRow, j, Sheet_Data);
					    					iData=ExcelUtils.getCellNumericData(iDataRow, j, Sheet_Data);
					    					System.out.println("ROW::"+iDataRow+" COLUMN::"+j);
					    					
					    					System.out.println("sData::"+sData);
					    					System.out.println("iData::"+iData);
					    					
					    					execute_Actions();
				    				 //   }
				    					
				    				}
				    				//sActionKeyword="click"; //OR sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Sheet_TestSteps);
				    				//execute_Actions();
				    				
				    		//	}
				    			
				    		}
				    		else {
				    			if (sData.equalsIgnoreCase("ExpectResult_DATA_FILE")) {
				    				System.out.println("THIS IS FOR text validation");
					    			Sheet_Data=""+sTestFlowID+"_Data";
					    			int iTotalNumberOfFieldColumn=ExcelUtils.getColCount(Sheet_Data);
					    			System.out.println("total column::"+iTotalNumberOfFieldColumn);
					    			int iDataRow=ExcelUtils.getTestStartRow(sTransitionRow, 0,Sheet_Data);//return row number from where we need to take value of transition row
					    			sData=ExcelUtils.getCellStringData(iDataRow, iTotalNumberOfFieldColumn-1, Sheet_Data);// will return expected text in string
			    					iData=ExcelUtils.getCellNumericData(iDataRow, iTotalNumberOfFieldColumn-1, Sheet_Data);// will return expected text in integer
			    					
			    					System.out.println("sData::"+sData);
			    					System.out.println("iData::"+iData);
			    					execute_Actions();
					    			
								}
				    			else
				    				execute_Actions();
							}
					    		//System.out.println("object type:::"+sObjectType);
					    		
				    		
				    		
				    		
				    		
				    		System.out.println(bResult);
				    		if(bResult==false)
				    		{
								//If 'false' then store the test case result as Fail
								ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,sTestFlowID,FlowFile);
								//End the test case in the logs
								Log.endTestCase(sTestCaseID);
								//By this break statement, execution flow will not execute any more test step of the failed test case
								break;		
				    		}
				    	}
						
						
						
						
						
						System.out.println("total number of steps exucuted:::"+count);
						System.out.println("omkar"+bResult);
						//This will only execute after the last step of the test case, if value is not 'false' at any step	
						if(bResult==true)
						{
						//Storing the result as Pass in the excel sheet
						ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,sTestFlowID,FlowFile);
						ActionKeywords.getResultantScreenshot(Constants.KEYWORD_PASS+"-"+sTestCaseID+"-"+sTransitionRow+"-", automationStartTime);
						Log.endTestCase(sTestCaseID);				
						}		
					}				
				}
				
			}
			
		}
		
		
		
	}
	
	private static void execute_Actions() throws Exception
	{
		//This is a loop which will run for the number of actions in the Action Keyword class 
		//method variable contain all the method and method.length returns the total number of methods
		
		for(int i=0;i<method.length;i++)
		{
			//This is now comparing the method name with the ActionKeyword value got from excel
			if(method[i].getName().equals(sActionKeyword))
			{
				//In case of match found, it will execute the matched method
				//method[i].invoke(actionKeywords);
				
				
				//Passing 'Page Object' name and 'Action Keyword' and 'objectType' as Arguments to this method
				method[i].invoke(actionKeywords,sPageObject,sData,iData,sObjectType);
				//Once any method is executed, this break statement will take the flow outside of for loop
				if(bResult==true)
				{ 
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Sheet_TestSteps,FlowFile);
					//ActionKeywords.getResultantScreenshot("SUCCESS"+sTestCaseID+sTransitionRow, automationStartTime);
					break;
				}else
				{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Sheet_TestSteps,FlowFile);
					ActionKeywords.getResultantScreenshot(Constants.KEYWORD_FAIL+"-"+sTestCaseID+"-"+sTransitionRow+"-",automationStartTime);
					//ActionKeywords.closeBrowser("","",0,"");
					break;
                }
			}
		}
	}
	
	
	


}
