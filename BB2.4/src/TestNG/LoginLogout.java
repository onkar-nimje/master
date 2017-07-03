package TestNG;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Browser;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.SpecialAction;

public class LoginLogout {
	  WebDriver driver;
	  LoginPage pageLogin;
	  HomePage pageHome;
	  Browser specificBrowser;
	  WebDriverWait wait;
	  Actions builder;
	  Action act;
	  String ScreenshotLocation = null;
	  String automationStartTime = null;
	  
	  String timeInMilisecond="Test"+System.currentTimeMillis();
	  
	  public LoginLogout() {
		  pageLogin = new LoginPage(driver);
		  pageHome = new HomePage(driver);
		  specificBrowser = new Browser(driver);
	  }
	  
	  @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
		        DOMConfigurator.configure("log4j.xml");
		        ScreenshotLocation = "D://workspaces//Selenuim//BB2.4//test-output//OutPut//Screenshot//";
				automationStartTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
				Log.startTestCase("Registration");
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
	  
	  
	  
	  @Test(priority=0,alwaysRun = true)
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome","sExpetMsgEmailMiss"})
		 public void openLoginPage(String testCaseName,String baseUrl,String pageUrl,String locale,String email,String password,String welcomeMsg,String expectedMsg) throws Exception
		 {
		  pageLogin.openLoginPageWithLocal(driver, baseUrl, locale);
		 }
	  
