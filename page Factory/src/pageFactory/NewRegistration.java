package pageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NewRegistration {

	
		private WebDriver driver;
		
		public NewRegistration(WebDriver driver)
		{
			this.driver=driver;
		}
		//HERE WE IMPLEMENT THE PAGE FACTORY PATTER. 
		//WE SAVED ALL REPEATED KEYWORD IN WEBELEMENT VARIABLE
		
		@CacheLookup
		@FindBy(id= "email")
		WebElement name;
		
		@CacheLookup
		@FindBy(id= "pass")
		WebElement enterpassword;
		
		@CacheLookup
		@FindBy(id= "loginbutton")
		WebElement submit;
		
		
		
		public NewRegistration enterUserName(String userName)
		{
			name.sendKeys(userName);
			return this;
			
		}
		
		public NewRegistration enterPassword(String password)
		{
			enterpassword.sendKeys(password);
			return this;
		}
		
		public AccountLoginPage submit()
		{
			submit.click();
			//return PageFactory.initElements(driver, AccountLoginPage.class);
			return new AccountLoginPage(driver);
		}
		
		public AccountLoginPage login(RegistrationData data)
		{
			return enterUserName(data.getUsername()).enterPassword(data.getPassword()).submit();
		}
	

}
