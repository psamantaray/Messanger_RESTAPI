package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TCXHomePage {
	
	@FindBy(xpath="//div[@id='gtn-navbar']/header/ul/li[6]/a/span")
	public static WebElement messaging;
	
	@FindBy(xpath="//a[@id='navmenu__utility']")
	public static WebElement utility;
	
	@FindBy(xpath="//a[text()='Import Message Action Receive']")
	public static WebElement importAction;
	
	@FindBy(id="importOrgId-typeahead")
	public static WebElement MessageOrg;
	
	@FindBy(id="importDocType")
	public static WebElement documentType;
	
	@FindBy(id="importFormat")
	public static WebElement fileFormat;
	
	@FindBy(id="importAgentUserId")
	public static WebElement AgentuserID;
	
	
	
	
}
