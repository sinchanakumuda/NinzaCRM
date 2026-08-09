package advanceSel_2_POM_Implementations;

//POM Import
import pomClasses.LoginPage;
import pomClasses.HomePage;
import pomClasses.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.HashMap;


public class CreateProductWithMandatoryFields {
	
	public static void main(String[] args) throws Throwable {
		
		PropertiesFileUtility plib=new PropertiesFileUtility(); 
	     ExcelFileUtility elib=new ExcelFileUtility(); 
	     JavaUtility jlib=new JavaUtility(); 
	     WebDriverUtility wlib=new WebDriverUtility(); 
	      
	     String BROWSER = plib.togetDataFromPropertiesFile("Browser"); 
	     String URL = plib.togetDataFromPropertiesFile("Url"); 
	     String USERNAME = plib.togetDataFromPropertiesFile("Username"); 
	     String PASSWORD = plib.togetDataFromPropertiesFile("Password"); 
	 
	    String productName = elib.toReadDataFromExcelFile("Product", 1, 2); 
	    String quantity = elib.toReadDataFromExcelFile("Product", 1, 3); 
	    String price = elib.toReadDataFromExcelFile("Product", 1, 4); 
	    
	    System.out.println("prodName "+productName);
	    System.out.println("quantity "+quantity);
	    System.out.println("price "+price);
	     
	    String productnameWithAlphabet=productName+jlib.togetRandomAlpha();
	    
	 
	 
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
	 
	    LoginPage lp=new LoginPage(driver); 
	    lp.getUsername().sendKeys(USERNAME); 
	    lp.getPassword().sendKeys(PASSWORD); 
	    lp.getLoginBtn().click(); 
	 
	                HomePage hp=new HomePage(driver); 
	                hp.getProduct().click(); 
	                
	                Product ap=new Product(driver); 
	                ap.getAddProduct().click(); 
	                 
	               
	                //Prodname             
	                ap.getProductName().sendKeys(productnameWithAlphabet); 
					//Quantity
	                ap.getQuantity().clear(); 
	                ap.getQuantity().sendKeys(quantity); 
	                //Price
	                ap.getPrice().clear(); 
	                ap.getPrice().sendKeys(price); 
	 
	                //Product Category
	                wlib.select(ap.getProdCategory(), 2);
	                //VendorId
	                wlib.select(ap.getVendorId(),"VID_001"); 
	                
	                ap.getAddProdButton().click();
	                
	                
	             // validation 
	          	  wlib.waitForVisibilityOfElement(driver, hp.getToastmsg()); 
	          	  String msg = hp.getToastmsg().getText(); 
	          	 
	          	  if (msg.contains(productnameWithAlphabet)) { 
	          	   System.out.println("product created"); 
	          	  } else { 
	          	   System.out.println("product not created"); 
	          	  } 
	          	  hp.getCloseMsg().click(); 
	          	   
	          	  // logout 
	          	  hp.getUserIcon().click(); 
	          	  hp.getLogOutBtn().click(); 
	          	  driver.quit();
	          	
		
	}

}
