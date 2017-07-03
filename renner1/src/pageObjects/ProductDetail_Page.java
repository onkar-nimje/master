package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.Utils;

public class ProductDetail_Page extends BaseClass {

	private static WebElement element= null;
	
	public ProductDetail_Page(WebDriver driver) {
		super(driver);		
	}

	public static void selectProductColor(WebDriver driver) throws Exception
	{
		try {
			//Utils.waitForElement(driver.findElement(By.xpath("//*[@id='color534373637']/img")));
			Thread.sleep(20000);
			WebElement main=driver.findElement(By.className("skuColorsList"));
			List<WebElement> list=main.findElements(By.tagName("label"));	
			Utils.hiddenElementClick(list,2);
			Log.info("product color found on PDP");
		} catch (Exception e) {
			Log.info("product color not found on PDP");
			throw(e);
		}
		
	}
	
	public static void selectProductSize(WebDriver driver,String size) throws Exception
	{
		try {
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			WebElement main=driver.findElement(By.className("skuSizeList"));
			List<WebElement> list=main.findElements(By.tagName("label"));
			System.out.println(list.size());
			
			if(size.equals("P"))
			Utils.hiddenElementClick(list,0);
			
			if(size.equals("M"))
			Utils.hiddenElementClick(list,3);
			
			if(size.equals("G"))
			Utils.hiddenElementClick(list,2);
			
			if(size.equals("GG"))
			Utils.hiddenElementClick(list,3);
			
			Log.info("product size found on PDP");
			
		} catch (Exception e) {
			Log.info("product size not found on PDP");
			throw(e);
		}
	}
	public static WebElement productPrice(WebDriver driver) throws Exception
	{
		try {
			Thread.sleep(10000);
			Utils.waitForElement(driver.findElement(By.xpath("//*[@id='skuLname-Price']")));
			element=driver.findElement(By.xpath("//*[@id='skuLname-Price']"));
			Log.info("product price found on PDP");
		} catch (Exception e) {
			Log.info("Product price not found on PDP");
			throw(e);
		}
		
		return element;
	}
	
	public static WebElement addToCartButton(WebDriver driver) throws Exception
	{
		try {
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Utils.waitForElement(driver.findElement(By.xpath("//*[@id='linkAddToCart']")));
			element=driver.findElement(By.id("linkAddToCart"));
			Log.info("add to cart button found on PDP");
		} catch (Exception e) {
			Log.info("add to cart button not found on PDP");
			throw(e);
		}
		
		return element;
	}
}
