package testcases;



import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class invalidLoginConver1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.netshoes.com.br/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testInvalidLoginConver1() throws Exception {
    // Author:Nirmala Ingale
    // TestCaseName:: InvalidLogin
    // TestCaseSummary: : Enter valid email address and invalid password
    // Last Update: 12-21-2015
    // TestCaseStatus : Working fine
    // NS SmokeTestCase.No:: TC-1.1
    boolean LoggedOut1 = isElementPresent(By.cssSelector(".holder-sub-menu-my-account a[href=\"/account/login.jsp\"]"));
    // ERROR: Caught exception [unknown command [gotoIf]]
    boolean SairExists = isElementPresent(By.cssSelector(".logout"));
    // ERROR: Caught exception [unknown command [gotoIf]]
    driver.get(baseUrl + "/?");
    boolean LoggedOut = isElementPresent(By.cssSelector(".holder-sub-menu-my-account a[href=\"/account/login.jsp\"]"));
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [label]]
    driver.findElement(By.cssSelector(".logout")).click();
    // ERROR: Caught exception [unknown command [label]]
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector(".holder-sub-menu-my-account a[href=\"/account/login.jsp\"]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector(".holder-sub-menu-my-account a[href=\"/account/login.jsp\"]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.id("email-cpf")).isDisplayed()) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("email-cpf")).clear();
    driver.findElement(By.id("email-cpf")).sendKeys("nc1@oe.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("99999999");
    driver.findElement(By.cssSelector("#sendFormRegistered [type=submit]")).click();
    try {
      assertTrue(driver.findElement(By.cssSelector(".login-wrapper>div+p+p")).getText().matches("^Ops! Usuário não encontrado[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(driver.getCurrentUrl().matches("^[\\s\\S]*account/login\\.jsp[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
