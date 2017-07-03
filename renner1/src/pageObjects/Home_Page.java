package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;
import utility.Utils;

public class Home_Page extends BaseClass {
    private static WebElement element= null;
	
    //Constructor
    public Home_Page(WebDriver driver) 
    {
    	super(driver);
    }
    
    
	public static WebElement lnk_MyAccountLogin()
	{
		
		try {
			element=driver.findElement(By.linkText("Entre ou cadastre-se"));
			Log.info("My Account link is found on the Home Page");		
		} catch (Exception e) {
			Log.error("My Acocunt link is not found on the Home Page");
			throw(e);
		}
		return element;
		
	}
	
	public static WebElement SubProductLancamentos()
	{
		
		try {
			Utils.waitForElement(driver.findElement(By.xpath("//*[@id='dynamicSidebar']/div[1]/ul/li[2]/label/p")));
			element=driver.findElement(By.xpath("//*[@id='dynamicSidebar']/div[1]/ul/li[2]/label/p"));
			Log.info("Masculino link is found on the Home Page");		
		} catch (Exception e) {
			Log.error("Masculino link is not found on the Home Page");
			throw(e);
		}
		return element;
		
	}
	
	public static WebElement SubProductInfantil()
	{
		
		try {
			element=driver.findElement(By.xpath("//*[@id='sidebarDepto']/div[1]/ul/li[2]/label/p"));
			Log.info("Menina - 1 a 4 anos link is found on the Home Page");		
		} catch (Exception e) {
			Log.error("Menina - 1 a 4 anos link is not found on the Home Page");
			throw(e);
		}
		return element;
		
	}
	
	
	public static class TopNavigation{
		static WebElement mainElement;
		
		
		public static void SignOut()
		{
			System.out.println("2");
			mainElement=driver.findElement(By.xpath("//*[@id='header']/div[1]/div/ul/li[6]/p"));
			Log.info("my account link is found");
			System.out.println("3");
			Utils.mouseHoverAction(mainElement,"Sair");
		}
		
		public static void moveOnProductImage()
		{
			try {
				mainElement=driver.findElement(By.xpath("//*[@id='534500835ProductDiv']"));
				Log.info("Product Image found on PLP");
				Utils.mouseHoverAction(mainElement,"Mais Detalhes");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		/*
		public static void Lancamentos()
		{
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("searching lancamentos xpath");
		//	mainElement= driver.findElement(By.xpath("//*[@id='mrkItm10003']"));
		//	mainElement= driver.findElement(By.id("mrkItm10003"));
			mainElement=driver.findElement(By.xpath("//*[@id='dynamicSidebar']/div[1]/ul/li[2]/label/p"));
			Log.info("Lancamentos category link is found under Top Navigation");
			Utils.mouseHoverAction(mainElement,"masculino");
		}
		
		public static void Infantil()
		{
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			mainElement=driver.findElement(By.xpath("//*[@id='mrkItm10002']"));
			Log.info("Infantil category link is found under Top Navigation");
			Utils.mouseHoverAction(mainElement,"Menina 1 a 4 anos");
		}
		*/
		
	}
}
