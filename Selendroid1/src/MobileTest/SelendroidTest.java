package MobileTest;

import io.selendroid.SelendroidDriver; 
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 

import org.testng.Assert; 
import org.testng.annotations.AfterSuite; 
import org.testng.annotations.BeforeSuite; 
import org.testng.annotations.Test;

public class SelendroidTest {

	   private WebDriver driver ;

	   
	   @BeforeSuite 
	   public void setUp() throws Exception {
	      SelendroidConfiguration config = new SelendroidConfiguration();
	      config.addSupportedApp("resigned-selendroid-test-app-0.17.0.apk");
	      SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
	      selendroidServer.launchSelendroid();
		  
	      SelendroidCapabilities caps = new
	         SelendroidCapabilities("io.selendroid.testapp:0.17.0"); 
	      System.out.println("hello1");
	      driver = new SelendroidDriver(caps);
	      System.out.println("hello2");
	   }
	   
	   @Test 
	   public void selendroidTest() throws Exception {
	      WebElement inputField = driver.findElement(By.id("my_text_field"));
	      Assert.assertEquals("true", inputField.getAttribute("enabled"));
	      inputField.sendKeys("Selendroid");
	      
	      Assert.assertEquals("Selendroid", inputField.getText());
		  
	      WebElement button = driver.findElement(By.id("buttonTest"));
	      button.click();
		  
	      button = driver.findElement(By.id("button2"));
	      button.click();
	      
	      Thread.sleep(5000);
		  
	      button = driver.findElement(By.id("startUserRegistration"));
	      button.click();
	      
	      Thread.sleep(10000);
		  
	      WebElement element = driver.findElement(By.id("label_username")); 
	      
	      String text = element.getText();
	      System.out.println(text);
	      element = driver.findElement(By.id("inputUsername"));
	      element.sendKeys("bob");
	      
	      element = driver.findElement(By.id("inputEmail"));
	      element.sendKeys("test@gmail.com"); 
	      
	      element = driver.findElement(By.id("inputPassword"));
	      element.clear();
	      element.sendKeys("test1233");
	      
	      element = driver.findElement(By.id("inputName"));
	      element.clear(); 
	      element.sendKeys("My Name ");
		  
	      element = driver.findElement(By.id("input_preferedProgrammingLanguage")); 
	      element.click();
	      
	      element = driver.findElement(By.id("text1"));
	      element.click();
		  
	      element = driver.findElement(By.id("input_adds"));
	      element.click();
		  
	      element = driver.findElement(By.id("btnRegisterUser"));
	      element.click();
	      
	      element = driver.findElement(By.id("buttonRegisterUser"));
	      element.click(); 
	   }
	   
	   @AfterSuite 
	   public void tearDown(){ 
	      driver.quit(); 
	   } 
	
	
}
