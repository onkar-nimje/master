package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Utils {
	public static WebDriver driver = null;
	
	public static WebDriver openBrowser(int iTestCaseRow) throws Exception
	{
		String sBrowserName;
		
		try {
			sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
			
			if(sBrowserName.equals("Mozilla"))
			   { 
	            driver = new FirefoxDriver();
	 
	            Log.info("New driver instantiated");
	 
	            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 
	            Log.info("Implicit wait applied on the driver for 10 seconds");
	 
	            driver.get(Constant.URL);
	            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 
	            Log.info("Web application launched successfully");
	 
	            }
			
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
			throw(e);
		}
		return driver;
	}
	
	public static String getTestCaseName(String sTestCase) throws Exception
	{
		String value = sTestCase;
		try
		{
			int posi= value.indexOf("@");
			value = value.substring(0,posi);
			posi=value.indexOf(".");
			value = value.substring(posi+1);
			return value;
		} 
		catch (Exception e) {
			// TODO: handle exception
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw(e);
		}	
	}
	
	public static int getRowContains(String sTestCaseName, int colNum ) throws Exception
	{
		int i;
		try 
		{
		 int rowCount= ExcelUtils.ExcelWSheet.getLastRowNum();
		 for(i=0;i<rowCount;i++)
		 {
			 if(ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName))
			 {
			 break;
			 }
		 }
		return i;
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw(e);
		}
	//	return i;
	}

}
