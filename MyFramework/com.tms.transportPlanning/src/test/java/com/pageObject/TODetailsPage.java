package com.pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TODetailsPage {
	
	//Items Tab
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr")
	public List<WebElement> nosOfLineItems;

	@FindBy(xpath="//a[text()='Items']")
	public WebElement itemsTab;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[1]/span[2]/div/button")
	public List<WebElement> expandButton;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[1]/span[2]/span/span")
	public List<WebElement> lineItemNum;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[2]/span[2]/span")
	public List<WebElement> productName;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[3]")
	public List<WebElement> commodity;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[4]")
	public List<WebElement> commodityClass;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr[contains(@class,'masterTableInset')]")
	public List<WebElement> lineItemDetails;
}
