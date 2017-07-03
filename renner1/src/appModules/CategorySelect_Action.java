package appModules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pageObjects.Home_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class CategorySelect_Action {
	
	public static void SelectCategory(int RowNumber, WebDriver driver) throws Exception
	{
		
		try{
		if("Lancamentos".equals(ExcelUtils.getCellData(RowNumber,Constant.Col_CategoryType)))
		{ 
			//Utils.waitForElement(driver.findElement(By.id("mrkItm10003")));
			//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			System.out.println("hello1");
			((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10003').click();");
			System.out.println("hello2");
			//Home_Page.SubProductLancamentos().click();
			driver.get("http://www.lojasrenner.com.br/lista/lancamentos/masculino");
			System.out.println("hello3");
			Log.info("Subcategory from Lancamentos category is selected from product listing page");
		}
		
		if("infantil".equals(ExcelUtils.getCellData(RowNumber,Constant.Col_ProductType)))
		{
			((JavascriptExecutor)driver).executeScript("document.getElementById('mrkItm10002').click();");
			Home_Page.SubProductInfantil().click();
			Log.info("Subcategory from Infantil category is selected from product listing page");
		}
		
		} catch(Exception e){
			throw(e);
		}
	}

}
