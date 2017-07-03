package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import config.ActionKeywords;
import config.Constants;

import utility.ExcelUtils;
import utility.Log;

public class DriverScript {

	//private static WebDriver driver = null;
	
	//This is a class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	
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
	//This is reflection class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static Method method[];
	
	//Here we are instantiating a new object of class 'ActionKeywords'
	
	public DriverScript() throws  NoSuchMethodException, SecurityException
	{
		actionKeywords = new ActionKeywords();
		
		//This will load all the methods of the class 'ActionKeywords' in it.
        //It will be like array of method, use the break point here and do the watch
		method=actionKeywords.getClass().getMethods();
		
	}
	
	
	public static void main(String [] args) throws Exception
	{
				
		//   THIS IS FOR ANTHOD METHOD OF SCRIPTING USING JAVA REFLECTION CLASS
		
		
		Main_FlowFile=Constants.Path_TestData+Constants.File_TestFlowData;
		//ExcelUtils.setExcelFile(Constants.Path_TestData);
		ExcelUtils.setExcelFile(Main_FlowFile);
		
		//This is to start the Log4j logging in the test case
		DOMConfigurator.configure("log4j.xml");
		
		//Declaring String variable for storing Object Repository path
		String Path_OR=Constants.Path_OR;	
		//Creating file system object for Object Repository text/property file
		FileInputStream fs = new FileInputStream(Path_OR);
		//Creating an Object of properties
		OR = new Properties(System.getProperties());
		//Loading all the properties from Object Repository property file in to OR object
		OR.load(fs);
		
		
		DriverScript DriverScript=new DriverScript(); //this class object for calling the constructor, since we are initializing the class in constructor
		
		DriverScript.execute_TestCase();
	
		
		
	}
	
