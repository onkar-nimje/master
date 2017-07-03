package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.Utils;

public class AddressSelection_Page extends BaseClass{
	private static WebElement element = null;

	public AddressSelection_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static void selectAddress(WebDriver driver)
	{
		try {
			WebElement mainElement=driver.findElement(By.id("adress-list"));
			List<WebElement> list=mainElement.findElements(By.tagName("li"));
			Utils.hiddenElementClick(list, 0);
			Log.info(" Address selection button found on address selection page");
		} catch (Exception e) {
			Log.info(" Address selection button not found on address selection page");
			throw(e);
		}
		
	}
	
	
		
	public static void shipMethod(WebDriver driver, String  shipMethod)
	{
		WebElement mainElement=driver.findElement(By.className("shipSelect"));
		List<WebElement> list=mainElement.findElements(By.tagName("li"));
		
		if(shipMethod.equals("Normal"))
		{
			Utils.hiddenElementClick(list, 0);	
		}
			
		if(shipMethod.equals("Schedule"))
		{
			Utils.hiddenElementClick(list,1);
		}
			
	}
	
	public static void paymentMethod(WebDriver driver, String payMethod)
	{
		try {
			WebElement main=driver.findElement(By.id("paymentContainer"));
			WebElement secondary=main.findElement(By.id("selectedPayment"));
		    List<WebElement> list=secondary.findElements(By.tagName("div"));
		    
		    if(payMethod.equals("payCardRenner"))
		    {
		    	Utils.hiddenElementClick(list, 0);
		    	Log.info("Payment Method CardRenner found on address page");
		    }	
		    if(payMethod.equals("payMyCard"))
		    {
		    	Utils.hiddenElementClick(list, 1);
		    	Log.info("Payment Method payMyCard found on address page");
		    }
		    if(payMethod.equals("payCreditCard"))
		    {
		    	Utils.hiddenElementClick(list, 2);
		    	Log.info("Payment Method payCreditCard found on address page");
		    }	
		    if(payMethod.equals("payBoleto"))
		    {
		    	Utils.hiddenElementClick(list, 3);
		    	Log.info("Payment Method  payBoleto found on address page");
		    }
		   
		} catch (Exception e) {
			Log.info("Payment Method not found on address page");
			throw(e);
		}
	}
		
		
	
	

}
