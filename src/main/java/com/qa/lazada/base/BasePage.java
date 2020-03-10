package com.qa.lazada.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This Class is the PARENT of all Page Classes
 * @Note: This class provides two methods:
 * @note: Calling init_properties() will LOAD the config file to memory
 * @note: Calling init_driver(browserName) will OPEN browser based on the browser value passed to it.
 * 
 * @author Sumit.Saha
 *
 */
public class BasePage {
	
	public Properties prop;	// We're making the Properties class reference "prop" global. Creating any class reference or interface reference in method will limit its scope to 
							//		that method.
	
	//public WebDriver driver;	// Not required anymore after implementation of ThreadLocal class
	
	public OptionsManager optionsManager;	// This when invoked, will make drive to open based on the desired properties like incognito, headless etc..
	
	public static boolean highlightElement;
	
	
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {		// getDriver() method will behave as same as driver
			return tldriver.get();
		}
	
	
	// !! NO STATIC METHODS INSIDE BASEPAGE.JAVA !!
	
	
	
	
	
	
	/**
	 * Calling this method will READ the config.properties file by connecting the file (using FileInputStream) and loading the file (using load() method) 
	 * @return prop
	 */
	public Properties init_properties() {
		prop = new Properties();	// This class is responsible to read the data from properties file and return a property Object reference with loaded config.prop values.
									// making connection with config.properties
		String path =null;
		String env = null;
	
		
		//**************** To find out File path of which config file to be used in FIleInputStream based on the environment **************** 
		
			try {
			env = System.getProperty("env");		//	Inside System.getProperty(env); --> env is the ENVIRONMENT VARIABLE. other examples like OS, JAVA_HOME
													// 		we'll set the "env" inside getProperty during RUNTIME using COMMAND LINE
			
			
			
			//******************************* LOCATING ANY PROJECT FILE *******************************//
			
			// Project always runs at "ProjectName i.e. Dec2019POMSeries" level which is represented as ".". Thus to locate any file inside the Project directory, we can use the
			//	PATH of the file mentioned inside PROPERTIES[ALT + ENTER] and navigate from ./src/........
			// This will make the file generic and avoid any dependency on local machines and its directories
			
			
			if (env.equals("qa")) {
				path="./src/main/java/com/qa/lazada/config/qa.config.properties";		// qa config.properties
			}
			else if (env.equals("stg")) {
				path="./src/main/java/com/qa/lazada/config/stg.config.properties";		// staging config.properties
				}
			}
			catch(Exception e) {
			path="./src/main/java/com/qa/lazada/config/config.properties";				// pdc config.properties
		}

		//************** RUNNING THE SCRIPT IN DIFFERENT ENVIRONMENT FROM COMMAND LINE ****************
		//	1. Rt. click on Project --> Properties --> Copy the LOCATION.
		//	2. Open CMD and navigate to that path copied from LOCATION.
		//	3. enter the command:
		
		//		mvn clean install -Denv=qa
		//		mvn clean install			--> This will run the the else part (config.properties) file
			
		//			Note: In MAVEN, -D is used to setup Environment Variable
		//			This command will make maven to clean, compile and then execute the project with the environment variable you gave
		

		//************** RUNNING THE SCRIPT IN DIFFERENT ENVIRONMENT FROM ECLIPSE ****************
		//	Run Test class file, there will be a nullpointer exception, and catch part i.e. config.properties file will handle and run
			
		
		
		//************** RUNNING THE SCRIPT IN DIFFERENT ENVIRONMENT FROM ECLIPSE-TERMINAL ****************
		//	Open Terminal (CTRL+SHIFT+ALT+T) --> Local Terminal --> follow the steps of COMMAND LINE mode of execution.
			
			
		
		//************** RUNNING THE SCRIPT IN DIFFERENT ENVIRONMENT FROM ECLIPSE-Maven ****************
		//	Rt. Click on Project --> Run As --> Maven Build(1st option) --> Navigate to Environment Variables --> Create a new Variable "env" and value as qa or stg etc..
		//		Once done, click on Apply and RUN. This is not a preferred way though. We need to be comfortable with Command Line mode of execution using Terminals	
		
			
		// Note: These parameters (-Denv =stg or qa) can be passed directly in JENKINS. Inside Jenkins Jobs, we'll mention maven clean install -D<Jenkins env> parameter
			
		// 	Q: Difference b/w maven clean install and maven test ?
		//	A: maven clean install --> follows a lifecycle:
		//		1. Clean the project
		//		2. Generate the resources						--> src/main/resources
		//		3. Compile the code								--> src/main/java
		//		4. Navigate to Test Resources you've created
		//		5. Navigate to testCompile and compile the code	--> src/test/java
		//		6. Execute your test cases
			
		//	To skip the entire maven lifecycle of compilations of main class files as well as test class files, we can switch to:
		//			mvn clean install -Denv=qa	--> mvn test -Denv=qa	OR	mvn test
		//	To execute only 1 Test class file on a particular environment, goto Command Prompt and mention the same code we used in testng_Regression.xml file	
		//				mvn clean install -Dtest=com.qa.hubspot.tests.LoginPageTest -Denv=qa
		//				mvn clean install -Dtest=com.qa.hubspot.tests.HomePageTest -Denv=stg
		//				mvn clean install -Dtest=com.qa.hubspot.tests.* -Denv=stg		--> This will run ALL tests in that package	
			
			
		// We can use "-D" keyword in command line to provide the Browser as well e.g:
		//	mvn clean install -Dtest=com.qa.hubspot.tests.* -Denv=stg -Dbrowser=chrome	--> Environment Variable is "browser"
		
		//	In such case in BasePage.java, we need to amend so the System.getProperty("browser") is being passed as browser under init_driver method
		//		This method of passing multiple environmental variable in command line is used mostly in Selenium Grid e.g:
		//	mvn clean install -Dtest=com.qa.hubspot.tests.* -Denv=stg -Dbrowser=chrome -Durl=https://app.hubspot.com/login
		//	Note: to do all these, make sure MAVEN IS INSTALLED in your system. to verify in cmd : mvn -version.
		//		If maven is not installed, use : https://mkyong.com/maven/how-to-install-maven-in-windows/  to install maven
			
		try {
			FileInputStream fis = new FileInputStream(path);	//		i.e "." represents C:\ECLIPSE WORKSPACE\Dec2019POMSeries
			// FileInputStream class will CONNECT with config.properties 
			//We've to provide the path of config.properties file. For that goto config.properties file --> ALT+ENTER --> copy "Location"
			// Once done, we need to LOAD the properties
			
			prop.load(fis);		//	This will load all the config properties into inside Properties reference "prop"																					//			"." also represents /Dec2019POMSeries part of path
		} 
		catch (FileNotFoundException e) {
			System.out.println("Unable to locate config properties.... Please correct your config address....");
		}
		catch (IOException e) {
			System.out.println("Some issue with loading the config.properties file...... Please check....");
		}
		
		return prop;
		// This make sure that whenever any one is calling this method, we'll get the Properties object reference so that using that reference, we can access all the properties
		//	that we've mentioned inside config.properties file.
	}
	
	
	
	
	
