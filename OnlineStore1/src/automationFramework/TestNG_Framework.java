package automationFramework;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Home_Page;
import appModule.SignIn_Action;

import utility.Constant;
import utility.ExcelUtils;
import utility.Log;

public class TestNG_Framework {
	public WebDriver driver;
	
	@BeforeClass
	public void beforeMethod() throws Exception
	{
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("Selenium_Test_001");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		Log.info(" Excel sheet opened");
	
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		Log.info("New driver instantiated");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		Log.info("Implicit wait applied on the driver for 10 seconds");
     
		driver.get(Constant.URL);
		Log.info("Web application launched");
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
	
	@AfterClass
	public void afterMethod() throws Exception
	{
		driver.quit();
	}

}
