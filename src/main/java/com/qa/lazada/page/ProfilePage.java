package com.qa.lazada.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.lazada.base.BasePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.ElementUtil;

/**
* All Page Classes has 3 components
* @note: Constructor 	| It receives the driver from calling party and passes the driver to this class
* @note: By Locators 	| To store all the WebElements on the current page
* @note: Page Methods 	| To create methods which performs the testing activity of test case + Method which returns object of next page 
* @author Sumit.Saha
*
*/
public class ProfilePage extends BasePage {

	WebDriver driver;	
	ElementUtil elementUtil;

	// 1. Constructor of Page Class --> 
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	

	// 2. By Locators | Page Objects | Object Repositories
	
	By homepageImg = By.xpath("//img[@src='//laz-img-cdn.alicdn.com/images/ims-web/TB1HKyxaMFY.1VjSZFqXXadbXXa.png']");
	

	
	// 3. Page Methods | Page Actions | Testing activities
	
	public String getProfilePageTitle() { 	// For Every page title method should be there
		
		elementUtil.waitForTitlePresent(Constants.PROFILE_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}

	// 4. Next Page Navigator
	public HomePage goToHomePage() {
		elementUtil.doClick(homepageImg);		
		return new HomePage(driver);
	}
	
	
}
