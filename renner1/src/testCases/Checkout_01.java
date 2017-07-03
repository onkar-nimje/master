package testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.AddToCart_Action;
import appModules.CategorySelect_Action;
import appModules.CheckOut_Action;
import appModules.ProductSelect_Action;
import appModules.SignIn_Action;
import appModules.SignOut_Action;

import pageObjects.BaseClass;

import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class Checkout_01 {
	
	public WebDriver driver;
	private String TestCaseName;
	private int RowNumber;
		
		@BeforeMethod
		public void beforeMethod() throws Exception
		{
			DOMConfigurator.configure("log4j.xml");
		    
			TestCaseName=this.toString();
			TestCaseName=Utils.getTestCaseName(TestCaseName);
			Log.startTestCase(TestCaseName);
			ExcelUtils.setExcelFile(Constant.Path_TestData+Constant.File_TestData, "Sheet1");			
			RowNumber=ExcelUtils.getRowContains(TestCaseName, Constant.Col_TestCaseName);
			driver=Utils.OpenBrowser(RowNumber);
			
			
			new BaseClass(driver);
		}
		
		
		@Test
		public void main() throws Exception
		{
			//1.user going to signIN
			//SignIn_Action.Execute(RowNumber,driver);
			
		    //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			//2.user going to SignOUT
			//SignOut_Action.Execute(RowNumber);
			
			
			//3.category & subcategory select action from Home page
			CategorySelect_Action.SelectCategory(RowNumber,driver);
		//	Thread.sleep(20000);
			//4.product select action from PLP
			ProductSelect_Action.SelectProduct(RowNumber,driver);
		//	Thread.sleep(20000);
			//5. add product to cart
			AddToCart_Action.Execute(RowNumber, driver);
			Thread.sleep(20000);
			//checkout action
			CheckOut_Action.Execute(RowNumber,driver);
			
			
		}
		
		@AfterMethod
		public void afterMethod()
		{
			
		}

	

}
