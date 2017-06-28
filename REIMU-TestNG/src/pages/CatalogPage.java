package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;


public class CatalogPage {
	WebDriver driver;
	public static WebElement element = null;
	public static String elementValue = null;
	public static String text = null;
	public CatalogPage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	
	public void openCatalogPage(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public WebElement btnAddProduct(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "//button[contains(text(),'Add Products')]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
            element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnAddProduct) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;		
	}
	
	public WebElement btnDeleteChecked(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "//button[@id='catalog-delete-products-modal-btn']";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
            element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnDeleteChecked) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;		
	}
	
	public WebElement btnChooseFile(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "//label[contains(text(),'Choose file...')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnChooseFile) - FindBy: xpath - Value: "+elementValue);
		}
		return element;
	}
	
	public WebElement btnSave(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "//button[@id='catalog-upload-product-list-confirm-btn']" ;
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='catalog-upload-product-list-confirm-btn']")));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSave) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	
	public WebElement searchBox(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue = "//input[@id='product-search-term-input']" ;
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(searchBox) - FindBy: xpath - Value: "+elementValue);
		}		
		return element;
	}
	
	public WebElement btnSearch(WebDriver driver,WebDriverWait wait) {
		element = null;
		String elementValue = null;
		try {
			elementValue= "//button[@id='product-search-icon-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");
		} catch (Exception e) {
			Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSearch) - FindBy: xpath - Value: "+elementValue);
		}	
		return element;
	}
	
	public static class ProductSection {
		public static WebElement findProduct(WebDriver driver,WebDriverWait wait, String expPrdName) throws InterruptedException {
			element = null;
			elementValue = null;
			try {
				 elementValue = "product-desc";
				Thread.sleep(2000);
				List<WebElement> productList = driver.findElements(By.className(elementValue)); // FINDING PRODUCTS NAME PRESENT ON CATALOG PAGE
		        if(productList.size()>0) {
		        	for (int i = 0; i < productList.size(); i++) {
		        		try {
		        			elementValue = "product-name";
							String pName= productList.get(i).findElement(By.className(elementValue)).getText();  //FINDING PRODUCT NAME
							if (pName.equals(expPrdName)) {
								element = productList.get(i);
								break;
							}
						} catch (Exception e) {
						}
					}
		        }
				SpecialAction.waitForElementCondition(driver, element, "visible");			
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(findProduct) - FindBy: className - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement btnProductMenu(WebDriver driver ,WebDriverWait wait,WebElement product) {
			element = null;
			String elementValue = null;
			try {
				elementValue = "button";
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-desc']//button")));
				element = product.findElement(By.tagName("button"));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnProductMenu) - FindBy: tagName - Value: "+elementValue);
			}		
			return element;
		}
		
		public static WebElement lnkProductDelete(WebDriver driver,WebDriverWait wait,WebElement productName) {
			element = null;
			String elementValue = null;
			try {
				elementValue = "Delete Products";
				element = productName.findElement(By.linkText(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(lnkProductDelete) - FindBy: linkText - Value: "+elementValue);
			}		
			return element;
		}
		
		public static WebElement lnkProductEdit(WebDriver driver,WebDriverWait wait,WebElement productName) {
			element = null;
			String elementValue = null;
			try {
				elementValue = "Edit Products";
				element = productName.findElement(By.linkText(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(lnkProductEdit) - FindBy: linkText - Value: "+elementValue);
			}			
			return element;
		}
		
		public static WebElement btnOKDeleteProduct(WebDriver driver,WebDriverWait wait) {
			element = null;
			String elementValue = null;
			try {
				elementValue = "//button[@id='catalog-delete-products-confirm-btn']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));			
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnOKDeleteProduct) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement btnCancelDeleteProduct(WebDriver driver,WebDriverWait wait) {
			element = null;
			String elementValue = null;
			try {
				elementValue = "//button[contains(text(),'Cancel')]";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnCancelDeleteProduct) - FindBy: xpath - Value: "+elementValue);
			}	
			return element;
		}
		
		public static String txtProdPrice(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//span[@class='product-price']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				text = element.getText();
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtProdPrice) - FindBy: xpath - Value: "+elementValue);
			}
			return text;
		}
		
		public static WebElement radioBtnProd(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {Thread.sleep(2000);
				elementValue = "//div[@class='videoContainer col-lg-3 col-md-3 col-xs-12']//div[@class='product-checkbox']//label";
				List<WebElement> allRadioBtn = driver.findElements(By.xpath(elementValue));  
				for (int j = 1; j <= allRadioBtn.size(); j++) {
					String path = "//div[@class='videoContainer col-lg-3 col-md-3 col-xs-12']["+j+"]//div[@class='product-checkbox']//label";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
					driver.findElement(By.xpath(path)).click();
				}
				element = driver.findElement(By.xpath(elementValue));			
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(radioBtnProd) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
	}
	
	public static class EditProductSection {
		public static WebElement btnSKU(WebDriver driver,WebDriverWait wait) throws InterruptedException {
			element = null;
			String elementValue = null;
			try {
				elementValue = "//button[@id='catalog-update-product-modal-skus-btn']";
				Thread.sleep(2000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSKU) - FindBy: tagName - Value: "+elementValue);
			}		
			return element;
		}
		
		public static WebElement findSku(WebDriver driver,WebDriverWait wait, String expSkuID, String expSkuName) throws InterruptedException {
			element = null;
			String elementValue = null;
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='sku-sort-item']//div[@class='flexContentItem']")));
				elementValue = "sku-sort-item";
				List<WebElement> skuList = driver.findElements(By.className(elementValue)); // FINDING SKU NAME PRESENT ON CATALOG PAGE
		        if(skuList.size()>0) {
		        	for (int i = 0; i < skuList.size(); i++) {
						String skuText= skuList.get(i).getText();  
						if (skuText.contains(expSkuID)) {//FINDING SKU ID
							if (skuText.contains(expSkuName)) {
								element = skuList.get(i);
								break;
							}
							else{
								Reporter.log("Sku name:"+expSkuName+" Not found on attached sku id "+expSkuID);
							}
							
						}
					}
		        }			
				SpecialAction.waitForElementCondition(driver, element, "visible");
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(findSku) - FindBy: xpath - Value: "+elementValue);
			}	
			return element;
		}
		
		public static List<WebElement> btnRedCross(WebDriver driver,WebDriverWait wait) {
			element = null;
			elementValue = null;
			List<WebElement> list = null;
			try {
				elementValue = "//span[@class='glyphicon glyphicon-remove form-control-feedback']";
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				list = driver.findElements(By.xpath(elementValue));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnRedCross) - FindBy: xpath - Value: "+elementValue);
				//return element;
			}
			return list;
		}
		
		public static WebElement btnDeleteSku(WebDriver driver,WebDriverWait wait,String skuID) {
			element = null;
			elementValue = null;
			try {
				elementValue = "//div[@class='sku-sort-item']//span[@id='catalog-update-sku-list-delete-"+skuID+"-btn']";
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				Thread.sleep(5000);
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnDeleteSku) - FindBy: xpath - Value: "+elementValue);
				return element;
			}
			return element;
		}
		
		public static WebElement btnEditSku(WebDriver driver,WebDriverWait wait,String skuID) {
			element = null;
			elementValue = null;
			try {
				elementValue = "//div[@class='sku-sort-item']//span[@id='catalog-update-sku-list-update-"+skuID+"-btn']";
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				Thread.sleep(5000);
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnEditSku) - FindBy: xpath - Value: "+elementValue);
				return element;
			}
			return element;
		}
		
		public static WebElement btnSkuDeleteYes(WebDriver driver,WebDriverWait wait) {
			element = null;
			elementValue = null;
			try {
				elementValue = "//button[contains(text(),'Yes')]";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSkuDeleteYes) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement btnSkuDeleteNo(WebDriver driver,WebDriverWait wait) {
			element = null;
			elementValue = null;
			try {
				elementValue = "//button[contains(text(),'No')]";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSkuDeleteNo) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement btnSkuSave(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				Thread.sleep(2000);
				elementValue = "//button[@id='catalog-update-sku-modal-save-btn']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSkuSave) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtSkuID(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-skuId-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtSkuID) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtSkuName(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-name-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtSkuName) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtColor(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-color-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtColor) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtSize(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-size-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtSize) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtInventory(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-inventory-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtInventory) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtSkuDescription(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-description-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtSkuDescription) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtListPrice(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-listPrice-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtListPrice) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtSalePrice(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-sku-modal-salePrice-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtSalePrice) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtBxCurrency(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-currency-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtBxCurrency) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtPrdID(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-productId-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdID) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtPrdName(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-name-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdName) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtCatID(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-categoryId-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtCatID) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtCatName(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-categoryName-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtCatName) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtCatURL(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-categoryUrl-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtCatURL) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtPrdDescription(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-shortDescription-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdDescription) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement txtPDPURL(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-productPageUrl-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPDPURL) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtPrdLargeImageURLL(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-largeImageUrl-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdLargeImageURLL) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtPrdSmallImageURL(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-smallImageUrl-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdSmallImageURL) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		public static WebElement txtPrdThumbnailImageURL(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//input[@id='catalog-update-product-modal-thumbnailImageUrl-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(txtPrdThumbnailImageURL) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
		
		public static WebElement btnSave(WebDriver driver, WebDriverWait wait){
			element = null;
			elementValue = null;
			try {
				elementValue = "//button[@id='catalog-edit-product-save-btn']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
			} catch (Exception e) {
				Reporter.log("ERROR: CatalogPage.java - Element Method:(btnSave) - FindBy: xpath - Value: "+elementValue);
			}
			return element;
		}
	}
	
	
	
}
