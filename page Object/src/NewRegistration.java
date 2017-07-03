import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NewRegistration {

	
		private WebDriver driver;
		
		public NewRegistration(WebDriver driver)
		{
			this.driver=driver;
		}
		
		public NewRegistration enterUserName(String userName)
		{
			driver.findElement(By.id("email")).sendKeys(userName);
			return this;
			
		}
		
		public NewRegistration enterPassword(String password)
		{
			driver.findElement(By.id("pass")).sendKeys(password);
			return this;
		}
		
		public AccountLoginPage submit()
		{
			driver.findElement(By.id("loginbutton")).click();
			return new AccountLoginPage(driver);
		}
		
		public AccountLoginPage login(RegistrationData data)
		{
			return enterUserName(data.getUsername()).enterPassword(data.getPassword()).submit();
		}
	

}
