package TestNG;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginLogoutNOTComplete {
	  WebDriver driver;
	  Actions builder;
	  Action act;
	  WebDriverWait wait;
	  String timeInMilisecond="Test"+System.currentTimeMillis();
	  @BeforeTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
				Log.startTestCase("Registration");
				
				
				if(browser.equalsIgnoreCase("Firefox"))     //FIREFOX
				{
			         driver = new FirefoxDriver();
			         Log.info("FF Driver started");
				}
				if(browser.equalsIgnoreCase("Chrome"))      //CHROME
				{
					Log.info("Chrome Driver started");
					System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
					driver=new ChromeDriver();
				}
				
				if(browser.equalsIgnoreCase("IE"))          //INTERNET EXPLORER
				{
					Log.info("IE Driver started");
					System.setProperty("webdriver.ie.driver", "D:\\workspaces\\IEDriverServer.exe");
					driver=new InternetExplorerDriver();
				}
				
			//	driver = new FirefoxDriver();
			//	System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
			//	driver=new ChromeDriver();
			//	System.setProperty("webdriver.ie.driver", "D:\\workspaces\\IEDriverServer.exe");
			//	driver=new InternetExplorerDriver();
			//	driver = new FirefoxDriver();
				wait=new WebDriverWait(driver, 120);
				driver.manage().window().maximize();
			 
				
		} catch (Exception e) {
			Reporter.log(e.toString());
			Log.info(e.toString());
		}
	    
	  }
	  
	  
	  @Test(priority=0)
	  @Parameters ({"sBrowser","sCountry"})
		 public void openCountrySpecificSite(String browser, String country) throws Exception
		 {
		  String title1=null,title2=null,title3=null;
		  System.out.println(browser+country);
			 if(country.equalsIgnoreCase("en_US")){
				 driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/us/home");
				 title1=driver.getTitle();
				 System.out.println(title1);
			 }
				 
			 
			 if(country.equalsIgnoreCase("es_US")){
				 driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/us/home?locale=es_US");
				 title2=driver.getTitle();
			 }
				 
			 
			 if(country.equalsIgnoreCase("en_CA")){
				 driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/ca/home");
				 title3=driver.getTitle();
			 }
				
			 try {
				 if (!title1.equals(null)) {
					if (title1.equalsIgnoreCase("Shop Fitness Programs, Nutritional Products, and Gear & Apparel - Teambeachbody US")) {
						Reporter.log("Page open correctly");
						Log.info("Country specific page open correctly");
					}
				}
				 else {
					 if (!title2.equals(null)) {
						 if (title2.equalsIgnoreCase("Compra Programas y Herramientas de Ejercicio, Productos Nutricionales & Ropa - Teambeachbody US")) {
								Reporter.log("Page open correctly");
								Log.info("Country specific page open correctly");
							}
					 }
						 else {
							 if (!title3.equals(null)) {
								 if (title3.equalsIgnoreCase("Shop Fitness Programs, Nutritional Products, and Gear & Apparel - Teambeachbody US")) {
										Reporter.log("Page open correctly");
										Log.info("Country specific page open correctly");
									}
							}
							 
						}
					}		 
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("hello");
				System.out.println("TITLE for "+country+"::"+driver.getTitle());
				Reporter.log(e.toString());
				Log.info("Country specific page NOT open correctly");
			}
		
			
		 }
	  
	  
	  @Test(priority=1)
	  @Parameters ({"sBrowser"})
	  public void Login(String browser) throws Exception {
		  //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		  driver.findElement(By.xpath("//*[@id='top-header']/div[2]/a[1]")).click(); //click on 'sign in' link from header
		  //driver.findElement(By.linkText("Sign In")).click();
		  driver.findElement(By.id("email")).sendKeys("om_0211_1@beachbodytest.com");  // enter user name
		  driver.findElement(By.id("pass")).sendKeys("12345"); // enter password
		  driver.findElement(By.xpath("//*[@id='loginForm']/input[4]")).click(); //click on SIGNIN button from login page
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  try {
			WebElement loggedInUser=driver.findElement(By.partialLinkText("onkarB1")); //finding the loggedIn username
			System.out.println(loggedInUser.getText());
			Reporter.log("Expected user get logged in CORRECTLY");
			Log.info("Expected user get logged in CORRECTLY");
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(e.toString());
			Log.info("Expected user not get logged in");
		}
	  }
	  
	  @Test(priority=2)
	  @Parameters ({"sBrowser","sCountry"})
	  public void gotoMyAccountPage(String browser, String country) throws InterruptedException {
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='miniCart']/a/span[2]")));
		   //click on Welcome link from header to go to account page
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  if(country.equalsIgnoreCase("en_US") || country.equalsIgnoreCase("en_CA"))
		  {
			  driver.findElement(By.partialLinkText("Welcome")).click();
			  driver.findElement(By.linkText("My Account")).click();// click on 'My Account' link  
		  }
		  else
		  {
			  driver.findElement(By.partialLinkText("Bienvenida")).click();
			  driver.findElement(By.linkText("Mi cuenta")).click(); // click on 'My Account' link 
	      }
		 
		  System.out.println("click on my account");
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  try {
			  if (country.equalsIgnoreCase("en_US") || country.equalsIgnoreCase("es_US")) {
				  Assert.assertEquals(driver.getCurrentUrl(), "http://www-ultimateresetdevint2.beachbody.local/shop/us/account/settings");
					Log.info("user correctly landed on MY ACCOUNT SETTING page");
			} else {
				if (country.equalsIgnoreCase("en_CA")) {
					Assert.assertEquals(driver.getCurrentUrl(), "http://www-ultimateresetdevint2.beachbody.local/shop/ca/account/settings");
					Log.info("user correctly landed on MY ACCOUNT SETTING page");
					}
		}
			
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(e.toString());
			Log.info("user NOT correctly landed on MY ACCOUNT SETTING page");		
		}
	  }
	  
	  @Test(priority=3,enabled=false)
	  @Parameters ({"sBrowser","sCountry"})
	  public void updateShippingAddress1(String browser) throws InterruptedException {
		  Thread.sleep(5000);		  
		  WebElement shipTab=driver.findElement(By.xpath("//*[@id='page-content']/div/section/div/div/a[2]"));//finding Shipping Address tab
		  wait.until(ExpectedConditions.visibilityOf(shipTab));
		  System.out.println("End of ship tab visiblity wait");
		  wait.until(ExpectedConditions.elementToBeClickable(shipTab));
		  System.out.println("End of ship tab clickable wait");
		  shipTab.click();
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  WebElement shipAddress=driver.findElement(By.xpath("//*[@id='account-addresses']"));//shipping address section
		  wait.until(ExpectedConditions.visibilityOf(shipAddress));
		  
		  
		  
		  WebElement add1=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/p[2]"));
		  System.out.println("Address before editing ::"+add1.getText());
		  
		  
		  driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/div[1]/p[1]/a")).click();//click on edit link of ship address
		  driver.findElement(By.xpath("//*[@id='address1']")).clear(); //clear the existing address1
		  driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("456 Main St1"); //click on address1
		  driver.findElement(By.xpath("//*[@id='firstName']")).click();//click on the First name field		  
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  WebElement pop=driver.findElement(By.xpath("//*[@id='simplePopUpBackFace']"));// finding related address modal window
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='simplePopUpBackFace']")));
		  System.out.println("End of related address visiblity wait");
		  wait.until(ExpectedConditions.elementToBeClickable(pop));
		  System.out.println("End of related address clickability wait");
		  
		  
		  Boolean elementPresent=false;
		  try {
			  driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[2]/div/article/div[2]/div/input[1]"));
			  elementPresent=true;
			  System.out.println(elementPresent);
		} catch (Exception e) {
			elementPresent=false;
		}
		  if(elementPresent==true) 
		  {
			  System.out.println(elementPresent);
			  driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[2]/div/article/div[2]/div/input[1]")).sendKeys("444444");//enter value in related address popup window
			  System.out.println("entered value");
		  }
		  
		  
		  System.out.println(elementPresent);
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[3]/button[1]")).click(); // click on OK button from related address window
		  Thread.sleep(2000);
		  WebElement buttonUPDATE=driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[4]/button"));//finding UPDATE button
		  buttonUPDATE.click();
		  try {
			  String addrUpdateMsg=null;
			  addrUpdateMsg=driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[1]/div[2]")).getText();
			  if(addrUpdateMsg.equalsIgnoreCase("Your address has been successfully updated ") || addrUpdateMsg.equalsIgnoreCase("Tu dirección fue actualizada exitosamente")) {
				  Log.info("Address update meaasge coming SUCCESSFULLY");						  
			  }
			  
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(e.toString());
			Log.info("Address update meaasge NOT coming");
		}
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[2]/a")).click(); //click on SUCCESS pop-up window close
		  Thread.sleep(2000);
		  WebElement updatedAddress=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/p[2]"));
		  System.out.println(updatedAddress.getText());// updated address1
		  if (updatedAddress.getText().equalsIgnoreCase("444444 Main St ,  Omkar")) {
			  Reporter.log("Shipping address updated successfully");
			  Log.info("Ship address get updated successfully");
			
		} else {
			Reporter.log("Shipping address NOT updated successfully");
			  Log.info("Ship address NOT updated successfully");
		}
	  }
	  
	  
	  @Test(priority=4,enabled=false)
	  @Parameters ({"sBrowser","sCountry"})
	  public void updateShippingAddress2(String browser, String country) throws InterruptedException {
		  int addrChecker=0;
		  Thread.sleep(5000);		  
		  WebElement shipTab=driver.findElement(By.xpath("//*[@id='page-content']/div/section/div/div/a[2]"));//finding Shipping Address tab
		  wait.until(ExpectedConditions.visibilityOf(shipTab));
		  System.out.println("End of ship tab visiblity wait");
		  wait.until(ExpectedConditions.elementToBeClickable(shipTab));
		  System.out.println("End of ship tab clickable wait");
		  shipTab.click();
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  WebElement shipAddress=driver.findElement(By.xpath("//*[@id='account-addresses']"));//shipping address section
		  wait.until(ExpectedConditions.visibilityOf(shipAddress));
		  WebElement add1=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/p[2]"));
		  System.out.println("Address before editing ::"+add1.getText());
		  driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/div[1]/p[1]/a")).click();//click on edit link of ship address
		  Thread.sleep(2000);
		  String address1=driver.findElement(By.xpath("//*[@id='address1']")).getAttribute("value"); ///value of address1
		  System.out.println("address1 value::"+address1);
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[3]/fieldset/div[5]/div/div[2]/label/a")).click();//click on + sign for address2
		  String address2=driver.findElement(By.xpath("//*[@id='address2']")).getAttribute("value"); ///value of address2
		  System.out.println("address2 value::"+address2);
		  
		  
		  if (!address2.equalsIgnoreCase("")) {
			  driver.findElement(By.xpath("//*[@id='address2']")).clear(); //clear the existing address2
			  System.out.println("address2 is NOT null");
			  addrChecker=0;
		} else {
			 System.out.println("address2 was  null");
			 driver.findElement(By.xpath("//*[@id='address2']")).sendKeys("1450 11th Street");
			 addrChecker=1;
			
		}
          driver.findElement(By.xpath("//*[@id='firstName']")).click();//click on the First name field	
		  Thread.sleep(10000);
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  
		
		  if (address1.equalsIgnoreCase("789 Main St")) {
			  driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[2]/div/article/div[2]/div/input")).sendKeys("123");
			  System.out.println("entered 1st");
			  addrChecker=1;
			
		} else {
			if (address1.equals("123 Main St")) {
				driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[2]/div/article/div[2]/div/input")).sendKeys("789");
				System.out.println("entered 2nd");
				addrChecker=0;
			}
			
		}
		  WebElement addr2= driver.findElement(By.xpath("//*[@id='addressValidationModal']/div/form/div[3]/button[1]"));
		
		  System.out.println("Found YES button  "+addr2.getLocation());
          addr2.click();
		  System.out.println("Clicked on YES button");
		  Thread.sleep(4000);
		  WebElement buttonUPDATE=driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[4]/button"));//finding UPDATE button

		  buttonUPDATE.click();
		  System.out.println("Clicked on UPDATE  button");
		  try {
			  String addrUpdateMsg=null;
			  addrUpdateMsg=driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[1]/div[2]")).getText(); //finding SUCCESS message of address updation
			  if (country.equals("en_US")|| country.equals("en_CA")) {
				if (addrUpdateMsg.equalsIgnoreCase("Your address has been successfully updated ")) {
					Log.info("Address update meaasge coming SUCCESSFULLY");
				}
			} else {
				if(addrUpdateMsg.equalsIgnoreCase("Tu dirección fue actualizada exitosamente")) {
					  Log.info("Address update meaasge coming SUCCESSFULLY");	
				}
			}
			  
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(e.toString());
			Log.info("Address update meaasge NOT coming or somethig else");
		}
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[2]/a")).click(); //click on Close button of SUCCESS pop-up window
		  Thread.sleep(2000);
		  WebElement updatedAddress=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/p[2]")); // updated address1
		  System.out.println(updatedAddress.getText());
		  if (addrChecker==0) {
			if (updatedAddress.getText().equalsIgnoreCase("789 Main St")) {
				Reporter.log("Shipping address updated successfully");
				Log.info("Ship address get updated successfully");
			}
			else {
				Reporter.log("Shipping address NOT updated successfully");
				  Log.info("Ship address NOT updated successfully");
			}
		} else {
			if (updatedAddress.getText().equalsIgnoreCase("123 Main St ,  1450+11th+Street")) {
				  Reporter.log("Shipping address updated successfully");
				  Log.info("Ship address get updated successfully");	
			}
			else {
				Reporter.log("Shipping address NOT updated successfully");
				  Log.info("Ship address NOT updated successfully");
			}
		}
	  }
	  
	  
	  
	  
	  
	  
	  @Test(priority=5,enabled=false)
	  @Parameters ({"sBrowser","sCountry"})
	  public void changePrimaryShiAddr1(String browser) throws InterruptedException {
		  WebElement shipAddName=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[3]/p[1]/a")); //name of shipping address which we want to change as primary
		  String shipAddrName=shipAddName.getText();
		  WebElement checkboxPrimAddr=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[3]/div[2]/p/span[1]/input")); //primary address checkbox
		  checkboxPrimAddr.click();
		  driver.navigate().refresh();
		  Thread.sleep(5000);
		  WebElement shipTab=driver.findElement(By.xpath("//*[@id='page-content']/div/section/div/div/a[2]"));//finding Shipping Address tab
		  wait.until(ExpectedConditions.visibilityOf(shipTab));
		  System.out.println("End of ship tab visiblity wait");
		  wait.until(ExpectedConditions.elementToBeClickable(shipTab));
		  System.out.println("End of ship tab clickable wait");
		  shipTab.click();
		  Thread.sleep(3000);
		  WebElement addrname=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[1]/p[1]/a"));
		  if (addrname.getText().equals(shipAddrName)) {
			  Reporter.log("Primary address get changed correctly");
			  Log.info("Primary address get changed correctly");
			
		} else {
			  Reporter.log("Primary address NOT changed correctly");
			  Log.info("Primary address NOT changed correctly");
		}
	  }
	  
	  
	  @Test(priority=6,enabled=false)
	  @Parameters ({"sBrowser","sCountry"})
	  public void changePrimaryShiAddr2(String browser) throws InterruptedException {
		  WebElement shipAddName=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/p[1]/a"));
		  String shipAddrName=shipAddName.getText();
		  driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[2]/div[1]/p[1]/a")).click();//click on edit link of ship address
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[3]/fieldset/div[10]/span[1]/input")).click();
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[4]/button")).click();
		  Thread.sleep(2000);
		  WebElement addrname=driver.findElement(By.xpath("//*[@id='account-addresses']/ul/li[1]/p[1]/a"));
		  if (addrname.getText().equals(shipAddrName)) {
			  Reporter.log("Primary address get changed correctly");
			  Log.info("Primary address get changed correctly");
			
		} else {
			  Reporter.log("Primary address NOT changed correctly");
			  Log.info("Primary address NOT changed correctly");
		}
		  
	  }
	  
	  
	  @Test(priority=7,enabled=true)
	  @Parameters ({"sBrowser","sCountry"})
	  public void addNewAddress(String browser, String country) throws Exception {
		  Thread.sleep(5000);		  
		  WebElement shipTab=driver.findElement(By.xpath("//*[@id='page-content']/div/section/div/div/a[2]"));//finding Shipping Address tab
		  wait.until(ExpectedConditions.visibilityOf(shipTab));
		  System.out.println("End of ship tab visiblity wait");
		  wait.until(ExpectedConditions.elementToBeClickable(shipTab));
		  System.out.println("End of ship tab clickable wait");
		  shipTab.click();
		  driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);
		  WebElement shipAddress=driver.findElement(By.xpath("//*[@id='account-addresses']"));//shipping address section
		  wait.until(ExpectedConditions.visibilityOf(shipAddress));
		  
		  driver.findElement(By.xpath("//*[@id='page-content']/div/section/div/div/div[2]/div[2]/div[2]/button")).click(); //click on ADD NEW ADDRESS button
		  Thread.sleep(2000);
		  driver.findElement(By.id("firstName")).sendKeys("onkarS3"); //enter 1st name
		  driver.findElement(By.id("lastName")).sendKeys("nimjeS3"); //enter last name
		  driver.findElement(By.id("phoneNumber")).sendKeys("1111 111-1111"); //enter phone number
		  driver.findElement(By.id("address1")).sendKeys("Hawaii State Chapter"); //enter address1
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[3]/fieldset/div[5]/div/div[2]/label/a")).click(); //enter + button
		  driver.findElement(By.id("address2")).sendKeys("4155 Diamond Head Road"); //enter address2
		  driver.findElement(By.id("city")).sendKeys("Honolulu"); //enter city
		  WebElement selectBox=driver.findElement(By.xpath("//*[@id='state']")); //select state
		  Select sele=new Select(selectBox);
		  sele.selectByValue("HI");
		  driver.findElement(By.id("zipCode")).sendKeys("96816"); //enter zip code
		  driver.findElement(By.id("firstName")).click(); //go to 1st name field
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[4]/input")).click(); //click on ADD Button
		  
		  
		  try {
			  String addrUpdateMsg=null;
			  addrUpdateMsg=driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[1]/div[2]")).getText(); //finding SUCCESS message of address updation
			  if (country.equals("en_US")||country.equals("en_CA")) {
				if (addrUpdateMsg.equalsIgnoreCase("Your address has been successfully updated ")) {
					Log.info("Address update meaasge coming SUCCESSFULLY");
				}
			} else {
				if(addrUpdateMsg.equalsIgnoreCase("Tu dirección fue actualizada exitosamente")) {
					  Log.info("Address update meaasge coming SUCCESSFULLY");	
				}
			}
			  
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(e.toString());
			Log.info("Address update meaasge NOT coming or somethig else");
		}
		  
		  driver.findElement(By.xpath("//*[@id='simplePopup']/div/div/div[2]/form/div[2]/a")).click(); //click on Close button of SUCCESS pop-up window
		 try {
			Assert.assertEquals(driver.findElement(By.linkText("onkarS3  nimjeS3")).getText(), "onkarS3  nimjeS3");
			Reporter.log("New shipping address get added into profile.");
			Log.info("New shipping address get added into profile.");
		} catch (Exception e) {
			Reporter.log(e.toString());
			Reporter.log("New shipping address NOT get added into profile.");
			Log.info("New shipping address NOT get added into profile.");
		}
		  
	  }
	  
	  
	  @Test(priority=8,enabled=true,dependsOnMethods="addNewAddress")
	  @Parameters ({"sBrowser","sCountry"})
	  public void deleteAddress(String browser, String country) throws Exception {
		  Thread.sleep(5000);
		  int deleteCount=0;
		  String languageText=null;
		  if (country.equalsIgnoreCase("en_US") || country.equalsIgnoreCase("en_CA")) 
			languageText="delete";
		  else  if (country.equalsIgnoreCase("es_US")) languageText="eliminar";
		 
		  WebElement shipAddrSection=driver.findElement(By.id("account-addresses"));
		  List<WebElement> delete=shipAddrSection.findElements(By.linkText(languageText));  //listing how many shipping address present
		  deleteCount=delete.size();
		  System.out.println("total address::"+deleteCount+1);
		  if (deleteCount>0) {
			  for (int i = 0; i < delete.size(); i++) {
				  delete.get(i).click(); //clicking on delete link 1 by one to check the address which we want to delete
				  Thread.sleep(2000);
				  String addressName=driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[3]/div/ul/li[1]/strong")).getText();  //finding address name from delete popup window
				  System.out.println(addressName);
				  if(addressName.equals("onkarS3 nimjeS3")) {
					  System.out.println("address which we are going to delete:::"+addressName);
					  System.out.println("finding delete button");
					  
					  driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[4]/input[7]")).click();  //clicking on delete button from delete popup window
					  System.out.println("delete button clicked successfully");
					  Thread.sleep(3000);
					  try {
						  String addrUpdateMsg=null;
						  addrUpdateMsg=driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[1]/div[2]")).getText(); //finding SUCCESS message of address updation
						  System.out.println("got delete success message"+addrUpdateMsg);
						  
						  if (country.equals("en_US")||country.equals("en_CA")) {
							if (addrUpdateMsg.equalsIgnoreCase("Your address has been successfully deleted")) {
								Reporter.log("Address delete  meaasge coming SUCCESSFULLY");
								Log.info("Address delete  meaasge coming SUCCESSFULLY");
							}
						} else {
							if(addrUpdateMsg.equalsIgnoreCase("Tu dirección fue eliminada exitosamente")) {
								Reporter.log("Address delete  meaasge coming SUCCESSFULLY");
								Log.info("Address delete meaasge coming SUCCESSFULLY");	
							}
						}
						  
					} catch (Exception e) {
						// TODO: handle exception
						Reporter.log(e.toString());
						Reporter.log("Address update meaasge NOT coming or somethig else");
						Log.info("Address update meaasge NOT coming or somethig else");
					}
					  
					  driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[2]/a")).click(); //click on Close button of SUCCESS pop-up window
					  System.out.println("Clicked on CLOSE button of DELETE address window");
					  try {//here we are checking deleted address present in shipping address section or not
						  System.out.println("HERE I AM ");
							driver.findElement(By.linkText("onkarS3 nimjeS3"));
							Reporter.log("ADDRESS NOT DELETED SUCCESSFULLY");
							Log.info("ADDRESS NOT DELETED SUCCESSFULLY");
							System.out.println("ADDRESS NOT DELETED SUCCESSFULLY");
						} catch (Exception e) {
							Reporter.log("ADDRESS DELETION SUCCESSFULLY");
							Log.info("ADDRESS DELETION SUCCESSFULLY");
							System.out.println("ADDRESS DELETION SUCCESSFULLY");
						}
				  break;
				  }
				  else {
					driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[4]/a")).click();  //clicking on NO button from delete popup window
					System.out.println("");
					Thread.sleep(1000);
				}
				  Reporter.log("Address'onkarS3 nimjeS3' not found which we want to delete");
			  }
			 
		} else {
           System.out.println("Cant delete primary shipping address");
		}
	  }
	  
	  
	  
	  @AfterTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	  public void tearDown(String browser) throws Exception {
	    Log.endTestCase("Registration");
	  //  Reporter.log("I am in "+browser);
	  //  driver.close();
	  //  driver.quit();
	    
	  }
	 
}