	//******************************************************************************************************************************//
	//******************************************************************************************************************************//
	//******************************************************************************************************************************//
	//******************************************************************************************************************************//
	
	
	/**
	 * Calling this method will initialize the driver [Open the browser] on the basis of BROWSER NAME which is passed by the calling method + LAUNCHES the URL
	 * @param browserName
	 * @return driver
	 */
	public WebDriver init_driver(String browserName) {	
		
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;	// another way to check true or false 
		
		System.out.println("Browser name is: "+browserName);
		
		optionsManager = new OptionsManager(prop);						// creating object for OptionsManager class and sending Property reference to the class.
		
				
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();		// Installs the Chromedriver.exe on its own using boniegarcia's dependency
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		
		
		
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		
		
		
		
		
		
		
		
		else if (browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();	// this is the main Objective of this class i.e. to Initialize the Driver | Opening the browser
			tldriver.set(new SafariDriver());
		}
		else {
			System.out.println("Browser Name in not found. Please specify correct browser in config.properties");
		}
		
		
		
		
		
		
		
		
		getDriver().manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // We shouldn't use implicitly wait and explicit wait together
		getDriver().manage().window().maximize();

		return getDriver();		// getDriver() == driver
	}	
	
	
	
	/**
	 * take screenshot
	 */

	public String getScreenshot() {

		// Taking the screenshot of File type and copying it into src File
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		// Creating a path using project directory (i.e. System.getProperty("user.dir")) + screenshot folder + System time + filetype
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		
		// Saving the path as a Destination File
		File destination = new File(path);

		try {
			//	Copying the screenshot from src to destination
			FileUtils.copyFile(src, destination);
			
		} catch (IOException e) {
			System.out.println("screenshot captured failed...");
		}

		return path;

	}

}
