package assertionProg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Registration {
	private WebDriver driver;
	
	public Registration(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public AccountLoginPage login()
	{
		
		driver.findElement(By.id("email")).sendKeys("onkar.nimje@objectedge.com");
		driver.findElement(By.id("pass")).sendKeys("jawaharnagar");
		driver.findElement(By.id("loginbutton")).click();
		return new AccountLoginPage(driver);
	}

}
