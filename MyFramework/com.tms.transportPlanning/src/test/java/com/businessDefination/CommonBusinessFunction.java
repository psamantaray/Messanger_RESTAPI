package com.businessDefination;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObject.LoginPage;
import com.pageObject.TCXHomePage;
import com.tms.transportPlanning.TestRunner;

import stepDefination.TmsApplication;

public class CommonBusinessFunction extends TestRunner {

	// TO launch browser
	/*
	 * supported Browser
	 * 
	 * @chrome
	 * 
	 * @firefox
	 * 
	 * @IE
	 */

	public WebDriver launchBrowser(String browserType) {
		// WebDriver driver = null;
		try {
			switch (browserType.toLowerCase()) {
			case "firefox":
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						"D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\chromedriver.exe");
				driver = new ChromeDriver();

			case "ie":
				System.setProperty("webdriver.ie.driver",
						"D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			default:
				System.out.println("Provided browser(" + browserType + ")"
						+ "is not supported or invalid so using the default browser");
				driver = new FirefoxDriver();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.manage().window().maximize();
		}
		return driver;
	}

	// To launch application
	public void launchApplication(String appUrl) {
		try {
			driver.get(appUrl);
		} catch (Exception e) {
			// System.out.println(applicationUrl+": is invalid or not
			// accessible");
			e.printStackTrace();

		}
	}

	// Login to application
	public void loginTOApplication(String uname, String psw) {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		try {
			// Thread.sleep(10000);
			loginPage.uName.sendKeys(uname);
			loginPage.psw.sendKeys(psw);
			loginPage.loginBtn.click();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void shadowuserLogin(String uname) {
		try {
			driver.findElement(By.linkText("Home")).click();
		} catch (Exception e) {
			System.out.println("You are not in your Home page, current page title: " + driver.getTitle());
			System.out.println("Navigating to your Home or Welcome page");
			driver.findElement(By.linkText("Home")).click();
		}
		driver.findElement(By.id("loginAsField-input")).sendKeys(uname);
		driver.findElement(By.xpath("//table[@id='gtn_auto_14']/tbody/tr[2]/td[2]/em/button")).click();
	}

	public void processUO(String filePath) throws InterruptedException {
		TCXHomePage TCXHomePage = PageFactory.initElements(driver, TCXHomePage.class);
		TCXHomePage.messaging.click();
		TCXHomePage.utility.click();
		TCXHomePage.importAction.click();
		TCXHomePage.MessageOrg.sendKeys("Synergy Test Customer");
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		java.util.List<WebElement> lst = driver.findElements(By.xpath("//div[@class='tt-dataset tt-dataset-0']/div"));
		
		act.moveToElement(lst.get(0)).click().perform();
		
		System.out.println(lst.size());
		
		WebElement text = driver.findElement(By.xpath(""));
		act.moveToElement(driver.findElement(By.xpath("//div[@class='tt-menu tt-open']/div/div"))).click().build().perform();
		Thread.sleep(3000);
		Select sel = new Select(TCXHomePage.documentType);
		sel.selectByVisibleText("AdapterProfileRoute");
		sel= new Select(TCXHomePage.AgentuserID);
		sel.selectByIndex(1);
		sel = new Select(TCXHomePage.fileFormat);
		sel.selectByVisibleText("TradeCard XML v3.10");
		
	}
}
