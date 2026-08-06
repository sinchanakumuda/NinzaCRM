package config_BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;



import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import junit.framework.Assert;
import pomClasses.HomePage;
import pomClasses.LoginPage;


import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.HashMap;

//@Listeners → This tells TestNG to attach a "listener" class to your test.
@Listeners(listener.ListenerImplementation.class)

//(package name + class name)
/*listener.ListenerImplementation.class → This is the fully qualified name of your listener class (package name + class name). 
 * It points to the custom class you wrote that implements TestNG’s listener interfaces.
 */

public class BaseClass {
	
	 public PropertiesFileUtility plib = new PropertiesFileUtility(); 
	 
	 public JavaUtility jlib = new JavaUtility(); 
	 public WebDriverUtility wlib=new WebDriverUtility();
	 
	 public WebDriver driver = null; 
	 public static WebDriver sdriver=null; //listener
	 
	
	/* //Code for cross browser testing
	@Parameters("BROWSER")
	@BeforeClass(groups="smoketest")
	public void beforclass(String browser) throws Throwable
	{ 
		Reporter.log("Opening the browser for cross browser -> beforeclass",true);
		 
		 	String BROWSER=browser;
		  // Reading data from Properties file not needed because cross browser testing
		 //String BROWSER = plib.togetDataFromPropertiesFile("Browser");
		
	
		  
	
		 
		  if (BROWSER.equals("Edge")) { 
		   driver = new EdgeDriver(); 
		  } else if (BROWSER.equals("Chrome")) { 
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>(); 
				prefs.put("profile.password_manager_leak_detection", false); 
				settings.setExperimentalOption("prefs", prefs); //key:prefs  
			  
		   driver = new ChromeDriver(settings); 
		  } else if (BROWSER.equals("Firefox")) { 
		   driver = new FirefoxDriver(); 
		  } 
		  sdriver=driver;
		  wlib.maximize(driver); 
	      wlib.waitForPageToLoad(driver); 
		
	}*/
	

/*
	 //Using Assertion for browser if else
	 @BeforeClass(groups="smoketest")
		public void beforclass() throws Throwable
		{ 
			  // Reading data from Properties file 
			    String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
				Reporter.log("Opening the browser -> beforeclass",true);
				System.out.println("Opening the browser -> beforeclass");
		
				
				if (BROWSER.equals("Edge"))
				 {
			            driver = new EdgeDriver();
			            Assert.assertTrue("Driver should be EdgeDriver", driver instanceof EdgeDriver);
			     } 
				else if (BROWSER.equals("Chrome")) 
				{
			            ChromeOptions settings = new ChromeOptions();
			            Map<String, Object> prefs = new HashMap<String, Object>();
			            prefs.put("profile.password_manager_leak_detection", false);
			            settings.setExperimentalOption("prefs", prefs);

			            driver = new ChromeDriver(settings);
			            Assert.assertTrue("Driver should be ChromeDriver", driver instanceof ChromeDriver);
			    } else if (BROWSER.equals("Firefox")) 
			    {
			            driver = new FirefoxDriver();
			            Assert.assertTrue("Driver should be FirefoxDriver", driver instanceof FirefoxDriver);
			    } else 
			    {
			            Assert.fail("Unsupported browser: " + BROWSER);
			    }

			
			  
			 
			  sdriver=driver;
			  wlib.maximize(driver); 
		      wlib.waitForPageToLoad(driver); 
			
		}
*/
	 
