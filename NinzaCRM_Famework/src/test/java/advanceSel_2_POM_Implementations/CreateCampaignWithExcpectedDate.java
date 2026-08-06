package advanceSel_2_POM_Implementations;

import pomClasses.LoginPage;
import pomClasses.HomePage;
import pomClasses.CampaignPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CreateCampaignWithExcpectedDate {
	
	public static void main(String[] args) throws Throwable {
		
		
	  PropertiesFileUtility plib = new PropertiesFileUtility(); 
	  ExcelFileUtility elib = new ExcelFileUtility(); 
	  JavaUtility jlib = new JavaUtility(); 
	  WebDriverUtility wlib=new WebDriverUtility(); 
	 
	  // Reading data from Properties file -Common Data
	  String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
	  String URL = plib.togetDataFromPropertiesFile("Url"); 
	  String USERNAME = plib.togetDataFromPropertiesFile("Username"); 
	  String PASSWORD = plib.togetDataFromPropertiesFile("Password"); 
	 
	  // Read data from excel Product Specific Data
	  String campname = elib.toReadDataFromExcelFile("Campaign", 7, 2); 
	  String size = elib.toReadDataFromExcelFile("Campaign", 7, 3); 
	 
	  //RandomAlpha
	  String campNameWithRandom=campname+jlib.togetRandomAlpha();
	  String expectedDate = jlib.togetRequiredDate(30); 
	 
	  WebDriver driver = null; 
	 
	  if (BROWSER.equals("Edge")) { 
	   driver = new EdgeDriver(); 
	  } 
	  else if (BROWSER.equals("Chrome")) 
	  { 
		ChromeOptions settings=new ChromeOptions();  
		Map<String,Object> prefs=new HashMap<String, Object>();
		prefs.put("profile.password_manager_leak_detection",false);
		settings.setExperimentalOption("prefs",prefs); 
	   driver = new ChromeDriver(settings); 
	  }
	  else if (BROWSER.equals("Firefox")) { 
	   driver = new FirefoxDriver(); 
	  } 
	 
	  // login action 
	 wlib.maximize(driver);
	        wlib.waitForPageToLoad(driver); 
	  driver.get(URL); 
	  
	  //LoginPagePOM
	  LoginPage lp=new LoginPage(driver); 
	  lp.getUsername().sendKeys(USERNAME); 
	  lp.getPassword().sendKeys(PASSWORD); 
	  lp.getLoginBtn().click(); 
	 
	  // create campaign 
	  HomePage hp=new HomePage(driver); 
	  hp.getCreatecampaignBtn().click();
	  
	  CampaignPage cp=new CampaignPage(driver); 
	  cp.getCampaignNameTF().sendKeys(campNameWithRandom); 
	  cp.getTargetSizeTF().sendKeys(size); 
	  Thread.sleep(2000); 
	 
	        wlib.passInput(driver,cp.getExpectedCloseDateTF(),expectedDate); 
	        cp.getCreateCampaignSubmitBtn().click(); 
	 
	        // validation 
	        WebElement toastmsg = hp.getToastmsg();
	        wlib.waitForVisibilityOfElement(driver, toastmsg); 
	        String msg = toastmsg.getText(); 
	 
	  if (msg.contains(campNameWithRandom)) { 
	   System.out.println("campaign created"); 
	  } else { 
	   System.out.println("campaign not created"); 
	  } 
	  //WebElement of close X icon
	  hp.getCloseMsg(); 
	 
	 
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("Toastify__toast")));
	  //driver.findElement(By.className("user-icon")).click();

	  
	  // logout 
	  System.out.println("logout from app ");
		

	  WebElement profile=hp.getUserIcon();
	  wlib.clickOnWebElement(driver, profile);
	  hp.getLogOutBtn().click(); 
	 
	  
	  driver.quit(); 
	 
	 } 
	 


}
