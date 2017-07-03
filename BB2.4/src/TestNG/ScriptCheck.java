package TestNG;

import java.sql.Time;
import java.util.Date;

import org.apache.poi.hslf.util.SystemTimeUtils;
import org.openqa.selenium.support.ui.SystemClock;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

public class ScriptCheck {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//double f1=1234567890123,f2,f3;
		// TODO Auto-generated method stub
		//String currentTime1="test"+(System.currentTimeMillis()/10000000);
		String currentTime1="test"+(System.currentTimeMillis());
		//String currentTime1="test"+(1234567890/System.currentTimeMillis());
		//String currentDate="date"+Date.UTC(year, month, date, hrs, min, sec);
		System.out.println(currentTime1);
		String currentTime2="test"+(987654321/System.currentTimeMillis());
		//String currentDate="date"+Date.UTC(year, month, date, hrs, min, sec);
		System.out.println(currentTime2);
		String currentTime3="test"+(567891234/System.currentTimeMillis());
		//String currentDate="date"+Date.UTC(year, month, date, hrs, min, sec);
		System.out.println(currentTime3);
		//String rever=currentTime.re
	}

}
