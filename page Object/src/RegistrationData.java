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
	
	@DataProvider(name= "dataLogin1")
	public static Object[][] getLogin1()
	{
		return new Object[][]{
				{"omnimje1@gmail.com","jawaharnagar1"},
				{"omnimje2@gmail.com","jawaharnagar"}
				
		};
	}
	
	@DataProvider(name= "dataLogin2")
	public static Object[][] getLogin2()
	{
		RegistrationData regi1=new RegistrationData();
		regi1.setUsername("onkar.nimje1@objectedge.com");
		regi1.setPassword("jawaharnar");
		
		RegistrationData regi2=new RegistrationData();
		regi2.setUsername("onkar.nimje2@objectedge.com");
		regi2.setPassword("jawaharnar");
		
		
		return new RegistrationData[][]{
				{regi1},
				{regi2}
		};
	}

}
