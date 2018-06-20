package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tms.transportPlanning.TestRunner;

public class Listner extends TestRunner implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test case Execution Started!");
		System.out.println("Test Case Name: "+result.getName());
		long startTimeMiliSecond = result.getStartMillis();
		Date date = new Date(startTimeMiliSecond);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String startDateAndTime = format.format(date);
		System.out.println("Test case Execution Start Time: "+ startDateAndTime);
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case executed successfully!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Execution Failed!");
		
		UtilityMethods.captureScreenshot(driver, result.getName());
		System.out.println("Screenshot Captured!");
		//driver.close();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
