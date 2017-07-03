package Pages;

import java.util.List;

import org.apache.log4j.pattern.ThrowableInformationPatternConverter;
import org.junit.internal.Throwables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import TestNG.Log;
import Utilities.SpecialAction;

public class CheckoutPage {

	static WebDriver driver;
	static WebElement element = null;
	static Boolean timeout = false;
	
	public CheckoutPage(WebDriver driver) {
		//CheckoutLoginPage.driver = driver;
		//wait = new WebDriverWait(driver, 120);
	}

    public static class OrderSummarySection {
    	public static Boolean OrderSummary(WebDriver driver) {
    		timeout = false;
    		try {
    			 WebDriverWait wait = new WebDriverWait(driver, 60);
        		 wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-summary")));
        		 return timeout; 
			} catch (Exception e) {
				timeout = true;
				return timeout; 
			}  		
    		
    	}
		
	}
    
    
	public static class ShipAddressSection {
		public static WebElement enterFirstName(WebDriver driver) {
			WebElement shipFirstName = driver.findElement(By.id("fName"));
			return shipFirstName;
		}
		
		public static WebElement enterLastName(WebDriver driver) {
			WebElement shipLastName = driver.findElement(By.id("lName"));
			return shipLastName;
		}
		
		public static WebElement enterPhone(WebDriver driver) {
			WebElement phoneNumber = driver.findElement(By.id("phone"));
			return phoneNumber;
		}
		
		public static WebElement enterShipAdd1(WebDriver driver) {
			WebElement shipAddr1 = driver.findElement(By.id("address1"));
			return shipAddr1;
		}
		
		public static WebElement enterCity(WebDriver driver) {
			WebElement shipCity = driver.findElement(By.id("city"));
			return shipCity;
		}
		
		public static Select selectState(WebDriver driver) {
			WebElement shipState = driver.findElement(By.id("state"));
			return SpecialAction.selectList(shipState);
		}
			
		public static WebElement enterZipCode(WebDriver driver) {
			WebElement shipZipCode = driver.findElement(By.id("zipcd"));
			return shipZipCode;
		}
		
		public static WebElement shipOption(WebDriver driver) throws InterruptedException {
			WebElement shipOption = driver.findElement(By.xpath("//form[@id='shippingAddressForm']/section/div[2]/div"));
			SpecialAction.waitForElementCondition(driver, shipOption, "click");			
			return shipOption;

		}
		
		public static Select shipMethod(WebDriver driver) throws Exception {
			WebElement shipMethodListBox = null;
			try {
				 shipMethodListBox= driver.findElement(By.id("shipping-methods"));
				shipMethodListBox.click();
				Thread.sleep(2000);
				
			} catch (Exception e) {
				Log.error("Exception in shipMethod");
				Reporter.log("Exception in shipMethod");
				throw(e);
			}
			return SpecialAction.selectList(shipMethodListBox);
		}
		
		public static Select enableShipMethodListBox(WebDriver driver) throws Exception {
			WebElement shipMethodListBox = null;
			WebElement shipMethodList = null;
			try {
				 shipMethodListBox= driver.findElement(By.xpath("//*[@id='shippingAddressForm']/section/div[2]/div"));
				 SpecialAction.mouseAction(driver, shipMethodListBox).perform();
				 shipMethodList = shipMethodListBox.findElement(By.id("shipping-methods"));
				 SpecialAction.mouseAction(driver, shipMethodList).perform();
				 System.out.println("List area found1");
		
			} catch (Exception e) {
				Log.error("Exception in shipMethod");
				Reporter.log("Exception in shipMethod");
				throw(e);
			}
			return SpecialAction.selectList(shipMethodList);
		}
		
		
		public static WebElement buttonShipContinue(WebDriver driver) {
			WebElement btnCONTINU =null;
			try {
				btnCONTINU = driver.findElement(By.id("shippingFormHandlerSubmit"));
				SpecialAction.waitForElementCondition(driver, btnCONTINU, "click");
				
			} catch (Exception e) {
				Log.error("Exception in buttonShipContinue");
				Reporter.log("Exception in buttonShipContinue");
				throw(e);
			}
			
			return btnCONTINU;
		}
		
