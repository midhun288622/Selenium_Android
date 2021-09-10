package com.scs.test;

import java.net.MalformedURLException;
import java.util.Map;
import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

public class GroupTest extends BaseAppTest{

	
	
	/*--Create a new group--*/
	@Test(dataProvider="TestData")
	public void GP0001(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("group_random2", groupName);
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		groupPage.createGroup(groupName);
		homepage.searchDevice(groupName);
		ExtentTestManager.compareEquals(driver,homepage.isDeviceListed
				(groupName, 20), true, softAssert,
				"validate Group name is created");
		softAssert.assertAll();
	}
	
	/*Adding devices to groups */
	@Test(dataProvider="TestData")
	public void GP0002(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName);
		groupPage.clickOnSettings(groupName);
		groupPage.searchInGroups(testData.get("DeviceName"));
		groupPage.addDeviceToGroup(groupName);
		FunctionLibrary.syncTillTimePeriod(20);
		homepage.searchDevice(groupName);
		FunctionLibrary.syncTillTimePeriod(20);
		String actual=groupPage.validateDeviceAddedIntoGroup(groupName);
		ExtentTestManager.compareEquals(driver, actual, "1 Devices", softAssert, "Validate Add Device to Group");
		softAssert.assertAll();
	}
	/*--Edit group--*/
	@Test(dataProvider="TestData")
	public void GP0003(Map<String, String> testData)
	{
		
		String groupName1=FunctionLibrary.getRandomString(10,"STRING");
		String groupName2=FunctionLibrary.getRandomString(10,"STRING");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		groupPage.createGroup(groupName1);
		homepage.searchDevice(groupName1,5);
		devicesPage.clickDeviceSettings();
		groupPage.clickOnEditGroupIcon();
		groupPage.editGroup(groupName2);
		groupPage.clickBackArrow();
		homepage.searchDevice(groupName2);
		ExtentTestManager.compareEquals(driver,homepage.isDeviceListed(groupName2), true, softAssert,
				"validate Group name is created");
		softAssert.assertAll();
	}
	
	
	/*--Group level operations--*/
	@Test(dataProvider="TestData")
	public void GP0004(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName,5);
		devicesPage.clickSunSettings();
		devicesPage.switchOffDevice();
		devicesPage.switchOnDevice();
		devicesPage.switchOffDevice();
		boolean decreaseIntecity=groupPage.validateDecreaseIntencity();
		boolean increaseIntecity=groupPage.validateIncreaseIntencity();
		ExtentTestManager.compareEquals(driver, decreaseIntecity, true, softAssert, "validate Decrease Intencity");
		ExtentTestManager.compareEquals(driver, increaseIntecity, true, softAssert, "validate Increase Intencity");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDecreaseCCT(), true, softAssert, "validate Decrease CCT");
		ExtentTestManager.compareEquals(driver, devicesPage.validateIncreaseCCT(), true, softAssert, "validate Increase CCT");
		softAssert.assertAll();
	}
	@Test(dataProvider="TestData")
	public void GP0005(Map<String, String> testData)
	{	
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName,5);
		devicesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		groupPage.editIntencity(testData.get("IntencityValue"), testData.get("ColorValue"));
		String value= groupPage.getIntencityVlue();
		ExtentTestManager.compareEquals(driver,value, testData.get("IntencityValue"), softAssert, "validate Intencity Value");
		ExtentTestManager.compareContains(driver, groupPage.getColorVlue(), testData.get("ColorValue"), softAssert, "validate Color Value");
		softAssert.assertAll();
	}
	/*--Associated device count on Group--*/
	@Test(dataProvider="TestData")
	public void GP0006(Map<String, String> testData)
	{	
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName,5);
		String deviceCount=groupPage.getDeviceCount();
		ExtentTestManager.compareEquals(driver,deviceCount,testData.get("Expected"), softAssert, "validate Intencity Value");
		softAssert.assertAll();
	}
	/*-- Image change --*/
	@Test(dataProvider="TestData")
	public void GP0007(Map<String, String> testData) throws MalformedURLException
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName,5);
		devicesPage.clickDeviceSettings();
		groupPage.clickOnChangeImageBtn();
		scenesPage.validateAllImageIconsPresent();
		boolean flag=groupPage.changeImage();
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate change image");
		softAssert.assertAll();
	}
	@Test(dataProvider="TestData")
	public void GP0008(Map<String, String> testData)
	{	
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName,5);
		devicesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		spacesPage.editRGB(testData.get("RedRGB"), testData.get("GreenRGB"),testData.get("BlueRGB"));
		devicesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		boolean flag= spacesPage.validateRGB(testData.get("RedRGB"), testData.get("GreenRGB"),testData.get("BlueRGB"));
		ExtentTestManager.compareEquals(driver,flag, true, softAssert, "Validate RGB color Value");
		softAssert.assertAll();
	}
	/*Remove devices from groups*/
	@Test(dataProvider="TestData")
	public void GP0020(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName);
		groupPage.clickOnSettings(groupName);
		groupPage.searchInGroups(testData.get("DeviceName"));
		groupPage.removeDeviceFromGroup(groupName);
		homepage.searchDevice(groupName);
		String actual=groupPage.validateDeviceAddedIntoGroup(groupName);
		ExtentTestManager.compareEquals(driver, actual, "0 Devices", softAssert, "Validate Add Device to Group");
		softAssert.assertAll();
	}
	/*--Delete a new group--*/
	@Test(dataProvider="TestData")
	public void GP0030(Map<String, String> testData) throws MalformedURLException
	{
		String groupName1=FunctionLibrary.fileReader("group_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		groupPage.clickOnGroupsIcon();
		homepage.searchDevice(groupName1,5);
		devicesPage.clickDeviceSettings();
		boolean flag=groupPage.deleteGroup(groupName1);
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate delete name");
		homepage.searchDevice(groupName1,2);
		FunctionLibrary.swipeScreen("DOWN", driver);
		FunctionLibrary.syncTillTimePeriod(100);
		boolean flag1=homepage.isDeviceListed(groupName1,20);
		ExtentTestManager.compareEquals(driver,flag1 , false, softAssert, "validate Device name is  not listed");
		softAssert.assertAll();
	}
}
