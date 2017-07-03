package assertionProg;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class webdriverTest {

	private WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	    driver=new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.get("https://www.facebook.com");
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	// HERE ITSELF WE CHECK THE CONTENT AND IF 1 ASSERTION FAIL THEN THE TEST WILL NOT EXCUTE FURTHER. 
	//IT STOP THEIR WITH ERROR 
	public void hardAssertion()
	{
		Assert.assertTrue("id field is missing", driver.findElements(By.id("email")).size()==1);
		Assert.assertTrue("password field is missing",driver.findElements(By.id("pas")).size()==1);
		Assert.assertTrue("button is not present",driver.findElements(By.id("loginbutton")).size()==1);
	}
	
	@Test
	//HERE WE HANDLE THE ASSERTION IN OUR OWN STYLE CODE LIKE TRY AND CATCH BLOCK. IF FAIL THEN ERROR MSG WILL SAVE
	//IN  VARIABLE AND TESTING CONTINUE GOES TO NEXT ASSETION METHODS.
	public void softAssertion()
	{
	    Customverification verification=new Customverification();
	    
	    verification.verityTrue("id field is missing", driver.findElements(By.id("email")).size()==1);
	    verification.verityTrue("password field is missing",driver.findElements(By.id("pas")).size()==1);
	    verification.verityTrue("button is not present",driver.findElements(By.id("loginbutto")).size()==1);
	    
	    verification.checkForVerificationErrors();
	}
	
	
	
	@Test
	public void test1()
	{
		Registration register=new Registration(driver);
		register.login();
	}
	/*
	@Test
	public void assertion()
	{
		
		assertionClass asser=new assertionClass();
		asser.verifyTrue("field is missing", driver.findElements(By.id("email")).size()==1);
	}
	*/
	
	@Test(dataProvider = "dataLogin1", dataProviderClass=RegistrationData.class)
	public void login1(String username, String password)
	{
		RegistrationData registrationdata1=new RegistrationData();
		registrationdata1.setUsername(username);
		registrationdata1.setPassword(password);
		
		NewRegistration newregistration=new NewRegistration(driver);
		newregistration.login(registrationdata1);
	}
	
	@Test(dataProvider = "dataLogin2", dataProviderClass=RegistrationData.class)
	public void login2 (RegistrationData registrationdata2)
	{
		
		NewRegistration newregistration=new NewRegistration(driver);
		newregistration.login(registrationdata2);
	}
	
	/*
	@Test
	public void javascript()
	{
		driver.get("http://newtours.demoaut.com/mercuryregister.php");
		((JavascriptExecutor)driver).executeScript();
	}
	*/
}
