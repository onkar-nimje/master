import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class selenium {

	WebDriver driver;
	@Test //making home page
	public void test1()
	{
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("browser.startup.homepage","https://www.facebook.com");
		driver = new FirefoxDriver(profile);
	}
	
	@Test //adding addON 
	public void test2() throws IOException
	{
		FirefoxProfile profile=new FirefoxProfile();
		String path="C:\\Documents and Settings\\onkar.nimje.IBMPEERS\\Desktop\\firebug-2.0.6-fx.xpi";
		profile.addExtension(new File(path));
		driver=new FirefoxDriver(profile);
	}
	
	
	
	
	@Test
	public void test4()
	{

		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();	    
		driver.get("https://bugzilla.mozilla.org/query.cgi?query_format=advanced");
		
		WebElement ele1=driver.findElement(By.xpath("//*[@id='product']"));
		List<WebElement> list1=ele1.findElements(By.tagName("option"));
		Actions builder=new Actions(driver);
		Action act=builder.keyDown(Keys.SHIFT)
		.click(list1.get(6))
		.click(list1.get(7))
		.build();
		act.perform();
		
		WebElement ele2=driver.findElement(By.xpath("//*[@id='component']"));
		List<WebElement> list2=ele2.findElements(By.tagName("option"));
		System.out.println(list2.size());
	}
	
	@Test
	public void test5()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();	    
		driver.get("https://bugzilla.mozilla.org/query.cgi?query_format=advanced");
		WebElement ele1=driver.findElement(By.xpath("//*[@id='product']"));
		List<WebElement> list1=ele1.findElements(By.tagName("option"));
		Actions builder=new Actions(driver);
		Action act=builder.keyDown(Keys.SHIFT)
		.click(list1.get(1))
		.build();
		act.perform();
		
		WebElement ele2=driver.findElement(By.xpath("//*[@id='component']"));
		List<WebElement> list2=ele2.findElements(By.tagName("option"));
		for(WebElement el:list2)
		{
			if(el.getText().equalsIgnoreCase("Facebook Application"))
			{
				System.out.println(el.getText());
				el.click();
				break;
			}
			
		}
		
	}
	
	@Test
	public void test6()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();	    
		driver.get("https://bugzilla.mozilla.org/query.cgi?query_format=advanced");
		
		WebElement el1=driver.findElement(By.xpath("//*[@id='classification']"));
		List<WebElement> li1=el1.findElements(By.tagName("option"));
		li1.get(1).click();
		
		WebElement el2=driver.findElement(By.xpath("//*[@id='product']"));
		List<WebElement> li2=el2.findElements(By.tagName("option"));
		li2.get(2).click();
		
		WebElement el3=driver.findElement(By.xpath("//*[@id='component']"));
		List<WebElement> li3=el3.findElements(By.tagName("option"));
		li3.get(3).click();
		
		WebElement el4=driver.findElement(By.xpath("//*[@id='bug_status']"));
		List<WebElement> li4=el4.findElements(By.tagName("option"));
		li4.get(4).click();
		
		WebElement el5=driver.findElement(By.xpath("//*[@id='resolution']"));
		List<WebElement> li5=el5.findElements(By.tagName("option"));
		li5.get(5).click();
		
		
	}
	
	
	@Test
	public void test7() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();	    
		driver.get("https://bugzilla.mozilla.org/query.cgi?query_format=advanced");		
		driver.findElement(By.xpath("//*[@id='tab_instant']")).click();
		WebElement ele1=driver.findElement(By.xpath("//*[@id='product']"));
		ele1.click();
		List<WebElement> li1=ele1.findElements(By.tagName("optgroup"));
		for(WebElement el: li1)
		{
			if(el.getText().equalsIgnoreCase("Firefox"))
			{
				el.click();
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id='content']")).sendKeys("addons");
		Thread.sleep(3000);
		String mainWindow=driver.getWindowHandle();
		WebElement link1=driver.findElement(By.linkText("631431"));
		link1.click();
		for(String childwindow: driver.getWindowHandles())
		{
			driver.switchTo().window(childwindow);
			Thread.sleep(10000);	
		}
		System.out.println("child");
		System.out.println(driver.getCurrentUrl());
		WebElement link2=driver.findElement(By.linkText("Bug 631431"));
		System.out.println(link2.getText());
		
		driver.close();
		driver.switchTo().window(mainWindow);
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.linkText("462674")).click();
		
		
	}
	
	@Test
	public void test8()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();	    
		driver.get("https://bugzilla.mozilla.org/query.cgi");	
		driver.findElement(By.linkText("Log In")).click();
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys("omnimje@gmail.com");
		driver.findElement(By.id("Bugzilla_password_top")).sendKeys("jawahar");
		driver.findElement(By.id("Bugzilla_remember_top")).click();
		driver.findElement(By.id("log_in_top")).click();
		WebElement msg=driver.findElement(By.id("error_msg"));
		System.out.println(msg.getText());
		if(msg.getText().equals("The username or password you entered is not valid."))
			System.out.println("wrong user & pass");
	}
	
	@Test
	public void test9() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();
	    
		driver.get("https://bugzilla.mozilla.org/query.cgi?query_format=advanced");
		String [] link= new String[] {"contains all of the strings","contains any of the strings","contains the string", "contains the string (exact case)","contains all of the words","contains any of the words","matches regular expression"
				,"does not match regular expression"};
		
		for(int i=0;i<link.length;i++)
		{
			WebElement ele1=driver.findElement(By.xpath("//*[@id='summary_field']/select"));
			ele1.click();
			List<WebElement> list=ele1.findElements(By.tagName("option"));
			if(link[i].equals(list.get(i).getText()))
				{
					list.get(i).click();
					driver.findElement(By.xpath("//*[@id='short_desc']")).clear();
					driver.findElement(By.xpath("//*[@id='short_desc']")).sendKeys("addons");
					driver.findElement(By.xpath("//*[@id='Search_top']")).click();
					driver.navigate().back();
					Thread.sleep(5000);
				}
		}
	}
	
	@Test(invocationCount=10, threadPoolSize=3)
	public void test10()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");	
	    driver=new ChromeDriver();
	    System.out.println(Thread.currentThread().getId());
	    driver.get("https://www.google.co.in");
	   // driver.close();
	    driver.quit();
	}
	
}
