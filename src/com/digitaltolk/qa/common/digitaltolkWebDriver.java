package com.digitaltolk.qa.common;

import com.saucelabs.ci.sauceconnect.SauceConnectTwoManager;
import com.saucelabs.saucerest.SauceREST;

import org.apache.commons.exec.OS;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.AddRotatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.common.oTest.*;
import com.opera.core.systems.*;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipException;

import static org.testng.Assert.assertEquals;

//import org.sikuli.webdriver.SikuliFirefoxDriver;

/**
 * This class Demonstrates oTestWebDriverFactory().
 * <br>This class drives all oTest testNG tests.
 * <br> oTestWebDriveFactory encapsulates all other oTest factories.
 * <br>This class encapsulates all the oDesk. obo, mail_gui, and mobile page declarations.  
 * <br>
 * @param resources  ResouceBundle that contains testing parameters
 * @param oTestZipFactory  tool for zipping all screen shots
 * @param oTestSpreadSheetFactory tool for loading test data
 * @param screenGrab  tool for taking screen captures
 * @param oTestHTTPFactory tool for building HTTP requests
 * @param public String xlFile   xlFile that contains list of API's to test
 * @param public String xlWorkSheet  worksheet contained inside xlFile
 * @param public String xlTable 	 xlTable inside the worksheet
 * @return None.
 * @exception None.
 * @author ysaigalarza
 * @version 1.0
 */
@SuppressWarnings("unused")
public class digitaltolkWebDriver {

	public static String EmailedUserName ="ForgotUserName";
	public static String EmailedPassword ="ForgotPassword";
	
	//
	// ASSERT STATES
	//
	private static int FALSE_STATE = 0;
	private static int UNDEFINED_STATE = 2;
	private static int TRUE_STATE = 1;
	//
	// What type of web driver to create
	//
	public static String localWebDriver = "local";
	public static String phantomJSWebDriver = "phatomJS";
	public static String sauceLabWebDriver = "sauceLabs";
	public static String proxyWebDriver = "proxy";
	public static String browserStackWebDriver = "browserStack";
	//
	// type of proxy OS
	//
	public static String proxyVista = "vista";
	public static String proxyWindows = "windows";
	public static String proxyXP = "xp";
	public static String proxyWin8 = "win8";
	public static String proxyMAC = "mac";
	public static String proxyLinux = "linux";
	//
	// types of proxy browsers
	//
	public static String proxyFireFox = "fireFox";
	public static String proxyIE = "ie";
	public static String proxySafari = "safari";
	public static String proxyChrome = "chrome";
	public static String proxyOpera = "opera";
	//
	// Local web Drivers
	//
	public static String localFireFox = "firefox";
	public static String localIE = "ie";
	public static String localSafari = "safari";
	public static String localChrome ="chrome";
	public static String localOpera = "opera";
	public static String localMobileagent = "mobileagent";
	
	//
	// oTest Factories
	//
	private oTestScreenCaptureFactory sg = null;
	private oTestScreenVideoCaptureFactory videoCapture = null;
	private oTestEmailFactory email = null;
	private oTestPasswordVault passwordVault = null;
	private oTestLoremIpsumFactory oTestLoremIpsumFactory = null;
	//
	// DataProvider Information
	//
	public String testRunXLS = null;
	public String testRunTableLabel = null;
	public String testRunWorkSheet = null;
	public String[][] data = null;
	//
	// saucelabs
	//
	private SauceConnectTwoManager sauceConnectManager = null;
	private String sauceConnectLoginName = null;
	private String sauceConnectAccessKey = null;
	private String localPathToSauceConnectJarFile = null;
	private String localPathToSauceConnectJarFileMAC = null;
	//
	// screen grab direcrtory
	//
	private String screenGrabFilePath = null;

	private String methodName = null;
	//
	// PhatomJS properties
	//
	private String phantomexePath = null;
	private String phantomJSPath = null;
	private String phantomMacexePath = null;
	private String phantomMACJSPath = null;
	//
	// Selenium items
	//
	private  WebDriverWait wait; // new WebDriverWait(driver, 30);
	protected RemoteWebDriver driver;
	private DesiredCapabilities proxyCap = null;
	//
	// Mobile items
	//
	public static final String portrait = "PORTRAIT";
	public static final String landscape = "LANDSCAPE";
	//
	// local variables
	//
	private boolean remoteServerRunning = false;
	private DesiredCapabilities capabillities = null;
//	private String smsTo = null;
//	private String proxy =  null; // new String(resources.getString("ProxyIP-1"));
//	private boolean referenceCheck = true;
	private boolean remote = false;
	private static ResourceBundle resources;
	private Executor executor = null;
	private String osName = null;
	private boolean failProxyToLocalWebDriver;
	private boolean enableSMS = false;
	//
	// Tray item is running. See if we can access it and send messages
	//
	private remoteWebDriverIndicator rwdi = null;
	
	
	private String proxy =  null; // new String(resources.getString("ProxyIP-1"));
	
	//
	// FireFox Net Export File
	//
	private String fireBugFile = null;  // "fireBugFile";
	private String netExportFile = null; // "netExportFile";
	private String netEportLogFileDir = null; //netEportLogFileDir
	
