package com.digitaltolk.qa.page.declaration;

import java.util.Calendar;
import java.util.Date;
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
	private String datePickerMonth = "datePickerMonth";
	private String datePickerYear = "datePickerYear";

	private String dayDatepicker = "dayDatepicker";
	private String timepicker = "timepicker";
	private String duration = "duration";
	private String customerPhone = "customerPhone";
	private String submitRequest = "submitRequest";

	// modal
	private String modaluseremail = "modaluseremail";
	private String modalreference = "modalreference";
	private String modalEmailformbtn = "modalEmailformbtn";
	private String messsageConfirmation = "messsageConfirmation";
	
	private int dayPlus = 0;
	
	static ResourceBundle resources;

	static {
		try {
			resources = ResourceBundle.getBundle(
					"digitaltolk.main.CustomerHomePage", Locale.getDefault());
			// print the keys
			// Enumeration<String> enumeration = resources.getKeys();
			// while (enumeration.hasMoreElements()) {
			// String key = enumeration.nextElement();
			// System.out.println("Key: " + key + ", Value: " +
			// resources.getString(key));
			// }
			
		} catch (MissingResourceException mre) {
			System.out.println("CustomerHomePage.properties not found: " + mre);
			System.exit(0);
		}
	}

	public CustomerHomePage(WebDriver driver) {
		super(driver, resources);
		pageTitle = new String(resources.getString("pageTitle"));
		pageURL = new String(resources.getString("pageURL"));
		
		//Setup Test Case
		dayPlus = new Integer(resources.getString("dayPlus"));
	}

	/**
	 * This function overload loadTestData() and return a map<String, String>.
	 * This function should move up to the parent class oTestBasePageDeclaration
	 * later <br>
	 * 
	 * @param String
	 *            spreadsheet - spreadsheet file name
	 * @param String
	 *            workbook - workbook within the spreadsheet
	 * @param String
	 *            table - table within the spreadsheet
	 * @returns HashMap<String, String>
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public Map<String, String> loadTestData(String spreadsheet,
			String workbook, String table) {

		Map<String, String> testData = new HashMap<String, String>();
		oTestSpreadSheetFactory xlFactory = new oTestSpreadSheetFactory();
		testData = xlFactory.getTableMap(spreadsheet, workbook, table);
		if (testData == null) {
			Reporter.log("Error Finding freelancer Data", true);
			return null;
		}
		Reporter.log("obtained all the test data", true);
		return testData;
	}

	/**
	 * This function demonstrates isTranslationLanguageDisplayed(). <br>
	 * Function indicates if the translationLanguage is displayed <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public boolean isTranslationLanguageDisplayed() {
		try {
			WebElement element = findElement(translationLanguage);
			return (element.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function demonstrates clickTranslationLanguage(). <br>
	 * Function to clickTranslationLanguage <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickTranslationLanguage() {
		try {
			// waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(translationLanguage);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates clickDatepicker(). <br>
	 * Function to clickDatepicker <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickDatepicker() {
		try {
			// waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(datepicker);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method get getDatePickerMonth. <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public String getDatePickerMonth() {
		try {
			WebElement element = findElement(datePickerMonth);
			return element.getText();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * This method get getDatePickerYear. <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public int getDatePickerYear() {
		try {
			WebElement element = findElement(datePickerYear);
			return Integer.valueOf(element.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * This function demonstrates clickNextMonthDatepicker(). <br>
	 * Function to clickNextMonthDatepicker <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickNextMonthDatepicker(String month, int year) {
		try {
			String auxMoth = getDatePickerMonth();
			int auxYear = getDatePickerYear();

			Reporter.log("auxMoth: " + auxMoth, true);
			Reporter.log("auxYear: " + auxYear, true);

			WebElement element = findElement(nextMonthDatepicker);
			Actions actions = new Actions(driver);
			while (element.isDisplayed() == false) {
				Thread.sleep(2000);
				System.out
						.println("element nextMonthDatepicker isDisplayed....FALSE");
			}

			while (!month.equals(auxMoth) && year == auxYear) {
				// Click Next Month
				actions.moveToElement(element).click().perform();
				auxMoth = getDatePickerMonth();
				auxYear = getDatePickerYear();
				Reporter.log("auxMoth: " + auxMoth, true);
				Reporter.log("auxYear: " + auxYear, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates clickDayDatepicker(). <br>
	 * Function to clickDayDatepicker <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickDayDatepicker(int dayplus) {
		try {
			// waitForElementPresent(translationLanguage, 20);
			Reporter.log("dayDatepicker before: " + dayDatepicker, true);
			// //dayDatepicker = String.valueOf(dayplus);
			// Reporter.log("dayDatepicker after: " + dayDatepicker, true);
			// WebElement element = findElement(dayDatepicker);
			WebElement element = driver.findElement(By.linkText(String
					.valueOf(dayplus)));
			element.sendKeys(String.valueOf(dayplus));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			Reporter.log("Error in clickDayDatepicker: " + e.getMessage(), true);
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates setupDataPicker(). <br>
	 * Function to setupDataPicker <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setupDataPicker(String currentMonth, int year, int dayPlus) {
		try {
			Reporter.log("currentMonth: " + currentMonth + ", year: " + year
					+ ", day: " + dayPlus, true);
			clickDatepicker();
			clickNextMonthDatepicker(currentMonth, year);
			clickDayDatepicker(dayPlus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates setupDataPicker(). <br>
	 * Function to setupDataPicker <br>
	 * 
	 * @param Date
	 *            date
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setupDataPicker(Date date, int dayPlus) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, dayPlus);
			String month = getMonth(cal.get(Calendar.MONTH) + 1);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH) -1;
			Reporter.log("currentMonth: " + month + ", year: " + year + ", day: " + day, true);
			clickDatepicker();
			clickNextMonthDatepicker(month, year);
			clickDayDatepicker(day);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getMonth(int i){
		if(i == 1){
			return "January";
		}
		if(i == 2){
			return "February";
		}
		if(i == 3){
			return "March";
		}
		if(i == 4){
			return "April";
		}
		if(i == 5){
			return "May";
		}
		if(i == 6){
			return "June";
		}
		if(i == 7){
			return "July";
		}
		if(i == 8){
			return "August";
		}
		if(i == 9){
			return "September";
		}
		if(i == 10){
			return "October";
		}
		if(i == 11){
			return "November";
		}
		if(i == 12){
			return "December";
		}else{
			return "";
		}
	}

	/**
	 * This function demonstrates enterTranslationLanguage(). <br>
	 * Function to enter text into the translationLanguage <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterTranslationLanguage(String data) {
		try {
			// waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(translationLanguage);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
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
	// public void clickcancelButton() {
	// try {
	// waitForElementPresent(cancelButton, 2000);
	// WebElement element = findElement(cancelButton, resources);
	// // element.click();
	// Actions actions = new Actions(driver);
	// actions.moveToElement(element).click().perform();
	// Reporter.log("clickcancelButton Click OK...", true);
	// } catch (Exception e) {
	// Reporter.log("Error in clickcancelButton: " + e.getMessage(), true);
	// }
	// }

	/**
	 * This function demonstrates enterTranslationLanguageTextBox(). <br>
	 * Function to enterTranslationLanguageTextBox <br>
	 * 
	 * @param String
	 *            text
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterTranslationLanguageTextBox(String text) {
		try {
			WebElement element = findElement(translationLanguageTextBox);
			element.sendKeys(text);
			// Actions actions = new Actions(driver);
			// actions.moveToElement(element).click().perform();
			element.sendKeys(Keys.RETURN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates enterUserEmailTextBox(). <br>
	 * Function to enterUserEmailTextBox <br>
	 * 
	 * @param String
	 *            email
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterUserEmailTextBox(String email) {
		try {
			// waitForElementPresent(modaluseremail, 30);
			WebElement element = findElement(modaluseremail);
			while (element.isDisplayed() == false) {
				Thread.sleep(2000);
				System.out.println("element email isDisplayed....FALSE");
			}
			element.clear();
			element.sendKeys(email);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates enterReferenceTextBox(). <br>
	 * Function to enterReferenceTextBox <br>
	 * 
	 * @param String
	 *            reference
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void enterReferenceTextBox(String reference) {
		try {
			// waitForElementPresent(modalreference, 30);
			WebElement element = findElement(modalreference);
			while (element.isDisplayed() == false) {
				Thread.sleep(2000);
				System.out.println("element reference isDisplayed....FALSE");
			}
			element.clear();
			element.sendKeys(reference);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates setTimepicker(). <br>
	 * Function to setTimepicker <br>
	 * 
	 * @param String
	 *            text
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setTimepicker(String hourly) {
		try {
			WebElement element = findElement(timepicker);
			new Select(element).selectByVisibleText(hourly);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates setDuration(). <br>
	 * Function to setDuration <br>
	 * 
	 * @param String
	 *            time
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void setDuration(String time) {
		try {
			WebElement element = findElement(duration);
			new Select(element).selectByVisibleText(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates clickCustomerPhoneType(). <br>
	 * Function to clickCustomerPhoneType <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickCustomerPhoneType() {
		try {
			// waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(customerPhone);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates clickSubmitRequestBooking(). <br>
	 * Function to clickSubmitRequestBooking <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickSubmitRequestBooking() {
		try {
			// waitForElementPresent(translationLanguage, 20);
			WebElement element = findElement(submitRequest);
			// element.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function demonstrates clickSkickaBokningButton(). <br>
	 * Function to clickSkickaBokningButton <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @see oTestSpreadSheetFactory()
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void clickSkickaBokningButton() {
		try {
			// waitForElementPresent(translationLanguage, 20);
			// WebElement element = findElement(modalEmailformbtn);
			// element.click();
			// Actions actions = new Actions(driver);
			// actions.moveToElement(element).click().perform();

			WebElement element = findElement(modalEmailformbtn);
			while (element.isDisplayed() == false) {
				Thread.sleep(2000);
				System.out.println("element butoon isDisplayed....FALSE");
			}
			element.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method get MesssageConfirmationText. <br>
	 * 
	 * @param None
	 * @returns none.
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public String getMesssageConfirmationText() {
		try {
			WebElement element = findElement(messsageConfirmation);
			while (element.isDisplayed() == false) {
				Thread.sleep(2000);
				System.out
						.println("element messsageConfirmation isDisplayed....FALSE");
			}
			return element.getText();

		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * This function demonstrates validateCustomerHomePageElements(). <br>
	 * Function provides all @Tests with a function to validate elements on the
	 * login page. <br>
	 * 
	 * @param showWarnings
	 *            - if true will only show warning messages if elements are not
	 *            present. <br>
	 *            False will throw errors if elements are not found.
	 * @return none
	 * @see oTestPasswordVault
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public void validateCustomerHomePageElements(boolean showWarnings) {

		if (showWarnings) {

			if (!isTranslationLanguageDisplayed())
				Reporter.log(
						"Warning: translationLanguageTextBox missing on login page!",
						true);

		} else { // Throw errors

			Assert.assertTrue(isTranslationLanguageDisplayed());

		}

	}// validateCustomerHomePageElements

	public int getDayPlus() {
		return dayPlus;
	}

	public void setDayPlus(int dayPlus) {
		this.dayPlus = dayPlus;
	}

	/**
	 * This class Demonstrates elanceFindWorkPage. <br>
	 * Used to validate all page elements on the elanceFindWork PAge <br>
	 * 
	 * @param None
	 * @returns None
	 * @exception None.
	 */
	public static class Test {
		public static void main(final String[] args) {
			WebDriver driver = new FirefoxDriver();
			if (driver != null) {
				CustomerHomePage efwp = new CustomerHomePage(driver);

				if (null != efwp) {
					driver.get("http://dev.digitaltolk.com/login");
					try {
						// efwp.validateLoginPageElements(false);
						System.out.println("PASSED: Verify Login page: "
								+ efwp.pageURL);
					} catch (Exception e) {
						System.out.println("FAILED: Verify Login page: "
								+ efwp.pageURL);
					}
					driver.close();
				}
			}

		}

	}

}
