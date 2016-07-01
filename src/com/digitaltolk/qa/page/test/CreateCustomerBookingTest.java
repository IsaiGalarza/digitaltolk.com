package com.digitaltolk.qa.page.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.digitaltolk.qa.common.digitaltolkBaseTestNGDeclaration;


@SuppressWarnings("all")
@Listeners({ /* com.selenium.listners.reportFactoryListner.class, */com.selenium.listners.oTestTestNGTestListener.class })
public class CreateCustomerBookingTest extends digitaltolkBaseTestNGDeclaration {

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

	public CreateCustomerBookingTest() {
		spreadsheet = new String("digitaltolk.dev.CreateCustomerBookingTest.testRunXLS");
		workbook = new String("digitaltolk.dev.CreateCustomerBookingTest.testRunWorkSheet");
		table = new String("digitaltolk.dev.CreateCustomerBookingTest.testRunTableLabel");
	}

	public void reportConfigurationSettings(String testName, String browser,
			String platform, String version, String remote) {
		Reporter.log("Start the CreateCustomerBookingTest on " + platform + " using "
				+ browser + " browser, version " + version, true);
	}
	
	
	private int month;
	
	/**
	 * Title: CreateCustomerBookingTest
	 * 
	 * @param xlSheet
	 *            - P0
	 * @param xlTable
	 *            - CreateCustomerBookingTest
	 * @return None.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	@Test(enabled = true, groups = { "P0", "CreateCustomerBookingTest" }, dataProvider = "oTestBaseTestNGDeclarationDataProvider", description = "Create Booking")
	public void createCustomerBookingTest(String testCount, String runTest,
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

		driver = webDriver.createWebDriver(remote, version, platform, browser, "CreateCustomerBookingTest");

		if (driver == null) {
			throw new SkipException("No Web Driver");
		}

		testName = new String("Start the CreateCustomerBookingTest on " + platform
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
		
		webDriver.getLoginPage().login("testcustomer@gmail.com", "testcustomer");
		Reporter.log("Step 4:Log in with user", true);
		
		webDriver.getCustomerHomePage().verifyFoundPageByURL(20, webDriver.getCustomerHomePage().pageURL, "CustomerHomePage");
		Reporter.log("Verified Customer Home Page.", true);
		Reporter.log("Step 5:Open Customer Home Page " + driver.getCurrentUrl(), true);

		Assert.assertEquals(driver.getTitle().trim().equals(webDriver.getCustomerHomePage().pageTitle.trim()), true);
		Reporter.log("Expect 1:Verified Title Customer Home Page : " + driver.getTitle(), true);
		
		//CODE SS
		webDriver.getCustomerHomePage().clickTranslationLanguage();
		webDriver.getCustomerHomePage().enterTranslationLanguageTextBox("Spanska");
		Reporter.log("Step 6:Set  Translation Language Booking: " + "Spanska", true);
		
		webDriver.getCustomerHomePage().setupDataPicker(new Date(), webDriver.getCustomerHomePage().getDayPlus());
		Reporter.log("Step 7:Setup DataPicker Booking: " , true);
		
		webDriver.getCustomerHomePage().setTimepicker("12:00");
	    Reporter.log("Step 8:Set Hourly Booking: " + "12:00" , true);
	    
	    webDriver.getCustomerHomePage().setDuration("30 min");
	    Reporter.log("Step 9:Set Duration Booking: " + "30 Min" , true);
	    
	    webDriver.getCustomerHomePage().clickCustomerPhoneType();
	    Reporter.log("Step 10:Check CustomerPhoneType." , true);
	    
	    webDriver.getCustomerHomePage().clickSubmitRequestBooking();
	    Reporter.log("Step 11:Click Submit Request Booking." , true);
	    
	    webDriver.getCustomerHomePage().verifyFoundPageByURL(20, webDriver.getCustomerHomePage().pageURL, "CustomerHomePage");
		Reporter.log("Verified Customer Home Page.", true);
	    
	    webDriver.getCustomerHomePage().enterUserEmailTextBox("testcustomer@gmail.com");
	    Reporter.log("Step 12:Set Email Reference: " + "testcustomer@gmail.com" , true);

	    webDriver.getCustomerHomePage().enterReferenceTextBox("ALIAS123");
	    Reporter.log("Step 13:Set Evt referens: " + "TEST" , true);
	    
	    webDriver.getCustomerHomePage().clickSkickaBokningButton();
	    Reporter.log("Step 14:Click Skicka Bokning." , true);
	    
	    Assert.assertEquals(webDriver.getCustomerHomePage().getMesssageConfirmationText().contains("Tolkning bokad"), true);
	    Reporter.log("Expect 2:Verified Message Confirmation Booking : " + webDriver.getCustomerHomePage().getMesssageConfirmationText(), true);
	    
		Reporter.log("Step 15: Close Browser.", true);
		Reporter.log("sauceLab results: " + webDriver.obtainTestStatusInformation(), true);
		Reporter.log("CreateCustomerBookingTest: Passed", true);
	}
	
}
