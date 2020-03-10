package com.qa.lazada.page;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.lazada.base.BasePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;
import com.qa.lazada.util.ElementUtil;
import com.qa.lazada.util.JavaScriptUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	
	
	// 1. Constructor of Page Class --> 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	// 2. By Locators | Page Objects | Object Repositories
	
	By googlePlus = By.xpath("//button[@class='mod-button mod-button mod-third-party-login-btn mod-third-party-login-google']");
	By userName = By.xpath("//input[@type='email']");
	By userNameBtn = By.xpath("//span[@class='CwaK9']");
	By password = By.xpath("//input[@type='password']");
	By passwordBtn = By.xpath("(//span[@class='RveJvd snByac'])[1]");
	
	
	
	// 3. Page Methods | Page Actions | Testing activities
	
	public String getLoginPageTitle() throws InterruptedException {						
		String loginPageTitle= elementUtil.doGetPageTitle();
		System.out.println("Page Method LoginPageTitle is: "+loginPageTitle);
		return loginPageTitle ;
	}
	
	
	// 4. Next Page Navigator
	public ProfilePage doLogin(Credentials userCred) throws InterruptedException {
		
		elementUtil.waitForElementPresent(googlePlus);
		
		elementUtil.doClick(googlePlus);
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		System.out.println("Parent Window Id is: " + parentWindowId);
		String childWindowID = it.next();
		System.out.println("Child Window ID is: " + childWindowID);
		
		driver.switchTo().window(childWindowID);		// Now driver is at ChildWindow
		System.out.println(driver.getTitle());
		
		elementUtil.doSendKeys(userName, "deals4sumit@gmail.com");
		elementUtil.doClick(userNameBtn);
		
		Thread.sleep(3000);
		elementUtil.doSendKeys(password, "6037959884858628");
		Thread.sleep(3000);
		elementUtil.doClick(userNameBtn);
		
		driver.switchTo().window(parentWindowId);
		
		Thread.sleep(10000);
		
		return new ProfilePage(driver);
	}

	
}
