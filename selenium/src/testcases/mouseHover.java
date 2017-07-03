package testcases;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;




public class mouseHover {

	static WebDriver driver;
	
	
	
	@Test
	public  void test1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 10);
	//driver.get("http://www.lojasrenner.com.br/");
		driver.get("http://www.lojasrenner.com.br/lista/lancamentos/masculino");
	//driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	
	try {
		//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='mrkItm10003']")));
	
	//	Actions builder=new Actions(driver);
	//	WebElement ele1=driver.findElement(By.xpath("//*[@id='mrkItm10003']"));
		//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(30000);
		
		WebElement ele1=driver.findElement(By.className("productShelfCont"));
		WebElement ele2=ele1.findElement(By.tagName("ul"));
		List<WebElement> ele3=ele2.findElements(By.tagName("li"));
		Actions builder=new Actions(driver);
		Action action=builder.moveToElement(ele3.get(0)).click().build();
		action.perform();
		//WebElement ele2=ele1.findElement(By.tagName("ul"));
		
		/*
		WebElement color=driver.findElement(By.xpath("//*[@id='color534374269']/img"));
		((JavascriptExecutor)driver).executeScript("document.getElementById('color534374269').click();");
		System.out.println(color.getText());
		System.out.println("color clicked");
		//color.click();
		
		Thread.sleep(5000);
		WebElement li=driver.findElement(By.className("skuSizeList"));
		List<WebElement> list=li.findElements(By.tagName("label"));
		System.out.println(list.get(1).getText());
		System.out.println(list.size());
		Actions builder=new Actions(driver);
		Action action=builder.moveToElement(list.get(0)).click().build();
		action.perform();
				
		for(WebElement i:list)
		{
			String ele=i.getText();
			System.out.println(ele);
		}
		
		//Thread.sleep(10000);
		//((JavascriptExecutor)driver).executeScript("document.getElementsByName('P').click();");
		//WebElement size=driver.findElement(By.xpath("//*[@id='skuBoxSel']/label[4]/span"));
		//((JavascriptExecutor)driver).executeScript("document.getElementByXPath(\"//*[@id='skuBoxSel']/label[3]/span\").click();");
		
		Thread.sleep(10000);
		WebElement add=driver.findElement(By.id("linkAddToCart"));
		System.out.println(add.getText());
		add.click();
		System.out.println("clicked add to cart");
		
		Thread.sleep(10000);
		//((JavascriptExecutor)driver).executeScript("document.getElementsByName('P').click");
		//WebElement size=driver.findElement(By.xpath("//*[@id='skuBoxSel']/label[4]/span"));
		//size.click();
		System.out.println("size clicked");
		
	//	WebElement ele2=driver.findElement(By.xpath("//*[@id='skuBoxSel']/label[2]/span"));
	//	WebElement ele3=driver.findElement(By.xpath("//*[@id='skuBoxSel']"));
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//	Actions builder=new Actions(driver);
		//Action act=builder.moveToElement(ele2)
	//	builder.moveToElement(ele3).perform();
		
		//Action act=builder.moveToElement(ele1).
		//builder.moveToElement(ele1).perform();
	//	System.out.println("mouse moved to element");
	//((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10003').click();");
	//	ele2.click();
	//ele1.click();
	//	((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10003').hover();");
	//System.out.println("javascript executed");
		
	//	wait.until(ExpectedConditions.elementToBeClickable(By.id("mrkItm10003")));
		
	//	builder.moveToElement(ele1).click().perform();
	//	System.out.println("cliked on category");
	*/	
		
	} catch (Exception e) {
		throw(e);
	}
	
	//Actions builder=new Actions(driver);
	//WebElement ele1=driver.findElement(By.xpath("//*[@id='mrkItm10003']"));
	//WebElement ele1=driver.findElement(By.linkText("Lançamentos"));
	
	//WebElement ele1=driver.findElement(By.id("mrkItm10004"));
	//builder.moveToElement(ele1).perform();
	
	//WebDriverWait wait = new WebDriverWait(driver, 50);
	//WebElement ele2=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("mrkItm10004"))));
//	builder.moveToElement(ele2).click().perform();
	/*
	System.out.println("mouse move to main");
	WebElement ele2=driver.findElement(By.xpath("//*[@id='mrkItm10003SubMenu']/div/ul[1]/a[2]"));
	builder.moveToElement(ele2).click().perform();
	*/
	
	//System.out.println(ele1.getText());
	
	
			
//	builder.click().perform();
	//ele1.click();
	

	
	}



	
	
	
}