	//Maven reading from command prompt
		 @BeforeClass(groups="smoketest")
			public void beforclass() throws Throwable
			{ 
				  // Reading data from Properties file 
				   // String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
				   String BROWSER=System.getProperty("browser");
					Reporter.log("Opening the browser -> beforeclass",true);
					System.out.println("Opening the browser -> beforeclass");
			
					
					if (BROWSER.equals("Edge"))
					 {
				            driver = new EdgeDriver();
				            Assert.assertTrue("Driver should be EdgeDriver", driver instanceof EdgeDriver);
				     } 
					else if (BROWSER.equals("Chrome")) 
					{
				            ChromeOptions settings = new ChromeOptions();
				            Map<String, Object> prefs = new HashMap<String, Object>();
				            prefs.put("profile.password_manager_leak_detection", false);
				            settings.setExperimentalOption("prefs", prefs);

				            driver = new ChromeDriver(settings);
				            Assert.assertTrue("Driver should be ChromeDriver", driver instanceof ChromeDriver);
				    } else if (BROWSER.equals("Firefox")) 
				    {
				            driver = new FirefoxDriver();
				            Assert.assertTrue("Driver should be FirefoxDriver", driver instanceof FirefoxDriver);
				    } else 
				    {
				            Assert.fail("Unsupported browser: " + BROWSER);
				    }

				
				  
				 sdriver=driver;
				  
				  wlib.maximize(driver); 
			      wlib.waitForPageToLoad(driver); 
				
			}

	
	
	
	
	
	
	
	//Code for reading from properties file
	/*@BeforeClass(groups="smoketest")
	public void beforclass() throws Throwable
	{ 
		  // Reading data from Properties file 
		  String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
			Reporter.log("Opening the browser -> beforeclass",true);
	
		 
		  if (BROWSER.equals("Edge")) { 
		   driver = new EdgeDriver(); 
		  } else if (BROWSER.equals("Chrome")) { 
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>(); 
				prefs.put("profile.password_manager_leak_detection", false); 
				settings.setExperimentalOption("prefs", prefs); //key:prefs  
			  
		   driver = new ChromeDriver(settings); 
		   
		  } else if (BROWSER.equals("Firefox")) { 
		   driver = new FirefoxDriver(); 
		  } 
		  
		  sdriver=driver;
		  wlib.maximize(driver); 
	      wlib.waitForPageToLoad(driver); 
		
	}*/
	
	
	
	//Code for Listner
	/*@BeforeClass(groups="smoketest")
	public void beforclass(String browser) throws Throwable
	{ 
		  // Reading data from Properties file 
		  String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
		  
		Reporter.log("Opening the browser -> beforeclass",true);
		 
		  if (BROWSER.equals("Edge")) { 
		   driver = new EdgeDriver(); 
		  } else if (BROWSER.equals("Chrome")) { 
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>(); 
				prefs.put("profile.password_manager_leak_detection", false); 
				settings.setExperimentalOption("prefs", prefs); //key:prefs  
			  
		   driver = new ChromeDriver(settings); 
		  } 
		  else if (BROWSER.equals("Firefox")) { 
		   driver = new FirefoxDriver(); 
		  } 
		  sdriver=driver;
		  wlib.maximize(driver); 
	      wlib.waitForPageToLoad(driver); 
		
	}*/
	
	@AfterClass(groups="smoketest")
	public void afterclass()
	{
		Reporter.log("Closing the browser -> afterclass",true);
		driver.quit();
	}
	//login code
	@BeforeMethod(groups="smoketest")
	public void beforemethod() throws IOException
	{
		Reporter.log("login to app -> beforemethod",true);
		
		  String URL = plib.togetDataFromPropertiesFile("Url"); 
		  String USERNAME = plib.togetDataFromPropertiesFile("Username"); 
		  String PASSWORD = plib.togetDataFromPropertiesFile("Password");
		  driver.get(URL);
		
		 //LoginPagePM
		  LoginPage lp=new LoginPage(driver); 
		  lp.getUsername().sendKeys(USERNAME); 
		  lp.getPassword().sendKeys(PASSWORD); 
		  lp.getLoginBtn().click(); 
	}
	
	//logout code
	@AfterMethod(groups="smoketest")
	public void aftermethod()
	{
		Reporter.log("logout from app -> aftermethod",true);
		
		  HomePage hp=new HomePage(driver); 
		  WebElement profile=hp.getUserIcon();
		  wlib.clickOnWebElement(driver, profile);
		
		  hp.getLogOutBtn().click(); 
		 
	}
	

}
