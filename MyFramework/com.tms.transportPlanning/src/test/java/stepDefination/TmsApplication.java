package stepDefination;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.businessDefination.CommonBusinessFunction;
import com.businessDefination.TmsObjectCreation;
import com.tms.transportPlanning.TestRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataTable.DataTable;
@Listeners({utility.Listner.class})
public class TmsApplication extends TestRunner {

	public TmsApplication() {
		try {
			commBusinessFun = new CommonBusinessFunction();
			tmsObjectCreation = new TmsObjectCreation();
			dataFileLoc = System.getProperty("user.dir")+"\\DataFile.xlsx";
			ORFileLoc = System.getProperty("user.dir")+"\\OR.properties";
			dataTable = new DataTable();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	@Given("^open one browser$")
	public void open_one_browser() throws Throwable {
		driver = commBusinessFun.launchBrowser("");
	}

	@Given("^launch GTNexus application$")
	public void launch_GTNexus_application() throws Throwable {
		commBusinessFun.launchApplication(dataTable.getValue("appUrl"));
	}

	@When("^user logged in with valid username and password$")
	public void user_logged_in_with_valid_username_and_password() throws Throwable {
		commBusinessFun.loginTOApplication(dataTable.getValue("nhUser"));
	}

	@Then("^home page should be displayed$")
	public void home_page_should_be_displayed() throws Throwable {
		if (driver.getTitle().equalsIgnoreCase("Search")) {
			System.out.println("Successfully Logged In");
		} else
			System.out.println("Invalid user name or password");

	}

	@Given("^navigate to GTNx application page$")
	public void navigate_to_GTNx_application_page() {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("navmenu__user"))).click().perform();
		act.click(driver.findElement(By.id("navmenu__switch to gtn nh"))).perform();
	}

	@When("^user process a orderFile$")
	public void user_process_a_orderFile() throws Throwable {
		File orderXmlFile = new File("D:\\destop doc\\GTNX\\TMSSynergyPO1.xml");
		tmsObjectCreation.purchaseOrderCreation(orderXmlFile);
	}

	@Then("^Purchase order should be created successfully$")
	public void purchase_order_should_be_created_successfully() throws Throwable {

	}

	@Given("^shadow login as a shipper user$")
	public void shadow_login_as_a_shipper_user() throws Throwable {
		//commBusinessFun.shadowuserLogin(dataTable.getValue("shadowUser"));
		Thread.sleep(10000);
	}

	@When("^search for the purchase order$")
	public void search_for_the_purchase_order() throws Throwable {
		tmsObjectCreation.searchPurchaseOrder("PO_Auto0075PP1");
	}

	@Then("^Order number with id should be displayed$")
	public void order_number_with_id_should_be_displayed() throws Throwable {
		
	}
}
