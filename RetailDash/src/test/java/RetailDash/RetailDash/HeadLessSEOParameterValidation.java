package RetailDash.RetailDash;
import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import utility.*;
public class HeadLessSEOParameterValidation {
	static WebDriver driver;
	static WebElement centaurSearField;
	static WebElement CentrinoSearField;
	static boolean productFound = false;
	static String color1 = null;
	static String color2 = null;
	static String[] pdpURLs ;
	static String[] prdColors ;
	static String[] searchPageNumberUrl ;
	static WebElement searchResult;
	static WebElement pageNumberTab;
	static List<WebElement> pageList;
	static int count =0;
	static String URL;
	static String title;
	static String description;
	static String h1Tag;
	static String canonicalTag;
	static String canonicalLink;
	static long startTime;
	static long programStartTime;
	static String Pass;
	static String Fail;
	public static final String Path_FileName = "D://workspaces//Selenuim//RetailDash//src//test//java//dataEngine//SEOParameterValidation-prod.xls";                                           
	public static void main(String[] args) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		ExcelUtils.setExelFile(Path_FileName);
		int totalRow = ExcelUtils.getRowcount("Sheet1");
		//driver=new FirefoxDriver();  //FOR FIREFOX BROWSER
		//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");  //FOR CHROME DRIVER
		//driver=new ChromeDriver(); //FOR CHROME BROWSER
		driver = new HtmlUnitDriver();  //FOR HEADLESS BROWSER
	    programStartTime = System.currentTimeMillis();
		System.out.println("TEST START at :"+programStartTime);		
		System.out.println("TOTAL row count need to check from EXCEL SHEET::"+totalRow);
		
		Pass="BLACK";
		Fail="RED";
		
		try {
			for(int i=1;i<totalRow;i++) {
				URL = ExcelUtils.getCellStringData(i, 1, "Sheet1");
				System.out.println("Current Row Number: "+(i+1)+" URL::"+URL);
				startTime = System.currentTimeMillis();
				System.out.println("Start time::"+startTime);
				driver.get(URL);
				System.out.println("End of page load wait:::"+System.currentTimeMillis());
				System.out.println("TOTAL WAITING TIME IN SECONDS TO LOAD URL FROM ROW NUMBER ("+(i+1)+"):: "+((System.currentTimeMillis()-startTime)/1000));
                //Thread.sleep(5000);
				
				//  TITLE
				try {
					title = driver.findElement(By.name("title")).getAttribute("content"); //title
					//if (title!=null) {
					if (title.length()!=0) {
						ExcelUtils.setCellData(title, i, 2, "Sheet1", Path_FileName,Pass);
					} else {
						ExcelUtils.setCellData("NA", i, 2, "Sheet1", Path_FileName, Fail);
					}				
					System.out.println("Title::: "+title);
				} catch (Exception e) {
					ExcelUtils.setCellData("NA", i, 2, "Sheet1", Path_FileName, Fail);
					e.printStackTrace();
				}
				
				// DESCRIPTION
				try {
					description = driver.findElement(By.name("description")).getAttribute("content"); //description
					//if (description!=null) {
					if (description.length()!=0) {
						ExcelUtils.setCellData(description, i, 3, "Sheet1", Path_FileName,Pass);
					} else {
						ExcelUtils.setCellData("NA", i, 3, "Sheet1", Path_FileName , Fail);
					}				
					System.out.println("Description::: "+description);
				} catch (Exception e) {
					ExcelUtils.setCellData("NA", i, 3, "Sheet1", Path_FileName , Fail);
					e.printStackTrace();
				}
				
				//H1 TAG
				try { 
					h1Tag = driver.findElement(By.className("list-title")).getText();  //H1 Tag
					//if (h1Tag!=null  ) {	
					if (h1Tag.length()!=0) {
							ExcelUtils.setCellData(h1Tag, i, 5, "Sheet1", Path_FileName, Pass);				
					}
					else {
						ExcelUtils.setCellData("NA", i, 5, "Sheet1", Path_FileName , Fail);
					}
					System.out.println("H1 Tag::: "+h1Tag);
					
				} catch (Exception e) {
					ExcelUtils.setCellData("NA", i, 5, "Sheet1", Path_FileName , Fail);
					e.printStackTrace();
				}
				
				//CANONICAL TAG
				try { 
					List<WebElement> linkList=driver.findElements(By.tagName("link"));
					for(int l=0;l<linkList.size();l++) {
						try {
							canonicalLink = linkList.get(l).getAttribute("rel");
							if(canonicalLink.equals("canonical")) {
								canonicalTag = linkList.get(l).getAttribute("href");
								//if (canonicalTag!=null) {
								if (canonicalTag.length()!=0) {
									ExcelUtils.setCellData(canonicalTag, i, 4, "Sheet1", Path_FileName, Pass);
								} else {
									ExcelUtils.setCellData("NA", i, 4, "Sheet1", Path_FileName , Fail);
								}							
								System.out.println("CanonicalTag::: "+canonicalTag);
							}
						} catch (Exception e) {
							ExcelUtils.setCellData("NA", i, 4, "Sheet1", Path_FileName , Fail);
							e.printStackTrace();
						}		
					}
					
				} catch (Exception e) {
					ExcelUtils.setCellData("NA", i, 4, "Sheet1", Path_FileName ,Fail);
					e.printStackTrace();
				}
	
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		System.out.println("TOTAL TIME TAKEN BY SCRIPT TO EXECUTE ALL ROWS::: "+((System.currentTimeMillis()-programStartTime)/1000));
		driver.close();
	}  
}
