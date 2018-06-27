package com.tms.transportPlanning;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.businessDefination.CommonBusinessFunction;
import com.businessDefination.TmsObjectCreation;
import com.keywords.CustomExceptions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import dataTable.DataTable;

@Listeners({utility.Listner.class})
public class TestRunner {
	public static WebDriver driver;
	public static CommonBusinessFunction commBusinessFun;
	public static TmsObjectCreation tmsObjectCreation;
	public static WebDriverWait wait;
	public static String dataFileLoc; 
	public static DataTable dataTable;
	public static Properties p;
	public static String ORFileLoc = System.getProperty("user.dir")+"\\OR.properties";
	public static ExtentReports reports;
	public static ExtentTest logger;
	
	@BeforeTest
	public void reporting() {
		reports = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		logger = reports.startTest("GTNX Application test");
	}
	
	
	@Test(enabled=false)
	@Parameters({"browser","appUrl"})
	public void UO_RTS_TOCreationFlow(String browser, String appUrl) throws InterruptedException, CustomExceptions{
		
		commBusinessFun = new CommonBusinessFunction();
		commBusinessFun.launchBrowser(browser);
		commBusinessFun.launchApplication(appUrl);
		commBusinessFun.loginTOApplication();
		String orderNumber = commBusinessFun.createUO("C:\\Users\\psamantaray\\Documents\\My Received Files\\Pfizer_FTE_UO.xml");
		System.out.println("Order Number: "+orderNumber);
		
		//Switch to GTNX Application
		commBusinessFun.switchToGTNXAppFromTCX();
		commBusinessFun.shadowuserLogin();
		commBusinessFun.searchForTOInFlexViewUsingOrderNumber(orderNumber);
		
	}
	
	@Test
	@Parameters({"browser","appUrl"})
	public void IndependentRTSCreation(String browser, String appUrl) throws InterruptedException, CustomExceptions{
		
		commBusinessFun = new CommonBusinessFunction();
		commBusinessFun.launchBrowser(browser);
		commBusinessFun.launchApplication(appUrl);
		commBusinessFun.loginTOApplication();
		String rtsNumber = commBusinessFun.createIndependentRTS("C:\\Users\\psamantaray\\Desktop\\Remve UO&PO dependency on TO_18.5\\RTSInbound01.xml");
		System.out.println("RTS Number: "+rtsNumber);
		
		//Switch to GTNX Application
		commBusinessFun.switchToGTNXAppFromTCX();
		commBusinessFun.shadowuserLogin();
		commBusinessFun.searchForTOInFlexViewUsingRTSNumber(rtsNumber);
		
	}
	@AfterTest
	public void afterExecution() throws InterruptedException {
		reports.endTest(logger);
		reports.flush();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		String path = System.getProperty("user.dir")+"/test-output/STMExtentReport.html";
		System.out.println("Find the report in "+path);
		//Thread.sleep(5000);
		//driver.get(path);
	}
	
}