package TestNG;



import org.testng.Reporter;
import org.testng.annotations.Test;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class LoginLogout {
	
	
	 WebDriver driver;
	 WebElement ele1;
	 Actions builder;
	 Action act1;
	 
	 
	 
	 @BeforeTest(alwaysRun = true)
	 @Parameters ({"sBrowser"})
	  public void setUp(String browser) throws Exception {
		 try {
			 DOMConfigurator.configure("log4j.xml");
				Log.startTestCase("LoginLogout");
				
				if(browser.equalsIgnoreCase("Firefox"))     //FIREFOX
				{
			         driver = new FirefoxDriver();
			         Log.info("FF Driver started");
				}
				if(browser.equalsIgnoreCase("Chrome"))      //CHROME
				{
					System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
					driver=new ChromeDriver();
				}
				
				if(browser.equalsIgnoreCase("IE"))          //INTERNET EXPLORER
				{
					System.setProperty("webdriver.ie.driver", "D:\\workspaces\\IEDriverServer.exe");
					driver=new InternetExplorerDriver();
				}
				
			    driver.get("http://www.netshoes.com.br");
			    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    ele1=driver.findElement(By.cssSelector("span.user-name"));
			    System.out.println(ele1);
				builder= new Actions(driver);
				
		} catch (Exception e) {
			Log.info(e.toString());
		}
	    
	  }
	 
	 @Test(priority=0)
	 @Parameters ({"sBrowser"})
	 public void alreadyLoggedIn(String browser)
	 {
		//Execute it if user is already logged in
		 Reporter.log("I am in "+browser);
		 try {
			 if(!ele1.getText().equalsIgnoreCase("Visitante"))
					
				{
				    act1=builder.moveToElement(ele1).build();
				    act1.perform();
				    driver.findElement(By.linkText("Sair")).click();
				    Log.info("Already Logged in user successfully LOGOUT");
				    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				}
		} catch (Exception e) {
			Log.info(e.toString());
		}
		 
		 
	 }
	 
	 @Test(priority=1)//(dependsOnMethods="alreadyLoggedIn")
	 @Parameters ({"sBrowser"})
	 public void wrongEmailID(String browser)
	 {
		//  Wrong Email ID 
		 Reporter.log("I am in "+browser);
					try {
						    act1=builder.moveToElement(ele1).build();    
						    act1.perform();
					        driver.findElement(By.linkText("Login")).click();
						    driver.findElement(By.id("email-cpf")).clear();
						    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@wrong.com");
						    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=email-cpf | blur]]
						    driver.findElement(By.id("password")).clear();
						    driver.findElement(By.id("password")).sendKeys("123456");
						    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=password | blur]]
						   
						    driver.findElement(By.xpath("//button[@type='submit']")).click();
						    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
						   
						    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Ops! Usuário não encontrado."))
						    	Log.info("'1) Wrong Email ID' test case PASSED");
						    else 
								Log.info("'1) Wrong Email ID' test case FAILED");
						    
					} catch (Exception e) {
						Log.info(e.toString());
					}
				
	 }
	 
	 
	 
	 
	 
	 
	 @Test(priority=2)//(dependsOnMethods="wrongEmailID")
	 @Parameters ({"sBrowser"})
	 public void blankEmailID(String browser)
	 {
		 //  Blank Email ID 
		 Reporter.log("I am in "+browser);
			try {
				 driver.findElement(By.id("email-cpf")).clear();
			//	 driver.findElement(By.id("password")).clear();
				 driver.findElement(By.id("password")).sendKeys("12345");
			     driver.findElement(By.xpath("//button[@type='submit']")).click();
			     driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			     WebElement mess2=driver.findElement(By.xpath("//*[@id='sendFormRegistered']/div[1]/div/span"));
			         if(mess2.getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
				 //   if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
				    	Log.info("'3) Blank Email ID' test case PASSED");
				    else 
						Log.info("'3) Blank Email ID' test case FAILED");
				
			} catch (Exception e) {
				Log.info(e.toString());
			}
	 }
	 
	 
	 @Test(priority=3)//(dependsOnMethods="blankEmailID")
	 @Parameters ({"sBrowser"})
	 public void blankPassword(String browser)
	 {
		 //  Blank Password 
		 Reporter.log("I am in "+browser);
			try {
				 driver.findElement(By.id("email-cpf")).clear();
				 driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@oe.com");
				 driver.findElement(By.id("password")).clear();
			     driver.findElement(By.xpath("//button[@type='submit']")).click();
			     driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			     WebElement mess2=driver.findElement(By.xpath("//*[@id='sendFormRegistered']/div[2]/div/span"));
			         if(mess2.getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
				 //   if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
				    	Log.info("'4) Blank Password' test case PASSED");
				    else 
						Log.info("'4) Blank Password' test case FAILED");
				
			} catch (Exception e) {
				Log.info(e.toString());
			}
	 }
	 
	 
	 @Test(priority=4)//(dependsOnMethods="blankPassword")
	 @Parameters ({"sBrowser"})
	 public void wrongPassword(String browser)
	 {
		 //  Wrong Password 
		 Reporter.log("I am in "+browser);
			try {	  
			    driver.findElement(By.id("email-cpf")).clear();
			    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@oe.com");
			    driver.findElement(By.id("password")).clear();
			    driver.findElement(By.id("password")).sendKeys("wrong");
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			    
			    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("A senha fornecida não é válida. Por favor tente novamente."))
			    	Log.info("'5) Wrong Password' test case PASSED");
			    else 
					Log.info("'5) Wrong Password' test case FAILED");
			    
			} catch (Exception e) {
				Log.info(e.toString());
			}
	 }
	 
	 
	 @Test(priority=5)//(dependsOnMethods="wrongPassword")
	 @Parameters ({"sBrowser"})
	 public void bothFieldBlank(String browser)
	 {
		//  Both Fields Blank
		 Reporter.log("I am in "+browser);
			try {	   
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    driver.findElement(By.id("email-cpf")).clear();
		    driver.findElement(By.id("email-cpf")).sendKeys("");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("");  
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		    //driver.findElement(By.xpath("//button[@type='submit']")).click();
		    WebElement mess1=driver.findElement(By.cssSelector("span.tooltip-up"));
		    System.out.println(mess1.getText());
		    //WebElement mess2=driver.findElement(By.cssSelector("span.tooltip-up.firepath-matching-node"));
		    //System.out.println(mess2.getText());
		    System.out.println(driver.findElement(By.xpath("//*[@id='sendFormRegistered']/div[1]/div/span")).getText());
		    
		    if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	Log.info("'2) Both Field blank' test case PASSED");
		    else 
				Log.info("'2) Both Field blank' test case FAILED. Expected Message:  &   Actual Message:"+mess1.getText());
			
		   //   assertEquals(driver.findElement(By.cssSelector("span.tooltip-up")).getText(), "Ops! Este campo é obrigatório");
		    } catch (Error e) {
		    //  verificationErrors.append(e.toString());
		      Log.info(e.toString());
		    }
	 }
	 
	 
	 @Test(priority=6)//(dependsOnMethods="wrongPassword")
	 @Parameters ({"sBrowser"})
	 public void invalidEmailID(String browser)
	 {
		//  Invalid Email ID Format
		 Reporter.log("I am in "+browser);
	       try {
	    	    driver.findElement(By.id("email-cpf")).clear();
	    	    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@");
	    	   // driver.findElement(By.id("password")).click();
	    	  
	    	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    	    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	    	//  System.out.println(driver.findElement(By.xpath("//*[@id='sendFormRegistered']/div[1]/div/span")).getText());
	    	    System.out.println("5."+driver.findElement(By.cssSelector("span.tooltip-up")).getText());
	    	    if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! O Email ou CPF/CNPJ inserido não é válido"))
			    	Log.info("'6) Invalid Email ID Format' test case PASSED");
			    else 
					Log.info("'6) Invalid Email ID Format' test case FAILED");
	    	    
		} catch (Exception e) {
			Log.info(e.toString());
		}	
	 }
	 
	 
	 @Test(priority=7)//(dependsOnMethods="wrongPassword")
	 @Parameters ({"sBrowser"})
	 public void nonExistingEmailID(String browser)
	 {
		 //  Non Existing Email ID 
		 Reporter.log("I am in "+browser);
	       try {
	    	   
	    	    driver.findElement(By.id("email-cpf")).clear();
	    	    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@wrong.com");
	    	    driver.findElement(By.id("password")).clear();
	    	    driver.findElement(By.id("password")).sendKeys("wrong");
	    	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	    System.out.println("6."+driver.findElement(By.cssSelector("p.base-feedback")).getText());
	    	    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Ops! Usuário não encontrado."))
			    	Log.info("'7) Non Existing Email ID' test case PASSED");
			    else 
					Log.info("'7) Non Existing Email ID' test case FAILED");

		} catch (Exception e) {
			Log.info(e.toString());
		}
	 }
	 
	 
	 @Test(priority=8)//(dependsOnMethods="wrongPassword")
	 @Parameters ({"sBrowser"})
	 public void validLogin(String browser)
	 {
		//Valid user name and password. Successful login
		 Reporter.log("I am in "+browser);
			try {
				
			  //  act1.perform();
			 //   driver.findElement(By.linkText("Login")).click();
			    driver.findElement(By.id("email-cpf")).clear();
			    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@oe.com");
			    driver.findElement(By.id("password")).clear();
			    driver.findElement(By.id("password")).sendKeys("123456");
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    WebElement ele2=driver.findElement(By.cssSelector("span.user-name"));
			   
			    if(!ele2.getText().equalsIgnoreCase("Visitante"))
			    {
			    	 Action act2=builder.moveToElement(ele2).build();
			    	 act2.perform();
					driver.findElement(By.linkText("Sair")).click();
			    	Log.info("'8) Valid user name and password' test case PASSED");
			    }
			    	
			    else 
					Log.info("'8) Valid user name and password' test case FAILED");
			//    act1.perform();
			 //   driver.findElement(By.linkText("Sair")).click();
			 //   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			} catch (Exception e) {
				// TODO: handle exception
				Log.info(e.toString());
			}
	 }
	 
	  @AfterTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	// @AfterSuite(alwaysRun = true)
	  public void tearDown(String browser) throws Exception {
	    Log.endTestCase("LoginLogoutTestCases");
	    Reporter.log("I am in "+browser);
	    //driver.close();
	    driver.quit();
	    
	  }


}
