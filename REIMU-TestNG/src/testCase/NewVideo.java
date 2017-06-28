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

import pages.AddVideoPage;
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

public class NewVideo {

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
	AddVideoPage addVideoPage;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Video//NewVideo//";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	List<WebElement> elementList = null;
	public NewVideo() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		updateLookPage = new UpdateLookPage(driver);
		specialAction = new SpecialAction(driver);
		homePage =new HomePage(driver);
		catalogPage = new CatalogPage(driver);
		lookPage = new LookPage(driver);
		videoPage = new VideoPage(driver);
		updateVideoPage = new UpdateVideoPage(driver);
		addVideoPage = new AddVideoPage(driver);
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
	 @Parameters({"sEmail","sPassword"})
	 public void LoginToSite(String email, String password) throws InterruptedException, IOException {
		 loginPage.openLoginPage(driver, "http://qamediacom.objectedge.com/admin/#/login");// QA
		// loginPage.openLoginPage(driver, "https://app.reimucommerce.com/admin/#/login"); //PROD
		 loginPage.email(driver).sendKeys(email);
		 loginPage.password(driver).sendKeys(password);
		 loginPage.btnLogin(driver).click();
		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userDropDown")));
		 homePage.menu(driver,wait).click();
		 homePage.menuList(driver,wait, "Add Video").click();
	 }
	 
