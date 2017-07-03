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
	public static String sRunMode; 
	public static String sData;
	public static int iData;
	public static boolean bResult;
	public static String sObjectType;
	
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
		
		/*   THIS IS FOR DIRECT SCRIPTING
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get("http://www.store.demoqa.com");
	 
	    driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	    driver.findElement(By.id("log")).sendKeys("testuser_3"); 
	    driver.findElement(By.id("pwd")).sendKeys("Test@123");
	    driver.findElement(By.id("login")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
	    driver.quit();
	    */
		
		
		//   THIS IS FOR ANTHOD METHOD OF SCRIPTING
		/*
		String sPath = "D://workspaces//Selenuim//keywordDriven//src//dataEngine//DataEngine.xls";		
		ExcelUtils.setExcelFile(sPath, "Test Steps");
		
		for(int iRow=1;iRow<=9;iRow++)
		{
			String sActionKeyword = ExcelUtils.getCellData(iRow, 3);
			
			if(sActionKeyword.equals("openBrowser"))
			{
				ActionKeywords.openBrowser();
			}
			
			else if(sActionKeyword.equals("navigate"))
			{
    			ActionKeywords.navigate();
    		}
			
    		else if(sActionKeyword.equals("click_MyAccount"))
    		{
    			ActionKeywords.click_MyAccount();
    		}
			
    		else if(sActionKeyword.equals("input_Username"))
    		{
    			ActionKeywords.input_Username();
    		}
			
    		else if(sActionKeyword.equals("input_Password"))
    		{
    			ActionKeywords.input_Password();
    		}
			
    		else if(sActionKeyword.equals("click_Login"))
    		{
    			ActionKeywords.click_Login();
    		}
			
    		else if(sActionKeyword.equals("waitFor"))
    		{
    			ActionKeywords.waitFor();
    		}
			
    		else if(sActionKeyword.equals("click_Logout"))
    		{
    			ActionKeywords.click_Logout();
    		}
			
    		else if(sActionKeyword.equals("closeBrowser"))
    		{
    			ActionKeywords.closeBrowser();
    		}
 
		}
		*/
		
		//   THIS IS FOR ANTHOD METHOD OF SCRIPTING USING JAVA REFLECTION CLASS
		
		
		
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		
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
	
		/*
		
		for(int Row=1; Row<=9; Row++)
		{
			sActionKeyword = ExcelUtils.getCellData(Row, Constants.Col_ActionKeyword,Constants.);
			sPageObject = ExcelUtils.getCellData(Row, Constants.Col_PageObject);
			
			//A new separate method is created with the name 'execute_Actions'
			//You will find this method below of the this test
			//So this statement is doing nothing but calling that piece of code to execute
		    execute_Actions();
		}
		*/
		
	}
	
	private void execute_TestCase() throws Exception
	{
		
		//This will return the total number of test cases mentioned in the Test cases sheet
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		System.out.println("total number of test cases::: "+iTotalTestCases);
		//This loop will execute number of times equal to Total number of test cases
		for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++)
		{
			bResult = true;
			//This is to get the Test case name from the Test Cases sheet
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			System.out.println("current running test case::: "+sTestCaseID);
			//This is to get the value of the Run Mode column for the current test case
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
			System.out.println(" running mode of current test case::: "+sRunMode);
			//This is the condition statement on RunMode value
			if (sRunMode.equals("Yes")){
				//Only if the value of Run Mode is 'Yes', this part of code will execute
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps); //start of the test step for the test case
				System.out.println("First row number of test step::: "+iTestStep);
				
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep); //end of the test step for the test case
				System.out.println("Last row number of test step::: "+iTestLastStep);
				
				Log.startTestCase(sTestCaseID);
				//This loop will execute number of times equal to Total number of test steps
				//Setting the value of bResult variable to 'true' before starting every test step
				bResult=true;
				int count=0;
				for (;iTestStep<iTestLastStep;iTestStep++)
				{
					count=count+1;
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
		    		//System.out.println(sActionKeyword);
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    	    //System.out.println(sPageObject);
		    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet,Constants.Sheet_TestSteps);
		    		iData = ExcelUtils.getCellNumData(iTestStep, Constants.Col_DataSet,Constants.Sheet_TestSteps);
		    		sObjectType = ExcelUtils.getCellData(iTestStep, Constants.Col_ObjectType,Constants.Sheet_TestSteps);
		    		//System.out.println("object type:::"+sObjectType);
		    		execute_Actions();
		    		System.out.println(bResult);
		    		if(bResult==false)
		    		{
						//If 'false' then store the test case result as Fail
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
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
				ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
				Log.endTestCase(sTestCaseID);				
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
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					break;
				}else
				{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ActionKeywords.closeBrowser("","",0,"");
					break;
                }
			}
		}
	}
}
