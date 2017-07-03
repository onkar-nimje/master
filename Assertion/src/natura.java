import java.awt.AWTException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class natura {

	WebDriver driver;
	
	@BeforeClass
	public void setup() throws InterruptedException
	{
		
		//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		
	    //driver=new ChromeDriver();
	   
		driver=new FirefoxDriver();
		driver.get("http://naturawl.objectedge.com/");
		Thread.sleep(10000);
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown()
	{
		//driver.quit();
		//driver.close();
	}
	
	@Test
	public void t1() throws InterruptedException, AWTException
	{
		
        driver.findElement(By.xpath("//*[@id='cf-menu-box']/a")).click();   
        System.out.println("om1");
        
       
        
      driver.findElement(By.xpath("//*[@id='header-login']/div/div[1]/div/a[1]")).click();
      System.out.println("om2");
     Thread.sleep(5000);
    
     System.out.println("om3");
    
    
     String main1=driver.getWindowHandle();
    
     Set<String> popup=driver.getWindowHandles();
     
     for(String handles : popup)
     {
    	 
    	 driver.switchTo().window(handles);
    	// driver.manage().window().maximize();
    	 System.out.println(driver.getTitle());
    	 
    	 if(handles.equals(main1))
    	 {
    		 System.out.println("in main window");
    	 }
    	 else
    	 {
    		// System.out.println(driver.getTitle()); 
    	 driver.findElement(By.id("email")).sendKeys("oenatura@gmail.com");
    	 System.out.println("entered email");
    
    	 driver.findElement(By.id("pass")).sendKeys("9224314917");
    	 System.out.println("entered password");
    	 
    	 driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
    	 Thread.sleep(10000);
    	 System.out.println("facebook login Button clicked");
    	 System.out.println(driver.getTitle());
    	// driver.findElement(By.linkText("Fechar janela")).click();
    	 driver.switchTo().window(main1);
    	// Thread.sleep(20000);
    	 System.out.println("omkar1");
    	 
    	 
    	// WebElement radio1=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[2]"));
    	//WebElement radio1=driver.findElement(By.xpath("//*[@id='form-rede-natura']"));
    	// 
    	 /*
    	 WebElement radio1=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[1]"));
    	// WebElement radio1=driver.findElement(By.xpath("//*[@id='radio-has-account']"));
    	 
    	Actions builder1= new Actions(driver);
    	 Action clik1=builder1.moveToElement(radio1).build();
    	 clik1.perform();
    	 */
    	 Thread.sleep(5000);
    	 
    	 System.out.println("ERROR-START");
    	 // for continuar driver.findElement(By.xpath(".//*[@id='popupLayer_modal-rede-natura']/div/div[2]/a[2]")).click();
    	 // for cancel driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[2]/a[1]")).click();
    	 // for radio button    	 driver.findElement(By.xpath("//*[@id='radio-has-account']")).click();
    	// 1st WebElement ele=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[1]/h4"));    	 System.out.println(ele.getText());
    	// 2nd WebElement ele=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[1]/a"));System.out.println(ele.getText());
    	// 3rd WebElement ele=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[1]/div[1]/p[1]")); System.out.println(ele.getText());
    	// 4th WebElement ele=driver.findElement(By.xpath("//*[@id='form-rede-natura']/label[1]"));
    	//4th WebElement ele=driver.findElement(By.xpath("//*[@id='form-rede-natura']/label[1]/span"));    	System.out.println(ele.getText());
    	// 5th WebElement el=driver.findElement(By.xpath("//*[@id='email']"));System.out.println(el.getTagName());
    	//List<WebElement> ele=driver.findElements(By.tagName("label"));
    	 /*
    	 List<WebElement> ele=driver.findElements(By.xpath("//*[@id='form-rede-natura']"));
    	int count=0;
    	System.out.println(ele.size());
    	//Iterator<?> ite=ele.iterator();
    	for(int i=0;i<ele.size();i++)
    	{
    		//if(ele.get(i).getText().equals("Já faço parte da Rede Natura"))
    			if(ele.get(i).getTagName().equals("label"));
    			{
    			count=i;
    		System.out.println(count);
    			break;
    			}
    	}
    	Actions builder=new Actions(driver);
    	Action act=builder.click(ele.get(count)).build();
    	       act.perform();
    	
    	System.out.println(ele.get(count).getText());
    	*/
    	
    	 WebElement ele=driver.findElement(By.xpath("//*[@id='radio-has-account']"));
    	// WebElement ele1=driver.findElement(By.xpath("//*[@id='popupLayer_modal-rede-natura']/div/div[1]"));
    	// WebElement ele=ele1.findElement(By.xpath("//*[@id='radio-has-account']"));
    	
    	 Actions builder=new Actions(driver);
    	 //Action act=builder.moveToElement(ele).click().build();
    	 //Action act=builder.click().click(ele).build();
    	// Action act=builder.moveToElement(ele).click().build();
    	 
    	  Action act=builder.moveByOffset(490,20).doubleClick().build();   
    	        act.perform();
    	        System.out.println(ele.getText());
    	        System.out.println(ele.getLocation());
    
    	    //   driver.findElement(By.xpath("//*[@id='old-password']")).sendKeys("12313");
    	 // driver.findElement(By.xpath("//*[@id='radio-has-account']/span")).click();
    	//  for text field     	 driver.findElement(By.xpath("//*[@id='email']")).sendKeys("helllo");
    	// WebElement el=driver.findElement(By.xpath("//*[@id='email']"));
    	 //System.out.println(el.getTagName());
    	 System.out.println("element found on POPUP");
    	 driver.findElement(By.xpath("//*[@id='header-login']/div/div[1]/div/a[1]")).click();
    	 System.out.println("element found on MAIN");
    	 System.out.println("ERROR-END");
    	 
    	 
    	
    	 
    	 
    	 
    	
    	 }
    	
    	
    	
     }
    
     
        
        
	}

	
}
