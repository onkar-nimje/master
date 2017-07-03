package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.Utils;

public class LogIn_Page  extends BaseClass {
 private static WebElement element = null;
 
  public LogIn_Page(WebDriver driver)
  {
	  super(driver);
	  
  }
  
  
 
  public static WebElement txtbx_UserName(WebDriver driver)
  {
    try {
    	Utils.waitForElement(driver.findElement(By.id("emailOrCPF")));
    	element= driver.findElement(By.id("emailOrCPF"));
    	Log.info("email text box found for login");
	} catch (Exception e) {
		Log.error("email text box not found for login");
		throw(e);
	}
	  return element;
  }
  
  public static WebElement txtbx_password(WebDriver driver)
  {
	  try {
		element=driver.findElement(By.id("password"));
		Log.info("password text box found for login");
	} catch (Exception e) {
		Log.error("password text box not found for login");
		throw(e);
	}
	  return element;
  }
  
  public static WebElement btn_Login()
  {
	  try {
		  element= driver.findElement(By.id("buttonEnter"));
		  Log.info("Login button found in login page");
		
	} catch (Exception e) {
		Log.error("Login button not found in login page");
	}
	  return element;
  }
}
