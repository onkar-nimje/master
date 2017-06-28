package example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewTest {
	WebDriver driver;
	
	@BeforeTest
	  public void beforeTest() throws MalformedURLException {
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		//driver=new ChromeDriver();
		
		/*
		//FOR SELENIUM GRID
		String nodeURL = "http://192.168.169.172:5566/wd/hub";
		//String nodeURL = "http://192.168.169.183:5566/wd/hub";
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		//capabilities.setBrowserName("firefox");
		capabilities.setPlatform(Platform.VISTA);
		driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
		*/
		
	  }
  @Test
  public void f() {
	  driver.get("https://www.google.com");
  }
  

  @AfterTest
  public void afterTest() {
	 // driver.quit();
  }

}
