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


public class hideKeyboard {
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
		
		 driver.scrollTo("Views");
		  // Click on Views.
		  driver.findElement(By.name("Views")).click();
		  // Scroll till element which contains "Controls" text If It Is not visible on screen.
		  driver.scrollTo("Controls");
		  // Click on Controls.
		  driver.findElement(By.name("Controls")).click();
		  // Scroll till element which contains "2. Dark Theme" text If It Is not visible on screen.
		  driver.scrollTo("2. Dark Theme");
		  // Click on 2. Dark Theme.
		  driver.findElement(By.name("2. Dark Theme")).click();
		  // Typing in text box using sendKeys command.
		  driver.findElement(By.id("io.appium.android.apis:id/edit")).sendKeys("Test");
		  //To hide keyboard. 
		  driver.hideKeyboard();

		
		System.out.println("PROGRAMM SUCCSS");
		
		

		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
