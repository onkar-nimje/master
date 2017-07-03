package testcases;




import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class singleSelectDropDown {
	WebDriver driver;
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	    driver= new ChromeDriver();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	@Test
    public void t1()
	{
		driver.get("https://code.google.com/p/selenium/");
	  //  WebElement ele1=driver.findElement(By.id("projects-dropdown"));
	 //	ListIterator<WebElement> ele1=(ListIterator<WebElement>) driver.findElement(By.id("projects-dropdown"));
		List<WebElement> ele1=(List<WebElement>) driver.findElement(By.id("projects-dropdown"));
		
		System.out.println(ele1.size());
		
	  
	}
}
