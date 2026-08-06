package advanceSel_1_Implementation_Of_Utilities;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.HashMap;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;

public class CreateProduct {
	public static void main(String[] args) throws Throwable 
	{
	ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs); 
	

	PropertiesFileUtility putil = new PropertiesFileUtility(); 
	  ExcelFileUtility eutil = new ExcelFileUtility(); 
	  JavaUtility jutil = new JavaUtility(); 
	  WebDriverUtility wutil = new WebDriverUtility(); 
	 
	  String BROWSER = putil.togetDataFromPropertiesFile("Browser"); 
	  String URL = putil.togetDataFromPropertiesFile("Url"); 
	  String USERNAME = putil.togetDataFromPropertiesFile("Username"); 
	  String PASSWORD = putil.togetDataFromPropertiesFile("Password"); 
	 
	  // actual script 
	  WebDriver driver = null; 
	 
	  if (BROWSER.equals("Edge")) { 
	 
	   driver = new EdgeDriver(); 
	  } else if (BROWSER.equals("Chrome")) { 
	   driver = new ChromeDriver(settings); 
	  } else if (BROWSER.equals("Firefox")) { 
	   driver = new FirefoxDriver(); 
	  } 
	  driver.manage().window().maximize(); 
	
	  wutil.waitForPageToLoad(driver); 

	  driver.get(URL); 
	  //Login
	  driver.findElement(By.id("username")).sendKeys(USERNAME); 
	  driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD); 
	  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
	 
	  String prodname = eutil.toReadDataFromExcelFile("Product", 1, 2); 
	  String quantity1 = eutil.toReadDataFromExcelFile("Product", 1, 3); 
	  String price1 = eutil.toReadDataFromExcelFile("Product", 1, 4); 
	  
	  System.out.println("prodname "+prodname);
	  System.out.println("quantity "+quantity1);
	  System.out.println("price1 "+price1);
	  
	  String prodNameWithRandomNum=prodname + jutil.togetRandomCount();
	 
	  // enter details 
	  driver.findElement(By.linkText("Products")).click(); 
	  driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click(); 
	 
	  driver.findElement(By.name("productName")).sendKeys(prodNameWithRandomNum); 
	 
	  // Dropdown 1 
	  WebElement categorydropdown = driver.findElement(By.name("productCategory")); 
	  wutil.select(categorydropdown, 3); 
	  WebElement quantity = driver.findElement(By.name("quantity")); 
	  quantity.clear(); 
	  quantity.sendKeys(quantity1); 
	 
	  //price
	  WebElement priceEle = driver.findElement(By.name("price")); 
	  System.out.println(price1);
	  priceEle.clear(); 
	  priceEle.sendKeys(price1); 
	  
	  // DropDown 2 
	WebElement vendordropdown = driver.findElement(By.name("vendorId")); 
	wutil.select(vendordropdown,"VID_001"); 
	
	driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click(); 
	Thread.sleep(2000); 
	driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
	
	
	  
	wutil.waitForPageToLoad(driver); 
	   WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
	   
	
	   
	  wutil.waitForVisibilityOfElement(driver, toastmsg); 
	  String msg = toastmsg.getText(); 
	 
	  if (msg.contains(prodNameWithRandomNum)) { 
	   System.out.println("product created"); 
	  } 
	 
	  else { 
	   System.out.println("product not created"); 
	  } 
	  driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
	  
	  
	
	 
	  
	  
	  
	
	// logout 
	WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
	wutil.mouseHoverOnWebElement(driver, icon); 
	WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']")); 
	wutil.clickOnWebElement(driver, logout); 
	driver.quit();
}
}
