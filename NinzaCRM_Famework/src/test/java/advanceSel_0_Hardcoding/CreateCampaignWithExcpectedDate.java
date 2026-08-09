package advanceSel_0_Hardcoding;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.interactions.Actions; 
import java.text.SimpleDateFormat;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;



public class CreateCampaignWithExcpectedDate {
	
public static void main(String[] args) { 
		  // To disable the  popups
	ChromeOptions settings = new ChromeOptions();
	Map<String, Object> prefs = new HashMap<String, Object>(); 
	prefs.put("profile.password_manager_leak_detection", false); 
	settings.setExperimentalOption("prefs", prefs); //key:prefs 
	WebDriver driver=new ChromeDriver(settings); 

		  
		  driver.manage().window().maximize(); 
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		  
		  driver.get("http://49.249.28.218:8098/"); 
		  //Login
		  driver.findElement(By.id("username")).sendKeys("rmgyantra"); 
		  driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999"); 
		  driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
		  //Campaign Creation
		  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		  
			Random ran=new Random();
			int randomcount=ran.nextInt(500);
			String campNameWithRandom="TYPAmruta_ghij"+randomcount;
			System.out.println(campNameWithRandom);
		  
		  driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom);
		  
		  WebElement size = driver.findElement(By.name("targetSize")); 
		  size.clear(); 
		  size.sendKeys("2"); 
		  
		  //Taking Expected date
		  Date date =new Date(); 
		  SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy"); 
		  sim.format(date); 
		  Calendar cal = sim.getCalendar(); 
		  cal.add(Calendar.DAY_OF_MONTH, 30); 
		  String reqdate = sim.format(cal.getTime()); 
		  
		driver.findElement(By.name("expectedCloseDate")).sendKeys(reqdate); 
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
		//validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
		//Explict Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOf(toastmsg)); 
		String msg = toastmsg.getText(); 
			if (msg.contains(campNameWithRandom)) { 
			System.out.println("campaign created"); 
			} else { 
			System.out.println("campaign not created"); 
			} 
			//Logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
		
		Actions act = new Actions(driver); 
		act.moveToElement(icon).click().perform(); 
		
		driver.findElement(By.xpath("//div[text()='Logout']")).click(); 
		driver.quit(); 
		} 
		}


