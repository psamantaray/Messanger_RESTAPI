package com.tms.transportPlanning;

import java.util.Properties;


import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.businessDefination.CommonBusinessFunction;
import com.businessDefination.TmsObjectCreation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dataTable.DataTable;
@RunWith(Cucumber.class)
@CucumberOptions(features="features",glue="stepDefination")
public class TestRunner {
	public static WebDriver driver = null;
	public static CommonBusinessFunction commBusinessFun = null;
	public static TmsObjectCreation tmsObjectCreation = null;
	public static WebDriverWait wait = null;
	public static String dataFileLoc; 
	public static DataTable dataTable = null;
	public static Properties p = null;
	public static String ORFileLoc = null;
	
}