import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class ScrollUpDown {
	private static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "com.amazon.mShop.android.shopping-5.2.3-502030-minAPI9.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping"); //amazon

		capabilities.setCapability("appActivity", "com.amazon.mShop.home.web.MShopWebGatewayActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		//driver.get("https://www.google.com");
		Thread.sleep(10000);
		driver.findElementByName("Amazon.in").click();
		driver.findElementByName("Search in All Departments").sendKeys("Moto X Play 32GB"); System.out.println("2");  //entering in search box
		WebElement suggestion = driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_suggestion_list_view"); System.out.println("3");  
		List<WebElement> suggestList = suggestion.findElements(By.className("android.widget.LinearLayout"));  System.out.println("4"); //list of autosuggestion
		suggestList.get(4).click();  System.out.println("5");  //selecting autosuggestion
		Thread.sleep(3000);
		
		
		
		
		//driver.scrollToExact("Moto X Force (Black, 32GB)");
		driver.scrollToExact("omkar");
		Thread.sleep(10000);
		driver.scrollTo("Moto X Play - With Turbo Charger"); //scrolled by substring matching
		
		 // scrolled by exact text matching
		System.out.println("PROGRAMM SUCCSS");
		
		
		
		//driver.findElementByName("Motorola Moto X Play XT1562 (Black, 32GB)"); System.out.println("6");
		//driver.findElementByName("Motorola Moto X Play XT1562 (Black, 32GB)").click(); System.out.println("7");
		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
