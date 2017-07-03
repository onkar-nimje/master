package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class robotClass {

	/**
	 * @param args
	 */
	public static WebDriver driver;
	public static void main(String[] args) throws AWTException {
		
		driver=new FirefoxDriver();
		driver.get("http://spreadsheetpage.com/index.php/file/C35/P10/");
        driver.findElement(By.linkText("custmfmt.xls")).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.keyPress(KeyEvent.VK_TAB);
        
	}

}
