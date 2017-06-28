package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class UpdateLookPage {

	WebDriver driver;
	static WebElement element = null;
	static String elementValue = null;
	public UpdateLookPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void openLookUpdatePage(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(10000);
	}
	
	
	public WebElement btnPreview(WebDriver driver,WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("look-snippet-modal-btn")));	
			wait.until(ExpectedConditions.elementToBeClickable(By.id("look-snippet-modal-btn")));
			element = null;
			elementValue = "button";
			
			element = driver.findElement(By.id("look-snippet-modal-btn"));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnPreview) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Preiew");
		}
		return element;
	}
	
	public  WebElement btnClose(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//div[@class='modal-lg modal-dialog']//div//div[@class='modal-footer']//button[@id='uptate-look-image-cancel-button']";
			//elementValue = "//button[@id='uptate-look-image-cancel-button']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnClose) - FindBy: xpath - Value: "+elementValue+" - Button 'Close'");
			Reporter.log(e.toString());
		}
		return element;
	}
	public WebElement lookEndPoint(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//input[@id='update-look-endpoint-url']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(cartTypeDropDownMenu) - FindBy: xpath - Value: "+elementValue+" - Drop Down Menu of cart Type");
		}
		return element;
	}
	
	public WebElement cartTypeDropDownMenu(WebDriver driver,WebDriverWait wait) {
		try {
			elementValue = "//select[@id='update-look-cart-type']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(cartTypeDropDownMenu) - FindBy: xpath - Value: "+elementValue+" - Drop Down Menu of cart Type");
		}
		return element;
	}
	

	public  WebElement cartType(WebDriver driver,WebDriverWait wait,int index) {
		try {
			element = null;
			elementValue = "//select[@id='update-look-cart-type']//option["+index+"]";
			element = driver.findElement(By.id("update-look-cart-type"));
			SpecialAction.scrollUpToElement(driver, element);
			element.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));			
			SpecialAction.mouseAction(driver, element, "doubleClickOnly").perform();
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(cartType) - FindBy: id & xpath - Value:"+elementValue);
		}
		return element;		
}
	
	
	public void switchFrame(WebDriver driver,WebDriverWait wait, String frameName) {
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name(frameName)));
			//driver.switchTo().frame(frameName);
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(switchFrame) - FRAME with name: "+frameName+" Not found");
		}
	}
	
	public WebElement btnLookSnippet(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "button";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Look Snippet')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Look Snippet')]")));
			List<WebElement> listBtn = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(listBtn, "Look Snippet");
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnLookSnippet) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Look Snippet");
		}
		return element;
	}
	
	public WebElement btnCloseSign(WebDriver driver) {
		try {
			element = null;
			elementValue = "button";
			List<WebElement> listBtn = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(listBtn, "×");//button//span[Contains(text(),'×')]
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnCloseSign) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Look Snippet");
		}
		
			return element;	
	}
	
	public WebElement btnEditAnchor(WebDriver driver,WebDriverWait wait,int index) {
		try {
			element = null;
			elementValue = "//div[@id='look-anchor-point-list']//div[@class='row']["+index+"]//button[@class='look-edit-button btn btn-primary pull-right']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(editAnchor) - FindBy: tagName - Value: "+elementValue+" - edit anchor button");
		}
			return element;	
	}
	
	public WebElement btnRemoveAnchor(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='look-anchor-remove-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(removeAnchor) - FindBy: tagName - Value: "+elementValue+" - REMOVE ANCHOR button");
		}
			return element;	
	}
	
	public WebElement btnDelete(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='look-anchor-remove-product-confirm-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnDelete) - FindBy: xpath - Value: "+elementValue+" - DELETE button on Remove Product from cue point ");
		}
			return element;	
	}
	
	public WebElement btnCloseSign(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@class='close']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnCloseSign) - FindBy: tagName - Value: "+elementValue+" - close button of Look Snippet window");
		}
			return element;	
	}
	
	
	public WebElement btnGridiron(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[text()='Gridiron']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnGridiron) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Gridiron");
		}
		return element;
	}
	
	public WebElement btnClicktoSee(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			//elementValue = "button";
			elementValue = "//button[text()='Click to See']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnClicktoSee) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Click to See");
		}
		return element;
	}
	
	public WebElement btnOK(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			element = null;
			elementValue = "//div[@class='modal-dialog']/div//div[@class='modal-footer']//button[text()='Ok']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnOK) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Ok");
		}
		return element;
	}
	public WebElement btnCancel(WebDriver driver,WebDriverWait wait) {
		try {
			Thread.sleep(2000);
			element = null;
			elementValue = "//div[@class='modal-dialog']/div//div[@class='modal-footer']//button[text()='Cancel']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnCancel) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Calcel");
		}
		return element;
	}
	
	public WebElement lnkHome(WebDriver driver) {
		try {
			Thread.sleep(2000);
			element = null;
			elementValue = "a";
			List<WebElement> listBtn = driver.findElements(By.tagName(elementValue));
			element = SpecialAction.getElementByText(listBtn, "Home");
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(lnkHome) - FindBy: tagName - Value: "+elementValue+" - LinkText: Home");
		}
		return element;
	}
	
	public String snippetText(WebDriver driver) {
		String snippet = null;
		try {
			Thread.sleep(2000);
			element = null;
			elementValue = "pre";
			snippet = driver.findElement(By.tagName(elementValue)).getText();	
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(snippetText) - FindBy: tagName - Value: "+elementValue+" - This is snippet area");
		}
		return snippet;
	}
	
	public WebElement txtLookNamefield(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "update-look-title";
			//element = driver.findElement(By.className(elementValue)).findElement(By.tagName("input"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id("update-look-title"));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(lookNamefield) - FindBy: id - Value: "+elementValue+" - This is Look Name field");
		}
		return element;
	}
	
	public WebElement txtLookImageUrl(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='update-look-image-field']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
		    element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(txtLookImageUrl) - FindBy: xpath - Value: "+elementValue+" - Look Image url Field");
		}
		return element;
	}
	public WebElement btnAddLookImagePlusSign(WebDriver driver,WebDriverWait wait) { 
		try {
			element = null;
			elementValue = "//button[@id='look-add-image-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));			
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnAddLookImagePlusSign) - FindBy: xpath - Value: "+elementValue+" - This is + Sign for adding new look image");
		}
		return element;
	}
	
	public WebElement btnAddPrdPlusSign(WebDriver driver,WebDriverWait wait) { 
		try {
			element = null;
			elementValue = "//span[@class='glyphicon glyphicon-plus']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnAddLookImagePlusSign) - FindBy: xpath - Value: "+elementValue+" - This is + Sign for adding new look image");
		}
		return element;
	}
	
	
	public WebElement newAnchorPointLocation(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "look-design-image-container";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");	
			SpecialAction.mouseAction(driver, element, "click").perform();	
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(newAnchorPointLocation) - FindBy: id - Value: "+elementValue+" - This is new anchor on look image");
		}
		return element;
	}
	
	public WebElement newAnchorPointLocation2(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "look-design-image-container";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");	
			SpecialAction.mouseAction(driver, element, "click").perform();	
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(newAnchorPointLocation) - FindBy: id - Value: "+elementValue+" - This is new anchor on look image");
		}
		return element;
	}
	
	public WebElement newAnchorTitle(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "looks-anchor-point-edit-anchorTitle";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(newAnchorTitle) - FindBy: id - Value: "+elementValue+" - This is new anchor title");
		}
		return element;
	}
	
	public WebElement btnNewAnchorAddProd(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "look-anchor-add-product-btn";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(newAnchorTitle) - FindBy: id - Value: "+elementValue+" - This is ADD PTODUCT button on new anchor");
		}
		return element;
	}
	
	
	
	public WebElement btnBack(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "look-anchor-back-btn";
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.id(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnBack) - FindBy: id - Value: "+elementValue+" - This is BACK button on new anchor");
		}
		return element;
	}
	
	public WebElement btnRemoveProductSigntemp2(WebDriver driver,WebDriverWait wait,int prdBxNumber) {
		try {
			element = null;
			elementValue = "//div[@class='ibox-content product-box add-product-box']["+prdBxNumber+"]//button[@id='look-anchor-remove-product-btn']";		              
			//elementValue = "look-anchor-remove-product-btn";
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementValue)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnRemoveProductSigntemp2) - FindBy: xpath - Value: "+elementValue+" - This is X sign to remvoe product");
		}
		return element;
	}
	
	public WebElement txtProductShortDesc(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "product-short-description";
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementValue)));
			element = driver.findElement(By.id(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateLookPage.java - Element Method:(txtProductShortDesc) - FindBy: className - Value: "+elementValue+" - Product description on update look page");
		}
		return element;
	}
	
	
	
	
	
