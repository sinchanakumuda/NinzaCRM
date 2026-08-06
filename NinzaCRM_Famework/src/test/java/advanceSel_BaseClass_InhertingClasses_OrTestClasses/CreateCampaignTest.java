package advanceSel_BaseClass_InhertingClasses_OrTestClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import config_BaseClass.BaseClass;
import genericUtility_Methods.ExcelFileUtility;
import genericUtility_Methods.JavaUtility;
import genericUtility_Methods.PropertiesFileUtility;
import genericUtility_Methods.WebDriverUtility;
import pomClasses.CampaignPage;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import org.testng.annotations.Listeners;


@Listeners(listener.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass
{
	 
	  ExcelFileUtility elib = new ExcelFileUtility(); 
	  JavaUtility jlib = new JavaUtility(); 
	  WebDriverUtility wlib=new WebDriverUtility(); 
	
	  
	
	@Test(groups="smoketest")
	public void createCampaignWithMandatoryFieldTest() throws Throwable
	{
		String CAMPAIGN_NAME = elib.toReadDataFromExcelFile("Campaign",1,2); 
		String TARGET_SIZE = elib.toReadDataFromExcelFile("Campaign",1,3); 
				 
				  // create campaign 
				  HomePage hp = new HomePage(driver); 
				  hp.getCampaign().click();
				  hp.getCreatecampaignBtn().click();
				  String campNameWithRandom=CAMPAIGN_NAME+jlib.togetRandomAlpha();
				 
				  // enter mandatory details 
				  CampaignPage cmp = new CampaignPage(driver); 
				  cmp.getCampaignNameTF().sendKeys(campNameWithRandom);
				  Reporter.log(campNameWithRandom);
				  System.out.println(campNameWithRandom);
				  cmp.getTargetSizeTF().clear(); 
				  cmp.getTargetSizeTF().sendKeys(TARGET_SIZE); 
				  cmp.getCreateCampaignSubmitBtn().click(); 
				 
				  // validation 
				  WebElement toastmsg = hp.getToastmsg(); 
				  wlib.waitForVisibilityOfElement(driver, toastmsg); 
				  String msg = toastmsg.getText(); 
				  if (msg.contains(campNameWithRandom)) { 
				   System.out.println("campaign created"); 
				  } 
				  else { 
				   System.out.println("campaign not created"); 
				  }
				//WebElement of close X icon
				  hp.getCloseMsg(); 
		
	}
	@Test
	public void createCampaignWithStatusTest() throws Throwable
	{
		          String CAMPAIGN_NAME = elib.toReadDataFromExcelFile("Campaign", 4, 2); 
				  String TARGET_SIZE = elib.toReadDataFromExcelFile("Campaign", 4, 3); 
				  String STATUS = elib.toReadDataFromExcelFile("Campaign", 4, 4); 
				 
				  // create campaign 
				  HomePage hp = new HomePage(driver); 
				  hp.getCampaign().click(); 
				  hp.getCreatecampaignBtn().click();
				  String campNameWithRandom=CAMPAIGN_NAME+jlib.togetRandomAlpha();
				  System.out.println(campNameWithRandom);
				 
				  // enter mandatory details 
				  CampaignPage cmp = new CampaignPage(driver); 
				  cmp.getCampaignNameTF().sendKeys(campNameWithRandom); 
				  cmp.getTargetSizeTF().clear(); 
				  cmp.getTargetSizeTF().sendKeys(TARGET_SIZE); 
				  Thread.sleep(2000); 
				  cmp.getCampaignStatusTF().sendKeys(STATUS); 
				  cmp.getCreateCampaignSubmitBtn().click(); 
				 
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
	}
	
	@Test(groups="smoketest")
	public void createCampaignWithExpectedDateTest() throws Throwable
	{
		
		 
		 
		 
		  // Read data from excel 
		  String campname = elib.toReadDataFromExcelFile("Campaign", 7, 2); 
		  String size = elib.toReadDataFromExcelFile("Campaign", 7, 3); 
		 
		
		  String expectedDate = jlib.togetRequiredDate(30); 
		  System.out.println("expectedDate "+expectedDate);
		  
		  String campNameWithRandom=campname+jlib.togetRandomAlpha();
		  System.out.println(campNameWithRandom);
		 
		
		 
		  // create campaign 
		  HomePage hp=new HomePage(driver); 
		  hp.getCreatecampaignBtn().click();
		  
		  CampaignPage cp=new CampaignPage(driver); 
		  cp.getCampaignNameTF().sendKeys(campNameWithRandom); 
		  cp.getTargetSizeTF().clear();
		  cp.getTargetSizeTF().sendKeys(size); 
		  Thread.sleep(2000); 
		 
		        wlib.passInput(driver,cp.getExpectedCloseDateTF(),expectedDate); 
		        //cmp.getExpectedClosedate().sendKeys(jlib.togetRequiredDate(30
		        cp.getCreateCampaignSubmitBtn().click(); 
		 
		        // validation 
		 
		       
		        String msg = hp.getToastmsg().getText(); 
		        wlib.waitForVisibilityOfElement(driver, hp.getToastmsg()); 
		 
		  if (msg.contains(campNameWithRandom)) { 
		   System.out.println("campaign created"); 
		  } else { 
		   System.out.println("campaign not created"); 
		  } 
		  hp.getCloseMsg(); 
		 
		 
		
	}
	
	
	

}
