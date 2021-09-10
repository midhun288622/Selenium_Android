package com.scs.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.scs.driverfactory.BaseTest;
/**
 * Customized  class for implement create extends report.
 * @author MXC0RO7
 *         
 */

public class ExtentManager {

    private static ExtentReports extent;

    /**
     * to get report object.
     */
    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
         
            extent = new ExtentReports(BaseTest.getRepoPath(), true);
        }
        return extent;
    }
}
