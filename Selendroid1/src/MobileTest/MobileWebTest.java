package MobileTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.selendroid.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.common.device.DeviceTargetPlatform;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;

public class MobileWebTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");

	    capa.setPlatformVersion(DeviceTargetPlatform.ANDROID17);

	    capa.setEmulator(true);

	    WebDriver driver = new SelendroidDriver(capa);

	    WebElement inputField = driver.findElement(By.id("my_text_field"));
	    Assert.assertEquals("true", inputField.getAttribute("enabled"));
	    inputField.sendKeys("Selendroid");
	    Assert.assertEquals("Selendroid", inputField.getText());

	    driver.quit();
	        

	}

}
