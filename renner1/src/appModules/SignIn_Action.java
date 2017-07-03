package appModules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.Home_Page;
import pageObjects.LogIn_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class SignIn_Action {

	public static void Execute(int TestRowNumber, WebDriver driver) throws Exception
	{
		Home_Page.lnk_MyAccountLogin().click();
		Log.info("Login to my account is clicked");
		
		
		String UserName=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Email);
		LogIn_Page.txtbx_UserName(driver).sendKeys(UserName);
		Log.info(UserName+" entered into the box in login page");
		
		String Password=ExcelUtils.getCellData(TestRowNumber, Constant.Col_Password);
		LogIn_Page.txtbx_password(driver).sendKeys(Password);
		Log.info(Password+"  entered into the box in login page");
		
		
		
		LogIn_Page.btn_Login().click();
		Log.info("Click action is performed on Submit button");
		
		Reporter.log("SignIn Action is done successfully");
	}
}
