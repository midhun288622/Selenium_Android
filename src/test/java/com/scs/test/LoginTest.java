package com.scs.test;

import java.util.Map;
import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

public class LoginTest extends BaseAppTest{

	
	/*Adding devices to groups */
	@Test(dataProvider="TestData")
	public void LG0001(Map<String, String> testData)
	
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		ExtentTestManager.logTestResult("INFO","Validate Inorrect password");
		if(!login.isLoginButtonPresent())
		{
	 	 homepage.clickOnMoreBtn();
		 FunctionLibrary.swipeScreen("UP",driver);
		 login.logout();
		 FunctionLibrary.isElementPresentByElements(login.getForgotLink());
		}
		boolean flag = login.validate_errorMessage1();
		ExtentTestManager.compareEquals(driver, flag, true, softAssert,
	    		"Validate error message1");
		softAssert.assertAll();
	}
	/*Adding devices to groups */
	@Test(dataProvider="TestData")
	public void LG0002(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		ExtentTestManager.logTestResult("INFO","Validate Inorrect username");
		if(!login.isLoginButtonPresent())
		{
	 	 homepage.clickOnMoreBtn();
		 FunctionLibrary.swipeScreen("UP",driver);
		 login.logout();
		}
		boolean flag1 = login.validate_InvalidUsername("adadadad@asdad","afdafaf@123");
		ExtentTestManager.compareEquals(driver, flag1, true, softAssert,
	    		"Validate error message1");
		
		softAssert.assertAll();
	}
	/*Login-Language Change */
	@Test(dataProvider="TestData")
	public void LG0003(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		if(!login.isLoginButtonPresent())
		{
	 	 homepage.clickOnMoreBtn();
		 FunctionLibrary.swipeScreen("UP",driver);
		 login.logout();
		}
		boolean flag=login.changeItaliLanguage();
		ExtentTestManager.compareEquals(driver, flag, true, softAssert,
	    		"Change italy language");
		boolean flag1=login.changeEnglishLanguage();
		ExtentTestManager.compareEquals(driver, flag1, true, softAssert,
	    		"Change English language");
		softAssert.assertAll();
	}
	
}
