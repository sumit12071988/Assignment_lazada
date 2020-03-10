package com.qa.lazada.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.lazada.base.BasePage;
import com.qa.lazada.page.CheckoutPage;
import com.qa.lazada.page.HomePage;
import com.qa.lazada.page.LoginPage;
import com.qa.lazada.page.ProductPage;
import com.qa.lazada.page.ProfilePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;

public class CheckoutPageTest {
	WebDriver driver;
	
	Properties prop;
	BasePage basePage;
	Credentials userCred;
	
	LoginPage loginPage;
	ProfilePage profilePage;
	HomePage homePage;
	ProductPage productPage;
	CheckoutPage checkoutPage;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop.getProperty("browser"));		
		driver.get(prop.getProperty("url"));
		
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		profilePage = loginPage.doLogin(userCred);
		
		homePage = profilePage.goToHomePage();
		
		productPage = homePage.goToSelectedProductPage();
		
		checkoutPage = productPage.goToCheckOutPage();
	}
	
	@Test(priority = 1)
	public void verifyCheckoutPageTitle() {
		String title = checkoutPage.getCheckoutPageTitle();
		System.out.println("CheckOutPage title is: " + title);
		Assert.assertEquals(title,Constants.CHECKOUT_PAGE_TITLE);
	}	
	 
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
