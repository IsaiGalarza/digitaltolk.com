package com.digitaltolk.qa.page.declaration;

import java.util.ArrayList;
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

public class TranslatorHomePage extends oTestBasePageDeclaration {
	
	private String listBookings = "listBookings";
	
	
	static ResourceBundle resources;
	
	static
	{
		try
		{
			resources = ResourceBundle.getBundle("digitaltolk.main.TranslatorHomePage",Locale.getDefault());
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
	
	public TranslatorHomePage(WebDriver driver){
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
	
	
	/***
	 **
	 * This function demonstrates acceptFirstBooking() <br>
	 * Function that acceptFirstBooking of tasks
	 * 
	 * @return false if some status has wrong title
	 * @version 1.0
	 * @throws Exception 
	 * @see oTestSpreadSheetFactory()
	 * @author Brayam
	 */
	public boolean acceptFirstBooking() {
		try {
			ArrayList<WebElement> linksAccept  = (ArrayList<WebElement>) findElements(listBookings);
			for (WebElement element : linksAccept) {
				element.click();
				return true;
			}
			return false;
		} catch (Exception e) {
			Reporter.log(e.toString(),true);
			return false;
		}
	}
//	
//	/***
//	 * This function demonstrates clickDownloadGuideTopButton(). <br>
//	 * Function to click theDownloadGuideTopButton <br>
//	 * 
//	 * @param None
//	 * @returns none.
//	 * @exception None.
//	 * @see oTestSpreadSheetFactory()
//	 * @author ysaigalarza
//	 * @version 1.0
//	 */
//	public void clickDownloadGuideTopButton() {
//		try {
//			ArrayList<WebElement> elements = (ArrayList<WebElement>) findElements(DownloadGuideTopButton);
//
//			for (WebElement webElement : elements) {
//				if (webElement.isDisplayed()) {
//					webElement.click();
//					return;
//				}
//			}
//			Reporter.log("target Download Guide Button not found", true);
//		} catch (Exception e) {
//			Reporter.log(e.toString(), true);
//		}
//	}
//	
	
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
				TranslatorHomePage efwp = new TranslatorHomePage(driver);
			
				if(null != efwp){
					driver.get("http://dev.digitaltolk.com/login");
					try{				
						//efwp.validateLoginPageElements(false);
						System.out.println("PASSED: Verify Login page: " +efwp.pageURL);
					}catch(Exception e){
						System.out.println("FAILED: Verify Login page: " +efwp.pageURL);
					}
						driver.close();
				}
			}
			
		}
	
	}
	
}
