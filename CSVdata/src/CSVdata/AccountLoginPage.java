package CSVdata;
import org.openqa.selenium.WebDriver;


public class AccountLoginPage {

	private WebDriver driver;
	
	public AccountLoginPage(WebDriver driver) 
	{
		this.setDriver(driver);
		System.out.println("success");
		
		
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

}
