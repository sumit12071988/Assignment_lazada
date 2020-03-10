package com.qa.lazada.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;
	
	// Passing the driver to this class using constructor
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
	}
	
	//JavascriptExecutor js = (JavascriptExecutor) driver;			
	
		//	JavascriptExecutor is an INTERFACE
		//	(JavascriptExecutor) driver --> Typecasting to JavascriptExecutor i.e. converting driver into JavascriptExecutor 
		//		Thus the entire right hand side will be considered as JavascriptExecutor object. Thus have to store it
		//		inside JavascriptExecutor reference variable.
		
		// executeScript is the method used by JavascriptExecutor to run the javascripts
	
	
	// since driver is already be sent to this class when object of this class is created, thus we don't need to pass WebDriver to each and every method
		
		
		/**
		 * TO REFRESH BROWSER
		 * @param driver
		 */
		public void refreshBrowserByJS() {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("history.go(0)");										// Interview Question: how to refresh the WebPage using JS.
		}
		
		//***********************************************************************************************************//
		
		/**
		 * TO CHECK IF WEBPAGE IS LOADED OF SPINNING ICON IS APPEARING AND THE ENTIRE WEBPAGE IS NOT INTERACTIVE FOR A CERTAIN TIME.
		 * @param driver
		 */
		public  String webPageReadyStateCheck() {
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			String state = js.executeScript("document.readyState").toString();		// We've get o/p like complete, interactive etc...
			
			return state;
		}
		
		
		// ***********************************************************************************************************//
		
		/**
		 * TO GET TITLE
		 * @param driver
		 * @return
		 */
		public  String getTitleByJS() {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			String title = js.executeScript("return document.title;").toString();	// to test JavaScript, INSPECT the webPage --> goto Console Tab --> write document.title and ENTER 
																					//		this Javascript document.title will give you the title of the page which we're converting
																					//		into String using toString() and returning to the method
			return title;															
		}
		
		//***********************************************************************************************************//
		
		/**
		 * TO GENERATE ALERT
		 * @param driver
		 * @param message
		 */
		public void generateAlert(String message) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("alert('" + message + "')");
		}
		
		//***********************************************************************************************************//	
		
		/**
		 * TO FLASH A WEBELEMEMT | HELPFUL IN DEBUGGING
		 * @param element
		 * @param driver
		 */
		public void flash(WebElement element) {

			String bgcolor = element.getCssValue("backgroundColor");
			for (int i = 0; i < 10; i++) {									// i< 15/20 is ideal frequency
				changeColor("rgb(0,200,0)", element);// 1
				changeColor(bgcolor, element);// 2
			}
		}

		//***********************************************************************************************************//	
		
		/**
		 * TO CHANGE COLOR OF WEBELEMENT
		 * @param color
		 * @param element
		 * @param driver
		 */
		public void changeColor(String color, WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}

		//***********************************************************************************************************//
		
		/**
		 * TO DRAW BORDER | HIGHLIGHT A WEB ELEMENT
		 * @param element
		 * @param driver
		 */
		public void drawBorder(WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].style.border='3px solid red'", element);	// Useful in taking screenshot with Border
		}

		//***********************************************************************************************************//

		/**
		 * TO CLICK AT WEBELEMENT
		 * @param element
		 * @param driver
		 */
		public void clickElementByJS(WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].click();", element);
		}

		//***********************************************************************************************************//

		/**
		 * Provides all the text part of the page as String
		 * @Use: Useful in creating a collection of these Strings and traverse inside
		 * @param driver
		 * @return
		 */
		public  String getPageInnerText() {					
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			String pageText = js.executeScript("return document.documentElement.innerText;").toString();
			return pageText;
		}

		//***********************************************************************************************************//
		
		/**
		 * TO SCROLL DOWN IN WEBPAGE
		 * @param driver
		 */
		public void scrollPageDown() {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}
		
		//***********************************************************************************************************//	
		
		/**
		 * TO SCROLL TO VIEW THE WEBELEMENT
		 * @param element
		 * @param driver
		 */
		public void scrollIntoView(WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}
		//***********************************************************************************************************//	
		
		/**
		 * TO SCROLL TO VIEW THE WEBELEMENT
		 * @param element
		 * @param driver
		 */
		public void sliderHorizontal(WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].setAttribute('style', 'left: 100%;')", element);
		}		
		//***********************************************************************************************************//	
		
		/**
		 * returns a String, representing the user agent string for the current browser 
		 * @param driver
		 * @return
		 */
		public  String getBrowserInfo(){
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			String uAgent = js.executeScript("return navigator.userAgent;").toString(); 
			return uAgent;
		}
		
		//***********************************************************************************************************//
		
		/**
		 * TO PERFORM SEND KEYS USING WEBELEMENT'S ID ATTRIBUTE
		 * @param driver
		 * @param id
		 * @param value
		 */
		public void sendKeysUsingJSWithID(String id, String value){
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("document.getElementById('"+id+"').value='"+value+"'");	// document means DOM. document is the object for DOM
																							//	getElementById is the method of the document
		}
		
		//***********************************************************************************************************//
		
		/**
		 * TO PERFORM SEND KEYS USING WEBELEMENT'S CLASS NAME ATTRIBUTE
		 * @param driver
		 * @param id
		 * @param value
		 */
		public void sendKeysUsingJSWithClassName(String className, String value){
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("document.getElementById('" + className + "').value='"+value+"'");
		}
		
		//***********************************************************************************************************//
		
		/**
		 * TO PERFORM SEND KEYS USING WEBELEMENT'S NAME ATTRIBUTE
		 * @param driver
		 * @param id
		 * @param value
		 */
		public void sendKeysUsingJSWithName(String name, String value){
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("document.getElementById('" + name + "').value='"+value+"'");
		}
		
		// For scenarios where Webpage is in loading state. we can use the JS cript for document.readyState

	
		//***********************************************************************************************************//
		
		/**
		 * TO CHECK IF THE PAGE IS PROPERLY LOADED OR NOT
		 */
		public void checkPageIsReady() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Initially bellow given if condition will check ready state of page.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page Is loaded.");
				return;
			}

			// This loop will rotate for 25 times to check If page Is ready after
			// every 1 second.
			// You can replace your value with 25 If you wants to Increase or
			// decrease wait time.
			for (int i = 0; i < 25; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// To check page ready state.
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
	
		}
	
	
	
	
}
