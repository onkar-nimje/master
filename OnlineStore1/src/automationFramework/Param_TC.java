package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.Home_Page;
import appModule.SignIn_Action;

public class Param_TC {
private static WebDriver driver= null;
	
	public static void main(String [] args)
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    driver.get("http://www.store.demoqa.com");
	    
	 // Pass the Arguments (Username and Password) to this method
	    SignIn_Action.Execute(driver,"testuser_1","Test@123");
	    
	    System.out.println(" Login Successfully, now it is the time to Log Off buddy.");
	    Home_Page.lnk_LogOut(driver).click();
	    
	    driver.quit();
	}

}
