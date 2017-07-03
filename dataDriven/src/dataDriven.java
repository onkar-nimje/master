import java.io.FileInputStream;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class dataDriven{

	public static void main(String[] args) throws Exception {
		
		//Hierarchy for excel file
		// Excel Application > Workbook > sheet > cell (data)
		FileInputStream file=new FileInputStream("C:\\Documents and Settings\\omkar.nimje\\Desktop\\selenium\\selenium\\omkar\\data.xls");//to locate where is excel file
		Workbook wb=Workbook.getWorkbook(file);
		Sheet sh=wb.getSheet(0);
		//Sheet sh=wb.getSheet("Sheet1");// xls sheets name are in array format. so first sheet is at 0 position. we can give direct name also
		//int k=sh.getRows();  // to get number of rows
		
		for(int i=1; i<sh.getRows();i++)  //sh.getRows()  how much number of time we need to run loop
		{
			int j=0;//j=column i=row
			String id=sh.getCell(j,i).getContents();//id is always at 1st column hence j
			String password=sh.getCell(j+1,i).getContents();//password is always at 2nd column hence j+1
		
		
		WebDriver driver=new FirefoxDriver();
		driver.get("http://127.0.0.1:8080/apex/f?p=4550:11:2860097841756195::NO:::");
		driver.findElement(By.id("P11_USERNAME")).sendKeys(id);
		driver.findElement(By.name("p_t02")).sendKeys(password);
		driver.findElement(By.xpath("//html/body/form/div[6]/table/tbody/tr/td[1]/table/tbody/tr[2]/td/table[2]/tbody/tr/td/input")).click();
		driver.findElement(By.xpath("//*[@id='wwvFlowForm']/a[4]")).click();
		driver.close();
		
		}

	}

}
