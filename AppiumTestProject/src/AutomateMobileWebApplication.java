import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AutomateMobileWebApplication {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		
		// Create object of  DesiredCapabilities class and specify android platform
		DesiredCapabilities capabilities=DesiredCapabilities.android();
				 
				 
		// set the capability to execute test in chrome browser
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
				 
		// set the capability to execute our test in Android Platform
		capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
				 
		// we need to define platform name
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
				 
		// Set the device name as well (you can give any name)
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
				 
		// set the android version as well 
		capabilities.setCapability(MobileCapabilityType.VERSION,"5.0.1");
				 
		// Create object of URL class and specify the appium server address
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
				 
		// Create object of  AndroidDriver class and pass the url and capability that we created
		WebDriver driver = new AndroidDriver(url, capabilities);
		
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
        
        
		
		
		
        driver.quit();
		

	}

}
