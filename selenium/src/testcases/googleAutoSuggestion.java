	package testcases;

	import java.util.Iterator;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.Test;

	public class googleAutoSuggestion {

		@Test
		public void test1() throws InterruptedException
		{
			//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
			WebDriver driver= new FirefoxDriver();
			driver.get("https://www.google.com");
			driver.findElement(By.id("gbqfq")).click();
			WebElement search=driver.findElement(By.id("gbqfq"));
			search.sendKeys("Onkar Nimje");
			
			Thread.sleep(3000);
			
			WebElement el1=driver.findElement(By.cssSelector("div.sbdd_b"));//select the block of auto suggestion
			List<WebElement> li=el1.findElements(By.tagName("li"));//select the li tag inside the auto suggestion block
			Iterator<WebElement> it=li.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next().getText());//print text of each li tag
			}
		
		}
		
	}