     ///////////////////////////*CREATING NEW VIDEO*///////////////////////////////////
	 @Test(priority=1,enabled=true)
	 @Parameters({"sVideoURL","sVideoName","sKeywords","sAnchorname1","sAnchorname2","sTitleImage","sVideoDescription","sOOSPrdName","sAvailablePrdName","sProduct3","sProduct4","sProduct5","sProduct6"}) //CREATING NEW LOOK FOR TEMPLATE2 AND VALIDATING PRODUCT ATTACHED TO LOOK OR NOT
	 public void createNewVideo(String videoUrl,String videoName,String keywords,String anchorName1,String anchorName2,String titleImage,String videoDesc,String oosPrd,String availPrd,String prd3,String prd4,String prd5,String prd6) throws Exception {
		 addVideoPage.bxVideoFileNamefield(driver,wait).sendKeys(videoName);
		 addVideoPage.bxVideoUrl(driver, wait).sendKeys(videoUrl);
		 addVideoPage.bxKeywords(driver, wait).sendKeys(keywords);
		 addVideoPage.lnkAnchorat0Position(driver, wait).click();
		 addVideoPage.bxAnchorName(driver, wait).sendKeys(anchorName1);
		 addVideoPage.bxTitleImage(driver, wait).sendKeys(titleImage);
		 addVideoPage.bxVideoDescription(driver, wait).sendKeys(videoDesc);
		 addVideoPage.btnSaveChanges(driver, wait).click();
		 Thread.sleep(5000);
		 updateVideoPage.btnEditAnchor(driver, wait,anchorName1).click();		 
		 updateVideoPage.btnAddProduct(driver, wait).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(oosPrd);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, oosPrd).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(availPrd);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, availPrd).click();
		 updateVideoPage.btnAddProducts(driver, wait).click();
		 updateVideoPage.btnOk(driver, wait).click();
		 
		 
		 updateVideoPage.btnPlayVideo(driver, wait).click();
		 Thread.sleep(30000);
		 updateVideoPage.btnAddAnchor(driver, wait).click();
		 updateVideoPage.boxTitle(driver, wait).sendKeys(anchorName2);
		 updateVideoPage.boxTitleImageURL(driver, wait).sendKeys(titleImage);
		 updateVideoPage.boxDescription(driver, wait).sendKeys(videoDesc);
		 updateVideoPage.boxToolTip(driver, wait).sendKeys("toolTip");
		 updateVideoPage.btnSave(driver, wait).click();
		 updateVideoPage.btnEditAnchor(driver, wait,anchorName2).click();		 
		 updateVideoPage.btnAddProduct(driver, wait).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(prd3);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, prd3).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(prd4);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, prd4).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(prd5);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, prd5).click();
		 updateVideoPage.bxSearch(driver, wait).clear();
		 updateVideoPage.bxSearch(driver, wait).sendKeys(prd6);
		 updateVideoPage.btnSearch(driver, wait).click();
		 updateVideoPage.searchedPrdResult(driver, wait, prd6).click();
		 updateVideoPage.btnAddProducts(driver, wait).click();
		 updateVideoPage.btnOk(driver, wait).click();
		 
		 
		 
		 updateVideoPage.btnPreview(driver, wait).click();
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 videoValidation.testValidationMsg(driver, wait, "NewVideo", "createNewVideo", oosPrd+"+"+availPrd+"*"+anchorName1);
		 Thread.sleep(30000);
		 videoValidation.testValidationMsg(driver, wait,"NewVideo", "createNewVideo", prd3+"+"+prd4+"*"+anchorName2);
		 driver.switchTo().defaultContent();
		 UpdateVideoPage.VideoPreviewSection.btnCloseSign(driver, wait).click();
	 }
	 
     
	 ////////////////////////////*Easterly*////////////////////////////////
	 
	 //VALIDATING OOS PRODUCT MSG
	 @Test(priority=2,dependsOnMethods={"createNewVideo"},enabled=true)
	 @Parameters({"sOOSPrdName","sPrOOSmsg"})  
	 public void ProdOOSMsg_Easterly(String oosPrdName,String expMsg) throws Exception {
		 Thread.sleep(2000);
		 updateVideoPage.btnTemplate(driver, wait, "Easterly").click();
		 Thread.sleep(5000);
		 updateVideoPage.btnPreview(driver,wait).click();			 
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"DETAIL").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.alermsgOOS(driver, wait);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSMsg_Easterly",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=3,dependsOnMethods={"ProdOOSMsg_Easterly"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})    
	 public void ProdOOSRedirect1_Easterly(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"DETAIL").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver, wait,"NewVideo","ProdOOSRedirect1_Easterly", prdCatURL);	
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=4,dependsOnMethods={"ProdOOSRedirect1_Easterly"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})   
	 public void ProdOOSRedirect2_Easterly(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"DETAIL").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSRedirect2_Easterly", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=5,dependsOnMethods={"ProdOOSRedirect2_Easterly"},enabled=true)
	 @Parameters({"sAvailablePrdName","sSkuOOSmsg"})
	 public void SkuOOSMsg_Easterly(String availablePrdName,String expMsg) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,"DETAIL").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","SkuOOSMsg_Easterly",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=6,dependsOnMethods={"SkuOOSMsg_Easterly"},enabled=true)
	 @Parameters({"sAvailablePrdName","sPDPURL"})
	 public void SkuAvailableRedirect_Easterly(String availablePrdName,String pdpURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,"DETAIL").click();
		 UpdateVideoPage .VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait, "NewVideo","SkuAvailableRedirect_Easterly", pdpURL);
		 SpecialAction.switchToMainWindow(driver);
		 //UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.btnCloseSign(driver, wait).click();
	 }
	 
	 @Test(priority=7,dependsOnMethods={"SkuAvailableRedirect_Easterly"},enabled=true)
	 @Parameters()
	 public void SnippetVideoEasterly() throws Exception {
		 String snippetText = null;
		 Thread.sleep(2000);
		 updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		 snippetText = updateVideoPage.snippetText(driver);	
		 System.out.println("NEW VIDEO-EASTERLY SNIPPET:"+snippetText);
		 updateVideoPage.btnCloseSign(driver, wait).click();
	 }
	 
	 ////////////////////////////*Southerly*////////////////////////////////
	 
	 //VALIDATING OOS PRODUCT MSG
	 @Test(priority=8,dependsOnMethods={"createNewVideo","SnippetVideoEasterly"},enabled=true)
	 @Parameters({"sOOSPrdName","sPrOOSmsg"})  
	 public void ProdOOSMsg_Southerly(String oosPrdName,String expMsg) throws Exception {
		 Thread.sleep(2000);
		 updateVideoPage.btnTemplate(driver, wait, "Southerly").click();
		 Thread.sleep(5000);
		 updateVideoPage.btnPreview(driver,wait).click();			 
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"Details").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.alermsgOOS(driver, wait);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSMsg_Southerly",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=9,dependsOnMethods={"ProdOOSMsg_Southerly"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})    
	 public void ProdOOSRedirect1_Southerly(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"Details").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver, wait,"NewVideo","ProdOOSRedirect1_Southerly", prdCatURL);	
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=10,dependsOnMethods={"ProdOOSRedirect1_Southerly"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})   
	 public void ProdOOSRedirect2_Southerly(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, oosPrdName,"Details").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSRedirect2_Southerly", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=11,dependsOnMethods={"ProdOOSRedirect2_Southerly"},enabled=true)
	 @Parameters({"sAvailablePrdName","sSkuOOSmsg"})
	 public void SkuOOSMsg_Southerly(String availablePrdName,String expMsg) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,"Details").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","SkuOOSMsg_Southerly",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=12,dependsOnMethods={"SkuOOSMsg_Southerly"},enabled=true)
	 @Parameters({"sAvailablePrdName","sPDPURL"})
	 public void SkuAvailableRedirect_Southerly(String availablePrdName,String pdpURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.btnDetail(driver, wait, availablePrdName,"Details").click();
		 UpdateVideoPage .VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait, "NewVideo","SkuAvailableRedirect_Southerly", pdpURL);
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.btnCloseSign(driver, wait).click();
	 }
	 	 
	 @Test(priority=13,dependsOnMethods={"SkuAvailableRedirect_Southerly"},enabled=true)
	 @Parameters()
	 public void SnippetVideoSoutherly() throws Exception {
		 String snippetText = null;
		 Thread.sleep(2000);
		 updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		 snippetText = updateVideoPage.snippetText(driver);	
		 System.out.println("NEW VIDEO-SOUTHERLY SNIPPET:"+snippetText);
		 updateVideoPage.btnCloseSign(driver, wait).click();
	 }
	 
	 ////////////////////////////*Doldrums*////////////////////////////////
	 
	 //VALIDATING OOS PRODUCT MSG- *EASTERLY*
	 @Test(priority=14,dependsOnMethods={"createNewVideo","SkuAvailableRedirect_Southerly"},enabled=true)
	 @Parameters({"sOOSPrdName","sPrOOSmsg"})  
	 public void ProdOOSMsg_Doldrums(String oosPrdName,String expMsg) throws Exception {
		 Thread.sleep(2000);
		 updateVideoPage.btnTemplate(driver, wait, "Doldrums").click();
		 Thread.sleep(5000);
		 updateVideoPage.btnOKIgetIt(driver, wait).click();	 
		 updateVideoPage.btnPreview(driver,wait).click();			 
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.btnPayVideo(driver, wait).click();
		 UpdateVideoPage.VideoPreviewSection.btnShop(driver, wait).click();
		 Thread.sleep(5000);
		 UpdateVideoPage.VideoPreviewSection.imagePrd(driver, wait, ".0.1.0.0.0.0:$0");
		 UpdateVideoPage.VideoPreviewSection.btnPlusSignOnProduct(driver, wait, ".0.1.0.0.0.0:$0.1").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.alermsgOOS(driver, wait);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSMsg_Doldrums",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE NOT SELECTING THE SKU. JUST DIRECT CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=15,dependsOnMethods={"ProdOOSMsg_Doldrums"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})    
	 public void ProdOOSRedirect1_Doldrums(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.imagePrd(driver, wait, ".0.1.0.0.0.0:$0");
		 UpdateVideoPage.VideoPreviewSection.btnPlusSignOnProduct(driver, wait, ".0.1.0.0.0.0:$0.1").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver, wait,"NewVideo","ProdOOSRedirect1_Doldrums", prdCatURL);	
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //OOS PRODUCT REDIRECTION- IN THIS TEST WE ARE  SELECTING THE SKU AND THEN CLICKING ON ADD TO CART BUTTON FOR OOS PRODUCT
	 @Test(priority=16,dependsOnMethods={"ProdOOSRedirect1_Doldrums"},enabled=true)
	 @Parameters({"sOOSPrdName","sCategoryURL"})   
	 public void ProdOOSRedirect2_Doldrums(String oosPrdName,String prdCatURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.imagePrd(driver, wait, ".0.1.0.0.0.0:$0");
		 UpdateVideoPage.VideoPreviewSection.btnPlusSignOnProduct(driver, wait, ".0.1.0.0.0.0:$0.1").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","ProdOOSRedirect2_Doldrums", prdCatURL);
		 SpecialAction.switchToMainWindow(driver);
		 UpdateVideoPage.VideoPreviewSection.switchFrame(driver, wait, "iframeModal");
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //DISPLAYING MSG FOR OOS SKU
	 @Test(priority=17,dependsOnMethods={"ProdOOSRedirect2_Doldrums"},enabled=true)
	 @Parameters({"sAvailablePrdName","sSkuOOSmsg"})
	 public void SkuOOSMsg_Doldrums(String availablePrdName,String expMsg) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.imagePrd(driver, wait, ".0.1.0.0.0.0:$1");
		 UpdateVideoPage.VideoPreviewSection.btnPlusSignOnProduct(driver, wait, ".0.1.0.0.0.0:$1.1").click();
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,3);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait).click(); 
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","SkuOOSMsg_Doldrums",expMsg);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnClose(driver,wait).click();
	 }
	 
	 //USER REDIRECTING TO PDP PAGE OF PRODUCT FOR AVAILABLE SKU
	 @Test(priority=18,dependsOnMethods={"SkuOOSMsg_Doldrums"},enabled=true)
	 @Parameters({"sAvailablePrdName","sPDPURL"})
	 public void SkuAvailableRedirect_Doldrums(String availablePrdName,String pdpURL) throws Exception {
		 UpdateVideoPage.VideoPreviewSection.imagePrd(driver, wait, ".0.1.0.0.0.0:$1");
		 UpdateVideoPage.VideoPreviewSection.btnPlusSignOnProduct(driver, wait, ".0.1.0.0.0.0:$1.1").click();
		 UpdateVideoPage .VideoPreviewSection.ProductDetailSection.skuColor(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuSize(driver,wait,2);
		 UpdateVideoPage.VideoPreviewSection.ProductDetailSection.skuQTY(driver,wait,3);
		 Thread.sleep(2000);
		 element  = UpdateVideoPage.VideoPreviewSection.ProductDetailSection.btnAddToCart(driver,wait);
	     SpecialAction.mouseAction(driver, element,"click").perform();
		 SpecialAction.switchNextWindow(driver);
		 videoValidation.testValidationMsg(driver,wait,"NewVideo", "SkuAvailableRedirect_Doldrums", pdpURL);
		 SpecialAction.switchToMainWindow(driver);	
		 UpdateVideoPage.VideoPreviewSection.btnCloseSign(driver, wait).click();
	 }
	 
	 @Test(priority=19,dependsOnMethods={"SkuAvailableRedirect_Doldrums"},enabled=true)
	 @Parameters()
	 public void SnippetVideoDoldrums() throws Exception {
		 String snippetText = null;
		 Thread.sleep(2000);
		 updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		 snippetText = updateVideoPage.snippetText(driver);	
		 System.out.println("NEW VIDEO-DOLDRUMS SNIPPET:"+snippetText);
		 updateVideoPage.btnCloseSign(driver, wait).click();
	 }
	 
	 @Test(priority=20,dependsOnMethods={"SnippetVideoDoldrums"},enabled=true)
	 @Parameters({"sVideoName"})
	 public void deleteNewVideo(String videoName) throws Exception {
		 driver.get("http://qamediacom.objectedge.com/admin/#/videos");
		 Thread.sleep(5000);
		 videoPage.searchBx(driver,wait).clear();
		 videoPage.searchBx(driver,wait).sendKeys(videoName);
		 videoPage.btnSearch(driver,wait).click();	
         videoPage.btnTool(driver, wait).click();
         videoPage.lnkDeleteVideo(driver, wait).click();
         videoPage.btnOk(driver, wait).click();
         videoPage.searchBx(driver,wait).clear();
		 videoPage.searchBx(driver,wait).sendKeys(videoName);
		 videoPage.btnSearch(driver,wait).click();
		 videoValidation.testValidationMsg(driver,wait,"NewVideo","deleteNewVideo","No Videos found! Please change your search filter.");
		 
	 }
	 
	 
	 
	 @AfterClass
	 public void tearDown() {
		 driver.close();
		 //driver.quit();
	 }
	  
}
