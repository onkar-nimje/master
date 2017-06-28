package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	//static WebDriver driver;
	
	public Browser(WebDriver driver) {
		//Browser.driver= driver;
	}
	
	public WebDriver firefox(WebDriver driver) {
		//ProfilesIni pf = new ProfilesIni();
		//FirefoxProfile fir = pf.getProfile("Selenium");
		
		FirefoxProfile geoDisabled = new FirefoxProfile();
		geoDisabled.setPreference("geo.enabled", false);
		geoDisabled.setPreference("geo.provider.use_corelocation", false);
		geoDisabled.setPreference("geo.prompt.testing", false);
		geoDisabled.setPreference("geo.prompt.testing.allow", false);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, geoDisabled);
		
		
		 return driver = new FirefoxDriver(geoDisabled);
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
