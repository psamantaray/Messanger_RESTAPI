package com.businessDefination;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tms.transportPlanning.TestRunner;

public class TmsObjectCreation extends TestRunner {

	public void purchaseOrderCreation(File orderXmlFile) {
		wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Administration']")));
		try {
			driver.findElement(By.xpath("//a[text()='Administration']")).click();
	        
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("System Tools")));
	        driver.findElement(By.linkText("System Tools")).click();
	      
	        WebElement systemToolDropDown = driver.findElement(By.id("toolselect"));
	        Select sel = new Select(systemToolDropDown);
	        sel.selectByValue("/sysmgmt/prodsupport.jsp");
	      
	       driver.findElement(By.linkText("Process-Inbound")).click();
	       WebElement poProcess = driver.findElement(By.name("selectMsgType"));
	       sel = new Select(poProcess);
	       sel.selectByValue("5");
	       driver.findElement(By.name("fileinput")).sendKeys(orderXmlFile.getAbsolutePath());
	       driver.findElement(By.xpath("//input[@value='Process']")).click();
	       //driver.findElement(By.linkText("Refresh TIAudit Rows")).click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void searchPurchaseOrder(String OrderNum) throws InterruptedException {
		WebElement chooseAreaDropDown = driver.findElement(By.name("searchtype"));
		WebElement narrowByDropDown = driver.findElement(By.name("searchfieldrow1"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("searchtype")));
		Select sel = new Select(chooseAreaDropDown);
		sel.selectByValue("1");
		sel = new Select(narrowByDropDown);
		sel.selectByVisibleText("Purchase Order");
		driver.findElement(By.name("searchvaluerow1")).clear();
		Thread.sleep(5000);
		driver.findElement(By.name("searchvaluerow1")).sendKeys(OrderNum);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='qstext']")));
		driver.findElement(By.xpath("//input[@class='qstext']")).click();

	}
}