	static {
		try {
			resources = ResourceBundle.getBundle("common.oTestGeneral", Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.out.println("oTestGeneral.properties not found: "+ mre);
			System.exit(0);
		}
	}
	
	/**
	 * oTestWebDriverFactory() constructor
	 * @param None
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public digitaltolkWebDriver() {
	
		osName = new String(System.getProperty("os.name"));
		failProxyToLocalWebDriver = false;
		//
		// SauceLab Connection Information
		//
		sauceConnectManager = new SauceConnectTwoManager();
		sauceConnectLoginName =  new String(resources.getString("sauceConnectLoginName"));
		sauceConnectAccessKey =  new String(resources.getString("sauceConnectAccessCode"));
//		localPathToSauceConnectJarFile = "\\JARs\\Sauce-Connect.jar";
		localPathToSauceConnectJarFile = "\\JARs\\saucelabs\\1.0.14\\sauc-connect-1.0.14.jar";
		localPathToSauceConnectJarFileMAC = "/JARs/Sauce-Connect.jar";
		//
		// screen Grap Directory
		//
		screenGrabFilePath = new String(resources.getString("screenGrabFilePath"));
		//
		// Selenium Grid IP
		
		proxy =  new String(resources.getString("ProxyIP-1"));
		
		//
//		proxy =  new String(resources.getString("ProxyIP-1"));
		//
		// Setup Http Factory
		//
		// PhantomJS paths
		//
		phantomexePath = new String(resources.getString("phantomexe"));
		phantomJSPath = new String(resources.getString("phantomJS"));
		phantomMacexePath = new String(resources.getString("phantomexeMAC"));
		phantomMACJSPath = new String(resources.getString("phantomJSMAC"));
		//
		// Net Export File
		//
		fireBugFile = new String(resources.getString("fireBugFile"));
		netExportFile = new String(resources.getString("netExportFile"));
		netEportLogFileDir = new String(resources.getString("netEportLogFileDir"));		
	}
	/**
	 * oTestWebDriverFactory() constructor
	 * @param ResourceBundle to initialize all Factories
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public digitaltolkWebDriver(ResourceBundle remoteResources) {
		//
		// SauceLabs Information
		//
		sauceConnectManager = new SauceConnectTwoManager();
		sauceConnectLoginName =  new String(remoteResources.getString("sauceConnectLoginName"));
		sauceConnectAccessKey =  new String(remoteResources.getString("sauceConnectAccessCode"));

		localPathToSauceConnectJarFile = "\\executableJobs\\sauce-connect-plugin-1.0.14\\Sauce-Connect.jar";
		localPathToSauceConnectJarFileMAC = "/executableJobs/sauce-connect-plugin-1.0.14/Sauce-Connect.jar";
		//
		// Used to Zip screen snaps for any given test
		//
		
		osName = new String(System.getProperty("os.name"));
		//
		// If true oTestWebdriver will try to fail-over to local web driver
		//
		failProxyToLocalWebDriver = false;
		//
		
		// screen Grap Directory
		//
		screenGrabFilePath = new String(remoteResources.getString("screenGrabFilePath"));
		//
		
		// PhantomJS paths
		//
		phantomexePath = new String(remoteResources.getString("phantomexe"));
		phantomJSPath = new String(remoteResources.getString("phantomJS"));
		//
		// Net Export File in constructor
		//
		fireBugFile = new String(remoteResources.getString("fireBugFile"));
		netExportFile = new String(remoteResources.getString("netExportFile"));
		netEportLogFileDir = new String(remoteResources.getString("netEportLogFileDir"));
	}
	
	//
	// See if the Test needs to connect to Saucelabs
	//
	/**
	 * checkRemoteSauceConnection() 
	 * <br> Opens the Sauce connection tunnel if the dynamic data wants to run against remote browser
	 * @param None
	 * @return None.
	 * @exception None.
	 * @see startSauceConnectionServer()
	 * @author davidwramer
	 * @version 1.0
	 */
	public void checkRemoteSauceConnection() {
		//
		// Very important that the data columns are like this
		// column 0 is test number
		// column 1 is yes/no to run specific configuration
		// column 5 is remote yes/no if yes then saucelabs tunnel needs to be opened
		//
		for (int cnt = 0; cnt < data.length; cnt++) {
			if (data[cnt][5].equals(sauceLabWebDriver)
					&& data[cnt][1].toLowerCase().contains("yes")) {
				startSauceConnectionServer();
				break;
			}
		}
	}

	
	
	
	/**
	 * oTestScreenVideoCaptureFactory() 
	 * <br> Encapsulate the oTestScreenVideoCaptureFactory
	 * @param None
	 * @return oTestScreenVideoCaptureFactory
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public oTestScreenVideoCaptureFactory getoTestScreenVideoCaptureFactory(String run) {
		
		if (videoCapture == null)
			videoCapture = new oTestScreenVideoCaptureFactory(run);
		return videoCapture;
	}
	/**
	 * getoTestScreenVideoCaptureFactory() 
	 * <br>Encapsulate the oTestScreenVideoCaptureFactory. This method returns the videoCapture factory
	 * @param None
	 * @return oTestScreenVideoCaptureFactory
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public oTestScreenVideoCaptureFactory getoTestScreenVideoCaptureFactory() {
		return videoCapture;
	}
	
	

	
	/**
	 * getScreenGrabber() Encapsulate the screenGrab
	 * @param None
	 * @return screenGrab
	 * @see screenGrab
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public oTestScreenCaptureFactory getScreenGrabber() {
		return sg;
	}
	
	/**
	 * setFailProxyToLocalWebDriver() Call this to enable proxy failover to local webdriver.
	 * <br> This only works for tests that call webdriver for same OS
	 * @param None
	 * @return String - nameOfTheOS
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void setFailProxyToLocalWebDriver(){
		failProxyToLocalWebDriver = true;
		System.out.println("Proxy WebDriver will failover to local webDriver for the following OS: "+osName);
		
	}
	
	/**
	 * getoTestLoremIpsumFactory() This function return oTestLoremIpsumFactory object
	 * @param None
	 * @return oTestLoremIpsumFactory
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public oTestLoremIpsumFactory getoTestLoremIpsumFactory(){
		if(oTestLoremIpsumFactory == null)
			oTestLoremIpsumFactory = new oTestLoremIpsumFactory();
		return oTestLoremIpsumFactory;
	}
	
	
	/**
	 * getOSName() returns the name of the OS that is running
	 * @param None
	 * @return String - nameOfTheOS
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public String getOSName(){
		return osName;
	}
	
	/**
	 * sleep() used to stop the thread
	 * @param time - milli-seconds
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time); // 1 minute wait
		} catch (InterruptedException e) {
			System.out.println("Error during Driver sleep");
			e.printStackTrace();
		}
	}
	/**
	 * cleanWebDriver() clear the global webDriver and videoCapture
	 * @param None
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	private void cleanWebDriver(){
		driver = null;
		videoCapture = null;
	}
	
	
	/**
	 * createSilukiWebDriver() Main class that creates the createSilukiWebDriver
	 * <br>- This webDrive extends the FireFox webdriver that runs on local host
	 * <br>- See https://answers.launchpad.net/sikuli
	 * <br>- download jar file from http://www.java2s.com/Code/Jar/s/Downloadsikuliwebdriver101jar.htm
	 * @return SikuliFirefoxDriver
	 * @see SikuliFirefoxDriver
	 * @return SikuliFirefoxDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
//	Disable due to Sikuli is using old javacv dependency. 
//	public SikuliFirefoxDriver createSilukiWebDriver() {
//		
//		SikuliFirefoxDriver driver = new SikuliFirefoxDriver(); 
//		return driver;
//	}
	/**
	 * createFireFoxNetPanelWebDriver()
	 * <br> createFireFoxNetPanelWebDriver creates a FireFox web driver with Firebug and NetPanel installed
	 * <br> and active. This web driver is used to test page load times with firefox netPanel.  You still have to parse
	 * <br> the net Panel log files for any meaningful information.
	 * @param none
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver createFireFoxNetPanelWebDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		//
		// plugins for the firefox webdriver
		//
        File firebug = new File(fireBugFile);
        File netExport = new File(netExportFile);

        try
        {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        }
        catch (IOException err)
        {
            System.out.println(err);
            return null;
        }

        // 
        // Set default Firefox preferences
        //
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        // 
        // Set default Firebug preferences
        //
        profile.setPreference(domain + "currentVersion", "2.0");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // 
        // Set default NetExport preferences
        //
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.autoExportToFile", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        // 
        // Need to fix path information here
        //
        profile.setPreference(domain + "netexport.defaultLogDir", netEportLogFileDir);
        //profile.setPreference(domain + "netexport.defaultLogDir", "C:\\");

        RemoteWebDriver driver = new FirefoxDriver(profile);
        return driver;
	}// createFireFoxNetPanelWebDrier
	
	/**
	 * buildNewBrowserStackRemoteWebDriver(String os, String browser, String browserVersion)
	 * <br> buildNewBrowserStackRemoteWebDriver creates a FireFox web driver given desired capabilities
	
	 * @param String os = name of the OS
	 * @param String browser - name of browser
	 * @param String browser version - 
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	private RemoteWebDriver buildNewBrowserStackRemoteWebDriver(String os, String browser, String browserVersion){
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", browser);
	    caps.setCapability("browser_version", browserVersion);
	    caps.setCapability("os", os);
	    // caps.setCapability("os_version", "XP");
	    caps.setCapability("browserstack.debug", "true");
	    oTestBrowserStackWebDriverFactory bs = new oTestBrowserStackWebDriverFactory();
	    if(null != bs){
	    	return (RemoteWebDriver) (bs.getBrowserStackWebDriver(caps));
	    }
	    return null;
	    
	}
	
	
	/**
	 * createWebDriver() Main class that creates the selenium web driver
	 * @param remote  - local/proxy/Saucelabs/phatomjs
	 * @param version = browser/Ipad/Android/IPhone version 
	 * @param platform - what OS (LINUX,MAC, WINDOWS)
	 * @param browser - (Firefox,Chrome,IOS,Android,IE)
	 * @param setupString - Test description
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @return None
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver createWebDriver(String remote, String version, String platform, String browser, String setupString) {
		//
		// Closes all the page declarations
		//
		cleanWebDriver();
		
		driver = null;
		
		this.methodName = new String(setupString);
		
		String screenGrabberInfo = null;
		//
		// Test on sauceLabs cloud servers
		//
		if(remote.equals(browserStackWebDriver)){
			this.remote = true;
			driver = buildNewBrowserStackRemoteWebDriver(platform, browser, version);
			
		}else
		if (remote.equals(sauceLabWebDriver)) {
			this.remote = true;
			if (browser.toLowerCase().contains("ipad"))
				driver = buildNewIPadRemoteDriver(platform, version);
			if (browser.toLowerCase().contains("firefox"))
				driver = buildNewFireFoxRemoteDriver(platform, version);
			if (browser.toLowerCase().contains("ie"))
				driver = buildNewIERemoteDriver(platform, version);
			if (browser.toLowerCase().contains("chrome"))
				driver = buildNewChromeRemoteDriver(platform, version);
			if (browser.toLowerCase().contains("iphone"))
				driver = buildNewIPhoneRemoteDriver(platform, version);
			if (browser.toLowerCase().contains("android"))
				driver = buildNewAndroidRemoteDriver(platform, version);
			if (browser.toLowerCase().contains("safari"))
				driver = buildNewSafariRemoteDriver(platform, version);
			
			screenGrabberInfo = new String(sauceLabWebDriver);
		} else if (remote.equals(proxyWebDriver)) {
			//
			// Selenium Grid
			// To start the grid on windows: java -jar selenium-server-standalone-2.35.0.java -role hub
			// You have to start a node on the hub if you want to use the hub for testing.
			// To start nodes  java -jar selenium-server-standalone-2.35.0.jar -role node -hub http://<IP>:4444/grid/register
			// on Mac set the -cp (classpath) java -cp /home/downloads/selenium-server-standalone-2.35.0.jar -role node -hub http://<IP>:4444/grid/register
			// on mac make sure you down load the chrome exe for webdriver and put in same directory.
			// on export Ubuntu 12.04
			//	 ctrl +alt + F1 to open console
			//	 then enter "export DISPLAY=:0"  "DISPLAY" MUST BE CAPITALIZED!!!!!!
			//	 java -cp /home/<dir for standalone server>/selenium-server-standalone-2/35/0.jar -jar selenium-server-standalone-2.35.0.jar -role node -hub http://<IP of hub>:4444/grid/register
			// WINDOWS VISTA
			//
			if (platform.toLowerCase().contains(proxyVista) ) {
				
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.VISTA,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.VISTA,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.VISTA,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.VISTA,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.VISTA,version);
				//
				// Windows
				//
			}else if( platform.toLowerCase().contains(proxyWindows) ){
				
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.WINDOWS,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.WINDOWS,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.WINDOWS,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.WINDOWS,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.WINDOWS,version);
				//
				// Windows XP
				//
			}else if( platform.toLowerCase().contains(proxyXP) ){
				
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.XP,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.XP,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.XP,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.XP,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.XP,version);
				//
				// Windows 8
				//
			}else if( platform.toLowerCase().contains(proxyWin8) ){

				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.WIN8,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.WIN8,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.WIN8,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.WIN8,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.WIN8,version);
				//
				// Macintosh
				//
			}else if (platform.toLowerCase().contains(proxyMAC)) {
				
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.MAC,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.MAC,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.MAC,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.MAC,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.MAC,version);
				//
				// Linux
				//
			} else if(platform.toLowerCase().contains(proxyLinux)) {
				
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.LINUX,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.LINUX,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.LINUX,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.LINUX,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.LINUX,version);
				//
				// None Specified
				//
			}else{// NO Proxy OS was specified in the XLS
				if (browser.toLowerCase().contains(proxyFireFox))
					driver = buildNewFireFoxProxyDriver(proxy,Platform.ANY,version);
				if (browser.toLowerCase().contains(proxyIE))
					driver = buildNewIEProxyDriver(proxy,Platform.ANY,version);
				if (browser.toLowerCase().contains(proxyChrome))
					driver = buildNewChromeProxyDriver(proxy,Platform.ANY,version);
				if (browser.toLowerCase().contains(proxySafari))
					driver = buildNewSafariProxyDriver(proxy,Platform.ANY,version);
				if (browser.toLowerCase().contains(proxyOpera))
					driver = buildNewOperaProxyDriver(proxy,Platform.ANY,version);
			}
			
			screenGrabberInfo = new String("proxy-" + proxy.trim());
			
		} else if (remote.equals(phantomJSWebDriver)) {
			//
			// PhantomJS
			//
			driver = buildNewPhantomDriver();
			screenGrabberInfo = new String("phantomJSWebDriver");
			
		} else if (remote.equals(localWebDriver)) {
			//
			// Local web Driver 
			//
			if (browser.toLowerCase().contains(localFireFox))
				driver = buildNewFireFoxLocalDriver();
			if (browser.toLowerCase().contains(localIE))
				driver = buildNewIELocalDriver();
			if (browser.toLowerCase().contains(localChrome))
				driver = buildNewChromeLocalDriver();
			if (browser.toLowerCase().contains(localSafari))
				driver = buildNewSafariLocalDriver();
			if (browser.toLowerCase().contains(localOpera))
				driver = buildNewOperaLocalDriver();
			if (browser.toLowerCase().contains(localMobileagent))
				driver = buildNewMobileFireFoxLocalDriver();
			
			this.remote = false;
			screenGrabberInfo = new String("local");
			//
			// Create the system Tray app to post messages to tester
			//
			// rwdi = new remoteWebDriverIndicator();
			// rwdi.createAndShowTrayIcon();
		}else{
			System.out.println("invalid remote setting, must define remote as: "+ localWebDriver +", "+phantomJSWebDriver +", "+proxyWebDriver +", or"+ sauceLabWebDriver);
			driver = null;
		}
		if(driver != null){
			//
			// Setup Screen Capture
			//
			if(sg != null) sg = null;
			sg = new oTestScreenCaptureFactory(driver, screenGrabberInfo + "-" + setupString);
			//
			// Can't take screen shots from Proxy box.
			//
			sg.setRemote(remote);
			wait =  new WebDriverWait(driver, 30);
			//
			// Clean all cookies
			//
			driver.manage().deleteAllCookies();
		}
		
		return driver;

	}

	

	//
	// @Optional("dramer") String username,
	// @Optional("c0d25cce-6def-4404-809c-311a2b90f0fb") String key,
	// @Optional("MAC") String os,
	// @Optional("iphone") String browser,
	// @Optional("5.0") String browserVersion,
	// Method method) throws Exception

	public boolean isSauceConnectionServerRunning() {

		return remoteServerRunning;
	}
	/**
	 * obtainTestStatusInformation()
	 * <br> This function when called will return the test status from saucelabs for the currently running test
	 * @param Title - Title of the message
	 * @param message = body of the messsage
	 * @return String with the status
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public String obtainTestStatusInformation() {

		if (remote) {
			/*
			 * to get your jobID: Selenium 1: String jobID =
			 * browser.getEval("selenium.sessionId"); Selenium 2: String jobID =
			 * ((RemoteWebDriver) driver).getSessionId().toString();
			 */

			SauceREST client = new SauceREST(sauceConnectLoginName, sauceConnectAccessKey);
			/*
			 * Using a map of udpates:
			 * (http://saucelabs.com/docs/sauce-ondemand#
			 * alternative-annotation-methods)
			 * 
			 * Map<String, Object> updates = new HashMap<String, Object>();
			 * updates.put("name", "this job has a name"); updates.put("passed",
			 * true); updates.put("build", "c234");
			 * client.updateJobInfo("<your-job-id>", updates);
			 */
			String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
			String jobInfo = client.getJobInfo(jobID);
			System.out.println("Job info: " + jobInfo);
			// client.jobPassed(jobID);
			// client.jobFailed("<your-job-id>");
			return new String(jobInfo);
		} else {
			return new String("Local Web Driver");
		}
	}
	
	/**
	 * enableSMS()
	 * <br> This function enable SMS messages from ASSERT_TRUE, and ASSERT_FALSE
	 * @param None
	 * @param None
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 * @deprecated Please use the disable/enable switch inside Factory classes
	 */
	public void enableSMS(){
		enableSMS = true;
	}
	/**
	 * disableSMS()
	 * <br> This function disable SMS messages from ASSERT_TRUE, and ASSERT_FALSE
	 * @param None
	 * @param None
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 * @deprecated Please use the disable/enable switch inside Factory classes
	 */
	public void disableSMS(){
		enableSMS = false;
	}
	/**
	 * smsStatus()
	 * <br> This function returns the status of  SMS messages
	 * @param None
	 * @param None
	 * @return boolean  true if sms messages are enabled, false otherwise
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 * @deprecated Please use the disable/enable switch inside Factory classes
	 */
	public boolean smsStatus(){
		return enableSMS;
	}
	/**
	 * setupDataProvider()
	 * <br> This function when called generates the data[]. You must have setup testRunXLS, testRunWorkSheet, testRunTableLabel
	 * @param none
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void setupDataProvider() {
		oTestSpreadSheetFactory xlFactory = new oTestSpreadSheetFactory();
		data = xlFactory.getTableArray(testRunXLS, testRunWorkSheet,
				testRunTableLabel);
	}
	/**
	 * closeSauceLabsConnectionServer()
	 * <br> This function when called terminates the connection to the saucelab
	 * @param none
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void closeSauceLabsConnectionServer() {
		System.out.println("Closing Sauce Connection Server");
		if (sauceConnectManager != null)
			sauceConnectManager.closeTunnelsForPlan("ysaigalarza", null);
		remoteServerRunning = false;
	}
	
	/**
	 * imageReferenceCheck()
	 * <br> This function does a screen capture reference check
	 * @param image - the reference image to check.
	 * @return Boolean -True if image matched reference check image, false otherwise
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public boolean imageReferenceCheck(String image){
		//
		// This does not work yet. 8/27/2013
		//
		return true;
		//if(!referenceCheck) return true;
		//return(sg.imageReferenceCheck(image));
	}
	/**
	 * buildNewIELocalDriver()
	 * <br> This function setsup the IE local webDriver.
	 * <br> Manually launch the Sauce Connect C:\Program
	 * <br>  Files\eclipse\sauce-connect-plugin-1.0.14>java -jar Sauce-Connect.jar
	 * <br>  dramer c0d25cce-6def-4404-809c-311a2b90f0fb Debug messages will be
	 * <br>  sent to sauce_connect.log 2013-02-23
	 * <br>  18:08:58.303:INFO::jetty-7.x.y-SNAPSHOT 2013-02-23
	 * <br>  18:08:58.356:INFO::Started SelectChannelConnector@0.0.0.0:49992
	 * <br>  .---------------------------------------------------. | Have
	 * <br>  questions or need help with Sauce Connect? | | Contact us:
	 * <br>  http://support.saucelabs.com/forums | | Terms of Service:
	 * <br>  http://saucelabs.com/tos |
	 * @param None
	 * @return None
	 * @see http://support.saucelabs.com/forums
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void startSauceConnectionServer() {

		if (sauceConnectManager != null && !remoteServerRunning) {
			try {
				executor = Executors.newFixedThreadPool(1);
				System.out.println("<======JAVA CLASS PATH =============>");
				System.out.println(System.getProperty("java.class.path"));
				System.out.println("<======JAVA CLASS PATH =============>");
				//add working dir path to SauceConnectJar
				String workingDir = System.getProperty("user.dir");
				if(osName.toUpperCase().contains("MAC")){
					workingDir = workingDir + localPathToSauceConnectJarFileMAC;	
				}else {
					workingDir = workingDir + localPathToSauceConnectJarFile;
				}
							
				sauceConnectManager.openConnection(sauceConnectLoginName,sauceConnectAccessKey,4445,
						         new File( workingDir),null, null);
				remoteServerRunning = true;
			} catch (IOException e1) {
				System.out.println("Failed to open sauce Lab connection: ");
				e1.printStackTrace();
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Error waiting for sauceConnect to start");
				e.printStackTrace();
			}
		}

	}
	/**
	 * buildNewIELocalDriver()
	 * <br> This function setsup the IE local webDriver.
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewIELocalDriver() {

		return (driver = new InternetExplorerDriver());

	}
	
	/**
	 * buildNewOperaProxyDriver()
	 * <br> This function setsup the selenium grid webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - use Selenium Platform
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @see Platform
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewOperaProxyDriver(String proxyInfo,Platform os, String browserVersion) {
		System.out.println("Building "+os.name()+" Safari Web Driver for Selenium Grid");
		try {
			String proxyServer = "http://" + proxyInfo.trim()+ ":4444/wd/hub";
			capabillities = DesiredCapabilities.opera();
			capabillities.setCapability("platform", os.name());
			capabillities.setCapability("name", methodName);
			driver = new RemoteWebDriver(new URL(proxyServer), capabillities);
		} catch (IOException e) {
			//
			// If the failover to local driver is true and the OS matches call local driver
			//
			if(failProxyToLocalWebDriver && osName.toLowerCase().contains(os.name())){
				System.out.println("Proxy Failover to local Web driver");
				return  buildNewSafariLocalDriver();
			}else{
				return null;
			}
		}
		return (driver);
	}
	
	/**
	 * buildNewSafariProxyDriver()
	 * <br> This function setsup the selenium grid webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - use Selenium Platform
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @see Platform
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewSafariProxyDriver(String proxyInfo,Platform os, String browserVersion) {
		System.out.println("Building "+os.name()+" Safari Web Driver for Selenium Grid");
		try {
			String proxyServer = "http://" + proxyInfo.trim()+ ":4444/wd/hub";
			capabillities = DesiredCapabilities.safari();
			capabillities.setCapability("platform", os.name());
			capabillities.setCapability("name", methodName);
			driver = new RemoteWebDriver(new URL(proxyServer), capabillities);
		} catch (IOException e) {
			//
			// If the failover to local driver is true and the OS matches call local driver
			//
			if(failProxyToLocalWebDriver && osName.toLowerCase().contains(os.name())){
				System.out.println("Proxy Failover to local Web driver");
				return  buildNewSafariLocalDriver();
			}else{
				return null;
			}
		}
		return (driver);
	}
	/**
	 * buildNewIEProxyDriver()
	 * <br> This function setsup the selenium grid webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - not used, default is linux
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewIEProxyDriver(String proxyInfo,Platform os, String browserVersion) {
		
		System.out.println("Building "+os.name()+" Internet Explorer Web Driver for Selenium Grid");
		try {
			URL proxyServer = new URL("http://" + proxyInfo + ":4444/wd/hub");
			capabillities = DesiredCapabilities.internetExplorer();
			capabillities.setCapability("platform", os.name());
			capabillities.setCapability("name", methodName);
			driver = new RemoteWebDriver(proxyServer, capabillities);
		} catch (IOException e) {
			//
			// If the failover to local driver is true and the OS matches call local driver
			//
			if(failProxyToLocalWebDriver && osName.toLowerCase().contains(os.name())){
				System.out.println("Proxy Failover to local Web driver");
				return  buildNewIELocalDriver();
			}else{
				return null;
			}
		}
		return (driver);
	}
	/**
	 * buildNewSafariLocalDriver()
	 * <br> This function setsup local Safari webdriver
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewSafariLocalDriver() {
		return (driver = new SafariDriver());
	}
	
	/**
	 * buildNewSafariLocalDriver()
	 * <br> This function setsup local Safari webdriver
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewOperaLocalDriver() {	
		return (driver = new OperaDriver());
	}
	
	/**
	 * buildNewMobileFireFoxLocalDriver() 
	 * It uses custom user agent profile for mobile testing Android devices  
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception none
	 * @auther dmann
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewMobileFireFoxLocalDriver(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference(
				"general.useragent.override", 
				"Mozilla/5.0 (Linux; Android 4.4.2); Nexus 5 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Mobile Safari/537.36 OPR/20.0.1396.72047");
		return (driver = new FirefoxDriver(profile));
	}
	
	/**
	 * buildNewFireFoxLocalDriver()
	 * <br> This function setsup local firefox webdriver
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewFireFoxLocalDriver() {
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setEnableNativeEvents(true);
		
		return (driver = new FirefoxDriver(profile));
	}
	/**
	 * buildNewPhantomDriver()
	 * <br> This function setsup the phatomJS webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - not used, default is linux
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewPhantomDriver() {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);

		if(osName.contains("Windows")){
			System.out.println("Looking for PhantomJS in: "+ phantomexePath);
			System.out.println("Looking for PhantomJS main.js in: "+ phantomJSPath);
			caps.setCapability("phantomjs.binary.path",phantomexePath);
			caps.setCapability("phantomjs.ghostdriver.path",phantomJSPath);
		}else{   //for MAC or Linux
			System.out.println("Looking for PhantomJS in: "+ phantomMacexePath);
			System.out.println("Looking for PhantomJS main.js in: "+ phantomMACJSPath);
			caps.setCapability("phantomjs.binary.path",phantomMacexePath);
			caps.setCapability("phantomjs.ghostdriver.path",phantomMACJSPath);
		}

		PhantomJSDriver mDriver = new PhantomJSDriver(caps);
		driver = (RemoteWebDriver) mDriver;
		return (driver);
	}
	/**
	 * buildNewFireFoxProxyDriver()
	 * <br> This function setsup the selenium grid webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - not used, default is linux
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewFireFoxProxyDriver(String proxyInfo,Platform os, String browserVersion) {
		System.out.println("Building "+os.name()+" Firefox Web Driver for Selenium Grid");
		try {
			String proxyServer = "http://" + proxyInfo.trim()+ ":4444/wd/hub";
			capabillities = DesiredCapabilities.firefox();
			capabillities.setCapability("platform", os);
			capabillities.setCapability("name", methodName);
			driver = new RemoteWebDriver(new URL(proxyServer), capabillities);
		} catch (IOException e) {
			//
			// If the failover to local driver is true and the OS matches call local driver
			//
			if(failProxyToLocalWebDriver && osName.toLowerCase().contains(os.name())){
				System.out.println("Proxy Failover to local Web driver");
				return  buildNewFireFoxLocalDriver();
			}else{
				return null;
			}
		}
		return (driver);
	}
	/**
	 * buildNewChromeLocalDriver()
	 * <br> This function setsup the local chrome web driver.
	 * @param proxyInfo - browser version
	 * @param platform - not used, default is linux
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewChromeLocalDriver() {
		if(OS.isFamilyWindows()){

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedrivers/win32/chromedriver.exe");
		}
		if(OS.isFamilyMac()){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedrivers/mac32/chromedriver");
		}
		System.out.println("Loading chromdriver...");
		return (driver = new ChromeDriver());
	}
	/**
	 * buildNewChromeProxyDriver()
	 * <br> This function setsup the selenium grid webdriver.
	 * @param proxyInfo - browser version
	 * @param platform - Uses Selenium Platform
	 * @param browserVersion - version of the browser
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewChromeProxyDriver(String proxyInfo,Platform os, String browserVersion) {
		System.out.println("Building "+os.name()+" Chrome Web Driver for Selenium Grid");
		try {
			String proxyServer = "http://" + proxyInfo.trim()+ ":4444/wd/hub";
			if(os.name().equals("MAC")){
				System.setProperty("webdriver.chrome.driver","/user/downloads/chromedriver");
			}
			capabillities = DesiredCapabilities.chrome();
			capabillities.setCapability("platform", os.name());
			capabillities.setCapability("name", methodName);
			driver = new RemoteWebDriver(new URL(proxyServer), capabillities);
		} catch (IOException e) {
			//
			// If the failover to loca driver is true and the OS matches call local driver
			//
			if(failProxyToLocalWebDriver && osName.toLowerCase().contains(os.name())){
				System.out.println("Proxy Failover to local Web driver");
				return  buildNewChromeLocalDriver();
			}else{
				return null;
			}
		}
		return (driver);
	}
	/**
	 * buildNewIERemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	  * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewIERemoteDriver(String platform, String version) {

		capabillities = DesiredCapabilities.internetExplorer();
		capabillities.setCapability("platform", platform);
		capabillities.setCapability("version", version);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewChromeRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewChromeRemoteDriver(String platform,String version) {

		capabillities = DesiredCapabilities.chrome();
		capabillities.setCapability("platform", platform);
		if (!version.toLowerCase().contains("none")) { // SauceLabs recommends
														// not to set the
														// browser version if on
														// MAC or Linux
			capabillities.setCapability("version", version);
		}
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create Chrome Remote Driver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewSafariRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewSafariRemoteDriver(String platform,String version) {

		capabillities = DesiredCapabilities.safari();
		capabillities.setCapability("platform", platform);
		if (!version.toLowerCase().contains("none")) { // SauceLabs recommends
														// not to set the
														// browser version if on
														// MAC or Linux
			capabillities.setCapability("version", version);
		}
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create Chrome Remote Driver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewIPadRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewIPadRemoteDriver(String platform,
			String version) {

		capabillities = DesiredCapabilities.ipad();
		capabillities.setCapability("platform", platform);
		capabillities.setCapability("version", version);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewIPhoneRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewIPhoneRemoteDriver(String platform,
			String version) {

		capabillities = DesiredCapabilities.iphone();
		capabillities.setCapability("platform", platform/* "Mac 10.8" */);
		capabillities.setCapability("version", version /* "6" */);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return this.driver;
	}
	/**
	 * buildNewFireFoxRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewFireFoxRemoteDriver(String platform,
			String version) {

		capabillities = DesiredCapabilities.firefox();
		capabillities.setCapability("platform", platform);
		capabillities.setCapability("version", version);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewOperaRemoteDriver()
	 * <br> This function setsup the saucelabs webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewOperaRemoteDriver(String platform,
			String version) {




		capabillities = DesiredCapabilities.opera();
		capabillities.setCapability("platform", platform/* "Mac 10.8" */);
		capabillities.setCapability("version", version /* "6" */);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewPhantomRemoteDriver()
	 * <br> This function setsup the phantomJS webdriver.
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewPhantomRemoteDriver(String platform,String version) {

		capabillities = DesiredCapabilities.phantomjs();
		capabillities.setCapability("platform", platform/* "Mac 10.8" */);
		capabillities.setCapability("version", version /* "6" */);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewAndroidRemoteDriver()
	 * <br> This function setsup the DesiredCapabilities for the saucelab web driver
	 * @param version - browser version
	 * @param platform - not used, default is linux
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewAndroidRemoteDriver(String platform,String version) {

		capabillities = DesiredCapabilities.android();
		capabillities.setCapability("platform", "Linux");
		capabillities.setCapability("version", version /* "4" */);
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	/**
	 * buildNewConfiguredRemoteDriver()
	 * <br> This function setsup the DesiredCapabilities for the saucelab web driver
	 * @param browser - browser name
	 * @param version - browser version
	 * @param platformOS - platform (Mac, Windows, linux)
	 * @return RemoteWebDriver
	 * @see RemoteWebDriver
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public RemoteWebDriver buildNewConfiguredRemoteDriver(String browser,
			String browserVersion, String platformOS) {

		capabillities = new DesiredCapabilities();
		capabillities.setBrowserName(browser);
		capabillities.setCapability("version", browserVersion);
		capabillities.setCapability("platform", Platform.valueOf(platformOS));
		capabillities.setCapability("name", methodName);
		try {
			this.driver = new RemoteWebDriver(new URL("http://" + sauceConnectLoginName
					+ ":" + sauceConnectAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabillities);
		} catch (MalformedURLException e) {
			System.out.println("unable to create RemoteWebDriver: " + e);
		}
		return driver;
	}
	
	/**
	  * logReader()
	  * <br> This function displays all log entries to standard out
	  * @param none
	  * @return none
	  * @exception None.
	  * @author davidwramer
	  * @version 1.0
	  */
	public void logReader(){
		if(driver != null){
			Logs l = driver.manage().logs();
			Set<String> logEntries = l.getAvailableLogTypes();
			for (String obj: logEntries) {
				/* ... do something to each object ... */
				System.out.println("Log entries: "+obj);
			}
		}
	}//logReader
	  
	/**
	 * showWhatWebDriversCanBeCreated()
	 * <br> This supporting function is used to show combinations of web Drivers people can create
	 * <br> 
	 * @param none
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	private void showWhatWebDriversCanBeCreated(){
		
		System.out.println("oTest can create the following web drivers");
		System.out.println("SikuliFirefoxDriver");
		System.out.println("createFireFoxNetPanelWebDriver");
		System.out.println("Local web Drivers");
		System.out.println("\tbrowser = fireFox");
		System.out.println("\tbrowser = IE");
		System.out.println("\tbrowser = Safari");
		System.out.println("\tbrowser = Chrome");
		System.out.println("\tbrowser = Opera");
		System.out.println("Any SauceLab web Drivers");
		System.out.println("Any Browser Stack web Drivers");
		System.out.println("PhatomJS web Drivers");
		System.out.println("The following Selenium grid web Drivers");
		System.out.println("\t OS = vista");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		System.out.println("\t OS = Windows");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		System.out.println("\t OS = XP");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		System.out.println("\t OS = Windows 8");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		System.out.println("\t OS = Macintosh");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		System.out.println("\t OS = Linux");
		System.out.println("\t\t browser = fireFox");
		System.out.println("\t\t browser = IE");
		System.out.println("\t\t browser = Safari");
		System.out.println("\t\t browser = Chrome");
		System.out.println("\t\t browser = Opera");
		
	}
	
	/**
	 * ASSERT_TRUE()
	 * <br> This function wraps the  selenium Assert.assertTrue with access to hipchat and SMS
	 * @param boolean answer - the assert answer
	 * @param message = body of the message
	 * @param smsHeader = header message for sms messages
	 * @return None.
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void ASSERT_TRUE(boolean answer,String message,String smsHeader){
		
		 Assert.assertTrue(answer,message);
		
	}
	
	/**
	 * selfTest()
	 * <br> This supporting function is used to validate oTestWebDriverFactory is connecting
	 * <br> to phytomJS, Local web Driver, and Sauce Labs
	 * @param none
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	public void selfTest(){
		String[] args = {};
		RemoteWebDriver driver = null;
		String platform = this.getOSName();
		String country = Locale.getDefault().getCountry();	
		//
		// Show all web driver combinations
		//
		showWhatWebDriversCanBeCreated();
		
		//
		// First Test create local web Driver
		//
		System.out.println("\n==> 1. Local Web Driver Self Test <==");
		driver = createWebDriver(localWebDriver, "25", platform, "FireFox","oTest Self Test - local firefox web driver");
		if (driver != null) {
			System.out.println("Starting local web driver test");
			driver.get("https://www.google.com");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				Assert.assertTrue(driver.getCurrentUrl().contains("https://www.google.com"));
				sg.takeScreenShot("google landing page");
				System.out.println("Test 1 passed: Create local web driver");
				
			}catch(TimeoutException e){
				System.out.println("Test 1 Failed: Local web driver no created");
			}
			driver.quit();
			
		} else {
			System.out.println("Test 1 Failed: Local web driver no created");
		}
		//
		// Second Test phantomJS
		//
		System.out.println("\n==> 2. PhantonJS Self Test <==");
		driver = null;
		driver = createWebDriver(phantomJSWebDriver, "25", platform, "PhantomJS","oTest Self Test - PhantomJS");
		if (driver != null) {
			System.out.println("Starting local phantomJS test");
			driver.get("https://www.google.com");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				Assert.assertTrue(driver.getCurrentUrl().contains("https://www.google.com"));
				sg.takeScreenShot("google landing page");
				System.out.println("Test 2 passed: Create phatomJS web driver");
				
			}catch(TimeoutException e){
				System.out.println("Test 2 Failed: phatomJS web Driver no created");
			}
			driver.quit();

		} else {
			System.out.println("Test 2 Failed: phatomJS web Driver no created");
		}
		
		//
		// Third Test SauceLabs connection
		//
		System.out.println("\n==> 3. SauceLabs Connection Self Test <==");
		startSauceConnectionServer();
		driver = null;
		driver = createWebDriver(sauceLabWebDriver, "18", "Linux", "FireFox","oTest Self Test - SauceLabs");
		if (driver != null) {
			System.out.println("Starting SauceLabs test");
			driver.get("https://www.google.com");
			sleep(10);
			try{
				Assert.assertTrue(driver.getCurrentUrl().contains("https://www.google.com"));
				System.out.println("Test 3 passed: Create sauceLabs web driver");
				
			}catch(TimeoutException e){
				System.out.println("Test 3 Failed: sauceLabs web Driver no created");
			}
			driver.quit();
			closeSauceLabsConnectionServer();
		} else {
			System.out.println("Test 3 Failed: sauceLabs web Driver no created");
		}
		
		//
		// Forth Test create Firefox NetPanel web Driver
		//
		System.out.println("\n==> 4. Firefox NetPanel / Har Parser Factory self test <==");
		
		//oTestHARParserFactory.Test.main(args);		
		//
		// Fifth Test for oTestSpreadSheetFactory
		//
		System.out.println("\n==> 5. Spread Sheet Factory self test <==");
		oTestSpreadSheetFactory.Test.main(args);
		//
		// Sixth Test for oTestZipFactory
		//
		System.out.println("\n==> 6. Zip Factory self test <==");
		
		//
		// Eighth test for oTestSlackFactory
		//
		//
		// Ninth test for oTestPasswordVault
		//
		System.out.println("\n==> 9. Password Vault self test <==");
		oTestPasswordVault.Test.main(args);
		//
		// Tenth test for oTestBrowserStackWebDriverFactory 
		//
		System.out.println("\n==> 10. oTest Browser Stack Web Driver Factory self test <==");
		oTestBrowserStackWebDriverFactory bs = new oTestBrowserStackWebDriverFactory();
		if(bs != null)bs.selfTest();
		//
		// Eleven test for oTestScrollIntoView feature
		
		//
		// Twelve test for oTestSMSFactory feature
//		System.out.println("\n==> 12. oTestSMSFactory self test <==");
//		getoTestSMSFactory().selfTest();
		//
		//
		
		//
		//
		// Fourteen test for oTestLoremIpsumFactory feature
		System.out.println("\n==> 14. oTestLoremIpsumFactory self test <==");
		oTestLoremIpsumFactory oTestLoremIpsumFactory = new oTestLoremIpsumFactory();
		oTestLoremIpsumFactory.selfTest();
		
		System.out.println("\n==> Self Test Complete <==");
	}
	 
	 /**
	  * dragAndDrop()
	  * <br> This function takes 2 elemengs and drags the first element to the second element.
	  * <br> https://code.google.com/p/selenium/wiki/AdvancedUserInteractions
	  * @param WebElement  from element to be dragged
	  * @param WebElement  to  where the from element will be dropped
	  * @return void
	  * @see oTestWebDriverFactory
	  * @exception None.
	  * @author davidwramer
	  * @version 1.0
	  */
	public void dragAndDrop(WebElement from, WebElement to){
		Actions builder = new Actions(driver);

		Action dragAndDrop = builder.clickAndHold(from)
		   .moveToElement(to)
		   .release(to)
		   .build();

		dragAndDrop.perform();
	}
	
	
	/**
	 * getEmailFactory()
	 * <br> This function return the instance of getEmailFactory
	 * @param none
	 * @return oTestEmailFactory
	 * @exception none
	 * @author Jason So
	 * @version 1.0
	 */
	public oTestEmailFactory getEmailFactory(){
		if(email == null){
			email = new oTestEmailFactory();
		}
		return email;
	}
	
	/**
	 * getPasswordVaultFactory()
	 * <br> This function return the instance of getPasswordVaultFactory
	 * @param none
	 * @return getPasswordVault
	 * @exception none
	 * @author Jason So
	 * @version 1.0
	 */
	public oTestPasswordVault getPasswordVaultFactory(){
		if(passwordVault == null){
			passwordVault = new oTestPasswordVault();
		}
		return passwordVault;
	}
	
	/**
	 * patternMatcher()
	 * <br> This supporting function is used for testing only
	 * @param none
	 * @return none
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
//	public void patternMatcher(){
//		Pattern pattern = buildPatternFromURL("https://www.odesk.com/e/\\d+/jobs/");
//		final Pattern pattern1 = Pattern.compile("https://www.odesk.com/e/\\d+/jobs/");
//		
//		Matcher matcher = pattern.matcher("https://www.odesk.com/e/12345/jobs/");
//		if (matcher.find()) {
//			System.out.println("matched");
//		} else {
//			System.out.println("did not match");
//		}
//		Matcher matcher1 = pattern1.matcher("https://www.odesk.com/e/12345/jobs/");
//		if (matcher.find()) {
//			System.out.println("matched");
//		} else {
//			System.out.println("did not match");
//		}
//	}
	
	//
	// Inner class for testing on the command line
	//
	public static class Test {
		public static void main(final String[] args) {

			oTestWebDriverFactory slwd = new oTestWebDriverFactory();
			slwd.disableAllMessaging();
			System.out.println("OS running: "+slwd.getOSName());
			System.out.println("created oTestWebDriverFactory class");
			System.out.println("Starting self test");
			slwd.selfTest();
			
		}// main
	}// Test

	public Object getLoginPage() {
		// TODO Auto-generated method stub
		return null;
	}
}// oTestWebDriverFactory
















