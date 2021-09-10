package com.scs.test;

import java.net.MalformedURLException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

public class SpacesTest extends BaseAppTest{

	/*--Create new space--*/
	@Test(dataProvider="TestData")
	public void SP0001(Map<String, String> testData)
	{
		String spaceName=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("space_random", spaceName);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		spacesPage.createSpace(spaceName);
		homepage.searchDevice(spaceName);
		ExtentTestManager.compareEquals(driver,spacesPage.isDeviceListed(spaceName,10), true, softAssert,
				"validate Space is created");
		softAssert.assertAll();
	}
	/*--Edit space--*/
	@Test(dataProvider="TestData")
	public void SP0002(Map<String, String> testData)
	{
		
		String spaceName1=FunctionLibrary.fileReader("space_random");
		String spaceName2=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("space_random2", spaceName2);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1,5);
		spacesPage.clickSpaceSettings();
		spacesPage.clickOnEditGroupIcon();
		spacesPage.editSpace(spaceName2);
		spacesPage.clickBackArrow();
		homepage.searchDevice(spaceName2);
		ExtentTestManager.compareEquals(driver,spacesPage.isDeviceListed(spaceName2,10), true, softAssert,
				"validate SpaceName name is created");
		softAssert.assertAll();
	}
	/*--Delete space--*/
	@Test(dataProvider="TestData")
	public void SP0020(Map<String, String> testData) throws MalformedURLException
	{
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1,5);
		spacesPage.clickSpaceSettings();
		boolean flag=spacesPage.deleteSpace();
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate delete name");
		homepage.searchDevice(spaceName1,2);
		FunctionLibrary.swipeScreen("DOWN", driver);
		FunctionLibrary.syncTillTimePeriod(10);
		ExtentTestManager.compareEquals(driver, spacesPage.isDeviceListed(spaceName1,10), false, softAssert, "validate Space name is  not listed");
		softAssert.assertAll();
	}
	

	/*-- Image change --*/
	@Test(dataProvider="TestData")
	public void SP0003(Map<String, String> testData) throws MalformedURLException
	{
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1);
		spacesPage.clickSpaceSettings();
		spacesPage.clickOnChangeImageBtn();
		scenesPage.validateAllImageIconsPresent();
		boolean flag=spacesPage.changeImage();
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate change image");
		softAssert.assertAll();
	}
	/*Adding devices to groups */
	@Test(dataProvider="TestData")
	public void SP0004(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1);
		spacesPage.clickSpaceSettings();
		spacesPage.searchInSpace(groupName);
		spacesPage.addGroupToSpace(spaceName1);
		homepage.searchDevice(spaceName1);
		String actual=spacesPage.getGroupCount(spaceName1);
		ExtentTestManager.compareEquals(driver, actual, "1 Groups", softAssert, "Validate Add SpaceName to Group");
		softAssert.assertAll();
	}
	
	/*--Group level operations--*/
	@Test(dataProvider="TestData")
	public void SP0005(Map<String, String> testData)
	{
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1,5);
		spacesPage.clickSunSettings();
		devicesPage.switchOffDevice();
		devicesPage.switchOnDevice();
		devicesPage.switchOffDevice();
		ExtentTestManager.compareEquals(driver, groupPage.validateDecreaseIntencity(), true, softAssert, "validate Decrease Intencity");
		ExtentTestManager.compareEquals(driver, groupPage.validateIncreaseIntencity(), true, softAssert, "validate Increase Intencity");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDecreaseCCT(), true, softAssert, "validate Decrease CCT");
		ExtentTestManager.compareEquals(driver, devicesPage.validateIncreaseCCT(), true, softAssert, "validate Increase CCT");
		softAssert.assertAll();
	}
	/*--Edit value on sun settings--*/
	@Test(dataProvider="TestData")
	public void SP0006(Map<String, String> testData)
	{	
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1,5);
		spacesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		groupPage.editIntencity(testData.get("IntencityValue"), testData.get("ColorValue"));
		String intvalue= spacesPage.getIntencityVlue();
		ExtentTestManager.compareEquals(driver,intvalue, testData.get("IntencityValue"), softAssert, "validate Intencity Value");
		String colorValue=spacesPage.getColorVlue();
		ExtentTestManager.compareContains(driver, colorValue, testData.get("ColorValue"), softAssert, "validate Color Value");
		softAssert.assertAll();
	}
	
	/*--Edit values RGB--*/
	@Test(dataProvider="TestData")
	public void SP0007(Map<String, String> testData)
	{	
		String spaceName1=FunctionLibrary.fileReader("space_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		spacesPage.clickOnSpaceIcon();
		homepage.searchDevice(spaceName1,5);
		spacesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		spacesPage.editRGB(testData.get("RedRGB"), testData.get("GreenRGB"),testData.get("BlueRGB"));
		groupPage.clickOnEditIntencity();
		boolean flag= spacesPage.validateRGB(testData.get("RedRGB"), testData.get("GreenRGB"),testData.get("BlueRGB"));
		ExtentTestManager.compareEquals(driver,flag, true, softAssert, "Validate RGB color Value");
		softAssert.assertAll();
	}
	
}
