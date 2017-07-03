package appModules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pageObjects.ProductListing_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class ProductSelect_Action {
	
	public static void SelectProduct(int RowNumber, WebDriver driver) throws Exception
	{
		try {
			
			if("Product 1".equals(ExcelUtils.getCellData(RowNumber, Constant.Col_ProductNumber)))
			{
				
				//ProductListing_Page.Product1.AddToCart();
			//	Utils.waitForElement(driver.findElement(By.id("534372001ProductUrl")));
			//	((JavascriptExecutor)driver).executeScript("document.getElementById('534372001ProductUrl').click();");
			//	ProductListing_Page.Product1.moreDetail(driver);
				
				//we are using direct url of the product to add to cart BECAUSE durring automation testing we are able to test only those product whose property we already configure in use in other pages also.
				//If we use the dynamic product from product listing page then it may possible that we will get error of element not found on some other pages if we uses parameter of that product.
				
				driver.get("http://www.lojasrenner.com.br/p/bermuda-masculina-em-sarja-534967232-534967267");
				Log.info("clicked on more detail link from product listing page");
			}
			
		} catch (Exception e) {
			throw(e);
		}
	}

}
