package testcases;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DropDownSelection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://code.google.com/p/selenium");
		driver.findElement(By.linkText("Issues")).click();
		
		WebElement root = driver.findElement(By.id("can"));
		List<WebElement> element=root.findElements(By.tagName("option"));
		System.out.println(element.size());
		
		//Iterator<WebElement> it=element.iterator();
		for(int i=0; i<element.size();i++)
		{
			String st=element.get(i).getText();
			System.out.println(st);
		}
		
		Select eleme=new Select(root);
		eleme.selectByIndex(3);
		/*
		Actions builder= new Actions(driver);
		
		//Action act=builder.moveToElement(element.get(3)).click().build();
		Action act=builder.moveToElement(element.get(4)).click().build();
		act.perform();
		System.out.println(element.get(4).getText());
*/
	}

}
