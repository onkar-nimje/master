import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class AsusZen5 {
	private static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/Apps/Amazon/");
		//File app = new File(appDir, "com.android.chrome_v53.0.2785.97-278509700_Android-4.1.apk");
		File app = new File(appDir, "com.amazon.mShop.android.shopping-5.2.3-502030-minAPI9.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");  //it is in place of appPackage & appActivity
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "emulator-5554");
		//capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping"); //amazon
		//capabilities.setCapability("appPackage", "com.android.chrome"); //chrome browser
		
		//capabilities.setCapability("appPackage", "com.android.browser");
		//capabilities.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.web.MShopWebGatewayActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("1");
		driver.get("https://www.google.com");
		Thread.sleep(10000);
		driver.findElementByName("Amazon.in").click();
		driver.findElementByName("Search in All Departments").sendKeys("Moto X Play 32GB"); System.out.println("2");  //entering in search box
		Thread.sleep(3000);
		WebElement suggestion = driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_suggestion_list_view"); System.out.println("3");  
		List<WebElement> suggestList = suggestion.findElements(By.className("android.widget.LinearLayout"));  System.out.println("4"); //list of autosuggestion
		suggestList.get(4).click();  System.out.println("5");  //selecting autosuggestion
		Thread.sleep(3000);
		
		
		
		
		
		WebElement searchedPhone = driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_vertical_stack_view")); //now on search result page
		//List<WebElement> phoneList = searchedPhone.findElements(By.className("android.widget.LinearLayout"));
		//List<WebElement> phoneList = searchedPhone.findElements(By.className("android.widget.ImageView"));
		List<WebElement> phoneList = searchedPhone.findElements(By.id("com.amazon.mShop.android.shopping:id/item_title")); //list of searched phones
		System.out.println("SIZE :" +phoneList.size());
		for(int i=0;i<phoneList.size();i++) {
			//System.out.println(phoneList.get(i).findElement(By.id("com.amazon.mShop.android.shopping:id/rs_results_image")).getText());
			//System.out.println(phoneList.get(i).findElement(By.id("com.amazon.mShop.android.shopping:id/rs_results_image")).getAttribute("content-desc"));
			System.out.println(phoneList.get(i).getText());
			if(phoneList.get(i).getText().equalsIgnoreCase("Moto G Plus, 4th Gen (Black, 32 GB)")) {
				phoneList.get(i).click();
				System.out.println("value of I :"+i);
				Thread.sleep(6000);
				break;
			}
		}
		WebElement pdp = driver.findElementByXPath("//android.view.View[@content-desc='Product Details']");  //now on PDP
		
		
		List<WebElement> PDPButtons = pdp.findElements(By.className("android.widget.Button"));  //finding all Buttons on PDP
		System.out.println("SIZE::"+PDPButtons.size());
		String detaultColor = pdp.findElement(By.name("Moto G Plus, 4th Gen (Black, 32 GB)")).getAttribute("name");
		System.out.println("detaultColor::"+detaultColor);
		PDPButtons.get(0).click(); //clicking on color button from PDP
		WebElement ColorSection = driver.findElement(By.className("android.widget.ListView"));  //now on color page
		List<WebElement> colorList = ColorSection.findElements(By.className("android.view.View")); //listing the number of colors
		System.out.println("Number of Clors :"+colorList.size());
		System.out.println("6");
		//System.out.println(colorList.get(1).getAttribute("name"));
		
		if(colorList.size() > 2) {
		for(int i =0;i<colorList.size();i++) {
			System.out.println("COLOR I::"+i);
			System.out.println("COLOR NAME::"+colorList.get(i).getAttribute("name"));
            
			if ((colorList.get(i).getAttribute("name")!=null) && (!colorList.get(i).getAttribute("name").equalsIgnoreCase(detaultColor)))  {
				colorList.get(i).click();
				Thread.sleep(3000);
				break;
			}	
			}
		}
		
		else {
			colorList.get(1).click();
			Thread.sleep(3000);
		}
		
		String phoneNEwColor = pdp.findElement(By.name("Moto G Plus, 4th Gen (White, 32 GB)")).getAttribute("name");
		System.out.println("NEW color::"+phoneNEwColor);
		
		if(phoneNEwColor!=detaultColor) {
			System.out.println("phone color change SUCCESS");
			
		}
		else {
			System.out.println("phone color change NOT sucess");
		}
		
		
		
		 // scrolled by exact text matching
		System.out.println("PROGRAMM SUCCSS");
		
		
		
		//driver.findElementByName("Motorola Moto X Play XT1562 (Black, 32GB)"); System.out.println("6");
		//driver.findElementByName("Motorola Moto X Play XT1562 (Black, 32GB)").click(); System.out.println("7");
		Thread.sleep(3000);
		
		
		
		//driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.quit();

}
}
