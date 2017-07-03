package automationFramework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTestCase {

	/**
	 * @param args
	 */
	
	private static WebDriver driver= null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver= new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		 
		 
		 //RADIO BUTTON and CHECKBOX
		 driver.get("http://www.toolsqa.com/automation-practice-form");
		 List<WebElement> listRadio=driver.findElements(By.name("sex"));
		 boolean tf= true;
		 if(tf==listRadio.get(0).isSelected())
		 {
			 listRadio.get(1).click();
		 }
		 
		 else
			 listRadio.get(0).click();
		 
		/*
		 driver.get("http://www.store.demoqa.com");
		 
		 //GET TITLE
		 String title= driver.getTitle();
		 int length1 = title.length();
		 System.out.println("Title: "+title);
		 System.out.println("Title Length: "+length1);
		 
		 //GET CURRENT URL
		 String url= driver.getCurrentUrl();
		 int length2 = url.length();
		 System.out.println("Current URL : "+url);
		 System.out.println("URL Length:"+length2);
		 
		 //GET PAGE SOURCE
		 String source= driver.getPageSource();
		 int length3 = source.length();
		// System.out.println("Page Source : "+source);
		 System.out.println("Source Length:"+length3);
		 
		 //SWITCH WINDOW
		 driver.get("http://www.toolsqa.com/automation-practice-switch-windows/");
		 String windowHandle=driver.getWindowHandle();
		 System.out.println("Window Handle : "+windowHandle);
		 
		 
		 
		 driver.findElement(By.id("alert")).click();
		 
		 
		 
		 Alert alert= driver.switchTo().alert();
		 Set<String> windowHandles=driver.getWindowHandles();
		 System.out.println("Window Handle : "+windowHandles);
		 alert.accept();
		 
		 
		 System.out.println("alert accepted ");
		 /*
		 driver.findElement(By.xpath("//*[@id='account']/a")).click();
		 driver.findElement(By.id("log")).sendKeys("testuser_1");
		 driver.findElement(By.id("pwd")).sendKeys("Test@123");
		 driver.findElement(By.id("login")).click();
		 System.out.println(" Login Successfully, now it is the time to Log Off buddy.");
		 driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
		 
		 driver.close();
		 driver.quit();
		 */

	}

}
