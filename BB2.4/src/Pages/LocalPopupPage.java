package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocalPopupPage {

	 WebDriver driver;
	public LocalPopupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public static void acceptlocalPopup(WebDriver driver, String local) throws InterruptedException {
		if (local.equalsIgnoreCase("es_US")) {
			driver.navigate().refresh();
		}		
		if (ExpectedConditions.alertIsPresent()!= null) {
			//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='simplePopup']/div/div[3]/a[1]"))));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='simplePopup']/div/div[3]/a[1]")).click();
		}
	}

}
