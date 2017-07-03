import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class test2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver;
		//driver=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.netshoes.com.br/account/login.jsp?");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("invalidpftest@yahoo.com");
	    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	   // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    WebElement link=driver.findElement(By.linkText("pessoa jurí­dica"));
	   // driver.findElement(By.cssSelector("box-tab>li>a")).click();
	   // driver.findElement(By.linkText("pessoa jurí­dica")).click();
	  // WebDriverWait wait=new WebDriverWait(driver, 30);
	//   wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.linkText("pessoa jurí­dica")), true));
	 //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("pessoa jurí­dica")));
	  // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[2]"));
	//	System.out.println("link text:"+link.getText());
		link.click();
	   System.out.println("wait finish"); 
	 //   WebElement link3=driver.findElement(By.xpath("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[2]/a"));
	//	System.out.println("1st:"+link3.getText());
	//	link3.click();
	    new Select(driver.findElement(By.id("dayBrithday"))).selectByVisibleText("29");
	    WebElement el1=driver.findElement(By.id("dayBrithday"));
	    Select sel=new Select(el1);
	   // sel.selectByVisibleText("29");
	//	System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
	//	driver=new ChromeDriver();
		/*
		driver.get("https://www.netshoes.com.br/account/register.jsp?target=null&emailId=08.181.5618187-18@test.com");
		Thread.sleep(10000);
		WebElement link2=driver.findElement(By.xpath("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[1]/a"));
		Actions builder=new Actions(driver);
		Action act=builder.moveToElement(link2).build();
		act.perform();
		System.out.println("1st:"+link2.getText());
		WebElement link3=driver.findElement(By.xpath("html/body/div[2]/div/div[2]/form/div/div[1]/ul/li[2]/a"));
		System.out.println("1st:"+link3.getText());
		link3.click();
	Thread.sleep(5000);
		WebElement link=driver.findElement(By.linkText("pessoa jurí­dica"));
		System.out.println("link text:"+link.getText());
		link.click();
		
		WebElement link1=driver.findElement(By.cssSelector("box-tab>li>a"));
		System.out.println("link CSS:"+link1.getText());
		*/
		/*
driver.get("http://www.netshoes.com.br/");
	
driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//WebElement ele1=driver.findElement(By.cssSelector("span.user-name"));
 WebElement ele1=driver.findElement(By.cssSelector("span.menu-item"));
		Actions builder= new Actions(driver);
		Action act1=builder.moveToElement(ele1).build();
		act1.perform();
		
		//WebElement ele2=driver.findElement(By.cssSelector("li.holder-sub-menu-my-account.firepath-matching-node")); holder-sub-menu-my-account
		WebElement ele2=driver.findElement(By.xpath("html/body/div[3]/div[1]/div[1]/div/ul[2]/li"));
		//WebElement ele2=driver.findElement(By.cssSelector("holder-sub-menu-my-account"));
		 List<WebElement> li=ele2.findElements(By.tagName("li"));
		 Iterator<WebElement> ite=li.iterator();
		 System.out.println(li.size());
		 for (int i = 0; i < li.size(); i++) {
			System.out.println(li.get(i).getText());
						
			//li.get(i).click();
			//li.get(0).sendKeys(Keys.ENTER);
		//	Action act2=builder.moveToElement(li.get(i)).build();
		//	act2.perform();
		//	Thread.sleep(10000);
		//	li.get(0).sendKeys(Keys.ENTER);
		//	li.get(i).sendKeys(Keys.RETURN);
			
		}
		
		*/
	//	 li.get(0).click();
		/*
		driver.findElement(By.linkText("Login")).click();
	//	driver.navigate().refresh();
		driver.get("https://www.netshoes.com.br/account/login.jsp?");
		// driver.findElement(By.id("email-cpf")).clear();
		  //  driver.findElement(By.id("email-cpf")).sendKeys("");
		// driver.findElement(By.id("email-cpf")).click();
		//    driver.findElement(By.id("password")).clear();
		 //   driver.findElement(By.id("password")).sendKeys("");
		//    driver.findElement(By.id("password")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		    //driver.findElement(By.xpath("//button[@type='submit']")).click();
		    WebElement mess1=driver.findElement(By.cssSelector("span.tooltip-up"));
		    System.out.println(mess1.getText());
		    */
	}

}
