package assertionProg;

import org.junit.Assert;
import org.testng.Reporter;
import com.thoughtworks.selenium.SeleneseTestBase;  // import this package for verification method fail(Errors)

public class Customverification extends SeleneseTestBase {

	private StringBuffer verificationErrors;
	public Customverification() {
		verificationErrors=new StringBuffer();
	}
	
	public void verityTrue(String msg, Boolean b)
	{
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
			verificationErrors.append(e);
			Reporter.log(msg+"<br>");
		}
	}
	
	public void clearVerificationError()
	{
		verificationErrors = new StringBuffer();
	}
	
	public void checkForVerificationErrors()
	{
		String Errors=verificationErrors.toString();
		System.out.println(Errors);
		
		clearVerificationError();
		//clear verification error since this method will use by other test also. and it should be new for each test.
		if(!"".equals(Errors))
			fail(Errors);
	}
	
	

}
