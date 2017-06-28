package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.SpecialAction;

public class UpdateVideoPage {

	WebDriver driver;
     static WebElement element = null;
     List<WebElement> elementList = null;
     static String elementValue = null;
    
	public UpdateVideoPage(WebDriver driver) {
		 this.driver = driver;
	 }
	
	public void openVideoUpdatePage(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		//Thread.sleep(10000);
	}
	
	public WebElement bxVideoFileNamefield(WebDriver driver, WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='new-video-filename']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(txtVideoFileNamefield) - FindBy: xpath - Value: "+elementValue+" - This is Video Name field");
		}
		return element;
	}
	
	
	public WebElement btnPreview(WebDriver driver,WebDriverWait wait) {
		try {
			
			element = null;
			elementValue = "//button[@id='video-update-preview-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnPreview) - FindBy: xpath - Value: "+elementValue+" - ButtonText: Preiew");
		}
		return element;
	}
	
	public WebElement btnTemplate(WebDriver driver,WebDriverWait wait,String templateName) {
		try {		
			element = null;
			elementValue = "//button[text()='"+templateName+"']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnTemplate) - FindBy: xpath - Value: "+elementValue+" - Template button");
		}
		return element;
	}
	
	public WebElement btnOKIgetIt(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[contains(text(),'Ok, I get it!')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnOKIgetIt) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Ok, I get it!");
		}
		return element;
	}
	
	public WebElement btnVideoSnippet(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[contains(text(),'Video Snippet')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnVideoSnippet) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Video Snippet");
		}
		return element;
	}
	
	public WebElement btnAddAnchor(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//button[@id='video-update-add-anchor-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnAddAnchor) - FindBy: xpath - Value: "+elementValue+" - ADD ANCHOR button");
		}
		return element;
	}
	
	public WebElement boxTitle(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//input[@label='Title']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(boxTitle) - FindBy: xpath - Value: "+elementValue+" - Anchor Title field");
		}
		return element;
	}
	public WebElement boxTitleImageURL(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//input[@label='Title Image URL']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(boxTitleImageURL) - FindBy: xpath - Value: "+elementValue+" - Title Image URL field");
		}
		return element;
	}
	
	public WebElement boxDescription(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//input[@label='Description']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(boxDescription) - FindBy: xpath - Value: "+elementValue+" - Description field");
		}
		return element;
	}
	public WebElement boxToolTip(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//input[@label='ToolTip']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(boxToolTip) - FindBy: xpath - Value: "+elementValue+" - Tool Tip field");
		}
		return element;
	}
	
	public WebElement btnSave(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//button[text()='Save']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btSave) - FindBy: xpath - Value: "+elementValue+" - SAVE button");
		}
		return element;
	}
	
	public WebElement btnAddProduct(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='video-update-product-add-btn']//span";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnAddProduct) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Add Product");
		}
		return element;
	}
	public WebElement btnEditAnchor(WebDriver driver,WebDriverWait wait,String anchorName) {
		try {
			element = null;
			//elementValue = "//button[contains(text(),'Edit Anchor')]";
			elementValue = "//p[starts-with(text(),'"+anchorName+"')]//parent::div//following-sibling::div//button[contains(text(),'Edit Anchor')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnEditAnchor) - FindBy: tagName - Value: "+elementValue+" - ButtonText: EDIT ANchor");
		}
		return element;
	}
	public WebElement btnOk(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='video-update-product-add-btn']//parent::div//button[text()='Ok']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnOk) - FindBy: tagName - Value: "+elementValue+" - ButtonText: Ok");
		}
		return element;
	}
	
	public WebElement bxSearch(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//input[@id='video-update-search-product-input']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(bxSearch) - FindBy: tagName - Value: "+elementValue+" - This is product search box");
		}
		return element;
	}
	
	public  WebElement searchedPrdResult(WebDriver driver,WebDriverWait wait, String prdname) {
		try {
			element = null;
			elementValue = "//small[text()='"+prdname+"']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(searchedPrdResult) - FindBy: classname - Value: "+elementValue+" - This is search result for product on new anchor");
		}
		return element;
	}
	
	public WebElement btnAddProducts(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='video-update-add-procucts-confirm-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnAddProducts) - FindBy: tagName - Value: "+elementValue+" - This is  ADD PRODUCTS button");
		}
		return element;
	}
	
	public WebElement btnSearch(WebDriver driver,WebDriverWait wait) {
		try {
			element = null;
			elementValue = "//button[@id='video-update-search-product-btn']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnSearch) - FindBy: tagName - Value: "+elementValue+" - This is  search button");
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
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(snippetText) - FindBy: tagName - Value: "+elementValue+" - This is snippet area");
		}
		return snippet;
	}
	public WebElement btnCloseSign(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			//elementValue = "//button[@class='close']//span[text()='x']";
			elementValue = "//button[@class='close']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnCloseSign) - FindBy: xpath - Value: "+elementValue+" - Close button sign X");
		}
		return element;
	}
	
	public WebElement btnPlayVideo(WebDriver driver,WebDriverWait wait) {
		try {			
			element = null;
			elementValue = "//div[@id='video-player-component']//button//span[text()='Play Video']";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
			element = driver.findElement(By.xpath(elementValue));
			SpecialAction.waitForElementCondition(driver, element, "visible");		
		} catch (Exception e) {
			Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnPlayVideo) - FindBy: xpath - Value: "+elementValue+" - Big PLAY button on video");
		}
		return element;
	}
	
	
	
	public static class VideoPreviewSection {
		public static void switchFrame(WebDriver driver,WebDriverWait wait, String frameName) {
			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name(frameName)));
				//driver.switchTo().frame(frameName);	
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(switchFrame) - FRAME with name: "+frameName+" Not found");
			}
		}
		public static WebElement btnCloseSign(WebDriver driver,WebDriverWait wait) {
			try {			
				element = null;
				//elementValue = "//button[@class='close']//span[text()='x']";
				elementValue = "//button[@class='close']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnCloseSign) - FindBy: xpath - Value: "+elementValue+" - Close button sign X");
			}
			return element;
		}
		
		public static WebElement btnDetail(WebDriver driver,WebDriverWait wait,String prdName,String btn) {
			try {
				//elementValue = "//h4[text()='"+prdName+"']//parent::div//button//span[contains(text(),'DETAIL') OR contains(text(),'Details')]";	
				elementValue = "//*[text()='"+prdName+"']//parent::div//button//span[contains(text(),'"+btn+"')]";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnDetail) - FindBy: xpath = "+elementValue+" - ButtonText: Detail");
			}
			return element;
		}
		
		public static WebElement btnPayVideo(WebDriver driver,WebDriverWait wait) {
			try {			
				element = null;
				elementValue = "//div[@id='player']//button//span[text()='Play Video']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnPayVideo) - FindBy: xpath - Value: "+elementValue+" - Play video button for template3");
			}
			return element;
		}
		
		public static WebElement btnShop(WebDriver driver,WebDriverWait wait) {
			try {			
				element = null;
				elementValue = "//span[text()='Shop']";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnShop) - FindBy: xpath - Value: "+elementValue+" - SHOP button for template3");
			}
			return element;
		}
		
		public static WebElement imagePrd(WebDriver driver,WebDriverWait wait,String ID) {
			try {			
				element = null;
				elementValue = "//div[@data-reactid='"+ID+"']//div";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));		
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");	
				SpecialAction.mouseAction(driver, element, "hover").perform();
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(imagePrd) - FindBy: xpath - Value: "+elementValue+" - This is Product image for template 3");
			}
			return element;
		}
		
		public static WebElement btnPlusSignOnProduct(WebDriver driver,WebDriverWait wait,String ID) {
			try {			
				element = null;
				elementValue = "//div[@data-reactid='"+ID+"']//span";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));		
				element = driver.findElement(By.xpath(elementValue));
				SpecialAction.waitForElementCondition(driver, element, "visible");		
			} catch (Exception e) {
				Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnPlusSignOnProduct) - FindBy: xpath - Value: "+elementValue+" - This is + Sign on product for add to cart ");
			}
			return element;
		}
		
		public static class ProductDetailSection {
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
					Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(skuColor) - FindBy: id & xpath  - Value:"+elementValue);
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
						Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(skuSize) - FindBy: id & xpath - Value:"+elementValue);
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
					Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(skuQTY) - FindBy: id & xpath  - Value:"+elementValue);
				}
				return element;		
			}
			
			public static WebElement btnClose(WebDriver driver, WebDriverWait wait) {
				try {				
					element = null;
					elementValue = "//button[text()='Close']";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					//SpecialAction.waitForElementCondition(driver, element, "visible");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnClose) - FindBy: xpath - Value: "+elementValue+" - button text: 'Close'");
				}
				return element;
			}
			
			public static WebElement btnAddToCart(WebDriver driver, WebDriverWait wait) {
				try {
					element = null;
					elementValue = "//button//span[text()='Add to cart']";
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));
					element = driver.findElement(By.xpath(elementValue));
					SpecialAction.waitForElementCondition(driver, element, "click");		
				} catch (Exception e) {
					Reporter.log("ERROR: UpdateVideoPage.java - Element Method:(btnAddToCart) - FindBy: xpath - value: "+elementValue+" - button text : Add to cart");
				}
				return element;
			}
		}
		
	}
	
	

}
