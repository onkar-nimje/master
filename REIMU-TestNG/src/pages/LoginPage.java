package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utilities.SpecialAction;


public class LoginPage {

	WebDriver driver;
    WebElement element = null;
    String elementValue = null;
	 public LoginPage(WebDriver driver) {
		 this.driver = driver;
	 }
	 
	 public void openLoginPage(WebDriver driver,String url) {
		 driver.get(url);
	 }
	 
	 public WebElement email(WebDriver driver) {
		 try {
			 element = null;
			 elementValue = "loginInput";
			 element = driver.findElement(By.id(elementValue));
			 SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LoginPage.java - Element Method:(email) - FindBy: id - Value: "+elementValue);
		}	
		 return element;
	 }
	 
	 public WebElement password(WebDriver driver) {
		 try {
			 element = null;
			 elementValue = "passwordInput";
			 element = driver.findElement(By.id(elementValue));
			 SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LoginPage.java - Element Method:(password) - FindBy: id - Value: "+elementValue);
		}	
		 return element;
	 }

	 public WebElement btnLogin(WebDriver driver) {
		 try {
			 element = null;
			 elementValue = "button";
			 List<WebElement> btnList = driver.findElements(By.tagName(elementValue));
			 element = SpecialAction.getElementByText(btnList, "Login");
			 SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LoginPage.java - Element Method:(btnLogin) - FindBy: id - Value: "+elementValue);
		}
		
		 return element;
	 }
	 
	 public WebElement btnSignUp(WebDriver driver) {
		 try {
			 element = null;
			 elementValue = "button";
			 List<WebElement> btnList = driver.findElements(By.tagName(elementValue));
			 element = SpecialAction.getElementByText(btnList, "Sign Up");
			 SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LoginPage.java - Element Method:(btnSignUp) - FindBy: tagName - Value: "+elementValue);
		}	
		 return element;
	 }
}
