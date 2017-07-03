package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Utils {

	public static WebDriver driver= null;
	 public static WebDriver openBrowser(int iTestCaseRow) throws Exception
	 {
		 String BrowserName;
		 try 
		 {			
			BrowserName=ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
			if (BrowserName.equals("GoogleChrome"))
			{
				System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
				driver=new ChromeDriver();
				Log.info("New driver instantiated");
				 
	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	            Log.info("Implicit wait applied on the driver for 10 seconds");
	 
	            driver.get(Constant.URL);
	            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	       	 
	            Log.info("Implicit wait applied on the driver for 30 seconds");
	            Log.info("Web application launched successfully");
			}
		 }
		 catch (Exception e) 
		 {
			 Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		 }
		 
		 return driver;
	 }
	 
	 // it will return the test case name
	 public static String getTestCaseName(String sTestCase)throws Exception
	 {

		String value = sTestCase;
		try
		 {
			 int posi=value.indexOf("@");
			 value=value.substring(0, posi);
			 posi=value.lastIndexOf(".");
			 value=value.substring(posi+1);
			 return value;
		 }
		catch (Exception e)
		{
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw(e);
		}
		
	 }
	 
	
		
	 
}