	  @Test(priority=1,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome","sExpetMsgEmailMiss"})
		 public void blankUserName(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg,String expectedMsg) throws Exception
		 {
		  try {
			     pageLogin.openLoginPage(driver,baseUrl,local);
				 pageLogin.email(driver).clear();
				 pageLogin.email(driver).sendKeys("");
				 pageLogin.password(driver).clear();
				 pageLogin.password(driver).sendKeys(password);
				 pageLogin.buttonSIGNIN(driver,local).click();
				 loginVerificationMsg( driver, testCaseName,"blankUserName",expectedMsg,2 , ScreenshotLocation);
				
			} catch (Exception e) {
				String screenShotName = testCaseName+"-blankUserName";
				  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
			}
				
		 }
	  
	  @Test(priority=2,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome","sExpetMsgPasswordMiss"})
		 public void blankPassword(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg,String expectedMsg) throws Exception
		 {
		  try {
			  pageLogin.openLoginPage(driver,baseUrl,local);
			    pageLogin.email(driver).clear();
				pageLogin.email(driver).sendKeys(email);
				pageLogin.password(driver).clear();
				pageLogin.password(driver).sendKeys("");
				pageLogin.buttonSIGNIN(driver,local).click();	
				loginVerificationMsg(driver, testCaseName,"blankPassword",expectedMsg,2 , ScreenshotLocation);
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
	
		 }
	  
	  @Test(priority=3,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome","sExpetMsgEmailMiss","sExpetMsgPasswordMiss"})
		 public void blankBothEmailPassword(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg,String missEmailMsg,String missPasswordlMsg) throws Exception
		 {
		  try {
			  pageLogin.openLoginPage(driver,baseUrl,local);
			    pageLogin.email(driver).clear();
				pageLogin.email(driver).sendKeys("");
				pageLogin.password(driver).clear();
				pageLogin.password(driver).sendKeys("");
				pageLogin.buttonSIGNIN(driver,local).click();
				loginVerificationMsg(driver, testCaseName,"blankBothEmailPassword",missEmailMsg,2 , ScreenshotLocation);
				loginVerificationMsg(driver, testCaseName,"blankBothEmailPassword",missPasswordlMsg,2 , ScreenshotLocation);
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
		
		 }

	  @Test(priority=4,enabled=false,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sExpetMsgEmailMiss","sExpetMsgPasswordMiss","sWrongEmailPassmsg"})
		 public void nonExistingUsernamePassword(String testCaseName,String baseUrl,String pageUrl,String local,String email1,String password1,String missEmailMsg,String missPasswordlMsg, String wrongEmailPass) throws Exception
		 {
		  try {
			  String email ="helloWorld@gmail.com"; 
		        String password = "246854";
			    pageLogin.openLoginPage(driver,baseUrl,local);
			    pageLogin.email(driver).clear();
				pageLogin.email(driver).sendKeys(email);
				pageLogin.password(driver).clear();
				pageLogin.password(driver).sendKeys(password);
				pageLogin.buttonSIGNIN(driver,local).click();
				loginVerificationMsg(driver, testCaseName,"nonExistingUsernamePassword",wrongEmailPass,2 , ScreenshotLocation);
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}

		 }
	  
	  @Test(priority=5,enabled=false,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sExpetMsgEmailMiss","sExpetMsgPasswordMiss","sWrongEmailPassmsg"})
		 public void wrongPassword(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password1,String missEmailMsg,String missPasswordlMsg, String wrongEmailPass) throws Exception
		 {
		  try {

		        String password = "246854";
			    pageLogin.openLoginPage(driver,baseUrl,local);
			    pageLogin.email(driver).clear();
				pageLogin.email(driver).sendKeys(email);
				pageLogin.password(driver).clear();
				pageLogin.password(driver).sendKeys(password);
				pageLogin.buttonSIGNIN(driver,local).click();
				loginVerificationMsg(driver, testCaseName,"wrongPassword",wrongEmailPass,2 , ScreenshotLocation);
				
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
		 }
	  
	  @Test(priority=6,enabled=true,dependsOnMethods={})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome"})
		 public void validUserlogin(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg) throws Exception
		 {
		  try {
			  pageHome.openHomePageWithoutLocal(driver, baseUrl, pageUrl);
				pageHome.signINLink(driver, local).click();
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='loginForm']/input[4]"))));
				pageLogin.email(driver).clear();
				pageLogin.email(driver).sendKeys(email);
				pageLogin.password(driver).clear();
				pageLogin.password(driver).sendKeys(password);
				pageLogin.buttonSIGNIN(driver,local).click();
				loginVerificationMsg(driver, testCaseName,"validUserlogin",welcomeMsg,2 , ScreenshotLocation);		
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
								  		
		 }
	  
	  @Test(priority=7,enabled=true,dependsOnMethods={"validUserlogin"})
	  @Parameters ({"sTestCaseName","sBaseUrl","sPageUrl","sLocal","sEmail","sPassword","sWelcome"})
		 public void Logout(String testCaseName,String baseUrl,String pageUrl,String local,String email,String password,String welcomeMsg) throws Exception
		 {
		  try {
			  pageHome.openHomePageWithoutLocal(driver, baseUrl, pageUrl);
				pageHome.signOUTLink(driver, local).click();	
				pageHome.alertWindow(driver);
		  		logoutVerificationMsg(driver, testCaseName,"Logout",local,2 , ScreenshotLocation);
		} catch (Exception e) {
			String screenShotName = testCaseName+"-blankUserName";
			  SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
	
		 }
	  
	    void loginVerificationMsg(WebDriver driver,String testCasename,String testMethodname, String msg  , int rowNumber , String ScreenshotLocation) throws Exception {
	    	//String testCasename,String testMethodname, String local  , int rowNumber , String ScreenshotLocation
	    	Thread.sleep(5000);
		  String pageSource = null;
			pageSource= driver.getPageSource();
			if (pageSource.contains(msg)) {
				Reporter.log("Expected Msg :"+msg+" Found on LOGIN page \n");
				Log.info("Expected Msg :"+msg+" Found on LOGIN page");
			} else {
				Reporter.log("Expected Msg :"+msg+" NOT Found on LOGIN page \n");
				Log.info("Expected Msg :"+msg+" NOT Found on LOGIN page");
			}
			
			String screenShotName = testCasename+"-"+testMethodname;

			  
			  //SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
	    
	   void logoutVerificationMsg(WebDriver driver,String testCasename,String testMethodname, String local  , int rowNumber , String ScreenshotLocation) throws Exception {
		   Thread.sleep(10000);
			if (local.equalsIgnoreCase("en_US")||local.equalsIgnoreCase("en_CA")) { //LOGOUT VERIFICATION CODE HERE
				driver.findElement(By.linkText("Sign In"));				
			} else {
				driver.findElement(By.linkText("Iniciar sesión"));
			}
					Reporter.log("User LOGOUT correctly \n");
					Log.info("User LOGOUT correctly \n");
					String screenShotName = testCasename+"-"+testMethodname;

					  
					 // SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		}
	  	  
	  @AfterTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void tearDown(String browser) throws Exception {
	    Log.endTestCase("LoginLogout");
	   // driver.close();
	    driver.quit();
	    
	  }
	 
}
