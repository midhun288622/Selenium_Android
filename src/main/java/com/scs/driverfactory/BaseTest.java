package com.scs.driverfactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestDataHandler;
import com.scs.commons.TestDataHandlerPOI;
import com.scs.report.ExtentTestManager;

import io.appium.java_client.AppiumDriver;

import com.scs.exceptions.DataSheetException;
import com.scs.listeners.AnnotationTransformer;
import com.scs.listeners.ExecutionListener;
import com.scs.listeners.SuiteListener;
import com.scs.listeners.TestListener;

import jxl.read.biff.BiffException;

/**
 * class for all the configurations and integrations is done through this class.
 * All qmetry related integrations , Extends report integrations,test data
 * handling are implemented in Base test. This class handling the major part of
 * the framework
 * 
 * @author MXC0RO7
 * 
 */

@Listeners({ TestListener.class, SuiteListener.class, ExecutionListener.class, AnnotationTransformer.class})
public abstract class BaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
	private static String testDataSource;
	private static String externalSheetPath;
	protected WebDriver driver;
	private String testBrowser;
	private static ResourceBundle globalProperties;
	private HashMap<String, String> exelMap;
	private static final String reportPath = FunctionLibrary.readPathFromPropertyFile("reportPath");
	private static final String browser = FunctionLibrary.readPropertyValue("browser");
	public SoftAssert softAssert;

	public WebDriver getDriver() {
		return driver;
	}

	public static String getRepoPath() {
		
		//return reportPath+System.getenv("timestamp")+"/Report.html";
		return reportPath+FunctionLibrary.getCurrentDateAndTime()+"/Report.html";
	}

	public static String externalSheetPath() {
		return externalSheetPath;
	}

	public void setBrowserName(String browserName) {

		this.testBrowser = browserName;
	}


	/**
	 * constructor Name WebHooks parameters :NA Purpose :This constructor is used to
	 * load the property files.
	 */
	public BaseTest() {


		getGlobalProperties();
		//FunctionLibrary.deleteDirectory("./Report");
	}
	
	/**
	 * Load the browser browser.
	 * @throws BiffException 
	 */
	@BeforeMethod
	public void beforemethod(Method method) {
		try {
			
			LOGGER.info("\n");
			LOGGER.info("####################################################################################");
			LOGGER.info("####---------------------------------------------------------------------------####");
			LOGGER.info("####------------------------------------"+method.getName()+"---------------------------------####");
			LOGGER.info("####---------------------------------------------------------------------------####");
			LOGGER.info("###################################################################################");
			
			setBrowserName(browser);
			if (!(testBrowser == null)) {
				if (testBrowser.equalsIgnoreCase("ios")
						|| testBrowser.equalsIgnoreCase("android")) {


					startWebDriverClient(testBrowser);
					//LOGGER.info("Calling startWebDriverClient for browser: " + testBrowser);


					// call setup pages
					setupPages(driver);
					
					softAssert=new SoftAssert();
					// open base url
					//launchUrl();

				} else {
					LOGGER.error("Browser name" + " '" + testBrowser + "' "
							+ "is invalid:Please give InternetExplorer,Chrome or Firefox");
				}
			}


		} catch (DataSheetException | IOException e) {
			LOGGER.error("before  method execution failed" + e.getMessage());
			throw new DataSheetException("Exception ocuured while capturing the test data");
		}


	}
	/**
	 * This method is called at the end of @BeforeMethod.
	 * This allows subclassers to pre-configure pages for use.
	 * @param driver
	 */

	public void setupPages(WebDriver driver) {

	}
	/**
	 * This method is used the get the test run status and attach the result to
	 * Qmetry.
	 */
	@AfterMethod
	public void afterClass(ITestResult testResult) {

		try {
			
			LOGGER.info("###################################################################################");
			LOGGER.info("####---------------------------------------------------------------------------####");
			LOGGER.info("####--------------------------TC Execution completed--------------------------#####");
			LOGGER.info("####---------------------------------------------------------------------------####");
			LOGGER.info("###################################################################################");
			driver.quit();
		} catch (NullPointerException e) {
			LOGGER.error("driver quit is failed due to driver object is null"+e.getMessage());

		}
	}




	/*******************************************************************************************************
	 * method Name :getGlobalProperties parameters :NA Purpose :This method is used
	 * to set the global properties.
	 ******************************************************************************************************/
	protected static void getGlobalProperties() {
		globalProperties = ResourceBundle.getBundle("global");
		externalSheetPath = FunctionLibrary.readPathFromPropertyFile("external_sheet_path");
		if (globalProperties.containsKey("test_data_source")) {
			testDataSource = globalProperties.getString("test_data_source");
		}
		if (testDataSource.equalsIgnoreCase("excel")) {
			externalSheetPath = FunctionLibrary.readPathFromPropertyFile("external_sheet_path");
			if (externalSheetPath == null || externalSheetPath.isEmpty()) {
				LOGGER.error("Please provide a valid sheet path");
				Assert.fail();
			}
		} else {
			LOGGER.error("Please provide a valid test data source value");
			Assert.fail();
		}
	}

	/*******************************************************************************************************
	 * This function Starts WebDriver client based on the given Browser.
	 ******************************************************************************************************/
	protected void startWebDriverClient(String browser) throws MalformedURLException {
		try {

			driver = DriverSetup.getWebdriver(browser);
			if (driver != null) {
				LOGGER.info("startWebDriverClient: Started WebDriver for " + driver);
			} else {
				LOGGER.error("driver value is null");
				Assert.fail();
			}
		} catch (UnreachableBrowserException e) {
			LOGGER.error("Could not start a new session. Possible causes are: " + e.getMessage());
		}
	}

	/*******************************************************************************************************
	 * method Name :getBrowserName parameters :NA Purpose :This method is used to
	 * load the application URL return : NA.
	 ******************************************************************************************************/
	public void launchUrl() {
		try {

			String  applicationUrl = globalProperties.getString("applicationURL");
			if (applicationUrl != null) {

				driver.get(applicationUrl);
				System.out.println("Launch URL");
			}
		} catch (NullPointerException e) {
			ExtentTestManager.logTestResult("FAIL", "launch URL is failed" + e.getMessage());
		}
	}
}