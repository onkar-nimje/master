package pageObjects;

import org.openqa.selenium.WebDriver;

public class BaseClass {
	public static WebDriver driver;
	public static boolean bResult;
	public BaseClass(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		BaseClass.driver=driver;
		BaseClass.bResult=true;
	}

}
