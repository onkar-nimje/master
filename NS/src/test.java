import java.util.concurrent.TimeUnit;



import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class test {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	//private static Logger Log = Logger.getLogger(test.class.getName());
	
	public static void main(String[] args) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		 WebDriver driver;
		Log.startTestCase("LoginLogoutTestCases");
		// TODO Auto-generated method stub
		driver=new FirefoxDriver();
		driver.get("http://www.netshoes.com.br/");
	
		
		WebElement ele1=driver.findElement(By.cssSelector("span.user-name"));
		Actions builder= new Actions(driver);
		Action act1=builder.moveToElement(ele1).build();
		
		if(!ele1.getText().equalsIgnoreCase("Visitante"))
			
		{
			//Execute it if only user is already logged in
		    act1.perform();
		    driver.findElement(By.linkText("Sair")).click();
		    Log.info("Already Logged in user successfully LOGOUT");
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		
		
		
	//  Wrong Email ID 
			try {
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
		
		
		
		
		 //  Both Fields Blank 
		try {	   
	   // driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
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
		
		
		
		 //  Blank Email ID 
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
		
		
		
	    //  Blank Password 
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
	   
		
		
	    
	   
		
		
		 //  Wrong Password 
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
		
		
	//  Invalid Email ID Format
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
		
		
   //  Non Existing Email ID 
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
       
		
		//Valid user name and password. Successful login
		try {
			
		  //  act1.perform();
		 //   driver.findElement(By.linkText("Login")).click();
		    driver.findElement(By.id("email-cpf")).clear();
		    driver.findElement(By.id("email-cpf")).sendKeys("sushant.parab@oe.com");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		    WebElement ele2=driver.findElement(By.cssSelector("span.user-name"));
		    if(ele2.getText().equalsIgnoreCase("Sushant Edit"))
		    	Log.info("'8) Valid user name and password' test case PASSED");
		    else 
				Log.info("'8) Valid user name and password' test case FAILED");
		//    act1.perform();
		 //   driver.findElement(By.linkText("Sair")).click();
		 //   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
			Log.info(e.toString());
		}
		
	
		Log.endTestCase("LoginLogoutTestCases");
	}

}
