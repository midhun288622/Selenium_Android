package com.scs.test;

import java.net.MalformedURLException;
import java.util.Map;

import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

public class ScenesTest extends BaseAppTest{


	
	
	/*--Create a new Scene--*/
	@Test(dataProvider="TestData")
	public void SC0001(Map<String, String> testData)
	{
		
		String scename=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("scene_random", scename);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		scenesPage.createScene(scename);
		homepage.searchDevice(scename);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(scename,20), true, softAssert,
				"validate Scene name is created");
		softAssert.assertAll();
	}
	/*Add group into scene */
	@Test(dataProvider="TestData")
	public void SC0002(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		String scename1=FunctionLibrary.fileReader("scene_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		scenesPage.clickOnSettings(scename1);
		scenesPage.searchGroupInSenes(groupName);
		scenesPage.addGroupToScene(scename1);
		scenesPage.clickOnBackArrowBtn();
		homepage.searchDevice(scename1);
		String actual=scenesPage.validateGroupAddedToScene(scename1);
		ExtentTestManager.compareContains(driver, actual, testData.get("Expected"), softAssert, "Validate Add group to Scene");
		softAssert.assertAll();
	}
	/*--Add device Into scene--*/
	@Test(dataProvider="TestData")
	public void SC0003(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1,5);
		scenesPage.clickOnSettings(scename1);
		scenesPage.searchDeviceInSenes(testData.get("deviceName"));
		scenesPage.addDeviceToScene();
		scenesPage.clickOnBackArrowBtn();
		homepage.searchDevice(scename1);
		String value=scenesPage.validateAddDeviceToScene(scename1);
		ExtentTestManager.compareContains(driver, value, testData.get("Expected"), softAssert, "validate Add device");
		softAssert.assertAll();
	}
	
	/*--Edt value on sun settings--*/
	@Test(dataProvider="TestData")
	public void SC0004(Map<String, String> testData)
	{	
		String scename1=FunctionLibrary.fileReader("scene_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1,5);
		devicesPage.clickDeviceSettings();
		scenesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		groupPage.editIntencity(testData.get("IntencityValue"), testData.get("ColorValue"));
		String value= scenesPage.getIntencityVlue();
		ExtentTestManager.compareEquals(driver,value, testData.get("IntencityValue"), softAssert, "validate Intencity Value");
		ExtentTestManager.compareContains(driver, scenesPage.getColorVlue(), testData.get("ColorValue"), softAssert, "validate Color Value");
		softAssert.assertAll();
	}
	/*--Group level operations--*/
	@Test(dataProvider="TestData")
	public void SC0005(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1,5);
		devicesPage.clickDeviceSettings();
		scenesPage.clickSunSettings();
		devicesPage.switchOffDevice();
		devicesPage.switchOnDevice();
		devicesPage.switchOffDevice();
		ExtentTestManager.compareEquals(driver, scenesPage.validateDecreaseIntencity(), true, softAssert, "validate Decrease Intencity");
		ExtentTestManager.compareEquals(driver, scenesPage.validateIncreaseIntencity(), true, softAssert, "validate Increase Intencity");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDecreaseCCT(), true, softAssert, "validate Decrease CCT");
		ExtentTestManager.compareEquals(driver, devicesPage.validateIncreaseCCT(), true, softAssert, "validate Increase CCT");
		softAssert.assertAll();
	}

	/*--Edit Scene--*/
	@Test(dataProvider="TestData")
	public void SC0006(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random");
		String scename2=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("scene_random2", scename2);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		scenesPage.clickOnSettings(scename1);
		boolean flag=scenesPage.editScene(scename2);
		scenesPage.clickOnBackArrow();
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate edit scene success message");
		homepage.searchDevice(scename2);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(scename2,20), true, softAssert,
				"validate Scene name is created");
		
		softAssert.assertAll();
	}
	
	/*--create scene animation--*/
	@Test(dataProvider="TestData")
	public void SC0007(Map<String, String> testData)
	{
		String sceneAnimation=FunctionLibrary.getRandomString(10,"STRING");
		FunctionLibrary.fileWriter("sceneanimation_random", sceneAnimation);
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		scenesPage.createSceneAnimation(sceneAnimation);
		homepage.searchDevice(sceneAnimation);
		FunctionLibrary.syncTillTimePeriod(50);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(sceneAnimation,20), true, softAssert,
				"validate Scene name is created");
		softAssert.assertAll();
	}

	/*--Edit Scene Animation--*/
	@Test(dataProvider="TestData")
	public void SC0008(Map<String, String> testData)
	{
		String sceneAnimation2=FunctionLibrary.getRandomString(10,"STRING");
		String sceneAnimation3=FunctionLibrary.getRandomString(10,"STRING");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		scenesPage.createSceneAnimation(sceneAnimation2);
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
	    scenesPage.editSceneAnimation(sceneAnimation3);
		scenesPage.clickOnBackArrowAnimationBtn();
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation3);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(sceneAnimation3,20), true, softAssert,
				"validate Scene name is created");
		softAssert.assertAll();
	}

	/*Scene Animation in Repeat mode*/
	@Test(dataProvider="TestData")
	public void SC0009(Map<String, String> testData) throws MalformedURLException
	{
		String sceneAnimation2=FunctionLibrary.fileReader("sceneanimation_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
		boolean flag=scenesPage.validateRepeatModeEnabled(sceneAnimation2);
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Repeate mode activated");
		softAssert.assertAll();
	}
	/*--Scene animation play, pause, previous, next and stop--*/
	@Test(dataProvider="TestData")
	public void SC0010(Map<String, String> testData) throws MalformedURLException
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		String sceneAnimation2=FunctionLibrary.fileReader("sceneanimation_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
		scenesPage.addSceneToAnimation(scename1,"01","05","02","12","02");
		groupPage.clickOnGroupsIcon();
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2);
		boolean flag=scenesPage.validatePlayButonCount();
		scenesPage.clickAllPlayButtons();
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Scene animation play buttons");
		
		scenesPage.clickOnSettings(sceneAnimation2);
		boolean flag1=scenesPage.validateTimeValues(sceneAnimation2,scename1,"01","05","02","12","02");
		ExtentTestManager.compareEquals(driver,flag1, true, softAssert,
				"Validate the updated time fields");
		
		softAssert.assertAll();
	}
	
	/*--update Scene animation play, pause, previous, next and stop--*/
	@Test(dataProvider="TestData")
	public void SC0011(Map<String, String> testData) throws MalformedURLException
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		String sceneAnimation2=FunctionLibrary.fileReader("sceneanimation_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
		scenesPage.updateSceneAnimation(sceneAnimation2,scename1,"02","06","03","13","03");
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
		boolean flag1=scenesPage.validateTimeValues(sceneAnimation2,scename1,"02","06","03","13","03");
		ExtentTestManager.compareEquals(driver,flag1, true, softAssert,
				"Validate the updated time fields");
		softAssert.assertAll();
	}
	
	/*-- Image change --*/
	@Test(dataProvider="TestData")
	public void SC0012(Map<String, String> testData) throws MalformedURLException
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		scenesPage.clickOnSettings(scename1);
		scenesPage.clickOnChangeImageBtn();
		scenesPage.validateAllImageIconsPresent();
		boolean flag=spacesPage.changeImage();
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate change image");
		softAssert.assertAll();
	}
	
	/*-- Scene Invoke, Search --*/
	@Test(dataProvider="TestData",enabled=false)
	public void SC0013(Map<String, String> testData) throws MalformedURLException
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		ExtentTestManager.compareEquals(driver,scenesPage.getPowerButon(scename1).size()>0, true, softAssert,
				"validate Power button is displayed");
		scenesPage.clickOnPowerButton(scename1);
		softAssert.assertAll();
	}

	/*--create scene schedule--*/
	@Test(dataProvider="TestData")
	public void SC0014(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		scenesPage.clickSceneSchedule();
		scenesPage.createSceneSchedule(testData.get("SceneSchedule"));
		ExtentTestManager.compareEquals(driver,scenesPage.validateSceneScheduleDisplayed(testData.get("SceneSchedule")), true, softAssert,
				"validate Scene schedule name is created");
		softAssert.assertAll();
	}
	
	/*--Scene Schedule-Date Configuration--*/
	@Test(dataProvider="TestData")
	public void SC0015(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		scenesPage.clickSceneSchedule();
		scenesPage.clickOnSceneSchedule(testData.get("SceneSchedule"));
		boolean flag=scenesPage.updateDateAndTime(testData.get("SceneSchedule"),scename1, testData.get("Time"));
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Scene Schedule-Date Configuration");
		softAssert.assertAll();
	}
	/*--Scene scedule -Repeat Configuration--*/
	@Test(dataProvider="TestData")
	public void SC0016(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		scenesPage.clickSceneSchedule();
		scenesPage.clickOnSceneSchedule(testData.get("SceneSchedule"));
		boolean flag=scenesPage.updateRepeatConfig(testData.get("SceneSchedule"),"Mo", testData.get("Time"));
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Scene Schedule-Date Configuration");
	
		softAssert.assertAll();
	}
	/*-- Edit Scene scedule --*/
	@Test(dataProvider="TestData")
	public void SC0017(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		scenesPage.clickSceneSchedule();
		scenesPage.clickOnSceneSchedule(testData.get("SceneSchedule").split(",")[0]);
		scenesPage.editSceneSchedule(testData.get("SceneSchedule").split(",")[1]);
		FunctionLibrary.clickElement(driver, scenesPage.scenScheduleBackBtn);
		FunctionLibrary.syncTillTimePeriod(20);
		boolean flag=FunctionLibrary.isElementPresentByElements
				(scenesPage.getSceneScheduleBtn(testData.get("SceneSchedule").split(",")[1]));
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate edit scene schedule success message");
		softAssert.assertAll();
	}
	/*-- Delete Scene scedule --*/
	@Test(dataProvider="TestData")
	public void SC0035(Map<String, String> testData)
	{
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		homepage.clickOnMoreBtn();
		scenesPage.clickSceneSchedule();
		scenesPage.clickOnSceneSchedule(testData.get("SceneSchedule"));
		scenesPage.deleteSceneSchedule(testData.get("SceneSchedule"));
		scenesPage.clickOnBackArrow();
		FunctionLibrary.swipeScreen("DOWN", driver);
		FunctionLibrary.syncTillTimePeriod(20);
		scenesPage.clickSceneSchedule();
		boolean flag=scenesPage.validateSceneScheduleDisplayed(testData.get("SceneSchedule"));
		ExtentTestManager.compareEquals(driver,flag, false, softAssert,
				"validate delete scene schedule success message");
		softAssert.assertAll();
	}
	/*Remove group from scene */
	@Test(dataProvider="TestData")
	public void SC0036(Map<String, String> testData)
	{
		String groupName=FunctionLibrary.fileReader("group_random2");
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		scenesPage.clickOnSettings(scename1);
		scenesPage.removeGroupFromScene(groupName);
		scenesPage.clickOnBackArrowBtn();
		homepage.searchDevice(scename1);
		String value=scenesPage.validateAddDeviceToScene(scename1);
		ExtentTestManager.compareContains(driver, value, testData.get("Expected"), softAssert, "validate Add device");
		softAssert.assertAll();
	}
	/*--Remove device scene--*/
	@Test(dataProvider="TestData")
	public void SC0037(Map<String, String> testData)
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1,5);
		scenesPage.clickOnSettings(scename1);
		scenesPage.searchDeviceInSenes(testData.get("deviceName"));
		scenesPage.removeDeviceFromScene();
		scenesPage.clickOnBackArrowBtn();
		homepage.searchDevice(scename1);
		String value=scenesPage.validateAddDeviceToScene(scename1);
		ExtentTestManager.compareContains(driver, value, testData.get("Expected"), softAssert, "validate Add device");
		softAssert.assertAll();
	}
	/*--Delete Scene--*/
	@Test(dataProvider="TestData")
	public void SC0038(Map<String, String> testData) throws MalformedURLException
	{
		String scename1=FunctionLibrary.fileReader("scene_random2");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1);
		scenesPage.clickOnSettings(scename1);
		boolean flag=scenesPage.deleteScene(scename1);
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Scene name is created");
		driver.quit();
		startWebDriverClient("ios");
		setupPages(driver);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(scename1,5);
		FunctionLibrary.swipeScreen("DOWN", driver);
		FunctionLibrary.syncTillTimePeriod(10);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(scename1, 2), false, softAssert,
				"validate Scene name is Deleted");
		softAssert.assertAll();
	}

	/*--Delete Scene Animation--*/
	@Test(dataProvider="TestData")
	public void SC0039(Map<String, String> testData) throws MalformedURLException
	{
		String sceneAnimation2=FunctionLibrary.fileReader("sceneanimation_random");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName,TestConstants.password);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2);
		scenesPage.clickOnSettings(sceneAnimation2);
		boolean flag=scenesPage.deleteSceneAnimation(sceneAnimation2);
		ExtentTestManager.compareEquals(driver,flag, true, softAssert,
				"validate Scene name is created");
		driver.quit();
		startWebDriverClient("ios");
		setupPages(driver);
		scenesPage.clickOnScenesIcon();
		homepage.searchDevice(sceneAnimation2,5);
		FunctionLibrary.swipeScreen("DOWN", driver);
		FunctionLibrary.syncTillTimePeriod(10);
		ExtentTestManager.compareEquals(driver,scenesPage.isDeviceListed(sceneAnimation2,5), false, softAssert,
				"validate Scene name is Deleted");
		softAssert.assertAll();

	}
}
