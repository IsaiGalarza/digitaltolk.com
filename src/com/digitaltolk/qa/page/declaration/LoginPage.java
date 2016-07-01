package com.digitaltolk.qa.page.declaration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.common.oTest.oTestBasePageDeclaration;
import com.common.oTest.oTestPasswordVault;
import com.common.oTest.oTestSpreadSheetFactory;
import com.digitaltolk.qa.common.digitaltolkWebDriverFactory;

public class LoginPage extends oTestBasePageDeclaration {
	
	private String loggain = "loggain";
	private String usernameTextBox = "usernameTextBox";
	private String passwdTextBox = "passwdTextBox";
	private String signInButton = "signInButton";
	private String keepMeSignInCheckBox = "keepMeSignInCheckBox";
	private String wantToHireRadioButton= "wantToHireRadioButton";
	private String wantToWorkRadioButton= "wantToWorkRadioButton";
	public String continueButton = "continueButton";
	public String facebooklogin = "facebooklogin";
	public String linkedinLogin = "linkedinLogin";
	public String googleAppinLogin = "googleAppinLogin";
	public String msjError = "msjError";
	// Password text
	//private String passwordtext="passwordtext";
	
	//public loginPageData[] loginPageData = null;	
	static ResourceBundle resources;
	
	static
	{
		try
		{
			resources = ResourceBundle.getBundle("digitaltolk.main.LoginPage",Locale.getDefault());
			
			
		} catch (MissingResourceException mre) {
			System.out.println("loginPage.properties not found: "+mre);
			System.exit(0);
		}
	}
	
