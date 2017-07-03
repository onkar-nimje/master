
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.SystemClock;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

public class Log {
	
	static Calendar cal = Calendar.getInstance();
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
	// Initialize Log4j logs
	     private static Logger Log = Logger.getLogger(Log.class.getName()); 
	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String sTestCaseName){
	    Log.info("****************************************************************************************");
	    Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	    Log.info("                                      Started at "+sdf.format(cal.getTime())+"                               ");
	    Log.info("                                      Started at "+System.currentTimeMillis()+"                               ");
	    Log.info("****************************************************************************************");   
	    }
	 
	    //This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName){
	    Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
	    Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+sdf.format(cal.getTime())+"             XXXXXXXXXXXXXXXXXXXXXXX");
	    Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+System.currentTimeMillis()+"             XXXXXXXXXXXXXXXXXXXXXXX");
	   	    }
	 
	    // Need to create these methods, so that they can be called  
	public static void info(String message) {
	        Log.info(message);
	        }
	public static void warn(String message) {
	    Log.warn(message);
	    }
	public static void error(String message) {
	    Log.error(message);
	    }
	public static void fatal(String message) {
	    Log.fatal(message);
	    }
	public static void debug(String message) {
	    Log.debug(message);
	    }
	}