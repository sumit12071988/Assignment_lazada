package com.qa.lazada.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Purpose of Options Manager is have one class to configure all Browser options set and keep BasePage.java neat and clean
 * @author Sumit.Saha
 *
 */
public class OptionsManager {

	public Properties prop;
	public ChromeOptions co;
	public FirefoxOptions fo;
	
	// Creating Object of this class will pass property reference to this class using constructor
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	
	
	
	
	// This method will ALWAYS make Chrome to open in incognito mode and opens in Headless or non-headless based on config properties
	
	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		
		if (prop.getProperty("incognito").equals("yes")) {
		co.addArguments("--incognito");
		}
		
		if (prop.getProperty("headless").equals("yes")) {
		co.addArguments("--headless");
		}
		
		//	For other arguments in ChromeOptions, refer https://peter.sh/experiments/chromium-command-line-switches/
		
		return co;
	}
	
	
	//*****************************************************************************************************************//
	
	
	public FirefoxOptions getFirefoxOptions() {
		
		fo = new FirefoxOptions();
		
		if (prop.getProperty("incognito").equals("yes")) {
		fo.addArguments("--incognito");
		}
		
		
		if (prop.getProperty("headless").equals("yes")) {
		fo.addArguments("--headless");
		}
		
		//	For other arguments in FirefoxOptions, refer https://www.selenium.dev/selenium/docs/api/py/webdriver_firefox/selenium.webdriver.firefox.options.html
		
		return fo;
	}
	
	
	
	
}
