package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import TestNG.Log;

public class OrderConfirmationPage {

	WebDriver driver;
	String sourceCode = null;
	//Boolean pass = false;
	WebElement element = null;
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public  Boolean verifyOrderNumberMsg(WebDriver driver,String msg) {
		Boolean pass = false;
		sourceCode= driver.getPageSource();
		if (sourceCode.contains(msg)) {
			Reporter.log("1.Test Case pass");
			Log.info("1.Test Case pass");
			pass = true;
		} else {
			Reporter.log("1.Test Case FAIL");
			Log.info("1.Test Case FAIL");
			pass = false;
		}
		return pass;
	}
	
	public  Boolean verifyPrinterFriendlyVersionMsg(WebDriver driver,String msg) {
		Boolean pass = false;
		sourceCode= driver.getPageSource();
		if (sourceCode.contains(msg)) {
			Reporter.log("Required msg Found on Confirmation page");
			pass = true;
		} else {
			Reporter.log("Required msg NOT Found on Confirmation page");
			pass = false;
		}
		return pass;
	}

	public String getOrderNumber(WebDriver driver) {
		String orderNumber = null;
		orderNumber = driver.findElement(By.id("orderNumber")).getText();
		System.out.println("ORDER NUMBER::"+orderNumber);
		return orderNumber;
	}

	public  Boolean verifyMsgText(WebDriver driver,String msg) {
		Boolean pass = false;
		sourceCode= driver.getPageSource();
		if (sourceCode.contains(msg)) {
			//Reporter.log("Required msg Found on Confirmation page");
			pass = true;
		} else {
			//Reporter.log("Required msg NOT Found on Confirmation page");
			pass = false;
		}
		return pass;
	}

}
