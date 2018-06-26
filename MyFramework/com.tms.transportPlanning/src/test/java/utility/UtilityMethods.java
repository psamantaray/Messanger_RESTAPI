package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.tms.transportPlanning.TestRunner;

public class UtilityMethods extends TestRunner {

	public static void captureScreenshot(String screenshotName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Screenshot/" + screenshotName + ".png"));
		} catch (Exception e) {
			System.out.println("Issue with your provided path could not take the screenshot" + e);
		}
	}

	public static String TakeSnapshot(String screenshotName) {
		String path = null;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Screenshot/" + screenshotName + ".png"));
			path = System.getProperty("user.dir")+"/Screenshot/"+screenshotName+".png";
		} catch (Exception e) {
			System.out.println("Issue with your provided path could not take the screenshot" + e);
		}
		return path;
	}
}
