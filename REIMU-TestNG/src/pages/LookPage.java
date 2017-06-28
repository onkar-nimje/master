package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class LookPage {

	WebDriver driver;
     WebElement element = null;
     List<WebElement> elementList = null;
     String elementValue = null;
    
	public LookPage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	public  WebElement btnTool(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			elementValue = "2";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			Thread.sleep(2000);
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(btnTool) - FindBy: ID - Value="+elementValue);
		}
		return element;
	}
	
	
	
	public  WebElement searchBox(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//input[@id='look-list-search-input']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(searchBox) - FindBy: xpath - value: "+elementValue+" - search box on look page");
		}
		return element;
	}
	
	public  WebElement lnkLookSnippet(WebDriver driver) {
		try {
			Thread.sleep(5000);
			elementValue = "a";
			List<WebElement> listAlllnk = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(listAlllnk, "Look Snippet");
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(lnkLookSnippet) - FindBy: tagName - TagName: "+elementValue+" - LinkText: Look Snippet");
		}
		return element;
	}
	
	public  WebElement lnkDeleteLook(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			elementValue = "//a[contains(text(),'Delete Look')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			//List<WebElement> listAlllnk = driver.findElements(By.tagName(elementValue));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(lnkDeleteLook) - FindBy: xpath - TagName: "+elementValue+" - LinkText: Delete Look");
		}
		return element;
	}
	

	public  WebElement lnkLookName(WebDriver driver, WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			elementValue = "//*[@id='look-list-item-0-title']/div";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(lnkLookName) - FindBy: xpath - value: "+elementValue+" THIS IS LOOK NAME");
		}
		return element;
	}
	
	public WebElement btnSearch(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue= "//*[@id='look-list-search-btn']//span";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(btnSearch) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	
	public WebElement btnOKDeleteLook(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue= "//button[@id='look-delete-modal-ok-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(btnOKDeleteLook) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	
	public WebElement btnCancelDeleteLook(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue= "//button[@id='look-delete-modal-cancel-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(btnCancelDeleteLook) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	public WebElement btnClose(WebDriver driver) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "button" ;
			List<WebElement> btnList = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(btnList, "Close");
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSave) - FindBy: tagName - Value: "+elementValue);
		}	
		return element;
	}
	public List<WebElement> listOfLook(WebDriver driver,WebDriverWait wait) {
		elementList = null;
		elementValue = null;
		try {
			elementValue= "//div[@class='file-name']//a[starts-with(@id,'look-list-item-')]/div";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			elementList = driver.findElements(By.xpath(elementValue));
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(listOfLook) - FindBy: xpath - Value: "+elementValue);
		}	
		return elementList;
	}

}
