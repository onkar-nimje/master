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

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


public class MultiTouch {
	private static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "com.the511plus.MultiTouchTester.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "com.the511plus.MultiTouchTester");
		
		//capabilities.setCapability("appPackage", "com.android.browser");
		//capabilities.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");
		capabilities.setCapability("appActivity", "com.the511plus.MultiTouchTester.MultiTouchTester");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(5000);
		size = driver.manage().window().getSize();
		System.out.println(size);

		int startX,endX,startY,endY,midX,midY;
		
		startX = (int) (size.width * 0.3) ;
		endX = (int) (size.width * 0.7);
		startY = (int) (size.height * 0.2) ;
		endY = (int) (size.height * 0.8);
		midX= (int) (size.width * 0.5);
		midY= (int) (size.height * 0.5);
		
		MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
		TouchAction action1 = new TouchAction(driver).longPress(startX, startY).waitAction(2000);
		TouchAction action2 = new TouchAction(driver).longPress(startX, endY).waitAction(2000);
		TouchAction action3 = new TouchAction(driver).longPress(endX, endY).waitAction(2000);
		TouchAction action4 = new TouchAction(driver).longPress(endX, startY).waitAction(2000);
		TouchAction action5 = new TouchAction(driver).longPress(midX, midY).waitAction(2000);
		
		multiTouchAction.add(action1).add(action2).add(action3).add(action4).add(action5).perform();
		
		System.out.println("PROGRAMM SUCCSS");
		
		

		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
