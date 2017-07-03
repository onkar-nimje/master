package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import TestNG.Log;
import Utilities.SpecialAction;

public class LoginPage {

	 WebDriver driver;
	 WebElement element;
	 String pageSource= null;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
	}
	
	public  void openLoginPageWithLocal(WebDriver driver,String baseUrl,String locale) throws InterruptedException {
		driver.get(baseUrl+"login"+"?locale="+locale);
		LocalPopupPage.acceptlocalPopup(driver, locale);	
	}
	
	public  void openLoginPage(WebDriver driver,String baseUrl,String local) {
		driver.get(baseUrl+"login");
		SpecialAction.waitForElementCondition(driver, buttonSIGNIN(driver, local), "visible");
	}
	
	public WebElement email(WebDriver driver) {
		element = driver.findElement(By.id("email"));
		return element;
	}
	
	public WebElement password(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}
	

	
	public  WebElement buttonSIGNIN(WebDriver driver,String local) {
		List<WebElement> inputButton= driver.findElements(By.tagName("input"));
		String buttonName =null;
		for (int i = 0; i < inputButton.size(); i++) {
			buttonName = inputButton.get(i).getAttribute("value");
			if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("en_CA")) {
				if (buttonName.equalsIgnoreCase("Sign In")) {
					element = inputButton.get(i);//.click();
					break;
				}						
			} else {
				if (buttonName.equalsIgnoreCase("Ingresar")) {
					element = inputButton.get(i);//.click();
					break;
				}
			}
		}
		return element;
	}
	
	public  void verificationMsg(WebDriver driver,String msg) {
		pageSource= driver.getPageSource();
		if (pageSource.contains(msg)) {
			Reporter.log("1.Test Case pass");
			Log.info("1.Test Case pass");
		} else {
			Reporter.log("1.Test Case FAIL");
			Log.info("1.Test Case FAIL");
		}
	}

}