		public static WebElement enableButtonShipContinue(WebDriver driver) {
			WebElement btnCONTINU =null;
			try {
				WebElement ele = driver.findElement(By.xpath("//*[@id='shippingAddressForm']/div[16]/div"));
				btnCONTINU = ele.findElement(By.id("shippingFormHandlerSubmit"));
				SpecialAction.mouseAction(driver, btnCONTINU).perform();
			} catch (Exception e) {
				Log.error("Exception in buttonShipContinue");
				Reporter.log("Exception in buttonShipContinue");
				throw(e);
			}
			return btnCONTINU;
		}
		public static WebElement linkChangeShipAdd(WebDriver driver) {
			try {
				WebElement linkChange = driver.findElement(By.id("changeShippingAddress"));
				SpecialAction.waitForElementCondition(driver, linkChange, "click");
				return linkChange;
			} catch (Exception e) {
				Log.error("Exception in linkChangeShipAdd");
				Reporter.log("Exception in linkChangeShipAdd");
				throw(e);
			}
			
		}
		
		public static WebElement chkBxPrimaryShip(WebDriver driver) {
			WebElement area = null;
			WebElement element = null;
			try {		
				area = driver.findElement(By.xpath("//*[@id='shippingAddressForm']/div[15]/div"));
				element = area.findElement(By.id("set_as_primary_shipping"));
				SpecialAction.mouseAction(driver, element).perform();
			} catch (Exception e) {
				Log.error("ERROR DETAIL- /n Page:Confirmation /n Section:ShipAddress /n Element: Primary Ship Address Checkbox for new address \n FindElementMethod:By.id- "+element.toString());
				Reporter.log("ERROR DETAIL- /n Page:Confirmation /n Section:ShipAddress /n Element: Primary Ship Address Checkbox for new address \n FindElementMethod:By.id- "+element.toString());
				throw(e);
			}
			return element;
		}
		
		public static WebElement btnEDITShipAdd(WebDriver driver) {
			element = null;
			try {
				element = driver.findElement(By.xpath("//*[@id='page-content']/div[1]/div[2]/div[2]/div/div[1]/button"));
				if (element.isEnabled()) {
					element.click();
				}
			} catch (Exception e) {
					        
			}
			return element;
			
		}
	}
	
	
	public static class chooseShippingAddress {
		public static WebElement selectDiffAddr(WebDriver driver) throws InterruptedException {
			Actions builder= new Actions(driver);
			Action act= builder.moveToElement(driver.findElement(By.xpath("//*[@id='account-addresses-return-visitor']/div[2]"))).build();
			act.perform();
			Thread.sleep(2000);
			WebElement addr = driver.findElement(By.id("account-addresses-return-visitor"));
			List<WebElement> listAddr = addr.findElements(By.className("radio-button"));
			System.out.println("total radiobutton::"+listAddr.size());
			if (listAddr.size()>1) {
				element = driver.findElement(By.xpath("//*[@id='account-addresses-return-visitor']/div[2]/ul/li[2]/div[1]/input"));
			}
			return element;
		}
		
		public static WebElement btnUpdate(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='account-addresses-return-visitor']/div[3]/div[2]/button"));
			return element;
		}
		
