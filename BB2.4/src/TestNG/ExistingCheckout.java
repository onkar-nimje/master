package TestNG;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Check;

import Pages.CheckoutLoginPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LocalPopupPage;
import Pages.LoginPage;
import Pages.Browser;
import Pages.OrderConfirmationPage;
import Pages.PDPPage;
import Utilities.SpecialAction;

public class ExistingCheckout {
	  WebDriver driver;
	  LoginPage pageLogin;
	  HomePage pageHome;
	  Browser specificBrowser;
	  PDPPage pdpPage;
	  CheckoutLoginPage checkoutLoginPage;
	  CheckoutPage checkoutPage;
	  LocalPopupPage localePOPUP;
	  SpecialAction specialAction;
	  OrderConfirmationPage orderConfirmation;
	  Actions builder;
	  Action act;
	  WebDriverWait wait;
	  String timeInMilisecond="Test"+System.currentTimeMillis();
	  String ScreenshotLocation = null;
	  String automationStartTime = null;
	  
	  public ExistingCheckout() {
		  pageLogin = new LoginPage(driver);
		  pageHome = new HomePage(driver);
		  specificBrowser = new Browser(driver);
		  pdpPage = new PDPPage(driver);
		  checkoutLoginPage = new CheckoutLoginPage(driver);
		  checkoutPage = new CheckoutPage(driver);
		  localePOPUP = new LocalPopupPage(driver);
		  specialAction = new SpecialAction(driver);
		  orderConfirmation = new OrderConfirmationPage(driver);
	  }
	  
	  @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
			
