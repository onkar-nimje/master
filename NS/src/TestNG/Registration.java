package TestNG;

import java.util.Iterator;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.Math;

public class Registration {
 
	 WebDriver driver;
	 WebElement ele1;
	 Actions builder;
	 Action act1;
	 
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
				driver.manage().window().maximize();
			    driver.get("http://www.netshoes.com.br");
			    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			   // ele1=driver.findElement(By.cssSelector("span.user-name"));
			    ele1=driver.findElement(By.cssSelector("span.menu-item"));
			 //   ele1=driver.findElement(By.xpath("html/body/div[3]/div[1]/div[1]/div/ul[2]/li/span"));
			    System.out.println(ele1);
			    System.out.println(ele1.getText());
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
		// Reporter.log("I am in "+browser);
		 try {
			 if(!ele1.getText().equalsIgnoreCase("Visitante") && !ele1.getText().equalsIgnoreCase("Entrar") )
			    {System.out.println("alreadyLoggedIn");	
				    act1=builder.moveToElement(ele1).build();
				    act1.perform();
				    driver.findElement(By.linkText("Sair")).click();
				    Log.info("Already Logged in user successfully LOGOUT");
				    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				}
		} catch (Exception e) {
			Log.info(e.toString());
		}
		 
		 
	 }
	 
	  @Test(priority=1,enabled=false)
	  @Parameters ({"sBrowser"})
	  public void exisitngNormalUser(String browser) throws Exception {
		  try {
			//Existing Normal User
			    act1=builder.moveToElement(ele1).build();
			    act1.perform();
			 //   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    System.out.println("sttt1");
			    WebElement ele2=driver.findElement(By.xpath("html/body/div[3]/div[1]/div[1]/div/ul[2]/li"));
				 List<WebElement> li=ele2.findElements(By.tagName("li"));
				 int count=0;
				 for (int i = 0; i < li.size(); i++) 
				 {
					System.out.println(li.get(i).getText());
					if(li.get(i).getText().equalsIgnoreCase("Login"))
					{
					li.get(i).click();
					break;		
					}	
				 }			
			//	driver.get("https://www.netshoes.com.br/account/login.jsp");
			//	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys("sushant.parab@oe.com");
			    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Um usuário já existe com o login sushant.parab@oe.com"))
			    	Log.info("'Exisitng Normal User' test case PASSED");
			    else 
					Log.info("'Exisitng Normal User' test case FAILED");
		} catch (Exception e) {
			Log.info(e.toString());
			Reporter.log(e.toString());
		}
		  
	  }
	  
	  
	  
	  @Test(priority=2,enabled= false)
	  @Parameters ({"sBrowser"})
	  public void existingCompanyUser(String browser) throws Exception {
		  try {
			  // Existing Company User
			  System.out.println("cexistingCompanyUser");
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys("comp.prod2@ns.com");
			    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Um usuário já existe com o login comp.prod2@ns.com"))
			    	Log.info("'Exisitng Company User' test case PASSED");
			    else 
					Log.info("'Exisitng Company User' test case FAILED");
			   
		} catch (Exception e) {
			Log.info(e.toString());
		}
	  }
	  
	  
	  
	  public int randomiza(int n){
    	  int ranNum=(int) Math.round(Math.random()*n);
		  return ranNum;
      }
      
      public int mod(int dividendo,int divisor) { 
		  return (int) Math.round(dividendo - (Math.floor(dividendo/divisor)*divisor)); }
      
      public String newCPFgenerator() {
    	  String newCPF;	
	      int n=9,
	          n1=randomiza(n),
	    	  n2 = randomiza(n),
	    	  n3 = randomiza(n),
	    	  n4 = randomiza(n),
	    	  n5 = randomiza(n),
	    	  n6 = randomiza(n),
	    	  n7 = randomiza(n),
	    	  n8 = randomiza(n),
	    	  n9 = randomiza(n),
	          d1 = n9*2+n8*3+n7*4+n6*5+n5*6+n4*7+n3*8+n2*9+n1*10; 
		      d1 = 11 - ( mod(d1,11) );
		  if (d1>=10) d1 = 0;
		  
		  int d2 = d1*2+n9*3+n8*4+n7*5+n6*6+n5*7+n4*8+n3*9+n2*10+n1*11; 
		      d2 = 11 - ( mod(d2,11) ); 
		  if (d2>=10) d2 = 0; 
		  
		  newCPF=""+n1+n2+n3+"."+n4+n5+n6+"."+n7+n8+n9+"-"+d1+d2;
		  System.out.println("NEWCPF::"+newCPF);
		  return newCPF;
      }
      
      public String newCNPJgenerator() {
    	  String newCNPJ,newCNPJNoSlash;
		  int n = 9, 
		   n1 = randomiza(n), 
		   n2 = randomiza(n), 
		   n3 = randomiza(n), 
		   n4 = randomiza(n), 
		   n5 = randomiza(n), 
		   n6 = randomiza(n), 
		   n7 = randomiza(n), 
		   n8 = randomiza(n), 
		   n9 = randomiza(n), 
		   n10 = randomiza(n); 
		  int n11 = randomiza(n), 
		   n12 = randomiza(n), 
		   d1 = n12*2+n11*3+n10*4+n9*5+n8*6+n7*7+n6*8+n5*9+n4*2+n3*3+n2*4+n1*5; 
		  d1 = 11 - ( mod(d1,11) ); 
		  if (d1>=10) d1 = 0; 
		  int d2 = d1*2+n12*3+n11*4+n10*5+n9*6+n8*7+n7*8+n6*9+n5*2+n4*3+n3*4+n2*5+n1*6; 
		  d2 = 11 - ( mod(d2,11) ); 
		  if (d2>=10) d2 = 0; 
		  newCNPJ=""+n1+n2+"."+n3+n4+n5+"."+n6+n7+n8+"/"+n9+n10+n11+n12+"-"+d1+d2;
		  System.out.println("NEW CNPJ with /::"+newCNPJ);
		  newCNPJNoSlash=""+n1+n2+"."+n3+n4+n5+"."+n6+n7+n8+""+n9+n10+n11+n12+"-"+d1+d2;
		  System.out.println("NEW CNPJ without /::"+newCNPJNoSlash);
		  return newCNPJNoSlash;
      }
      
      @Test(priority=3,enabled= false)
	  @Parameters ({"sBrowser"})
	  public void testExistingCPF(String browser) throws Exception {			  
			    driver.findElement(By.id("email")).clear();
			   // driver.findElement(By.id("email")).sendKeys(newCPF + "@hmg06.com");
			    driver.findElement(By.id("email")).sendKeys(newCPFgenerator() + "@hmg06.com");
			    driver.findElement(By.id("email")).clear();
			  //  driver.findElement(By.id("email")).sendKeys(newCPF + "@hmg06.com");
			    driver.findElement(By.id("email")).sendKeys(newCPFgenerator() + "@hmg06.com");
			    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			    driver.findElement(By.id("name")).clear();
			    driver.findElement(By.id("name")).sendKeys("Sushant");
			    driver.findElement(By.id("name")).clear();
			    driver.findElement(By.id("name")).sendKeys("Sushant");
			    driver.findElement(By.id("last-name")).clear();
			    driver.findElement(By.id("last-name")).sendKeys("Parab");
			    driver.findElement(By.id("last-name")).clear();
			    driver.findElement(By.id("last-name")).sendKeys("Parab");
			    driver.findElement(By.id("male")).click();
			    driver.findElement(By.id("male")).click();
			    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("1");
			    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("1");
			    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
			    new Select(driver.findElement(By.id("monthBrithday"))).selectByVisibleText("Mar");
			    new Select(driver.findElement(By.id("monthBrithday"))).selectByVisibleText("Mar");
			    driver.findElement(By.cssSelector("#monthBrithday > option[value=\"3\"]")).click();
			    driver.findElement(By.id("yearBrithday")).click();
			    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("1988");
			    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("1988");
			    driver.findElement(By.cssSelector("option[value=\"1988\"]")).click();
			    // Existing CPF
			    driver.findElement(By.id("cpf")).clear();
			    driver.findElement(By.id("cpf")).sendKeys("012.280.888-61");
			    driver.findElement(By.id("cpf")).clear();
			    driver.findElement(By.id("cpf")).sendKeys("012.280.888-61");
			    driver.findElement(By.id("cep-1")).clear();
			    driver.findElement(By.id("cep-1")).sendKeys("01504");
			    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-1 | blur]]
			    driver.findElement(By.id("cep-2")).clear();
			    driver.findElement(By.id("cep-2")).sendKeys("001");
			    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-2 | blur]]
			    driver.findElement(By.id("address-number")).clear();
			    driver.findElement(By.id("address-number")).sendKeys("123");
			    driver.findElement(By.id("address-number")).clear();
			    driver.findElement(By.id("address-number")).sendKeys("123");
			    driver.findElement(By.id("reference")).clear();
			    driver.findElement(By.id("reference")).sendKeys("Ramada Hotel");
			    driver.findElement(By.id("reference")).clear();
			    driver.findElement(By.id("reference")).sendKeys("Ramada Hotel");
			    driver.findElement(By.id("phone-number-ddd")).clear();
			    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
			    driver.findElement(By.id("phone-number-ddd")).clear();
			    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
			    driver.findElement(By.id("phone-number")).clear();
			    driver.findElement(By.id("phone-number")).sendKeys("4-5623-4234");
			    driver.findElement(By.id("phone-number")).clear();
			    driver.findElement(By.id("phone-number")).sendKeys("4-5623-4234");
			    driver.findElement(By.id("password")).clear();
			    driver.findElement(By.id("password")).sendKeys("123456");
			    driver.findElement(By.id("password")).clear();
			    driver.findElement(By.id("password")).sendKeys("123456");
			    driver.findElement(By.id("celphone-number-ddd")).clear();
			    driver.findElement(By.id("celphone-number-ddd")).sendKeys("12");
			    driver.findElement(By.id("celphone-number-ddd")).clear();
			    driver.findElement(By.id("celphone-number-ddd")).sendKeys("12");
			    driver.findElement(By.id("celphone-number")).clear();
			    driver.findElement(By.id("celphone-number")).sendKeys("2-1323-2323");
			    driver.findElement(By.id("celphone-number")).clear();
			    driver.findElement(By.id("celphone-number")).sendKeys("2-1323-2323");
			    driver.findElement(By.id("password-characteres")).click();
			    driver.findElement(By.id("password-characteres")).click();
			    new Select(driver.findElement(By.id("favoriteTeam"))).selectByVisibleText("Gama DF");
			    driver.findElement(By.cssSelector("option[value=\"Gama DF\"]")).click();
			    driver.findElement(By.name("/atg/userprofiling/B2CProfileFormHandler.value.zattiniSubscribeToNewsletter")).click();
			    driver.findElement(By.id("check-privacy-policy")).click();
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    
			    if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Houve problemas com seu registro."))
			    	Log.info("SUCCESS");
			    else
			    	Log.info("FAILED");
			   
			    
			    if(driver.findElement(By.xpath("//form/div/p[2]")).getText().equalsIgnoreCase("Um usuário já existe com o CPF 012.280.888-61"))
			    	Log.info("SUCCESS");
			    else
			    {
			    	Log.info("FAILED");
			    	Reporter.log("not found");
			    }
			  
	  }
	  
	  @Test(priority=4,enabled=false)
	  @Parameters ({"sBrowser"})
	  public void testExistingCNPJ(String browser) throws Exception {		    
		    driver.get("https://www.netshoes.com.br/account/login.jsp");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys(newCNPJgenerator()+"@test.com");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		   // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   // driver.findElement(By.xpath("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[2]/a")).click();
		    driver.get("https://www.netshoes.com.br/account/register.jsp?target=null&emailId="+newCNPJgenerator()+"@test.com&register=business");
		  //  driver.findElement(By.linkText("pessoa jurí­dica")).click();
		    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		    
		    
		  //  driver.findElement(By.linkText("pessoa jurí­dica")).click();
		  //  driver.findElement(By.linkText("pessoa jurí­dica")).click();
		    driver.findElement(By.id("company-name")).clear();
		    driver.findElement(By.id("company-name")).sendKeys("Object");
		    driver.findElement(By.id("fantasy-name")).clear();
		    driver.findElement(By.id("fantasy-name")).sendKeys("Edge");
		    
		    // Existing CNPJ
		    driver.findElement(By.id("cnpj")).clear();
		    driver.findElement(By.id("cnpj")).sendKeys("35.394.357/0001-86");
		    driver.findElement(By.id("municipal-registration")).clear();
		    driver.findElement(By.id("municipal-registration")).sendKeys("232323");
		    driver.findElement(By.id("state-registration")).clear();
		    driver.findElement(By.id("state-registration")).sendKeys("23213");
		    driver.findElement(By.id("cep-1")).click();
		    driver.findElement(By.id("cep-1")).clear();
		    driver.findElement(By.id("cep-1")).sendKeys("01504");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-1 | blur]]
		    driver.findElement(By.id("cep-2")).clear();
		    driver.findElement(By.id("cep-2")).sendKeys("020");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-2 | blur]]
		    driver.findElement(By.id("address-number")).clear();
		    driver.findElement(By.id("address-number")).sendKeys("3455");
		    driver.findElement(By.id("phone-number-ddd")).clear();
		    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("3-1323-1313");
		    driver.findElement(By.id("celphone-number-ddd")).clear();
		    driver.findElement(By.id("celphone-number-ddd")).sendKeys("23");
		    driver.findElement(By.id("celphone-number")).clear();
		    driver.findElement(By.id("celphone-number")).sendKeys("4-3423-4422");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.id("password-characteres")).click();
		    driver.findElement(By.id("password-characteres")).click();
		    driver.findElement(By.cssSelector("option[value=\"Gama DF\"]")).click();
		    new Select(driver.findElement(By.id("favoriteTeam"))).selectByVisibleText("Gama DF");
		    driver.findElement(By.cssSelector("option[value=\"Gama DF\"]")).click();
		    driver.findElement(By.id("check-privacy-policy")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		  
		    
		    try 
		    {
		    	if(driver.findElement(By.cssSelector("p.base-feedback")).getText().equalsIgnoreCase("Houve problemas com seu registro."))
		    	{
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    try
		    {
		    	if(driver.findElement(By.xpath("//form/div/p[2]")).getText().equalsIgnoreCase("Um usuário já existe com o CNPJ 35.394.357/0001-86"))
		    	{
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
	  }
	  
	  
	  
	  
	  @Test(priority=5,enabled=false)
	  @Parameters ({"sBrowser"})
	  public void testInvalidPFRegistration(String browser) throws Exception
	  {
		//  Invalid Email ID
		    driver.get("https://www.netshoes.com.br/account/login.jsp");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("invalidpftest@yahoo");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("E-mail inválido"))
		    	{
		    		System.out.println("found1");
		    		Log.info("SUCCESS");
		    	}
		    	/*
		    	else
		    	{
		    		System.out.println("Notfound1");
		    		Log.info("FAIL");
				}
				*/
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		  //  Valid Email ID 
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("invalidpftest@yahoo.com");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		    
		//  Blank First Name 
		    driver.findElement(By.id("check-sms")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();		    
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		    
		    
		   
		    
		   //  Blank Last Name 
		    driver.findElement(By.id("name")).clear();
		    driver.findElement(By.id("name")).sendKeys("Sushant");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=name | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found3");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		    
		    
		   
		    
		    //  Blank Gender
		    driver.findElement(By.id("last-name")).clear();
		    driver.findElement(By.id("last-name")).sendKeys("Parab");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=last-name | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found4");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		   
		    //  Invalid Date 
		    driver.findElement(By.id("male")).click();
		    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("29");
		   // driver.findElement(By.cssSelector("option[value=\"29\"]")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=dayBrithday | blur]]
		    new Select(driver.findElement(By.id("monthBrithday"))).selectByVisibleText("Fev");
		   // driver.findElement(By.cssSelector("#monthBrithday > option[value=\"2\"]")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=monthBrithday | blur]]
		    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("1997");
		   // driver.findElement(By.cssSelector("option[value=\"1997\"]")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=yearBrithday | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Data inválida"))
		    	{
		    		System.out.println("found5");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		    
		   
		    //  Invalid CPF 
		    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("14");
		  //  driver.findElement(By.cssSelector("option[value=\"14\"]")).click();
		    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("2002");
		  //  driver.findElement(By.cssSelector("option[value=\"2002\"]")).click();
		    driver.findElement(By.id("cpf")).clear();
		    driver.findElement(By.id("cpf")).sendKeys("234.234.342-34");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cpf | blur]]
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Que tal verificar este campo"))
		    	{
		    		System.out.println("found6");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		   
		    driver.findElement(By.id("cpf")).clear();
		    driver.findElement(By.id("cpf")).sendKeys(newCPFgenerator());
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cpf | blur]]
		    driver.findElement(By.id("cep-1")).clear();
		    driver.findElement(By.id("cep-1")).sendKeys("01504");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-1 | blur]]
		    driver.findElement(By.id("cep-2")).clear();
		    driver.findElement(By.id("cep-2")).sendKeys("001");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-2 | blur]]
		    driver.findElement(By.id("address-number")).clear();
		    driver.findElement(By.id("address-number")).sendKeys("343");
		    driver.findElement(By.id("reference")).clear();
		    driver.findElement(By.id("reference")).sendKeys("Ramada Hotel");
		    driver.findElement(By.id("phone-number-ddd")).clear();
		    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("3-2321-3232");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.id("password-characteres")).click();
		    new Select(driver.findElement(By.id("favoriteTeam"))).selectByVisibleText("Remo PA");
		    driver.findElement(By.cssSelector("option[value=\"Remo PA\"]")).click();
		    //  Terms & Conditions Not Checked 
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found7");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }	   
	  }
	  
	  
	  @Test(priority=6,enabled=false)
	  @Parameters ({"sBrowser"})
	  public void testInvalidPJRegistration(String browser) throws Exception{
		  System.out.println(newCNPJgenerator());
		    //  Invalid Email ID 
		    driver.get("https://www.netshoes.com.br/account/login.jsp");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("invalidpjtest@ya");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		    driver.manage().timeouts().implicitlyWait(05,TimeUnit.SECONDS);		    
		    try {
				Assert.assertEquals(driver.findElement(By.cssSelector("span.tooltip-up")).getText(), "E-mail inválido");
			} catch (Error e) {
				// TODO: handle exception
				Reporter.log(e.toString());
			}
		    
		    //  Valid Email ID 
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("invalidpjtest@yahoo.com");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		    //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
		    driver.get("https://www.netshoes.com.br/account/register.jsp?target=null&emailId=invalidpjtest@yahoo.com&register=business");
		    
		    
		   // driver.findElement(By.xpath("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[2]/a")).click();
		   // driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		    //  Blank First Name 
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    //  Blank Last Name 
		    driver.findElement(By.id("company-name")).clear();
		    driver.findElement(By.id("company-name")).sendKeys("Object");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=company-name | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    //  Blank CNPJ 
		    driver.findElement(By.id("fantasy-name")).clear();
		    driver.findElement(By.id("fantasy-name")).sendKeys("Edge");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=fantasy-name | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    //  Invalid CNPJ 
		    driver.findElement(By.id("cnpj")).clear();
		    driver.findElement(By.id("cnpj")).sendKeys("31.232.132/3233-31");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cnpj | blur]]
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Que tal verificar este campo"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		   
		    //  Valid CNPJ 
		    driver.findElement(By.id("cnpj")).clear();
		    driver.findElement(By.id("cnpj")).sendKeys(newCNPJgenerator());
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cnpj | blur]]
		    driver.findElement(By.id("municipal-registration")).clear();
		    driver.findElement(By.id("municipal-registration")).sendKeys("Mahape");
		    driver.findElement(By.id("exempt")).click();
		    driver.findElement(By.id("cep-1")).clear();
		    driver.findElement(By.id("cep-1")).sendKeys("01504");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-1 | blur]]
		    driver.findElement(By.id("cep-2")).clear();
		    driver.findElement(By.id("cep-2")).sendKeys("020");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-2 | blur]]
		    driver.findElement(By.id("address-number")).clear();
		    driver.findElement(By.id("address-number")).sendKeys("3454532");
		    driver.findElement(By.id("reference")).clear();
		    driver.findElement(By.id("reference")).sendKeys("Taj Hotel");
		    driver.findElement(By.id("celphone-number-ddd")).clear();
		    driver.findElement(By.id("celphone-number-ddd")).sendKeys("12");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=celphone-number-ddd | blur]]
		    driver.findElement(By.id("celphone-number")).clear();
		    driver.findElement(By.id("celphone-number")).sendKeys("3-2323-3133");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=celphone-number | blur]]
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.id("password-characteres")).click();
		    driver.findElement(By.name("/atg/userprofiling/B2CProfileFormHandler.value.zattiniSubscribeToNewsletter")).click();
		    //  Blank Phone Number 
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		    
		    driver.findElement(By.id("phone-number-ddd")).clear();
		    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=phone-number-ddd | blur]]
		    //  Invlaid Phone Number 
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("4");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=phone-number | blur]]
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Que tal verificar este campo"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    
		    
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("4-3454-5345");
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("4-3454-5345");
		    //  Terms & Conditions Not Checked 
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    try
		    {
		    	if(driver.findElement(By.cssSelector("span.tooltip-up")).getText().equalsIgnoreCase("Ops! Este campo é obrigatório"))
		    	{
		    		System.out.println("found2");
		    		Log.info("SUCCESS");
		    	}	
		    	else
		    	{
		    		Log.info("FAIL");
				}
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
	 }
	  
	  
	  @Test(priority=7,enabled=true)
	  @Parameters ({"sBrowser"})
	  public void testPFRegistration() throws Exception  {
		    driver.get("https://www.netshoes.com.br/account/login.jsp");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys(newCPFgenerator()+"@hmg06.com");
		    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		    driver.findElement(By.id("name")).clear();
		    driver.findElement(By.id("name")).sendKeys("Sushant");
		    driver.findElement(By.id("last-name")).clear();
		    driver.findElement(By.id("last-name")).sendKeys("Parab");
		    driver.findElement(By.id("male")).click();
		    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("1");
		    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
		    new Select(driver.findElement(By.id("monthBrithday"))).selectByVisibleText("Mar");
		    new Select(driver.findElement(By.id("monthBrithday"))).selectByVisibleText("Mar");
		    driver.findElement(By.cssSelector("#monthBrithday > option[value=\"3\"]")).click();
		    driver.findElement(By.id("yearBrithday")).click();
		    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("1988");
		    new Select(driver.findElement(By.id("yearBrithday"))).selectByVisibleText("1988");
		    driver.findElement(By.cssSelector("option[value=\"1988\"]")).click();
		    driver.findElement(By.id("cpf")).clear();
		    driver.findElement(By.id("cpf")).sendKeys(newCPFgenerator());
		    driver.findElement(By.id("cpf")).clear();
		    driver.findElement(By.id("cpf")).sendKeys(newCPFgenerator());
		    driver.findElement(By.id("cep-1")).clear();
		    driver.findElement(By.id("cep-1")).sendKeys("01504");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-1 | blur]]
		    driver.findElement(By.id("cep-2")).clear();
		    driver.findElement(By.id("cep-2")).sendKeys("001");
		    // ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=cep-2 | blur]]
		    driver.findElement(By.id("address-number")).clear();
		    driver.findElement(By.id("address-number")).sendKeys("123");
		    driver.findElement(By.id("address-number")).clear();
		    driver.findElement(By.id("address-number")).sendKeys("123");
		    driver.findElement(By.id("reference")).clear();
		    driver.findElement(By.id("reference")).sendKeys("Ramada Hotel");
		    driver.findElement(By.id("reference")).clear();
		    driver.findElement(By.id("reference")).sendKeys("Ramada Hotel");
		    driver.findElement(By.id("phone-number-ddd")).clear();
		    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("phone-number-ddd")).clear();
		    driver.findElement(By.id("phone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("4-5623-4234");
		    driver.findElement(By.id("phone-number")).clear();
		    driver.findElement(By.id("phone-number")).sendKeys("4-5623-4234");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.id("celphone-number-ddd")).clear();
		    driver.findElement(By.id("celphone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("celphone-number-ddd")).clear();
		    driver.findElement(By.id("celphone-number-ddd")).sendKeys("12");
		    driver.findElement(By.id("celphone-number")).clear();
		    driver.findElement(By.id("celphone-number")).sendKeys("2-1323-2323");
		    driver.findElement(By.id("celphone-number")).clear();
		    driver.findElement(By.id("celphone-number")).sendKeys("2-1323-2323");
		    driver.findElement(By.id("password-characteres")).click();
		    driver.findElement(By.id("password-characteres")).click();
		    new Select(driver.findElement(By.id("favoriteTeam"))).selectByVisibleText("Gama DF");
		    driver.findElement(By.id("check-privacy-policy")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    // Warning: verifyTextNotPresent may require manual changes
		    try {
		      Assert.assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Olá Visitante[\\s\\S]*$"));
		      
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    try {
		      Assert.assertFalse(driver.getCurrentUrl().matches("^[\\s\\S]*error[\\s\\S]*$"));
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    // ERROR: Caught exception [Error: unknown strategy [class] for locator [class=menu-item]]
		    // Search & Buy a Product
		    driver.findElement(By.id("search")).clear();
		    driver.findElement(By.id("search")).sendKeys("camiseta");
		    driver.findElement(By.cssSelector("button.input-search-bt")).click();
		    driver.findElement(By.cssSelector("img.lazy")).click();
		    // Select 1st Size
		    driver.findElement(By.xpath("//li[1]/label/span")).click();
		  //  boolean NotAvailable = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		  //  boolean Size2 = isElementPresent(By.xpath("//li[2]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 2ndSize
		    driver.findElement(By.xpath("//li[2]/label/span")).click();
		 //   boolean NotAvailable2 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		 //   boolean Size3 = isElementPresent(By.xpath("//li[3]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 3rd Size
		    driver.findElement(By.xpath("//li[3]/label/span")).click();
		//    boolean NotAvailable3 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		//    boolean Size4 = isElementPresent(By.xpath("//li[4]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 4th Size
		    driver.findElement(By.xpath("//li[4]/label/span")).click();
		//    boolean NotAvailable4 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		//    boolean Size5 = isElementPresent(By.xpath("//li[5]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select th Size
		    driver.findElement(By.xpath("//li[5]/label/span")).click();
		//    boolean NotAvailable5 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		 //   boolean Size6 = isElementPresent(By.xpath("//li[6]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 6th Size
		    driver.findElement(By.xpath("//li[6]/label/span")).click();
		//    boolean NotAvailable6 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
	//	    boolean Size7 = isElementPresent(By.xpath("//li[7]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 7th Size
		    driver.findElement(By.xpath("//li[7]/label/span")).click();
		//    boolean NotAvailable7 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		//    boolean Size8 = isElementPresent(By.xpath("//li[8]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 8th Size
		    driver.findElement(By.xpath("//li[8]/label/span")).click();
		 //   boolean NotAvailable8 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Check if next size is available
		//    boolean Size9 = isElementPresent(By.xpath("//li[9]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Select 9th Size
		    driver.findElement(By.xpath("//li[9]/label/span")).click();
		//    boolean NotAvailable9 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.name("buy")).click();
		    // ERROR: Caught exception [unknown command [goto]]
		    // Selecting 2nd Product
		    // ERROR: Caught exception [unknown command [label]]
		    //  Search Bota
		    driver.findElement(By.id("search")).clear();
		    driver.findElement(By.id("search")).sendKeys("bota");
		    driver.findElement(By.cssSelector("button.input-search-bt")).click();
		    //  Selecting 1st Product 
		    driver.findElement(By.cssSelector("img.lazy")).click();
		    driver.findElement(By.xpath("//li[1]/label/span")).click();
		//    boolean NotAvailable10 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size10 = isElementPresent(By.xpath("//li[2]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[2]/label/span")).click();
		//    boolean NotAvailable11 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size11 = isElementPresent(By.xpath("//li[3]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[3]/label/span")).click();
		//    boolean NotAvailable12 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		 //   boolean Size12 = isElementPresent(By.xpath("//li[4]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[4]/label/span")).click();
		//    boolean NotAvailable13 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size13 = isElementPresent(By.xpath("//li[5]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[5]/label/span")).click();
		//    boolean NotAvailable14 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size14 = isElementPresent(By.xpath("//li[6]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[6]/label/span")).click();
		//    boolean NotAvailable15 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size15 = isElementPresent(By.xpath("//li[7]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[7]/label/span")).click();
		//    boolean NotAvailable16 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size16 = isElementPresent(By.xpath("//li[8]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[8]/label/span")).click();
		 //   boolean NotAvailable17 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size17 = isElementPresent(By.xpath("//li[9]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[9]/label/span")).click();
		 //   boolean NotAvailable18 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.name("buy")).click();
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // Selecting 3rd Product
		    driver.get("http://www.netshoes.com.br"+"/produto/camisa-feminina-nike-selecao-brasil-i-2014-sn-004-2690-046");
		    driver.findElement(By.xpath("//li[1]/label/span")).click();
		 //   boolean NotAvailable19 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size19 = isElementPresent(By.xpath("//li[2]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[2]/label/span")).click();
		 //   boolean NotAvailable20 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		//    boolean Size320 = isElementPresent(By.xpath("//li[3]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[3]/label/span")).click();
		//    boolean NotAvailable21 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
	//	    boolean Size21 = isElementPresent(By.xpath("//li[4]/label/span"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.xpath("//li[4]/label/span")).click();
		//    boolean NotAvailable22 = isElementPresent(By.cssSelector("p.unavailable-message"));
		    // ERROR: Caught exception [unknown command [gotoIf]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    // ERROR: Caught exception [unknown command [goto]]
		    // ERROR: Caught exception [unknown command [label]]
		    driver.findElement(By.cssSelector("button.hot-button.medium-button")).click();
		    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
		    try {
		      Assert.assertEquals(driver.findElement(By.cssSelector("h2.payment-title.is-boleto > strong")).getText(), "FALTA POUCO! AGORA É SÓ PAGAR O BOLETO");
		    } catch (Error e) {
		      Reporter.log(e.toString());
		    }
		    try {
		      Assert.assertEquals(driver.findElement(By.cssSelector("h2.payment-title.is-boleto > span")).getText(), "SEU PEDIDO SERÁ ENVIADO SOMENTE APÓS A CONFIRMAÇÃO DE PAGAMENTO");
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    driver.findElement(By.linkText("Netshoes. Viva o esporte.")).click();
		    driver.findElement(By.id("search")).clear();
		    driver.findElement(By.id("search")).sendKeys("tenis");
		    driver.findElement(By.cssSelector("button.input-search-bt")).click();
		    driver.findElement(By.cssSelector("img.lazy")).click();
		    driver.findElement(By.name("add")).click();
		    try {
		      Assert.assertEquals(driver.findElement(By.cssSelector("p.base-feedback")).getText(), "Por favor, selecione o tamanho para continuar");
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    driver.findElement(By.xpath("//li[2]/label/span")).click();
		    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
		    try {
		      Assert.assertEquals(driver.findElement(By.cssSelector("h1.my-account-title")).getText(), "Lista de desejos");
		    } catch (Error e) {
		    	Reporter.log(e.toString());
		    }
		    driver.findElement(By.linkText("Sair")).click();
		    // ERROR: Caught exception [unknown command [label]]
		  

	  }
	  
	  @AfterTest(alwaysRun = true)
	  @Parameters ({"sBrowser"})
	// @AfterSuite(alwaysRun = true)
	  public void tearDown(String browser) throws Exception {
	    Log.endTestCase("Registration");
	  //  Reporter.log("I am in "+browser);
	  //  driver.close();
	    driver.quit();
	    
	  }
	 
	 
	 
}
