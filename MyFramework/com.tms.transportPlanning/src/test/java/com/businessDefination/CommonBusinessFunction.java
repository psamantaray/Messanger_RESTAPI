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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.keywords.CustomExceptions;
import com.pageObject.GtnexusNHHomePage;
import com.pageObject.LoginPage;
import com.pageObject.ShipperUserHomePage;
import com.pageObject.TCXHomePage;
import com.relevantcodes.extentreports.LogStatus;
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
				logger.log(LogStatus.PASS, "Firefox browser launched Successfully.");
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				logger.log(LogStatus.PASS, "Chrome browser launched Successfully.");
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver",
						"D:\\FrameWorkData_Virtual\\Prasant_Repository\\Prasant_KeywordDriven_Framework\\BrowserServer\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				logger.log(LogStatus.PASS, "IE browser launched Successfully.");
				break;
			default:
				System.out.println("Provided browser(" + browserType + ")"
						+ "is not supported or invalid so using the default browser");
				driver = new FirefoxDriver();
				logger.log(LogStatus.PASS, "Default Firefox browser launched Successfully.");
			}

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to launch the Browser, please the browser installation");
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
			logger.log(LogStatus.PASS, "Application launched successfully.");
			logger.log(LogStatus.INFO, "Application url: " + appUrl);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to access the Appliation url: " + appUrl);
			logger.log(LogStatus.ERROR, "Application Url is invalid or not accessible", e);
			e.printStackTrace();
		}
	}

	// Login to application
	public void loginTOApplication(String loginType) {
		DataTable datatable = new DataTable();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		if (loginType.equalsIgnoreCase("NH")) {
			try {
				loginPage.uName.sendKeys(datatable.getValue("nhUser"));
				loginPage.psw.sendKeys(datatable.getValue("nhPsw"));
				loginPage.loginBtn.click();
				logger.log(LogStatus.PASS, "NH user login is successful.");

			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "NH user login has failed.");
				logger.log(LogStatus.ERROR, "NH user login failed.", e);
				e.printStackTrace();
			}

		}

	}

	public boolean shadowuserLogin() throws InterruptedException {
		DataTable datatable = new DataTable();
		GtnexusNHHomePage gtnHomePage = PageFactory.initElements(driver, GtnexusNHHomePage.class);
		ShipperUserHomePage shipperUser = PageFactory.initElements(driver, ShipperUserHomePage.class);
		try {
			if (!(driver.getTitle().equals("Welcome")))
				logger.log(LogStatus.WARNING, "User is not in the Home Page");
		} catch (Exception e) {
			gtnHomePage.homeLink.click();
			logger.log(LogStatus.PASS, "Successfully landed on shipper user Home page");
		}
		Thread.sleep(10000);

		gtnHomePage.shadowUser.sendKeys(datatable.getValue("shadowUser"));
		gtnHomePage.loginButton.click();

		try {
			Thread.sleep(3000);
			WebElement okBtn = driver.findElement(By.xpath("//button[text()='OK']"));
			if (okBtn.isDisplayed()) {
				logger.log(LogStatus.FAIL,
						"Provided shadow user [" + datatable.getValue("shadowUser") + "] is invalid");
				throw new CustomExceptions("Invalid user");
			}

		} catch (Exception e) {
			
		}

		Thread.sleep(10000);
		if (shipperUser.homeLink.isDisplayed())

		{
			// System.out.println("Successfully landed on Shipper user (" +
			// datatable.getValue("shadowUser") + ") page");
			logger.log(LogStatus.PASS, "Shadow user login is successful");
		}
		logger.log(LogStatus.INFO, "Shadow logi user: " + datatable.getValue("shadowUser"));
		return true;
	}

	public boolean switchToGTNXAppFromTCX() {
		TCXHomePage homePage = PageFactory.initElements(driver, TCXHomePage.class);
		try {
			homePage.userIcon.click();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "You are not in TCX Page.", e);

		}
		homePage.switchTOGTNXNH.click();
		if (!(driver.getTitle().equals("Welcome"))) {
			System.out.println("Unable to ");
			return false;
		}
		System.out.println("Switched to GTNX NH Successfully!");
		logger.log(LogStatus.PASS, "Switched to GTNX Admin Page is successful.");
		return true;
	}

	public String createUO(String filePath) {
		String orderNumber = null;
		try {
			TCXHomePage homePage = PageFactory.initElements(driver, TCXHomePage.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			homePage.messaging.click();
			homePage.utility.click();
			homePage.importAction.click();
			homePage.MessageOrg.sendKeys("Synergy Test Customer");
			List<WebElement> allResults = driver.findElements(By.xpath("//div[@class='tt-menu tt-open']/div/div"));
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
			alt.dismiss();
			homePage.messageUID.click();
			Thread.sleep(5000);
			if (!(homePage.boUID.getText().equals(""))) {
				orderNumber = homePage.orderNumber.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderNumber;

	}

	public void searchForTOInFlexView(String orderNumber) throws InterruptedException {
		ShipperUserHomePage shipperUser = PageFactory.initElements(driver, ShipperUserHomePage.class);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOf(shipperUser.application));
		wait.until(ExpectedConditions.elementToBeClickable(shipperUser.application));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		shipperUser.application.click();
		shipperUser.transportOrder.click();
	}
}
