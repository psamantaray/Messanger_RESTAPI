package com.businessDefination;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.pageObject.LoginPage;
import com.pageObject.TCXHomePage;
import com.tms.transportPlanning.TestRunner;

import dataTable.DataTable;

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
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver");
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver",
						"D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
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
	public void loginTOApplication(String loginType) {
		DataTable datatable = new DataTable();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		if (loginType.equalsIgnoreCase("NH")) {
			try {
				// Thread.sleep(10000);
				loginPage.uName.sendKeys(datatable.getValue("nhUser"));
				loginPage.psw.sendKeys(datatable.getValue("nhPsw"));
				loginPage.loginBtn.click();

			} catch (Exception e) {
				e.printStackTrace();
			}

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

	public String processUO(String filePath) throws InterruptedException {
		String orderNumber = null;
		try{
			TCXHomePage homePage = PageFactory.initElements(driver, TCXHomePage.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			homePage.messaging.click();
			homePage.utility.click();
			homePage.importAction.click();
			homePage.MessageOrg.sendKeys("Synergy Test Customer");
			List<WebElement> allResults = driver.findElements(By.xpath("//div[@class='tt-menu tt-open']/div/div"));
			System.out.println(allResults.size());

			for (WebElement ele : allResults) {
				System.out.println("Text on the Element: " + ele.getText());
				System.out.println("Element Is Enabled: " + ele.isEnabled());
			}
			allResults.get(0).click(); 
			Select sel = new Select(homePage.documentType);
			sel.selectByVisibleText("Order");
			sel = new Select(homePage.AgentuserID);
			sel.selectByIndex(1);
			sel = new Select(homePage.fileFormat);
			sel.selectByVisibleText("Inbound Po");
			
			homePage.file.sendKeys(filePath);
			homePage.importBtn.click();
			Thread.sleep(5000);
			Alert alt = driver.switchTo().alert();
			//alt.wait(5000);
			alt.dismiss();
			homePage.messageUID.click();
			Thread.sleep(5000);
			if(!(homePage.boUID.getText().equals(""))){
				orderNumber = homePage.orderNumber.getText();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return orderNumber;
		
	}
}
