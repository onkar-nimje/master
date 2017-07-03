package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	//public static WebDriver driver = null;
	public static void waitForElement(WebDriver driver,WebElement element){
		long startTime = System.currentTimeMillis();
		 System.out.println("start of wait at time:::"+startTime);
		 WebDriverWait wait = new WebDriverWait(driver, 120);
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	    // wait.until(ExpectedConditions.elementToBeSelected(element));
	     long endTime = System.currentTimeMillis();
	     System.out.println("end of wait at time:::"+endTime);
	     System.out.println("Total time wait:::"+(endTime - startTime));
	     
	 	}
}
