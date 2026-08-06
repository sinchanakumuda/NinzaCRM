package advanceSel_2_POM_Implementations;
import pomClasses.LoginPage;
import pomClasses.HomePage;
import pomClasses.CampaignPage;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;

public class CreateCampaignWithMandatoryFields {
	
	public static void main(String[] args) throws Throwable { 
		 
		  
		  PropertiesFileUtility plib = new PropertiesFileUtility(); 
		  ExcelFileUtility elib = new ExcelFileUtility(); 
		  JavaUtility jlib = new JavaUtility(); 
		  WebDriverUtility wlib = new WebDriverUtility(); 
		 
		// Reading data from Properties file 
		  String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
		  String URL = plib.togetDataFromPropertiesFile("Url"); 
		  String USERNAME = plib.togetDataFromPropertiesFile("Username"); 
		  String PASSWORD = plib.togetDataFromPropertiesFile("Password"); 
		 
		  // Read data from excel 
		  String campname = elib.toReadDataFromExcelFile("Campaign", 1, 2); 
		  String size = elib.toReadDataFromExcelFile("Campaign", 1, 3); 
		  
		  System.out.println(campname);
		  System.out.println(size);
		  
		  
		  
		  String campNameWithRandom=campname+jlib.togetRandomAlpha();
		  System.out.println(campNameWithRandom);
		 
		  WebDriver driver = null; 
		 
		  if (BROWSER.equals("Edge")) { 
		   driver = new EdgeDriver(); 
		  } else if (BROWSER.equals("Chrome")) 
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
		  driver.manage().window().maximize(); 
		  wlib.waitForPageToLoad(driver); 
		  driver.get(URL); 
		  
		  //Implementing POM Classes
		  //Implementing Login POM Class
		  LoginPage lp = new LoginPage(driver); 
		  lp.getUsername().sendKeys(USERNAME); 
		  lp.getPassword().sendKeys(PASSWORD); 
		  lp.getLoginBtn().click(); 
		 
		  //Implementing Home POM Class
		  // click on create campaign button 
		  HomePage hp = new HomePage(driver); 
		  hp.getCreatecampaignBtn().click(); 
		 
		  // enter mandatory fields 
		  //Implementing Campaign POM Class
		  CampaignPage cp = new CampaignPage(driver); 
		  cp.getCampaignNameTF().sendKeys(campNameWithRandom); 
		  cp.getTargetSizeTF().sendKeys(size); 
		  cp.getCreateCampaignSubmitBtn().click(); 
		 
		  // validation 
		  WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
		  wlib.waitForVisibilityOfElement(driver, toastmsg); 
		  String msg = toastmsg.getText(); 
		  System.out.println(msg);
		 
		  if (msg.contains(campNameWithRandom)) { 
		   System.out.println("campaign created"); 
		  }
		  
		  else { 
		   System.out.println("campaign not created"); 
		  } 
		  driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
		 
		  // logout 
		  hp.getUserIcon().click(); 
		  hp.getLogOutBtn().click(); 
		  driver.quit(); 
		 
		 }

}
