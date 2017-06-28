package config;

public class Constants {
	//public static final String URL = "http://www-ultimateresetuat.beachbody.com/shop/us/b/ultimate-reset";
	public static final String Path_OR = "D://workspaces//Selenuim//REIMU//src//config//OR";
	//public static final String Path_PageObject = "D://workspaces//Selenuim//BB2.6Upgrade//src//dataEngine//PageObjectValuePair.xls";
	//public static final String Path_TestData = "D://workspaces//Selenuim//KeywordDrivenBB//src//dataEngine//DataEngine.xls";
	public static final String Path_DataFileLocation = "D://workspaces//Selenuim//REIMU//src//dataEngine//";
	
	public static final String Path_ScreenshotLocation = "D://workspaces//Selenuim//REIMU//test-output//Output//Screenshot//";

	public static final String File_TestFlowData = "DataEngine.xls";

	public static final String File_PageObject = "PageObjectValuePair.xls";
 
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_PageObject = 3;
	public static final int Col_PageObjectName = 0;
	public static final int Col_PageObjectValue = 0;
	public static final int Col_ActionKeyword =4 ;
	public static final int Col_RunMode = 2 ;
	public static final int Col_ObjectType=7;
	public static final int Col_ErrorMsg=8;
	public static final int Col_TotalTestCaseRun=1;
	public static final int Col_TotalTestCasePass=2;
	public static final int Col_TotalTestCaseFail=3;
	public static final int Col_TotalRunTime=4;
	public static final int Col_RunDate=0;
	
	
	//List of Transition(data row) for test cases
	public static final int Col_TestData=4;
	
	
	public static final int Col_Result = 3 ;
	public static final int Col_DataSet = 5;
	public static final int Col_TestStepResult =6 ;
	public static final int Col_FlowID = 0;
 
	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	public static final String Sheet_TestFlows = "Flows";
	public static final String Sheet_ObjectNameValue = "Objects";

	
	
	
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";

	

}
