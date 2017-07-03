package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class Home_Page {
		
		 private static WebElement element= null;
		 
		 public static WebElement lnk_MyAccount(WebDriver driver)
		 {
			 
			    element = driver.findElement(By.id("account"));
			    Log.info("My Account link element found");
			    System.out.println("omkar1");
			    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    return element;
			 
	    }
			 
		 public static WebElement lnk_LogOut(WebDriver driver)
		 {
			 
			    element = driver.findElement(By.id("account_logout"));
			    Log.info("Log Out link element found");
			    System.out.println("omkar2");
			    return element;
			 
	    }

	

}
