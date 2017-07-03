package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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

//import config.Constants;

public class SpecialAction {

	 WebDriver driver;
	public SpecialAction(WebDriver driver) {
		this.driver= driver;
	}
	
	public static Select selectList(WebElement element) {
		Select elementList = new Select(element);
		return elementList;
	}
	
	public static Action mouseAction(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		Action act = builder.moveToElement(element).click().build();
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
		    			 wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
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
