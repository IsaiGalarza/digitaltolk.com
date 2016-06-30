package com.digitaltolk.qa.page.declaration;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.common.oTest.oTestBasePageDeclaration;

public class AcceptTraslatorBookingPage extends oTestBasePageDeclaration {
	
	private String usernameTextBox = "usernameTextBox";
	private String passwdTextBox = "passwdTextBox";
	
	// public loginPageData[] loginPageData = null;
	static ResourceBundle resources;

	static {
		try {
			resources = ResourceBundle.getBundle("digitaltolk.main.LoginPage",
					Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.out.println("loginPage.properties not found: " + mre);
			System.exit(0);
		}
	}
	
	public AcceptTraslatorBookingPage(WebDriver driver){
		super(driver,resources);
		pageTitle = new String(resources.getString("pageTitle"));
		pageURL = new String(resources.getString("pageURL"));
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
}
