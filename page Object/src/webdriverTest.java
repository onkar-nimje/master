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
