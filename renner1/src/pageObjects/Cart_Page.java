package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class Cart_Page extends BaseClass {

	private static WebElement mainElement = null;
	public Cart_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static WebElement addToCart(WebDriver driver)
	{
		try {
			mainElement=driver.findElement(By.id("buyFinal"));
			Log.info("BUY FINAL button found on cart page");
		} catch (Exception e) {
			Log.info("BUY FINAL button not found on cart page");
			throw(e);
		}
		return mainElement;
		
	}
	
	

}
