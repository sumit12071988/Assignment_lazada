package com.qa.lazada.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.lazada.base.BasePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.ElementUtil;
import com.qa.lazada.util.JavaScriptUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	
	// Constructor
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	// By Locators
	
	By textSearch = By.xpath("//input[@type='search']");
	By searchBtn = By.xpath("//button[@class='search-box__button--1oH7']");
	By productSelected = By.linkText("Bose Soundbar 700");

	
	// Page methods
	public String getHomePageTitle() {
		elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}

	
	// Next page navigator
	public ProductPage goToSelectedProductPage() throws InterruptedException {
		elementUtil.doSendKeys(textSearch,"bose soundbar 700");
		Thread.sleep(3000);
		elementUtil.doClick(searchBtn);
		elementUtil.doClick(productSelected);
		return new ProductPage(driver);
	}
	

}
