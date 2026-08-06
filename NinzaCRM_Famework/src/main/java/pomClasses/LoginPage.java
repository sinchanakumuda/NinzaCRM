package pomClasses;


import java.time.Duration;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	 

	  
	 WebDriver driver; 
	 public LoginPage(WebDriver driver) 
	 { 
	    //it will avoid Stale Element Refreance
	      PageFactory.initElements(driver,this); 
	   
	 } 
	 
	 
	 @FindBy(id="username") 
	  private  WebElement Username; 
	  
	 @FindBy(id="inputPassword") 
	   private  WebElement Password; 
	  
	 @FindBy(xpath="//button[text()='Sign In']") 
	   private  WebElement LoginButton; 
	 
	 public WebElement getUsername() { 
	  return Username; 
	 } 
	 
	 public WebElement getPassword() { 
	  return Password; 
	 } 
	 
	 public WebElement getLoginBtn() { 
	  return LoginButton; 
	 } 
	      
	 
	  
		
}

