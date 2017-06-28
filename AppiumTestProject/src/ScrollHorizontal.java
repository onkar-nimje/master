
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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


public class ScrollHorizontal {
	private static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "ApiDemos.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "io.appium.android.apis");
		
		//capabilities.setCapability("appPackage", "com.android.browser");
		//capabilities.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(5000);
		
		driver.findElement(By.name("Views")).click();
		driver.scrollTo("Tabs").click();
		driver.findElement(By.name("5. Scrollable")).click();
		for(int i=0; i<11;i++) {
			if(driver.findElements(By.name("Tab 11")).size()!=0) {
				driver.findElement(By.name("Tab 11")).click();
				Thread.sleep(2000);
				break;
			}
			else {
				horizontalScroll();
			}
		}
		
		WebElement mainTab= driver.findElement(By.id("android:id/tabhost"));
		WebElement textArea = mainTab.findElement(By.className("android.widget.TextView"));	
		List<WebElement> listtextview = mainTab.findElements(By.className("android.widget.TextView"));
		
		System.out.println(listtextview.get(listtextview.size()-1).getText());
	
		System.out.println("PROGRAMM SUCCSS");

		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
	public static void horizontalScroll () {
		size = driver.manage().window().getSize();
		int y = 173;
		int startX, endX = 0;
		startX = (int) (size.width * 0.2);
		endX = (int) (size.width * 0.8);
		driver.swipe(endX, y, startX, y, 2000);	
	}
}
