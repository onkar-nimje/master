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

public class Existing_Look_Template2 {

	WebDriver driver;
	Browser specificBrowser;
	LoginPage loginPage;
	UpdateLookPage updateLookPage;
	HomePage homePage;
	LookPage lookPage;
	LookValidation lookValidation;
	SpecialAction specialAction;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Look//Existing_Look_Template1";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	public Existing_Look_Template2() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		updateLookPage = new UpdateLookPage(driver);
		specialAction = new SpecialAction(driver);
		homePage =new HomePage(driver);
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
	
	 //////////////////////TEMPLATE2 - REDIRECT//////////////////////////
	 //validating OOS product msg
	 @Test(priority=1,enabled=true)
	 @Parameters({"sLookURL","sOOSPrdName","sPrOOSmsg"})
	 public void ProdOOSTemp2OOSMsg(String lookUrl,String oosPrdName,String expMsg) throws Exception {
		 updateLookPage.openLookUpdatePage(driver, lookUrl);	 
		 updateLookPage.btnPreview(driver,wait).click();
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.btnMoreDetail(driver,wait,oosPrdName).click();
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template2", "ProdOOSTemp2OOSMsg", expMsg);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnClose(driver, wait).click();
	 }
	 //IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=2,enabled=true)
	 @Parameters({"sLookURL","sOOSPrdName","sCategoryURL"})    
	 public void ProdOOSTemp2OOSRedirect1(String lookUrl,String oosPrdName,String prdCatURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.btnMoreDetail(driver,wait,oosPrdName).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template2", "ProdOOSTemp2OOSRedirect1", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnClose(driver, wait).click();
	 }
	 
	 //IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=3,enabled=true)
	 @Parameters({"sLookURL","sOOSPrdName","sCategoryURL"})   
	 public void ProdOOSTemp2OOSRedirect2(String lookUrl,String oosPrdName,String prdCatURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.btnMoreDetail(driver,wait,oosPrdName).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template2", "ProdOOSTemp2OOSRedirect2", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnClose(driver, wait).click();
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=4,enabled=true)
	 @Parameters({"sLookURL","sAvailablePrdName","sSkuOOSmsg"})   //DISPLAYING MSG FOR OOS SKU
	 public void SkuOOSTemp2OOSMsg(String lookUrl,String availProd,String expMsg) throws Exception {
		 UpdateLookPage.LookPreviewSection.btnMoreDetail(driver,wait,availProd).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template2", "SkuOOSTemp2OOSMsg", expMsg);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnClose(driver, wait).click();
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=5,enabled=true)
	 @Parameters({"sLookURL","sAvailablePrdName","sPDPURL"})   
	 public void SkuAvailableSTemp2Redirect(String lookUrl,String availProd,String pdpURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.btnMoreDetail(driver,wait,availProd).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver, wait, 2);		
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver, wait, 2);;	
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuQTY(driver, wait, 5);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver, wait, "Existing_Look_Template2", "SkuAvailableSTemp2Redirect", pdpURL);
		 SpecialAction.switchToMainWindow(driver);	 
	 }

	
	 @AfterClass
	 public void tearDown() {
		 //driver.quit();
	 }
	  
}
