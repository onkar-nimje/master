package testcases;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by onkar.nimje on 5/16/2014.
 */
public class testExample1 {

    public static WebDriver driver;
    @Before
    public void setUp()
    {
       System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
        driver=new ChromeDriver();
        //driver= new FirefoxDriver();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testExamples()
    {
    	System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
        driver=new ChromeDriver();
       driver.get("http://www.google.com");
      //  driver.findElements(By.id("verified"));
       // System.out.println("omkar");
    }

}
