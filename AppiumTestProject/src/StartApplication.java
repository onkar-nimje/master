import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class StartApplication {
	private static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		//File app = new File(appDir, "com.amazon.mShop.android.shopping-5.2.3-502030-minAPI9.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "F4AZFG01U338");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		//capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping"); //amazon
		capabilities.setCapability("appPackage", "com.android.chrome"); //chrome browser
		
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		driver.get("https://www.google.com");
		Thread.sleep(10000);
		driver.findElement(By.name("Search")).sendKeys("omkar");
		//driver.findElement(By.name("Sign in")).click();
		//driver.findElementByName("Sign in").click();
		System.out.println("2");
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
