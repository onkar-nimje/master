
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.selenium.webdriven.commands.Click;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


public class ChromeAPK {
	private static WebDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		File app = new File(appDir, "com.android.chrome_v52.0.2743.98-275609800_Android-4.1.apk");
		
		//File app = new File(appDir, "com.android.chrome-41.0.2272.94-2272094-minAPI14.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		//capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("deviceName", "F4AZFG01U338");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.android.chrome");
		capabilities.setCapability("appActivity", "org.chromium.chrome.browser.ChromeTabbedActivity");
		//capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		capabilities.setCapability("chromedriverExecutable", "D:/workspaces/MOBILE TESTING/chromedriver");
		System.out.println("ACtivity loaded");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(5000);
		driver.get("http://ultimatereset.beachbody.com/shop/us/b/ultimate-reset");
		Thread.sleep(10000);
		System.out.println("2");
		WebElement productSection = driver.findElement(By.id("categoryOptionDestination"));
		System.out.println("3");
		List<WebElement> listOfProducts = productSection.findElements(By.tagName("li"));
		System.out.println("4");
		WebElement buyButtonSection = listOfProducts.get(1).findElement(By.className("cta-container"));
		System.out.println("5");
		WebElement buyNow = buyButtonSection.findElement(By.linkText("BUY NOW"));
		System.out.println("6");
		buyNow.click();
		Thread.sleep(5000);
		WebElement skuOptions = driver.findElement(By.id("sku"));
		List<WebElement> skuList = skuOptions.findElements(By.tagName("option"));
		skuList.get(2).click();
		System.out.println("7");
		Thread.sleep(3000);
		
		WebElement qty = driver.findElement(By.id("qty"));
		List<WebElement> qtyList = qty.findElements(By.tagName("option"));
		qtyList.get(2).click();
		
		//driver.findElement(By.className("addToCart button radius trackable")).click();
		driver.findElement(By.xpath("//*[@id=\"add1\"]/ul/li[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"ultimate-reset-header\"]/div[1]/a/span")).click();
      
		
		
		

		System.out.println("PROGRAMM SUCCSS");

		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
