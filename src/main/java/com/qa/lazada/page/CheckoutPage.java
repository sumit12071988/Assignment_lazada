package com.qa.lazada.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.lazada.util.Constants;
import com.qa.lazada.util.ElementUtil;
import com.qa.lazada.util.JavaScriptUtil;

public class CheckoutPage {
	WebDriver driver;
	
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// 2. By Locators | Page Objects | Object Repositories	
	By placeOrder = By.xpath("//button[@class='next-btn next-btn-primary next-btn-large checkout-order-total-button automation-checkout-order-total-button-button']");
	
	// 3. Page Methods | Page Actions | Testing activities
	
	public String getCheckoutPageTitle() {						
		elementUtil.waitForTitlePresent(Constants.CHECKOUT_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	// Page Navigator | Ignoring the method as the order is getting placed and Lazada is asking us to pay
//	public PaymentSelectionPage gotoPaymentChannelSelection() {
//		elementUtil.doClick(placeOrder);
//		
//		return new PaymentSelectionPage(driver);
//	}
}
