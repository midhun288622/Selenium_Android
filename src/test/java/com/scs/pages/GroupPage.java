package com.scs.pages;

import java.util.List;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.scs.commons.BasePage;
import com.scs.commons.FunctionLibrary;
import com.scs.report.ExtentTestManager;

public class GroupPage extends BasePage{

	public GroupPage(WebDriver driver) {
		super(driver);
	}

	public List <WebElement> getsuccessSettingsMsg(String testgroup)
	{
		return driver.findElements(By.xpath("//*[contains(@name,'"+testgroup+" created successfully')]"));
	}
	public List <WebElement> getEditsuccessSettingsMsg()
	{
		return driver.findElements(By.xpath("//*[contains(@name,'Group name edited successfully')]"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private List <WebElement> enterGroupName;

	@FindBy(xpath = "//*[@name='Rename Group']/..//XCUIElementTypeTextField")
	private WebElement renameGroupInp;


	@FindBy(xpath = "//*[@text='Group added successfully']")
	private List <WebElement> newGroupSuccessMsg;


	@FindBy(xpath = "//*[@text='Groups']")
	private List <WebElement> groupIcon;

	@FindBy(xpath = "//*[contains(@resource-id,'add_remove_device')]")
	private  List <WebElement> plusBlueBtn;


	@FindBy(xpath = "//XCUIElementTypeButton[@name='silderMin']")
	private  List <WebElement> removeDeviceFromGroup;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Added to Group']")
	private  List <WebElement> deviceAddedGroupMsg;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Removed from Group']")
	private  List <WebElement> deviceRemovedGroupMsg;

	@FindBy(xpath = "//*[contains(@resource-id,'app_bar')]//android.widget.ImageView[1]")
	private   List <WebElement> backArrowBtn;

	@FindBy(xpath = "//*[@text='Group Automation Settings']")
	private  List <WebElement> groupSettingsIcon;

	@FindBy(xpath = "//*[contains(@resource-id,'imageView27')]")
	private  List <WebElement> deleteIcon;



	@FindBy(xpath ="//*[@text='OK']")
	private List <WebElement> okBtn;

	@FindBy(name = "cancel icon")
	private WebElement cancelIconInp;

	@FindBy(name = "Intensity")
	private WebElement intensityLabel;

	@FindBy(xpath = "//*[contains(@resource-id,'edttxt_brightness')]")
	private List <WebElement> intensityInp;

	@FindBy(name = "Color temperature (CCT)")
	private WebElement colorPercentageLbl;

	@FindBy(name = "K")
	private WebElement colorDencity;

	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_color_temp')]")
	private WebElement colorPercentageInp;

	@FindBy(name = "Color (RGB)")
	private WebElement colorRGBLabel;

	@FindBy(xpath = "//*[contains(@resource-id,'brightness_seekbar')]")
	private WebElement intencityCount;

	@FindBy(xpath = "//*[contains(@resource-id,'cool_seekbar')]")
	private WebElement colorCount;

	@FindBy(xpath = "//*[contains(@resource-id,'edit_text')]")
	private List<WebElement>  editBtn;

	@FindBy(name = "Filter")
	private List<WebElement> filter;

	@FindBy(name = "NEAR DEVICES")
	private WebElement nearDevice;

	@FindBy(name = "NEAR DEVICES")
	private List<WebElement> nearDevices;

	@FindBy(name = "FAR DEVICES")
	private WebElement farDevices;

	@FindBy(name = "VERY FAR DEVICES")
	private WebElement veryFarDevices;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='NEAR DEVICES']/../../..//XCUIElementTypeCell[1]//XCUIElementTypeStaticText")
	private WebElement nearDeviceList;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='FAR DEVICES']/../../..//XCUIElementTypeCell[2]//XCUIElementTypeStaticText")
	private WebElement farDeviceList;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='VERY FAR DEVICES']/..//XCUIElementTypeStaticText[3]//XCUIElementTypeStaticText")
	private WebElement veryFarDevicesList;
	@FindBy(name = "List Settings")
	private WebElement deviceSettings;



