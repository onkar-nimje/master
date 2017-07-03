package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ActionKeywords {

	public static WebDriver driver;
	 
	public static void openBrowser(String object){		
		driver=new FirefoxDriver();
		}
 
	public static void navigate(String object){	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		}
 
	public static void click_MyAccount(String object){
		driver.findElement(By.xpath(".//*[@id='account']/a")).click();
		}
	
	public static void click(String object)
	{
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
 
	public static void input_Username(String object){
		driver.findElement(By.id("log")).sendKeys(Constants.UserName); 
		}
 
	public static void input_Password(String object){
		driver.findElement(By.id("pwd")).sendKeys(Constants.Password);
		}
 
	public static void click_Login(String object){
		driver.findElement(By.id("login")).click();
		}
 
	public static void waitFor(String object) throws Exception{
		Thread.sleep(5000);
		}
 
	public static void click_Logout(String object){
		driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
		}
 
	public static void closeBrowser(String object){
			driver.quit();
		}
}
