package appModules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import pageObjects.Home_Page;
import pageObjects.Login_Page;

import utility.ExcelUtils;

public class SignIn_Action {

	public static void Execute(WebDriver driver) throws Exception
	{
		String sUserName=ExcelUtils.getCellData(1,1);
		System.out.println("sUserName::"+sUserName);
		
		String sPassword=ExcelUtils.getCellData(1,2);
		System.out.println("sPassword:::"+sPassword);
		
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Home_Page.link_Myaccount(driver).click();
		System.out.println("cliked on My account");
		
	//	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Login_Page.txtbx_Username(driver).sendKeys(sUserName);
		Login_Page.txtbx_Password(driver).sendKeys(sPassword);
		Login_Page.btn_Login(driver).click();
	}
	
}
