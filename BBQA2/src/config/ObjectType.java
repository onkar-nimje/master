package config;

import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObjectType {
	public static By getObject (WebDriver driver, String objectName, String objectType ) throws Exception {
		try {
			System.out.println("I m in OBJECT CLASS objectType:::"+objectType);
			System.out.println("I m in OBJECT CLASS objectName:::"+objectName);
			if(objectType.equalsIgnoreCase("XPATH")) {
				return By.xpath(OR.getProperty(objectName));
			}
			
			//find by class
			else if(objectType.equalsIgnoreCase("CLASSNAME")){
				
				return By.className(OR.getProperty(objectName));
				
			}
			//find by name
			else if(objectType.equalsIgnoreCase("NAME")){
				
				return By.name(OR.getProperty(objectName));
				
			}
			
			//Find by css
			else if(objectType.equalsIgnoreCase("CSS")){
				
				return By.cssSelector(OR.getProperty(objectName));
				
			}
			//find by link
			else if(objectType.equalsIgnoreCase("LINKTEXT")){
				
				return By.linkText(OR.getProperty(objectName));
				
			}
			//find by partial link
			else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")){
				
				return By.partialLinkText(OR.getProperty(objectName));
				
			}
            
			
			else if(objectType.equalsIgnoreCase("ID")){
					//System.out.println("in ObjectType class::"+objectName+objectType+OR.getProperty(objectName));
					return By.id(OR.getProperty(objectName));
				}else
				{			
				throw new Exception("Wrong object type");
				}
			
		} catch (Exception e) {
			throw(e);
		}
	}

}
