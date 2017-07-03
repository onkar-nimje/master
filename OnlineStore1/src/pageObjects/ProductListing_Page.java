package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class ProductListing_Page 
{
	
	public static class Product_1 
	{
		public static WebElement txt_Price(WebDriver driver) 
		{
			WebElement element= null;
			try {
				element=driver.findElement(By.xpath("//*[@id='default_products_page_container']/div[3]/div[2]/form/div[1]/p/span"));
			} catch (Exception e) {
				Log.error("Product 1 Sales Price is not found");
				throw(e);
			}
			return element;
		}
		
		 public static WebElement img_Product(WebDriver driver)
		 { 

			 WebElement element = null;

			 try{
	          		element= driver.findElement(By.xpath("//*[@id='imagecol_32']/a/img[1]"));
	          		Log.info("Product Image is found for Product 1");
	          	}catch (Exception e){
	          		Log.error("Product 1 Image is not found");
	          		throw(e);
	          		}
			return element;

		 }

		 public static WebElement txt_Name(WebDriver driver)
		 {

			 WebElement element = null; 

			 try{
	         		element= driver.findElement(By.xpath(".//*[@id='default_products_page_container']/div[3]/div[2]/h2"));
	         		Log.info("Product Name is found for Product 1");
	         	}catch (Exception e){
	         		Log.error("Product 1 Name is not found");
	         		throw(e);
	         		}

			 return element;

		 } 

		 public static WebElement txt_Desc(WebDriver driver)
		 {

			 WebElement element = null; 

			// Write Code to find element here

			 return element; 

		 } 	

		 public static WebElement btn_AddToCart( WebDriver driver)
		 { 

			 WebElement element = null;

			// Write Code to find element here

			 return element; 

		 } 

	}

}
