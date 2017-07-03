package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {
	
	@Test(dataProvider="getData")
	public void test1(String u , String p)
	{
		System.out.println("User: "+u+"  Password: "+p);
	}
	
	@DataProvider
	 public Object [][] getData()
	{
		Object [][] data= new Object[3][2];
		data[0][0]="u1";
		data[0][1]="p1";
		
		data[1][0]="u2";
		data[1][1]="p2";
		
		data[2][0]="u3";
		data[2][1]="p3";
		return data;
	}

	
}
