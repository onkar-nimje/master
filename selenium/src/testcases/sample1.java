package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sample1 {
	
	
	

	
	@Test
	public void openGoogle() throws InterruptedException
	{
	 ProfilesIni profile=new ProfilesIni();
	 FirefoxProfile selenium=profile.getProfile("selenium");
      WebDriver driver= new FirefoxDriver();
      driver.get("https://www.gmail.com");
      WebElement webEmail= driver.findElement(By.id("Email"));
      webEmail.sendKeys("omnimje@gmail.com");
      WebElement webPass= driver.findElement(By.id("Passwd"));
      webPass.sendKeys("jawaharnagar3");
      //Thread.sleep(15000);
     driver.findElement(By.xpath("//*[@id='signIn']")).click();
     
     System.out.println("inserted");
	}
	
	
	@Test
	public void testFirsy()
	{
		System.out.println("hello test");
	}
	
	
	@Test(dependsOnMethods={"testFirsy"})
	public void test1()
	{
		System.out.println("hello test1");
	}
	
	@Test(dependsOnMethods={"testFirsy","test1"})
	public void test2()
	{
		System.out.println("hello test2");
	}

	@Test
	public void login()
	{
		String a="omkar";
		String b="omkar";
		Assert.assertEquals(a, b);
	}
	
	
	
	
}
