package com.tms.transportPlanning;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.businessDefination.CommonBusinessFunction;
import com.businessDefination.TmsObjectCreation;

import dataTable.DataTable;
@Listeners({utility.Listner.class})



public class TestRunner {
	public static WebDriver driver = null;
	public static CommonBusinessFunction commBusinessFun = null;
	public static TmsObjectCreation tmsObjectCreation = null;
	public static WebDriverWait wait = null;
	public static String dataFileLoc; 
	public static DataTable dataTable = null;
	public static Properties p = null;
	public static String ORFileLoc = System.getProperty("user.dir")+"\\OR.properties";
	
	
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
		commBusinessFun.searchForTOInFlexView("orderNumber");
		
	}
	
}