package config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import executionEngine.DriverScript;

import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import static executionEngine.DriverScript.OR;

public class ActionKeywords {
	public static ObjectType objectType;
	public static WebElement element ;
	public ActionKeywords()
	{
		objectType=new ObjectType();
	}
	

	public static WebDriver driver;
	
	public static void openBrowser(String object,String data,int idata,String objectType)
	{
		try {	
			Log.info("Opening Browser");
			//If value of the parameter is Mozilla, this will execute
			if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
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
		} catch (Exception e) {
			Log.info("Not able to open Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
		
	}
	
	public static void navigate(String object,String data,int idata,String objectType)
	{	
		try {
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get("http://www.store.demoqa.com");
			//driver.get(Constants.URL);
			
			driver.get(data);
			System.out.println("navigate method");
		} catch (Exception e) {
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
		}
		
	}
	
	//public static void click_MyAccount(String object) 
	//HERE WE ARE USING GENERALISE CLICK METHOD WHICH WE WILL USR FOR ALL CLICK ACTION (LOGIN, LOGOUT,MYACCOUNT)
	public static void click(String object,String data,int idata,String objectType) 
	{
		try {
			//driver.findElement(By.xpath("//*[@id='account']/a")).click();
			Log.info("Clicking on Webelement "+ object);
		//	System.out.println("click function::"+data);
			if(data.equals(""))
			{
			//This is fetching the xpath of the element from the Object Repository property file
		//	driver.findElement(By.xpath(OR.getProperty(object))).click();
				element = driver.findElement(ObjectType.getObject(driver, object, objectType));
				Utils.waitForElement(driver,element);
				element.click();
				System.out.println("clicked on::"+object);
			//	driver.findElement(ObjectType.getObject(driver, object, objectType)).click();
			}
			
			if(data.equals("Lancamentos"))
			{
				((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10003').click();");
			}
			
			if(data.equals("infantil"))
			{
				((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10002').click();");
			}
			
		} catch (Exception e) {
			Log.error("Not able to click --- " + e.getMessage());
 			DriverScript.bResult = false;
		}
		
	}
 
	
	public static void switchWindow(String object,String data,int idata,String objectType) 
	{
		try {
			//driver.findElement(By.xpath("//*[@id='account']/a")).click();
			Log.info("Clicking on Webelement "+ object);
		//	System.out.println("click function::"+data);
			if(data.equals(""))
			{
			//This is fetching the xpath of the element from the Object Repository property file
		//	driver.findElement(By.xpath(OR.getProperty(object))).click();
				element = driver.findElement(ObjectType.getObject(driver, object, objectType));
				Utils.waitForElement(driver,element);
				element.click();
				System.out.println("clicked on::"+object);
			//	driver.findElement(ObjectType.getObject(driver, object, objectType)).click();
			}
			
			if(data.equals("Lancamentos"))
			{
				((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10003').click();");
			}
			
			if(data.equals("infantil"))
			{
				((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10002').click();");
			}
			
		} catch (Exception e) {
			Log.error("Not able to click --- " + e.getMessage());
 			DriverScript.bResult = false;
		}
		
	}
	
	
	
	
	public static void input(String object,String data,int idata,String objectType)
	{
		try {
			Log.info("Entering the text in " + object);
			//driver.findElement(By.id("log")).sendKeys("testuser_3"); 
			
			
			//driver.findElement(By.id(OR.getProperty(object))).sendKeys(data); 
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Utils.waitForElement(driver,element);
     
			if(!data.equals(""))
				{
				//System.out.println("String:::"+data);
				element.clear();
				element.sendKeys(data);
				System.out.println("entered value::"+data);
				}
			
			else 
				{
				//System.out.println("INPUT1 STRING:::"+idata);
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
			 Log.error("Not able to Enter "+object+" --- " + e.getMessage());
			 DriverScript.bResult = false;
		}
		
	}
	
	///////////////
	
	
	
	public static void data(String object,String data,int idata,String objectType)
	{
		try {
			
			int numberOfTransition=ExcelUtils.getRowCount("Data");
		//	int numberOfWebElement=ExcelUtils.get
			int numberOFColumn=ExcelUtils.getColCount("Data");
			
			String [] objectName= new String[numberOFColumn-1];
			
			for(int j=1;j<=numberOfTransition;j++)
			{
			for(int i=1;i<=numberOFColumn;i++)
			{
				objectName[i]=ExcelUtils.getCellData(0, i, "Data");
				WebElement element=driver.findElement(ObjectType.getObject(driver, objectName[i], objectType));
				String inputValue=ExcelUtils.getCellData(j, i, "Data");
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
			 Log.error("Not able to Enter "+object+" --- " + e.getMessage());
			 DriverScript.bResult = false;
		}
		
	}
	
	
	
	////////////////
	
	public static void waitCondition(String object,String data,int idata,String objectType) throws Exception
	{
		try {
		    System.out.println("Implicit wait");
			Log.info("Wait for page load completely");
			System.out.println("data::"+data);
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			System.out.println("element3:::"+element);
			Utils.waitForElementCondition(driver, element,data);
			System.out.println("element1:::"+element);
			
		}
		 catch (Exception e) {
			 System.out.println("element2:::"+element);
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScript.bResult = false;
		}
	}	
	
	
	public static void waitFor(String object,String data,int idata,String objectType) throws Exception
	{
		try {
		    System.out.println("Implicit wait");
			Log.info("Wait for page load completely");
			/*
			int i=0;
			if(data.equals("Five"))
				i=5;
			else {
				if (data.equals("Ten"))
					i=10;
				else {
					if (data.equals("Twenty"))
						i=20;
					else {
						if (data.equals("Thirty"))
							i=30;
						else {
							if(data.equals("Sixty"))
								i=60;
							else {
								if (data.equals("Two"))
									i=120;
							}
						}
					}
				}
			}
			*/
			Thread.sleep(idata*1000);
			System.out.println("Impicit wait of "+idata+" seconds End");
			
		}
		 catch (Exception e) {
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScript.bResult = false;
		}
	}	
	
	public static void select(String object,String data,int idata,String objectType) throws Exception
	{
		WebElement main= null;
		WebElement secondary = null;
		try {
			Log.info("selecting the "+object);
			/*
			int i =0;
			if(data.equals("One"))
				i=1;
			else {
				if(data.equals("Two"))
					i=2;
				else {
					if(data.equals("Three"))
						i=3;
					else {
						if(data.equals("Four"))
							i=4;
						else {
							if(data.equals("Five"))
								i=5;
						}
					}
				}
			} */
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
			
			 Log.error("Not able to select --- " + e.getMessage());
			 DriverScript.bResult = false;
			// throw(e);
		}
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
			li.get(idata).click();
		} 
		
		catch (Exception e) {
			Log.error("Not able to select element from dropdown menu "+e.getMessage());
			DriverScript.bResult = false;
			// TODO: handle exception
		}
	}
	
	public static void  selectDropDown(String object,String data,int idata,String objectType) throws Exception
	{
		Log.info("selecting the dropdown menu "+object);
		try {
			/*
			int i =0;
			if(data.equals("One"))
				i=1;
			else {
				if(data.equals("Two"))
					i=2;
				else {
					if(data.equals("Three"))
						i=3;
					else {
						if(data.equals("Four"))
							i=4;
						else {
							if(data.equals("Five"))
								i=5;
						}
					}
				}
			} */
			element = driver.findElement(ObjectType.getObject(driver, object, objectType));
			Select select = new Select(element);
			select.selectByIndex(idata);
		} catch (Exception e) {
			Log.error("Not able to select element from dropdown menu "+e.getMessage());
			DriverScript.bResult = false;
			// TODO: handle exception
		}
	}
	
	public static void mouseHover(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("Mouse Hover on object::"+object);
			element=driver.findElement(ObjectType.getObject(driver, object, objectType));
			Actions builder = new Actions(driver);
			Action move=builder.moveToElement(element).build();
			move.perform();
			
		} catch (Exception e) {
			Log.error(object+" not found for mouse hover");
			DriverScript.bResult = false;
			
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
				 DriverScript.bResult = false;
				
		} catch (Exception e) {
			Log.info("Element "+object+"  not found");
		    DriverScript.bResult = false;
		}
	}
	
	
	public static void findText(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			System.out.println("Finding text on whole page::"+data);
			
			if (!data.equals("")) {
				//driver.findElement(By.linkText(data));
				if (driver.getPageSource().contains(data)) {
					System.out.println("Text "+data+"   found on page");
					Log.info("Text "+data+"   found on page");
				}
				else {
					DriverScript.bResult = false;
					 System.out.println("Text "+data+" NOT found on page");
					 Log.info("Text "+data+" NOT found on page");
				}
			}
			else {
				if (driver.getPageSource().contains(""+idata)) {
					System.out.println("Text "+idata+"   found on page");
					Log.info("Text "+idata+"   found on page");
				}
				else {
					 DriverScript.bResult = false;
					 System.out.println("Text "+idata+" NOT found on page");
					 Log.info("Text "+idata+" NOT found on page");
				}
			}
			
			
		} catch (Exception e) {
			Log.info("Text "+object+"  not found on page");
		    DriverScript.bResult = false;
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
				 DriverScript.bResult = false;
				
		} catch (Exception e) {
			Log.info("Element "+object+"  not found");
		    DriverScript.bResult = false;
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
			Log.info("URL OF Page::"+data+"  not FOUND");
		    DriverScript.bResult = false;
		}
	}

	
	public static void selectDate(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("selecting the date");
			/*
			int i =0;
			if(data.equals("One"))
				i=1;
			else {
				if(data.equals("Two"))
					i=2;
				else {
					if(data.equals("Three"))
						i=3;
					else {
						if(data.equals("Four"))
							i=4;
						else {
							if(data.equals("Five"))
								i=5;
							else {
								if(data.equals("Six"))
									i=6;
							}
						}
					}
				}
			} */
			System.out.println(OR.getProperty(object));
			String value=OR.getProperty(object);
			System.out.println(value);
			int startPosi=value.indexOf("Z");
			String value1=value.substring(0, startPosi);
			int endPosi=value.lastIndexOf("Z");
			String value2=value.substring(startPosi+1);
			System.out.println("original::"+value+", startPosi of idata::"+startPosi+", endPosi of idata::"+endPosi+", value1::"+value1+", value2::"+value2);
			//driver.findElement(By.xpath(OR.getProperty(object))).click();
			//driver.findElement(By.xpath(path)).click();
			//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td["+idata+"]/a")).click();
			driver.findElement(By.xpath(value1+idata+value2)).click();
		
			
			
			
		} catch (Exception e) {
			Log.error("Not able to select date");
			DriverScript.bResult = false;
		}
	}
 
	public static void closeBrowser(String object,String data,int idata,String objectType) throws Exception
	{
		try {
			Log.info("Closing the browser");
			driver.quit();
		} catch (Exception e) {
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 DriverScript.bResult = false;
		}
			
	}
	
	
}
