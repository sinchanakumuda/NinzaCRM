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

public class CreateCampaignWithStatus {



public static void main(String[] args) throws Throwable { 
	
	  // To disable the  popups
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>(); 
			prefs.put("profile.password_manager_leak_detection", false); 
			settings.setExperimentalOption("prefs", prefs); //key:prefs 
			
	
	
	  PropertiesFileUtility plib = new PropertiesFileUtility(); 
	  ExcelFileUtility elib = new ExcelFileUtility(); 
	  WebDriverUtility wlib=new WebDriverUtility(); 
	  JavaUtility jlib=new JavaUtility();
	   
	  // Reading data from properties file 
	  String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
	  String URL = plib.togetDataFromPropertiesFile("Url"); 
	  String USERNAME = plib.togetDataFromPropertiesFile("Username"); 
	  String PASSWORD = plib.togetDataFromPropertiesFile("Password"); 
	  
	  System.out.println(BROWSER);
	  System.out.println(URL);
	  System.out.println(USERNAME);
	  System.out.println(PASSWORD);
	  
	 
	  // reading data from excel 
	  String CAMPAIGN_NAME = elib.toReadDataFromExcelFile("Campaign", 4, 2); 
	  String TARGET_SIZE = elib.toReadDataFromExcelFile("Campaign", 4, 3); 
	  String STATUS = elib.toReadDataFromExcelFile("Campaign", 4, 4); 
	 
	  System.out.println("campaign name " +CAMPAIGN_NAME); 
	   System.out.println("campaign target "+TARGET_SIZE);
	   System.out.println("campaign target "+STATUS);
	   String campNameWithRandom=CAMPAIGN_NAME+jlib.togetRandomAlpha();
	   System.out.println(campNameWithRandom);
	   
	  
	  
	  
	  WebDriver driver = null; 
	  if (BROWSER.equals("edge")) { 
	   driver = new EdgeDriver(); 
	  } else if (BROWSER.equals("Chrome")) { 
	   driver = new ChromeDriver(settings); 
	  } else if (BROWSER.equals("firefox")) { 
	   driver = new FirefoxDriver(); 
	  } 
	  driver.manage().window().maximize(); 
	  wlib.waitForPageToLoad(driver); 
	  driver.get(URL); 
	  driver.findElement(By.id("username")).sendKeys(USERNAME); 
	 
	 driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD); 
	 
	  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
	  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
	 
	 driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom); 
	 
	 driver.findElement(By.name("campaignStatus")).sendKeys(STATUS); 
	  WebElement size = driver.findElement(By.name("targetSize")); 
	  size.clear(); 
	  size.sendKeys(TARGET_SIZE); 
	  driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
	  
	
	  
	  WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
	  wlib.waitForVisibilityOfElement(driver, toastmsg); 
	  String msg = toastmsg.getText(); 
	 
	  if (msg.contains(campNameWithRandom)) { 
	   System.out.println("campaign created"); 
	  } 
	 
	  else { 
	   System.out.println("campaign not created"); 
	  } 
	  driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
	 
	  // logout 
	  WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
	  wlib.mouseHoverOnWebElement(driver, icon); 
	  WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']")); 
	  wlib.clickOnWebElement(driver, logout); 
	 

	 
	  // close browser 
	  driver.quit(); 
	 
	  
	  
	 } 
	 
	} 
	 
	 
