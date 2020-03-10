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
import com.qa.lazada.page.HomePage;
import com.qa.lazada.page.LoginPage;
import com.qa.lazada.page.ProfilePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	
	Properties prop;
	BasePage basePage;
	Credentials userCred;
	
	LoginPage loginPage;
	ProfilePage profilePage;
	HomePage homePage;
	
	
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
	}

	
	
	@Test(priority = 1)
	public void verifyhomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("HomePage title is: " + title);
		Assert.assertEquals(title,Constants.HOME_PAGE_TITLE);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
}