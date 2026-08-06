package advanceSel_0_Hardcoding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException {
		// 
		//Disable popup
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs); 
		WebDriver driver=new ChromeDriver(settings); 
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//create campaign
		
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		Random ran=new Random();
		int randomcount=ran.nextInt(500);
		String campNameWithRandom="TYPAmruta_ghij"+randomcount;
		System.out.println(campNameWithRandom);
		
		driver.findElement(By.name("campaignName")).sendKeys(campNameWithRandom);
		WebElement tsize=driver.findElement(By.name("targetSize"));
		tsize.clear();
		tsize.sendKeys("20");
		
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		

		
	
		
		//validate
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg=toastmsg.getText();
		
		/* correct Approch
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		String msg = toastmsg.getText();*/
		
		if(msg.contains(campNameWithRandom))
		{
			System.out.println("campaign created");
		}
		else
		{
			System.out.println("not created");
		}
		
		Thread.sleep(5000);
		
		//logout
		
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(icon).click().perform();
		
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		
		
		driver.quit();
		
			
		
		
		
		
		
		

	}

}
