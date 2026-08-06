package advanceSel_BaseClass_InhertingClasses_OrTestClasses;
import config_BaseClass.BaseClass;
import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.Product;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listener.ListenerImplementation.class)
public class CreateProductTest extends BaseClass{
	
	
	@Test(groups="smoketest")
	
	public void createProduct() throws Throwable
	{
		
		
	     ExcelFileUtility elib=new ExcelFileUtility(); 
	     JavaUtility jlib=new JavaUtility(); 
	     WebDriverUtility wlib=new WebDriverUtility(); 
	      
 
	 
	    String productName = elib.toReadDataFromExcelFile("Product", 1, 2); 
	    String quantity = elib.toReadDataFromExcelFile("Product", 1, 3); 
	    String price = elib.toReadDataFromExcelFile("Product", 1, 4); 
	    
	    System.out.println("prodName "+productName);
	    System.out.println("quantity "+quantity);
	    System.out.println("price "+price);
	     
	    String priceWithRandom=productName+jlib.togetRandomCount();
	    
	 
	 
	                HomePage hp=new HomePage(driver); 
	                hp.getProduct().click(); 
	                
	                Product ap=new Product(driver); 
	                ap.getAddProduct().click(); 
	                 
	               
	                //Prodname             
	                ap.getProductName().sendKeys(priceWithRandom); 
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
	          	  
	        	  if (msg.contains(priceWithRandom)) { 
		          	   System.out.println("product created"); 
		          	  } else { 
		          	   System.out.println("product not created"); 
		          	  } 
		          	  hp.getCloseMsg().click(); 
	          	 
	          
	}
	
	
	
	

}
