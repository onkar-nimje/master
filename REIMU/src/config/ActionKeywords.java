package config;

import static executionEngine.DriverScript.OR;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

import sun.invoke.util.VerifyType;
//import utilities.SpecialAction;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import executionEngine.TestFlow;


public class ActionKeywords {
	public static ObjectType objectType;
	public static WebElement element;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static By findBy;
	public static String mainWindow = null;
	
	

	//public static ObjectType objectType;
	//public static WebElement element ;
	//public ActionKeywords(WebDriver driver)
	public ActionKeywords(WebDriver driver)
	{
		ActionKeywords.driver=driver;
		objectType=new ObjectType();
		
	}
	

	
	
	public static void openBrowser(String object,String data,int idata,String objectType)
	{
		try {	
			Log.info("Opening Browser:"+data);
			System.out.println("1i m in action brower");
			//If value of the parameter is Mozilla, this will execute
			if(data.equals("Mozilla")){
				System.out.println("2i m in action brower");
				/////////////////
				FirefoxProfile geoDisabled = new FirefoxProfile();
				geoDisabled.setPreference("geo.enabled", false);
				geoDisabled.setPreference("geo.provider.use_corelocation", false);
				geoDisabled.setPreference("geo.prompt.testing", false);
				geoDisabled.setPreference("geo.prompt.testing.allow", false);
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxDriver.PROFILE, geoDisabled);
				/////////////////
				driver=new FirefoxDriver(geoDisabled);
				driver.manage().window().maximize();
				System.out.println("3i m in action brower");
				Log.info("Mozilla browser started");
				}
			else if(data.equals("IE")){
				//You may need to change the code here to start IE Driver
				System.setProperty("webdriver.ie.driver", "D:\\workspaces\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
				}	
			wait = new WebDriverWait(driver, 120);
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to open Browser --- " + e.getMessage();
			Log.info(TestFlow.errorMsg);		
			TestFlow.bResult = false;
		}
		
	}
	
	public static void maximize(String object,String data,int idata,String objectType) {
		driver.manage().window().maximize();
	}

	
	public static void navigate(String object,String data,int idata,String objectType)
	{	
		try {
			Log.info("Navigating to URL:"+data);
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get("http://www.store.demoqa.com");
			//driver.get(Constants.URL);
			
			driver.get(data);
			System.out.println("navigate method");
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to navigate --- " + e.getMessage();
			Log.info(TestFlow.errorMsg);			
			TestFlow.bResult = false;
		}
		
	}
	
	public static void reload(String object,String data,int idata,String objectType)
	{	
		try {
			Log.info("PAGE RELOAD");
			driver.navigate().refresh();
			System.out.println("PAGE RELOAD");
		} catch (Exception e) {
			TestFlow.errorMsg = "Page not refreshing --- " + e.getMessage();
			Log.info(TestFlow.errorMsg);		
			TestFlow.bResult = false;
		}
		
	}
	
