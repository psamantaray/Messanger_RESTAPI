package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TCXHomePage {
	
	@FindBy(xpath="//div[@id='gtn-navbar']/header/ul/li[6]/a/span")
	public WebElement messaging;
	
	@FindBy(xpath="//a[@id='navmenu__utility']")
	public  WebElement utility;
	
	@FindBy(xpath="//a[text()='Import Message Action Receive']")
	public  WebElement importAction;
	
	@FindBy(id="importOrgId-typeahead")
	public  WebElement MessageOrg;
	
	@FindBy(id="importDocType")
	public  WebElement documentType;
	
	@FindBy(id="importFormat")
	public  WebElement fileFormat;
	
	@FindBy(id="importAgentUserId")
	public  WebElement AgentuserID;
	
}
