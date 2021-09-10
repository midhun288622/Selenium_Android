package com.scs.test;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.scs.commons.FunctionLibrary;
import com.scs.commons.TestConstants;
import com.scs.execution.BaseAppTest;
import com.scs.report.ExtentTestManager;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DeviceTest extends BaseAppTest {

	/* Device commissioning setting options availability */
	@Test(dataProvider = "TestData")
	public void DV0001(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		devicesPage.addDeviceBtn();
		devicesPage.clickOnCommissioningSettings();

		ExtentTestManager.compareEquals(driver, devicesPage.validateOldSecuInputOptionDisplayed(), true, softAssert,
				"validate Old SecuInput Option Displayed");
		ExtentTestManager.compareEquals(driver, devicesPage.validateNewSecuInputOptionDisplayed(), true, softAssert,
				"validate New Secu Input Option Displayed");
		ExtentTestManager.compareEquals(driver, devicesPage.validateSetBtnDisplayed(), true, softAssert,
				"Validate set button");
		ExtentTestManager.compareEquals(driver, devicesPage.validateOutputChannSettDisplayed(), true, softAssert,
				"Validate Output channel settings");
		ExtentTestManager.compareEquals(driver, devicesPage.validateInputChannSettDisplayed(), true, softAssert,
				"Validate Input sensor settings");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDeviceConfgSettDisplayed(), true, softAssert,
				"Validate Device configuration settings");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDevicePowerSettDisplayed(), true, softAssert,
				"Validate Device power settings");
		ExtentTestManager.compareEquals(driver, devicesPage.validateEditConfgSettDisplayed(), true, softAssert,
				"Validate add device success nessage");

		devicesPage.switchOnEditConfig();

		softAssert.assertAll();
	}

	/*--Device commissoning configured values are reflects in a device--*/

	@Test(dataProvider = "TestData")
	public void DV0002(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		devicesPage.addDeviceBtn();
		devicesPage.clickOnCommissioningSettings();
		devicesPage.enterNewSecurityCode(testData.get("SecurityCode"));
		devicesPage.clickOnSetButton();
		FunctionLibrary.syncTillTimePeriod(20);
		devicesPage.clickOnBackArrowBtn();
		devicesPage.clickOnCommissioningSettings();
		String code = devicesPage.getSecurityCodeFromCommisonSettings();
		ExtentTestManager.compareEquals(driver, code, testData.get("SecurityCode"), softAssert,
				"Validate add device Security code");

		softAssert.assertAll();
	}

	/*
	 * Add device into application
	 * 
	 * @Test(dataProvider="TestData") public void DV0003(Map<String, String>
	 * testData) { ExtentTestManager.setDiscription(testData.get("Test Scenario"));
	 * login.loginToApp(TestConstants.userName,TestConstants.password); List<String>
	 * devices=Arrays.asList(testData.get("deviceName").split(",")); boolean
	 * flag=devicesPage.addMultipleDevices(devices);
	 * ExtentTestManager.compareEquals(driver, flag, true, softAssert,
	 * "Validate add multiple device success nessage");
	 * devicesPage.clickOnCloseIcon(); for(String devName:devices) {
	 * homepage.searchDevice(devName,5); ExtentTestManager.compareEquals(driver,
	 * homepage.isDeviceListed(devName,5), true, softAssert,
	 * "validate Device name is listed"); } softAssert.assertAll(); }
	 */
	/*--Edt value on sun settings--*/
	@Test(dataProvider = "TestData")
	public void DV0004(Map<String, String> testData) {

		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"), 5);
		devicesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		groupPage.editIntencity(testData.get("IntencityValue"), testData.get("ColorValue"));
		String value = devicesPage.getIntencityVlue().replace(".0", "");
		String color = devicesPage.getColorVlue();
		ExtentTestManager.compareEquals(driver, value, testData.get("IntencityValue"), softAssert,
				"validate Intencity Value");
		ExtentTestManager.compareContains(driver, color, testData.get("ColorValue"), softAssert,
				"validate Color Value");
		softAssert.assertAll();
	}

	/* Validate device Intensity and CCT settings */
	@Test(dataProvider = "TestData")
	public void DV0005(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickSunSettings();
		devicesPage.switchOffDevice();
		devicesPage.switchOnDevice();
		devicesPage.switchOffDevice();
		boolean decreaseIntecity = devicesPage.validateDecreaseIntencity();
		boolean increaseIntecity = devicesPage.validateIncreaseIntencity();
		ExtentTestManager.compareEquals(driver, decreaseIntecity, true, softAssert, "validate Decrease Intencity");
		ExtentTestManager.compareEquals(driver, increaseIntecity, true, softAssert, "validate Increase Intencity");
		ExtentTestManager.compareEquals(driver, devicesPage.validateDecreaseCCT(), true, softAssert,
				"validate Decrease CCT");
		ExtentTestManager.compareEquals(driver, devicesPage.validateIncreaseCCT(), true, softAssert,
				"validate Increase CCT");
		softAssert.assertAll();
	}

	/* Validate device Intensity and CCT settings */
	@Test(dataProvider = "TestData", enabled = false)
	public void DV0006(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickSunSettings();
		boolean flag = devicesPage.validateCoolvalues("sliderMin", "sliderMax");
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "validate warm cool values");
		softAssert.assertAll();
	}

	@Test(dataProvider = "TestData")
	public void DV0007(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickSunSettings();
		groupPage.clickOnEditIntencity();
		spacesPage.editRGB(testData.get("RedRGB"), testData.get("GreenRGB"), testData.get("BlueRGB"));
		try {
			devicesPage.clickSunSettings();
		} catch (Exception e) {

		}
		groupPage.clickOnEditIntencity();
		boolean flag = spacesPage.validateRGB(testData.get("RedRGB"), testData.get("GreenRGB"),
				testData.get("BlueRGB"));
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate RGB color Value");
		softAssert.assertAll();
	}

	/* Device settings-Individual schedule Settings */
	@Test(dataProvider = "TestData")
	public void DV0008(Map<String, String> testData) {
		String indScheName = FunctionLibrary.getRandomString(5, "STRING");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		devicesPage.clickOnIdScheduleSettingsBtn();
		devicesPage.createIndiScheduleSettings(indScheName);
		devicesPage.clickOnIdScheduleSettingsBtn();
		boolean flag = devicesPage.validateIndSheduleCreation(indScheName);
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate Idividual schedule settings");
		softAssert.assertAll();
	}

	/* Device Settings-Light Configuration Settings */
	@Test(dataProvider = "TestData")
	public void DV0009(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		devicesPage.clickOnlightConfigSettingsBtn();
		devicesPage.enterHighEndTrim("55");
		boolean flag = devicesPage.validateUpdatedScheduleMessage();
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate Idividual schedule settings");
		devicesPage.enterHighEndTrim("100");
		devicesPage.validateUpdatedScheduleMessage();
		softAssert.assertAll();
	}

	/* Device configuration settings */
	@Test(dataProvider = "TestData")
	public void DV0010(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnDeviceConfigurationSettings();
		devicesPage.increaseTimeIntervel("6.0");
		devicesPage.clickOnSetButton();

		devicesPage.validateOperationSuccessMessage();

		devicesPage.clickOnBackArrowBtn();
		devicesPage.clickOnDeviceConfigurationSettings();
		boolean flag = devicesPage.validateIncreaseTimeIntervel("6.0");
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "validate device configuration");
		softAssert.assertAll();

	}

	/* Device backup */
	@Test(dataProvider = "TestData")
	public void DV0011(Map<String, String> testData) {
		String backup = FunctionLibrary.getRandomString(5, "STRING");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnCreateBackupButton();
		devicesPage.createBackup(backup);
		devicesPage.clickOnBackArrowBtn();
		homepage.clickOnMoreBtn();
		devicesPage.clickOnBackupList();
		FunctionLibrary.syncTillTimePeriod(50);
		boolean flag = devicesPage.validateBackupCreated(testData.get("deviceName").replaceAll("-1", ""), backup);
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "validate Backup is created");
		softAssert.assertAll();

	}

	/* Beacon configuration */
	@Test(dataProvider = "TestData")
	public void DV0012(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnAdditionalSettings();
		devicesPage.clickOnBeaconSettings();
		boolean flag = devicesPage.createBeaconConfig(testData.get("BeaconName"));
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "validate Beacon is created");
		devicesPage.clickOnBackArrowBtn();
		boolean flag1 = devicesPage.validateBeconEnabled(testData.get("BeaconName"));
		ExtentTestManager.compareEquals(driver, flag1, true, softAssert, "validate Beacon is enabled");
		softAssert.assertAll();

	}

	/* Device Settings-Motion Sensor Settings */
	@Test(dataProvider = "TestData")
	public void DV0013(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		devicesPage.clickOnMotionSensorSettings();
		devicesPage.whenSomeoneArrivesSettings("2.0");
		devicesPage.whenEverybodyLeavesSettings("2.0", "30");
		devicesPage.thenSettings("2.0", "30");
		devicesPage.clickOnBackArrowBtn();
		devicesPage.clickOnMotionSensorSettings();
		ExtentTestManager.compareEquals(driver, devicesPage.validateWhenSomeoneArrivesSettings("2.0"), true, softAssert,
				"validate validateWhenSomeoneArrivesSettings");
		ExtentTestManager.compareEquals(driver, devicesPage.validateWhenEverybodyLeavesSettings("2.0", "30"), true,
				softAssert, "validate validateWhenEverybodyLeavesSettings");
		FunctionLibrary.swipeScreen("UP", driver);
		ExtentTestManager.compareEquals(driver, devicesPage.validateThenSettings("2.0", "30"), true, softAssert,
				"validate validateThenSettings");
		softAssert.assertAll();

	}

	/* Validate sceurity code */
	@Test(dataProvider = "TestData")
	public void DV0014(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		devicesPage.clickDeviceIcon();
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnDeviceConfigurationSettings();
		String temp = devicesPage.getSecurityCode();
		ExtentTestManager.compareEquals(driver, temp, testData.get("SecurityCode"), softAssert,
				"validate Security Code");
		softAssert.assertAll();
	}

	/* Output Channel settings */
	@Test(dataProvider = "TestData")
	public void DV0015(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnAdditionalSettings();
		devicesPage.clickOutPutChannelSettings();
		boolean flag1 = devicesPage.clickOnRelayRadio();
		ExtentTestManager.compareEquals(driver, flag1, true, softAssert, "validate validateRelayRadioEnabled");
		boolean flag2 = devicesPage.clickOntwoSinglesradio();
		ExtentTestManager.compareEquals(driver, flag2, true, softAssert, "validate validatetwoSinglesradioEnabled");
		softAssert.assertAll();
	}

	/* input sensor settings-motion settings */
	@Test(dataProvider = "TestData")
	public void DV0016(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnAdditionalSettings();
		devicesPage.clickOnInputSensorSettings();
		devicesPage.addChannel1MotionSensor("2.0");
		devicesPage.clickOnBackArrowBtn();
		devicesPage.clickOnInputSensorSettings();
		ExtentTestManager.compareEquals(driver, devicesPage.validateChannel1Settings("2.0"), true, softAssert,
				"validateChannel1Settings");

	}

	/* input sensor settings-daylight */
	@Test(dataProvider = "TestData")
	public void DV0017(Map<String, String> testData) {
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		FunctionLibrary.swipeScreen("UP", driver);
		devicesPage.clickOnAdditionalSettings();
		devicesPage.clickOnInputSensorSettings();
		devicesPage.addChannel2DayLightSensor("9.0");
		devicesPage.clickOnBackArrowBtn();
		devicesPage.clickOnInputSensorSettings();
		ExtentTestManager.compareEquals(driver, devicesPage.validateChannel2Settings("9.0"), true, softAssert,
				"validateChannel1Settings");

	}

	/* Device backup--Delete */
	@Test(dataProvider = "TestData")
	public void DV0018(Map<String, String> testData) {
		String backup = FunctionLibrary.getRandomString(5, "STRING");
		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.clickOnMoreBtn();
		devicesPage.clickOnBackupList();
		FunctionLibrary.syncTillTimePeriod(50);
		devicesPage.deleteBackupCreated(testData.get("deviceName").replaceAll("-1", ""));
		softAssert.assertAll();

	}

	/* Validate Remove device */
	@Test(dataProvider = "TestData")
	public void DV0020(Map<String, String> testData) throws MalformedURLException {

		ExtentTestManager.setDiscription(testData.get("Test Scenario"));
		login.loginToApp(TestConstants.userName, TestConstants.password);
		homepage.searchDevice(testData.get("deviceName"));
		devicesPage.clickDeviceSettings();
		boolean flag = devicesPage.removeDevice();
		ExtentTestManager.compareEquals(driver, flag, true, softAssert, "Validate delete device");
		homepage.searchDevice(testData.get("deviceName"), 5);
		FunctionLibrary.swipeScreen("DOWN", driver);
		homepage.waitToLoadProgressView();
		ExtentTestManager.compareEquals(driver, homepage.isDeviceListed(testData.get("deviceName"), 5), false,
				softAssert, "validate Device name is  not listed");
		softAssert.assertAll();
	}

}