	@FindBy(xpath = "//*[contains(@resource-id,'imageView28')]")
	private List<WebElement>  EditIcon;

	
	@FindBy(xpath = "//*[contains(@resource-id,'brightness_seekbar')]")
	private List<WebElement>  geIntencityCurrentNumber;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_minus']")
	private List<WebElement> minusBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_plus']")
	private List<WebElement>  plusBtn;

	public boolean validateIncreaseIntencity()
	{
		FunctionLibrary.isElementPresentByElements(geIntencityCurrentNumber);
		String temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int defaultValue=Integer.parseInt(temp);
		FunctionLibrary.clickElement(driver, plusBtn);
		FunctionLibrary.syncTillTimePeriod(100);
		temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int actual=Integer.parseInt(temp);
		int expectedValue=actual-defaultValue;
		if(expectedValue==1)
		{
			return true;
		}
		return false;
	}
	public boolean validateDecreaseIntencity()
	{
		FunctionLibrary.isElementPresentByElements(geIntencityCurrentNumber);
		FunctionLibrary.syncTillTimePeriod(50);
		String temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int defaultValue=Integer.parseInt(temp);
		FunctionLibrary.clickElement(driver, minusBtn);
		FunctionLibrary.syncTillTimePeriod(100);
		temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int actual=Integer.parseInt(temp);
		int expectedValue=defaultValue-actual;

		if(expectedValue==1)
		{
			return true;
		}
		return false;
	}
	public void clickOnEditGroupIcon()
	{
		FunctionLibrary.clickElement(driver, EditIcon);
	}
	public void clickDeviceSettings()
	{
		deviceSettings.click();
		FunctionLibrary.isElementPresentByElements(deleteIcon);
	}
	public WebElement getNearDeviceElement()
	{
		return nearDevice;
	}
	public WebElement getFarDeviceElement()
	{
		return farDevices;
	}
	public void clickFilter()
	{

		FunctionLibrary.isElementPresentByElements(filter,10);
		filter.get(0).click();
		FunctionLibrary.isElementPresentByElements(nearDevices,100);
	}


	public boolean validateNearDevice(String deviceName)
	{
		try {
			nearDevice.isDisplayed();
			String temp=nearDeviceList.getText();
			if(temp.contains(deviceName))
			{
				return true;
			}
			return false;
		} catch (Exception e) {

		}
		return true;
	}
	public boolean validateFarDevice(String deviceName)
	{
		farDevices.isDisplayed();
		String temp=farDeviceList.getText();
		if(temp.contains(deviceName))
		{
			return true;
		}
		return false;
	}
	public boolean validateVeryFarDevice(String deviceName)
	{
		veryFarDevices.isDisplayed();
		String temp=farDeviceList.getText();
		if(temp.contains(deviceName))
		{
			return true;
		}
		return false;
	}

