package testCase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

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
import pages.CatalogPage.ProductSection;
import utilities.Log;
import validation.CatalogValidation;

//import TestNG.Log;

public class Catalog {

	WebDriver driver;
	Browser specificBrowser;
	CatalogPage catalogPage;
	HomePage homePage;
	LoginPage loginPage;
	CatalogValidation catalogValidation;
	SpecialAction specialAction;
	final static String ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Catalog//";
	final static String automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
	private WebDriverWait wait;
	private WebElement element = null;
	String successMsg = null;
	String failMsg = null;
	String screenShotName = null;

	public Catalog() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		homePage =new HomePage(driver);
		catalogPage = new CatalogPage(driver);	
		specialAction = new SpecialAction(driver);
		catalogValidation = new CatalogValidation(driver,ScreenshotLocation,automationStartTime);
	}
	
	 @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
			
				//Log.startTestCase("Registration");
				//filePath = "D://workspaces//Selenuim//BB2.4//test-output//TestOutPut.xls";
				//ExcelUtils.setExelFile(filePath);
				//ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Catalog//";
				//automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

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
	 
	 //LOGIN//
	 @Test(priority=0,enabled=true)
	 @Parameters({"sEmail","sPassword"})
	 public void LoginToSite(String email, String password) throws InterruptedException, IOException {
		 loginPage.openLoginPage(driver, "http://qamediacom.objectedge.com/admin/#/login");
		 loginPage.email(driver).sendKeys(email);
		 loginPage.password(driver).sendKeys(password);
		 loginPage.btnLogin(driver).click();
		 homePage.menu(driver,wait).click();
		 homePage.menuList(driver,wait, "Catalog").click();
	 }
	
	 //UPLOAD PRODUCT FILE WHICH INCLUDE NEW AND EXISTING PRODUCTS. EXISTING PRODUCT FOR VERYING OVERWRITE PROPERTIES//
	 @Test(priority=1,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName","sPrdID"})//uploading all the products at a time which are needed in our testing (new product, product for deletion, existing product).
	 public void uploadProductFile(String prodName, String prodID) throws Exception {	
		 catalogPage.btnAddProduct(driver,wait).click();
		 catalogPage.btnChooseFile(driver,wait).click();
		 Runtime.getRuntime().exec("D://workspaces//Selenuim//REIMU-TestNG//src//utilities//ReimuProductFileUpload.exe");
         Thread.sleep(2000);
         SpecialAction.getResultantScreenshot(driver, "uploadProductFile", automationStartTime, ScreenshotLocation);
         catalogPage.btnSave(driver,wait).click();
         catalogValidation.verifyProductsPresence(driver,wait,"uploadProductFile",prodName,prodID);  //verifying new product uploaded or not
	 }
	 
	//UPLOAD SKU FILE WHICH INCLUDE NEW AND EXISTING SKU. EXISTING SKU FOR VERYING OVERWRITE PROPERTIES//
	 @Test(priority=2,dependsOnMethods={"LoginToSite","uploadProductFile"},enabled=true)
	 @Parameters({"sPrdName","sPrdID","sSkuID","sSkuName"}) //uploading all the skus at a time which are needed in our testing (new sku, sku for deletion, existing sku).
	 public void uploadSkuFile(String prodName, String prodID,String skuID,String skuName) throws Exception {	
		 element = null;
		 catalogPage.btnAddProduct(driver,wait).click();
		 catalogPage.btnChooseFile(driver,wait).click();
		 Runtime.getRuntime().exec("D://workspaces//Selenuim//REIMU-TestNG//src//utilities//ReimuSkuFileUpload.exe");
		 Thread.sleep(2000);
		 SpecialAction.getResultantScreenshot(driver, "uploadSkuFile", automationStartTime, ScreenshotLocation);
         catalogPage.btnSave(driver,wait).click();
         catalogValidation.verifySkuPresence(driver,wait,"uploadSkuFile",prodName,prodID,skuID,skuName); //verifying new sku uploaded or not
	 }
	 
	 //SEARCH PRODUCT FUNCTIONALITY//
	 @Test(priority = 3,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sExistPrdName"})
	 public void searchProduct(String prodName) throws Exception {
		 catalogValidation.verifyProductsPresence(driver,wait,"searchProduct",prodName,""); //verifying search functionality
	 }
	 
	 //VALIDATING DUPLICATE PRODUCT CREATION WHILE UPLOADING EXISTING PRODUCT FILE//
	 @Test(priority = 4,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sExistPrdName"})
	 public void existingProductOverwriteAfterFileUpload(String prodName) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);
		 driver.navigate().refresh();
		 catalogValidation.VerifyExistingProduct(driver, wait, "existingProductOverwriteAfterFileUpload", prodName); //verifying existing products not get created duplicate. 
	 }
	 
	//VALIDATING DUPLICATE SKU CREATION WHILE UPLOADING EXISTING SKU FILE//
	 @Test(priority = 5,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sExistPrdName","sExistSkuName","sExistSkuID"})
	 public void existingSkuOverwriteAfterFileUpload(String prodName, String skuName, String skuID) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);	
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(5000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 Thread.sleep(2000);
		 CatalogPage.EditProductSection.btnSKU(driver,wait).click();
		 catalogValidation.VerifyExistingSku(driver, wait, "existingSkuOverwriteAfterFileUpload", skuName, skuID);
		 
	 }
	 
	 //VERIFYING EXTRA EMPTY PRODUCT NOT GETTING CREATED AFTER PRODUCT FILE UPLOAD//
	 @Test(priority = 6,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName","sPrdID"})
	 public void extraEmptyProduct(String prodName, String prodID) throws Exception {
		 element = null;
		 catalogPage.openCatalogPage(driver, "http://qamediacom.objectedge.com/admin/#/products");	
		 catalogValidation.verifyProductsPresence(driver,wait,"extraEmptyProduct","","");
	 }
	
	 //VERIFYING CURRECY SYMBOL FOR USD//
	 @Test(priority = 7,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName"})
	 public void currencySignUSD(String prodName) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);
		 driver.navigate().refresh();
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(2000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
	     CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
	     CatalogPage.EditProductSection.txtBxCurrency(driver, wait).clear();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).sendKeys("USD");
		 CatalogPage.EditProductSection.btnSave(driver, wait).click();
		 catalogValidation.currencyVeify(driver,wait,"currencySignUSD");
				
	 }
	 
	//VERIFYING CURRECY SYMBOL FOR INR//
	 @Test(priority = 8,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName"})
	 public void currencySignINR(String prodName) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);
		 driver.navigate().refresh();
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);	
		 Thread.sleep(2000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).clear();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).sendKeys("INR");
		 CatalogPage.EditProductSection.btnSave(driver, wait).click();
		 catalogValidation.currencyVeify(driver,wait,"currencySignINR");		
	 }
	 
	//VERIFYING CURRECY SYMBOL FOR BRT//
	 @Test(priority = 9,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName"})
	 public void currencySignBRT(String prodName) throws Exception {
		 element = null;	 
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);
		 driver.navigate().refresh();
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);	
		 Thread.sleep(2000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).clear();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).sendKeys("BRT");
		 CatalogPage.EditProductSection.btnSave(driver, wait).click();
		 catalogValidation.currencyVeify(driver,wait,"currencySignBRT");		
	 }

	 //VERYING PRODUCT PROPERTIES OVERWRITE CORRECTLY FOR EXISTING PRODUCT//
	 @Test(priority = 10,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sExistPrdName","sExistPrdID","sCatID","sCatName","sCatURL","sPrdDesc","sPDPURL","sPrdLargeImageURL","sPrdSmallImageURL","sPrdThumbnailImageURL","sCurrency"})
	 public void prdProperties(String prodName, String prodID,String catID,String catName,String catURL, String PrdDesc,String PDPURL,String prdLargeImageURL, String prdSmallImageURL,String prdThumbnailImageURL,String currency) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodID);
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(5000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 catalogValidation.verifyProductProperties(driver, wait,"prdProperties", prodName, prodID, catID, catName, catURL, PrdDesc, PDPURL, prdLargeImageURL, prdSmallImageURL, prdThumbnailImageURL, currency);
	 }
	 
	//VERYING SKU PROPERTIES OVERWRITE CORRECTLY FOR EXISTING SKU//
	 @Test(priority = 11,dependsOnMethods={"LoginToSite","prdProperties"},enabled=true)
	 @Parameters({"sExistPrdName","sExistPrdID","sExistSkuID","sExistSkuName","sColor","sSize","sInventory","sSkuDesc","sListPrice","sSalePrice"})
	 public void skuProperties(String prodName, String prodID,String skuID,String skuName,String color, String size,String inventory,String skuDesc, String listPrice,String salePrice) throws Exception {
		 CatalogPage.EditProductSection.btnSKU(driver,wait).click();
		 CatalogPage.EditProductSection.btnEditSku(driver, wait, skuID).click();
		 catalogValidation.verifySkuProperties(driver, wait,"skuProperties", prodName, prodID, skuID, skuName, color, size, inventory, skuDesc, listPrice, salePrice);
	 }
	
	 //TRYING TO DELETE SKU FROM PRODUCT//
	 @Test(priority = 12,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName","sPrdID","sSkuID","sSkuName"})
	 public void deleteSku(String prodName, String prodID,String skuID,String skuName) throws Exception {
		 prodName = "ready, set, BROW! clear brow gel";
		 prodID ="prod26012";
		 skuID = "xsku6013";
		 skuName = "Selenium Test Sku 1";
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodID);
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(5000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 CatalogPage.EditProductSection.btnSKU(driver,wait).click();
		 CatalogPage.EditProductSection.btnDeleteSku(driver, wait,skuID).click();
		 CatalogPage.EditProductSection.btnSkuDeleteYes(driver, wait).click();
		 catalogValidation.verifyDeletedSku(driver, wait, "deleteSku", prodName, prodID, skuID, skuName);
	 }
	 
	
	 //TRYING TO DELETE PRODUCT USING DELETE PRODUCT LINK//
	 @Test(priority = 13,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName","sPrdID"})
	 public void deleteProductByPrdMenuToolTip(String prodName, String prodID) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodID);
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductDelete(driver,wait, element).click();
		 CatalogPage.ProductSection.btnOKDeleteProduct(driver,wait).click();
		 Thread.sleep(2000);
		 catalogValidation.verifyProductsPresence(driver,wait,"deleteProductByPrdMenuToolTip",prodName,prodID);
	 }
	 
	 //TRYING TO SELECT MORE THAN ONE PRODUCT AT A TIME  USING RADIO BUTTON FROM PRODUCTS//
	 @Test(priority = 14,dependsOnMethods={"LoginToSite"},enabled=true)
	 @Parameters({"sPrdName","sPrdID"})
	 public void selectMoreThanOneProduct(String prodName, String prodID) throws Exception {
		 prodName = "delete more than one product";
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodName);
		 driver.navigate().refresh();
		 CatalogPage.ProductSection.radioBtnProd(driver, wait);
		 SpecialAction.getResultantScreenshot(driver, "PASS-Catalog-selectMoreThanOneProduct", automationStartTime, ScreenshotLocation);
		 Reporter.log("selectMoreThanOneProduct -  SUCCESSFULLY");
	 }
	 
	 //TRYING TO DELETE MORE THAN ONE PRODUCT AT A TIME  USING DELETE PRODUCTS BUTTON//
	 @Test(priority = 15,dependsOnMethods={"LoginToSite","selectMoreThanOneProduct"},enabled=true)
	 @Parameters({"sPrdName","sPrdID"})//in this method, selected products gets delete only. All products selection done in above method only "selectMoreThanOneProduct"
	 public void deleteMoreThanOneProduct(String prodName, String prodID) throws Exception {
		 prodName = "delete more than one product";
		 catalogPage.btnDeleteChecked(driver, wait).click();
		 CatalogPage.ProductSection.btnOKDeleteProduct(driver,wait).click();
		 catalogValidation.verifyProductsPresence(driver,wait,"deleteMoreThanOneProduct",prodName,"");
	 }
	  
	//VERYING PRODUCT REQUIRED PROPERTIES//
	 @Test(priority = 16,dependsOnMethods={"LoginToSite","uploadProductFile","uploadSkuFile"},enabled=true)
	 @Parameters({"sBlankPrdName","sBlankPrdID","sCatID","sCatName","sCatURL","sPrdDesc","sPDPURL","sPrdLargeImageURL","sPrdSmallImageURL","sPrdThumbnailImageURL","sCurrency"})
	 public void prdRequiredProperties(String prodName, String prodID,String catID,String catName,String catURL, String PrdDesc,String PDPURL,String prdLargeImageURL, String prdSmallImageURL,String prdThumbnailImageURL,String currency) throws Exception {
		 element = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products?pageNum=1&searchTerm="+prodID);
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(5000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 CatalogPage.EditProductSection.txtPrdName(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPrdID(driver, wait).clear();
		 CatalogPage.EditProductSection.txtCatID(driver, wait).clear();
		 CatalogPage.EditProductSection.txtCatName(driver, wait).clear();
		 CatalogPage.EditProductSection.txtCatURL(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPrdDescription(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPDPURL(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPrdLargeImageURLL(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPrdSmallImageURL(driver, wait).clear();
		 CatalogPage.EditProductSection.txtPrdThumbnailImageURL(driver, wait).clear();
		 CatalogPage.EditProductSection.txtBxCurrency(driver, wait).clear();
		 CatalogPage.EditProductSection.btnSave(driver, wait).click();
		 catalogValidation.verifyRequiredParameter(driver, wait, "prdRequiredProperties");
	 }
	 
	//VERYING PRODUCT REQUIRED PROPERTIES//
		 @Test(priority = 17,dependsOnMethods={"LoginToSite","prdRequiredProperties","uploadSkuFile"},enabled=true)
		 @Parameters({"sBlankPrdName","sBlankPrdID","sBlankSkuID","sBlankSkuName","sCatURL","sPrdDesc","sPDPURL","sPrdLargeImageURL","sPrdSmallImageURL","sPrdThumbnailImageURL","sCurrency"})
		 public void skuRequiredProperties(String prodName, String prodID,String skuID,String skuName,String catURL, String PrdDesc,String PDPURL,String prdLargeImageURL, String prdSmallImageURL,String prdThumbnailImageURL,String currency) throws Exception {
			 CatalogPage.EditProductSection.btnSKU(driver,wait).click();
			 CatalogPage.EditProductSection.btnEditSku(driver, wait, skuID).click();
			 CatalogPage.EditProductSection.txtSkuName(driver, wait).clear();
			 CatalogPage.EditProductSection.txtSkuID(driver, wait).clear();
			 CatalogPage.EditProductSection.txtColor(driver, wait).clear();
			 CatalogPage.EditProductSection.txtSize(driver, wait).clear();
			 CatalogPage.EditProductSection.txtInventory(driver, wait).clear();
			 CatalogPage.EditProductSection.txtSkuDescription(driver, wait).clear();
			 CatalogPage.EditProductSection.txtListPrice(driver, wait).clear();
			 CatalogPage.EditProductSection.txtSalePrice(driver, wait).clear();
			 CatalogPage.EditProductSection.btnSkuSave(driver, wait).click();
			 catalogValidation.verifyRequiredParameter(driver, wait, "skuRequiredProperties");
		 }

	 
	 @AfterClass
	 public void tearDown() {
		 //driver.quit();
	 }
	  
}
