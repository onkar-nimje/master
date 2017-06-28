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

import com.thoughtworks.selenium.webdriven.commands.Click;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


public class DragDrop {
	private static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "com.mobeta.android.demodslv-0.5.0-3_APKdot.com.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		
		//capabilities.setCapability("appPackage", "com.android.browser");
		//capabilities.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");
		capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(5000);
		
		driver.findElementByName("Basic usage playground").click();
		WebElement ele1 = driver.findElement(By.id("android:id/list"));
		List<WebElement> listItem = ele1.findElements(By.className("android.view.View"));	
		
		//WebElement from = driver.findElement(By.name("Joshua Redman"));
		
		//WebElement to = driver.findElement(By.name("Kurt Rosenwinkel"));
		
		TouchAction action = new TouchAction(driver);
		action.longPress(listItem.get(1)).moveTo(listItem.get(5)).release().perform();
		
		System.out.println("PROGRAMM SUCCSS");
		
		

		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
