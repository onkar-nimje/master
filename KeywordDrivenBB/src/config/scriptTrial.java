package config;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Selection;
import com.google.common.base.Predicate;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class scriptTrial {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver;
		// TODO Auto-generated method stub
		//final String firebug="D:\\workspaces\\firebug.xpi";
		// File profilePath = new File("D:\\workspaces\\firebug-1.8.0.xpi");
       //  File profilePath = new File("C:\\Users\\onkar.nimje\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\y13wr7at.Selenium");
		
		//File addonpath = new File("D:\\workspaces\\firebug-1.8.0.xpi");
		//FirefoxProfile profile= new FirefoxProfile();
		
		//	profile.addExtension(profilePath);
		//profile.setPreference("extensions.firebug.currentVersion", "1.8.1");
		
		ProfilesIni initializeprofile=new ProfilesIni();
		FirefoxProfile profile=initializeprofile.getProfile("Selenium");
		
		//driver = new FirefoxDriver(profile);
		driver = new FirefoxDriver();
	     //driver = new PhantomJSDriver();
		driver.manage().window().maximize();
		//////testing without opening browser: USE HtmlUnitDriver()
		/*
		driver =new HtmlUnitDriver();	
		driver.get("http://admin:Admin123@2tgauthappdevint01.dev.dc2.beachbody.com:11000/dyn/admin/");
		*/
		
		//driver.get("http://2tgweb01.dev.dc2.beachbody.com:9080/shop/us/login");
		//driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/us/login");
		driver.get("http://2tgweb01.dev.dc2.beachbody.com:9080/shop/us");
	
	//	driver.findElement(By.id("email")).sendKeys("om_0819_1@beachbodytest.com");
	//	driver.findElement(By.id("email")).sendKeys("om_0909_11@beachbodytest.com");
		
	//	driver.findElement(By.id("password")).sendKeys("12345");
	//	driver.findElement(By.xpath("//*[@id='loginForm']/input[4]")).click();
        Thread.sleep(5000);
      
      //  driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/us/d/ultimate-reset-dual-kit-URDual");
        driver.get("http://2tgweb01.dev.dc2.beachbody.com:9080/shop/us/d/ultimate-reset-optimize-UROptimize");
       // Thread.sleep(10000);
        System.out.println("Mini cart count:::"+driver.findElement(By.xpath("//*[@id='miniCart']/a/span[2]")).getText());
       WebElement element=driver.findElement(By.xpath("//*[@id='miniCart']/a/span[2]"));
     //   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,120);
         wait.equals(element.getText().contains("("));
        
    //  WebDriverWait wait= new WebDriverWait(driver, 20);
    //  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='miniCart']/a/span[2]"))));
        WebElement ele1 = driver.findElement(By.id("sku"));
        Select sel1= new Select(ele1);
        sel1.selectByIndex(1);
        
        WebElement ele2 = driver.findElement(By.id("qty"));
        Select sel2 = new Select(ele2);
        sel2.selectByIndex(3);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='add1']/ul/li[3]/button")).click();
        Thread.sleep(3000);
      // driver.get("http://2tgweb01.dev.dc2.beachbody.com:9080/shop/us/cart");
        driver.findElement(By.xpath("//*[@id='miniCart']/a")).click();
       Thread.sleep(10000);
       WebElement skuname=driver.findElement(By.linkText("UR Optimize 30-Day HD"));
       System.out.println(skuname.getText());
       if(skuname.getText().equals("UR Optimize 30-Day HD"))
       {
    	   System.out.println("added sku found in cart page");
           
    	   WebElement skuQty=driver.findElement(By.xpath("//*[@id='MDSUCLN33105HD']/div[3]/select"));
    	   System.out.println(skuQty.getAttribute("data-option-qty"));
    	   if(skuQty.getAttribute("data-option-qty").equals("3"))
    		   System.out.println("qtantity found correctly");
    	   
       
       }
       else
       {
    	   System.out.println("sku not found");
    	   Thread.sleep(60000);
       }
       
       //
       
        
      //  Actions builder= new Actions(driver);
      //  Action act=builder.moveToElement(driver.findElement(By.id("miniCart"))).build();
      //  act.perform();
       
   /*    
        Thread.sleep(2000);
        driver.get("http://www-ultimateresetdevint2.beachbody.local/shop/us/checkout");
        Thread.sleep(2000);
        System.out.println("Original windowBEFORE:::"+driver.getWindowHandle());       */
        //driver.findElement(By.linkText("Back")).click();
        
        
       
        
        driver.findElement(By.xpath("//*[@id='changeShippingAddress']")).click();
        Thread.sleep(5000);
    
        
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> ite=windows.iterator();
        int count=0;
        System.out.println("Total window:::"+windows.size());
         while(ite.hasNext())
         {
        	 count++;
        	 String win=ite.next();
        	 System.out.println(win);
         }                                                                              
    // String originalW=driver.getWindowHandle();
    // System.out.println("originale window::"+originalW);
    //   Thread.sleep(5000);
       WebElement ele11=driver.findElement(By.id("simplePopup"));
       System.out.println(":::simplePopup Found:::");
     
       WebElement ele21=driver.findElement(By.xpath("//*[@class='simplePopupContainer beta']//*[@id='account-addresses-return-visitor']/div[3]/div[1]/button"));
       System.out.println(":::++ADD NEW  found:::");
       System.out.println(ele21);
       Actions builder=new Actions(driver);
       Action act=builder.moveToElement(ele11).click(ele21).build();
       act.perform();
       System.out.println("window AFTER ADD NEW ADDRES POPUP:::"+driver.getWindowHandle());

      // System.out.println("New window:::"+driver.getWindowHandle());
       Thread.sleep(5000);
       driver.findElement(By.xpath("//*[@id='fName']")).sendKeys("omi");
       driver.findElement(By.xpath("//*[@id='lName']")).sendKeys("nimj");
       driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("1111111111");
       driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("123 Main St");
       driver.findElement(By.xpath("//*[@id='city']")).sendKeys("long beach");
       driver.findElement(By.xpath("//*[@id='state']")).sendKeys("CA");
       driver.findElement(By.xpath("//*[@id='zipcd']")).sendKeys("94598");
      // Thread.sleep(2000);
       System.out.println("1");
      // driver.findElement(By.xpath("//*[@id='shipping-methods']")).click();
       WebElement element1=  driver.findElement(By.xpath("//*[@id='page-content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/div[2]/form/fieldset/section/div[2]/div"));
       element1.click();
       System.out.println("2");
       //driver.findElement(By.name("Shipping Options")).click();
       System.out.println("3");
       Thread.sleep(5000);
       System.out.println("4");
       WebElement popup=driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[3]/button[2]"));
       popup.click();
                                                                           
       /*
       System.out.println("1main window:::"+driver.getWindowHandle());
       String originalWindow=driver.getWindowHandle();
       Thread.sleep(2000);
       Set<String> window2=driver.getWindowHandles();
       Iterator<String> ite2=window2.iterator();
       int count2=0;
       System.out.println("Total window:::"+window2.size());
       while(ite2.hasNext())
        {
         count2++;
         driver.switchTo().window(ite2.next().toString());
         System.out.println(driver.getTitle());
       	        	WebElement popup1=driver.findElement(By.xpath("//*[@id='simplePopup']/div/form/div[3]/button[2]"));
       	 popup1.click();
       	// driver.close();
       	 driver.switchTo().window(originalWindow);
       //	 driver.close();
        }
       
       

      
       
       System.out.println("window AFTER NO from address validation POPUP:::"+driver.getWindowHandle());
       
       
      
       System.out.println("6");
      WebElement elem1=driver.findElement(By.xpath("//*[@id='page-content']/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/form/fieldset/section/div[2]/div//*[@id='shipping-methods']"));
      
      WebElement shipMehtod=driver.findElement(By.id("shipping-methods"));
      
      
    
      
   
      System.out.println("7");
      Actions builder1=new Actions(driver);
      Action act7=builder1.moveToElement(shipMehtod).doubleClick().build();
      act7.perform();
      
      
      System.out.println("10");
         // Action act2=builder1.moveToElement(shipMehtod).click().build();
      Action act2=builder1.moveToElement(element).click(shipMehtod).build();
      act2.perform();
      
      
       
      
       
       List<WebElement> li=shipMehtod.findElements(By.tagName("option"));
       li.get(2).click();
       */
       /*
           //  System.out.println("9");
      // driver.findElement(By.id("set_as_primary_shipping")).click();
   //    System.out.println("10");
     //  driver.findElement(By.xpath(".//*[@id='page-content']/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/form/fieldset/div[12]/div/label/input[1]")).click();
       driver.findElement(By.xpath("//*[@id='set_as_primary_shipping']")).click();
       //WebElement continueButton= driver.findElement(By.xpath("//*[@id='shippingFormHandlerSubmit']"));
    //   WebElement continueButton= driver.findElement(By.id("shippingFormHandlerSubmit"));
       Thread.sleep(2000);
     //  WebElement cont=driver.findElement(By.xpath("//*[@id='page-content']/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/form/fieldset/div[15]/div"));
       System.out.println("11");
       
       WebElement cont1=driver.findElement(By.xpath("//*[@id='page-content']/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/form/fieldset/div[15]/div//*[@id='shippingFormHandlerSubmit']"));
     
   
     //visible.until(ExpectedConditions.elementToBeClickable(cont1));
  //   wait.until(ExpectedConditions.visibilityOf(cont1));
       //  Action actCont1=builder.moveToElement(cont1).click().build();
      // actCont1.perform();
       cont1.click();
       
       driver.findElement(By.id("security_code")).sendKeys("111");
    //   Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id='existingPaymentMethodForm']/div[3]/fieldset/div[8]/div/input")).click();
       
       System.out.println("12");
                                             */
    //   Action actCont=builder.moveToElement(cont).click(cont1).build();
     //  actCont.perform();
   
       
      
     //  actCont1.perform();
       // continueButton.click();
       
      
      // System.out.println("11");
       
       /*
       int timeout=1;
       do
       {
    	   driver.getWindowHandles();
    	   Thread.sleep(1000);
    	   timeout++;
    	   if(timeout > 10)
    	   {
    		   break;
    	   }
       }
       while(driver.getWindowHandles().size()==1);
       
       Set<String> windows=driver.getWindowHandles();
       for( String str:windows)
       {
    	  if(!str.equals(originalW))
    	  {
    		  driver.switchTo().frame(str);
    	  }
       }
      
       
       */
   
     
     //  Set<String> popIte=driver.getWindowHandles();
      // driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    //   System.out.println(driver.getWindowHandles().size());
	/*
       Iterator<String> popIte=(Iterator<String>) driver.getWindowHandles();
	
       while(popIte.hasNext())
       {
    	   String next=popIte.next();
    	   driver.switchTo().window(next);
       }
       */
       /*
       for(String pop:driver.getWindowHandles())
       {
    	   System.out.println("POP UP TITLE::"+pop);
    	   if(!pop.equals(originalW))
    	   driver.switchTo().window(pop);
       }
        */
        
      //  driver.switchTo().alert();
      //  driver.findElement(By.xpath("//*[@id='account-addresses-return-visitor']/div[3]/div[1]/button")).click();
       // driver.findElement(By.linkText("BUY NOW")).click();
        
		//driver.findElement(By.linkText("Welcome onkar")).click();
		//driver.findElement(By.linkText("Sign out")).click();
	//	String url=driver.getCurrentUrl();

	}

}
