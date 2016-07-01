package com.digitaltolk.qa.common;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.common.oTest.oTestWebDriverFactory;
import com.digitaltolk.qa.page.declaration.AcceptTraslatorBookingPage;
import com.digitaltolk.qa.page.declaration.CreateCustomerBookingPage;
import com.digitaltolk.qa.page.declaration.CustomerHomePage;
import com.digitaltolk.qa.page.declaration.LoginPage;
import com.digitaltolk.qa.page.declaration.TranslatorHomePage;
import com.digitaltolk.qa.page.test.LoginPageTest;

@SuppressWarnings({"all"}) 
public class digitaltolkWebDriverFactory extends digitaltolkWebDriver {
//public class digitaltolkWebDriverFactory extends digitaltolkWebDriver {
	
//public class digitaltolkWebDriverFactory extends oTestWebDriverFactory {	
	
	// Main Pages
	
	private LoginPage loginPage = null;	
	private CustomerHomePage customerHomePage = null;
	private TranslatorHomePage translatorHomePage = null;
	private CreateCustomerBookingPage createCustomerBookingPage = null;
	private AcceptTraslatorBookingPage acceptTraslatorBookingPage = null;
	
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
		customerHomePage = null;
		translatorHomePage = null;
		createCustomerBookingPage = null;
		acceptTraslatorBookingPage = null;
	}
	
	/**
	 * getLoginPage() Encapsulate the workPlaceLoginPage
	 * @param None
	 * @return workPlaceLoginPage
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */

	public LoginPage getLoginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	/**
	 * getworkPlaceLoginPage() Encapsulate the workPlaceLoginPage
	 * @param None
	 * @return workPlaceLoginPage
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public CustomerHomePage getCustomerHomePage() {
		if (customerHomePage == null)
			customerHomePage = new CustomerHomePage(driver);
		return customerHomePage;
	}
	
	/**
	 * getTranslatorHomePage() Encapsulate the translatorHomePage
	 * @param None
	 * @return TranslatorHomePage
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public TranslatorHomePage getTranslatorHomePage() {
		if (translatorHomePage == null)
			translatorHomePage = new TranslatorHomePage(driver);
		return translatorHomePage;
	}
	
	
	
	/**
	 * getCreateCustomerBookingPage Encapsulate the workPlaceLoginPage
	 * @param None
	 * @return workPlaceLoginPage
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */

	public CreateCustomerBookingPage getCreateCustomerBookingPage() {
		if (createCustomerBookingPage == null)
			createCustomerBookingPage = new CreateCustomerBookingPage(driver);
		return createCustomerBookingPage;
	}
	
	/**
	 * getAcceptTraslatorBookingPage Encapsulate the workPlaceLoginPage
	 * @param None
	 * @return AcceptTraslatorBookingPage
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */

	public AcceptTraslatorBookingPage getAcceptTraslatorBookingPage() {
		if (acceptTraslatorBookingPage == null)
			acceptTraslatorBookingPage = new AcceptTraslatorBookingPage(driver);
		return acceptTraslatorBookingPage;
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