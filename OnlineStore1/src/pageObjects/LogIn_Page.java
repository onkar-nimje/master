package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class LogIn_Page {
	 private static WebElement element = null;
	 
	    public static WebElement txtbx_UserName(WebDriver driver){
	 
	         element = driver.findElement(By.id("log"));
	         Log.info("Username text box found");
	      //   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         return element;
	 
	         }
	 
	     public static WebElement txtbx_Password(WebDriver driver){
	 
	         element = driver.findElement(By.id("pwd"));
	         Log.info("Password text box found");
	       //  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         return element;
	 
	         }
	 
	     public static WebElement btn_LogIn(WebDriver driver){
	 
	         element = driver.findElement(By.id("login"));
	         driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	         Log.info("Submit button found");
	         return element;
	 
	         }
}
