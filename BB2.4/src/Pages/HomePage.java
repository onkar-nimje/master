package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import TestNG.Log;
import Utilities.SpecialAction;

public class HomePage {

	 WebDriver driver;
	 WebElement element;
	 String pageSource= null;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openHomePage(WebDriver driver,String baseUrl,String pageUrl,String locale) throws InterruptedException {
		driver.get(baseUrl+pageUrl+"?locale="+locale);	
		LocalPopupPage.acceptlocalPopup(driver, locale);		
	}
	
	public void openHomePageWithoutLocal(WebDriver driver,String baseUrl,String pageUrl) throws InterruptedException {
		driver.get(baseUrl+pageUrl);
	}
	
	public WebElement signINLink(WebDriver driver,String local) {
		if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("en_CA")) {
			element = driver.findElement(By.linkText("Sign In"));//.click();
		} else {
			element = driver.findElement(By.linkText("Iniciar sesión"));//.click();
		}
		return element;
	}
	
	public  WebElement signOUTLink(WebDriver driver,String local) throws InterruptedException {
		element  = driver.findElement(By.xpath("//*[@id='top-header']/div[2]/a[2]/span[1]"));
		SpecialAction.waitForElementCondition(driver, element, "visible");
		Actions builder = new Actions(driver);
  		//Action act= builder.moveToElement(driver.findElement(By.xpath("//*[@id='top-header']/div[2]/a[2]/span[1]"))).build();
		Action act= builder.moveToElement(element).build();
  		act.perform();
  		Thread.sleep(2000);
  		
  		if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("en_CA")) {
			element = driver.findElement(By.linkText("Sign Out"));//.click();				
		} else {
			element = driver.findElement(By.linkText("Salir"));//.click();
		}
  		Thread.sleep(2000);
  		return element;
  		
	}
	
	public void alertWindow(WebDriver driver) throws InterruptedException {
		if (ExpectedConditions.alertIsPresent()!= null) {
  			driver.switchTo().alert().accept();
  			Thread.sleep(5000);
		}
	}
	
	public  void verificationMsg(WebDriver driver,String local) {
		if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("en_CA")) { //LOGOUT VERIFICATION CODE HERE
			driver.findElement(By.linkText("Sign In"));				
		} else {
			driver.findElement(By.linkText("Iniciar sesión"));
		}
				Reporter.log("2.User Correctly loggedOut on site");
				Log.info("2.User Correctly loggedOut on site");
	}

}
