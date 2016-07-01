package com.digitaltolk.qa.page.test;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.digitaltolk.qa.common.digitaltolkBaseTestNGDeclaration;


@SuppressWarnings("all")
@Listeners({ /* com.selenium.listners.reportFactoryListner.class, */com.selenium.listners.oTestTestNGTestListener.class })
public class AcceptTraslatorBookingTest extends digitaltolkBaseTestNGDeclaration {

	static {
		try {
			resources = ResourceBundle.getBundle("com.digitaltolk.qa.configuration.oTestDataDrivenTests");
//			resources = ResourceBundle.getBundle("com.digitaltolk.qa.configuration.oTestDataDrivenTests",
//					Locale.getDefault());
		} catch (MissingResourceException mre) {
			mre.printStackTrace();
			System.out.println("oTestDataDrivenTests.properties not found: "
					+ mre);
			System.exit(0);
		}
	}

	public AcceptTraslatorBookingTest() {
		spreadsheet = new String("digitaltolk.dev.AcceptTraslatorBookingTest.testRunXLS");
		workbook = new String("digitaltolk.dev.AcceptTraslatorBookingTest.testRunWorkSheet");
		table = new String("digitaltolk.dev.AcceptTraslatorBookingTest.testRunTableLabel");
	}

	public void reportConfigurationSettings(String testName, String browser,
			String platform, String version, String remote) {
		Reporter.log("Start the AcceptTraslatorBookingTest on " + platform + " using "
				+ browser + " browser, version " + version, true);
	}

	/**
	 * Title: AcceptTraslatorBookingTest
	 * 
	 * @param xlSheet
	 *            - P0
	 * @param xlTable
	 *            - AcceptTraslatorBookingTest
	 * @return None.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	@Test(enabled = true, groups = { "P0", "AcceptTraslatorBookingTest" }, dataProvider = "oTestBaseTestNGDeclarationDataProvider", description = "Accept Booking (Traslater)")
	public void acceptTraslatorBookingTest(String testCount, String runTest,
			String browser, String platform, String version, String remote,
			String digitaltolkURL, String description,
//			String client,, String clientAdmin, String freelancer, String freelancerAdmin,
//			String password, String secretAnswer1, String secretAnswer2,
			String xlFile, String xlSheet, String xlTable) {

		// TASK ="QA-002";
		if (runTest.toLowerCase().contains("no")) {
			throw new SkipException("Skip");
		}

		if (webDriver == null) {
			throw new SkipException("No webDriver");
		}
		Reporter.log("created webDriver class");

		driver = webDriver.createWebDriver(remote, version, platform, browser, "AcceptTraslatorBookingTest");

		if (driver == null) {
			throw new SkipException("No Web Driver");
		}

		testName = new String("Start the AcceptTraslatorBookingTest on " + platform
				+ " using " + browser + " browser, version " + version);
		reportConfigurationSettings(testName, browser, platform, version, remote);
		Reporter.log("Step 1: Launch web browser (FireFox,IE, Chrome)");

		driver.manage().window().maximize();
		
		driver.get(digitaltolkURL);
		driver.manage().deleteAllCookies();
		Reporter.log("Step 2:Open Web Page " + digitaltolkURL, true);
		
		webDriver.getLoginPage().verifyFoundPageByURL(20, webDriver.getLoginPage().pageURL, "LoginPage");
		Reporter.log("Step 3:Verified load web page" + webDriver.getLoginPage().pageURL, true);
				
		webDriver.getLoginPage().clickLoggaInButton();
		Reporter.log("Click Logga In Button", true);
		
		webDriver.getLoginPage().login("isai.galarza@gmail.com", "freddy");
		Reporter.log("Step 4:Log in with user", true);
		
	
		webDriver.getTranslatorHomePage().verifyFoundPageByURL(20, webDriver.getTranslatorHomePage().pageURL, "TranslatorHomePage");
		Reporter.log("Verified Traslator Home Page.", true);
		Reporter.log("Step 5:Open Traslator Home Page " + driver.getCurrentUrl(), true);
		
		webDriver.ASSERT_TRUE((driver.getCurrentUrl().contains(webDriver.getTranslatorHomePage().pageURL)), "Expected 1. Verified Redirect to http://dev.digitaltolk.com/ Home Page", "redirected to Traslator Home page");
		Reporter.log("Expect 1:Verified Traslator Home Page : URL Home Page", true);
		
		//CODE
		webDriver.getTranslatorHomePage().acceptFirstBooking();
		Reporter.log("Step 6: Accept Firts Booking.", true);
		
		Reporter.log("Step 6: Close Browser.", true);
		Reporter.log("sauceLab results: " + webDriver.obtainTestStatusInformation(), true);
		Reporter.log("AcceptTraslatorBookingTest: Passed", true);
	}

}
