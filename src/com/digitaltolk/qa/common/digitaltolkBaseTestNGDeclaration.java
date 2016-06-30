package com.digitaltolk.qa.common;


import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.common.oTest.oTestSpreadSheetFactory;

public class digitaltolkBaseTestNGDeclaration {

	 
	protected static ResourceBundle resources; // Access to the properties file for releaseNightTest.
	protected WebDriver driver = null; // Selenium web driver to drive the testing
	//protected digitaltolkWebDriverFactory webDriver = null; // USed to build WebDrivers for local and remote testing
	
	protected digitaltolkWebDriverFactory webDriver = null;
	
	protected digitaltolkMobileAppDriverFactory mobileAppDriver = null; // For Appium mobile test
	
	protected String spreadsheet = "none";
	protected String workbook = "none";
	protected String table = "none";
//	protected String jiraTestID = "none";
//	protected String jiraTestCycleToExecute = "none";
	protected String testName = "none";
		
	
	 @BeforeSuite(alwaysRun=true)
	  public void beforeSuite() {

		 Reporter.log("Before Suite complete\n\n", true);
	  }
	
	/************************************************************
	   * <br>BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   * <br>(Called last after all tests run) AfterSuite
	   * 
	   * <br>@BeforeClass
	   * 
	   * <br>Not used for this test
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @BeforeClass(alwaysRun=true)
	  public void beforeClass() {
		  webDriver = new digitaltolkWebDriverFactory();
		  
//		  mobileAppDriver = new digitaltolkMobileAppDriverFactory();
		  
		  if(webDriver!=null){
			    webDriver.testRunXLS = new String (resources.getString(spreadsheet));
			    webDriver.testRunWorkSheet = new String (resources.getString(workbook));
			    webDriver.testRunTableLabel = new String (resources.getString(table));
			  }
		  
		  Reporter.log("BeforeClass complete...create oTestWebDriverFactory\n\n",true);
	  }
	  /**
	   * <br>BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   * <br>(Called last after all tests run) AfterSuite
	   * 
	   * <br>@BeforeMethod
	   * 
	   *  <br>elanceWebDriver.checkRemoteSauceConnection() is called to setup Sauce lab tunnel if needed for the test 
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @BeforeMethod (alwaysRun=true)
	  public void beforeMethod() {
		  //
		  // See if the data wants to test remotely. If yes open the tunnel to sauce labs
		  //
		  Reporter.log("Before Method...\n",true);
		  //
		  // Run thru the data to see if any test is remote. If remote open tunnel to Saucelabs
		  //
		  webDriver.checkRemoteSauceConnection();
	  }
	  /**
	   *<br>BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   *<br>(Called last after all tests run) AfterSuite
	   * 
	   *<br>@AfterMethod
	   *  
	   *<br>Close the web application
	   *<br>Zip all screen shots
	   *<br>driver = null;
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @AfterMethod (alwaysRun=true)
	  public void afterMethod() {
		  // if(elanceWebDriver != null) elanceWebDriver.zipScreenShots();
		  // Reporter.log("After Method: Zipping all screen shots",true);
		  //
		  // Close Web Page
		  //
		  if(null != driver) driver.quit();
		  driver = null;
		  //
		  //
		  //
		  //
		  // Update Jira if the test has a Jira ID
		  //
		  
		  
		  /*
		  if(!jiraTestID.equals("none")){
			  webDriver.updateJiraTestComment(jiraTestID, "oTest","TEST COMPLETED: " +testName);
		  }
		  jiraTestID = new String("none");*/
		  
