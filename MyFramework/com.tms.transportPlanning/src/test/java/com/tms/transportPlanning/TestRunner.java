package com.tms.transportPlanning;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.businessDefination.CommonBusinessFunction;
import com.businessDefination.TmsObjectCreation;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
	
	
	@Test
	@Parameters({"browser","appUrl","loginType"})
	public void Executer(String browser, String appUrl, String loginType) throws InterruptedException{
		
		commBusinessFun = new CommonBusinessFunction();
		commBusinessFun.launchBrowser(browser);
		commBusinessFun.launchApplication(appUrl);
		commBusinessFun.loginTOApplication(loginType);
		//String orderNumber = commBusinessFun.createUO("C:\\Users\\psamantaray\\Desktop\\10865_UO.XML");
		//System.out.println("Order Created Successfully!!");
		//System.out.println("Order Number: "+orderNumber);
		
		//Switch to GTNX Application
		commBusinessFun.switchToGTNXAppFromTCX();
		commBusinessFun.shadowuserLogin();
		//commBusinessFun.searchForTOInFlexView("orderNumber");
		
	}
	@AfterTest
	public void afterExecution() {
		reports.endTest(logger);
		reports.flush();
		String path = System.getProperty("user.dir")+"/test-output/STMExtentReport.html";
		driver.get(path);
	}
	
}