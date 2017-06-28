package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class VideoPage {

	WebDriver driver;
     WebElement element = null;
     List<WebElement> elementList = null;
     String elementValue = null;
    
	public VideoPage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	
	public  WebElement searchBx(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//input[@id='video-list-search-input']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(searchBx) - FindBy: xpath - value: "+elementValue+" - search box on video page");
		}
		return element;
	}
	
	public WebElement btnSearch(WebDriver driver,WebDriverWait wait) {
		element = null;
		elementValue = null;
		try {
			elementValue= "//button[@id='video-list-search-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(btnSearch) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	
	public  WebElement btnTool(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			elementValue = "//button[@class='dropdown-toggle btn btn-default']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			Thread.sleep(2000);
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(btnTool) - FindBy: xpath - Value="+elementValue);
		}
		return element;
	}
	
	public  WebElement btnOk(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//button[text()='Ok']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			Thread.sleep(2000);
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(btnOk) - FindBy: xpath - Value="+elementValue+" - Button 'Ok' to delete video");
		}
		return element;
	}
	public  WebElement lnkVideoSnippet(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(5000);
			elementValue = "//a[contains(text(),'Video Snippet')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(lnkVideoSnippet) - FindBy: xpath - Value: "+elementValue+" - LinkText: Video Snippet");
		}
		return element;
	}
	
	public  WebElement lnkDeleteVideo(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(5000);
			elementValue = "//a[contains(text(),'Delete Video')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: LookPage.java - Element Method:(lnkDeleteVideo) - FindBy: xpath - value: "+elementValue+" - LinkText: Delete Video");
		}
		return element;
	}
	
	
	
	public List<WebElement> listOfVideos(WebDriver driver,WebDriverWait wait) {
		elementList = null;
		elementValue = null;
		try {
			elementValue= "//div[@class='file-name']//a[starts-with(@id,'video-list-item-')]/div";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			elementList = driver.findElements(By.xpath(elementValue));
		} catch (Exception e) {
			Reporter.log("ERROR: VideoPage.java - Element Method:(listOfVideos) - FindBy: xpath - Value: "+elementValue);
		}	
		return elementList;
	}

}
