package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TODetailsPage {
	
	//Items Tab
	@FindBy(xpath="//a[text()='Items']")
	public WebElement itemsTab;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[1]/span[2]/div/button")
	public WebElement expandButton;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[1]/span[2]/span/span")
	public WebElement lineItemNum;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[2]/span[2]/span")
	public WebElement productName;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[3]")
	public WebElement commodity;
	
	@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[4]")
	public WebElement commodityClass;
	

}
