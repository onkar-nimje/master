package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.SpecialAction;

public class PDPPage {

	static WebDriver driver;

	public PDPPage(WebDriver driver) {
		//PDPPage.driver = driver;
		LocalPopupPage localPopup = new LocalPopupPage(driver);
		
	}
	
	public void openPDPPagewithLocal(WebDriver driver,String baseUrl,String pageUrl,String local) throws InterruptedException {
		driver.get(baseUrl+pageUrl+"?locale="+local);
		LocalPopupPage.acceptlocalPopup(driver, local);
	}
	public void openPDPPagewithoutLocal(WebDriver driver,String baseUrl,String pageUrl) throws InterruptedException {
		driver.get(baseUrl+pageUrl);
	}
	
	public Select selectProductType(WebDriver driver) throws InterruptedException {
		//Thread.sleep(3000);
		WebElement element = driver.findElement(By.id("order-type"));
		return SpecialAction.selectList(element);
		//Select productType = new Select(element);
		//productType.selectByIndex(productTypeIndex);
	}
	
	public Select selectProductQty(WebDriver driver) throws InterruptedException {
		//Thread.sleep(2000);
		WebElement element = driver.findElement(By.id("quantity"));
		return SpecialAction.selectList(element);
		//Select productQty = new Select();
		//productQty.selectByIndex(qty);
	}
	
	public WebElement buttonCHECKOUT(WebDriver driver) throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement element = driver.findElement(By.xpath("//*[@id='add1']/ul/li[3]/button"));
		return element;
	}
	
	public WebElement miniCart(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.className("mini-cart-text"));
		return element;
		//.click();
	}
	
	public WebElement miniCartCheckoutLink(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[@id='miniCartData']/div/div/div[4]/a[2]"));
		return element;
		//.click();
	}
	
	

}
