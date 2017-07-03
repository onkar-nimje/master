package appModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import pageObjects.Home_Page;
import pageObjects.LogIn_Page;
import utility.ExcelUtils;
import utility.Log;

//Modular Driven Framework

public class SignIn_Action {
	/*  this is when we call this method and provide the data in parameter
	public static void  Execute(WebDriver driver, String username, String password)
	{
		Home_Page.lnk_MyAccount(driver).click();
		LogIn_Page.txtbx_UserName(driver).sendKeys(username);
	    LogIn_Page.txtbx_Password(driver).sendKeys(password);
	    LogIn_Page.btn_LogIn(driver).click();
	}

   */
	
	// this is when we call this method and provide the data from datafile
	public static void Execute(WebDriver driver)
	{
		String username=ExcelUtils.getCellData(1,1);
		Log.info("Username picked from Excel is ");
		
		String password=ExcelUtils.getCellData(1,2);
		Log.info("Password picked from Excel is " );
		
		Home_Page.lnk_MyAccount(driver).click();
		Log.info("Click action performed on My Account link");
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		
		LogIn_Page.txtbx_UserName(driver).sendKeys(username);
		Log.info("Username entered in the Username text box");
		
	    LogIn_Page.txtbx_Password(driver).sendKeys(password);
	    Log.info("Password entered in the Password text box");
	    
	    LogIn_Page.btn_LogIn(driver).click();   
	    Log.info("Click action performed on Submit button");
	}
}
