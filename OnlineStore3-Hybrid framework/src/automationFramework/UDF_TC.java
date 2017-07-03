package automationFramework;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import appModules.SignIn_Action;

import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class UDF_TC {
  
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	
  @BeforeMethod
  public void beforeMethod() throws Exception 
  {
	  DOMConfigurator.configure("log4j.xml");
	  sTestCaseName=this.toString();
	 // sTestCaseName=Utils.getTestCaseName(sTestCaseName);
	  sTestCaseName = Utils.getTestCaseName(this.toString());
	  Log.statTestCase(sTestCaseName);
	  
	  ExcelUtils.setExcelFile(Constant.Path_TestData+Constant.File_TestData,"Sheet1");
	  System.out.println("file found");
	  iTestCaseRow=Utils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
	 driver= Utils.openBrowser(iTestCaseRow);
  }

  @Test
  public void main() throws Exception
  {
	  SignIn_Action.Execute(driver);
	  Log.info("Login Successfully, now it is the time to Log Off buddy.");	  
  }
  
  @AfterMethod
  public void afterMethod() throws Exception
  {
	  driver.quit();
  }

}
