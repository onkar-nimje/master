package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class HomePage {
	WebDriver driver;
    static WebElement element = null;
    static String elementValue = null;
	public HomePage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	public void openHomePage(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public WebElement menu(WebDriver driver,WebDriverWait wait) {	
		try {
			element = null;
			elementValue = "userDropDown";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: HomePage.java - Element Method:(menu) - FindBy: id - Value: "+elementValue);
		}
		return element;
	}
	
	public WebElement menuList(WebDriver driver,WebDriverWait wait, String menuname) {
		try {
			element = null;
			elementValue = "dropdown-menu";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown-menu']")));
			WebElement menuBox = driver.findElement(By.className(elementValue));
			  List<WebElement> menuOptions = menuBox.findElements(By.tagName("li"));		  
			  for (int i = 0; i < menuOptions.size(); i++) {
				if (menuOptions.get(i).getText().equalsIgnoreCase(menuname)) {
					element = menuOptions.get(i);
					break;
				} 
			}
			  SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: HomePage.java - Element Method:(menuList) - FindBy: className - Value: "+elementValue);
		}	 
	 return element;
	}
	
	public static WebElement btnViewAllLooks(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "button";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='view-all-looks-btn']//button")));
			Thread.sleep(2000);
			List<WebElement> listAllBtn = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(listAllBtn, "View All Looks");
			Thread.sleep(2000);
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: HomePage.java - Element Method:(btnViewAllLooks) - FindBy: tagName - TagName: "+elementValue+" - ButtonText: View All Looks");
		}
		return element;
	}
	
	public static WebElement btnViewAllVideos(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//a[@id='view-all-videos-btn']//button";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
		} catch (Exception e) {
			Reporter.log("ERROR: HomePage.java - Element Method:(btnViewAllVideos) - FindBy: xpath - TagName: "+elementValue+" - ButtonText: View All Videos");
		}
		return element;
	}
	
}
