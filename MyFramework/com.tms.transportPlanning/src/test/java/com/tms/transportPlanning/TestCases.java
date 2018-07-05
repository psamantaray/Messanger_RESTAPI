/**
 * 
 */
package com.tms.transportPlanning;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.businessDefination.CommonBusinessFunction;
import com.keywords.CustomExceptions;

/**
 * @author psamantaray
 *
 */
public class TestCases extends TestRunner {
	
	//Test Case: Transport Order Creation from UO-->RTS.
	/**
	 * @param browser
	 * @param appUrl
	 * @param UOfilePath
	 * @throws InterruptedException
	 * @throws CustomExceptions
	 */
	@Test(enabled = false)
	@Parameters({ "browser", "appUrl", "UOfilePath" })
	public void UO_RTS_TOCreationFlow(String browser, String appUrl, String UOfilePath)
			throws InterruptedException, CustomExceptions {

		commBusinessFun = new CommonBusinessFunction();
		commBusinessFun.launchBrowser(browser);
		commBusinessFun.launchApplication(appUrl);
		commBusinessFun.loginTOApplication();
		String orderNumber = commBusinessFun.createUO(System.getProperty("user.dir") + UOfilePath);
		System.out.println("Order Number: " + orderNumber);

		// Switch to GTNX Application
		commBusinessFun.switchToGTNXAppFromTCX();
		commBusinessFun.shadowuserLogin();
		commBusinessFun.searchForTOInFlexViewUsingOrderNumber(orderNumber);
		commBusinessFun.FEFTOItemsTabValidation();
	}

	// Transport Order creation from Independent RTS.	
	@Test(enabled = true)
	@Parameters({ "browser", "appUrl", "RTSfilePath" })
	public void IndependentRTSCreation(String browser, String appUrl, String RTSfilePath)
			throws InterruptedException, CustomExceptions {

		commBusinessFun = new CommonBusinessFunction();
		commBusinessFun.launchBrowser(browser);
		commBusinessFun.launchApplication(appUrl);
		commBusinessFun.loginTOApplication();
		String rtsNumber = commBusinessFun.createIndependentRTS(System.getProperty("user.dir") + RTSfilePath);
		System.out.println("RTS Number: " + rtsNumber);

		// Switch to GTNX Application
		commBusinessFun.switchToGTNXAppFromTCX();
		commBusinessFun.shadowuserLogin();
		commBusinessFun.searchForTOInFlexViewUsingRTSNumber(rtsNumber);
		commBusinessFun.FEFTOItemsTabValidation();
	}
	
	// Add your test cases here.
	

}