	public LoginPage(WebDriver driver){
		super(driver,resources);
		pageTitle = new String(resources.getString("pageTitle"));
		pageURL = new String(resources.getString("pageURL"));
	}
	/**
	 * this method review the status enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	@SuppressWarnings("deprecation")
	public void clickGoogleAppinLogin(){
		try{
			waitForElementPresent(googleAppinLogin, 20);
			WebElement element = findElement(googleAppinLogin);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates isUserNameTextBoxDisplayed().
	 * <br>Function indicates if the usernameTextBox is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isUserNameTextBoxDisplayed(){
		try{
			WebElement element = findElement(usernameTextBox);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * This function demonstrates isPasswordTextBoxDisplayed().
	 * <br>Function indicates if the passwdTextBox is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public boolean isPasswordTextBoxDisplayed(){
		try{
			WebElement element = findElement(passwdTextBox);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * This function demonstrates isKeepMeSignInCheckBoxDisplayed().
	 * <br>Function indicates if the keepMeSignInCheckBox is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isFacebookloginDisplayed(){
		try{
			WebElement element = findElement(facebooklogin);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * this method review the status enabled
	 * <br> 
	 * @param None
	 * @returns boolean.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isFacebookloginEnabled(){
		try{
			WebElement element = findElement(facebooklogin);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * This function demonstrates autoReviewPage().
	 * <br>review all element of page
	 * <br>
	 * @param elanceWebDriverFactory
	 * @returns none.
	 * @exception None.
	 * @see autoReviewPage
	 * @see secureLogin
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public void autoReviewPage(digitaltolkWebDriverFactory webDriver) {
		Reporter.log("verify all the web elements and text found the page",true);
		webDriver.ASSERT_TRUE((validateTextItems() == 0),"Validate Login Page text Items"," elanceLoginPage - Text Validation");
		Reporter.log("LoginPage - Validated all text on the page",true);	
				
		webDriver.ASSERT_TRUE(isUserNameTextBoxDisplayed(),"UserName text box present"," elanceLoginPage - UserName box present");
		Reporter.log("LoginPage - UserName box present",true);
				
		webDriver.ASSERT_TRUE(isPasswordTextBoxDisplayed(),"Password text box present"," elanceLoginPage - Password box present");
		Reporter.log("LoginPage - Password box present",true);	
				
		webDriver.ASSERT_TRUE(isKeepMeSignInCheckBoxDisplayed(),"KeepMeSignIn Check box present"," elanceLoginPage - KeepMeSignIn Check box present");
		Reporter.log("LoginPage - KeepMeSignIn Check box present",true);
				
		webDriver.ASSERT_TRUE(isSignInButtonEnabled(),"SignInButton Enabled"," elanceLoginPage - SignIn Button Enabled");
		Reporter.log("LoginPage - SignIn Button Enabled",true);
	}
	
	/**
	 * this method review the status enabled
	 * <br> 
	 * @param None
	 * @returns boolean.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public void clickFacebooklogin(){
		try{
			Thread.sleep(3000); 
			WebElement element = findElement(facebooklogin);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates isKeepMeSignInCheckBoxDisplayed().
	 * <br>Function indicates if the keepMeSignInCheckBox is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isLinkedinLoginDisplayed(){
		try{
			WebElement element = findElement(facebooklogin);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * this method review the status enabled
	 * <br> 
	 * @param None
	 * @returns boolean.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isLinkedinLoginEnabled(){
		try{
			WebElement element = findElement(facebooklogin);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * this method review the status enabled
	 * <br> 
	 * @param None
	 * @returns boolean.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	@SuppressWarnings("deprecation")
	public void clickLinkedinLoginlogin(){
		try{
			waitForElementPresent(linkedinLogin, 20);
			WebElement element = findElement(linkedinLogin);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates isKeepMeSignInCheckBoxDisplayed().
	 * <br>Function indicates if the keepMeSignInCheckBox is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isKeepMeSignInCheckBoxDisplayed(){
		try{
			WebElement element = findElement(keepMeSignInCheckBox);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	
	
	/**
	 * This function demonstrates isSignInButtonEnabled().
	 * <br>Function indicates if the signInButton is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isSignInButtonEnabled(){
		try{
			WebElement element = findElement(signInButton);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * This function demonstrates isSignInButtonDisplayed().
	 * <br>Function indicates if the signInButton is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isSignInButtonDisplayed(){
		try{
			WebElement element = findElement(signInButton);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	
	
	
	/**
	 * This function demonstrates clickLoggaInButton().
	 * <br>Function to click the clickLoggaInButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickLoggaInButton(){
		
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(loggain);
			element.click();
//			Actions actions = new Actions(driver);
//			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
		
//		
//		try{
//			WebElement element = findElement(loggain);
//			element.click();
//		}catch (Exception e){
//			
//		}
	}
	
	/**
	 * This function demonstrates clickSignInButton().
	 * <br>Function to click the signInButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickSignInButton(){
		try{
			WebElement element = findElement(signInButton);
			element.click();
		}catch (Exception e){
			
		}
	}

	/**
	 * This function demonstrates clickKeepMeSignInCheckBox().
	 * <br>Function to click the keepMeSignInCheckBox
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickKeepMeSignInCheckBox(){
		try{
			WebElement element = findElement(keepMeSignInCheckBox);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates enterUsernameTextBoxInfo().
	 * <br>Function to enter text into the usernameTextBox
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void enterUsernameTextBoxInfo(String data){
		try{
			WebElement element = findElement(usernameTextBox);
			element.clear();
			element.sendKeys(data);
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates enterPasswordTextBoxInfo().
	 * <br>Function to enter text into the passwdTextBox
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public void enterPasswordTextBoxInfo(String data){
		try{
			WebElement element = findElement(passwdTextBox);
			element.clear();
			element.sendKeys(data);
	}catch (Exception e){
			
		}
	}

	/**
	 * This function demonstrates isWantToHireRadioButtonEnabled().
	 * <br>Function indicates if the wantToHireRadioButton is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */			
	public boolean isWantToHireRadioButtonEnabled(){
		try{
			WebElement element = findElement(wantToHireRadioButton);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates isWantToWorkRadioButtonEnabled().
	 * <br>Function indicates if the wantToWorkRadioButton is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public boolean isWantToWorkRadioButtonEnabled(){
		try{
			WebElement element = findElement(wantToWorkRadioButton);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates isWantToHireRadioButtonDisplayed().
	 * <br>Function indicates if the wantToHireRadioButton is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public boolean isWantToHireRadioButtonDisplayed(){
		try{
			WebElement element = findElement(wantToHireRadioButton);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates isWantToWorkRadioButtonDisplayed().
	 * <br>Function indicates if the wantToWorkRadioButton is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isWantToWorkRadioButtonDisplayed(){
		try{
			WebElement element = findElement(wantToWorkRadioButton);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates clickWantToHireRadioButton().
	 * <br>Function to click the wantToHireRadioButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickWantToHireRadioButton(){
		try{
			WebElement element = findElement(wantToHireRadioButton);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates clickWantToWorkRadioButton().
	 * <br>Function to click the wantToWorkRadioButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickWantToWorkRadioButton(){
		try{
			WebElement element = findElement(wantToWorkRadioButton);
			element.click();
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates clickContinueButton().
	 * <br>Function to click the continueButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public void clickContinueButton(){
		try{
			WebElement element = findElement(continueButton);
			element.click();
		}catch (Exception e){
			
		}
	}
	/**
	 * This function demonstrates isContinueButtonDisplayed().
	 * <br>Function to see if continue button is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isContinueButtonDisplayed(){
		try{
			WebElement element = findElement(continueButton);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * This function demonstrates isContinueButtonEnabled().
	 * <br>Function to see if element is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isContinueButtonEnabled(){
		try{
			WebElement element = findElement(continueButton);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates openLoginPageByUrl().
	 * <br>Function to open login page by URL
	 * <br> 
	 * @param login page url
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author Gyanendra
	 * @version 1.0
	 * @deprecated
	 */	
	public void openLoginPageByUrl(String value){
		try{
			String loginPage = new String(resources.getString("pageURL"));
			System.out.println(loginPage);
			String[] tmp = loginPage.split("digitaltolk.com");
			loginPage = value+tmp[1];
			System.out.println(loginPage);
			driver.navigate().to(loginPage);
		}catch (Exception e){
			
		}
	}
	
	/**
	 * This function demonstrates login().
	 * <br>Function is used to take care of the entire functionality of LoginPage
	 * <br>
	 * <br>Note: We encourage to use the securedLogin() which provides a better 
	 * <br>and secure way of accessing passwords. 
	 * @param url
	 * @param username
	 * @param password
	 * @returns none.
	 * @exception None.
	 * @see loginPage
	 * @author ysaigalarza
	 * @deprecated
	 * @version 1.0
	 */	
	public void login(String url, String username, String password) {
		openLoginPageByUrl(url);
		enterUsernameTextBoxInfo(username);
		enterPasswordTextBoxInfo(password);
		clickSignInButton();
	}
	
	/**
	 * This function demonstrates Login().
	 * <br>In this function we perform login using password 'n00bWHIP!' 
	 * @param url: enter Elance url
	 * @param username: Enter username
	 * @param openurl: set openurl to 'yes' if we want to open login url
	 * @returns none.
	 * @exception None.
	 * @author ysaigalarza
	 * @deprecated
	 * @version 1.0
	 */	
	public void Login(String url, String username, String openurl) {
		// Get password
		oTestPasswordVault pv = new oTestPasswordVault();
		pv.addNewProperty("NewUser1", "NewPassword4NewUser1"); //add key-value pair (user, password)
		pv.savePasswordVault(); //save into file
		String password = pv.retreivePassword("NewUser1"); //password is NewPassword4NewUser1
		// Set openurl to 'yes' if we want to open login page url
		if(openurl.equals("yes")){
		// Open Sign In Page
		openLoginPageByUrl(url);
		Reporter.log("Open Sign In Page", true);}
		// Enter Username
		enterUsernameTextBoxInfo(username);
		Reporter.log("Enter Username", true);
		// Enter password
		enterPasswordTextBoxInfo(password);
		Reporter.log("Enter password");
		// Click Sign In button
		clickSignInButton();
		Reporter.log("Click Sign In button", true);
	}
	
	/**
	 * This function demonstrates login().
	 * <br>Function is used to take care of the entire functionality of LoginPage
	 * <br> 
	 * @param username
	 * @param password
	 * @returns none.
	 * @exception None.
	 * @see loginPage
	 * @see secureLogin
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	@SuppressWarnings("deprecation")
	public void login(String username, String password) {
		waitForElementPresent(signInButton, 20);
		enterUsernameTextBoxInfo(username);
		waitForElementPresent(passwdTextBox, 20);
		enterPasswordTextBoxInfo(password);
		clickSignInButton();
	}	
	
	/**
	 * This function demonstrates isMessageWrongDisable().
	 * <br>Function to see if element is enabled
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public boolean isMessageWrongDisable(){
		try{
			WebElement element = findElement(msjError);
			return(element.isEnabled());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This login() takes only username and will retrieve a common shared password from oTestPasswordVault
	 * <br>Function provides login with commonly SHARED PASSWORD that 
	 * <br>saved inside the /properties/secureVault.properties file
	 * <br>
	 * @param username
	 * @return void
	 * @see oTestPasswordVault
	 * @author ysaigalarza
	 * @version 1.0  
	 */
	public void login(String username){
		String password = null;
		oTestPasswordVault passwordVault = new oTestPasswordVault();
		password = passwordVault.retreivePassword("password");		
		enterUsernameTextBoxInfo(username);
		enterPasswordTextBoxInfo(password);
		clickSignInButton();
	}
	/**
	 * This function demonstrates validateLoginPageElements().
	 * <br>Function provides all @Tests with a function to validate elements on the login page.
	 * <br>
	 * @param showWarnings - if true will only show warning messages if elements are not present.
	 * <br> False will throw errors if elements are not found. 
	 * @return none
	 * @see oTestPasswordVault
	 * @author ysaigalarza
	 * @version 1.0  
	 */
	public void validateLoginPageElements(boolean showWarnings){
		
		if(showWarnings){
			
			if(!isUserNameTextBoxDisplayed()) Reporter.log("Warning: UserNameTextBox missing on login page!",true);
			
			if(!isWantToWorkRadioButtonDisplayed()) Reporter.log("Warning: WantToWorkRadioButton missing on login page!",true);
			
			if(!isWantToHireRadioButtonDisplayed()) Reporter.log("Warning: WantToHireRadioButton missing on login page!",true);
			
			
			if(!isContinueButtonDisplayed()) Reporter.log("Warning: ContinueButton missing on login page!",true);
			
			if(!isWantToWorkRadioButtonDisplayed()) Reporter.log("Warning: WantToWorkRadioButton missing on login page!",true);
			
			if(!isKeepMeSignInCheckBoxDisplayed()) Reporter.log("Warning: KeepMeSignedInCheckBox missing on login page!",true);
			
			if(!isSignInButtonDisplayed()) Reporter.log("Warning: SignInButton missing on login page!",true);
			
			if(!isPasswordTextBoxDisplayed()) Reporter.log("Warning: PasswordTextBox missing on login page!",true);
			
		}else{  // Throw errors
			
			Assert.assertTrue(isUserNameTextBoxDisplayed());
			Assert.assertTrue(isWantToWorkRadioButtonDisplayed());
			Assert.assertTrue(isWantToHireRadioButtonDisplayed()); 
			Assert.assertTrue(isContinueButtonDisplayed()); 
			Assert.assertTrue(isWantToWorkRadioButtonDisplayed()); 
			Assert.assertTrue(isKeepMeSignInCheckBoxDisplayed()); 
			Assert.assertTrue(isSignInButtonDisplayed()); 
			Assert.assertTrue(isPasswordTextBoxDisplayed()); 
		}
		
	}//validateLoginPageElements
	
	
	/**
	 * This function overload loadTestData() and return a map<String, String>. 
	 * This function should move up to the parent class oTestBasePageDeclaration later
	 * <br> 
	 * @param String spreadsheet - spreadsheet file name
	 * @param String workbook - workbook within the spreadsheet
	 * @param String table - table within the spreadsheet
	 * @returns HashMap<String, String>
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public Map<String, String> loadTestData(String spreadsheet,String workbook, String table){
		
		Map<String, String> testData = new HashMap<String, String>();
		oTestSpreadSheetFactory xlFactory = new oTestSpreadSheetFactory();
		testData = xlFactory.getTableMap(spreadsheet,workbook,table);
		if(testData == null){
			Reporter.log("Error Finding freelancer Data",true);
			return null;
		}
		Reporter.log("obtained all the test data",true);
		return testData;
	}
	
	
	/**
	 * This class Demonstrates elanceFindWorkPage.
	 * <br>Used to validate all page elements on the elanceFindWork PAge
	 * <br>
	 * @param None
	 * @returns None
	 * @exception None.
	 */	
	public static class Test
	{
		public static void main(final String[] args){
			WebDriver driver = new FirefoxDriver();
			if(driver != null){
				LoginPage efwp = new LoginPage(driver);
			
				if(null != efwp){
					driver.get("http://dev.digitaltolk.com/login");
					try{				
						efwp.validateLoginPageElements(false);
						System.out.println("PASSED: Verify Elance Login page: " +efwp.pageURL);
					}catch(Exception e){
						System.out.println("FAILED: Verify Elance Login page: " +efwp.pageURL);
					}
						driver.close();
				}
			}
			
		}
	
	}
	
}
