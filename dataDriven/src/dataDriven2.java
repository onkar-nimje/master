import java.io.FileInputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Sheet;
import jxl.Workbook;


public class dataDriven2 {

	static WebDriver driver;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileInputStream file=new FileInputStream("C:\\Documents and Settings\\omkar.nimje\\Desktop\\selenium\\selenium\\omkar\\data1.xls");
		Workbook wk=Workbook.getWorkbook(file);
		Sheet sh=wk.getSheet(0);
		
		for(int i=1;i<sh.getRows();i++)
		{
			int j=0;
			String u1=sh.getCell(j,i).getContents();
			String u2=sh.getCell(j+1,i).getContents();
			String u3=sh.getCell(j+2,i).getContents();
			String u4=sh.getCell(j+3,i).getContents();
			driver=new FirefoxDriver();
			driver.get(u1+u2+u3+u4);
			driver.quit();
			
			
		}
		

	}

}
