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
import com.qa.lazada.page.PaymentSelectionPage;
import com.qa.lazada.page.ProductPage;
import com.qa.lazada.page.ProfilePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;
/**
 * Ignoring the class as clicking on "Place Order" makes Lazada ask customer to pay"
 * @author Sumit.Saha
 *
 */
//public class PaymentSelectionPageTest {
//	WebDriver driver;
//	
//	Properties prop;
//	BasePage basePage;
//	Credentials userCred;
//	
//	LoginPage loginPage;
//	ProfilePage profilePage;
//	HomePage homePage;
//	ProductPage productPage;
//	CheckoutPage checkoutPage;
//	PaymentSelectionPage paymentSelectionPage;
//	
//	@BeforeClass
//	public void setUp() throws InterruptedException {
//		
//		basePage = new BasePage();
//		prop = basePage.init_properties();
//		driver = basePage.init_driver(prop.getProperty("browser"));		
//		driver.get(prop.getProperty("url"));
//		
//		loginPage = new LoginPage(driver);
//		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
//		
//		profilePage = loginPage.doLogin(userCred);
//		
//		homePage = profilePage.goToHomePage();
//		
//		productPage = homePage.goToSelectedProductPage();
//		
//		checkoutPage = productPage.goToCheckOutPage();
//		paymentSelectionPage = checkoutPage.gotoPaymentChannelSelection();
//	}
//	
//	@Test(priority = 1)
//	public void verifyFullFlowTest() {
//		String title = paymentSelectionPage.getCheckoutPageTitle();
//		System.out.println("PaymentSelectionPage title is: " + title);
//		Assert.assertEquals(title,Constants.PAYMENT_SELECTION_PAGE_TITLE);
//	}	
//	 
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}
//}
