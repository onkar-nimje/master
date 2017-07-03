package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class crateAccount {
	
	@Test
	public void createAccount()
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("user name enter kar idhar");
		driver.findElement(By.id("pass")).sendKeys("PASSWORD enter kar idhar");
		try
		{driver.findElement(By.id("loginbutton")).click();
		System.out.println("try success");

		}
		catch(Exception e)
		{
			System.out.println("exception");

		}
		System.out.println("hello");
		
	}

}
