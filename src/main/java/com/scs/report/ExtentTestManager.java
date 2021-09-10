package com.scs.report;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.scs.commons.FunctionLibrary;

/**
 * extentTestMap holds the information of thread id's and ExtentTest instances.
   ExtentReports instance created by calling getReporter() method from ExtentManager.
   At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
   At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
   At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 * @author MXC0RO7
 *         
 */
public class ExtentTestManager {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
    private static Map extentTestMap = new HashMap();
    private static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    /**
     * To start the extent test.
     *
     */
    @SuppressWarnings("unchecked")
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    /*******************************************************************************************************
     * method Name :logTestResult parameters :logType,msg Purpose :This method is
     * used to log the test status into extends report.
     *****************************************************************************************************/
    public static synchronized void logTestResultWithAssert(String logType, String msg) {
        switch (logType) {
        case "PASS":
           
            ExtentTestManager.getTest().log(LogStatus.PASS, msg);
            LOGGER.info(msg);
            Assert.assertTrue(true, msg);
            break;
        case "FAIL":
            ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
            LOGGER.info(msg);
            Assert.fail(msg);
            break;
        case "INFO":
            LOGGER.info(msg);
            ExtentTestManager.getTest().log(LogStatus.INFO, msg);
            break;
        default:
        }

    }
    public static synchronized void loadTest(String tescaseName,String scenario, String category) {
    	scenario=FunctionLibrary.getBoldText(scenario);
		ExtentTestManager.startTest(tescaseName,scenario);
		ExtentTestManager.getTest().assignCategory(category);
    }
    
    
    /*******************************************************************************************************
     * method Name :logTestResult parameters :logType,msg Purpose :This method is
     * used to log the test status into extends report.
     *****************************************************************************************************/
    public static synchronized void logTestResult(String logType, String msg) {
        switch (logType) {
        case "PASS":
           
            ExtentTestManager.getTest().log(LogStatus.PASS, msg);
            LOGGER.info(msg);
            break;
        case "FAIL":
            ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
            LOGGER.info(msg);
            break;
        case "INFO":
            LOGGER.info(msg);
            ExtentTestManager.getTest().log(LogStatus.INFO, msg);
            break;
        default:
        }

    }
    
    public static synchronized void setDiscription(String discription) {
    	ExtentTestManager.getTest().setDescription(discription);

    }

    
    public static synchronized void compareEquals(WebDriver driver,boolean actual,boolean expected,SoftAssert softAssert,String message) {
        if(actual==expected)
        {
        	 ExtentTestManager.getTest().log(LogStatus.PASS, message);
        	 LOGGER.info(message);
        	 softAssert.assertTrue(true,message);
        }
        else
        {
        	 ExtentTestManager.getTest().log(LogStatus.FAIL, message+"  is Failed");
        	 LOGGER.error( message+"  is Failed");
        	 softAssert.fail( message+"  is Failed");
        	 ExtentTestManager.takeScreenshot(driver, "FAIL", message);
        	
        }
    }
    
    public static synchronized void compareEquals(WebDriver driver,String actual,String expected,SoftAssert softAssert,String message) {
        if(actual.equals(expected))
        {
        	 ExtentTestManager.getTest().log(LogStatus.PASS, message);
        	 LOGGER.info(message);
        	 softAssert.assertTrue(true,message);
        }
        else
        {
        	 ExtentTestManager.getTest().log(LogStatus.FAIL, message+"  is Failed");
        	 LOGGER.error( message+"  is Failed");
        	 softAssert.fail( message+"  is Failed");
        	 ExtentTestManager.takeScreenshot(driver, "FAIL", message);
        	
        }
    }
    
    public static synchronized void compareContains(WebDriver driver,String actual,String expected,SoftAssert softAssert,String message) {
        if(actual.contains(expected))
        {
        	 ExtentTestManager.getTest().log(LogStatus.PASS, message);
        	 LOGGER.info(message);
        	 softAssert.assertTrue(true,message);
        }
        else
        {
        	 ExtentTestManager.getTest().log(LogStatus.FAIL, message+"  is Failed");
        	 LOGGER.error( message+"  is Failed");
        	 softAssert.fail( message+"  is Failed");
        	 ExtentTestManager.takeScreenshot(driver, "FAIL", message);
        	
        }
    }
    
    
    
    /**
     * to take the screenshot based on the status.
     *
     */
    public static synchronized void takeScreenshot(WebDriver driver, String status, String msg) {

        // Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        switch (status) {
        case "PASS":
            ExtentTestManager.getTest().log(LogStatus.PASS, msg,
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
            break;
        case "FAIL":
            ExtentTestManager.getTest().log(LogStatus.FAIL, msg,
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
            break;
        case "INFO":
            ExtentTestManager.getTest().log(LogStatus.INFO, msg,
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
            break;

        case "SKIP":
            ExtentTestManager.getTest().log(LogStatus.SKIP, msg,
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
            break;
        default:
        }

    }

}