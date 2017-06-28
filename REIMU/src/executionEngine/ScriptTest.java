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
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		driver= new FirefoxDriver();
		driver.get("http://qamediacom.objectedge.com/admin/#/login?_k=3nm1d6");
		driver.findElement(By.xpath("//*[@id='sign-up-btn']")).click();
		driver.findElement(By.id("first-name-input")).sendKeys("hhhh");
		driver.findElement(By.id("last-name-input")).sendKeys("hhdddddhh");
		driver.findElement(By.id("email-input")).sendKeys("546456456fdd@yopmail.com");
		driver.findElement(By.id("password-input")).sendKeys("Abcd123!");
		driver.findElement(By.id("confirm-password-input")).sendKeys("Abcd123!");
		//driver.findElement(By.xpath("//*[@id='page-wrapper']/div/div/div[2]/div/div/div[2]/div/div[6]/label/input")).click();
		//driver.findElement(By.id("org-id-input")).sendKeys("21312312sdfsdfsdf");
		driver.findElement(By.id("org-name-input")).sendKeys("sdfsdfsdfsdf");
		driver.findElement(By.id("org-phone-input")).sendKeys("111111111123");
		driver.findElement(By.id("org-url-input")).sendKeys("sdfsdfsdfsdf.com");
		driver.findElement(By.xpath("//*[@id='sign-up-btn']")).click();
		Thread.sleep(5000);
		String source = driver.getPageSource();
		if (source.contains("User Successfully created!")) {
			System.out.println("Text found1");
		}
		if (source.contains("Activate account by clicking on the link in your email.")) {
			System.out.println("Text found1");
		}
        
		System.out.println(driver.findElement(By.className("signup-message")).getText());
		
		
}
}
