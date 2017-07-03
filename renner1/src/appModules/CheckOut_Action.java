package appModules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.AddressSelection_Page;
import pageObjects.Cart_Page;
import pageObjects.LogIn_Page;

import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class CheckOut_Action {
	public static void Execute(int TestRowNumber,WebDriver driver) throws Exception
	{
		Cart_Page.addToCart(driver).click();
		Log.info("clicked on BUY FINAL");
		
		Thread.sleep(20000);
		//login action for logout user
		//entering username
		String UserName=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Email);
		LogIn_Page.txtbx_UserName(driver).sendKeys(UserName);
		Log.info(UserName+" entered into the box in login page");
		
		//entering password
		String Password=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Password);
		LogIn_Page.txtbx_password(driver).sendKeys(Password);
		Log.info(Password+"  entered into the box in login page");
		
		
		//clicking on login button
		LogIn_Page.btn_Login().click();
		Log.info("Click action is performed on Submit button");
		
		Reporter.log("login for checkout successfull");
		
		//address selection from address page
		Thread.sleep(30000);
		AddressSelection_Page.selectAddress(driver);
		Log.info("address selected from address selection page");
		
		Thread.sleep(10000);
		
		//Selecting shipping method
		String shipMethod=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Shipping_Method);
		AddressSelection_Page.shipMethod(driver,shipMethod);
		Log.info("Clicked on shipping methode from address selection page");
		
		Thread.sleep(10000);
		
		//payment detail
		String paymentType=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Payment_Type);
		AddressSelection_Page.paymentMethod(driver, paymentType);
		Log.info("Payment method selected from address page");
		
		
		
	}

}
