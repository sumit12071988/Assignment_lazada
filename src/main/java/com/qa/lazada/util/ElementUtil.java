package com.qa.lazada.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.lazada.base.BasePage;

/**
 * This class is ONLY USED BY PAGE CLASSES for all driver related actions.
 * @author Batto
 *
 */
public class ElementUtil extends BasePage {

	
	// !! 	NO STATIC METHODS INSIDE ELEMENTUTIL.JAVA	!!
	// !!	ALL DRIVER METHODS SHOULD BE HERE	!!
	// !!	NO IMPLICIT WAIT TO BE USED		!! 
	// !!	ONLY WEB DRIVER WAIT TO BE USED. WEBDRIVER WAIT TO BE DEFINED IN GETELEMENT AND INSIDE THOSE PAGE-MEHTODS WHICH DOESN'T USE GETELEMENT

		
	WebDriver driver;
	Properties prop;
	JavaScriptUtil jsUtil;
	
	WebDriverWait wait;
	
	public ElementUtil(WebDriver driver) {
		prop = super.prop;
		this.driver = driver;
		wait = new WebDriverWait(driver,Constants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	
	public boolean waitForElementPresent(By locator) {		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
		return true;
	}
	
	
	
	public boolean waitForElementVisible(By locator) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		return true;
	}
	
	
	public boolean waitForTitlePresent(String title) {		
		wait.until(ExpectedConditions.titleIs(title));		
		return true;
	}
	
	
	
	// This getElement will be the common method which will be called inside all the other methods
	/**
	 * This method is used to CREATE WEBELEMENT using BY LOCATOR
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		
		WebElement element=null;
		
		try {
			if(waitForElementPresent(locator));				// This will check if webelement is present or not and returns true or false.
															// Used in most of APPIUM based frameworks
															// If this doesn't work for application, then we can use individual waits under Page Methods for synchronization.
															// Using this wait will unnecessarily increase wait time since for each and every webElement, it will first wait
															// for element to be visible and then performs the action. Best approach will be to wait for any one weblement in
															//	the webpage and then no need to apply waits since all the web elements will be present if one webelement is
															//	found. only exception will be AGAX WebElements for which individual waits MUST be provided.
			element = driver.findElement(locator);
			if(highlightElement) {		// if true then perform actions
				jsUtil.flash(element);
			}
		}
		catch (Exception e) {
			System.out.println("Some exception occured while CREATING the WEB ELEMENT......."+ locator);
		}
		return element;	// if some exception occurs, element will return null
	}
	
	
	
	
	
	
	
	public void doClick(By locator) {
		try {
		getElement(locator).click();
		}
		catch(Exception e) {
			System.out.println("Some exception occured while CLICKING the WebElement.......");
		}
	}
	
	
	
	
	public void doSendKeys(By locator, String value) {
		try { 
		WebElement ele = getElement(locator);
		 ele.clear();
		 ele.sendKeys(value);
		}
		catch (Exception e) {
			System.out.println("Some exception occured while ENTERING VALUES in the WebElement........"+ locator);
		}
	}
	
	
	
	
	
	public boolean doIsDisplayed(By locator) {		
		try {
		return getElement(locator).isDisplayed();
		}
		catch (Exception e) {
			System.out.println("Some exception occured while diplaying......."+ locator);
		}
		return false;	  // return default value of Boolean
	}
	
	
	
	
	
	
	
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}
		catch(Exception e) {
			System.out.println("Some exception occured while GETTING TEXT from WebElement......."+ locator);
		}
		return null;  // return default value of String
	}
	
	
	
	
	
	
	
	public String doGetAttribute(By locator) {
		try {
		return getElement(locator).getAttribute("textContent");
		}
		catch(Exception e) {
			System.out.println("Some exception occured while GETTING ATTRIBUTE of TEXT from WebElement......."+ locator);
		}
		return null;  // return default value of String
	}
	
	
	
	
	
	/**
	 * This is the only method which doesn't use getElement. 
	 * Thus any page methods which is calling this method, MUST use ElementUtil's waitForElementPresent OR waitForElementVisible
	 * @return
	 */
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}
		catch (Exception e) {
		System.out.println("Some exception occured whicle GETTING PAGE TITLE of WebPage........");
		}
		return null;  // return default value of String
	}

}
