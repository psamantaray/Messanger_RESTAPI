 package com.businessDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tms.transportPlanning.TestRunner;

import stepDefination.TmsApplication;

public class CommonBusinessFunction extends TestRunner{
	
	//TO launch browser
	/*supported Browser
	 * @chrome
	 * @firefox
	 * @IE
	 */
	public  WebDriver launchBrowser(String browserType){
		//WebDriver driver = null;
		try {
			switch (browserType.toLowerCase()) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver","D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\chromedriver.exe");
				driver = new ChromeDriver();
				
			case "ie":
				System.setProperty("webdriver.ie.driver","D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			default:
				System.out.println("Provided browser("+browserType+")"+"is not supported or invalid so using the default browser");
				driver = new FirefoxDriver();
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	//To launch application
	public void launchApplication(String appUrl){
		try {
			driver.get(appUrl);
		}
		catch(Exception e) {
			//System.out.println(applicationUrl+": is invalid or not accessible");
			e.printStackTrace();
			
		}
	}
	
	//Login to application
	public void loginTOApplication(String uname,String psw) {
		try {
			Thread.sleep(10000);
			driver.findElement(By.id("login")).sendKeys(uname);
	        driver.findElement(By.id("password")).sendKeys(psw);
	        driver.findElement(By.id("loginButton")).click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void shadowuserLogin(String uname) {
		try {
			driver.findElement(By.linkText("Home")).click();
		}
		catch(Exception e) {
			System.out.println("You are not in your Home page, current page title: "+driver.getTitle());
			System.out.println("Navigating to your Home or Welcome page");
			driver.findElement(By.linkText("Home")).click();
		}
		driver.findElement(By.id("loginAsField-input")).sendKeys(uname);
		driver.findElement(By.xpath("//table[@id='gtn_auto_14']/tbody/tr[2]/td[2]/em/button")).click();
	}
}


