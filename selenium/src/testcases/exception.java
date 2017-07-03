package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class exception {

	@Test
	public void T1()
	{
		String t1="omkar";
		String t2="omkar2";
		Assert.assertEquals(t1, t2);
		System.out.println("T1");
	}
	
}
