package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tms.transportPlanning.TestRunner;

public class Listner extends TestRunner implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test case Execution Started!");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case executed successfully!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Execution Failed!");
		Screenshot.captureScreenshot(driver, result.getName());
		System.out.println("Screenshot Captured!");
		
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
