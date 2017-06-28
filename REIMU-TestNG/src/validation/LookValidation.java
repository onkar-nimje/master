package validation;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pages.CatalogPage;
import pages.LookPage;
import pages.UpdateLookPage;
import pages.UpdateVideoPage;
import pages.VideoPage;
import utilities.SpecialAction;

public class LookValidation {
	private WebElement element = null;
	List<WebElement> list = null;
	CatalogPage catalogPage;
	VideoPage videoPage;
	LookPage lookPage;
	String successMsg = null;
	String failMsg = null;
	String screenShotName = null;
	private String ScreenshotLocation;
	private String automationStartTime;
	List<WebElement> elementList = null;
	
	public LookValidation(WebDriver driver ,String ScreenshotLocation,String automationStartTime ) {
		catalogPage = new CatalogPage(driver);
		videoPage = new VideoPage(driver);
		lookPage = new LookPage(driver);
		this.ScreenshotLocation = ScreenshotLocation;
		this.automationStartTime = automationStartTime;
	 }
	public void testValidationMsg(WebDriver driver,WebDriverWait wait, String className,String methodName, String expMsg) throws Exception {
		 Thread.sleep(2000);
		 String successMsg = null;
		 String failMsg = null;
		 String screenShotName = className+"-"+methodName;
		 if (methodName.equalsIgnoreCase("ProdOOSTemp2OOSMsg")||methodName.equalsIgnoreCase("ProdOOSTemp1OOSMsg")||methodName.equalsIgnoreCase("SkuOOSTemp2OOSMsg")||methodName.equalsIgnoreCase("SkuOOSTemp1OOSMsg")) {
			 if(driver.getPageSource().contains(expMsg)) {
				 successMsg="OOS message displaying - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Expected message NOT displaying - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }	
		 
		 if (methodName.equalsIgnoreCase("ProdOOSTemp2OOSRedirect2")||methodName.equalsIgnoreCase("ProdOOSTemp1OOSRedirect1")||methodName.equalsIgnoreCase("ProdOOSTemp1OOSRedirect2")||methodName.equalsIgnoreCase("ProdOOSTemp2OOSRedirect1")) {
			 if(driver.getCurrentUrl().contains(expMsg)) {
				 successMsg="OOS product redirecting user to Category page - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="OOS product NOT redirecting user to Category page - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }	
		 
		 if (methodName.equalsIgnoreCase("SkuAvailableSTemp2Redirect")||methodName.equalsIgnoreCase("SkuAvailableSTemp1Redirect")) {
			 if(driver.getCurrentUrl().contains(expMsg)) {
				 successMsg="Available sku redirecting user to PDP page - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Available sku NOT redirecting user to PDP page - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }	
		 
		 if (methodName.equalsIgnoreCase("searchLook")) {
			 elementList = lookPage.listOfLook(driver, wait);
			 Boolean find = false;
			 for (int i = 0; i < elementList.size(); i++) {
				if (elementList.get(i).getText().equalsIgnoreCase(expMsg)) {
					find = true;
				    break;
				}
			}

			 if(find==true) {
				 successMsg="Look search - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Look search - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if (methodName.equalsIgnoreCase("createNewLookTempate1")||methodName.equalsIgnoreCase("createNewLookTempate2")) {
			 if(driver.getPageSource().contains(expMsg)) {
				 successMsg="Product Attached to New Look - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Product Attached to New Look - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if (methodName.equalsIgnoreCase("removeAnchor")) {
			 if(!driver.getPageSource().contains(expMsg)) {
				 successMsg="Anchor removed  - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Anchor removed - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if (methodName.equalsIgnoreCase("deleteLook")) {
			 if(driver.getPageSource().contains(expMsg)) {
				 successMsg="Look Deletion - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Look Deletion - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if (methodName.equalsIgnoreCase("removeProduct")) {
			 if(!driver.getPageSource().contains(expMsg)) {
				 successMsg="Product Removed - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Product Removed - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }	
		 
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }

	
}