///////////STARTING NEW STATIC CLASS (NewAnchor)///////////////////////////////
	public static class NewAnchor {
		public static WebElement searchFieldPrdInNewAnchor(WebDriver driver, WebDriverWait wait) {
			try {
				element = null;
				elementValue = "//input[@id='look-product-modal-search-input']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(searchPrdInNewAnchor) - FindBy: id - Value: "+elementValue+" - This is search field for product on new anchor");
			}
			finally {return element;}
		}
		
		public static WebElement searchSignNewAnchor(WebDriver driver,WebDriverWait wait) {
			try {
				element = null;
				elementValue = "//span[@class='glyphicon glyphicon-search']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(searchSignInNewAnchor) - FindBy: xpath - Value: "+elementValue+" - This is search button for product on new anchor");
			}
			return element;
		}
		
		public static WebElement searchedPrdResult(WebDriver driver,WebDriverWait wait, String prdname) {
			try {
				element = null;
				elementValue = "//small[text()='"+prdname+"']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(searchedPrdResult) - FindBy: classname - Value: "+elementValue+" - This is search result for product on new anchor");
			}
			return element;
		}
		
		public static WebElement btnAddProducts(WebDriver driver) {
			try {
				element = null;
				elementValue = "look-product-modal-save-btn";
				element = driver.findElement(By.id(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnAddProducts) - FindBy: id - Value: "+elementValue+" - This is ADD PRODUCT button for search result on new anchor");
			}
			return element;
		}
	}
	
       ///////////STARTING NEW STATIC CLASS (LookPreviewSection)///////////////////////////////
	public static class LookPreviewSection {
		//img[@data-reactid='.0.0.0.0.0.0']
		public static WebElement imgOnPreview(WebDriver driver,WebDriverWait wait) {
			try {
				element = null;
				elementValue = "//img[@data-reactid='.0.0.0.0.0.0']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(imgOnPreview) - FindBy: xpath - Value: "+elementValue+" - This is look image on Preview window");
				Reporter.log(e.toString());
			}
			return element;
		}
		public static WebElement btnClose(WebDriver driver,WebDriverWait wait) {
			try {
				element = null;
				elementValue = "//button[@class='close']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnClose) - FindBy: xpath - Value: "+elementValue+" - This is close X of preview window");
				Reporter.log(e.toString());
			}
			return element;
		}
		
		public static WebElement btnMoreDetails(WebDriver driver, WebDriverWait wait,int btnNumber) {
			try {
				elementValue = "button";
				Thread.sleep(2000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button//span[contains(text(),'Add to bag')]")));
				List<WebElement> listAllBtn = driver.findElements(By.tagName(elementValue));
				element = SpecialAction.getElementByText(driver,listAllBtn, "More Details", btnNumber);
				Thread.sleep(2000);
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnMoreDetails) - FindBy: tagName - TagName: "+elementValue+" - ButtonText: More Details");
			}
			return element;
		}
		
		public static WebElement btnMoreDetail(WebDriver driver,WebDriverWait wait,String prdName) {
			try {
				elementValue = "//*[text()='"+prdName+"']//parent::div//following-sibling::div//button//span[contains(text(),'More Details')]";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnMoreDetail) - FindBy: xpath = "+elementValue+" - ButtonText: More Detail");
			}
			return element;
		}
		
		public static WebElement imgClose(WebDriver driver) {
			try {
				element = null;
				elementValue = "close";
				System.out.println("I M ON look PREVIEW PAGE");
				element = driver.findElement(By.className(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");	
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(imgClose) - FindBy: className - Value:"+elementValue);
			}
			return element;
		}
		
		public static WebElement cuePoint(WebDriver driver, String cuePoint,WebDriverWait wait) {
			try {
				Thread.sleep(2000);
				element = null;
				elementValue = "i";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(elementValue)));
				 List<WebElement> cuePointList = driver.findElements(By.tagName(elementValue));
				 System.out.println("Total i tag::"+cuePointList.size());
				 for (int i = 0; i < cuePointList.size(); i++) {
					String cuePointName = cuePointList.get(i).getAttribute("data-reactid");
					if (cuePointName.equals(cuePoint)) {
						element = cuePointList.get(i);
						break;
					}
				}
				 Thread.sleep(2000);
				 SpecialAction.waitForElementCondition(driver, element, "visible");	
				
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateLookPage.java - Element Method:(cuePoint) - FindBy: tagName - Name: "+elementValue+" - Attribute Value:"+cuePoint);
				return element;
			}
			return element;
		}
			
		///////////STARTING NEW STATIC CLASS (ProductDetailSection)///////////////////////////////
		public static  class ProductDetailSection {
			public static WebElement alermsgOOS(WebDriver driver, WebDriverWait wait) {
				try {				
					element = null;
					elementValue = "//div[@class='alert alert-warning']";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));	
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					//SpecialAction.waitForElementCondition(driver, element, "visible");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(alermsgOOS) - FindBy: xpath - Value: "+elementValue+" - OOS msg section on product detail window");
				}
				return element;
			}
			public static WebElement skuColor(WebDriver driver,WebDriverWait wait,int index) {
				try {
					element = null;
					elementValue = "//select[@id='select-color']//option["+index+"]";	
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("select-color")));
					driver.findElement(By.id("select-color")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					SpecialAction.mouseAction(driver, element, "doubleClickOnly").perform();
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(skuColor) - FindBy: id & xpath  - Value:"+elementValue);
				}
				return element;		
		}
			
			public static WebElement skuSize(WebDriver driver,WebDriverWait wait,int index) {
					try {
						element = null;
						elementValue = "//select[@id='select-size']//option["+index+"]";				
						driver.findElement(By.id("select-size")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
						element = driver.findElement(By.xpath(elementValue));
						SpecialAction.mouseAction(driver, element, "doubleClickOnly").perform();
					} catch (Exception e) {
						Reporter.log("ERROR: UpdateLookPage.java - Element Method:(skuSize) - FindBy: id & xpath - Value:"+elementValue);
					}
					return element;		
			}

			public static WebElement skuQTY(WebDriver driver,WebDriverWait wait,int index) {
				try {
					element = null;
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(By.id("select-quantity")));
					driver.findElement(By.id("select-quantity")).click();
					Thread.sleep(2000);
					elementValue = "//select[@id='select-quantity']//option["+index+"]";				
					element = driver.findElement(By.xpath(elementValue));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
					SpecialAction.mouseAction(driver, element, "doubleClick").perform();
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(skuQTY) - FindBy: id & xpath  - Value:"+elementValue);
				}
				return element;		
			}
			
			public static WebElement btnAddToCart(WebDriver driver) {
				try {
					element = null;
					elementValue = "button";
					List<WebElement> listAllBtn = driver.findElements(By.tagName(elementValue));
					element = SpecialAction.getElementByText(listAllBtn, "Add to cart");
					SpecialAction.waitForElementCondition(driver, element, "click");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnAddToCart) - FindBy: tagName - TagName: "+elementValue+" - Value: Add to cart");
				}
				return element;
			}
			
			public static WebElement btnAddToCart(WebDriver driver, WebDriverWait wait) {
				try {
					element = null;
					elementValue = "//button//span[text()='Add to cart']";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					SpecialAction.waitForElementCondition(driver, element, "click");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnAddToCart) - FindBy: xpath - value: "+elementValue+" - button text : Add to cart");
				}
				return element;
			}
			
			public static WebElement btnClose(WebDriver driver,WebDriverWait wait) {
				try {
					Thread.sleep(2000);
					element = null;
					//xpath=//button[text()='Close']
					elementValue = "button";
					//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Close']")));
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Close']")));
					List<WebElement> listAllBtn = driver.findElements(By.tagName(elementValue));
					element = SpecialAction.getElementByText(listAllBtn, "Close");
					SpecialAction.waitForElementCondition(driver, element, "visible");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnClose) - FindBy: tagName - TagName: "+elementValue+" - Value: Close");
					Reporter.log(e.toString());
				}
				return element;
			}
			public static WebElement btnClose1(WebDriver driver, WebDriverWait wait) {
				try {				
					element = null;
					elementValue = "//button[text()='Close']";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					//SpecialAction.waitForElementCondition(driver, element, "visible");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(btnClose) - FindBy: xpath - Value: "+elementValue+" - button text: 'Close'");
				}
				return element;
			}
			
			public static WebElement imgClose(WebDriver driver) {
				try {
					element = null;
					elementValue = "javascript-views-commons-___ProductModal__closeIcon";
					System.out.println("I M ON ProductDetailSection POPUP");
					element = driver.findElement(By.className(elementValue));
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateLookPage.java - Element Method:(imgClose) - FindBy: className - Value:"+elementValue);
				}
				return element;
			}
		}
		
	}
	
	
	

}
