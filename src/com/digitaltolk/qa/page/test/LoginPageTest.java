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
public class LoginPageTest extends digitaltolkBaseTestNGDeclaration {

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

	public LoginPageTest() {
		spreadsheet = new String("digitaltolk.dev.testRunXLS");
		workbook = new String("digitaltolk.dev.testRunWorkSheet");
		table = new String("digitaltolk.dev.testRunTableLabel");
	}

	public void reportConfigurationSettings(String testName, String browser,
			String platform, String version, String remote) {
		Reporter.log("Start the CheckProfileTest on " + platform + " using "
				+ browser + " browser, version " + version, true);
	}

	/**
	 * Title: LoginPageTest
	 * 
	 * @param xlSheet
	 *            - P0
	 * @param xlTable
	 *            - LOGIN
	 * @return None.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	@Test(enabled = true, groups = { "P0", "LoginPageTest" }, dataProvider = "oTestBaseTestNGDeclarationDataProvider", description = "Login Page")
	public void loginPageTest(String testCount, String runTest,
			String browser, String platform, String version, String remote,
			String digitaltolkURL, String description,
//			String client,, String clientAdmin, String freelancer, String freelancerAdmin,
//			String password, String secretAnswer1, String secretAnswer2,
			String xlFile, String xlSheet, String xlTable) {

		// jiraTestID ="QTC-1035";
		if (runTest.toLowerCase().contains("no")) {
			throw new SkipException("Skip");
		}

		if (webDriver == null) {
			throw new SkipException("No webDriver");
		}
		Reporter.log("created webDriver class");

		driver = webDriver.createWebDriver(remote, version, platform, browser, "LoginPageTest");

		if (driver == null) {
			throw new SkipException("No Web Driver");
		}

		testName = new String("Start the LoginPageTest on " + platform
				+ " using " + browser + " browser, version " + version);
		reportConfigurationSettings(testName, browser, platform, version, remote);
		Reporter.log("Step 1: Launch web browser (FireFox,IE, Chrome)");

		driver.get(digitaltolkURL);
		Reporter.log("Step 2:Open Web Page " + digitaltolkURL, true);
		
		webDriver.getLoginPage().verifyFoundPageByURL(30, webDriver.getLoginPage().pageURL, "LoginPage");
		Reporter.log("Step 3:Verified load web page" + webDriver.getLoginPage().pageURL, true);
		
		String password = webDriver.getPasswordVaultFactory().retreivePassword("password");
		webDriver.getLoginPage().login("isai.galarza@gmail.com", password);
		Reporter.log("Step 4:Log in with user", true);
		
		Reporter.log("Step 5:Open Web Page " + webDriver.getLoginPage().pageURL, true);
		
		webDriver.ASSERT_TRUE((driver.getCurrentUrl().contains(webDriver.getLoginPage().pageURL)), "Expected 1. Verified Redirect to http://dev.digitaltolk.com/ Home Page", "redirected to Home page");
		Reporter.log("Expect 1:Verified Home Page : URL Home Page", true);
		
		Reporter.log("Step 6: Close Browser.", true);
		Reporter.log("sauceLab results: " + webDriver.obtainTestStatusInformation(), true);
		Reporter.log("Login Check: Passed", true);
	}

}
