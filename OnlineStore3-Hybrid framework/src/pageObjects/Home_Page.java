package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//public class Home_Page extends BaseClass {
public class Home_Page{
	private static WebElement element =null;
	
	public static WebElement link_Myaccount(WebDriver driver) throws Exception
	{
		try {
			//System.out.println("finding myaccount link");
			//element=driver.findElement(By.id("account"));
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			element=driver.findElement(By.xpath("//*[@id='account']/a/span[1]"));
			//element=driver.findElement(By.xpath("//*[@id='account']/a"));
			System.out.println("found myaccount link");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("no my account link found");
		}
		return element;
	}
	
	public static WebElement link_Logout(WebDriver driver) throws Exception
	{
		try {
			element = driver.findElement(By.id("account_logout"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("no logout link found");
		}
		return element;
	}
	
	
}
