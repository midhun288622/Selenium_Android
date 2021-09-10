package com.scs.listeners;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.scs.driverfactory.BaseTest;
import com.scs.report.ExtentManager;
import com.scs.report.ExtentTestManager;

/**
 * Customized Listener class for implement all test level setup.
 * @author MXC0RO7
 *         
 */
public class TestListener extends BaseTest implements ITestListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
   
   
    private static String getTestMethodName(ITestResult testResult) {
        return testResult.getMethod().getConstructorOrMethod().getName();
    }
   
    private static String getTestClassName(ITestResult testResult) {
        return testResult.getTestClass().getRealClass().getSimpleName();
    }
    
    @Override
    public void onStart(ITestContext testContext) {
    	
        testContext.setAttribute("WebDriver", this.getDriver());
      
    }

    @Override
    public void onTestStart(ITestResult testResult) {
    	
        LOGGER.info("Test case extent report setup started");
        String category = getTestClassName(testResult);
        String testname=getTestMethodName(testResult);
        ExtentTestManager.startTest(testname,"");
        ExtentTestManager.getTest().assignCategory(category);
        
        
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {

        LOGGER.info("Test case execution passed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        Object testClass = testResult.getInstance();
        try {
           WebDriver webDriver = ((BaseTest) testClass).getDriver();
		   ExtentTestManager.takeScreenshot(webDriver, "PASS", "");
		} catch (NullPointerException e) {
			LOGGER.error("Screenshot capture failed due to driver is null"+e.getMessage());
		
		}
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestFailure(ITestResult testResult) {

        LOGGER.info("Test case execution failed");
        ExtentTestManager.getTest().log(LogStatus.FAIL, testResult.getThrowable());
        Object testClass = testResult.getInstance();
        try {
			WebDriver webDriver = ((BaseTest) testClass).getDriver();
		    ExtentTestManager.takeScreenshot(webDriver, "FAIL", "Test Failed");
		} catch (NullPointerException e) {
			LOGGER.error("Screenshot capture failed due to driver is null"+e.getMessage());
		}
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {

        ExtentTestManager.getTest().log(LogStatus.SKIP, testResult.getThrowable().getMessage());
        Object testClass = testResult.getInstance();
        try {
			 WebDriver webDriver = ((BaseTest) testClass).getDriver();
			 ExtentTestManager.takeScreenshot(webDriver, "SKIP", "Test Skipped");
        } catch (NullPointerException e) {
			LOGGER.error("Screenshot capture failed due to driver is null"+e.getMessage());
		}
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(testResult));
    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub
        
    }

}
