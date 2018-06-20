package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TCXHomePage {
	
	@FindBy(xpath="//div[@id='gtn-navbar']/header/ul/li[6]/a/span")
	public  WebElement messaging;
	
	@FindBy(xpath="//a[@id='navmenu__utility']")
	public WebElement utility;
	
	@FindBy(xpath="//a[text()='Import Message Action Receive']")
	public WebElement importAction;
	
	@FindBy(id="importOrgId-typeahead")
	public WebElement MessageOrg;
	
	@FindBy(id="importDocType")
	public WebElement documentType;
	
	@FindBy(id="importFormat")
	public WebElement fileFormat;
	
	@FindBy(id="importAgentUserId")
	public WebElement AgentuserID;
	
	@FindBy(xpath="//div[@class='tt-menu tt-open']/div/div")
	public WebElement allResult;
	
	@FindBy(name="importFile")
	public WebElement file;
	
	@FindBy(name="Importbutton")
	public WebElement importBtn;
	

	@FindBy(xpath="//span[@class='datafield']/a")
	public WebElement messageUID;
	
	@FindBy(xpath="//form[@name='tradecardForm']/table[3]/tbody/tr/td[2]/table/tbody/tr[4]/td[4]")
	public WebElement boUID;
	
	@FindBy(xpath="//form[@name='tradecardForm']/table[9]/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/span/a")
	public WebElement orderNumber;
	
	
}
