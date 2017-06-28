package testCase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.SpecialAction;

import pages.Browser;
import pages.CatalogPage;
import pages.HomePage;
import pages.LoginPage;
import pages.LookPage;
import pages.CatalogPage.ProductSection;
import pages.UpdateLookPage;
import pages.UpdateVideoPage;
import pages.VideoPage;
import utilities.Log;
import validation.VideoValidation;

//import TestNG.Log;

public class Existing_Video_EasterlySoutherly {

	WebDriver driver;
	Browser specificBrowser;
	LoginPage loginPage;
	UpdateLookPage updateLookPage;
	HomePage homePage;
	SpecialAction specialAction;
	CatalogPage catalogPage;
	LookPage lookPage;
	VideoPage videoPage;
	UpdateVideoPage updateVideoPage;
	VideoValidation videoValidation;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Video//Existing_Video_EasterlySoutherly//";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	List<WebElement> elementList = null;
	public Existing_Video_EasterlySoutherly() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		updateLookPage = new UpdateLookPage(driver);
		specialAction = new SpecialAction(driver);
		homePage =new HomePage(driver);
		catalogPage = new CatalogPage(driver);
		lookPage = new LookPage(driver);
		videoPage = new VideoPage(driver);
		updateVideoPage = new UpdateVideoPage(driver);
		videoValidation = new VideoValidation(driver, ScreenshotLocation, automationStartTime);
	}
	
	 @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
			
				//Log.startTestCase("Registration");
				//filePath = "D://workspaces//Selenuim//BB2.4//test-output//TestOutPut.xls";
				//ExcelUtils.setExelFile(filePath);


				if(browser.equalsIgnoreCase("Firefox"))     //FIREFOX
				{
					driver = specificBrowser.firefox(driver);
				}
				if(browser.equalsIgnoreCase("Chrome"))      //CHROME
				{
					driver = specificBrowser.chrome(driver);
				}
				
				if(browser.equalsIgnoreCase("IE"))          //INTERNET EXPLORER
				{
					driver = specificBrowser.internetExplorer(driver);
				}
				wait = new WebDriverWait(driver, 120);
				driver.manage().window().maximize();
			 
				
		} catch (Exception e) {
			Reporter.log(e.toString());
			//Log.info(e.toString());
		}
	    
	  }
	 
	 
	 //LOGIN TO SITE
	 @Test(priority=0,enabled=true)
	 @Parameters({"sEmail","sPassword","sVideoURL"})
	 public void LoginToSite(String email, String password,String videoUrl) throws InterruptedException, IOException {
		 loginPage.openLoginPage(driver, "http://qamediacom.objectedge.com/admin/#/login");// QA
		 //loginPage.openLoginPage(driver, "https://app.reimucommerce.com/admin/#/login"); //PROD
		 //email = "demo@objectedge.com";
		 //password = "Abc123!";
		 loginPage.email(driver).sendKeys(email);
		 loginPage.password(driver).sendKeys(password);
		 loginPage.btnLogin(driver).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userDropDown")));
		 updateVideoPage.openVideoUpdatePage(driver, videoUrl);	
	 }
	 
      //////////////////////REDIRECT//////////////////////////
	 
	 //VALIDATING VIDEO SEARCH
	 @Test(priority=1,enabled=true)
	 @Parameters({"sExistVideo","sVideoURL"})
	 public void searchVideo(String videoName,String videoUrl) throws Exception {
		 driver.get("http://qamediacom.objectedge.com/admin/#/videos");
		 Thread.sleep(5000);
		 videoPage.searchBx(driver,wait).clear();
		 videoPage.searchBx(driver,wait).sendKeys(videoName);
		 videoPage.btnSearch(driver,wait).click();	
		 videoValidation.testValidationMsg(driver,wait,"Existing_Video_EasterlySoutherly","searchVideo",videoName);
		 updateVideoPage.openVideoUpdatePage(driver, videoUrl);
		 
	 }
	
	 //VALIDATING OOS PRODUCT MSG
	 @Test(priority=2,enabled=true)
	 @Parameters({"sVideoURL","sTemplateName","sOOSPrdName","sPrOOSmsg","sButtonName"})  
	 public void ProdOOSMsg(String videoUrl,String template,String oosPrdName,String expMsg, String btn) throws Exception {
		 updateVideoPage.btnTemplate(driver, wait, template).click();
		 Thread.sleep(5000);
		 updateVideoPage.btnPreview(driver,wait).click();			 
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,btn).click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.alermsgOOS(driver, wait);
		 videoValidation.testValidationMsg(driver,wait,"Existing_Video-"+template,"ProdOOSMsg",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=3,enabled=true)
	 @Parameters({"sVideoURL","sTemplateName","sOOSPrdName","sCategoryURL","sButtonName"})    
	 public void ProdOOSRedirect1(String videoUrl,String template,String oosPrdName,String prdCatURL, String btn) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,btn).click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver, wait,"Existing_Video-"+template,"ProdOOSRedirect1", prdCatURL);	
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=4,enabled=true)
	 @Parameters({"sVideoURL","sTemplateName","sOOSPrdName","sCategoryURL","sButtonName"})   
	 public void ProdOOSRedirect2(String videoUrl,String template,String oosPrdName,String prdCatURL,String btn) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,btn).click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait,"Existing_Video-"+template,"ProdOOSRedirect2", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=5,enabled=true)
	 @Parameters({"sVideoURL","sTemplateName","sAvailablePrdName","sSkuOOSmsg","sButtonName"})
	 public void SkuOOSMsg(String videoUrl,String template,String availablePrdName,String expMsg,String btn) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,btn).click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 videoValidation.testValidationMsg(driver,wait,"Existing_Video-"+template,"SkuOOSMsg",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=6,enabled=true)
	 @Parameters({"sVideoURL","sTemplateName","sAvailablePrdName","sPDPURL","sButtonName"})
	 public void SkuAvailableRedirect(String videoUrl,String template,String availablePrdName,String pdpURL,String btn) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,btn).click();
		 UpdateVideoPage .VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait, "Existing_Video-"+template,"SkuAvailableRedirect", pdpURL);
		 SpecialAction.switchToMainWindow(driver);	 
	 }
	 
	 @AfterClass
	 public void tearDown() {
		 driver.close();
		 //driver.quit();
	 }
	  
}
