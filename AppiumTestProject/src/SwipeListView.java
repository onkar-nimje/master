import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class SwipeListView {
	private static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "SwipeListView Demo_v1.13_apkpure.com.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "com.fortysevendeg.android.swipelistview");
		
		//capabilities.setCapability("appPackage", "com.android.browser");
		//capabilities.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");
		capabilities.setCapability("appActivity", "sample.activities.SwipeListViewExampleActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(5000);
		size = driver.manage().window().getSize();
		 System.out.println(size);
		  
		 int startX , endX , startY, endY = 0;
		 System.out.println("int varible def");
		 //HORIZONTAL swipe
		 startX = (int) (size.width * 0.70);
		 endX = (int) (size.width * 0.30);
		 startY = size.height / 2;
		 
		 System.out.println(startX+" "+endX+" "+startY);
		 //right to left swipe
		 driver.swipe(startX, startY, endX, startY, 3000);
		 Thread.sleep(5000);
		 
		 //left to right swipe
		 driver.swipe(endX, startY, startX, startY, 3000);
		 Thread.sleep(5000);
		 
		 // VERTICALLY swipe
		 startY = (int) (size.height * 0.80);
		 endY = (int) (size.height * 0.20);
		 startX = size.width / 2;
		 
		 //top to bottom swipe
		 driver.swipe(startX, startY, startX, endY, 3000);
		 Thread.sleep(5000);
		 
		 //bottom to top swipe
		 driver.swipe(startX, endY, startX, startY, 3000);
		 Thread.sleep(5000);
		
		
		System.out.println("PROGRAMM SUCCSS");
		
		

		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