		  Reporter.log("After Method, closed the browser page\n",true);
	  }
	  /*********************************************
	   * BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   * <br>(Called last after all tests run) AfterSuite
	   * 
	   * <br> No actions in AfterClass
	   *
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @AfterClass (alwaysRun=true)
	  public void afterClass(){
		  
		  Reporter.log("After Class...\n",true);
	  }
	  /**
	   * BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   * <br>Called last after all tests run) AfterSuite
	   * 
	   *  <br>No actions in @AfterTest
	   *
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @AfterTest (alwaysRun=true)
	  public void afterTest() {
		  Reporter.log("After Test:....\n",true);
	  }
	  /**
	   *BeforeSuite, BeforeTest, BeforeClass, DataProvider, BeforeMethod <TEST> AfterMethod, AfterClass, AfterTest, 
	   *<br>(Called last after all tests run) AfterSuite
	   * 
	   *<br>@AfterSuite
	   *  
	   *<br>Close Saucelabs connection.
	   *<br>elanceWebDriver = null;
	   *  
	   *
	   * @return None.
	   * @exception None.
	   * @author ysaigalarza
	   * @version 1.0  
	   */
	  @AfterSuite (alwaysRun=true)
	  public void afterSuite() {
		  Reporter.log("After Suite...",true);
		  
		  
		  if(webDriver != null) {
			  if(webDriver.isSauceConnectionServerRunning()) {
				  Reporter.log("After Suite: Closing SauceLabs Tunnel\n",true);
				  webDriver.closeSauceLabsConnectionServer();
			  }
			  webDriver = null;
		  }
	  }
	  /** setupDataProvider() is the testNG way to create Data Driven Tests
	   * 
	   * @param xlfile for the data
	   * @param xlsheet from the xlfile
	   * @param xltable from the xlsheet
	   * @return The 2 dimensional array consisting of the data from the spreadsheet
	   * @author ysaigalarza
	   * @version 1.0
	   */
	  @DataProvider (name = "oTestBaseTestNGDeclarationDataProvider")
	  public Object[][] setupDataProvider() {
		  System.out.println("DataProvider..oTestBaseTestNGDeclarationDataProvider");
		  if(webDriver==null) System.out.println("webDriver is null"); //debug statement
		  SpreadSheetFactory xlFactory = new SpreadSheetFactory();
		  Reporter.log("Getting test run data from: "+ webDriver.testRunXLS+" using label: "+webDriver.testRunTableLabel ,true);	
		  webDriver.data = xlFactory.getTableArray(webDriver.testRunXLS, webDriver.testRunWorkSheet, webDriver.testRunTableLabel);		  
		  
		  Reporter.log("setupDataProvider: Loaded data from spreadsheet complete" ,true);
		  
		 
		  return (webDriver.data);
	  } // oTestBaseTestNGDeclarationDataProvider
	  
	  /** setJiraID() 
	   * <br> setJiraID is used to set the jira ID of the test.
	   * @param String id - Jira ID EXAMPLE: QTC-1044
	   * @return none
	   * @author ysaigalarza
	   * @version 1.0
	   */
	  public void setJiraID(String id){
		  /*
		  jiraTestID = new String(id);
		  Reporter.log("<a href=\"https://jira.elance.com/secure/CreateIssue!default.jspa\">Open New Jira Issue Here</a>");
		  Reporter.log("<a href=\"https://jira.elance.com/browse/"+jiraTestID+"\">\""+jiraTestID+" \"</a>");
		  */
	  
	  }//setJiraID
	  /** reportConfigurationSettings() 
	   * <br> reportConfigurationSettings Uses Reporter to output the tests configuration information
	   * @param String testName - name of the test being run
	   * @param Strng browser - name of the browser to run the test on
	   * @param String platform - name of the platform OS
	   * @param String version - browser version
	   * @param String remote - Is this test being run local, phantomJS,Sauce labs, browserStack, proxy 
	   * @return none
	   * @author ysaigalarza
	   * @version 1.0
	   */
	  public void reportConfigurationSettings(String testName,String browser, String platform,String version, String remote){
		
		  
		  if(remote.toLowerCase().equals("local")){
			  String osName = new String(System.getProperty("os.name")); // Mac OS X, Windows 7
			  
			  Reporter.log("Start the "+testName+" on " + osName
						+ " using " + browser + " browser, version " + version, true);
		}else{
		  Reporter.log("Start the "+testName+" on " + platform
					+ " using " + browser + " browser, version " + version, true);
		}
	  }//reportConfigurationSettings

}
