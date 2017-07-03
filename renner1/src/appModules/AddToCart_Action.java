package appModules;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.ProductDetail_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;

public class AddToCart_Action {

	public static void Execute(int testRowNumber, WebDriver driver) throws Exception
	{
		//selecting product color
		ProductDetail_Page.selectProductColor(driver);
		Log.info("product colour clicked from PDP");
		
		//selecting product size
		String size=ExcelUtils.getCellData(testRowNumber,Constant.Col_Size);
		System.out.println(size);
		ProductDetail_Page.selectProductSize(driver,size);
		//((JavascriptExecutor)driver.executeScript(document.getElementById('')));
		Log.info("product size clicked from PDP");
		
		ProductDetail_Page.addToCartButton(driver).click();
		Log.info("clicked on add to cart from PDP");
		
		Reporter.log("Product added to the cart successfully");
	}
}
