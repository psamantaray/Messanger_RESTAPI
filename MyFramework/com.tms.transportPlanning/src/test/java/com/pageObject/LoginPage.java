package com.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LoginPage {

	@FindBy(id = "login")
	public static WebElement uName;
	
	@FindBy(id = "password")
	public static WebElement psw;
	
	@FindBy(id = "loginButton")
	public static WebElement loginBtn;
	
}
