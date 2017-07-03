package executionEngine;
import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.sun.jna.platform.win32.Sspi.SecHandle;

import sun.util.logging.resources.logging;
import utility.*;
public class RetailDash {
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
	public static final String Path_FileName = "D://workspaces//Selenuim//RetailDash//src//test//java//dataEngine//Netshoes-Centauro Manual Mapping - 501-900 - Onkar.xlsx";                                           
	public static void main(String[] args) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		CopyOfExcelUtils1.setExelFile(Path_FileName);
		int totalSkuRow = CopyOfExcelUtils1.getRowcount("501-900");
		driver=new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","D:\\workspaces\\chromedriver.exe");
		//driver=new ChromeDriver();
		System.out.println("TEST START at :"+System.currentTimeMillis());
		Log.info("TEST START at :"+System.currentTimeMillis());
		driver.get("http://www.centauro.com.br/");
		System.out.println("TOTAL row count of EXCEL SHEET::"+totalSkuRow);
		
		//for(int i=42;i<=42;i++) {
		for(int i=1;i<=totalSkuRow;i++) {  //LOOP FOR TOTAL ROW OF EXCEL SHEET 
			
			
			
			
			try { 
				String netshoesProduct1 = CopyOfExcelUtils1.getCellStringData(i,4,"501-900"); //product from excel sheet
				if(netshoesProduct1.equalsIgnoreCase("")) {
			//driver.get("http://www.netshoes.com.br/");		
			Log.info(":::::::::::::::::Current Product Row from EXCEL Sheet ::"+(i+1));
			String netshoesProduct = CopyOfExcelUtils1.getCellStringData(i,2,"501-900"); //product from excel sheet
			String productColor = CopyOfExcelUtils1.getCellStringData(i, 3, "501-900");  //COLOR FROM EXCEL SHEET
			int startPoint1 = productColor.indexOf("*");
			String color = productColor.substring(startPoint1+1);
			System.out.println("COlors::"+color);
			
			if(color.contains("+")) {
				int start2ndColoe = color.indexOf("+");
				 color1 = color.substring(0, start2ndColoe);  // GET COLOR 1
				 System.out.println("COLOR1::"+color1);
				 color2 = color.substring(start2ndColoe+1);  //GET COLOR 2
				 System.out.println("COLOR2::"+color2);
			}
			else {
				color1 = color;
				color2 = null;
				System.out.println("COLOR1::"+color1);
				System.out.println("COLOR2::"+color2);
			}
			
			
		   //do { //** start of DO WHILE LOOP
			    centaurSearField = driver.findElement(By.xpath("//*[@id='sli_search_1']"));
				centaurSearField.sendKeys(netshoesProduct);
				driver.findElement(By.xpath("//*[@id='search_']/button")).click();  //CLICKED ON SEARCH BUTTON

			do {	
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				searchResult = driver.findElement(By.xpath("//*[@id='content']/div[8]/div[12]/ul")); //search result table
				Log.info("Search page  found for Excel Row :"+(i+1));		
				//List<WebElement> searchedProducts = searchResult.findElements(By.tagName("li")); //list of searched all products using tag name 'li'
				List<WebElement> searchedProducts = searchResult.findElements(By.className("sli_grid_container")); //list of searched all products using class name
				System.out.println("Total search result:::"+searchedProducts.size());
				
				
				
				searchPageNumberUrl = new String[2];
				searchPageNumberUrl[0] = driver.getCurrentUrl();
				searchPageNumberUrl[1] = driver.getCurrentUrl();
				
				 if(searchedProducts.size() > 47) {
					 System.out.println("SEARCH PAGES MORE THAN 1");
					    pageNumberTab = driver.findElement(By.xpath("//*[@id='content']/div[8]/div[5]/ul"));
					    pageList = pageNumberTab.findElements(By.tagName("a"));
						//List<WebElement> pageList = pageNumberTab.findElements(By.tagName("li"));
					    
						//searchPageNumberUrl[0] = driver.getCurrentUrl();
						searchPageNumberUrl[1] = pageList.get(pageList.size()-1).getAttribute("href");
						System.out.println("CURRENT PAGE URL::"+searchPageNumberUrl[0]);
						System.out.println("NEXT PAGE URL::"+searchPageNumberUrl[1]);
						
					}
				
				
				//** STORING all PDP URL of search products from 1st search page				
				pdpURLs = new String[searchedProducts.size()];
				for (int p=1;p<=searchedProducts.size();p++) {
					String xpaths="//*[@id='zone"+p+"_product"+p+"']";
					pdpURLs[p-1] = driver.findElement(By.xpath(xpaths)).getAttribute("data-url");
					//System.out.println("PDP URLs ("+p+")::"+pdpURLs[p-1]);
				}
				
				
				
			   
				for (int j = 0; j < pdpURLs.length; j++) { //** loop for each searched products				
					try {
						Actions builder = new Actions(driver);
						Action act1= builder.moveToElement(searchedProducts.get(j)).build();
						act1.perform();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  //** instead of implicit wait. Implement explicit wait. FUture
						
						List<WebElement> colorList = searchedProducts.get(j).findElements(By.className("color-list"));
						//WebElement colorList=driver.findElement(By.xpath("//*[@id='content']/div[8]/div[12]/ul/li[1]/div[1]/div[1]/div[1]"));
						//System.out.println("FOUND XPATH");
						
						//System.out.println(colorList.getText());

						//List<WebElement> list = colorList.findElements(By.tagName("li"));
						System.out.println("COLOR LIST FOUND for product : "+j+"; Total Color :"+colorList.size());
						for(int c=0; c < colorList.size(); c++) {  //** COLOR FOR LOOP ON SEARCH PAGE
							String colorName = colorList.get(c).getAttribute("title");
							//System.out.println("COLOR NAME on PDP::"+colorName);
							
							if(color2!=null) {
								if(colorName.contains(color1) && colorName.contains(color2)) {
									//System.out.println("color2!=null");
									driver.get(pdpURLs[j]);	
									pdpPage(i, j);
									break;  //BREAKING COLOR FOR LOOP ON SEARCH PAGE
						       	}
							}
							else {
								//System.out.println("color2333=null");
								//if(colorName.contains(color1)) {
								if(colorName.equalsIgnoreCase("Cor: "+color1)) {	
									//System.out.println("color2=null");
									driver.get(pdpURLs[j]);
									pdpPage(i, j);
									break;  //BREAKING COLOR FOR LOOP ON SEARCH PAGE
						       	}
							}								
						}
						if(productFound) {
							productFound = false; 
							break;  //**  BREAKING SEARCH PRODUCT FOR LOOP
						}
						
					} catch (Exception e) {
						Log.info(":::::::Exception Happened for 'searched Product number' :"+(j+1)+" for 'Excel row number' :"+(i+1)+"::::::: PDP:("+driver.getCurrentUrl()+")");
						System.out.println(":::::::Exception Happened for 'searched Product number' :"+(j+1)+" for 'Excel row number' :"+(i+1)+":::::: PDP:("+driver.getCurrentUrl()+")");
						e.printStackTrace();
					}
					System.out.println("product found VAL :"+productFound);
					
					if((j==pdpURLs.length-1)&&productFound == false) {
						CopyOfExcelUtils1.setCellData("NA", i, 4, "501-900", Path_FileName);  //** Writing Result NA in excel sheet for not matching sku color for all searched product
					}
					if((j==47)&&productFound == false) {
						CopyOfExcelUtils1.setCellData("NA. Search result exceed more than 48 product  from 1st page", i, 4, "501-900", Path_FileName);  //** Writing Result NA in excel sheet for not matching sku color for all searched product
						driver.get(searchPageNumberUrl[1]);
					}			
				}  //**END OF EACH SEARCH PRODUCT FOR LOOP
		      } while (!searchPageNumberUrl[0].equalsIgnoreCase(searchPageNumberUrl[1]));  //**  END OF DO WHILE LOOP
				} //**END OF if BLOCK
			}   //**  END OF TRY BLOCK
			catch (Exception e) {
				Log.info("Search page NOT found for Excel Row :"+(i+1)+" Hence skipping this product from EXCEL");
				CopyOfExcelUtils1.setCellData("NA", i, 4, "501-900", Path_FileName);
				e.printStackTrace();
			}
			
			if(i==totalSkuRow) {
				driver.close();
				System.out.println("TEST END at :"+System.currentTimeMillis());
				Log.info("TEST END at :"+System.currentTimeMillis());
			}
				
		}  //**  END OF EXCEL SHEET FOR LOOP
	}  //** END OF MAIN METHOD
	
	public static void pdpPage(int i , int j) {

		System.out.println("Going on PDP for Row Number "+(i+1)+"with searched product number "+(j+1));
		//driver.get(pdpURLs[j]);  //**going to PDP. URL taken from array index					
		System.out.println("searching for color selector in J-"+(j+1));
		WebElement colors = driver.findElement(By.xpath("//*[@id='addToCart_pdp']/div[2]/div[1]/ul")); //color selector
		System.out.println("color selector found:: J-"+(j+1));
		List<WebElement> colorList = colors.findElements(By.tagName("li")); //list of colors
		System.out.println("COLOR FROM EXCELSHEET ::"+color1+"/"+color2);
		
		for(int k = 0; k < colorList.size();k++) { // COLOR FOR LOOP
			try {							
				//System.out.println("inside loop for color ::k-"+k);
				WebElement label= colorList.get(k).findElement(By.tagName("label"));
				String centrianoColorName = label.getAttribute("data-name"); //getting color name 
				
				System.out.println("COLOR on Centriano:::"+centrianoColorName);
				
				if(color2!=null) {
					if(centrianoColorName.contains(color1) && centrianoColorName.contains(color2)) {							
						colorList.get(k).click();
						RetailDash.writeResultInExcel(i,j,k);
						productFound = true;
						break; //** BREAKING COLOR FOR LOOP on PDP
			       	}
				}
				else {
					if(centrianoColorName.equalsIgnoreCase(color1)) {
						colorList.get(k).click();
						RetailDash.writeResultInExcel(i,j,k);
						productFound = true;
						break;  //** BREAKING COLOR FOR LOOP on PDP
			       	}						
				}							
			} catch (Exception e) {
				Log.info(":::::::Exception Happened for 'searched Product number' :"+(j+1)+" for 'Excel row number' :"+(i+1)+":::::::::::");
				System.out.println(":::::::Exception Happened for 'searched Product number' :"+(j+1)+" for 'Excel row number' :"+(i+1)+":::::::::::");
				e.printStackTrace();
			}							
		}
	}
	
	public static void writeResultInExcel(int i,int j ,int k) throws Exception {

			Log.info("COLOR FOUND");
			Log.info("EXCEL sheet Row Number: "+(i+1));
			Log.info("PRODUCT number from search page: "+(j+1));
			Log.info("COLOR number on PDP: "+k);
			
			String matchedColorPDPURL = driver.getCurrentUrl();  //**getting PDP URL 
			int index1 = matchedColorPDPURL.indexOf("?");
			int index2 = matchedColorPDPURL.indexOf("=");
			String requiredUrl= matchedColorPDPURL.substring(0, index1);
			String colorCode= matchedColorPDPURL.substring(index2+1);
			String productRef = driver.findElement(By.xpath("//*[@id='summary-review']/div/div[1]/p")).getText();
			int index3 = productRef.indexOf(":");
			String productCode = productRef.substring(index3+1);
			//System.out.println(requiredUrl);
			//System.out.println(colorCode);
			//System.out.println(productCode);
			//System.out.println(centrianoColorName);
			String colorName1=driver.findElement(By.xpath("//*[@id='addToCart_pdp']/div[2]/div[1]/p")).getText();
			int index4 = colorName1.indexOf("(");
			int index5 = colorName1.indexOf(")");
			String colorNameOnCantrino = colorName1.substring(index4+1, index5);
			//System.out.println(colorNameOnCantrino);
			
			//** writing RESULT in excel file
			
				CopyOfExcelUtils1.setCellData(productCode, i, 4, "501-900", Path_FileName);
				CopyOfExcelUtils1.setCellData(requiredUrl, i, 5, "501-900", Path_FileName);
				CopyOfExcelUtils1.setCellData(colorNameOnCantrino, i, 6, "501-900", Path_FileName);
				CopyOfExcelUtils1.setCellData(colorCode, i, 7, "501-900", Path_FileName);
	}

}
