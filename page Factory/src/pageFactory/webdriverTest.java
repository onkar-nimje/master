package pageFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class webdriverTest {

	private WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		//driver=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.facebook.com");
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		//if(result.isSuccess())
		//{
			File imageFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String failedImage=result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".jpg";
			File failureImageFile=new File(failedImage);
			FileUtils.moveFile(imageFile, failureImageFile);
			
			driver.close();
			driver.quit();
		//}
	}
	
	
	
	@Test
	public void test1()
	{
		Registration register=new Registration(driver);
		register.login();
	}
	
	@Test(dataProvider = "dataProvide", dataProviderClass=RegistrationData.class)
	public void login1(String username, String password)
	{
		RegistrationData registrationdata=new RegistrationData();
		registrationdata.setUsername(username);
		registrationdata.setPassword(password);
		
		NewRegistration newregistration=PageFactory.initElements(driver,NewRegistration.class);
		newregistration.login(registrationdata);
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
