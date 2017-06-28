package validation;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pages.CatalogPage;
import utilities.SpecialAction;

public class CatalogValidation {
	private WebElement element = null;
	List<WebElement> list = null;
	CatalogPage catalogPage;
	String successMsg = null;
	String failMsg = null;
	String screenShotName = null;
	private String ScreenshotLocation;
	private String automationStartTime;
	
	public CatalogValidation(WebDriver driver ,String ScreenshotLocation,String automationStartTime ) {
		catalogPage = new CatalogPage(driver);
		this.ScreenshotLocation = ScreenshotLocation;
		this.automationStartTime = automationStartTime;
	 }
	public void currencyVeify(WebDriver driver,WebDriverWait wait,String methodName) throws Exception{
		 successMsg = null;
		 failMsg = null;
		 screenShotName =null;
		 screenShotName = "Catalog-"+methodName;
		 String currencyText = null;
		 Thread.sleep(4000);
		 currencyText = CatalogPage.ProductSection.txtProdPrice(driver, wait);
		 if(methodName.equalsIgnoreCase("currencySignUSD")){
			 if (currencyText.contains("$")) {
				 successMsg="USD currency displaying correcly -  SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg="USD currency NOT displaying correcly -  FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if(methodName.equalsIgnoreCase("currencySignINR")){
			 if (currencyText.contains("₹ ")) {
				 successMsg="INR currency displaying correcly -  SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg="INR currency NOT displaying correcly -  FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 if(methodName.equalsIgnoreCase("currencySignBRT")){
			 if (currencyText.contains("$")) {
				 successMsg="BRT currency displaying correcly -  SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg="BRT currency NOT displaying correcly -  FAIL";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }
		 
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
		 
	 }
	 
	 public void verifyProductsPresence(WebDriver driver,WebDriverWait wait, String methodName, String prodname, String prodID) throws Exception {
		 Thread.sleep(5000);
		 successMsg = null;
		 failMsg = null;
		 screenShotName =null;
		 screenShotName = "Catalog-"+methodName;
		 driver.get("http://qamediacom.objectedge.com/admin/#/products");
		 driver.navigate().refresh();
		 catalogPage.searchBox(driver,wait).clear();	
		 if (prodID=="") {
			 catalogPage.searchBox(driver,wait).sendKeys(prodname);
		} else {
			catalogPage.searchBox(driver,wait).sendKeys(prodID);
		}		 
		 catalogPage.btnSearch(driver,wait).click();	
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodname);
		 if(methodName.equalsIgnoreCase("deleteExistingProduct")||methodName.equalsIgnoreCase("deleteMoreThanOneProduct")) {
			 if (element==null) {
				 successMsg= prodname+"::Product DELETED SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			}
			 else {
					failMsg=prodname+"::Product DELETION FAILED";
					 Reporter.log(failMsg);
					 screenShotName = "FAIL-"+screenShotName;
				}
		 }		 
		 if(methodName.equalsIgnoreCase("uploadProductFile")) {
			 if (element!=null) {
				 successMsg="Product UPLOADED SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			}
			 else {
					failMsg="Product UPLOAD FAILED";
					 Reporter.log(failMsg);
					 screenShotName = "FAIL-"+screenShotName;
				}
		 }	
		 if(methodName.equalsIgnoreCase("extraEmptyProduct")) {
			 if (element!=null) {
				 successMsg="Extra EMPTY Product CREATED";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			}
			 else {
					failMsg="Extra EMPTY Product NOT CREATED";
					 Reporter.log(failMsg);
					 screenShotName = "FAIL-"+screenShotName;
				}
		 }
		 if(methodName.equalsIgnoreCase("searchProduct")) {
			 if (element!=null) {
				 successMsg="Search working correctly -SUCCESSFULL ";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			}
			 else {
					failMsg="Search NOT working correctly -FAIL ";
					 Reporter.log(failMsg);
					 screenShotName = "FAIL-"+screenShotName;
				}
		 }
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }
	 
	 public void verifyDeletedSku(WebDriver driver,WebDriverWait wait, String methodName, String prodName, String prodID, String skuID, String skuName) throws Exception {
		 Thread.sleep(5000);
		 successMsg = null;
		 failMsg = null;
		 screenShotName =null;
		 screenShotName = "Catalog-"+methodName;		
		 element = CatalogPage.EditProductSection.btnDeleteSku(driver, wait, skuID);
		 if(methodName.equalsIgnoreCase("deleteSku")) {
			 element = CatalogPage.EditProductSection.btnDeleteSku(driver, wait, skuID);
			 if (element==null) {
				 successMsg=skuName+" SKU DELETED SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg=skuName+" SKU DELETION FAILED";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }		 
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }
	 
	 public void verifyRequiredParameter(WebDriver driver,WebDriverWait wait,String methodName) throws Exception {
		 Thread.sleep(5000);
		 element = null;
		 list = null;
		 successMsg = null;
		 failMsg = null;
		 screenShotName =null;
		 screenShotName = "Catalog-"+methodName;
		 int count = 0;
		 if (methodName.equals("prdRequiredProperties")) {
			 count = 11;
		}
		 if (methodName.equals("skuRequiredProperties")) {
			 count = 19;
		}
		 list = CatalogPage.EditProductSection.btnRedCross(driver, wait);
		 if (list!=null) {
			 if (list.size()==count) {
				 successMsg="Required paramters are empty -  SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			}		 
			}
		 else {
				failMsg="Required paramters are empty -  FAILED";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL"+screenShotName;
			} 
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }
	 
	 
	 
	 public void verifySkuPresence(WebDriver driver,WebDriverWait wait, String methodName, String prodName, String prodID, String skuID, String skuName) throws Exception {
		 Thread.sleep(5000);  
		 successMsg = null;
		 failMsg = null;
		 screenShotName =null;
		 screenShotName = "Catalog-"+methodName;
		 driver.navigate().refresh();
		 catalogPage.searchBox(driver,wait).clear();
		 catalogPage.searchBox(driver,wait).sendKeys(prodID);
		 catalogPage.btnSearch(driver,wait).click();		
		 element = CatalogPage.ProductSection.findProduct(driver,wait, prodName);
		 Thread.sleep(5000);
		 CatalogPage.ProductSection.btnProductMenu(driver,wait, element).click();
		 CatalogPage.ProductSection.lnkProductEdit(driver,wait, element).click();
		 Thread.sleep(2000);
		 CatalogPage.EditProductSection.btnSKU(driver,wait).click();
		 Thread.sleep(2000);
		 if(methodName.equalsIgnoreCase("deleteSku")) {
			 element = CatalogPage.EditProductSection.btnDeleteSku(driver, wait, skuID);
			 if (element==null) {
				 successMsg="SKU DELETED SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg="SKU DELETION FAILED";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }		 
		 if(methodName.equalsIgnoreCase("uploadSkuFile")) {
			 element =  CatalogPage.EditProductSection.findSku(driver,wait, skuID,skuName);
			 if (element!=null) {
				 successMsg="SKU UPLOADED SUCCESSFULLY";
				 Reporter.log(successMsg);
				 screenShotName = "PASS-"+screenShotName;
			} else {
				failMsg="SKU UPLOAD FAILED";
				 Reporter.log(failMsg);
				 screenShotName = "FAIL-"+screenShotName;
			}
		 }	
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 }
	 
	 public void VerifyExistingSku(WebDriver driver, WebDriverWait wait,String methodName, String skuName, String expSkuID) throws InterruptedException{
		    Thread.sleep(2000);
			element = null;
			String elementValue = null;
			successMsg = null;
			failMsg = null;
			screenShotName = "Catalog-"+methodName;
			int count = 0;
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='sku-sort-item']//div[@class='flexContentItem']")));
				elementValue = "sku-sort-item";
				List<WebElement> skuList = driver.findElements(By.className(elementValue)); // FINDING ALL SKUs PRESENT ON PRODUCT EDIT PAGE
		        if(skuList.size()>0) {
		        	for (int i = 0; i < skuList.size(); i++) {
						String skuText= skuList.get(i).getText();  
						if (skuText.contains(expSkuID)) {//FINDING SKU ID
							if (skuText.contains(skuName)) {
								count++;
							}
							else{
								Reporter.log("Sku name:"+skuName+" Not found on attached sku id "+expSkuID);
							}
							
						}
					}
		        }
		        	if (count==0) {
		        		failMsg = skuName+" : Sku not found in edit product page";
		        		Reporter.log(failMsg);
						screenShotName = "FAIL-"+screenShotName;
					}
		        	else {
						if (count<2) {
							successMsg = skuName+" : Existing sku overwriting correctly after existing sku file upload - SUCCESSFULLY";
			        		Reporter.log(successMsg);
							screenShotName = "PASS-"+screenShotName;
						} else {
							failMsg = skuName+" : Duplicate Existing sku created after existing sku file upload - FAILED";
			        		Reporter.log(failMsg);
							screenShotName = "FAIL-"+screenShotName;
						}
					}	
		        SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(VerifyExistingSku) - FindBy: className - Value: "+elementValue);
				System.out.println(e.toString());
			}
		}
	 
	 
	 
	 
	 public void VerifyExistingProduct(WebDriver driver, WebDriverWait wait,String methodName, String prdName){
			element = null;
			String elementValue = null;
			successMsg = null;
			failMsg = null;
			screenShotName = "Catalog-"+methodName;
			int count = 0;
			try {
				 elementValue = "product-desc";			 
				Thread.sleep(2000);
				List<WebElement> productList = driver.findElements(By.className(elementValue)); // FINDING PRODUCTS NAME PRESENT ON CATALOG PAGE
		        if(productList.size()>0) {
		        	for (int i = 0; i < productList.size(); i++) {
		        		try {
		        			elementValue = "product-name";
							String pName= productList.get(i).findElement(By.className(elementValue)).getText();  //FINDING PRODUCT NAME
							if (pName.equals(prdName)) {
								count++;
							}
						} catch (Exception e) {
							Reporter.log("ERROR: Catalog.java - Element Method:(VerifyExistingProduct) - FindBy: className - Value: "+elementValue);
							System.out.println(e.toString());
						}
					}
		        	if (count==0) {
		        		failMsg = prdName+" : Product not found in search result";
		        		Reporter.log(failMsg);
						screenShotName = "FAIL-"+screenShotName;
					}
		        	else {
						if (count<2) {
							successMsg = prdName+" : Existing product overwriting correctly after existing product file upload - SUCCESSFULLY";
			        		Reporter.log(successMsg);
							screenShotName = "PASS-"+screenShotName;
						} else {
							failMsg = prdName+" : Duplicate Existing product created after existing product file upload - FAILED";
			        		Reporter.log(failMsg);
							screenShotName = "FAIL-"+screenShotName;
						}
					}
		        }	
		        SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(VerifyExistingProduct) - FindBy: className - Value: "+elementValue);
				System.out.println(e.toString());
			}
		}
	 
	 public void verifyProductProperties(WebDriver driver, WebDriverWait wait,String methodName,String prodName, String prodID,String catID,String catName,String catURL, String PrdDesc,String PDPURL,String prdLargeImageURL, String prdSmallImageURL,String prdThumbnailImageURL,String currency) throws Exception {
		 screenShotName = "Catalog-"+methodName;	 
		 if (CatalogPage.EditProductSection.txtPrdName(driver, wait).getAttribute("value").equals(prodName)) {
				Reporter.log("Product name update SUCCESSFULLY");
			    }else {
				Reporter.log("Product name NOT update FAILED");
			    }
			 if (CatalogPage.EditProductSection.txtPrdID(driver, wait).getAttribute("value").equals(prodID)) {
					Reporter.log("Product ID update SUCCESSFULLY");
				}else {
					Reporter.log("Product ID  NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtCatID(driver, wait).getAttribute("value").equals(catID)) {
					Reporter.log("Product CAT ID update SUCCESSFULLY");
				}else {
					Reporter.log("Product CAT ID NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtCatName(driver, wait).getAttribute("value").equals(catName)) {
					Reporter.log("Product CAT NAME update SUCCESSFULLY");
				}else {
					Reporter.log("Product CAT NAME NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtCatURL(driver, wait).getAttribute("value").equals(catURL)) {
					Reporter.log("Product CAT URL update SUCCESSFULLY");
				}else {
					Reporter.log("Product CAT URL NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtPrdDescription(driver, wait).getAttribute("value").equals(PrdDesc)) {
					Reporter.log("Product DESCRIPTION update SUCCESSFULLY");
				}else {
					Reporter.log("Product DESCRIPTION NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtPDPURL(driver, wait).getAttribute("value").equals(PDPURL)) {
					Reporter.log("Product PDP URL update SUCCESSFULLY");
				}else {
					Reporter.log("Product PDP URL NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtPrdLargeImageURLL(driver, wait).getAttribute("value").equals(prdLargeImageURL)) {
					Reporter.log("Product Large image URL update SUCCESSFULLY");
				}else {
					Reporter.log("Product Large image URL NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtPrdSmallImageURL(driver, wait).getAttribute("value").equals(prdSmallImageURL)) {
					Reporter.log("Product Small image URL update SUCCESSFULLY");
				}else {
					Reporter.log("Product Small image URL NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtPrdThumbnailImageURL(driver, wait).getAttribute("value").equals(prdThumbnailImageURL)) {
					Reporter.log("Product Thumbnail image URL update SUCCESSFULLY");
				}else {
					Reporter.log("Product Thumbnail image URL NOT update FAILED");
				}
			 if (CatalogPage.EditProductSection.txtBxCurrency(driver, wait).getAttribute("value").equals(currency)) {
					Reporter.log("Product Currency update SUCCESSFULLY");
				}else {
					Reporter.log("Product Currency NOT update FAILED");
				}
			 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);
	 
	 }
	 
	 public void verifySkuProperties(WebDriver driver, WebDriverWait wait,String methodName,String prodName, String prodID,String skuID,String skuName,String color, String size,String inventory,String skuDesc, String listPrice,String salePrice) throws Exception {
		 screenShotName = "Catalog-"+methodName;	
		 if (CatalogPage.EditProductSection.txtSkuName(driver, wait).getAttribute("value").equals(skuName)) {
			Reporter.log("Sku name update SUCCESSFULLY");
		    }else {
			Reporter.log("Sku name NOT update FAILED");
		    }
		 if (CatalogPage.EditProductSection.txtSkuID(driver, wait).getAttribute("value").equals(skuID)) {
				Reporter.log("Sku ID update SUCCESSFULLY");
			}else {
				Reporter.log("Sku ID  NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtColor(driver, wait).getAttribute("value").equals(color)) {
				Reporter.log("Sku Color update SUCCESSFULLY");
			}else {
				Reporter.log("Sku Color  NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtSize(driver, wait).getAttribute("value").equals(size)) {
				Reporter.log("Sku Size update SUCCESSFULLY");
			}else {
				Reporter.log("Sku Size NAME NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtInventory(driver, wait).getAttribute("value").equals(inventory)) {
				Reporter.log("Sku inventory update SUCCESSFULLY");
			}else {
				Reporter.log("Sku inventory NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtSkuDescription(driver, wait).getAttribute("value").equals(skuDesc)) {
				Reporter.log("Sku DESCRIPTION update SUCCESSFULLY");
			}else {
				Reporter.log("Sku DESCRIPTION NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtListPrice(driver, wait).getAttribute("value").equals(listPrice)) {
				Reporter.log("Sku List price update SUCCESSFULLY");
			}else {
				Reporter.log("Sku List price NOT update FAILED");
			}
		 if (CatalogPage.EditProductSection.txtSalePrice(driver, wait).getAttribute("value").equals(salePrice)) {
				Reporter.log("Sku Sale price update SUCCESSFULLY");
			}else {
				Reporter.log("Sku Sale price NOT update FAILED");
			}
		 SpecialAction.getResultantScreenshot(driver, screenShotName, automationStartTime, ScreenshotLocation);

 
 }
	 
}
