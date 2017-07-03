package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//public class Login_Page extends BaseClass {
public class Login_Page{

	public static WebElement element= null;
	/*
	public Login_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	*/
	
	public static WebElement txtbx_Username(WebDriver driver) throws Exception
	{
		try {
			 element = driver.findElement(By.id("log"));
			 System.out.println("user name field found");
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("user name field not found");
		}
		return element;
	}
	
	public static WebElement txtbx_Password(WebDriver driver) throws Exception
	{
		try {
			element = driver.findElement(By.id("pwd"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}
	
	public static WebElement btn_Login(WebDriver driver) throws Exception
	{
		try {
			element = driver.findElement(By.id("login"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

}
