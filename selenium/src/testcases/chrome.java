package testcases;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class chrome {

	@Test
	public void test1() throws InterruptedException
	{
		//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		WebDriver driver= new FirefoxDriver();
		driver.get("https://www.google.com");
		//String url=driver.getTitle();
		//System.out.println(url);
		driver.findElement(By.id("gbqfq")).click();
		WebElement search=driver.findElement(By.id("gbqfq"));
		//Thread.sleep(3000);
		search.sendKeys("Omkar");
		//List<WebElement> el1=driver.findElements(By.xpath("//html/body/div[2]/div[2]/div[1]"));
		List<WebElement> el1=driver.findElements(By.id("gbqfq"));
		System.out.println("omkar"+el1.get(0).getText());
		Iterator<WebElement> it=el1.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next().getText());
		}
		System.out.println("hello");
		//driver.close();
		
	}
	
}
