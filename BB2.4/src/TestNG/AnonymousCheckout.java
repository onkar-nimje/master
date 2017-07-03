package TestNG;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
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
import Utilities.ExcelUtils;
import Utilities.SpecialAction;

public class AnonymousCheckout {
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
	  Boolean pass = false;
	  Boolean timeout = false;
	  String timeInMilisecond="Test"+System.currentTimeMillis();
	  String filePath = null;
	  String ScreenshotLocation = null;
	  String automationStartTime = null;
	  
	  public AnonymousCheckout() {
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
				//filePath = "D://workspaces//Selenuim//BB2.4//test-output//TestOutPut.xls";
				//ExcelUtils.setExelFile(filePath);
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
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","iBirthMonth","iBirthDay","iBirthYear"})
	  public void newCheckoutSameBillShipAddr(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String phoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		  if (local.equalsIgnoreCase("en_US")) {
			  email = (System.currentTimeMillis()/10000000)+email;
			  System.out.println("en_US:"+email);
		} else {
			if (local.equalsIgnoreCase("es_US")) {
				email = (System.currentTimeMillis()/20000000)+email;
				System.out.println("es_US:"+email);
			} else {
				email = (System.currentTimeMillis()/30000000)+email;
				System.out.println("en_CA:"+email);
			}
		}

		  pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;		  
		  pdpPage.buttonCHECKOUT(driver).click();
		  //checkoutLoginPage.openCheckoutLoginPage(driver, baseUrl, "checkout/Login");
		 // pdpPage.miniCart(driver).click();
		 // pdpPage.miniCartCheckoutLink(driver).click();
		  
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  
		  checkoutLoginPage.beginCheckout(driver).click();
		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
			driver.navigate().refresh();
			CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
	    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);  //this will perform only when user is not in ship address section
		  
		  CheckoutPage.ShipAddressSection.enterFirstName(driver).sendKeys(shipFirstname);
		  CheckoutPage.ShipAddressSection.enterLastName(driver).sendKeys(shipLastName);
		  CheckoutPage.ShipAddressSection.enterPhone(driver).sendKeys(phoneNumer);
		  CheckoutPage.ShipAddressSection.enterShipAdd1(driver).sendKeys(shipAdd1);
		  CheckoutPage.ShipAddressSection.enterCity(driver).sendKeys(shipCity);
		  CheckoutPage.ShipAddressSection.selectState(driver).selectByValue(shipState);
		  CheckoutPage.ShipAddressSection.enterZipCode(driver).sendKeys(shipZipcode); 
		  CheckoutPage.ShipAddressSection.shipOption(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.ShipAddrValidationSection.buttonNO(driver).click();
		  CheckoutPage.ShipAddressSection.shipMethod(driver).selectByIndex(shipMethod);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  CheckoutPage.PaymentSection.buttonPayContinue(driver);
		  CheckoutPage.PaymentSection.cardFirstName(driver).sendKeys(cardFirstName);
		  CheckoutPage.PaymentSection.cardLastName(driver).sendKeys(cardLastName);
		  CheckoutPage.PaymentSection.cardNumber(driver).sendKeys(cardNumber);
		  CheckoutPage.PaymentSection.cardExpMonth(driver).selectByIndex(cardExMonth);
		  CheckoutPage.PaymentSection.cardExpYear(driver).selectByIndex(cardExYear);
		  CheckoutPage.PaymentSection.cardSecurityCode(driver).sendKeys(secCode);
		  CheckoutPage.PaymentSection.cardFirstName(driver).click();
		  Thread.sleep(5000);
		  CheckoutPage.PaymentSection.buttonPayContinue(driver).click();
		  
		  CheckoutPage.AccountSection.buttonSubmitORDER(driver);
		  CheckoutPage.AccountSection.email(driver).sendKeys(email);
		  CheckoutPage.AccountSection.confirmEmail(driver).sendKeys(email);
		  CheckoutPage.AccountSection.password(driver).sendKeys(password);
		  CheckoutPage.AccountSection.confirmPassword(driver).sendKeys(password);
		  CheckoutPage.AccountSection.birthMonth(driver).selectByIndex(birthMonth);
		  CheckoutPage.AccountSection.birthDay(driver).selectByIndex(birthDay);
		  //Thread.sleep(2000);
		  CheckoutPage.AccountSection.birthYear(driver).selectByIndex(birthYear);
		  Thread.sleep(2000);
		  CheckoutPage.AccountSection.genderMale(driver).perform();
		  CheckoutPage.AccountSection.noCoach(driver).click();
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  Thread.sleep(3000);
		  //CheckoutPage.AccountSection.buttonSubmitORDER(driver).click();
		  
		  testExpectation(testCaseName,"newCheckoutSameBillShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
		  logOut(driver, baseUrl, local);
		  		  
	  }
	  
	  
	  @Test(priority=1,enabled=true)
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sPrinterFirendlyMsg","sOrderConfMsg","iProductType","iQty","sShipFirstName","sShipLastName","iShipPhone","sShipAdd1","sShipCity","sShipState","sShipZip","iShipMethod","sCardFirstName","sCardLastName","iCardNumber","iCardExMonth","iCardExYear","iSecCode","sBillAdd1","sBillCity","sBillState","sBillZip","iBillPhone","iBirthMonth","iBirthDay","iBirthYear"})
	  public void newCheckoutDiffBillShipAddr(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String printerMsg,String expectedMsg,int productTypeIndex,int qty,String shipFirstname,String shipLastName,String shipPhoneNumer,String shipAdd1,String shipCity,String shipState,String shipZipcode,int shipMethod,String cardFirstName,String cardLastName,String cardNumber,int cardExMonth,int cardExYear,String secCode,String billAdd1,String billCity,String billState,String billZipcode,String billPhone,int birthMonth,int birthDay,int birthYear) throws Exception {
		  
		  if (local.equalsIgnoreCase("en_US")) {
			  email = (System.currentTimeMillis()%55555555)+email;
			  System.out.println("en_US:"+email);
		} else {
			if (local.equalsIgnoreCase("es_US")) {
				email = (System.currentTimeMillis()%77777777)+email;
				System.out.println("es_US:"+email);
			} else {
				email = (System.currentTimeMillis()%22222222)+email;
				System.out.println("en_CA:"+email);
			}
		}

		  pdpPage.openPDPPagewithLocal(driver, baseUrl, pageUrl, local);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("es_US")) {
			  pdpPage.selectProductType(driver).selectByIndex(productTypeIndex);
		  } 
		  pdpPage.selectProductQty(driver).selectByIndex(qty);;		  
		  pdpPage.buttonCHECKOUT(driver).click();
		  Thread.sleep(2000);
		  pdpPage.openPDPPagewithoutLocal(driver,baseUrl, pageUrl);
		  wait.until(ExpectedConditions.visibilityOf(pdpPage.buttonCHECKOUT(driver)));
		  pdpPage.miniCart(driver).click();
		  pdpPage.miniCartCheckoutLink(driver).click();
		  
		  checkoutLoginPage.beginCheckout(driver).click();
		  
		  if (CheckoutPage.OrderSummarySection.OrderSummary(driver)) {
				driver.navigate().refresh();
				CheckoutPage.OrderSummarySection.OrderSummary(driver);  //waiting to load order summary
		    	}
		  CheckoutPage.ShipAddressSection.btnEDITShipAdd(driver);  //this will perform only when user is not in ship address section
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
		  CheckoutPage.ShipAddressSection.shipMethod(driver).selectByIndex(shipMethod);
		  CheckoutPage.ShipAddressSection.buttonShipContinue(driver).click();
		  
		  CheckoutPage.PaymentSection.buttonPayContinue(driver);
		  CheckoutPage.PaymentSection.cardFirstName(driver).sendKeys(cardFirstName);
		  CheckoutPage.PaymentSection.cardLastName(driver).sendKeys(cardLastName);
		  CheckoutPage.PaymentSection.cardNumber(driver).sendKeys(cardNumber);
		  CheckoutPage.PaymentSection.cardExpMonth(driver).selectByIndex(cardExMonth);
		  CheckoutPage.PaymentSection.cardExpYear(driver).selectByIndex(cardExYear);
		  CheckoutPage.PaymentSection.cardSecurityCode(driver).sendKeys(secCode);
		  CheckoutPage.PaymentSection.cardFirstName(driver).click();

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
		  
		  CheckoutPage.PaymentSection.buttonPayContinue(driver).click();
		  
		  
		  CheckoutPage.AccountSection.buttonSubmitORDER(driver);
		  CheckoutPage.AccountSection.email(driver).sendKeys(email);
		  CheckoutPage.AccountSection.confirmEmail(driver).sendKeys(email);
		  CheckoutPage.AccountSection.password(driver).sendKeys(password);
		  CheckoutPage.AccountSection.confirmPassword(driver).sendKeys(password);
		  CheckoutPage.AccountSection.birthMonth(driver).selectByIndex(birthMonth);
		  CheckoutPage.AccountSection.birthDay(driver).selectByIndex(birthDay);
		 // Thread.sleep(2000);
		  CheckoutPage.AccountSection.birthYear(driver).selectByIndex(birthYear);
		  Thread.sleep(2000);
		  CheckoutPage.AccountSection.genderMale(driver).perform();
		  CheckoutPage.AccountSection.noCoach(driver).click();
		  CheckoutPage.AccountSection.privacyPolicy(driver).click();
		  Thread.sleep(3000);
		  //CheckoutPage.AccountSection.buttonSubmitORDER(driver).click();

		  testExpectation(testCaseName,"newCheckoutDiffBillShipAddr",expectedMsg, printerMsg, 2 , ScreenshotLocation);
		  logOut(driver, baseUrl, local);
	  }
	  
	  void testExpectation(String testCasename,String testMethodname, String expectedMsg , String printerMsg , int rowNumber , String ScreenshotLocation) throws Exception{
		  
		  Boolean result= false;
		  String orderNumber = null;
		  Thread.sleep(10000);
		  String screenShotName = testCasename+"-"+testMethodname;

		  
		  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		  
		  
		  result = orderConfirmation.verifyMsgText(driver, expectedMsg); //verifying order number message
		  if (result == true) {
				Reporter.log("Expected msg :"+expectedMsg+" Displaying on confirmation page.");
			} else {
				Reporter.log("Expected msg :"+expectedMsg+" NOT Displaying on confirmation page.");
			}
		  orderNumber = orderConfirmation.getOrderNumber(driver);
		  Reporter.log("Order Number :"+orderNumber);

		  result = orderConfirmation.verifyMsgText(driver, printerMsg);  //verifying print order confirmation page link
		  if (result == true) {
				Reporter.log("Expected msg :"+printerMsg+" Displaying on confirmation page.");
			} else {
				Reporter.log("Expected msg :"+printerMsg+" NOT Displaying on confirmation page.");
			}	
		  
	  }
	  
	  void logOut(WebDriver driver, String baseUrl ,String local ) throws InterruptedException{
		  if(driver.getCurrentUrl().contains("/confirmation")){
			  pageHome.openHomePageWithoutLocal(driver, baseUrl,"");
			  pageHome.signOUTLink(driver, local).click();	
			  pageHome.alertWindow(driver);
			  Thread.sleep(5000);
		  }
	  }
  
}























