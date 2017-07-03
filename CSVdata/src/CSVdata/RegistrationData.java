package CSVdata;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;


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
		regi1.setPassword("jawaharnagar");
		
		RegistrationData regi2=new RegistrationData();
		regi2.setUsername("onkar.nimje2@objectedge.com");
		regi2.setPassword("jawaharnagar");
		
		
		return new RegistrationData[][]{
				{regi1},
				{regi2}
		};
	}
	
	
	@DataProvider(name="dataCSV")
	public static Object[][] getLogin3() throws IOException
	{
		
	// CSVReader csvReader=new CSVReader(new FileReader(RegistrationData.class.getResource("data.csv").getPath()));
		CSVReader csvReader=new CSVReader(new FileReader("data.csv"));
		List<String[]>dataList=csvReader.readAll();	
		Object[][] data=new Object[dataList.size()][1];
		List<RegistrationData> regList=new ArrayList<RegistrationData>();
		for(String[] strArray:dataList)
		{
			RegistrationData regi=new RegistrationData();			
			regi.setUsername(strArray[0].trim());
			regi.setPassword(strArray[1].trim());
			regList.add(regi);
		}	
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				data[i][j]=regList.get(i);
			}
		}
				
		
		csvReader.close();
		return data;
		
	}

}
