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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableBiMap.Builder;
import com.thoughtworks.selenium.webdriven.commands.ClickAt;

import utilities.SpecialAction;

import pages.Browser;
import pages.CatalogPage;
import pages.HomePage;
import pages.LoginPage;
import pages.CatalogPage.ProductSection;
import pages.LookPage;
import pages.UpdateLookPage;
import utilities.Log;
import validation.LookValidation;

//import TestNG.Log;

public class NewLook_Template1 {

	WebDriver driver;
	Browser specificBrowser;
	LoginPage loginPage;
	UpdateLookPage updateLookPage;
	HomePage homePage;
	CatalogPage catalogPage;
	LookPage lookPage;
	LookValidation lookValidation;
	SpecialAction specialAction;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Look//New_Look_Template1";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	public NewLook_Template1() {
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
	 
	 //LOGIN TO SITE
	 @Test(priority=0,enabled=true)
	 @Parameters({"sEmail","sPassword"})
	 public void LoginToSite(String email, String password) throws InterruptedException, IOException {
		 loginPage.openLoginPage(driver, "http://qamediacom.objectedge.com/admin/#/login");// QA
		// loginPage.openLoginPage(driver, "https://app.reimucommerce.com/admin/#/login"); //PROD
		 loginPage.email(driver).sendKeys(email);
		 loginPage.password(driver).sendKeys(password);
		 loginPage.btnLogin(driver).click();
		 homePage.menu(driver,wait).click();
		 homePage.menuList(driver,wait, "Add Look").click();
	 }
	 
	 ////CREATING NEW LOOK WITH TEMPLATE 1 (click to see)
	 @Test(priority=1,enabled=true)  
	 @Parameters({"sNewLookName","sLookImageUrl","sAnchorName1","sAnchorName2","sPrd1","sPrd2"})
	 public void createNewLookTempate1(String newLookName,String lookImgUrl,String anchor1,String anchor2,String prd1,String prd2) throws Exception {
		 updateLookPage.txtLookNamefield(driver,wait).sendKeys(newLookName);
		 updateLookPage.btnAddLookImagePlusSign(driver,wait).click();
		 updateLookPage.txtLookImageUrl(driver,wait).sendKeys(lookImgUrl);
		 updateLookPage.btnClose(driver, wait).click();
		 Thread.sleep(2000);
		 //adding 1st product
		 updateLookPage.newAnchorPointLocation(driver,wait);
		 updateLookPage.newAnchorTitle(driver, wait).clear();
		 updateLookPage.newAnchorTitle(driver, wait).sendKeys(anchor1);
		 updateLookPage.btnNewAnchorAddProd(driver,wait).click();
		 UpdateLookPage.NewAnchor.searchFieldPrdInNewAnchor(driver, wait).sendKeys(prd1);
		 UpdateLookPage.NewAnchor.searchSignNewAnchor(driver,wait).click();
		 UpdateLookPage.NewAnchor.searchedPrdResult(driver,wait,prd1).click();
		 UpdateLookPage.NewAnchor.btnAddProducts(driver).click();
		 Thread.sleep(5000);
		 updateLookPage.btnBack(driver,wait).click();
		 Thread.sleep(2000);
		 
		 //adding 2nd product
		 updateLookPage.newAnchorPointLocation2(driver,wait);
		 updateLookPage.newAnchorTitle(driver, wait).clear();
		 updateLookPage.newAnchorTitle(driver, wait).sendKeys(anchor2);
		 updateLookPage.btnNewAnchorAddProd(driver,wait).click();
		 UpdateLookPage.NewAnchor.searchFieldPrdInNewAnchor(driver, wait).sendKeys(prd2);
		 UpdateLookPage.NewAnchor.searchSignNewAnchor(driver,wait).click();
		 UpdateLookPage.NewAnchor.searchedPrdResult(driver,wait,prd2).click();
		 UpdateLookPage.NewAnchor.btnAddProducts(driver).click();
		 Thread.sleep(5000);
		 updateLookPage.btnBack(driver,wait).click();
		 
		 updateLookPage.btnPreview(driver,wait).click();
		 updateLookPage.switchFrame(driver, wait,  "iframeModal");
		 UpdateLookPage.LookPreviewSection.imgOnPreview(driver, wait);
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 lookValidation.testValidationMsg(driver, wait, "NewLook_Template1", "createNewLookTempate1", prd1);
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 lookValidation.testValidationMsg(driver, wait, "NewLook_Template1", "createNewLookTempate1", prd2);
	 }
	 
