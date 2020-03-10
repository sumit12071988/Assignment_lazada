package com.qa.lazada.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.lazada.base.BasePage;
import com.qa.lazada.page.CheckoutPage;
import com.qa.lazada.page.HomePage;
import com.qa.lazada.page.LoginPage;
import com.qa.lazada.page.ProductPage;
import com.qa.lazada.page.ProfilePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class EndToEndPurchaseTest {

	WebDriver driver;
	
	Properties prop;
	BasePage basePage;
	Credentials userCred;
	
	LoginPage loginPage;
	ProfilePage profilePage;
	HomePage homePage;
	ProductPage productPage;
	CheckoutPage checkoutPage;
	
	
	@BeforeTest(alwaysRun = true)
	@Parameters(value= {"browser"})	
	public void setUp(String browser) {
		String browserName = null;
		basePage = new BasePage();		
		prop = basePage.init_properties(); 
		
		if (browser.equals(null)) {
			browserName = prop.getProperty("browser");			
		}
		else {
			browserName = browser;
		}	
		

		driver = basePage.init_driver(browserName);	
		driver.get(prop.getProperty("url"));
		
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		loginPage = new LoginPage(driver);	
	}
	
	
	

	
	//************** WRITING TESTS***************************************//
	//	Here we call those Page methods and do Hard and Soft Assertions.
	
	
	@Test(priority = 1, description = "Verify Login Page Title Test....!!!")
	@Description("Verify LoginPage Title Test..... ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is: "+title);
		Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	
	
	
	@Test(priority = 2)
	@Description("Verify Profile Page Title Test..... ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyProfilePageTitleTest() throws InterruptedException {
		
		profilePage = loginPage.doLogin(userCred);
		
		String title = profilePage.getProfilePageTitle();
		System.out.println("Profile Page title is: "+ title);
		Assert.assertEquals(title,Constants.PROFILE_PAGE_TITLE);
	}
	
	
	@Test(priority = 3)
	public void verifyhomePageTitle() {
		
		homePage = profilePage.goToHomePage();
		
		String title = homePage.getHomePageTitle();
		System.out.println("HomePage title is: " + title);
		Assert.assertEquals(title,Constants.HOME_PAGE_TITLE);
	}
	
	
	@Test(priority = 4)
	public void verifyProductPageTitle() throws InterruptedException {
		
		productPage = homePage.goToSelectedProductPage();
		
		String title = productPage.getProductPageTitle();
		System.out.println("ProductPage title is: " + title);
		Assert.assertEquals(title,Constants.PRODUCT_PAGE_TITLE);
	}
	
	
	@Test(priority = 5)
	public void verifyCheckoutPageTitle() {
		
		checkoutPage = productPage.goToCheckOutPage();
		
		String title = checkoutPage.getCheckoutPageTitle();
		System.out.println("CheckOutPage title is: " + title);
		Assert.assertEquals(title,Constants.CHECKOUT_PAGE_TITLE);
	}
	
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
}
