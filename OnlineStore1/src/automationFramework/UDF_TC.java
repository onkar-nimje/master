package automationFramework;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hdf.extractor.Utils;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import pageObjects.Home_Page;
import appModule.SignIn_Action;

import utility.Constant;
import utility.ExcelUtils;
import utility.Log;

public class UDF_TC {
	
	public WebDriver driver;	 
    private String sTestCaseName; 
    private int iTestCaseRow;

	@BeforeMethod
	 public void beforeMethod() throws Exception 
	 {		 
        DOMConfigurator.configure("log4j.xml");
       sTestCaseName= this.toString();
       sTestCaseName= utility.Utils.getTestCaseName(this.toString());
      
        Log.startTestCase(sTestCaseName);
        
        ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
        iTestCaseRow=ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
        driver=utility.Utils.openBrowser(iTestCaseRow);
   	 }
	
	@Test
	public void main()
	{
		 SignIn_Action.Execute(driver);
	     System.out.println("Login Successfully, now it is the time to Log Off buddy.");	    
	     Home_Page.lnk_LogOut(driver).click(); 
	     System.out.println("logout successfull");
	     Log.info("Click action is perfomred on Log Out link");
	     
	     
	}
	
	@AfterMethod
	public void afterMethod()
	{
		driver.quit();
	}
}
