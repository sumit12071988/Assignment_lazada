package com.qa.lazada.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.lazada.base.BasePage;
import com.qa.lazada.page.HomePage;
import com.qa.lazada.page.LoginPage;
import com.qa.lazada.util.Constants;
import com.qa.lazada.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

// @Epic and @Feature are added at class level


@Epic("Epic - 101: Create Login Page Features")					// Epic Annotation is part of Allure Reports which describes on the feature (Agile Sprints epics). Its not a user story
@Feature("US - 501: Create test for Login Page on hubspot")		// Features annotation is part of Allure reports. Its like User stories
public class LoginPageTest {


	WebDriver driver;
	
	Properties prop;
	BasePage basePage;
	Credentials userCred;
	
	LoginPage loginPage;
	

	@BeforeClass(alwaysRun = true)
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
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	
}
