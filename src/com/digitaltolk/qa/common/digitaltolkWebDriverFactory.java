package com.digitaltolk.qa.common;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.common.oTest.oTestWebDriverFactory;

import com.digitaltolk.qa.page.declaration.LoginPage;
import com.digitaltolk.qa.page.test.LoginPageTest;

@SuppressWarnings({"all"}) 
public class digitaltolkWebDriverFactory extends digitaltolkWebDriver {
//public class digitaltolkWebDriverFactory extends digitaltolkWebDriver {
	
//public class digitaltolkWebDriverFactory extends oTestWebDriverFactory {	
	
	// Main Pages
	
	private LoginPage loginPage = null;	
	
	/**
	 * digitaltolkWebDriverFactory() constructor
	 * 
	 * @param None
	 * @return None
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public digitaltolkWebDriverFactory() {
		//
		// This will configure all the supporting oTest Factories
		super();

	}
	
	
	

	/**
	 * cleanWebDriver() reset all page declarations and web drivers before next
	 * test
	 * 
	 * @param None
	 * @return None
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	private void cleanWebDriver() {
		// Main Pages
		loginPage = null;
		
	}
	
	/**
	 * getworkPlaceLoginPage() Encapsulate the workPlaceLoginPage
	 * @param None
	 * @return workPlaceLoginPage
	 * @exception None.
	 * @author Mayank
	 * @version 1.0
	 */

	public LoginPage getLoginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}

	/**
	 * createWebDriver() Main class that creates the selenium web driver
	 * 
	 * @param remote
	 *            - NO/PROXY/Saucelabs
	 * @param version
	 *            = browser version, Ipad, Android,IPhone
	 * @param platform
	 *            - what OS (LINUX,MAC, WINDOWS)
	 * @param browser
	 *            - (Firefox,Chrome,IOS,Android,IE)
	 * @param setupString
	 *            - Testing info
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @return None
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public RemoteWebDriver createWebDriver(String remote, String version,
			String platform, String browser, String setupString) {
		//
		// Closes all the digitaltolk page declarations
		//
		cleanWebDriver();
		return (super.createWebDriver(remote, version, platform, browser,
				setupString));
	}

	//
	// Inner class for testing on the command line
	//
	public static class Test {
		public static void main(final String[] args) {

			digitaltolkWebDriverFactory ewdf = new digitaltolkWebDriverFactory();
			System.out.println("OS running: " + ewdf.getOSName());
			System.out.println("created oTestWebDriverFactory class");
			System.out.println("Starting self test");
			ewdf.selfTest();

		}// main
	}// Test


}