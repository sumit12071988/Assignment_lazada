package com.qa.lazada.page;

import org.openqa.selenium.WebDriver;

import com.qa.lazada.util.Constants;
import com.qa.lazada.util.ElementUtil;
import com.qa.lazada.util.JavaScriptUtil;

public class PaymentSelectionPage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	public PaymentSelectionPage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
	jsUtil = new JavaScriptUtil(driver);
	}

	
	public String getCheckoutPageTitle() {						
		elementUtil.waitForTitlePresent(Constants.CHECKOUT_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
}
