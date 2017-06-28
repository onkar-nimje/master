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
import pages.UpdateVideoPage;
import pages.VideoPage;
import utilities.SpecialAction;

public class VideoValidation {
	private WebElement element = null;
	List<WebElement> list = null;
	CatalogPage catalogPage;
	VideoPage videoPage;
	String successMsg = null;
	String failMsg = null;
	String screenShotName = null;
	private String ScreenshotLocation;
	private String automationStartTime;
	List<WebElement> elementList = null;
	
	public VideoValidation(WebDriver driver ,String ScreenshotLocation,String automationStartTime ) {
		catalogPage = new CatalogPage(driver);
		videoPage = new VideoPage(driver);
		this.ScreenshotLocation = ScreenshotLocation;
		this.automationStartTime = automationStartTime;
	 }
	public void testValidationMsg(WebDriver driver, WebDriverWait wait,String className,String methodName, String expMsg) throws Exception {
		 Thread.sleep(2000);
		 String successMsg = null;
		 String failMsg = null;
		 String screenShotName = className+"-"+methodName;
		 if (methodName.equalsIgnoreCase("ProdOOSTemp2OOSMsg")||methodName.equalsIgnoreCase("ProdOOSMsg")||methodName.equalsIgnoreCase("SkuOOSMsg")||methodName.equalsIgnoreCase("SkuOOSMsg_Doldrums")||methodName.equalsIgnoreCase("SkuOOSMsg_Easterly")||methodName.equalsIgnoreCase("SkuOOSMsg_Southerly")||methodName.equalsIgnoreCase("ProdOOSMsg_Doldrums")||methodName.equalsIgnoreCase("ProdOOSMsg_Southerly")||methodName.equalsIgnoreCase("ProdOOSMsg_Easterly")) {
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
		 if (methodName.equalsIgnoreCase("deleteNewVideo")) {
			 if(driver.getPageSource().contains(expMsg)) {
				 successMsg="Video Deleted - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Video Deleted - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 
		 if (methodName.equalsIgnoreCase("ProdOOSRedirect1")||methodName.equalsIgnoreCase("ProdOOSRedirect2")||methodName.equalsIgnoreCase("ProdOOSRedirect1_Doldrums")||methodName.equalsIgnoreCase("ProdOOSRedirect2_Doldrums")||methodName.equalsIgnoreCase("ProdOOSRedirect1_Southerly")||methodName.equalsIgnoreCase("ProdOOSRedirect2_Southerly")||methodName.equalsIgnoreCase("ProdOOSRedirect2_Easterly")||methodName.equalsIgnoreCase("ProdOOSRedirect1_Easterly")) {
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
		 
		 if (methodName.equalsIgnoreCase("SkuAvailableRedirect")||methodName.equalsIgnoreCase("SkuAvailableRedirect_Doldrums")||methodName.equalsIgnoreCase("SkuAvailableRedirect_Southerly")||methodName.equalsIgnoreCase("SkuAvailableRedirect_Easterly")) {
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
		 
		 if (methodName.equalsIgnoreCase("searchVideo")) {
			 elementList = videoPage.listOfVideos(driver, wait);
			 Boolean find = false;
			 for (int i = 0; i < elementList.size(); i++) {
				if (elementList.get(i).getText().equalsIgnoreCase(expMsg)) {
					find = true;
				    break;
				}
			}

			 if(find==true) {
				 successMsg="Video search - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Video search - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if (methodName.equalsIgnoreCase("createNewVideo")) {
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='product-detail-button theme-item-button']")));
			 int index1 = expMsg.indexOf("+");
			 int index2 = expMsg.indexOf("*");
			 String prd1 = expMsg.substring(0, index1);
			 String prd2 = expMsg.substring(index1+1, index2);
			 String anchorName = expMsg.substring(index2+1);
			 System.out.println("Prod1:"+prd1);
			 System.out.println("Prod2:"+prd2);
			 System.out.println("Anchor:"+anchorName);
			 if(driver.getPageSource().contains(prd1)&&driver.getPageSource().contains(prd2)&&driver.getPageSource().contains(anchorName)) {
				 successMsg="Product attach on new look - SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			 }
			 else {
				 failMsg="Product attach on new look - FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }
	 
	 

	
}
