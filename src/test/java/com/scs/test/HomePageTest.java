package com.scs.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

public class HomePageTest extends BaseAppTest{


	/*Validate App version*/
	@Test(dataProvider="TestData")
	public void HM0001(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		FunctionLibrary.swipeScreen("UP",driver);
		homepage.clickOnAboutBtn();
		String temp=homepage.getVersionData();
		ExtentTestManager.compareEquals(driver,temp, testData.get("AppVerion"), softAssert, "validate app version");
		softAssert.assertAll();
	}
	/*-- User management-Sub user creation --*/
	@Test(dataProvider="TestData")
	public void HM0002(Map<String, String> testData) throws MalformedURLException
	{
		String email="aaa"+FunctionLibrary.getRandomString(7,"STRING")+"@gmail.com";
		String userName="user"+FunctionLibrary.getRandomString(7,"STRING");
		FunctionLibrary.fileWriter("email", email);
		FunctionLibrary.fileWriter("userName", userName);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		homepage.clickOnUserManagement();
		homepage.clickOnAddBtn();
		homepage.creaeBasicDetails(userName, email, testData.get("password1"));
		homepage.searchUser(userName);
		boolean flag=homepage.validateUserIsCreated(userName);
		ExtentTestManager.compareEquals(driver,flag, true, softAssert, "validate user creation");
		softAssert.assertAll();
	}

	/*-- User management-Sub user details modification --*/
	@Test(dataProvider="TestData")
	public void HM0003(Map<String, String> testData) throws MalformedURLException
	{
		String email=FunctionLibrary.fileReader("email");
		String userName=FunctionLibrary.fileReader("userName");

		String email1="aaa"+FunctionLibrary.getRandomString(7,"STRING")+"@gmail.com";
		FunctionLibrary.fileWriter("email1", email);
		FunctionLibrary.fileWriter("userName1", userName);


		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		homepage.clickOnUserManagement();
		homepage.searchUser(userName);
		homepage.clickOnRightArrow(userName);
		homepage.clickOnEditBtn();

		boolean flag=homepage.updateBasicDetails(email1, testData.get("password1"));
	
		ExtentTestManager.compareEquals(driver,flag, true, softAssert, "validate update user");
		softAssert.assertAll();
	}

	/*-- User management-Sub user privileges modification --*/
	@Test(dataProvider="TestData")
	public void HM0004(Map<String, String> testData) throws MalformedURLException
	{
		String userName=FunctionLibrary.fileReader("userName");

		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		homepage.clickOnUserManagement();
		homepage.searchUser(userName);
		homepage.clickOnRightArrow(userName);
		boolean flag1=homepage.validateControlPanelDisplayed(5);

		homepage.clickOnEditBtn();
		homepage.updatePrivilageDetails();
		homepage.searchUser(userName);
		homepage.validateUserIsCreated(userName);
		homepage.clickOnRightArrow(userName);

		boolean flag=homepage.validateControlPanelDisplayed(2);
		if(flag1==flag)
		{
			ExtentTestManager.compareEquals(driver,false, true, softAssert, "validate update  privilage user");
		}
		else
		{
			ExtentTestManager.compareEquals(driver,true, true, softAssert, "validate update  privilage user");
		}
		softAssert.assertAll();
	}
}