	public static void switchToDefaultContent(String object,String data,int idata,String objectType)
	{	
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			TestFlow.errorMsg = "Switching to default content --- " + e.getMessage();
			Log.info(TestFlow.errorMsg);		
			TestFlow.bResult = false;
		}
		
	}
	
	//public static void click_MyAccount(String object) 
	//HERE WE ARE USING GENERALISE CLICK METHOD WHICH WE WILL USR FOR ALL CLICK ACTION (LOGIN, LOGOUT,MYACCOUNT)
	public static void click(String object,String data,int idata,String objectType) 
	{
		try {
			//driver.findElement(By.xpath("//*[@id='account']/a")).click();
			Log.info("Clicking on Webelement "+ object);
			//System.out.println("click function::"+data);
		
			    findBy = ObjectType.getObject(driver, object, objectType);
                wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
                wait.until(ExpectedConditions.elementToBeClickable(findBy));
				element = driver.findElement(findBy);
				wait.until(ExpectedConditions.visibilityOf(element));
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].scrollIntoView(true);",element);
				element.click();
				
				System.out.println("clicked on::"+object);
			//	driver.findElement(ObjectType.getObject(driver, object, objectType)).click();
			

			
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to click --- " + e.getMessage();
			Log.error(TestFlow.errorMsg);		
 			TestFlow.bResult = false;
		}
		
	}
	

	public static void specialButton(String object,String data,int idata,String objectType) {
		try {
			findBy = ObjectType.getObject(driver, object, objectType);
			List<WebElement> listAllBtn = driver.findElements(findBy);
			for (int i = 0; i < listAllBtn.size(); i++) {
				if (listAllBtn.get(i).getText().equalsIgnoreCase(data)) {
					 element = listAllBtn.get(i);
					 Log.info("Button "+data+" found and clicked");
					break;
				}
			}
			
			if(element!=null){
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
			}		
		} catch (Exception e) {
			if(!data.equalsIgnoreCase("×")){
				TestFlow.errorMsg = "Not able to click on button --- "+data + e.getMessage();
				Log.error(TestFlow.errorMsg);				
	 			TestFlow.bResult = false;
			}		
		}

	}
	
	public static void verifyElementPresent(String object,String data,int idata,String objectType) {
		try {
			findBy = ObjectType.getObject(driver, object, objectType);
			List<WebElement> listAllBtn = driver.findElements(findBy);
			for (int i = 0; i < listAllBtn.size(); i++) {
				if (listAllBtn.get(i).getText().equalsIgnoreCase(data)) {
					 element = listAllBtn.get(i);
					 Log.info("Button "+data+" found and clicked");
					break;
				}
			}
			
			if(element!=null){
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
			}		
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to click on button --- "+data + e.getMessage();
			Log.error(TestFlow.errorMsg);			
 			TestFlow.bResult = false;
		}

	}
	
	
	
	
	
	//not implemented correctly
	public static void clickAndWait(String object,String data,int idata,String objectType)
	{
		try {
			//driver.findElement(By.xpath("//*[@id='account']/a")).click();
			Log.info("ClickANDWait on Webelement "+ object);
			System.out.println("click function::"+data);
			if(data.equals(""))
			{
			//This is fetching the xpath of the element from the Object Repository property file
		//	driver.findElement(By.xpath(OR.getProperty(object))).click();
				element = driver.findElement(ObjectType.getObject(driver, object, objectType));
				System.out.println("element::"+element);
				Utils.waitForElement(driver,element);
				element.click();
				System.out.println("clicked on::"+object);
			//	driver.findElement(ObjectType.getObject(driver, object, objectType)).click();
			}
			
			
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to click --- " + e.getMessage();
			Log.error(TestFlow.errorMsg);			
 			TestFlow.bResult = false;
		}
	}
 
	public static void switchNextWindow(String object,String data,int idata,String objectType) throws InterruptedException {
		try {
			Thread.sleep(5000);
			mainWindow = null;
		    mainWindow = driver.getWindowHandle();
		    int count = 0;
		    Set<String> handle1 = driver.getWindowHandles();
			for (String handle : driver.getWindowHandles()) {				
				if (!mainWindow.equals(handle)) {
					driver.switchTo().window(handle);
					String url = driver.getCurrentUrl();
					if (!url.contains(data)) {
						Log.info("Switched to NEW WINDOW");
						break;
					}
				}
				count++;
		    }
			if(count == handle1.size()) {
				TestFlow.errorMsg ="Not able to switch on new Window";
				Log.error(TestFlow.errorMsg);				
	 			TestFlow.bResult = false;
			}
		} catch (Exception e) {
			TestFlow.errorMsg = "ERROR while switching to NEW Window"+ e.getMessage();
			Log.error(TestFlow.errorMsg);			
 			TestFlow.bResult = false;
		}
		
	}
	
	public static void switchToMainWindow(String object,String data,int idata,String objectType) throws InterruptedException {
		try {
			Thread.sleep(5000);
			for (String handle : driver.getWindowHandles()) {
				if (!mainWindow.equals(handle)) {
					String url = driver.getCurrentUrl();
					//if (!url.contains("http://qamediacom.objectedge.com/")) {
					if (!url.contains(data)) {
						driver.close();
						driver.switchTo().window(mainWindow);
						Log.info("Switched to MAIN WINDOW");
						break;
					}			
				}
		    }
			if (!driver.getCurrentUrl().contains(data)) {
				TestFlow.errorMsg = "Not able to switch on main Window";
				Log.error(TestFlow.errorMsg);
	 			TestFlow.bResult = false;
			}
		} catch (Exception e) {
			TestFlow.errorMsg ="Error while switching on MAIN Window" + e.getMessage();
			Log.error(TestFlow.errorMsg);		
 			TestFlow.bResult = false;
		}
		
	}
	
	
	public static void switchFrame(String object,String data,int idata,String objectType) 
	{
		try {
			Log.info("Switching to new Frame "+ object);
			findBy  = ObjectType.getObject(driver, object, objectType);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findBy));           
		} catch (Exception e) {
			TestFlow.errorMsg = "Error in switching frame "+object+" --- " + e.getMessage();
			Log.error(TestFlow.errorMsg);		
			TestFlow.bResult = false;;
		}
	}
	
	
	public static void input(String object,String data,int idata,String objectType)
	{
		try {
			Log.info("Entering the text '"+data+"' in " + object);
		    findBy = ObjectType.getObject(driver, object, objectType);
            wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
			element = driver.findElement(findBy);
			wait.until(ExpectedConditions.visibilityOf(element));   
			if(!data.equals(""))
				{
				Log.info("Entering the text '"+data+"' in " + object);
				element.clear();
				element.sendKeys(data);
				System.out.println("entered value::"+data);
				}
			
			else 
				{
				Log.info("Entering the text '"+idata+"' in: " + object);
				if (idata==0) {
					element.clear();
					element.sendKeys(data);
					System.out.println("entered value::"+data);
				}
				else {
					element.clear();
					element.sendKeys(""+idata);
					System.out.println("entered value::"+idata);
				}
				//element.sendKeys(""+idata);
				//System.out.println("entered value::"+idata);
				}
			
			System.out.println("Entered in text box:::"+object);
			
			//driver.findElement(ObjectType.getObject(driver, object, objectType)).sendKeys(data);
			
			//objectType.getObject(driver,object,objectType)
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to Enter "+object+" --- " + e.getMessage();
		    Log.error(TestFlow.errorMsg);
	        TestFlow.bResult = false;
		}
		
	}
	
	///////////////
	
	
	
	public static void data(String object,String data,int idata,String objectType)
	{
		try {
			
			int numberOfTransition=ExcelUtils.getRowcount("Data");
		//	int numberOfWebElement=ExcelUtils.get
			int numberOFColumn=ExcelUtils.getColCount("Data");
			
			String [] objectName= new String[numberOFColumn-1];
			
			for(int j=1;j<=numberOfTransition;j++)
			{
			for(int i=1;i<=numberOFColumn;i++)
			{
				objectName[i]=ExcelUtils.getCellStringData(0, i, "Data");
				WebElement element=driver.findElement(ObjectType.getObject(driver, objectName[i], objectType));
				String inputValue=ExcelUtils.getCellStringData(j, i, "Data");
				element.sendKeys(inputValue);
			}
			
			
			break;
			}
			
			
			Log.info("Entering the text in " + object);
			//driver.findElement(By.id("log")).sendKeys("testuser_3"); 
			
			
			
			
			//driver.findElement(By.id(OR.getProperty(object))).sendKeys(data); 
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Utils.waitForElement(driver,element);
     
			if(!data.equals(""))
				{
				//System.out.println("String:::"+data);
				element.sendKeys(data);
				}
			
			else 
				{
				//System.out.println("INPUT1 STRING:::"+idata);
				element.sendKeys(""+idata);
				}
			
			System.out.println("Entered in text box:::"+object);
			
			//driver.findElement(ObjectType.getObject(driver, object, objectType)).sendKeys(data);
			
			//objectType.getObject(driver,object,objectType)
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to Enter "+object+" --- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);			 
			 TestFlow.bResult = false;
		}
		
	}
	
	
	
	////////////////
	
	
	
	
	
	
	public static void waitCondition(String object,String data,int idata,String objectType) throws Exception
	{
		try {

			Log.info("Wait for element to load");
			findBy = ObjectType.getObject(driver, object, objectType);
			if (data.equalsIgnoreCase("presence")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
			}
			if (data.equalsIgnoreCase("clickable")) {
				wait.until(ExpectedConditions.elementToBeClickable(findBy));
			}
			if (data.equalsIgnoreCase("visible")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			}
		}
		 catch (Exception e) {
			 //System.out.println("element2:::"+element);
			 TestFlow.errorMsg = "Not able to Wait --- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);		 
			 TestFlow.bResult = false;
		}
	}	
	
	public static void copyTextandPrint(String object,String data,int idata,String objectType) throws Exception
	{
		try {
            String text = null;
			Log.info("Copy Text");
			findBy = ObjectType.getObject(driver, object, objectType);
			text = driver.findElement(findBy).getText();
			Log.info(data+text);
			System.out.println(data+text);
		}
		 catch (Exception e) {
			 TestFlow.errorMsg = "Not able to copy text from page--- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);
			 TestFlow.bResult = false;
		}
	}	
		
	
	public static void waitFor(String object,String data,int idata,String objectType) throws Exception
	{
		try {
		    System.out.println("Implicit wait");
			Log.info("Wait for page load completely");
			Thread.sleep(idata*1000);
			System.out.println("Impicit wait of "+idata+" seconds End");		
		}
		 catch (Exception e) {
			 TestFlow.errorMsg = "Not able to Wait --- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);
			 TestFlow.bResult = false;
		}
	}	
	
	public static void select(String object,String data,int idata,String objectType) throws Exception
	{
		WebElement main= null;
		WebElement secondary = null;
		try {
			Log.info("selecting the "+object);
			System.out.println("selecting object::"+object);
		//WebElement main=driver.findElement(By.className(OR.getProperty(object)));
		//	if(objectType.equalsIgnoreCase("className"))
		//	main=driver.findElement(By.className(OR.getProperty(object)));
			main=driver.findElement(ObjectType.getObject(driver, object, objectType));
			System.out.println("Main::"+main);
		//	main = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Utils.waitForElement(driver,main);
			
		//	else
		//		if (objectType.equalsIgnoreCase("id")) {
		//			main=driver.findElement(By.id(OR.getProperty(object)));
		//		}
			List<WebElement> list = null;
			//List<WebElement> list=main.findElements(By.tagName("label"));
			if(object.equalsIgnoreCase("option_Color")||object.equalsIgnoreCase("option_Size"))
			{	
				 list=main.findElements(By.tagName("label"));	
				 
			}
			else {				
				if(object.equalsIgnoreCase("option_Address")||object.equalsIgnoreCase("option_Ship"))
				{
					 list=main.findElements(By.tagName("li"));		
					
				}
				else {
					if(object.equalsIgnoreCase("option_PaymentCard"))
					{
						//secondary=main.findElement(By.id("selectedPayment"));
						((JavascriptExecutor)driver).executeScript("document.getElementById('selPayment').click();");
						secondary=main.findElement(By.id("selPayment"));
					//	System.out.println("Secondary::"+secondary);
						secondary=secondary.findElement(By.tagName("ul"));
						list=secondary.findElements(By.tagName("li"));
		     		}
						else {
							System.out.println("2nd TAG not found for select");
						}
						
					}
				}
			
		
		//	 System.out.println("List::"+list);
			Actions builder = new Actions(driver);
			Action action = builder.moveToElement(list.get(idata)).click().build();
			action.perform();
			
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to select --- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);
			 TestFlow.bResult = false;
			// throw(e);
		}
	}
	
	public static void scrollDown(String object,String data,int idata,String objectType)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	//If you want to scroll for a exact upto specified element then below piece of code will work for you.
    //je.executeScript("arguments[0].scrollIntoView(true);",element);
	
	public static void scrollUp(String object,String data,int idata,String objectType)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,-"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	
	public static void scrollRight(String object,String data,int idata,String objectType)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,-"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	
	public static void scrollLeft(String object,String data,int idata,String objectType)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,-"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	
	public static void  selectDropDown2(String object,String data,int idata,String objectType) throws Exception
	{
		Log.info("selecting the dropdown menu "+object);
		try {
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Actions builder=new Actions(driver);
		      Action act=builder.moveToElement(element).doubleClick().build();
		      act.perform();
			List<WebElement> li=element.findElements(By.tagName("option"));
			for(int i=0;i<li.size();i++) {
				System.out.println("OPTION"+i+"::"+li.get(i).getText());
			}
			li.get(idata).click();
		} 
		
		catch (Exception e) {
			TestFlow.errorMsg = "Not able to select element from dropdown menu "+e.getMessage();
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
		}
	}
	
	public static void  selectDropDown(String object,String data,int idata,String objectType) throws Exception  //select by INDEX
	{
		Log.info("selecting the dropdown menu "+object);
		try {
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Select select = new Select(element);
			select.selectByIndex(idata);
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to select element from dropdown menu "+e.getMessage();
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
		}
	}
	
	public static void selectRadio(String object,String data,int idata,String objectType) throws  Exception 
	{
		Log.info("selecting the radio button "+object);
		try {
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Actions builder = new Actions(driver);
			Action act = builder.moveToElement(element).click().build();
			act.perform();
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to select radio button "+e.getMessage();
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
		}
	}
	
	public static void mouseAction(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Actions builder = new Actions(driver);
			Action act =null;
			findBy = ObjectType.getObject(driver, object, objectType);
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			element=driver.findElement(findBy);
			Log.info("Mouse Action on object::"+object);
			if (data.equalsIgnoreCase("click")) {
				// act = builder.moveToElement(element).click(element).build();
				act = builder.moveToElement(element).click(element).build();
			}
			if (data.equalsIgnoreCase("doubleClick")) {
				 act = builder.moveToElement(element).doubleClick(element).build();
			}
			if (data.equalsIgnoreCase("doubleClickOnly")) {
				 act = builder.moveToElement(element).doubleClick().build();
			}
			if (data.equalsIgnoreCase("hover")) {
				 act = builder.moveToElement(element).build();
			}			
			act.perform();
			
		} catch (Exception e) {
			TestFlow.errorMsg = object+" not found for mouse hover";
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
			
		}
	}
	
	
	public static void mouseHoverClick(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("Mouse Hover on object::"+object);
			element=driver.findElement(ObjectType.getObject(driver, object, objectType));
			Actions builder = new Actions(driver);
			Action move=builder.moveToElement(element).doubleClick().build();
			move.perform();
			
		} catch (Exception e) {
			TestFlow.errorMsg = object+" not found for mouse hover";
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
			
		}
	}
	
	public static void verify(String object,String data,int idata,String objectType) throws Exception
	{
		try {	
			System.out.println("Verifying text::"+data);
			//WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			//WebElement element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Utils.waitForElement(driver,element);
			String text=element.getText();
			System.out.println("Actual text:"+text+" Expected Text:"+data);
			if(text.equals(data))
			{
				Log.info("Element text "+object+" found");
				//System.out.println("Actual text:"+text+" Expected Text:"+data);
			}
			else
				 TestFlow.bResult = false;
				
		} catch (Exception e) {
			TestFlow.errorMsg= "Element "+object+"  not found";
			Log.info(TestFlow.errorMsg);
		    TestFlow.bResult = false;
		}
	}
	
	
	public static void findText(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Thread.sleep(2000);
			System.out.println("Finding text on whole page::"+data);
			
			if (!data.equals("")) {
				//driver.findElement(By.linkText(data));
				if (driver.getPageSource().contains(data)) {
					System.out.println("Text "+data+"   found on page");
					Log.info("Text "+"'"+data+"' found on page");
				}
				else {
					TestFlow.bResult = false;
					 System.out.println("Text "+data+" NOT found on page");
					 Log.info("Text "+"'"+data+"' NOT found on page");
				}
			}
			else {
				if (driver.getPageSource().contains(""+idata)) {
					System.out.println("Text "+idata+"   found on page");
					Log.info("Text "+"'"+data+"' found on page");
				}
				else {
					 TestFlow.bResult = false;
					 System.out.println("Text "+idata+" NOT found on page");
					 Log.info("Text "+"'"+data+"' NOT found on page");
				}
			}
			
			
		} catch (Exception e) {
			TestFlow.errorMsg = "Text "+object+"  not found on page";
			Log.info(TestFlow.errorMsg);
		    TestFlow.bResult = false;
		}
	}
	
	public static void findPageURL(String object,String data,int idata,String objectType) throws Exception 
	{

		try {
			Log.info("Compairing Expected URL :"+data);
			System.out.println("Compairing Expected URL :"+data);		
				String pageUrl = driver.getCurrentUrl();				
				if (pageUrl.equals(data)) {
					Log.info("Requested URL found::"+data);
				}
				else {
					TestFlow.bResult = false;
					 System.out.println("Expected URL'"+data+"' NOT found::");
					 System.out.println("ACTUAL URL'"+pageUrl);
					 Log.info("Expected URL'"+data+"' NOT found::");
				}
		} catch (Exception e) {
			TestFlow.errorMsg = "Requested URL'"+data+"' NOT found::";
			Log.info(TestFlow.errorMsg);
		    TestFlow.bResult = false;
		}
	}
	
	
	public static void getFieldAttrVal(String object,String data,int idata,String objectType) throws Exception
	{
		try {	
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Utils.waitForElement(driver,element);
			
			String value=data;
			String attributName=null;
			int posi=value.indexOf(",");
			attributName=value.substring(0, posi);
			value=value.substring(posi+1);
			String text=element.getAttribute(attributName);
			System.out.println("Attribute name::"+attributName);
			System.out.println("Attribute value::"+text);
			System.out.println("Expected value::"+value);
			
			if(text.equals(value))
			{
				Log.info("Element text "+object+" found");
				//System.out.println("Actual text:"+text+" Expected Text:"+data);
			}
			else
				 TestFlow.bResult = false;
				
		} catch (Exception e) {
			TestFlow.errorMsg = "Element "+object+"  not found" ;
			Log.info(TestFlow.errorMsg);
		    TestFlow.bResult = false;
		}
	}
	
	public static void compareUrl(String object,String data,int idata,String objectType) throws Exception
	{
		String url;
		try {
			url=driver.getCurrentUrl();
			if(url.equals(data))
			{
				System.out.println("Expected URL of page FOUND");
				Log.info("URL OF Page::"+data);
			}
		} catch (Exception e) {
			TestFlow.errorMsg = "URL OF Page::"+data+"  not FOUND";
			Log.info(TestFlow.errorMsg );
		    TestFlow.bResult = false;
		}
	}

	
	public static void selectDate(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("selecting the date");
			System.out.println(OR.getProperty(object));
			String value=OR.getProperty(object);
			System.out.println(value);
			int startPosi=value.indexOf("Z");
			String value1=value.substring(0, startPosi);
			int endPosi=value.lastIndexOf("Z");
			String value2=value.substring(startPosi+1);
			System.out.println("original::"+value+", startPosi of idata::"+startPosi+", endPosi of idata::"+endPosi+", value1::"+value1+", value2::"+value2);
			driver.findElement(By.xpath(value1+idata+value2)).click();	
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to select date : "+e.getMessage();
			Log.error(TestFlow.errorMsg);
			TestFlow.bResult = false;
		}
	}
 
	public static void closeBrowser(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("Closing the browser");
			driver.quit();
		} catch (Exception e) {
			TestFlow.errorMsg = "Not able to Close the Browser --- " + e.getMessage();
			 Log.error(TestFlow.errorMsg);
			 TestFlow.bResult = false;
		}
			
	}
	
	
	public static void getResultantScreenshot (String screenshotName, String automationStartTime) throws Exception {		
		try {
			String todaysDateTime= new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			String todaysDate = new SimpleDateFormat("MM-dd-yyyy").format(new GregorianCalendar().getTime());
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String failedImage=result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".jpg";
			String failedImage= Constants.Path_ScreenshotLocation+todaysDate+"\\"+automationStartTime+"\\"+screenshotName+todaysDateTime+ ".jpg";
			System.out.println("image path::"+failedImage);
			File failureImageFile=new File(failedImage);
			FileUtils.moveFile(scrFile, failureImageFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (e);
			//e.printStackTrace();
		}
	}

}
