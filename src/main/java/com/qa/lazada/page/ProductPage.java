package com.qa.lazada.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.qa.lazada.base.BasePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.ElementUtil;
import com.qa.lazada.util.JavaScriptUtil;

public class ProductPage extends BasePage {

	WebDriver driver;
	
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// 2. By Locators | Page Objects | Object Repositories	
	By buyNow = By.xpath("//button[@class='add-to-cart-buy-now-btn  pdp-button pdp-button_type_text pdp-button_theme_yellow pdp-button_size_xl']");
	
	
	// 3. Page Methods | Page Actions | Testing activities
	
	public String getProductPageTitle() {						
		elementUtil.waitForTitlePresent(Constants.PRODUCT_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	// Next page navigator
	public CheckoutPage goToCheckOutPage() {
		elementUtil.doClick(buyNow);
		return new CheckoutPage(driver);
	}
	
}
