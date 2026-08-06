package listener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config_BaseClass.BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class ListenerImplementation implements ITestListener,ISuiteListener{
/*	ExtentReports report;
	ExtentTest test;
	@Override

	public void onStart(ISuite suite) {
		Date d =new Date();
		System.out.println(d);
		String newdate=d.toString().replace(" ","_").replace(":","_");
	ExtentSparkReporter spark=new ExtentSparkReporter("./advanceReport/NinzaReport_"+newdate+"spark.config().html");
	spark.config().setDocumentTitle("CRM");
	spark.config().setReportName("NinzaReports");
	spark.config().setTheme(Theme.DARK);
	report=new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("os", "windows 11");
	report.setSystemInfo("browser", "chrome");
	
	}

	@Override
	public void onFinish(ISuite suite) {
	report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		 test=report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "execution started");
	Reporter.log("execution started",true);	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("executed successfully",true);
		test.log(Status.PASS, "execution successfully");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "execution failed");
		
		String testname = result.getMethod().getMethodName();
		Date d =new Date();
		System.out.println(d);
		String newdate=d.toString().replace(" ","_").replace(":","_");
       //Extent report will not support screenshot in the file format
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver; //Listner Static variable from base class
			 String src = ts.getScreenshotAs(OutputType.BASE64);
			 test.addScreenCaptureFromBase64String(src,testname+newdate);
			
			
	}
	
	/*@Override
	 * //Extent report will not support screenshot in the file format
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "execution failed");
		Date d =new Date();
		System.out.println(d);
		String newdate=d.toString().replace(" ","_").replace(":","_");
       //Extent report will not support screenshot in the file format
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver; //Listner Static variable from base class
			 File temp = ts.getScreenshotAs(OutputType.FILE); 
			 File perm=new File("./screenshots/ninza_"+newdate+".png"); 
			
			 try
			 {
			   FileHandler.copy(temp, perm);
			 //FileUtils.copyFile(temp, perm);
			 }
			 catch(IOException e)
			 {
			 e.printStackTrace();
			}
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("execution skipped",true);
		test.log(Status.SKIP, "execution Skipped");
	}*/
	
	
	public ExtentSparkReporter spark; 
	 public ExtentReports report; 
	 public ExtentTest test; 
	 public void onStart(ISuite suite) { 
	   
	  Reporter.log("Report configuration",true); 
	  Date d=new Date(); 
	  String newDate = d.toString().replace(" ","_").replace(":","_");  
	spark=new 
	ExtentSparkReporter("./AdvanceReports/report_"+newDate+".html"); 
	   spark.config().setDocumentTitle("NinzaCRM Test Suite Results"); 
	   spark.config().setReportName("CRM Report"); 
	   spark.config().setTheme(Theme.DARK); 
	    
	   report=new ExtentReports(); 
	   report.attachReporter(spark); 
	   report.setSystemInfo("OS","Windows 11"); 
	   report.setSystemInfo("Browser", "Edge"); 
	   
	 } 
	 
	 public void onFinish(ISuite suite) { 
	   
	  report.flush(); 
	  Reporter.log("Report backup",true); 
	 } 
	 
	 public void onTestStart(ITestResult result) { 
	 
	   test = report.createTest(result.getMethod().getMethodName()); 
	   
	test.log(Status.INFO,"===="+result.getMethod().getMethodName()+"Execution STARTED===="); 
	 
	 
	 } 
	 
	  
	 
	public void onTestSuccess(ITestResult result) { 
	test.log(Status.PASS,"====="+result.getMethod().getMethodName()+"SUCCESS====="); 
	} 
	
	public void onTestFailure(ITestResult result) { 
	String testName = result.getMethod().getMethodName(); 
	Date d=new Date(); 
	String newDate = d.toString().replace(" ","_").replace(":","_");   
	TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver; 
	String src = ts.getScreenshotAs(OutputType.BASE64); 
	test.addScreenCaptureFromBase64String(src,testName+newDate); 
	test.log(Status.FAIL,"====="+testName+" FAILURE====="); 
	}
	
	public void onTestSkipped(ITestResult result) { 
	test.log(Status.SKIP,"====="+result.getMethod().getMethodName()+" SKIPPED====="); 

	}
	
}
