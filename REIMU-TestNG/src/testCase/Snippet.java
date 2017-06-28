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
import pages.VideoPage;
import pages.CatalogPage.ProductSection;
import pages.LookPage;
import pages.UpdateLookPage;
import pages.UpdateVideoPage;
import utilities.Log;

//import TestNG.Log;

public class Snippet {

	WebDriver driver;
	Browser specificBrowser;
	LoginPage loginPage;
	UpdateLookPage updateLookPage;
	UpdateVideoPage updateVideoPage;
	HomePage homePage;
	CatalogPage catalogPage;
	LookPage lookPage;
	VideoPage videoPage;
	SpecialAction specialAction;
	private String ScreenshotLocation;
	private String automationStartTime;
	private WebDriverWait wait;
	private WebElement element = null;
	public Snippet() {
		specificBrowser = new Browser(driver);
		loginPage = new LoginPage(driver);
		updateLookPage = new UpdateLookPage(driver);
		updateVideoPage = new UpdateVideoPage(driver);
		specialAction = new SpecialAction(driver);
		homePage =new HomePage(driver);
		catalogPage = new CatalogPage(driver);
		lookPage = new LookPage(driver);
		videoPage = new VideoPage(driver);
	}
	
	 @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
			
				//Log.startTestCase("Registration");
				//filePath = "D://workspaces//Selenuim//BB2.4//test-output//TestOutPut.xls";
				//ExcelUtils.setExelFile(filePath);
				ScreenshotLocation = "D://workspaces//Selenuim//REIMU-TestNG//test-output//OutPut//Screenshot//Look//";
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
	
