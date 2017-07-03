package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.Home_Page;

import appModules.SignIn_Action;

import utility.Constant;
import utility.ExcelUtils;

public class Apache_POI_TC {
	
	public static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			 ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");	
			 System.out.println(Constant.Path_TestData + Constant.File_TestData);
			 driver = new FirefoxDriver();
			 
			 driver.get(Constant.URL);
			 
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 SignIn_Action.Execute(driver);
			// Home_Page.link_Logout(driver).click();
			 driver.quit();
			 ExcelUtils.setCellData("Pass",1,3);
			 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
      	

	}

}
