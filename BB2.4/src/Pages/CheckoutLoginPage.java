package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.SpecialAction;

public class CheckoutLoginPage {

	 WebDriver driver;
	 WebElement element;
	
	public CheckoutLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openCheckoutLoginPage(WebDriver driver,String baseUrl,String pageUrl) throws InterruptedException {
		driver.get(baseUrl+pageUrl);
	}

	public WebElement beginCheckout(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='page-content']/div[2]/div[3]/div/div/a/input"));
		SpecialAction.waitForElementCondition(driver, element, "visible");
		return element;
	}
	
	public WebElement emailID(WebDriver driver) {
		element = driver.findElement(By.id("email"));
		return element;
	}
	
	public WebElement pasword(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}
	
	public WebElement btnSignIn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='loginForm']/input[4]"));
		SpecialAction.waitForElementCondition(driver, element, "visible");
		return element;
	}
}
