package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.Utils;

public class ProductListing_Page extends BaseClass{
  private static WebElement element;
  
  public ProductListing_Page(WebDriver driver)
  {
	  super(driver);
  }
  
  public static class Product1{
	  public static WebElement price(){
		  element=null;
		  try {
			element=driver.findElement(By.xpath("//*[@id='shelfNewPrice']"));
			Log.info("Product Price is found for Product 1");
		} catch (Exception e) {
			Log.error("Product Price is not found fro Product 1");
			throw(e);
		}
		  return element;
	  }
	  
	  public static void moreDetail(WebDriver driver)
	  {
		  WebElement mianClass=driver.findElement(By.className("productShelfCont"));
		  Utils.waitForElement(mianClass);
		  WebElement subClass=mianClass.findElement(By.tagName("ul"));
		  List<WebElement> element=subClass.findElements(By.tagName("li"));
		  Utils.hiddenElementClick(element, 0);
	  }
	  
	 
  }
}
