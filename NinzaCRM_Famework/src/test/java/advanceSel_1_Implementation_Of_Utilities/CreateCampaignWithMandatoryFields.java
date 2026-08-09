package advanceSel_1_Implementation_Of_Utilities;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebElement;
import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		ChromeOptions options=new ChromeOptions();
		Map<String,Object> prefs=new HashMap<String, Object>();
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		
		
		  PropertiesFileUtility putil = new PropertiesFileUtility(); 
		  ExcelFileUtility eutil = new ExcelFileUtility(); 
		  JavaUtility jutil = new JavaUtility(); 
		  WebDriverUtility wutil = new WebDriverUtility(); 
		 
		  String BROWSER = putil.togetDataFromPropertiesFile("Browser"); 
		  String URL = putil.togetDataFromPropertiesFile("Url"); 
		  String USERNAME = putil.togetDataFromPropertiesFile("Username"); 
		  String PASSWORD = putil.togetDataFromPropertiesFile("Password"); 
		 
		  String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2); 
		  String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
		  
		  String campNameWithRandom=campname+jutil.togetRandomAlpha();
		  System.out.println(campNameWithRandom);
		 
		  WebDriver driver = null; 
		 
		  if (BROWSER.equals("Edge")) { 
		 
		   driver = new EdgeDriver(); 
		  } else if (BROWSER.equals("Chrome")) { 
		   driver = new ChromeDriver(options); 
		  } else if (BROWSER.equals("Firefox")) { 
		   driver = new FirefoxDriver(); 
		  } 
		  driver.manage().window().maximize(); 
		  wutil.waitForPageToLoad(driver); 
		  driver.get(URL); 
		 
		 
		  driver.findElement(By.id("username")).sendKeys(USERNAME); 
		  driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD); 
		  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
		  
	
		 
		  // create campaign 
		  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
		  driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom); 
		  System.out.println(campNameWithRandom);
		  wutil.waitForPageToLoad(driver); 
		  
		  WebElement size = driver.findElement(By.name("targetSize")); 
		  size.clear(); 
		  size.sendKeys(target); 
		  driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
		 
		  // validation 
		  WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
		  wutil.waitForVisibilityOfElement(driver, toastmsg); 
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
		  wutil.mouseHoverOnWebElement(driver, icon); 
		  WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']")); 
		  wutil.clickOnWebElement(driver, logout); 
		 
		  // close browser 
		  driver.quit(); 
		 
		 } 
		 
	

	}