		public static WebElement btnADDNEW(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='account-addresses-return-visitor']/div[3]/div[1]/button"));
			return element;
		}
		
	}
	
	public static class ShipAddrValidationSection {
		public static WebElement buttonNO(WebDriver driver) {
			try {
				WebElement btnNo = driver.findElement(By.xpath("//*[@id='simplePopup']/div[2]/form/div[3]/button[2]"));
				SpecialAction.waitForElementCondition(driver, btnNo, "visible");
				return btnNo;
			} catch (Exception e) {
				Log.error("Exception in buttonNO");
				Reporter.log("Exception in buttonNO");
				throw(e);
			}
		}
		
		public static WebElement buttonYES(WebDriver driver) {
			WebElement btnYes =driver.findElement(By.xpath("//*[@id='simplePopup']/div[2]/form/div[3]/button[1]"));
			SpecialAction.waitForElementCondition(driver, btnYes, "visible");
			return btnYes;
		}			
	}
	
    public static class PaymentSection {
		public static WebElement cardFirstName(WebDriver driver) {
			WebElement cardFirstNameField = driver.findElement(By.id("pymt_cardname"));
			return cardFirstNameField;
		}
		
		public static WebElement cardLastName(WebDriver driver) {
			WebElement cardLastNameField = driver.findElement(By.id("last_name"));
			return cardLastNameField;
		}
		
		public static WebElement cardNumber(WebDriver driver) {
			WebElement cardFirstNameField = driver.findElement(By.xpath("//*[@id='paymentMethodForm']/fieldset/div[3]/div/div/input[1]"));
			return cardFirstNameField;
		}
		
		public static Select cardExpMonth(WebDriver driver) {
			WebElement cardExMonth = driver.findElement(By.id("credit-card-month"));
			return SpecialAction.selectList(cardExMonth);
			//return cardExMonth;
		}
		
		public static Select cardExpYear(WebDriver driver) {
			WebElement cardExpYear = driver.findElement(By.id("credit-card-year"));
			return (SpecialAction.selectList(cardExpYear));
		}
		
		public static WebElement cardSecurityCode(WebDriver driver) {
			WebElement cardSecurityCode = driver.findElement(By.id("sec_code_new"));
			return cardSecurityCode;
		}
		
		public static WebElement securityCode(WebDriver driver) {
			WebElement cardSecurityCode = driver.findElement(By.id("security_code"));
			return cardSecurityCode;
		}
		
		public static WebElement buttonPayContinue(WebDriver driver) {
			WebElement btnContinue = driver.findElement(By.id("applyPaymentGroups"));
			SpecialAction.waitForElementCondition(driver, btnContinue, "visible");
			return btnContinue;
		}
		
		public static WebElement btnSubmitOrderExistingBill(WebDriver driver) {
			WebElement btnSubmitOrder = driver.findElement(By.xpath("//*[@id='existingPaymentMethodForm']/div[3]/fieldset/div[6]/div/input"));
			SpecialAction.waitForElementCondition(driver, btnSubmitOrder, "visible");
			return btnSubmitOrder;
		}
		
		public static WebElement btnSubmitOrderNewBillAdd(WebDriver driver) {
			WebElement btnSubmitOrder = driver.findElement(By.id("applyPaymentGroups"));
			SpecialAction.waitForElementCondition(driver, btnSubmitOrder, "visible");
			return btnSubmitOrder;
		}
		
		public static WebElement linkChangeBillAdd(WebDriver driver) {
			try {
				WebElement linkChange = driver.findElement(By.className("return-visitor-change-address-btn"));
				SpecialAction.waitForElementCondition(driver, linkChange, "click");
				return linkChange;
			} catch (Exception e) {
				Log.error("Exception in linkChangeShipAdd");
				Reporter.log("Exception in linkChangeShipAdd");
				throw(e);
			}
			
		}
		
		public static WebElement chkBxPrimaryPayment(WebDriver driver) {
			WebElement area = null;
			WebElement element = null;
			try {		
				area = driver.findElement(By.xpath("//*[@id='paymentMethodForm']/fieldset/div[10]/div"));
				element = area.findElement(By.id("set_as_primary_payment"));
				SpecialAction.mouseAction(driver, element).perform();
			} catch (Exception e) {
				Log.error("ERROR DETAIL- /n Page:Confirmation /n Section:ShipAddress /n Element: Primary Ship Address Checkbox for new address \n FindElementMethod:By.id- "+element.toString());
				Reporter.log("ERROR DETAIL- /n Page:Confirmation /n Section:ShipAddress /n Element: Primary Ship Address Checkbox for new address \n FindElementMethod:By.id- "+element.toString());
				throw(e);
			}
			return element;
		}
		
		
		public static WebElement privacyPolicyArea(WebDriver driver) {
			WebElement area = driver.findElement(By.xpath("//*[@id='paymentMethodForm']/fieldset/fieldset/div[1]/div"));
    		WebElement privacyPolicy = area.findElement(By.id("chk_policies_comp"));
    		return privacyPolicy;
    	}
	
	}
    
    public static class BillAddrSection {
		public static WebElement addDiffBillAddr(WebDriver driver) throws InterruptedException {
			WebElement diffBillaread = driver.findElement(By.xpath("//*[@id='paymentMethodForm']/fieldset/div[8]/div"));  //finding area of diff bill address checkbox
			SpecialAction.mouseAction(driver, diffBillaread).perform();
			System.out.println("CLICKED1 bill address checkbox");
			WebElement checkBoxDiffBillAdd = driver.findElement(By.xpath("//*[@id='paymentMethodForm']/fieldset/div[8]/div/label"));
			SpecialAction.mouseAction(driver, checkBoxDiffBillAdd).perform();
			System.out.println("CLICKED2 bill address checkbox");
			Thread.sleep(2000);
			WebElement diffBillCheckbox = driver.findElement(By.id("billing-different"));
			return diffBillCheckbox;
		}
		

		
		
		public static WebElement billAddr1(WebDriver driver) {
			WebElement billAddr1 = driver.findElement(By.id("billing-address"));
			return billAddr1;
		}
		
		public static WebElement openBillAddr2(WebDriver driver) {
			WebElement openBillAddr2 = driver.findElement(By.xpath("//*[@id='postfix-container-generic-checkout']/div/div[2]/label/a"));
			return openBillAddr2;
		}
		
		public static WebElement billAddr2(WebDriver driver) {
			WebElement billAddr2 = driver.findElement(By.id("billing-address2"));
			return billAddr2;
			//return cardExMonth;
		}
		
		public static Select billState(WebDriver driver) {
			WebElement cardExpYear = driver.findElement(By.id("billing-state-province"));
			return SpecialAction.selectList(cardExpYear);
		}
		
		public static WebElement billCity(WebDriver driver) {
			WebElement billCity = driver.findElement(By.id("billing-city"));
			return billCity;
		}
		
		public static WebElement billZipCode(WebDriver driver) {
			WebElement billZipCode = driver.findElement(By.id("billing-zip-postal-code"));
			return billZipCode;
		}
		
		public static WebElement billPhone(WebDriver driver) {
			WebElement billZipCode = driver.findElement(By.id("billing-phone"));
			return billZipCode;
		}
		
		public static WebElement buttonNO(WebDriver driver) {
			WebElement btnNo = driver.findElement(By.xpath("//*[@id='simplePopup']/div[2]/form/div[3]/button[2]"));
			SpecialAction.waitForElementCondition(driver, btnNo, "visible");
			return btnNo;
		}
	
	}
    
    public static class AccountSection {
    	public static WebElement email(WebDriver driver) {
    		WebElement emailId = driver.findElement(By.id("email"));
    		return emailId;
    	}
    	
    	public static WebElement confirmEmail(WebDriver driver) {
    		WebElement confirmEmailId = driver.findElement(By.id("email_confirm"));
    		return confirmEmailId;
    	}
    	
    	public static WebElement password(WebDriver driver) {
    		WebElement password = driver.findElement(By.id("password"));
    		return password;
    	}
    	
    	public static WebElement confirmPassword(WebDriver driver) {
    		WebElement confirmPassword = driver.findElement(By.id("password_confirm"));
    		return confirmPassword;
    	}
    	
    	public static Select birthMonth(WebDriver driver) {
    		WebElement birthMonth = driver.findElement(By.className("birth_month"));
    		birthMonth.click();
    		return SpecialAction.selectList(birthMonth);
    	}
    	
    	public static Select birthDay(WebDriver driver) {
    		WebElement birthDay = driver.findElement(By.className("birth_day"));
    		birthDay.click();
    		return SpecialAction.selectList(birthDay);
    	}
    	
    	public static Select birthYear(WebDriver driver) {
    		WebElement birthYear = driver.findElement(By.className("birth_year"));
    		birthYear.click();
    		return SpecialAction.selectList(birthYear);
    	}
    	
    	public static Action genderMale(WebDriver driver) {
    		WebElement genderMale = driver.findElement(By.id("gender_male"));
    		return SpecialAction.mouseAction(driver, genderMale);
    	}
    	
    	public static Action genderFemale(WebDriver driver) {
    		WebElement genderFemale = driver.findElement(By.id("gender_female"));
    		return SpecialAction.mouseAction(driver, genderFemale);
    	}
    	
    	public static WebElement noCoach(WebDriver driver) {
    		WebElement noCoach = driver.findElement(By.className("radio-no-before"));
    		return noCoach;
    	}
    	
    	public static WebElement privacyPolicy(WebDriver driver) {
    		WebElement privacyPolicy = driver.findElement(By.id("chk_policies_comp"));
    		return privacyPolicy;
    	}
    	
    	public static WebElement buttonSubmitORDER(WebDriver driver) {
    		WebElement submitORDER = driver.findElement(By.name("submitRegister"));
    		SpecialAction.waitForElementCondition(driver, submitORDER, "click");
    		return submitORDER;
    	}
    	
    	
    	
    
	}
}
