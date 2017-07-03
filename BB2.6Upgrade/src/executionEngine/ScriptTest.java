package executionEngine;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScriptTest {

	/**
	 * @param args
	 */
	public static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver= new FirefoxDriver();
		driver.get("http://www-ultimateresetuat.beachbody.com/shop/us/b/ultimate-reset?locale=es_US");
		driver.navigate().refresh();
		String mainWindow = driver.getWindowHandle();
		Set windows = driver.getWindowHandles();
		System.out.println("main window:"+mainWindow);
		System.out.println(windows.size());
		//windows.remove(mainWindow);
		String nextWnidow = (String) windows.iterator().next();
		//driver.switchTo().window(nextWnidow);
		//*[@id='simplePopup']/div/div[3]/a[1]
		//driver.findElement(By.id("simplePopup")).findElement(By.className("button radius secondary borderless")).click();
		driver.findElement(By.xpath("//*[@id='simplePopup']/div/div[3]/a[1]")).click();
		
		
}
}
