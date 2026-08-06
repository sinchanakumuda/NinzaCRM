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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;  

public class CreateCampaignWithSataus
{
public static void main(String[] args) { 
	  // TODO Auto-generated method stub
	  // TODO Auto-generated method stub 
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
	  
	  //Create Campaign
	  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	  
	  Random ran=new Random();
		int randomcount=ran.nextInt(500);
		String campNameWithRandom="TYPAmruta_ghij"+randomcount;
		System.out.println(campNameWithRandom);
	  driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom); 
	  //status
	  driver.findElement(By.name("campaignStatus")).sendKeys("pass"); 
	  
	  WebElement size = driver.findElement(By.name("targetSize")); 
	  size.clear(); 
	  size.sendKeys("1"); 
	  driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
	  //Validation
	  WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
	  //Explicit Wait
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	  wait.until(ExpectedConditions.visibilityOf(toastmsg)); 
	  String msg = toastmsg.getText(); 
	  if (msg.contains(campNameWithRandom)) { 
	   System.out.println("campaign are created"); 
	  } else { 
	   System.out.println("campaign not created"); 
	  } 
	  //logout
	  WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
	  Actions act=new Actions(driver); 
	  act.moveToElement(icon).click().perform(); 
	  WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']")); 
	  act.moveToElement(logout).click().perform(); 
	 } 
	 
	} 