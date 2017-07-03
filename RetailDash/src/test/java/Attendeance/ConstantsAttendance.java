package Attendeance;

public class ConstantsAttendance {
	public static final String Path_DataFileLocation = "D://workspaces//Selenuim//RetailDash//src//test//java//Attendeance//";

	public static final String Path_ScreenshotLocation = "D://workspaces//Selenuim//REIMU//test-output//Output//Screenshot//";

	public static final String File_TestFlowData = "JAN 2017.xls";

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
	
	/////////////////ATTENDANCE//////////////////////
	public static final int Row_MainHeader=6;
	public static final int Col_EmpID=0;
	public static final int Col_EmpName=1;
	public static final int Col_Date=2;
	public static final int Col_TotalWorkHours=33;
	
	
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
