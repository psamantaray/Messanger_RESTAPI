package com.keywords;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {

 WebDriver driver = null;
	WebDriverWait wait = null;

	public Keywords(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	public boolean clickElement(WebElement element, String buttonName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isDisplayed();
		if (status) {
			try {
				element.click();
				System.out.println("Clicked on button --> " + buttonName);
				return status;
			} catch (Exception e) {
				status = false;
				System.out.println("Button is not present -->" + buttonName);
			}
		}
		return status;
	}

	public boolean enterText(WebElement element, String text, String fieldName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isDisplayed();
		if (status) {
			try {
				element.sendKeys(text);
				System.out.println("Enter the provided input String -->" + text);
				return status;
			} catch (Exception e) {
				status = false;
				System.out.println("Element is not present or visble in UI -->" + fieldName);
			}
		}
		return status;
	}

	public boolean cleartextField(WebElement element, String fieldName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isDisplayed();
		if (status) {
			try {
				element.clear();
				return status;
			} catch (NoSuchElementException e) {
				status = false;
				System.out.println("Text flement is not present in UI --> " + fieldName);
			}
		}
		return status;
	}

	public boolean selectOptionByValue(WebElement element, String value, String action) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select sel = new Select(element);
		boolean status = element.isDisplayed();
		if (status) {
			try {
				sel.selectByValue(value);
				return status;
			} catch (Exception e) {
				status = false;
				e.printStackTrace();
				System.out.println(
						"Element is not resent in the UI or the option is not availble in the drop down--> " + action);
			}
		}
		return status;
	}
}
