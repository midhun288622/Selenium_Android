package com.scs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.scs.commons.BasePage;
import com.scs.commons.FunctionLibrary;
import com.scs.report.ExtentTestManager;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class DevicesPage extends BasePage{

	//*[@resource-id='com.wisilica.Home:id/tv_min']
	//*[contains(@resource-id,'coolSeekbar')]


	@FindBy(xpath = "//*[contains(@resource-id,'txt_ok')]")
	private List<WebElement> closeWindow;

	@FindBy(xpath = "(//*[contains(@resource-id,'icon')])[1]")
	private List<WebElement> addDevice;
	@FindBy(xpath = "//*[@text='Tap here']")
	private List<WebElement> tapHere;
	@FindBy(xpath = "//*[@text='to search device']")
	private WebElement searchHere;
	@FindBy(xpath = "//*[@text='SKIP']")
	private List <WebElement> skipLink;
	@FindBy(xpath = "//XCUIElementTypeTextField[contains(@value,'Search')]")
	private List <WebElement> searchTextField;
	@FindBy(xpath = "WRA5WA65B27")
	private WebElement deviviceLst;
	@FindBy(xpath = "RadioBtn UnSelected")
	private List<WebElement> radioBtnOnAddPage;
	@FindBy(xpath = "Plus Blue")
	private WebElement addBtnOnAddpage;
	@FindBy(xpath = "Progress")
	private WebElement progressBar;
	@FindBy(xpath = "//*[@text='Device paired successfully.']")
	private List<WebElement> pairSuccessMsg;
	@FindBy(xpath = "Ok")
	private WebElement okBtnOnPairSuccessMsg;
	@FindBy(xpath = "//XCUIElementTypeTextField[`value ='WRA5WA65B27-1']")
	private WebElement deviceNameOnDeviceNamePopup;
	@FindBy(xpath = "//XCUIElementTypeStaticText[`label ='OK'][1]")
	private WebElement okBtnOnDeviceNameOkBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Group 1']")
	private WebElement groupNameOnadd;
	@FindBy(xpath = "Ok")
	private WebElement addGroupOkBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Added to Group']")
	private WebElement devcieAddedMsg;



	@FindBy(xpath = "//*[contains(@resource-id,'power_on')]")
	private  List <WebElement> sunBtn;

	@FindBy(xpath = "//*[@text='OFF']")
	private  List <WebElement> offBtn;
	@FindBy(xpath = "//*[@text='ON']")
	private  List <WebElement> onBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_minus']")
	private WebElement minusBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_plus']")
	private WebElement plusBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'brightnessseekBar')]")
	private List <WebElement>  geIntencityCurrentNumber;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_minus_cool']")
	private WebElement minusBtnCCT;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_plus_cool']")
	private WebElement plusBtnCCT;
	@FindBy(xpath = "//*[contains(@resource-id,'coolSeekbar')]")
	private WebElement geCCTCurrentNumber;
	@FindBy(xpath = "//*[contains(@resource-id,'delete')]")
	private   List <WebElement>  deviceDeleteBtn;
	@FindBy(xpath = "//*[@text='Devices']")
	private WebElement devicesIcon;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/settings']")
	private List <WebElement> deviceSettings;
	@FindBy(xpath = "//*[contains(@resource-id,'iv_comm_settings')]")
	private  List <WebElement>  commissioningSettings;
	@FindBy(xpath = "//*[contains(@resource-id,'iv_comm_settings')]/..//android.widget.TextView")
	private WebElement settingsCommissioning;
	@FindBy(xpath = "//*[contains(@resource-id,'ti_old')]")
	private  List <WebElement>  oldSecurityLabel;
	@FindBy(xpath = "(//*[contains(@resource-id,'ti_old')]/..//android.widget.EditText)[1]")
	private WebElement oldSecuInput;
	@FindBy(xpath = "(//*[contains(@resource-id,'ti_old')]/..//android.widget.EditText)[2]")
	private  List <WebElement> newSecuInput;
	@FindBy(xpath = "//*[@text='Set']")
	private WebElement settBtn;
	@FindBy(xpath = "//*[@text='Output Channel Settings']")
	private WebElement outputChannSett;	
	@FindBy(xpath = "//*[@text='Input Sensor Settings']")
	private WebElement inputChannSett;
	@FindBy(xpath = "//*[@text='Device Configuration Settings']")
	private WebElement deviceConfgSett;
	@FindBy(xpath = "//*[@text='Device Power Settings']")
	private WebElement devicePowerSett;
	@FindBy(xpath ="//*[@text='Edit these settings during commissioning?']")
	private WebElement editConfgSett;
	@FindBy(xpath = "//*[@text='Settings saved successfully']")
	private List <WebElement>  successSettingsMsg;
	@FindBy(xpath = "//*[@text='OK']")
	private WebElement OKBtn;
	@FindBy(xpath = "//*[@text='Cancel']")
	private List <WebElement> CancelBtn;
	@FindBy(xpath = "//*[@text='Additional Settings']")
	private List <WebElement> addSettings;
	@FindBy(xpath = "//*[@text='Device Configuration Settings']")
	private List <WebElement> deviceCinfigSettings;
	@FindBy(xpath = "//*[contains(@resource-id,'appCompatTextView2')]")
	private List <WebElement> securityCode;
	@FindBy(xpath = "//*[contains(@resource-id,'sc_showPopup')]")
	private List <WebElement> editConfigBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/txtSubmit']")
	private List <WebElement> submitBtn;




	public void switchOnEditConfig()
	{
		FunctionLibrary.isElementPresentByElements(editConfigBtn);
		String temp=editConfigBtn.get(0).getAttribute("text").trim();
		if(!temp.equals("ON"))
		{
			editConfigBtn.get(0).click();
		}

	}

	public void clickOnDeviceConfigurationSettings() {

		FunctionLibrary.clickElement(driver, addSettings);
		FunctionLibrary.clickElement(driver, deviceCinfigSettings);
		ExtentTestManager.logTestResult("INFO","Navigate to device Configuration settings screen");
	}
	public String getSecurityCode()
	{
		FunctionLibrary.isElementPresentByElements(securityCode);
		return securityCode.get(0).getAttribute("text");
	}
	public boolean validateSuccessMessage()
	{
		return FunctionLibrary.isElementPresentByElements(successSettingsMsg);

	}
	public void clickOnCommissioningSettings() {

		FunctionLibrary.isElementPresentByElements(commissioningSettings);
		settingsCommissioning.click();
		FunctionLibrary.isElementPresentByElements(oldSecurityLabel);
	}

	public void enterNewSecurityCode(String newSecurityCode)
	{
		FunctionLibrary.sendKeys(driver, oldSecuInput, "0000");
		FunctionLibrary.sendKeys(driver, newSecuInput, newSecurityCode);
	}

	public void clickOnSetButton()
	{
		settBtn.click();
	}
	public String  getSecurityCodeFromCommisonSettings()
	{
		FunctionLibrary.isElementPresentByElements(newSecuInput);
		return newSecuInput.get(0).getAttribute("text");
	}

	public boolean validateOldSecuInputOptionDisplayed()
	{
		return oldSecuInput.isDisplayed();
	}
	public boolean validateNewSecuInputOptionDisplayed()
	{
		return newSecuInput.get(0).isDisplayed();
	}
	public boolean validateSetBtnDisplayed()
	{
		return settBtn.isDisplayed();
	}
	public boolean validateOutputChannSettDisplayed()
	{
		return outputChannSett.isDisplayed();
	}
	public boolean validateInputChannSettDisplayed()
	{
		return inputChannSett.isDisplayed();
	}
	public boolean validateDeviceConfgSettDisplayed()
	{
		return deviceConfgSett.isDisplayed();
	}
	public boolean validateDevicePowerSettDisplayed()
	{
		return devicePowerSett.isDisplayed();
	}
	public boolean validateEditConfgSettDisplayed()
	{
		return editConfgSett.isDisplayed();
	}

	
	
	
	
	@FindBy(xpath = "//*[contains(@resource-id,'edit_text')]")
	private List <WebElement> eitIntencity;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_color_temp')]")
	private List <WebElement> cctValue;
	@FindBy(xpath = "//*[contains(@resource-id,'iv_close')]")
	private List <WebElement> closePopup;
	
	
	public boolean validateDecreaseCCT()
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String defaultValue=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		
		FunctionLibrary.syncTillTimePeriod(20);
		minusBtnCCT.click();
		FunctionLibrary.syncTillTimePeriod(50);
		
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String actual=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		FunctionLibrary.syncTillTimePeriod(20);
		
		
		
		int expectedValue=Integer.parseInt(defaultValue)-Integer.parseInt(actual);

		if(expectedValue==47||expectedValue==0)
		{
			return true;
		}
		return false;
	}
	public boolean validateIncreaseCCT()
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String defaultValue=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		
		FunctionLibrary.syncTillTimePeriod(20);
		plusBtnCCT.click();
		FunctionLibrary.syncTillTimePeriod(50);
		
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String actual=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		FunctionLibrary.syncTillTimePeriod(20);
		
		
		
		int expectedValue=Integer.parseInt(actual)-Integer.parseInt(defaultValue);

		if(expectedValue==47||expectedValue==0)
		{
			return true;
		}
		return false;
	}
	public boolean validateDecreaseIntencity()
	{
		ExtentTestManager.logTestResult("INFO","Validate Derease intencity");
		FunctionLibrary.syncTillTimePeriod(50);
		FunctionLibrary.isElementPresentByElements(geIntencityCurrentNumber, 0);
		String temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		double defaultValue=Double.parseDouble(temp);
		minusBtn.click();
		FunctionLibrary.syncTillTimePeriod(150);
		temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		double actual=Float.parseFloat(temp);
		double expectedValue=defaultValue-actual;

		if(expectedValue==1)
		{
			return true;
		}
		return false;
	}

	public boolean validateIncreaseIntencity()
	{
		ExtentTestManager.logTestResult("INFO","Validate Increase intencity");
		FunctionLibrary.syncTillTimePeriod(50);
		double defaultValue=Double.parseDouble(geIntencityCurrentNumber.get(0).getText().replace("%", ""));
		plusBtn.click();
		FunctionLibrary.syncTillTimePeriod(150);
		double actual=Float.parseFloat(geIntencityCurrentNumber.get(0).getText().replace("%", ""));
		double expectedValue=actual-defaultValue;

		if(expectedValue==1)
		{
			return true;
		}
		return false;
	}

	public void changeIntensity(double number)
	{
		try {

			int count=0;
			double currNum=Double.parseDouble(geIntencityCurrentNumber.get(0).getText());
			while(!(currNum==number)&&currNum>number)
			{
				minusBtn.click();
				currNum=Float.parseFloat(geIntencityCurrentNumber.get(0).getText());
				count++;

				if(count==200)
				{
					break;
				}
			}	

			System.out.println();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public WebElement getUserElement(String userName)
	{
		return driver.findElement(By.xpath("//h5[text()='"+userName+"']"));
	}

	String flag = "NA";

	public DevicesPage(WebDriver driver) {


		super(driver);
	}


	public void clickOkBtn()
	{
		FunctionLibrary.clickElement(driver, OKBtn);
	}
	public boolean removeDevice()
	{
		FunctionLibrary.isElementPresentByElements(deviceDeleteBtn);
		deviceDeleteBtn.get(0).click();
		FunctionLibrary.syncTillTimePeriod(20);
		if(FunctionLibrary.isElementPresentByElements(deviceAddedOkBtn))
		{
			deviceAddedOkBtn.get(0).click();
			FunctionLibrary.syncTillTimePeriod(50);
			return true;
		}

		return false;
	}


	public void selctDeviceRadioBtn(String deviceName)
	{

		String xpath="//*[@text='"+deviceName+"']/..//*[contains(@resource-id,'iv_selectDevice')]";
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)),50);
		driver.findElements(By.xpath(xpath)).get(0).click();

	}

	public void selctDevicePlusBtn(String deviceName)
	{
		String xpath="//*[@text='"+deviceName+"']/..//*[contains(@resource-id,'pair_icon')]";
		FunctionLibrary.clickElement(driver, driver.findElements(By.xpath(xpath)));
	}


	public void addDeviceBtn()
	{
		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.clickElement(driver, addDevice);
	}

	public boolean addDevice(String deviceName)
	{
		ExtentTestManager.logTestResult("INFO","Navigate to add device page");
		addDeviceBtn();

		FunctionLibrary.clickElement(driver, tapHere);
		FunctionLibrary.syncTillTimePeriod(70);

		selctDeviceRadioBtn(deviceName);
		selctDevicePlusBtn(deviceName);

		FunctionLibrary.syncTillTimePeriod(30);
		try {
			FunctionLibrary.clickElement(driver,submitBtn);
		} catch (Exception e) {
		}
		editWarmCoolPopup();

		FunctionLibrary.clickElementWithTry(driver,skipLink);
		if(FunctionLibrary.isElementPresentByElements(pairSuccessMsg))
		{
			FunctionLibrary.clickElement(driver, OKBtn);

			FunctionLibrary.clickElementWithTry(driver, CancelBtn);
			FunctionLibrary.clickElementWithTry(driver, CancelBtn);

			FunctionLibrary.clickElementWithTry(driver, closeWindow);
			return true;
		}

		return false;


	}



	public boolean isDeviceListed(String deviceName,int count)
	{
		String xpath="//XCUIElementTypeStaticText[contains(@value,'"+deviceName+"')]";
		if(FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)),count))
		{
			List<WebElement> list=driver.findElements(By.xpath(xpath));
			return list.size()>0;
		}
		return false;
	}
	public void clickSunSettings()
	{
		FunctionLibrary.clickElement(driver, sunBtn);
		ExtentTestManager.logTestResult("INFO","Navigate to Sun settings");
	}

	public void clickDeviceIcon()
	{
		devicesIcon.click();

	}
	public void switchOnDevice()
	{
		FunctionLibrary.isElementPresentByElements(onBtn,10);
		onBtn.get(0).click();
	}
	public void switchOffDevice()
	{

		FunctionLibrary.isElementPresentByElements(offBtn,10);
		offBtn.get(0).click();

	}



	// SUITE 3 Changes




	@FindBy(xpath = "//*[@text='Warmcool value']")
	private List <WebElement> coolLabel;
	@FindBy(xpath = "//*[@text='Warmcool value']/..//*[@resource-id='com.wisilica.Home:id/min_warmcool']")
	private WebElement minCoolValue;
	@FindBy(xpath = "//*[@text='Warmcool value']/..//*[@resource-id='com.wisilica.Home:id/max_warmcool']")
	private WebElement maxCoolValue;
	@FindBy(xpath = "//*[@text='Warmcool value']/..//*[contains(@resource-id,'min_warmcoolSeekbar')]")
	private WebElement minSlider;
	@FindBy(xpath = "//*[@text='Warmcool value']/..//*[contains(@resource-id,'max_warmcool_seekbar')]")
	private WebElement maxSlider;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/tv_min_cool']")
	private WebElement geCCTminValue;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_plus_cool']/..//android.widget.TextView[2]")
	private WebElement geCCTmaxValue;
	public void editWarmCoolPopup()
	{
		FunctionLibrary.isElementPresentByElements(coolLabel);
		//		FunctionLibrary.moveSeekBar(minSlider, driver);
		FunctionLibrary.moveSeekBar(maxSlider, driver);
		String min=minCoolValue.getAttribute("text");
		String max=maxCoolValue.getAttribute("text");
		FunctionLibrary.fileWriter("sliderMin", min);
		FunctionLibrary.fileWriter("sliderMax", max);
		FunctionLibrary.clickElement(driver, OKBtn);
	}
	public boolean validateCoolvalues(String minStr,String maxStr)
	{
		String min=FunctionLibrary.fileReader(minStr).replace("K", "").trim();
		String max=FunctionLibrary.fileReader(maxStr).replace("K", "").trim();
		String minValue=geCCTminValue.getText().replace("K", "");
		String maxValue=geCCTmaxValue.getText().replace("K", "");

		if(min.equals(minValue))
		{
			if(max.equals(maxValue))
			{
				return true;
			}
		}
		return false;
	}
	//---------------------------------SUITE 4 ---------------

	@FindBy(xpath = "//*[contains(@resource-id,'nav_host_fragment')]//android.widget.ImageView")
	private List <WebElement> addSechedule;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private List <WebElement> enterScheduleName;
	@FindBy(xpath = "Submit")
	private WebElement submitScheduleBtn;
	@FindBy(xpath = "adjust")
	private WebElement adjustBtn;
	@FindBy(xpath = "//*[@text='Individual schedule setting']")
	private List <WebElement> indScheduleSettings;
	@FindBy(xpath ="//*[@text='Light Configuration Settings']")
	private List <WebElement> lightConfigSettings;

	//XCUIElementTypeStaticText[@name="Device details updated..."]

	@FindBy(xpath = "Schedule added successfully")
	private List <WebElement> scheSuccessMsg;
	@FindBy(xpath = "//*[@text='Operation success']")
	private List <WebElement> detailsUpdated;

	@FindBy(xpath = "//*[contains(@resource-id,'editext_max')]")
	private WebElement higEndTxt;
	@FindBy(xpath = "//*[contains(@resource-id,'buttonMaxlevel')]")
	private WebElement upgradeBtn;


	public void enterHighEndTrim(String value)
	{
		FunctionLibrary.sendKeys(driver, higEndTxt, value);
		upgradeBtn.click();
	}

	public void clickOnIdScheduleSettingsBtn()
	{
		FunctionLibrary.clickElement(driver, indScheduleSettings);
		;

	}
	public boolean validateIndSheduleCreation(String name)
	{
		FunctionLibrary.syncTillTimePeriod(50);
		return FunctionLibrary.isElementPresentByElements(getTextElement(name));

	}
	public void clickOnlightConfigSettingsBtn()
	{
		FunctionLibrary.clickElement(driver, lightConfigSettings);
	}
	public void createIndiScheduleSettings(String sheduleName)
	{
		FunctionLibrary.clickElement(driver, addSechedule);
		FunctionLibrary.sendKeys(driver, enterScheduleName,sheduleName);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.syncTillTimePeriod(50);
		FunctionLibrary.clickElement(driver, backArrowBtn);
	}
	public void clickDeviceSettings()
	{
		FunctionLibrary.clickElement(driver, deviceSettings);
		ExtentTestManager.logTestResult("INFO","Navigate to device settings page");
	}
	public void clickOnSubmitBtn()
	{
		FunctionLibrary.clickElement(driver, submitScheduleBtn);
	}

	public boolean validateSuccessScheduleMessage()
	{
		return FunctionLibrary.isElementPresentByElements(scheSuccessMsg);
	}
	public boolean validateUpdatedScheduleMessage()
	{
		if(FunctionLibrary.isElementPresentByElements(detailsUpdated))
		{
			return true;
		}
		return false;
	}

	@FindBy(xpath = "//*[contains(@resource-id,'edtMin')]")
	private List <WebElement> interVelTimeTxt;


	public void increaseTimeIntervel(String intervelTime)
	{
		FunctionLibrary.sendKeys(driver, interVelTimeTxt, intervelTime);

	}		
	public boolean validateIncreaseTimeIntervel(String intervelTime)
	{
		FunctionLibrary.isElementPresentByElements(interVelTimeTxt);
		String temp=interVelTimeTxt.get(0).getAttribute("text");
		if(temp.equals(intervelTime))
		{
			return true;
		}
		return false;
	}	

	/*SUITE 4*/

	@FindBy(xpath = "//*[@text='Create Backup']")
	private List <WebElement> createBackup;
	@FindBy(xpath = "//*[@text='Skip Syncing']")
	private List <WebElement> skipSyncing;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private List <WebElement> enterBackup;
	@FindBy(xpath = "//*[contains(@text,'Backup updated successfully')]")
	private List <WebElement> backupUpdated;
	@FindBy(xpath = "//*[@text='Backup List']")
	private List <WebElement> backupList;
	public List <WebElement> getBackupName(String deviceName)
	{
		return driver.findElements(By.xpath("//*[@text='"+deviceName+"']/..//android.widget.ImageView"));
	}
	public List <WebElement>  isBackupNameListed(String backupName)
	{
		return driver.findElements(By.xpath("//*[contains(@text,'"+backupName+"')]"));
	}

	public void clickOnCreateBackupButton()
	{
		FunctionLibrary.clickElement(driver, createBackup);
	}
	public boolean createBackup(String backupName)
	{
		if(FunctionLibrary.isElementPresentByElements(skipSyncing,5))
		{
			FunctionLibrary.clickElement(driver, skipSyncing);
		}
		FunctionLibrary.sendKeys(driver, enterBackup, backupName);
		FunctionLibrary.clickElement(driver, OKBtn);
		if(FunctionLibrary.isElementPresentByElements(backupSuccessMessage))
		{
			return true;
		}
		return false;
	}
	public void clickOnBackupList()
	{
		FunctionLibrary.clickElement(driver, backupList);
	}
	public boolean validateBackupCreated(String deviceName,String backupName)
	{
		FunctionLibrary.clickElement(driver, getBackupName(deviceName));
		if(FunctionLibrary.isElementPresentByElements(isBackupNameListed(backupName)))
		{
			return true;
		}
		return false;
	}
	@FindBy(xpath = "//*[contains(@resource-id,'imgDelete')]")
	private List <WebElement> deleteBackup;
	@FindBy(xpath = "//*[@text='Item is deleted']")
	public List <WebElement> itemDeleted;
	
	public void deleteBackupCreated(String deviceName)
	{
		FunctionLibrary.clickElement(driver, getBackupName(deviceName));
		for(WebElement element :deleteBackup)
		{
			FunctionLibrary.clickElement(driver, element);
			FunctionLibrary.clickElement(driver, OKbutton);
			FunctionLibrary.isElementPresentByElements(itemDeleted);
		}
	}
	@FindBy(xpath = "//*[@text='Beacon Settings']")
	private List <WebElement> beaconSettings;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/textView1']")
	private List <WebElement> setDeviceAsBeacon;

	@FindBy(xpath = "//*[contains(@resource-id,'more')]")
	private List <WebElement> clickOnBeaconMenu;
	@FindBy(xpath = "//*[@text='Set default values']")
	private List <WebElement> setDefulValue;
	@FindBy(xpath = "//*[@text='Configure']")
	private List <WebElement> confgureBtn;

	@FindBy(xpath = "//*[@text='Backup Success']")
	public List <WebElement> backupSuccessMessage;

	@FindBy(xpath = "//*[contains(@text,'You have successfully configured the device as beacon')]")
	private List <WebElement> successBeaconMsg;
	@FindBy(xpath = "//*[cotains(@text,'You have successfully enabled the beacon')]")
	private List <WebElement> enableBeaconSettings;
	@FindBy(xpath = "//*[@text='Enable']")
	private List <WebElement> enableBeacon;
	@FindBy(xpath = "//*[contains(@content-desc,'Navigate up‎‏‎‎‏‎')]")
	private List <WebElement> backArrowBtn;
	@FindBy(xpath = "//*[@text='Motion sensor settings']")
	private List <WebElement> motionSensorSettings;



	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/etTimeArrive']")
	private List <WebElement> wheNSomeoneArrivetime;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/appCompatButton3']")
	private List <WebElement> wheNSomeoneArrivetimeOk;



	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/etPrevStateLevel1Time']")
	private List <WebElement> everyBodyLeavesTime;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/etTimeLevel1']")
	private List <WebElement> everyBodyLeavesWaitfor;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/appCompatButton']")
	private List <WebElement> everyBodyLeavesTimeOk;


	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/etPrevStateLevel2Time']")
	private List <WebElement> thenStateTime;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/etLevel2Time']")
	private List <WebElement> thenWaitfor;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/swEveLeave']")
	private List <WebElement> thenBtn;
	@FindBy(xpath ="//*[@resource-id='com.wisilica.Home:id/appCompatButton2']")
	private List <WebElement> thenOk;

	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/sw_arrive']")
	private List <WebElement> whenSomeoneArriveBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/swLeave']")
	private List <WebElement> whenEverybodyLeavesBtn;

	public void enableThenBtn()
	{

		FunctionLibrary.clickElement(driver, thenBtn);

	}
	public void enableWhenSomeoneArriveBtn()
	{

		FunctionLibrary.clickElement(driver, whenSomeoneArriveBtn);

	}
	public void enableEverybodyLeavesBtn()
	{

		FunctionLibrary.clickElement(driver, whenEverybodyLeavesBtn);

	}

	public void thenSettings(String waitFor,String preIllState) {

		FunctionLibrary.swipeScreen("UP",driver);
		enableThenBtn();

		FunctionLibrary.sendKeys(driver, thenWaitfor, preIllState);

		FunctionLibrary.sendKeys(driver, thenStateTime, waitFor);

		FunctionLibrary.clickElement(driver, thenOk);
		validateOperationSuccessMessage();

	}
	public void whenEverybodyLeavesSettings(String waitFor,String preIllState) {
		//enableEverybodyLeavesBtn();

		FunctionLibrary.sendKeys(driver, everyBodyLeavesWaitfor, preIllState);

		FunctionLibrary.sendKeys(driver, everyBodyLeavesTime, waitFor);

		FunctionLibrary.clickElement(driver, everyBodyLeavesTimeOk);
		validateOperationSuccessMessage();
	}
	public void whenSomeoneArrivesSettings(String waitFor) {
		//enableWhenSomeoneArriveBtn();
		FunctionLibrary.sendKeys(driver, wheNSomeoneArrivetime,waitFor);
		FunctionLibrary.clickElement(driver, wheNSomeoneArrivetimeOk);
		validateOperationSuccessMessage();
	}

	public boolean validateWhenSomeoneArrivesSettings(String state)
	{
		FunctionLibrary.isElementPresentByElements(wheNSomeoneArrivetime);
		if(wheNSomeoneArrivetime.get(0).getAttribute("text").equals(state))
		{
			return true;
		}
		return false;
	}
	public boolean validateWhenEverybodyLeavesSettings(String waitFor,String preIllState)
	{

		FunctionLibrary.isElementPresentByElements(everyBodyLeavesWaitfor);
		ExtentTestManager.logTestResult("INFO", "validateWhenEverybodyLeavesSettings 1");
		if(everyBodyLeavesWaitfor.get(0).getAttribute("text").equals(preIllState))
		{
			ExtentTestManager.logTestResult("INFO", "validateWhenEverybodyLeavesSettings 2");
			FunctionLibrary.isElementPresentByElements(everyBodyLeavesTime);
			if(everyBodyLeavesTime.get(0).getAttribute("text").equals(waitFor))
			{
				ExtentTestManager.logTestResult("INFO", "validateWhenEverybodyLeavesSettings 3");
				return true;
			}
		}
		return false;
	}
	public boolean validateThenSettings(String waitFor,String preIllState)
	{
		FunctionLibrary.isElementPresentByElements(thenWaitfor);
		if(thenWaitfor.get(0).getAttribute("text").equals(preIllState))
		{
			FunctionLibrary.isElementPresentByElements(thenStateTime);
			if(thenStateTime.get(0).getAttribute("text").equals(waitFor))
			{
				return true;
			}
		}
		return false;
	}

	public List <WebElement> selectBeaconConfig(String beaconName)
	{
		return driver.findElements(By.xpath("//*[@text='"+beaconName+"']"));
	}

	public List <WebElement> getBeaconStatus(String beaconName)
	{
		return driver.findElements(By.xpath("//*[@text='"+beaconName+"']/..//android.widget.Button"));
		//*[@text='Beacon 3']
	}
	public void clickOnAdditionalSettings() {
		FunctionLibrary.clickElement(driver, addSettings);
	}
	public void clickOnMotionSensorSettings() {

		FunctionLibrary.clickElement(driver, motionSensorSettings);
	}
	public void clickOnBackArrowBtn() {

		FunctionLibrary.clickElement(driver, backArrowBtn);
		FunctionLibrary.syncTill(driver, "", null, 0, 1, "NA");
	}
	public void clickOnBeaconSettings() {

		FunctionLibrary.clickElement(driver, beaconSettings);
	}

	public boolean createBeaconConfig(String beaconSettings)
	{
		FunctionLibrary.syncTillTimePeriod(40);
		FunctionLibrary.clickElement(driver, selectBeaconConfig(beaconSettings));
		FunctionLibrary.clickElement(driver, clickOnBeaconMenu);
		FunctionLibrary.clickElement(driver, setDefulValue);
		FunctionLibrary.clickElement(driver, confgureBtn);
		if(FunctionLibrary.isElementPresentByElements(successBeaconMsg))
		{
			return true;
		}

		return false;
	}

	public boolean enableBeacon()
	{
		FunctionLibrary.clickElement(driver, enableBeacon);
		if(FunctionLibrary.isElementPresentByElements(enableBeaconSettings))
		{
			return true;
		}
		return false;
	}
	public boolean validateBeconEnabled(String beaconName)
	{
		FunctionLibrary.syncTillTimePeriod(40);
		String beaconEnable=getBeaconStatus(beaconName).get(0).getAttribute("text");
		if(beaconEnable.equals("Disabled"))
		{
			return true;
		}
		return false;
	}
	@FindBy(xpath = "//*[@text='Output Channel Settings']")
	private List <WebElement> outPutChannelSettings;
	@FindBy(xpath = "//*[@text='Input Sensor Settings']")
	private List <WebElement> inpPutChannelSettings;
	@FindBy(xpath = "//*[@text='Relay']/..//*[contains(@resource-id,'iv_selectDevice')]")
	private List <WebElement> relayradio;
	@FindBy(xpath = "//*[@text='2 Single Channels']/..//*[contains(@resource-id,'iv_selectDevice')]")
	private List <WebElement> twoSinglesradio;

	public void clickOutPutChannelSettings() {

		FunctionLibrary.clickElement(driver, outPutChannelSettings);
	}
	public void clickOnInputSensorSettings() {

		FunctionLibrary.clickElement(driver, inpPutChannelSettings);
	}
	public boolean clickOnRelayRadio() {

		FunctionLibrary.clickElement(driver, relayradio);
		return FunctionLibrary.isElementPresentByElements(operationSuccessMessage);
	}

	public boolean clickOntwoSinglesradio() {

		FunctionLibrary.clickElement(driver, twoSinglesradio);
		return FunctionLibrary.isElementPresentByElements(operationSuccessMessage);
	}



	@FindBy(xpath = "//*[@content-desc='Channel 1']/android.widget.TextView")
	private List <WebElement> channel1Btn;
	@FindBy(xpath = "//*[contains(@resource-id,'text1')]")
	private List <WebElement> selectSensorBtn;
	@FindBy(xpath = "//*[@text='Motion Sensor']")
	private List <WebElement> motionSensorSlt;
	@FindBy(xpath = "//*[@text='Daylight Sensor']")
	private List <WebElement> dayLightSensorSlt;
	@FindBy(xpath = "//*[contains(@resource-id,'volt_custom')]")
	private List <WebElement> customRadioBtn;
	@FindBy(xpath = "//*[@name='1 V']/..//XCUIElementTypeButton")
	private List <WebElement> oneVolRadio;
	@FindBy(xpath = "//*[contains(@resource-id,'editText3')]")
	private List <WebElement> motionSensorTextInp;
	@FindBy(xpath = "//*[@text='Submit']")
	private List <WebElement> motionSubmitBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'editText3')]")
	private List <WebElement> dalyLightCustomTxt;
	@FindBy(xpath = "//*[@content-desc='Channel 2']/android.widget.TextView")
	private List <WebElement> channel2Btn;

	@FindBy(xpath = "//*[contains(@resource-id,'tv_commission')]")
	private List <WebElement> commissionBtn;
	@FindBy(xpath = "//*[contains(@text,'Pairing success for')]")
	private List <WebElement> bukCommCompletedMsg;

	public boolean validateOperationSuccessMsg()
	{
		return FunctionLibrary.isElementPresentByElements(operationSuccessMessage);

	}
	public void addChannel1MotionSensor(String time)
	{
		FunctionLibrary.clickElement(driver, channel1Btn);
		FunctionLibrary.clickElement(driver, selectSensorBtn);
		FunctionLibrary.clickElement(driver, motionSensorSlt);
		FunctionLibrary.clickElement(driver, customRadioBtn);
		FunctionLibrary.sendKeys(driver, motionSensorTextInp, time);
		FunctionLibrary.clickElement(driver, motionSubmitBtn);
		FunctionLibrary.isElementPresentByElements(operationSuccessMessage);
	}
	public void addChannel2DayLightSensor(String time)
	{
		FunctionLibrary.clickElement(driver, channel2Btn);
		FunctionLibrary.clickElement(driver, selectSensorBtn);
		FunctionLibrary.clickElement(driver, dayLightSensorSlt);
		FunctionLibrary.clickElement(driver, customRadioBtn);
		FunctionLibrary.sendKeys(driver, dalyLightCustomTxt, time);
		FunctionLibrary.clickElement(driver, motionSubmitBtn);
		FunctionLibrary.isElementPresentByElements(operationSuccessMessage);
	}

	public boolean validateChannel1Settings(String time)
	{
		FunctionLibrary.clickElement(driver, channel1Btn);
		if(motionSensorTextInp.get(0).getAttribute("text").equals(time))
		{
			return true;
		}
		return false;
	}
	public boolean validateChannel2Settings(String time)
	{
		FunctionLibrary.syncTillTimePeriod(40);
		FunctionLibrary.clickElement(driver, channel2Btn);
		if(motionSensorTextInp.get(0).getAttribute("text").equals(time))
		{
			return true;
		}
		return false;
	}
	public boolean addMultipleDevices(List<String> deviceName)
	{
		addDeviceBtn();

		FunctionLibrary.clickElement(driver, tapHere);
		FunctionLibrary.syncTillTimePeriod(70);

		for(String devName:deviceName)
		{
			selctDeviceRadioBtn(devName);
		}
		FunctionLibrary.clickElement(driver, commissionBtn);

		if(FunctionLibrary.isElementPresentByElements(bukCommCompletedMsg,200))
		{
			return true;
		}
		return false;

	}
	@FindBy(xpath = "//*[contains(@resource-id,'txt_ok')]")
	private List<WebElement> closeIcon;

	public void clickOnCloseIcon()
	{
		FunctionLibrary.clickElement(driver, closeIcon);
	}


	@FindBy(xpath = "//*[contains(@resource-id,'brightnessseekBar')]")
	private WebElement intencityCount;
	public String getIntencityVlue()
	{
		//String inte=intencityCount.getAttribute("text").replace("%","");
		String inte=intencityCount.getText().replace(".0","");;
		return inte;
	}
	@FindBy(xpath = "//*[contains(@resource-id,'coolSeekbar')]")
	private WebElement colorCount;
	public String getColorVlue()
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String defaultValue=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		return defaultValue;
	}

}
