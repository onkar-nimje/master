package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class AddVideoPage {

	WebDriver driver;
     static WebElement element = null;
     List<WebElement> elementList = null;
     static String elementValue = null;
    
	public AddVideoPage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	public void openVideoUpdatePage(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		//Thread.sleep(10000);
	}
	
	public WebElement bxVideoFileNamefield(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-filename']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(txtVideoFileNamefield) - FindBy: xpath - Value: "+elementValue+" - This is Video Name field");
		}
		return element;
	}
	
	public WebElement bxVideoUrl(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-url']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(bxVideoUrl) - FindBy: xpath - Value: "+elementValue+" - This is Video URL field");
		}
		return element;
	}
	public WebElement bxKeywords(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-keywords']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(bxKeywords) - FindBy: xpath - Value: "+elementValue+" - This is Keywords field");
		}
		return element;
	}
	
	public WebElement lnkAnchorat0Position(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//a[contains(text(),'Anchor at 0 Seconds')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(lnkAnchorat0Position) - FindBy: xpath - Value: "+elementValue+" - This is Anchor at 0 Seconds link");
		}
		return element;
	}
	
	public WebElement bxAnchorName(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-title']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(bxAnchorName) - FindBy: xpath - Value: "+elementValue+" - This is 0th anchor name  field");
		}
		return element;
	}
	
	public WebElement bxTitleImage(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-image']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(bxTitleImage) - FindBy: xpath - Value: "+elementValue+" - This is Title Image  field");
		}
		return element;
	}
	
	public WebElement bxVideoDescription(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-description']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(bxVideoDescription) - FindBy: xpath - Value: "+elementValue+" - This is Video Description  field");
		}
		return element;
	}
	
	public WebElement btnSaveChanges(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='new-video-btn-submit']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: AddVideoPage.java - Element Method:(btnSaveChanges) - FindBy: xpath - Value: "+elementValue+" - This is Button 'Save Sanges'");
		}
		return element;
	}
	
	

}
