package pageFactory;
import org.testng.annotations.DataProvider;


public class RegistrationData {
	private String username;
	private String password;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	@DataProvider(name= "dataProvide")
	public static Object[][] getRegistrationData()
	{
		return new Object[][]{
				{"omnimje1@gmail.com","jawaharnagar1"},
				{"omnimje@gmail.com","jawaharnagar"}
				
		};
	}

}
