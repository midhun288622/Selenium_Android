package com.scs.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.scs.commons.FunctionLibrary;
import com.scs.driverfactory.BaseTest;
import com.scs.report.ExtentTestManager;

/**
 *Customized Listener class for retry the test case for failed test case.
 * @author MXC0RO7
 *         
 */
public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static  int maxTry = Integer.parseInt(FunctionLibrary
            .readPropertyValue("retry_Count")); 

    @Override
    public boolean retry(ITestResult testResult) {
        if (!testResult.isSuccess()) { 
            if (count < maxTry) { 
                count++; 
                testResult.setStatus(ITestResult.FAILURE);
                extendReportsFailOperations(testResult);
                return true; 
            }
        } else {
            testResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    /**
     * Function is used to log the fail operations.
     */
    public void extendReportsFailOperations(ITestResult testResult) {
        Object testClass = testResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

}
