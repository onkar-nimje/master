package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	//public static WebDriver driver = null;
	
	public static void waitForElement(WebDriver driver,WebElement element){
		long startTime = System.currentTimeMillis();
		// System.out.println("start of wait at time:::"+startTime);
		 WebDriverWait wait = new WebDriverWait(driver, 120);
		 wait.until(ExpectedConditions.elementToBeClickable(element));
		long endTime = System.currentTimeMillis();
	   //  System.out.println("end of wait at time:::"+endTime);
	     System.out.println("Total time wait:::"+(endTime - startTime));
	     
	 	}
	
	public static void waitForElementCondition(WebDriver driver,WebElement element, String condition){
		long startTime = System.currentTimeMillis();
		 System.out.println("start of wait at time:::"+startTime);
		 WebDriverWait wait = new WebDriverWait(driver, 120);
		 if(condition.equalsIgnoreCase("click"))
		 wait.until(ExpectedConditions.elementToBeClickable(element));
		 else
			 if(condition.equalsIgnoreCase("visible"))
			 wait.until(ExpectedConditions.visibilityOf(element));
		     else
		    	 if(condition.equalsIgnoreCase("select"))
		    	 wait.until(ExpectedConditions.elementToBeSelected(element));
		    	 else
		    	 {
		    		 System.out.println("condition:::"+condition);
		    		 wait.equals(element.getText().contains(condition));
		    	 }
		    		 
		 
			 
	     long endTime = System.currentTimeMillis();
	     System.out.println("end of wait at time:::"+endTime);
	     System.out.println("Total time wait:::"+(endTime - startTime));
	     
	 	}

}
