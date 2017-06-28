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


public class SwipeUsingTouchAction {
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
		
		int leftX,rightX = 0,y=200;
		leftX = (int) (size.width * 0.1);
		rightX = (int) (size.width * 0.9);
		
		TouchAction action = new TouchAction(driver);
		WebElement mainFrame = driver.findElement(By.id("com.fortysevendeg.android.swipelistview:id/example_lv_list"));
		List<WebElement> listItem = mainFrame.findElements(By.className("android.widget.FrameLayout"));
		System.out.println("TOTAL ITEM :"+listItem.size());
		
		//swapping from left to right
		for(int i=0;i<listItem.size();i++) {
			WebElement button1= listItem.get(i).findElement(By.id("com.fortysevendeg.android.swipelistview:id/example_row_b_action_1"));
			action.longPress(button1).moveTo(rightX, y).release().perform();
			y=y+150;
			Thread.sleep(2000);
		}
		System.out.println("value of Y after 1st loop::"+y);
		Thread.sleep(10000);
		//swapping from right to left
		List<WebElement> listItem1 = mainFrame.findElements(By.id("com.fortysevendeg.android.swipelistview:id/back"));
		System.out.println("TOTAL ITEM LOOP2 :"+listItem1.size());
		y=200;
		for(int i=0;i<listItem1.size();i++) {
			WebElement button3= listItem1.get(i).findElement(By.id("com.fortysevendeg.android.swipelistview:id/example_row_b_action_3"));
			action.longPress(button3).moveTo(leftX, y).release().perform();
			y=y+150;
			Thread.sleep(2000);
		}

		
		
		


		
		System.out.println("PROGRAMM SUCCSS");
		
		

		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