	 //////////////////////LOOK-TEMPLATE1//////////////////////////	
	 ///////////////////// snippet from LOOK EDIT PAGE ////////////
	 @Test(priority=1,enabled=true)
	 @Parameters({"sLookURL-Template1"})
	 public void LookTemplate1snippetEditPage(String lookUrl) throws Exception {
		 String snippetText = null;
		 updateLookPage.openLookUpdatePage(driver, lookUrl);	 
		 Thread.sleep(5000);
		 updateLookPage.btnLookSnippet(driver,wait).click();	 
		 snippetText = updateLookPage.snippetText(driver);	 	 
	 }
	 
	 
     //////////////////////LOOK-TEMPLATE1//////////////////////////	
	 /////////////////////	snippet from LOOK PAGE/////////////////// 
	 @Test(priority=2,enabled=true)
	 @Parameters({"sExistLookName-Template1"})
	 public void LookTemplate1snippetHomePage(String lookName) throws Exception {
		 String snippetText = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/looks");
		 driver.navigate().refresh();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys(lookName);
		 lookPage.btnSearch(driver,wait).click();	
		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("2")));
		 lookPage.btnTool(driver,wait).click();
		 lookPage.lnkLookSnippet(driver).click();
		 snippetText = updateLookPage.snippetText(driver);	
		 System.out.println("LOOKTemplate1:::"+snippetText);
	 }
	 
     //////////////////////LOOK-TEMPLATE2//////////////////////////	
     ///////////////////// snippet from LOOK EDIT PAGE ////////////
	 @Test(priority=3,enabled=true)
	 @Parameters({"sLookURL-Template2"})
	 public void LookTemplate2snippet(String lookUrl) throws Exception {
		 String snippetText = null;
		 updateLookPage.openLookUpdatePage(driver, lookUrl);	 
		 Thread.sleep(5000);
		 updateLookPage.btnLookSnippet(driver,wait).click();	 
		 snippetText = updateLookPage.snippetText(driver);	 	 
	 }
	 
      //////////////////////LOOK-TEMPLATE2//////////////////////////	
	 /////////////////////	snippet from LOOK PAGE/////////////////// 
	 @Test(priority=4,enabled=true)
	 @Parameters({"sExistLookName-Template2"})
	 public void LookTemplate2snippetHomePage(String lookName) throws Exception {
		 String snippetText = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/looks");
		 driver.navigate().refresh();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys(lookName);
		 lookPage.btnSearch(driver,wait).click();	
		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("2")));
		 lookPage.btnTool(driver,wait).click();
		 lookPage.lnkLookSnippet(driver).click();
		 snippetText = updateLookPage.snippetText(driver);	
		 System.out.println("LOOKTemplate2:::"+snippetText);
	 }
	 
	 
	 ///////////////LOOK TEMPLATE CHANGE AND THEN CHECK SNIPPET////////////////////
	 @Test(priority=5,enabled=true)
	 @Parameters({})
	 public void LookTemplateChange1To2() throws Exception {
		 String snippetTextBeforeChange = null;
		 String snippetTextAfterChange = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/looks");
		 driver.navigate().refresh();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys("Selenium Test Look - TemplateInterchange");
		 lookPage.btnSearch(driver,wait).click();	
		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("2")));
		 lookPage.btnTool(driver,wait).click();
		 lookPage.lnkLookSnippet(driver).click();
		 snippetTextBeforeChange = updateLookPage.snippetText(driver);
		 lookPage.btnClose(driver).click();
		 updateLookPage.openLookUpdatePage(driver, "http://qamediacom.objectedge.com/admin/#/updatelook?lookId=589dcd412d0e7c905c098043&_k=lb3emv");
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("previewLink")));
		 if (snippetTextBeforeChange.contains("Click to See")) {
			updateLookPage.btnGridiron(driver,wait).click();
		} else {
			updateLookPage.btnClicktoSee(driver,wait).click();
		}
		 updateLookPage.btnOK(driver,wait).click();
		 driver.get("http://qamediacom.objectedge.com/admin/#/looks");
		 driver.navigate().refresh();
		 lookPage.searchBox(driver,wait).clear();
		 lookPage.searchBox(driver,wait).sendKeys("Selenium Test Look - TemplateInterchange");
		 lookPage.btnSearch(driver,wait).click();	
		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("2")));
		 lookPage.btnTool(driver,wait).click();
		 lookPage.lnkLookSnippet(driver).click();
		 snippetTextAfterChange = updateLookPage.snippetText(driver);
		 if (!snippetTextBeforeChange.equalsIgnoreCase(snippetTextAfterChange)) {
			Reporter.log("Test LookTemplateChange1To2 - SUCCESS");
		}else {
			Reporter.log("Test LookTemplateChange1To2 - FAIL");
		}
 	 
	 }
	 
	 
	 //////////////////////VIDEO-TEMPLATE1 (Easterly)//////////////////////////	
	 ///////////////////// snippet from VIDEO EDIT PAGE ////////////
	 @Test(priority=6,enabled=true)
	 @Parameters({"sVideoURL"})
	 public void VideoEasterlySnippetEditPage(String videoUrl) throws Exception {
		 String snippetText = null;
		 updateVideoPage.openVideoUpdatePage(driver, videoUrl);	 	 
		 updateVideoPage.btnTemplate(driver, wait, "Easterly").click();
		 Thread.sleep(2000);
		 updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		 snippetText = updateVideoPage.snippetText(driver);	 	 
	 }
	 
	 
     //////////////////////VIDEO-TEMPLATE1 (Easterly)//////////////////////////	
	 /////////////////////	snippet from VIDEO PAGE/////////////////// 
	 @Test(priority=7,enabled=true)
	 @Parameters({"sExistVideoName"})
	 public void VideoEasterlySnippetHomePage(String videoName) throws Exception {
		 String snippetText = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/videos");
		 Thread.sleep(5000);
		 videoPage.searchBx(driver,wait).clear();
		 videoPage.searchBx(driver,wait).sendKeys(videoName);
		 videoPage.btnSearch(driver,wait).click();
		 videoPage.btnTool(driver,wait).click();
		 videoPage.lnkVideoSnippet(driver,wait).click();
		 snippetText = updateVideoPage.snippetText(driver);	 
	     System.out.println("VIDEOTemplate1="+snippetText);	
	 }
	 
     //////////////////////VIDEO-TEMPLATE2 (Southerly)//////////////////////////	
	 ///////////////////// snippet from VIDEO EDIT PAGE ////////////
	 @Test(priority=8,enabled=true)
	 @Parameters({"sVideoURL"})
	 public void VideoSoutherlySnippetEditPage(String videoUrl) throws Exception {
		 String snippetText = null;
		 updateVideoPage.openVideoUpdatePage(driver, videoUrl);	 	 
		 updateVideoPage.btnTemplate(driver, wait, "Southerly").click();
		 Thread.sleep(2000);
		 updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		 snippetText = updateVideoPage.snippetText(driver);	 	 
	 }
     //////////////////////VIDEO-TEMPLATE1 (Southerly)//////////////////////////	
	 /////////////////////	snippet from VIDEO PAGE/////////////////// 
	 @Test(priority=9,enabled=true)
	 @Parameters({"sExistVideoName"})
	 public void VideoSoutherlySnippetHomePage(String videoName) throws Exception {
		 String snippetText = null;
		 driver.get("http://qamediacom.objectedge.com/admin/#/videos");
		 Thread.sleep(5000);
		 videoPage.searchBx(driver,wait).clear();
		 videoPage.searchBx(driver,wait).sendKeys(videoName);
		 videoPage.btnSearch(driver,wait).click();
		 videoPage.btnTool(driver,wait).click();
		 videoPage.lnkVideoSnippet(driver,wait).click();
		 snippetText = updateVideoPage.snippetText(driver);	 
	     System.out.println("VIDEOTemplate2="+snippetText);	
	 }
	 	 
		//////////////////////VIDEO-TEMPLATE3 (Doldrums)//////////////////////////	
		///////////////////// snippet from VIDEO EDIT PAGE ////////////
		@Test(priority=10,enabled=true)
		@Parameters({"sVideoURL"})
		public void VideoDoldrumsSnippetEditPage(String videoUrl) throws Exception {
		String snippetText = null;
		updateVideoPage.openVideoUpdatePage(driver, videoUrl);	 	 
		updateVideoPage.btnTemplate(driver, wait, "Doldrums").click();
		Thread.sleep(2000);
		updateVideoPage.btnVideoSnippet(driver,wait).click();	 
		snippetText = updateVideoPage.snippetText(driver);	 	 
		}
		//////////////////////VIDEO-TEMPLATE3 (Doldrums)//////////////////////////	
		/////////////////////	snippet from VIDEO PAGE/////////////////// 
		@Test(priority=11,enabled=true)
		@Parameters({"sExistVideoName"})
		public void VideoDoldrumsSnippetHomePage(String videoName) throws Exception {
		String snippetText = null;
		driver.get("http://qamediacom.objectedge.com/admin/#/videos");
		Thread.sleep(5000);
		videoPage.searchBx(driver,wait).clear();
		videoPage.searchBx(driver,wait).sendKeys(videoName);
		videoPage.btnSearch(driver,wait).click();
		videoPage.btnTool(driver,wait).click();
		videoPage.lnkVideoSnippet(driver,wait).click();
		snippetText = updateVideoPage.snippetText(driver);	 
		System.out.println("VIDEOTemplate3="+snippetText);	
		}
	 

	 @AfterClass
	 public void tearDown() {
		 //driver.quit();
	 }
	  
}
