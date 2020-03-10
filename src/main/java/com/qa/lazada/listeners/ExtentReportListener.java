package com.qa.lazada.listeners;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.lazada.base.BasePage;

public class ExtentReportListener extends BasePage implements ITestListener {
	//	ITestListener is a TestNG Interface
	
	// Our objective is that the Report "TestExecutionReport.html" gets generated under ./build/ folder
	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	
	// Extent Report works on ExtentReports class having reference extent calling init() method | initialization method
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	// INITIALIZATION METHOD CONFIGURATIONS
	private static ExtentReports init() {		
		
		
		
		
		// Verifying if the Report O/P Path folder(directory) is not created then we'll create the path directory using path ./build/
		Path path = Paths.get(OUTPUT_FOLDER);		
		// if directory doesn't exists, create directory
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// Configuring Report contents like: Document Title, Report Name, Chart Location and Theme (Standard/ Dark)
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
			htmlReporter.config().setDocumentTitle("Automation Test Results");
			htmlReporter.config().setReportName("Automation Test Results");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);

		
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

		return extent;
	}

	
	
	
	// This will run BEFORE Test cases starts
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}
	
	

	// This will run AFTER Test cases ends
	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();		// Whatever the objects are created during Extent Report 
		test.remove();		//	will be flushed (closing connection with Extent Report) and removed....
	}

	
	// This will run DURING Test case executions
	@Override
	public synchronized void onTestStart(ITestResult result) {
		
		// We're getting the method Name
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		// We're printing in console that method has started
		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	
	
	
	// Actions that will initiate when test case is SUCCESSFUL. It will be marked as PASS in extent report
	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		try {
			test.get().pass("Test passed",
				MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException e) {
			System.err
					.println("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	
	
	// Actions that will initiate when test case is FAILED. It will be marked as FAIL and SCREENSHOT is attached in extent report
	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		try {
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException e) {
			System.err
					.println("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	
	
	// Actions that will initiate when test case is SKIPPED.
	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		try {
			test.get().skip(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException e) {
			System.err
					.println("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	
	
	

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	

}
