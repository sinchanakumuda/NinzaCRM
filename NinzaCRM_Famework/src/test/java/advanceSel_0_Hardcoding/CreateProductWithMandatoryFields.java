package advanceSel_0_Hardcoding;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateProductWithMandatoryFields {
	

			public static void main(String[] args) { 
				  // Disabling the the password change popup
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>(); 
				prefs.put("profile.password_manager_leak_detection", false); 
				settings.setExperimentalOption("prefs", prefs); 
				WebDriver driver=new ChromeDriver(settings); 
				
			
				
				 
				  
				  driver.manage().window().maximize(); 
				  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				  driver.get("http://49.249.28.218:8098/"); 
				  //Login
				  driver.findElement(By.id("username")).sendKeys("rmgyantra"); 
				  driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999"); 
				  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
				  //Click on product link on Home page
				  driver.findElement(By.linkText("Products")).click(); 
				  //click on Add Product
				  driver.findElement(By.xpath("//span[text()='Add Product']")).click();
				  
					Random rand=new Random();
					char upper=(char)('A'+rand.nextInt(26));
					String RandomAlphabet=""+upper;
					String productnameWithAlphabet="shoebrand_xi"+RandomAlphabet;
				  driver.findElement(By.name("productName")).sendKeys(productnameWithAlphabet); 
				  System.out.println(productnameWithAlphabet);
				 
				  WebElement dropdown1 = driver.findElement(By.name("productCategory")); 
				  Select sel = new Select(dropdown1); 
				  sel.selectByVisibleText("Electronics"); 
				 
				  WebElement quantity = driver.findElement(By.name("quantity")); 
				  quantity.clear(); 
				  quantity.sendKeys("2"); 
				 
				  WebElement price = driver.findElement(By.name("price")); 
				  price.clear(); 
				  price.sendKeys("678"); 
				 
				  WebElement dropdown2 = driver.findElement(By.name("vendorId")); 
				  Select sel1 = new Select(dropdown2); 
				  sel1.selectByVisibleText("Vendor_27589 - (Electronics)"); 
				   
				  driver.findElement(By.xpath("//button[text()='Add']")).click(); 
				   
				  //Validation
				  WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
				  //Adding Explicit Wait 
				  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
				  wait.until(ExpectedConditions.visibilityOf(toastmsg)); 
				  String msg = toastmsg.getText(); 
				  if (msg.contains(productnameWithAlphabet)) { 
				   System.out.println("product are created"); 
				  } else { 
				   System.out.println("product not created"); 
				  } 
				 
				  //logout
				  WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
				  Actions act = new Actions(driver); 
				  act.moveToElement(icon).click().perform(); 
				  WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']")); 
				  act.moveToElement(logout).click().perform();
			}

}
