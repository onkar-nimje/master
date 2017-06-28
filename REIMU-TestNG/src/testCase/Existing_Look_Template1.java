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
import utilities.Log;
import validation.LookValidation;

//import TestNG.Log;

public class Existing_Look_Template1 {

	WebDriver driver;
	Browser specificBrowser;
	LoginPage loginPage;
	UpdateLookPage updateLookPage;
	HomePage homePage;
	SpecialAction specialAction;
	CatalogPage catalogPage;
	LookValidation lookValidation;
	LookPage lookPage;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Look//Existing_Look_Template1";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	public Existing_Look_Template1() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		updateLookPage = new UpdateLookPage(driver);
		specialAction = new SpecialAction(driver);
		homePage =new HomePage(driver);
		catalogPage = new CatalogPage(driver);
		lookPage = new LookPage(driver);
		lookValidation = new LookValidation(driver, ScreenshotLocation, automationStartTime);
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
	 
	 
	 @Test(priority=0,enabled=true)
	 @Parameters({"sEmail","sPassword"})
	 public void LoginToSite(String email, String password) throws InterruptedException, IOException {
		 loginPage.openLoginPage(driver, "http://qamediacom.objectedge.com/admin/#/login");// QA
		// loginPage.openLoginPage(driver, "https://app.reimucommerce.com/admin/#/login"); //PROD
		 loginPage.email(driver).sendKeys(email);
		 loginPage.password(driver).sendKeys(password);
		 loginPage.btnLogin(driver).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userDropDown")));
	 }
	 
      //////////////////////TEMPLATE1 - REDIRECT//////////////////////////
	 
	 //validating look search
	 @Test(priority=1,enabled=true)
	 @Parameters({"sLookName"})
	 public void searchLook(String lookname) throws Exception {
		 HomePage.btnViewAllLooks(driver,wait).click();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys(lookname);
		 lookPage.btnSearch(driver,wait).click();	
		 //lookPage.lnkLookName(driver,wait).click();
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1","searchLook", lookname);		 
	 }
	
	 //validating OOS product msg
	 @Test(priority=2,enabled=true)
	 @Parameters({"sLookURL","sPrOOSmsg"})  
	 public void ProdOOSTemp1OOSMsg(String lookUrl,String expMsg) throws Exception {
		 updateLookPage.openLookUpdatePage(driver, lookUrl);	
		 updateLookPage.btnPreview(driver,wait).click();
		 updateLookPage.switchFrame(driver, wait, "iframeModal");
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 //testValidationMsg(driver,"ProdOOSTemp1OOSMsg",expMsg);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1", "ProdOOSTemp1OOSMsg", expMsg);
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=3,enabled=true)
	 @Parameters({"sLookURL","sCategoryURL"})    
	 public void ProdOOSTemp1OOSRedirect1(String lookUrl,String prdCatURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		 SpecialAction.switchNextWindow(driver);
		 //testValidationMsg(driver, "ProdOOSTemp1OOSRedirect1", prdCatURL);	
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1", "ProdOOSTemp1OOSRedirect1", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=4,enabled=true)
	 @Parameters({"sLookURL","sCategoryURL"})   
	 public void ProdOOSTemp1OOSRedirect2(String lookUrl,String prdCatURL) throws Exception {
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 Thread.sleep(2000);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		 SpecialAction.switchNextWindow(driver);
		 //testValidationMsg(driver, "ProdOOSTemp1OOSRedirect2", prdCatURL);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1", "ProdOOSTemp1OOSRedirect2", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=5,enabled=true)
	 @Parameters({"sLookURL","sSkuOOSmsg"})    //DISPLAYING MSG FOR OOS SKU
	 public void SkuOOSTemp1OOSMsg(String lookUrl,String expMsg) throws Exception {
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 Thread.sleep(2000);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		// testValidationMsg(driver,"SkuOOSTemp1OOSMsg",expMsg);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1", "SkuOOSTemp1OOSMsg", expMsg);
		// Thread.sleep(5000);
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=6,enabled=true)
	 @Parameters({"sLookURL","sPDPURL"})    //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 public void SkuAvailableSTemp1Redirect(String lookUrl,String pdpURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 Thread.sleep(2000);	
		 UpdateLookPage .LookPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		// testValidationMsg(driver, "SkuAvailableSTemp1Redirect", pdpURL);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template1", "SkuAvailableSTemp1Redirect", pdpURL);
		 SpecialAction.switchToMainWindow(driver);	 
	 }

	 @AfterClass
	 public void tearDown() {
		 //driver.quit();
	 }
	  
}
