package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GtnexusNHHomePage {
	@FindBy(id = "loginAsField-input")
	public WebElement shadowUser;
	
	@FindBy(xpath = "//button[text()='Login']")
	public WebElement loginButton;
	
	@FindBy(linkText = "Home")
	public WebElement homeLink;
}
