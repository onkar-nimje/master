package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class Test {
public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://www.facebook.com");
	driver.get("");
	driver.close();
	driver.quit();
}
}
