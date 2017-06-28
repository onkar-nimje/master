import java.io.File;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserMobileWebApp {
	public static void main(String[] args) throws Exception {
		DesiredCapabilities capabilities =  new DesiredCapabilities();
		
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		File app = new File(appDir, "com.android.chrome_v52.0.2743.98-275609800_Android-4.1.apk");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "F4AZFG01U338");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.android.chrome");
		//capabilities.setCapability("appPackage", "com.android.browser");
		capabilities.setCapability("appActivity", "com.android.browser.BrowserActivity");
		
		//capabilities.setCapability("appActivity", "org.chromium.chrome.browser.ChromeTabbedActivity");
		
        System.out.println("CAPABILITIES loaded");
		WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		driver.get("http://ultimatereset.beachbody.com/shop/us/b/ultimate-reset");
		System.out.println("SUCCESS");
		
	}
}
