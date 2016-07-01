package com.digitaltolk.qa.page.declaration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.common.oTest.oTestBasePageDeclaration;
import com.common.oTest.oTestPasswordVault;
import com.common.oTest.oTestSpreadSheetFactory;

public class CustomerHomePage extends oTestBasePageDeclaration {
	
	private String translationLanguage = "translationLanguage";
	private String translationLanguageTextBox = "translationLanguageTextBox";
	
	private String datepicker = "datepicker";
	private String nextMonthDatepicker = "nextMonthDatepicker";
	private String dayDatepicker = "dayDatepicker";
	private String timepicker = "timepicker";
	private String duration = "duration";
	private String customerPhone = "customerPhone";
	private String submitRequest = "submitRequest";
	
	//modal
	private String modaluseremail = "modaluseremail";
	private String modalreference = "modalreference";
	private String modalEmailformbtn = "modalEmailformbtn";
	
	static ResourceBundle resources;
	
	static
	{
		try
		{
			resources = ResourceBundle.getBundle("digitaltolk.main.CustomerHomePage",Locale.getDefault());
			// print the keys
//			Enumeration<String> enumeration = resources.getKeys();
//			while (enumeration.hasMoreElements()) {
//				String key = enumeration.nextElement();
//				System.out.println("Key: " + key + ", Value: " + resources.getString(key));
//			}

		} catch (MissingResourceException mre) {
			System.out.println("CustomerHomePage.properties not found: "+mre);
			System.exit(0);
		}
	}
	
	public CustomerHomePage(WebDriver driver){
		super(driver,resources);
		pageTitle = new String(resources.getString("pageTitle"));
		pageURL = new String(resources.getString("pageURL"));
	}
	
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
	 * This function demonstrates isTranslationLanguageDisplayed().
	 * <br>Function indicates if the translationLanguage is displayed
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public boolean isTranslationLanguageDisplayed(){
		try{
			WebElement element = findElement(translationLanguage);
			return(element.isDisplayed());
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * This function demonstrates clickTranslationLanguage().
	 * <br>Function to clickTranslationLanguage
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickTranslationLanguage(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(translationLanguage);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates clickDatepicker().
	 * <br>Function to clickDatepicker
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickDatepicker(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(datepicker);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates clickNextMonthDatepicker().
	 * <br>Function to clickNextMonthDatepicker
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickNextMonthDatepicker(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(nextMonthDatepicker);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates clickDayDatepicker().
	 * <br>Function to clickDayDatepicker
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickDayDatepicker(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(dayDatepicker);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates setupDataPicker().
	 * <br>Function to setupDataPicker
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void setupDataPicker(){
		try{
			clickDatepicker();
			clickNextMonthDatepicker();
			clickDayDatepicker();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function demonstrates enterTranslationLanguage().
	 * <br>Function to enter text into the translationLanguage
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */	
	public void enterTranslationLanguage(String data){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(translationLanguage);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function demonstrates clickcancelButton(). <br>
	 * Function to click thecancelButton <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
//	public void clickcancelButton() {
//		try {
//			waitForElementPresent(cancelButton, 2000);
//			WebElement element = findElement(cancelButton, resources);
//			// element.click();
//			Actions actions = new Actions(driver);
//			actions.moveToElement(element).click().perform();
//			Reporter.log("clickcancelButton Click OK...", true);
//		} catch (Exception e) {
//			Reporter.log("Error in clickcancelButton: " + e.getMessage(), true);
//		}
//	}
	
	/**
	 * This function demonstrates enterTranslationLanguageTextBox().
	 * <br>Function to enterTranslationLanguageTextBox
	 * <br> 
	 * @param String text
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterTranslationLanguageTextBox(String text){
		try{
			WebElement element = findElement(translationLanguageTextBox);
			element.sendKeys(text);
//			Actions actions = new Actions(driver);
//			actions.moveToElement(element).click().perform();
			element.sendKeys(Keys.RETURN);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates enterUserEmailTextBox().
	 * <br>Function to enterUserEmailTextBox
	 * <br> 
	 * @param String email
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterUserEmailTextBox(String email){
		try{
//			waitForElementPresent(modaluseremail, 30);
			WebElement element = findElement(modaluseremail);
			//driver.findElement(By.xpath("//input[@id='edit-box-big' and @placeholder='Job Title *']")).click()
//			WebDriverWait wait = new WebDriverWait(driver, timeout);
//			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ByLocator(modaluseremail)));
			
			element.sendKeys(email);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates enterReferenceTextBox().
	 * <br>Function to enterReferenceTextBox
	 * <br> 
	 * @param String reference
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterReferenceTextBox(String reference){
		try{
//			waitForElementPresent(modalreference, 30);
			WebElement element = findElement(modalreference);
			element.sendKeys(reference);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * This function demonstrates setTimepicker().
	 * <br>Function to setTimepicker
	 * <br> 
	 * @param String text
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setTimepicker(String hourly){
		try{
			WebElement element = findElement(timepicker);
			new Select(element).selectByVisibleText(hourly);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates setDuration().
	 * <br>Function to setDuration
	 * <br> 
	 * @param String time
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setDuration(String time){
		try{
			WebElement element = findElement(duration);
			new Select(element).selectByVisibleText(time);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function demonstrates clickCustomerPhoneType().
	 * <br>Function to clickCustomerPhoneType
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickCustomerPhoneType(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(customerPhone);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates clickSubmitRequestBooking().
	 * <br>Function to clickSubmitRequestBooking
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickSubmitRequestBooking(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(submitRequest);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function demonstrates clickSkickaBokningButton().
	 * <br>Function to clickSkickaBokningButton
	 * <br> 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */		
	public void clickSkickaBokningButton(){
		try{
//			waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(modalEmailformbtn);
			 element.click();
//			Actions actions = new Actions(driver);
//			actions.moveToElement(element).click().perform();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * This function demonstrates validateCustomerHomePageElements().
	 * <br>Function provides all @Tests with a function to validate elements on the login page.
	 * <br>
	 * @param showWarnings - if true will only show warning messages if elements are not present.
	 * <br> False will throw errors if elements are not found. 
	 * @return none
	 * @see oTestPasswordVault
	 * @author ysaigalarza
	 * @version 1.0  
	 */
	public void validateCustomerHomePageElements(boolean showWarnings){
		
		if(showWarnings){
			
			if(!isTranslationLanguageDisplayed()) Reporter.log("Warning: translationLanguageTextBox missing on login page!",true);
			
			
		}else{  // Throw errors
			
			Assert.assertTrue(isTranslationLanguageDisplayed());
			
		}
		
	}//validateCustomerHomePageElements
	
	
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
				CustomerHomePage efwp = new CustomerHomePage(driver);
			
				if(null != efwp){
					driver.get("http://dev.digitaltolk.com/login");
					try{				
						//efwp.validateLoginPageElements(false);
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
