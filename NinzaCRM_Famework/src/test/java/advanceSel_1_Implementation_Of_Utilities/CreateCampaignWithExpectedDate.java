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


public class CreateCampaignWithExpectedDate {
	
	public static void main(String[] args) throws Throwable
	{

	
	PropertiesFileUtility putil = new PropertiesFileUtility(); 
	  ExcelFileUtility eutil = new ExcelFileUtility(); 
	  JavaUtility jutil = new JavaUtility(); 
	  WebDriverUtility wutil = new WebDriverUtility(); 
	 
	 
	  String BROWSER = putil.togetDataFromPropertiesFile("Browser"); 
	  String URL = putil.togetDataFromPropertiesFile("Url"); 
	  String USERNAME = putil.togetDataFromPropertiesFile("Username"); 
	  String PASSWORD = putil.togetDataFromPropertiesFile("Password"); 
	  
	  System.out.println(BROWSER);
	  System.out.println(URL);
	  System.out.println(USERNAME);
	  System.out.println(PASSWORD);
	  
	 
	  String campname = eutil.toReadDataFromExcelFile("Campaign", 7, 2); 
	 String target = eutil.toReadDataFromExcelFile("Campaign", 7, 3); 
	 String campNameWithRandom=campname+jutil.togetRandomAlpha();
	 System.out.println("campaign name " +campNameWithRandom); 
	   System.out.println("campaign target "+target); 
	 
	   /*ChromeOptions options = new ChromeOptions();
	   options.addArguments("--disable-save-password-bubble");
	   options.addArguments("--disable-password-manager");*/
	  

	 
	  WebDriver driver = null; 
	 
	  if (BROWSER.equals("Edge")) { 
		  driver = new EdgeDriver(); 
	  } else if (BROWSER.equals("Chrome")) {
		  // To disable the  popups
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>(); 
			prefs.put("profile.password_manager_leak_detection", false); 
			settings.setExperimentalOption("prefs", prefs); //key:prefs 
			
			
			
	   driver = new ChromeDriver(settings); 
	  } else if (BROWSER.equals("Firefox")) { 
	   driver = new FirefoxDriver(); 
	  } 
	  wutil.maximize(driver);
	  wutil.waitForPageToLoad(driver); 
	  driver.get(URL);
	  //wutil.waitForPageToLoad(driver); 
	  
	   
	  driver.findElement(By.id("username")).sendKeys(USERNAME); 
	  driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD); 
	  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
	 
	 String daterequired = jutil.togetRequiredDate(30);
	 System.out.println("Required Date " +daterequired); 
	 
	 
	  // create campaign 
	  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
	  
	  /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	  System.out.println(alert.getText());
	  alert.accept();*/

	  
	  
	  
	  driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom); 
	  WebElement size = driver.findElement(By.name("targetSize")); 
	  size.clear(); 
	  size.sendKeys(target); 
	 
	  WebElement expClosedate = driver.findElement(By.name("expectedCloseDate")); 
	  wutil.passInput(driver, expClosedate, daterequired); 
	 
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
	//div[@class='user-icon
	  wutil.clickOnWebElement(driver,logout); 
	 
	  driver.quit();
	  

}
}
