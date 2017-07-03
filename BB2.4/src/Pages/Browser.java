package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {

	//static WebDriver driver;
	
	public Browser(WebDriver driver) {
		//Browser.driver= driver;
	}
	
	public WebDriver firefox(WebDriver driver) {
		 return driver = new FirefoxDriver();
	}
	
	public WebDriver chrome(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		return driver = new ChromeDriver();
	}

	public WebDriver internetExplorer(WebDriver driver) {
		System.setProperty("webdriver.ie.driver", "D:\\workspaces\\IEDriverServer.exe");
		return driver = new InternetExplorerDriver();
	}

}
