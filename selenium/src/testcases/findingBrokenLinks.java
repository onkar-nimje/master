package testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class findingBrokenLinks {
    //import 2 jar files mail.jar and activation.jar
	static String url = "";
	static HttpURLConnection hu;
	static int response;
	public static void findAllLinks(WebDriver driver) throws MalformedURLException, IOException{
	 
		try {
			List<WebElement> ele1 = driver.findElements(By.tagName("a"));
			System.out.println("Size of list1:"+ele1.size());
			List<WebElement> ele2 = driver.findElements(By.tagName("img"));
			System.out.println("Size of list1:"+ele2.size());
			
			for (int i = 0; i < ele1.size(); i++) {
				url = ele1.get(i).getAttribute("href");
				if (url.isEmpty()||url==null) {
					System.out.println(ele1.get(i)+" is EMPTY");
				}
				else {
					hu = (HttpURLConnection) new URL(url).openConnection();
					hu.connect();
					response = hu.getResponseCode();
					if (response >=400) {
						System.out.println(url+" is BROKEN");
					}
					else {
						System.out.println(url+" is CORRECT");
					}
				}
			}
			
		} catch (Exception e) {
			
		}
		
		
		
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		WebDriver driver= new FirefoxDriver();
		driver.get("https://www.google.com");
		findAllLinks(driver);

	}

}
