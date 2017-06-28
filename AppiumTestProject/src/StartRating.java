
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


public class StartRating {
	private static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		File app = new File(appDir, "ApiDemos.apk");	
		DesiredCapabilities capabilities = new DesiredCapabilities();	
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
		  // Click on Views.
		  driver.findElement(By.name("Views")).click();
		  // Scroll till element which contains "Rating Bar" text If It Is not visible on screen.
		  driver.scrollTo("Rating Bar");
		  // Click on Rating Bar.
		  driver.findElement(By.name("Rating Bar")).click();
		
		WebElement threeStarRatingbar = driver.findElement(By.id("io.appium.android.apis:id/ratingbar1"));
		  //Get start point of threeStarRatingbar.
		  int startX = threeStarRatingbar.getLocation().getX();
		  //System.out.println("startX:"+startX);
		  //System.out.println("Y:"+threeStarRatingbar.getLocation().getY());
		  //Get end point of threeStarRatingbar.
		  int endX = threeStarRatingbar.getSize().getWidth();
		  //System.out.println("SIZE: "+threeStarRatingbar.getSize().toString());
		 // System.out.println("endX:"+endX);
		 // System.out.println(threeStarRatingbar.getSize().getHeight());
		  //Get vertical location of threeStarRatingbar.
		  int yAxis = threeStarRatingbar.getLocation().getY();
		 // System.out.println("yAxis :"+yAxis);
		  //Set threeStarRatingbar tap position to set Rating = 1 star.
		  //You can use endX * 0.3 for 1 star, endX * 0.6 for 2 star, endX * 1 for 3 star.
		  int tapAt = (int) (endX * 0.3);    
		  //Set threeStarRatingbar to Rating = 1.0 using TouchAction class.
		  TouchAction act=new TouchAction(driver);  
		  act.press(tapAt,yAxis).release().perform();
		


		System.out.println("PROGRAMM SUCCSS");

		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
