package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class clearMyTrip {
	

	static WebDriver driver;
	WebDriverWait wait ;
	public clearMyTrip() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 120);
	}
	
	public  void searchFlight() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.id("OneWay")).click();
		driver.findElement(By.id("FromTag")).sendKeys("Mumbai");
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");
		driver.findElement(By.id("DepartDate")).click();
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[4]/a")).click();
		driver.findElement(By.id("Adults")).click();
		Select adult = new Select(driver.findElement(By.id("Adults")));
		adult.selectByIndex(0);
		driver.findElement(By.id("Childrens")).click();
		Select childrens = new Select(driver.findElement(By.id("Childrens")));
		childrens.selectByIndex(0);
		driver.findElement(By.id("Childrens")).click();
		Select children = new Select(driver.findElement(By.id("Childrens")));
		children.selectByIndex(0);
		driver.findElement(By.id("SearchBtn")).click();		
	}
	
	public  void searchResult() throws InterruptedException{	
		Thread.sleep(5000);
		List<WebElement> bookList2 = driver.findElements(By.xpath("//*[@id='flightForm']//button[@class='booking']"));		
		bookList2.get(0).click();
	}
	public void flightDetail() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.id("insurance_confirm")).click();
		driver.findElement(By.id("itineraryBtn")).click();
	}
	public void email() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.id("username")).sendKeys("omnimj1e@gmail.com");
		driver.findElement(By.id("LoginContinueBtn_1")).click();
	}
	public void travellers() throws InterruptedException{
		Thread.sleep(5000);
		WebElement adulttitle = driver.findElement(By.id("AdultTitle1"));
		adulttitle.click();
		List<WebElement> titleList = adulttitle.findElements(By.tagName("option"));
		Actions builder = new Actions(driver);
		Action act = builder.moveToElement(titleList.get(1)).doubleClick().build();
		act.perform();
		driver.findElement(By.id("AdultFname1")).sendKeys("Onkar");
		driver.findElement(By.id("AdultLname1")).sendKeys("Nimje");
		driver.findElement(By.id("mobileNumber")).sendKeys("8657202534");
		driver.findElement(By.id("travellerBtn")).click();
		
	}
	
	public static void main(String[] args) throws Exception {
		clearMyTrip clrT = new clearMyTrip();
		
		driver.get("https://www.cleartrip.com/");
		
		clrT.searchFlight();
		clrT.searchResult();
		clrT.flightDetail();
		clrT.email();
		clrT.travellers();
		

	}

}