	 //VALIDATING DISPLAYING MSG FOR OOS SKU ON PREVIEW PAGE
	 @Test(priority=2,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({"sSkuOOSmsg"})    
	 public void SkuOOSTemp1OOSMsg(String expMsg) throws Exception {
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		 lookValidation.testValidationMsg(driver, wait, "NewLook_Template1","SkuOOSTemp1OOSMsg",expMsg);
	 }
	 
	 //VALIDATING USER REDIRECTION TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=3,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({"sPDPURL"})    
	 public void SkuAvailableSTemp1Redirect(String pdpURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 UpdateLookPage .LookPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver, wait, "NewLook_Template1","SkuAvailableSTemp1Redirect", pdpURL);
		 SpecialAction.switchToMainWindow(driver);	 
	 }
	 	 
	 //validating OOS PRODUCT msg  ON PREVIEW PAGE
	 @Test(priority=4,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({"sPrOOSmsg"})  
	 public void ProdOOSTemp1OOSMsg(String prdOOSMsg) throws Exception {
		 updateLookPage.switchFrame(driver,  wait, "iframeModal");
		 Thread.sleep(2000);
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 lookValidation.testValidationMsg(driver,wait, "NewLook_Template1","ProdOOSTemp1OOSMsg",prdOOSMsg);
	 }
	 
	 //IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
     //VALIDATING USER REDIRECTION TO CATEGORY PAGE FOR OOS PRODUCT
	 @Test(priority=5,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({"sCategoryURL"})    
	 public void ProdOOSTemp1OOSRedirect1(String prdCatURL) throws Exception {
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 Thread.sleep(2000);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver,wait, "NewLook_Template1", "ProdOOSTemp1OOSRedirect1", prdCatURL);	
		 SpecialAction.switchToMainWindow(driver);
	 }
	 
	 //IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
     //VALIDATING USER REDIRECTION TO CATEGORY PAGE FOR OOS PRODUCT
	 @Test(priority=6,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({"sCategoryURL"})   
	 public void ProdOOSTemp1OOSRedirect2(String prdCatURL) throws Exception {	
		 Thread.sleep(5000);
		 updateLookPage.switchFrame(driver,  wait, "iframeModal");
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$1.0.0",wait).click();
		 Thread.sleep(2000);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateLookPage.LookPreviewSection.ProductDetailSection.btnAddToCart(driver).click(); 
		 SpecialAction.switchNextWindow(driver);
		 lookValidation.testValidationMsg(driver, wait, "NewLook_Template1","ProdOOSTemp1OOSRedirect2", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
	 }
	  
	 //TAKING THE SNIPPET OF NEWLY CREATED LOOK
	 @Test(priority=7,dependsOnMethods = "createNewLookTempate1",enabled=true)
	 @Parameters({})  
	 public void Snippet() throws Exception {
		 Thread.sleep(2000);
		 String snippetText = null;
		 UpdateLookPage.LookPreviewSection.btnClose(driver, wait).click();
		 Thread.sleep(2000);
		 updateLookPage.btnLookSnippet(driver,wait).click();	 
		 snippetText = updateLookPage.snippetText(driver);	
		 System.out.println("Snippet New Look Template1::"+snippetText);
		 updateLookPage.btnCloseSign(driver, wait).click();
	 }
	
	 //remove anchor
	 @Test(priority=8,dependsOnMethods = {"createNewLookTempate1","Snippet"},enabled=true)
	 @Parameters({"sPrd1"})    
	 public void removeAnchor(String prd1) throws Exception {	 
		 Thread.sleep(2000);
		 updateLookPage.btnEditAnchor(driver, wait, 1).click();
		 updateLookPage.btnRemoveAnchor(driver, wait).click();
		 updateLookPage.btnOK(driver,wait).click();
		 updateLookPage.btnPreview(driver, wait).click();
		 //Thread.sleep(20000);
		 updateLookPage.switchFrame(driver, wait, "iframeModal");
		 UpdateLookPage.LookPreviewSection.imgOnPreview(driver, wait);
		 UpdateLookPage.LookPreviewSection.cuePoint(driver, ".0.0.0.0.0.1:$0.0.0",wait).click();
		 lookValidation.testValidationMsg(driver,wait, "NewLook_Template1","removeAnchor",prd1);
	 }
	 
	//DELETING LOOK
	 @Test(priority=9,dependsOnMethods = {"createNewLookTempate1","removeAnchor"},enabled=true)
	 @Parameters({"sNewLookName","sDeleteLookmsg"})
	 public void deleteLook(String newLookName, String expMsg) throws Exception {
		 driver.get("http://qamediacom.objectedge.com/admin/#/looks?");
		 driver.navigate().refresh();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys(newLookName);
		 lookPage.btnSearch(driver,wait).click();	
		 lookPage.btnTool(driver,wait).click();
		 lookPage.lnkDeleteLook(driver,wait).click();
		 lookPage.btnOKDeleteLook(driver, wait).click();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys(newLookName);
		 lookPage.btnSearch(driver,wait).click();
		 lookValidation.testValidationMsg(driver,wait, "NewLook_Template1","deleteLook",expMsg);		 
	 }

	 @AfterClass
	 public void tearDown() {
		 //driver.quit();
	 }
	  
}
