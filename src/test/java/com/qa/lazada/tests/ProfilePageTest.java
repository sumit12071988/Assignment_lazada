package com.qa.lazada.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.lazada.base.BasePage;
import com.qa.lazada.page.HomePage;
import com.qa.lazada.page.LoginPage;
import com.qa.lazada.page.ProfilePage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProfilePageTest {


	WebDriver driver;
	
	Properties prop;
	BasePage basePage;
	Credentials userCred;
	
	LoginPage loginPage;
	ProfilePage profilePage;
	


	@BeforeClass(alwaysRun = true)
	@Parameters(value= {"browser"})	
	public void setUp(String browser) throws InterruptedException {
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
		
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		
		profilePage = loginPage.doLogin(userCred);
	}
	
	
	
	
	
	//************** WRITING TESTS***************************************//
	@Test(priority = 1,groups = "sanity")
	@Description("Verify Profile Page Title Test..... ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyProfilePageTitleTest() {
		String title = profilePage.getProfilePageTitle();
		System.out.println("Profile Page title is: "+ title);
		Assert.assertEquals(title,Constants.PROFILE_PAGE_TITLE);
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
