package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

//import config.Constants;

public class SpecialAction {

	
	 WebDriver driver;
	 static WebElement element;
	 static String mainWindow = null;
	 static List<WebElement> list = null;
	public SpecialAction(WebDriver driver) {
		this.driver= driver;
	}
	
	public static Select selectList(WebElement element) {
		Select elementList = new Select(element);
		return elementList;
	}
	
	public static Action mouseAction(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		Action act = builder.moveToElement(element).build();
		return act;
	}
	
	public static Action mouseAction(WebDriver driver, WebElement element, String action) {
		Actions builder = new Actions(driver);
		Action act = null;;
		if (action.equalsIgnoreCase("click")) {
			 act = builder.moveToElement(element).click(element).build();
		}
		if (action.equalsIgnoreCase("doubleClick")) {
			 act = builder.moveToElement(element).doubleClick(element).build();
		}
		if (action.equalsIgnoreCase("doubleClickOnly")) {
			 act = builder.moveToElement(element).doubleClick().build();
		}
		if (action.equalsIgnoreCase("hover")) {
			 act = builder.moveToElement(element).build();
		}
		if (action.equalsIgnoreCase("offsetClick")) {
			 act = builder.moveToElement(element,0,0).moveByOffset(100, 100).click().build();
		}
		return act;
	}
	
	
	public static void scrollDown(WebDriver driver,int idata)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	//If you want to scroll for a exact upto specified element then below piece of code will work for you.
    //je.executeScript("arguments[0].scrollIntoView(true);",element);
	public static void scrollUpToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public static void scrollUp(WebDriver driver,int idata)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String windowXYaxis="window.scrollBy(0,-"+idata+")";
		jse.executeScript(windowXYaxis, "");
	}
	
	public static void waitForElementCondition(WebDriver driver,WebElement element, String condition){
		 WebDriverWait wait = new WebDriverWait(driver, 120);
		 if(condition.equalsIgnoreCase("click"))
		 wait.until(ExpectedConditions.elementToBeClickable(element));
		 else
			 if(condition.equalsIgnoreCase("visible"))
			 wait.until(ExpectedConditions.visibilityOf(element));
		     else
		    	 if(condition.equalsIgnoreCase("select"))
		    	 wait.until(ExpectedConditions.elementToBeSelected(element));
		    	 else
		    	 {
		    		 if (condition.equalsIgnoreCase("presence")) {
		    			// wait.until(ExpectedConditions.presenceOfElementLocated(element));
					} else {
						System.out.println("condition:::"+condition);
			    		 wait.equals(element.getText().contains(condition));
					}
		    	 }	     
	 	}
	
	public static void getResultantScreenshot (WebDriver driver, String screenshotName, String automationStartTime , String ScreenshotLocation) throws Exception {		
		try {
			String todaysDateTime= new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			String todaysDate = new SimpleDateFormat("MM-dd-yyyy").format(new GregorianCalendar().getTime());
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String failedImage=result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".jpg";
			String failedImage= ScreenshotLocation+todaysDate+"\\"+automationStartTime+"\\"+screenshotName+todaysDateTime+ ".jpg";
			//System.out.println("image path::"+failedImage);
			File failureImageFile=new File(failedImage);
			FileUtils.moveFile(scrFile, failureImageFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (e);
			//e.printStackTrace();
		}
	}

	
	public static WebElement getElementByText(List<WebElement> list , String text) {
		element = null;	
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equalsIgnoreCase(text)) {
				element = list.get(i);
				break;
			}
		}
		return element;
	}
	

	public static WebElement getElementByText(WebDriver driver,List<WebElement> list , String text, int number) {
		element = null;	
		int count = 0;
		System.out.println("ListSize"+list.size());
		for (int i = 0; i < list.size(); i++) {
			SpecialAction.waitForElementCondition(driver, list.get(i), "click");
			System.out.println("i:"+i+" value:"+list.get(i).getText());
			if (list.get(i).getText().equalsIgnoreCase(text)) {	
				++count;
				if(count==number){
					System.out.println("BUtton found at i:"+i);
					element = list.get(i);
					break;
				}				
			}
		}		
		return element;
	}
	
	
	public static WebElement getElementByTextTest(WebDriver driver,List<WebElement> list , String text, int number) {
		element = null;	
		System.out.println("ListSize"+list.size());
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			SpecialAction.waitForElementCondition(driver, list.get(i), "click");
			System.out.println("i:"+i+" value:"+list.get(i).getText());
			if (list.get(i).getText().equalsIgnoreCase(text)) {	
				++count;
				System.out.println(" Count:"+count+" number:"+number);
				if(count==number){
					System.out.println("BUtton found at i:"+i+" Count:"+count);
					element = list.get(i);
					break;
				}				
			}
		}
		System.out.println("hello2");
		return element;
	}
	
	public static void switchNextWindow(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		mainWindow = null;
	    mainWindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				String url = driver.getCurrentUrl();
				if (!url.contains("http://qamediacom.objectedge.com/")) {
					break;
				}
			}
	    }
	}
	
	public static void switchToMainWindow(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		for (String handle : driver.getWindowHandles()) {
			if (!mainWindow.equals(handle)) {
				String url = driver.getCurrentUrl();
				if (!url.contains("http://qamediacom.objectedge.com/")) {
					driver.close();
					driver.switchTo().window(mainWindow);
					break;
				}			
			}
	    }
	}
	
	

}
