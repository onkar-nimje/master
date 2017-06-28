package config;

import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Log;

public class ObjectType {
	public static String objectValue;
	public static By getObject (WebDriver driver, String objectName, String objectType ) throws Exception {
		try {
			objectValue=OR.getProperty(objectName);			
			System.out.println("objectType:"+objectType+", objectName:"+objectName+", objectValue:"+objectValue);
			Log.info("objectType:"+objectType+", objectName:"+objectName+", objectValue:"+objectValue);
			
			if(objectType.equalsIgnoreCase("XPATH")) {
				return By.xpath(objectValue);
			}
			
			//find by class
			else if(objectType.equalsIgnoreCase("CLASSNAME")){
				return By.className(objectValue);
				
			}
			//find by name
			else if(objectType.equalsIgnoreCase("NAME")){
				return By.name(objectValue);
				
			}
			
			//Find by css
			else if(objectType.equalsIgnoreCase("CSS")){
				return By.cssSelector(objectValue);
				
			}
			//find by link
			else if(objectType.equalsIgnoreCase("LINKTEXT")){
				return By.linkText(objectValue);
				
			}
			//find by partial link
			else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")){
				return By.partialLinkText(objectValue);
				
			}
            			
			else if(objectType.equalsIgnoreCase("ID")){
					return By.id(objectValue);
				}
			
			else if(objectType.equalsIgnoreCase("tagName")){
					return By.tagName(objectValue);
				}
				{	
				Log.info("Wrong object type");
				throw new Exception("Wrong object type");
				}
			
		} catch (Exception e) {
			throw(e);
		}
	}

}