	private void execute_TestCase() throws Exception
	{
		
		//This will return total number of test flows mentioned in test flow sheet
		int iTotaltestFlows = ExcelUtils.getRowCount(Constants.Sheet_TestFlows);
		int totalflows=iTotaltestFlows-1;
		System.out.println("Total numner of flows ::"+totalflows);
		for(int iTestFlow=1;iTestFlow<=iTotaltestFlows;iTestFlow++) {
			
			//-------------
			ExcelUtils.setExcelFile(Main_FlowFile);
			//------------
			
			
			sTestFlowID=ExcelUtils.getCellData(iTestFlow, Constants.Col_FlowID,Constants.Sheet_TestFlows);
			sRunMode = ExcelUtils.getCellData(iTestFlow, Constants.Col_RunMode,Constants.Sheet_TestFlows);
			System.out.println("Main file::"+Main_FlowFile);
			if(sRunMode.equalsIgnoreCase("Yes"))
			{
				
				
				//-------------------now connecting to flow file--------------//
				//------------
				FlowFile = Constants.Path_TestData+sTestFlowID+".xls";
                ExcelUtils.setExcelFile(FlowFile);
                System.out.println("inside particular flow file1:::"+FlowFile);
                //------------
                
                
				//This will return the total number of test scenario mentioned in the Test cases sheet
				int iTotalTestCases = ExcelUtils.getRowCount(sTestFlowID);
				System.out.println("Total number of test caes present in sheet::"+iTotalTestCases);
				//This loop will execute number of times equal to Total number of test cases
				for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++)
				{
					bResult = true;
					//This is to get the Test case name from the Test Cases sheet
					sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, sTestFlowID);
				//	System.out.println("current running test case::: "+sTestCaseID);
					//This is to get the value of the Run Mode column for the current test case
					sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,sTestFlowID);
					
					//////////this is data row for test scenarios NEW CODE
					sTransitionRow = ExcelUtils.getCellData(iTestcase, Constants.Col_TestData,sTestFlowID);
					System.out.println("Transition::"+sTransitionRow);
					//////////////////NEW CODE
					
					
					System.out.println(" running mode of current test case::: "+sRunMode);
					//This is the condition statement on RunMode value
					
					if (sRunMode.equalsIgnoreCase("Yes")){
						
						System.out.println("Current running test case::: "+sTestCaseID);
						//Here we get the sheet name/path from where we read steps to execute the flow
						Sheet_TestSteps=""+sTestFlowID+"_TestSteps";
						//Only if the value of Run Mode is 'Yes', this part of code will execute
						iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Sheet_TestSteps); //start of the test step for the test case
						System.out.println("First row number of test step::: "+iTestStep);
						
						iTestLastStep = ExcelUtils.getTestStepsCount(Sheet_TestSteps, sTestCaseID, iTestStep); //end of the test step for the test case
						System.out.println("Last row number of test step::: "+iTestLastStep);
						
						Log.startTestCase(sTestCaseID);
						//This loop will execute number of times equal to Total number of test steps
						//Setting the value of bResult variable to 'true' before starting every test step
						bResult=true;
						int count=0;
						for (;iTestStep<iTestLastStep;iTestStep++)
						{   //this loop is for test steps
							count=count+1;
				    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Sheet_TestSteps);
				    		//System.out.println(sActionKeyword);
				    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Sheet_TestSteps);
				    	    //System.out.println(sPageObject);
				    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Sheet_TestSteps);
				    		iData = ExcelUtils.getCellNumData(iTestStep, Constants.Col_DataSet, Sheet_TestSteps);
				    		sObjectType = ExcelUtils.getCellData(iTestStep, Constants.Col_ObjectType, Sheet_TestSteps);
				    		
				    		
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
				    				    int iDataRow=ExcelUtils.getRowContains(sTransitionRow, 0,Sheet_Data);//this will return row number from test case data sheet in which transition name present 
				    				    sPageObject=ExcelUtils.getCellData(0,j,Sheet_Data);
				    					sData=ExcelUtils.getCellData(iDataRow, j, Sheet_Data);
				    					iData=ExcelUtils.getCellNumData(iDataRow, j, Sheet_Data);
				    					System.out.println("ROW::"+iDataRow+" COLUMN::"+j);
				    					
				    					System.out.println("sData::"+sData);
				    					System.out.println("iData::"+iData);
				    					
				    					execute_Actions();
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
					    			int iDataRow=ExcelUtils.getRowContains(sTransitionRow, 0,Sheet_Data);//return row number from where we need to take value of transition row
					    			sData=ExcelUtils.getCellData(iDataRow, iTotalNumberOfFieldColumn-1, Sheet_Data);// will return expected text in string
			    					iData=ExcelUtils.getCellNumData(iDataRow, iTotalNumberOfFieldColumn-1, Sheet_Data);// will return expected text in integer
			    					
			    					System.out.println("sData::"+sData);
			    					System.out.println("iData::"+iData);
			    					execute_Actions();
					    			
								}
				    			else
				    				execute_Actions();
							}
					    		//System.out.println("object type:::"+sObjectType);
					    		
				    		
				    		
				    			
				    		/*
				    		 if(sPageObject.equalsIgnoreCase("DATA"))
				    		{
				    			Sheet_Data=""+sTestFlowID+"_Data";
				    			//total number of transition
				    			int iTotalNumberOfTransitionRow=ExcelUtils.getRowCount(Sheet_Data);
				    			int iTotalNumberOffieldColumn=ExcelUtils.getColCount(Sheet_Data);
				    			for(int i=1;i<=iTotalNumberOfTransitionRow;i++)
				    			{
				    				for(int j=1;j<=iTotalNumberOffieldColumn;i++)
				    				{
				    					sActionKeyword="input";
				    					sPageObject=ExcelUtils.getCellData(0,j,Sheet_Data);
				    					sData=ExcelUtils.getCellData(i, j, Sheet_Data);
				    					iData=ExcelUtils.getCellNumData(i, j, Sheet_Data);
				    					
				    					execute_Actions();
				    				}
				    				sActionKeyword="click"; //OR sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Sheet_TestSteps);
				    				execute_Actions();
				    				
				    			}
				    			
				    		}
				    		 */
				    		/////////////////////////
				    		
				    		
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
						Log.endTestCase(sTestCaseID);				
						}		
					}				
				}
				
			}
			
		}
		
		
		
	}
	
	
	
	
	    //This method contains the code to perform some action
		//As it is completely different set of logic, which revolves around the action only,
		//It makes sense to keep it separate from the main driver script
		//This is to execute test step (Action)
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
					break;
				}else
				{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Sheet_TestSteps,FlowFile);
					ActionKeywords.closeBrowser("","",0,"");
					break;
                }
			}
		}
	}
}
