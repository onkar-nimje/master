package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.SignIn_Action;

import pageObjects.Home_Page;
import utility.Constant;



// Constant variable passing to methods
public class Global_Var_TC {
  private static WebDriver driver= null;
  
  public static void main(String [] args){
	  System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    driver.get(Constant.URL);
	    Home_Page.lnk_MyAccount(driver);
	    SignIn_Action.Execute(driver, Constant.Username, Constant.Password);
	    
	    System.out.println("Login Successfully, now it is the time to Log Off buddy.");
	    Home_Page.lnk_LogOut(driver).click();
	    
	    driver.quit();
  }
}
