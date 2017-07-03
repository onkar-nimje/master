package appModules;

import org.testng.Reporter;

import pageObjects.Home_Page;
import utility.Log;

public class SignOut_Action {
	public static void Execute(int TestRowNumber) throws Exception
	{
		Home_Page.TopNavigation.SignOut();
		Log.info("Click action is performed on Submit button");
		
		Reporter.log("SignIn Action is done successfully");
	}

}
