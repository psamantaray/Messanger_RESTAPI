package com.businessDefination;

import java.util.Iterator;
import java.util.List;
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
import com.pageObject.TOFlexview;
import com.relevantcodes.extentreports.LogStatus;
import com.tms.transportPlanning.TestRunner;

import dataTable.DataTable;
import utility.UtilityMethods;

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

	// launch Browser
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
	public void loginTOApplication() throws CustomExceptions {

		DataTable datatable = new DataTable();

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.uName.sendKeys(datatable.getValue("nhUser"));
		loginPage.psw.sendKeys(datatable.getValue("nhPsw"));
		loginPage.loginBtn.click();
		// boolean set = loginPage.invalidUserAlertBox.isDisplayed();

		int size = driver.findElements(By.id("alertboxmessage")).size();
		if (size == 0)
			logger.log(LogStatus.PASS, "NH user login is successful.");
		else {
			logger.log(LogStatus.FAIL, "Invalid User name or password -->NH user login has failed.");
			/*
			 * logger.log(LogStatus.FAIL,
			 * "Invalid User name or password -->NH user login has failed.",
			 * logger.addScreenCapture(UtilityMethods.TakeSnapshot("loginPage"))
			 * );
			 */
			throw new CustomExceptions("Invalid user /password entered");
		}
	}

	// Shadow user login
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

		// Thread.sleep(6000);

		if (shipperUser.tabBar.isDisplayed()) {
			logger.log(LogStatus.PASS, "Shadow user login is successful");
		}
		logger.log(LogStatus.INFO, "Shadow logi user: " + datatable.getValue("shadowUser"));
		return true;
	}

	// Switch to GTN application from TCX
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
			return false;
		}
		System.out.println("Switched to GTNX NH Successfully!");
		logger.log(LogStatus.PASS, "Switched to GTNX Admin Page is successful.");
		return true;
	}

	// Unified Order creation through integration
	public String createUO(String filePath) throws CustomExceptions {
		TCXHomePage homePage = PageFactory.initElements(driver, TCXHomePage.class);
		String orderNumber = null;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			homePage.messaging.click();
			homePage.utility.click();
			homePage.importAction.click();
			homePage.MessageOrg.sendKeys("pfizer");
			List<WebElement> allResults = driver.findElements(By.xpath("//div[@class='tt-menu tt-open']/div/div"));
			allResults.get(0).click();
			Select sel = new Select(homePage.documentType);
			sel.selectByVisibleText("Order");
			sel = new Select(homePage.AgentuserID);
			sel.selectByIndex(1);
			sel = new Select(homePage.fileFormat);
			sel.selectByVisibleText("TradeCard XML v3.10");
			homePage.file.sendKeys(filePath);
			homePage.importBtn.click();
			Thread.sleep(5000);
			Alert alt = driver.switchTo().alert();
			alt.dismiss();
			homePage.messageUID.click();
			Thread.sleep(5000);
			while (homePage.boUID.getText().equals("") && !(homePage.state.getText().equals("Failed"))) {
				driver.navigate().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (homePage.state.getText().equals("Failed")) {
			logger.log(LogStatus.FAIL, "Error occures on File processing: " + homePage.notes.getText());
			throw new CustomExceptions("UO Creation failed  due to error in the file content");
		}

		orderNumber = homePage.orderNumber.getText();
		logger.log(LogStatus.PASS, "Unified Order Created successfully.");

		logger.log(LogStatus.INFO, "UO Number: " + orderNumber);
		return orderNumber;

	}

	public String createIndependentRTS(String filePath) throws CustomExceptions {
		String rtsNumber = null;
		TCXHomePage homePage = PageFactory.initElements(driver, TCXHomePage.class);
		try {

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			homePage.messaging.click();
			homePage.utility.click();
			homePage.importAction.click();
			homePage.MessageOrg.sendKeys("pfizer");
			List<WebElement> allResults = driver.findElements(By.xpath("//div[@class='tt-menu tt-open']/div/div"));
			allResults.get(0).click();
			Select sel = new Select(homePage.documentType);
			sel.selectByVisibleText("ReadyToShipOrder");
			sel = new Select(homePage.AgentuserID);
			sel.selectByIndex(1);
			sel = new Select(homePage.fileFormat);
			sel.selectByIndex(1);
			homePage.file.sendKeys(filePath);
			homePage.importBtn.click();
			Thread.sleep(5000);
			Alert alt = driver.switchTo().alert();
			alt.dismiss();
			homePage.messageUID.click();
			Thread.sleep(5000);
			while (homePage.boUID.getText().equals("") && !(homePage.state.getText().equals("Failed"))) {
				driver.navigate().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (homePage.state.getText().equals("Failed")) {
			logger.log(LogStatus.FAIL, "Error occures on File processing: " + homePage.notes.getText());
			throw new CustomExceptions("RTS Creation failed  due to error in the file content");
		}
		rtsNumber = homePage.rtsNumber.getText();
		logger.log(LogStatus.PASS, "Ready TO Ship Order Created successfully.");

		logger.log(LogStatus.INFO, "RTS Number: " + rtsNumber);
		return rtsNumber;
	}

	// Search for Transport order in Flexview using order number
	public void searchForTOInFlexViewUsingOrderNumber(String orderNumber)
			throws InterruptedException, CustomExceptions {
		ShipperUserHomePage shipperUser = PageFactory.initElements(driver, ShipperUserHomePage.class);
		TOFlexview TOFlexview = PageFactory.initElements(driver, TOFlexview.class);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(shipperUser.application));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		shipperUser.application.click();
		shipperUser.transportOrder.click();
		wait.until(ExpectedConditions.visibilityOf(TOFlexview.orderNumber));
		TOFlexview.orderNumber.sendKeys(orderNumber);
		TOFlexview.apply.click();
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(""), "Searching..."));
		wait.until(ExpectedConditions.invisibilityOfAllElements(TOFlexview.searching));
		int nosOfTOs = TOFlexview.transportOrders.size();
		if (nosOfTOs > 0) {
			StringBuilder sb = new StringBuilder();
			logger.log(LogStatus.PASS, "Transport Order Created Successfully.");
			Iterator<WebElement> itr = TOFlexview.transportOrders.iterator();
			while (itr.hasNext()) {
				sb.append(itr.next().getText() + ", ");
			}
			logger.log(LogStatus.INFO, "Created Transport order ID/s: " + sb.toString().trim());
		} else {
			logger.log(LogStatus.FAIL, "Transport Order has not created from RTS, Please check your data");
			throw new CustomExceptions("Transport Order has not created from RTS, Please check your data");
		}
		TOFlexview.transportOrders.get(0).click();
		logger.log(LogStatus.PASS, "Successfully Landded on Transport order Details page");
	}

	// Search for Transport order in Flexview using RTS Number
	public void searchForTOInFlexViewUsingRTSNumber(String rtsNumber) throws InterruptedException, CustomExceptions {
		ShipperUserHomePage shipperUser = PageFactory.initElements(driver, ShipperUserHomePage.class);
		TOFlexview TOFlexview = PageFactory.initElements(driver, TOFlexview.class);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(shipperUser.application));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		shipperUser.application.click();
		shipperUser.transportOrder.click();
		wait.until(ExpectedConditions.visibilityOf(TOFlexview.orderNumber));
		TOFlexview.RTSNumber.sendKeys(rtsNumber);
		TOFlexview.apply.click();
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(""), "Searching..."));
		wait.until(ExpectedConditions.invisibilityOfAllElements(TOFlexview.searching));
		int nosOfTOs = TOFlexview.transportOrders.size();
		if (nosOfTOs > 0) {
			StringBuilder sb = new StringBuilder();
			logger.log(LogStatus.PASS, "Transport Order Created Successfully.");
			Iterator<WebElement> itr = TOFlexview.transportOrders.iterator();
			while (itr.hasNext()) {
				sb.append(itr.next().getText() + ", ");
			}
			logger.log(LogStatus.INFO, "Created Transport order ID/s: " + sb.toString().trim());
		} else {
			logger.log(LogStatus.FAIL, "Transport Order has not created from RTS, Please check your data");
			throw new CustomExceptions("Transport Order has not created from RTS, Please check your data");
		}
		TOFlexview.transportOrders.get(0).click();
		logger.log(LogStatus.PASS, "Successfully Landded on Transport order Details page");
	}
}
