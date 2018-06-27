package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShipperUserHomePage {

	@FindBy (xpath = "//div[@class='toolbar']/div/h1/span")
	public WebElement homeLink;
	
	@FindBy (id = "navmenu__applications")
	public WebElement application;
	
	@FindBy (id = "navmenu__transportorder")
	public WebElement transportOrder;
	
	
	@FindBy (id = "navmenu__planning")
	public WebElement transportPlanning;
	
	@FindBy(id = "gtn-navbar")
	public WebElement tabBar;
	
}