	public String getDeviceCount()
	{
		WebElement element=driver.findElement(By.xpath("(//*[contains(@resource-id,'scene_name')]/..//android.widget.TextView)[2]"));
		String temp=element.getText();
		return temp;
	}
	public void clickOKBtn()
	{
		FunctionLibrary.clickElement(driver, okBtn);
	}
	public void clickOnEditIntencity()
	{
		FunctionLibrary.clickElement(driver, editBtn);
		ExtentTestManager.logTestResult("INFO","Navigate to edit intencity page");
	}
	public void editIntencity(String intencity,String colorTemp)
	{
		FunctionLibrary.isElementPresentByElements(intensityInp);
		FunctionLibrary.sendKeys(driver, intensityInp, intencity);
		FunctionLibrary.sendKeys(driver, colorPercentageInp, colorTemp);
		FunctionLibrary.clickElement(driver, okBtn);
		FunctionLibrary.syncTillTimePeriod(50);
	}
	public String getIntencityVlue()
	{
		//String inte=intencityCount.getAttribute("text").replace("%","");
		String inte=intencityCount.getText().replace(".0","");;
		return inte;
	}
	@FindBy(xpath = "//*[contains(@resource-id,'edit_text')]")
	private List <WebElement> eitIntencity;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_color_temp')]")
	private List <WebElement> cctValue;
	@FindBy(xpath = "//*[contains(@resource-id,'iv_close')]")
	private List <WebElement> closePopup;
	public String getColorVlue()
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, eitIntencity);
		FunctionLibrary.isElementPresentByElements(cctValue, 30);
		String defaultValue=cctValue.get(0).getAttribute("text");
		FunctionLibrary.clickElement(driver, closePopup);
		return defaultValue;
	}


	public void editGroup(String groupName)
	{
		FunctionLibrary.sendKeys(driver, enterGroupName, groupName);
		FunctionLibrary.clickElement(driver, okBtn);
		FunctionLibrary.syncTillTimePeriod(20);
	}

	public boolean createGroup(String groupName)
	{
		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.sendKeys(driver, enterGroupName, groupName);
		FunctionLibrary.clickElement(driver, okBtn);

		if(FunctionLibrary.isElementPresentByElements(newGroupSuccessMsg))
		{

			FunctionLibrary.clickElement(driver, okBtn);
			return true;
		}
		return false;
	}

	public boolean deleteGroup(String groupName)
	{
		FunctionLibrary.clickElement(driver, deleteIcon);
		if(FunctionLibrary.isElementPresentByElements(yesBtn))
		{
			yesBtn.get(0).click();
			FunctionLibrary.isElementPresentByElements(grupDeletedMessage);
			FunctionLibrary.syncTillTimePeriod(10);
			return true;
		}

		return false;
	}
	public void clickBackArrow()
	{
		FunctionLibrary.clickElement(driver, backArrowBtn);
	}

	public void clickOnGroupsIcon()
	{
		FunctionLibrary.clickElement(driver, groupIcon);
		ExtentTestManager.logTestResult("INFO","Navigate to group page");

	}

	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/search_bar']")
	private  List <WebElement> searchInGroups;
	
	public void searchInGroups(String deviceName)
	{
		FunctionLibrary.isElementPresentByElements(groupSettingsIcon,30);
		FunctionLibrary.sendKeys(driver, searchInGroups, deviceName);
		FunctionLibrary.syncTillTimePeriod(40);
	}
	@FindBy(xpath = "//*[contains(@resource-id,'tv_app_gallery')]")
	private  List <WebElement>   chooseAppGalary;
	@FindBy(xpath = "(//*[contains(@resource-id,'iv_icon')])[3]")
	private  List <WebElement>   imageSelect;
	@FindBy(xpath = "//*[contains(@resource-id,'imageView3')]")
	private  List <WebElement>   selectedImage;
	@FindBy(xpath = "//*[contains(@resource-id,'textView110')]")
	private  List <WebElement>   submitBtn;
	public boolean changeImage()
	{
		FunctionLibrary.clickElement(driver, chooseAppGalary);
		FunctionLibrary.clickElement(driver, imageSelect);
		FunctionLibrary.clickElement(driver, submitBtn);
		FunctionLibrary.clickElement(driver, OKbutton);
		return FunctionLibrary.isElementPresentByElements(selectedImage);
	}
	public void clickOnSettings(String groupName)
	{
		String xpath="//*[contains(@resource-id,'group_view')]//*[@text='"+groupName+"']/..//*[contains(@resource-id,'settings')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	public void removeDeviceFromGroup(String groupName)
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		FunctionLibrary.syncTillTimePeriod(100);

		FunctionLibrary.clickElement(driver, backArrowBtn);
		FunctionLibrary.syncTillTimePeriod(30);
	}
	public void addDeviceToGroup(String groupName)
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		FunctionLibrary.syncTillTimePeriod(50);
		FunctionLibrary.clickElement(driver, backArrowBtn);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	public String validateDeviceAddedIntoGroup(String groupName)
	{
		String listDeviceCount="(//*[contains(@resource-id,'group_view')]//*[@text='"+groupName+"']/..//*[@class='android.widget.TextView'])[2]";
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(listDeviceCount)));
		List<WebElement> list1=driver.findElements(By.xpath(listDeviceCount));
		String value=list1.get(0).getAttribute("text");
		return value;
	}


	//Suite 3 chnages

	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/imv_dp']")
	private WebElement imagechange;
	public void clickOnChangeImageBtn()
	{
		FunctionLibrary.clickElement(driver, imagechange);
	}
}
