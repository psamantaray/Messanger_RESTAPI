package com.pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TOFlexview {

		@FindBy(xpath = "//div[@class='sohoxi']/span/div[2]/div[2]/div/div/div/div/div[2]/div[2]/span/span[2]/span/span/input")
		public WebElement orderNumber;
		
		@FindBy(xpath = "//div[@class='filterset']/div[13]/span/span[2]/span/span/input")
		public WebElement RTSNumber;
		
		@FindBy(xpath="//button[@title='Apply']")
		public WebElement apply;
		
		@FindBy(xpath="//div[contains(@class,'flexresults')]/table/tbody/tr/td[1]/span[2]/span/span/a")
		public List<WebElement> transportOrders;
		
		@FindBy(xpath="//td[contains(text(),'Searching')]")
		public List<WebElement> searching;
		
		
		
}