				Log.startTestCase("Registration");
				ScreenshotLocation = "D://workspaces//Selenuim//BB2.4//test-output//OutPut//Screenshot//";
				automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
				
				
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
			Log.info(e.toString());
		}
	    
	  }
	  
	  @Test(priority=0,enabled=true)
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome"})
		 public void existingUserlogin(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg) throws Exception
		 {
		  pageLogin.openLoginPageWithLocal(driver, baseUrl, local);
		  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='loginForm']/input[4]"))));
		  pageLogin.email(driver).clear();
		  pageLogin.email(driver).sendKeys(email);
		  pageLogin.password(driver).clear();
		  pageLogin.password(driver).sendKeys(password);
		  pageLogin.buttonSIGNIN(driver,local).click();
		  Thread.sleep(2000);	
		  pageLogin.verificationMsg(driver,welcomeMsg);
		 }
	  
	  
	  
	  @Test(priority=1,enabled=false,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutSameBillShipAddr(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {

		  pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  //checkoutLoginPage.openCheckoutLoginPage(driver, baseUrl, "checkout/Login");
		  
		  checkoutLoginPage.btnSignIn(driver); //wait to load page correctly
		  checkoutLoginPage.emailID(driver).clear();
		  checkoutLoginPage.emailID(driver).sendKeys(email);
		  checkoutLoginPage.pasword(driver).clear();
		  checkoutLoginPage.pasword(driver).sendKeys(password);
		  checkoutLoginPage.btnSignIn(driver).click();
		 
		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.securityCode(driver).sendKeys(secCode);	  
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  //CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  
		  confirmOrderMsgVerification(testCaseName,"checkoutSameBillShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }
	  
	  @Test(priority=2,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutSameBillShipAddrLoginFirst(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
	  
		 // pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {  //some time due to slow Internet connection order summary not get loaded on checkout at 1st attempt so to avoid that , here we reload the checkout page
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.securityCode(driver).sendKeys(secCode);	  
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver).click();

 
		 // confirmOrderMsgVerification(testCaseName,"newCheckoutDiffBillShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
		  confirmOrderMsgVerification(testCaseName,"checkoutSameBillShipAddrLoginFirst",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }
	  
	  
	  @Test(priority=3,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutSelectDiffExistingShipAddr(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
  
		  //pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  //pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.linkChangeShipAdd(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.chooseShippingAddress.selectDiffAddr(driver).click();
		  CheckoutPage.chooseShippingAddress.btnUpdate(driver).click();
		  Thread.sleep(2000);  
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.securityCode(driver).sendKeys(secCode);	  
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver).click();

		  confirmOrderMsgVerification(testCaseName,"checkoutSelectDiffExistingShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }
	  
	  @Test(priority=4,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutAddNewShipAddr(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		 // pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.linkChangeShipAdd(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.chooseShippingAddress.btnADDNEW(driver).click();
		  Thread.sleep(2000);  
		  CheckoutPage.ShipAddressSection.enterFirstName(driver).sendKeys(shipFirstname);
		  CheckoutPage.ShipAddressSection.enterLastName(driver).sendKeys(shipLastName);
		  CheckoutPage.ShipAddressSection.enterPhone(driver).sendKeys(shipPhoneNumer);
		  CheckoutPage.ShipAddressSection.enterShipAdd1(driver).sendKeys(shipAdd1);
		  CheckoutPage.ShipAddressSection.enterCity(driver).sendKeys(shipCity);
		  CheckoutPage.ShipAddressSection.selectState(driver).selectByValue(shipState);
		  CheckoutPage.ShipAddressSection.enterZipCode(driver).sendKeys(shipZipcode); 
		  CheckoutPage.ShipAddressSection.shipOption(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.ShipAddrValidationSection.buttonNO(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.ShipAddressSection.enableShipMethodListBox(driver).selectByIndex(shipMethod);
		  Thread.sleep(2000); 
		  CheckoutPage.ShipAddressSection.enableButtonShipContinue(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.securityCode(driver).sendKeys(secCode);	  
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver).click();
		  
		  confirmOrderMsgVerification(testCaseName,"checkoutAddNewShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }
	  
	  @Test(priority=5,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutNewShipAddrAsPrimary(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		 // pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.linkChangeShipAdd(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.chooseShippingAddress.btnADDNEW(driver).click();
		  Thread.sleep(2000);  
		  CheckoutPage.ShipAddressSection.enterFirstName(driver).sendKeys(shipFirstname);
		  CheckoutPage.ShipAddressSection.enterLastName(driver).sendKeys(shipLastName);
		  CheckoutPage.ShipAddressSection.enterPhone(driver).sendKeys(shipPhoneNumer);
		  CheckoutPage.ShipAddressSection.enterShipAdd1(driver).sendKeys(shipAdd1);
		  CheckoutPage.ShipAddressSection.enterCity(driver).sendKeys(shipCity);
		  CheckoutPage.ShipAddressSection.selectState(driver).selectByValue(shipState);
		  CheckoutPage.ShipAddressSection.enterZipCode(driver).sendKeys(shipZipcode); 
		  CheckoutPage.ShipAddressSection.shipOption(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.ShipAddrValidationSection.buttonNO(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.ShipAddressSection.enableShipMethodListBox(driver).selectByIndex(shipMethod);
		  CheckoutPage.ShipAddressSection.chkBxPrimaryShip(driver).click();
		  Thread.sleep(2000); 
		 
		  CheckoutPage.ShipAddressSection.enableButtonShipContinue(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.securityCode(driver).sendKeys(secCode);	  
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		 // CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver).click();
		  
		  confirmOrderMsgVerification(testCaseName,"checkoutNewShipAddrAsPrimary",expectedMsg, printerMsg, 2 , ScreenshotLocation); 
	  }
	  
	  @Test(priority=6,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutNewPaymentCard(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.linkChangeBillAdd(driver).click();
		  Thread.sleep(1000);
		  CheckoutPage.PaymentSection.cardFirstName(driver).sendKeys(cardFirstName);
		  CheckoutPage.PaymentSection.cardLastName(driver).sendKeys(cardLastName);
		  CheckoutPage.PaymentSection.cardNumber(driver).sendKeys(cardNumber);
		  CheckoutPage.PaymentSection.cardExpMonth(driver).selectByIndex(cardExMonth);
		  CheckoutPage.PaymentSection.cardExpYear(driver).selectByIndex(cardExYear);
		  CheckoutPage.PaymentSection.cardSecurityCode(driver).sendKeys(secCode);
		  CheckoutPage.PaymentSection.cardFirstName(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.PaymentSection.privacyPolicyArea(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver).click();

 
		  confirmOrderMsgVerification(testCaseName,"checkoutNewPaymentCard",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }
	  
	  @Test(priority=7,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutNewPaymentCardMarkPrimaryPayment(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.linkChangeBillAdd(driver).click();
		  Thread.sleep(1000);
		  CheckoutPage.PaymentSection.cardFirstName(driver).sendKeys(cardFirstName);
		  CheckoutPage.PaymentSection.cardLastName(driver).sendKeys(cardLastName);
		  CheckoutPage.PaymentSection.cardNumber(driver).sendKeys(cardNumber);
		  CheckoutPage.PaymentSection.cardExpMonth(driver).selectByIndex(cardExMonth);
		  CheckoutPage.PaymentSection.cardExpYear(driver).selectByIndex(cardExYear);
		  CheckoutPage.PaymentSection.cardSecurityCode(driver).sendKeys(secCode);
		  CheckoutPage.PaymentSection.cardFirstName(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.PaymentSection.chkBxPrimaryPayment(driver);
		  
		  CheckoutPage.PaymentSection.privacyPolicyArea(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver).click();

 
		  confirmOrderMsgVerification(testCaseName,"checkoutNewPaymentCardMarkPrimaryPayment",expectedMsg, printerMsg, 2 , ScreenshotLocation);
		  
	  }
	  
	  
	  @Test(priority=8,enabled=true,dependsOnMethods={"existingUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","sBillAdd1","sBillCity","sBillState","sBillZip","iBillPhone","iBirthMonth","iBirthDay","iBirthYear"})
	  public void checkoutNewBillAdd(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,String billAdd1,String billCity,String billState,String billZipcode,String billPhone,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;	
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  
		  CheckoutPage.PaymentSection.btnSubmitOrderExistingBill(driver); //wait to load page correctly
		  CheckoutPage.PaymentSection.linkChangeBillAdd(driver).click();
		  Thread.sleep(1000);
		  CheckoutPage.PaymentSection.cardFirstName(driver).sendKeys(cardFirstName);
		  CheckoutPage.PaymentSection.cardLastName(driver).sendKeys(cardLastName);
		  CheckoutPage.PaymentSection.cardNumber(driver).sendKeys(cardNumber);
		  CheckoutPage.PaymentSection.cardExpMonth(driver).selectByIndex(cardExMonth);
		  CheckoutPage.PaymentSection.cardExpYear(driver).selectByIndex(cardExYear);
		  CheckoutPage.PaymentSection.cardSecurityCode(driver).sendKeys(secCode);
		  CheckoutPage.PaymentSection.cardFirstName(driver).click();
		  Thread.sleep(3000);	
		  
		  CheckoutPage.BillAddrSection.addDiffBillAddr(driver).click();
		  Thread.sleep(2000);
		  CheckoutPage.BillAddrSection.billAddr1(driver).clear();
		  CheckoutPage.BillAddrSection.billAddr1(driver).sendKeys(billAdd1);
		  CheckoutPage.BillAddrSection.billCity(driver).clear();
		  CheckoutPage.BillAddrSection.billCity(driver).sendKeys(billCity);
		  CheckoutPage.BillAddrSection.billState(driver).selectByValue(billState);
		  CheckoutPage.BillAddrSection.billZipCode(driver).clear();
		  CheckoutPage.BillAddrSection.billZipCode(driver).sendKeys(billZipcode);
		  CheckoutPage.BillAddrSection.billAddr1(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.BillAddrSection.buttonNO(driver).click();
		  CheckoutPage.BillAddrSection.billPhone(driver).clear();
		  CheckoutPage.BillAddrSection.billPhone(driver).sendKeys(billPhone);
		  
		  CheckoutPage.PaymentSection.privacyPolicyArea(driver).click();
		  CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver); //wait to load page correctly
		  //CheckoutPage.PaymentSection.btnSubmitOrderNewBillAdd(driver).click();

		  confirmOrderMsgVerification(testCaseName,"checkoutNewBillAdd",expectedMsg, printerMsg, 2 , ScreenshotLocation);
	  }

	  void confirmOrderMsgVerification(String testCasename,String testMethodname, String expectedMsg , String printerMsg , int rowNumber , String ScreenshotLocation ) throws Exception{
		  Boolean result= false;
		  Thread.sleep(10000);
		  
          String screenShotName = testCasename+"-"+testMethodname;

		  
		  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		  
		  result = orderConfirmation.verifyMsgText(driver, expectedMsg); //verifying order number message
		  if (result == true) {
				Reporter.log("Expected msg :"+expectedMsg+" Displaying on confirmation page. \n");
			} else {
				Reporter.log("Expected msg :"+expectedMsg+" NOT Displaying on confirmation page.");
			}
		  
		  Reporter.log("Order Number :"+orderConfirmation.getOrderNumber(driver));
		  
		  result = orderConfirmation.verifyMsgText(driver, printerMsg);  //verifying print order confirmation page link
		  if (result == true) {
				Reporter.log("Expected msg :"+printerMsg+" Displaying on confirmation page. \n");
			} else {
				Reporter.log("Expected msg :"+printerMsg+" NOT Displaying on confirmation page.");
			}	
	  }
	  
	  
}























