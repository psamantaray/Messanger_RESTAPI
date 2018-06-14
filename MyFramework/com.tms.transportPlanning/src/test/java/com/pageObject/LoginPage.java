package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LoginPage {

	@FindBy(id = "login")
	public WebElement uName;
	
	@FindBy(id = "password")
	public WebElement psw;
	
	@FindBy(id = "loginButton")
	public WebElement loginBtn;
	
}
