
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


public class DateTime {
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
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		Thread.sleep(3000);
         
		driver.scrollTo("Views");
		driver.findElement(By.name("Views")).click();
		driver.scrollTo("Date Widgets");
		driver.findElement(By.name("Date Widgets")).click();
		driver.scrollTo("1. Dialog"); 
		driver.findElement(By.name("1. Dialog")).click();  
		driver.findElement(By.name("change the date")).click();
		
		WebElement datePicket = driver.findElement(By.id("android:id/datePicker"));
		List<WebElement> listOfFields = datePicket.findElements(By.className("android.widget.NumberPicker"));
		
		listOfFields.get(0).findElement(By.className("android.widget.EditText")).clear();
		listOfFields.get(0).findElement(By.className("android.widget.EditText")).sendKeys("Nov");
		
		listOfFields.get(1).findElement(By.className("android.widget.EditText")).clear();
		listOfFields.get(1).findElement(By.className("android.widget.EditText")).sendKeys("13");
		
		listOfFields.get(2).findElement(By.className("android.widget.EditText")).clear();
		listOfFields.get(2).findElement(By.className("android.widget.EditText")).sendKeys("2020");
		//	String month = listOfFields.get(0).findElement(By.className("android.widget.EditText")).getText();
		/*
		    while (!listOfFields.get(0).findElement(By.className("android.widget.EditText")).getText().equalsIgnoreCase("Nov")) {
		    	System.out.println(listOfFields.get(0).findElement(By.className("android.widget.EditText")).getText());
		    	listOfFields.get(0).findElement(By.className("android.widget.Button")).click();					
			}
		    
		    while (!listOfFields.get(1).findElement(By.className("android.widget.EditText")).getText().equalsIgnoreCase("01")) {
		    	System.out.println(listOfFields.get(1).findElement(By.className("android.widget.EditText")).getText());
		    	listOfFields.get(1).findElement(By.className("android.widget.Button")).click();					
			}
		    
		    while (!listOfFields.get(2).findElement(By.className("android.widget.EditText")).getText().equalsIgnoreCase("2010")) {
		    	System.out.println(listOfFields.get(2).findElement(By.className("android.widget.EditText")).getText());
		    	listOfFields.get(2).findElement(By.className("android.widget.Button")).click();					
			}
		    
		*/    
		


		System.out.println("PROGRAMM SUCCSS");

		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
