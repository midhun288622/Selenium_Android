package com.scs.pages;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;

import com.scs.commons.BasePage;
import com.scs.commons.FunctionLibrary;
import com.scs.report.ExtentTestManager;
import com.sun.mail.handlers.text_html;

public class ScenesPage extends BasePage{

	public ScenesPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath = "//*[@text='OK']")
	private WebElement deviceAddedOkBtn;

	@FindBy(xpath = "//*[contains(@resource-id,'toolbar')]//android.widget.ImageButton")
	private List <WebElement> backArrowBtn;

	@FindBy(xpath = "//*[@text='Scenes']")
	private List <WebElement> scenesIcon;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/buttonAdd']")
	private  List <WebElement> plusBlueBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'successfully added')]")
	private  List <WebElement> sceneddedGroupMsg;
	@FindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'success')]")
	private  List <WebElement> sceneRemovedGroupMsg;

	@FindBy(xpath = "//*[contains(@resource-id,'back_button')]")
	private   List <WebElement>  backArrowAnimationBtn;
	@FindBy(xpath = "//*[@text='GROUPS']")
	private   List <WebElement>  groupsIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'search_bar')]")
	private  WebElement sceneSearchIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'repeat_switch')]")
	private  List <WebElement> repeatButon;
	@FindBy(xpath = "//*[@text='Repeat Mode Updated']")
	private  List <WebElement> repeatModeUpdateMsg;


	@FindBy(xpath = "//*[contains(@resource-id,'edtName')]")
	private WebElement enterSceneName;

	@FindBy(xpath = "//*[@text='OK']")
	private WebElement okButton;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private  List <WebElement>  renameEdit;
	@FindBy(xpath = "//android.widget.TextView[@content-desc='Delete']")
	private  List <WebElement> deleteIconAnimation;

	@FindBy(xpath = "//*[contains(@resource-id,'btn_delete')]")
	private  List <WebElement> deletIconScene;

	@FindBy(xpath = "//*[contains(@resource-id,'btn_rename')]")
	private  List <WebElement> renameIcon;
	@FindBy(xpath = "//*[@text='OK']")
	private List <WebElement> deviceAddedOkBtns;
	@FindBy(xpath = "//*[@text='DEVICES']")
	private  List <WebElement> devicesBtn;


	@FindBy(xpath = "//*[contains(@resource-id,'add_seane_card')]//android.widget.ImageView")
	private  List <WebElement>  increaseBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'profileList')]")
	private  List <WebElement>   arrowDownIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'btn_camera')]")
	private  List <WebElement>   imageChangeBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'tv_phone_gallery')]")
	private  List <WebElement>   choosePhoneGalary;
	@FindBy(xpath = "//*[contains(@resource-id,'tv_app_gallery')]")
	private  List <WebElement>   chooseAppGalary;
	@FindBy(xpath = "//*[contains(@resource-id,'tv_camera')]")
	private  List <WebElement>   captureFromCamera;
	@FindBy(xpath = "//XCUIElementTypeImage[contains(@name,'029')]")
	private  List <WebElement>   imageSelect;
	@FindBy(xpath = "//*[contains(@resource-id,'scan_icon')]")
	public List<WebElement>  addBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'nav_hos')]//android.widget.ImageView")
	public List<WebElement>  addSceneScheduleBtn;


	@FindBy(name = "Set Run Time")
	private List<WebElement> setRunTimeText;



	@FindBy(xpath = "//*[contains(@resource-id,'constraintLayout')]//android.widget.Spinner/android.widget.TextView")
	private  List<WebElement>   fadeTimeInMins;
	@FindBy(xpath = "//*[contains(@resource-id,'constraintLayout')]/following-sibling::android.view.ViewGroup//android.widget.TextView[contains(@ resource-id,'text1')]")
	private  List<WebElement>     fadeTimeInSecs;
	@FindBy(xpath = "//*[contains(@resource-id,'cl_min')]//android.widget.Spinner/android.widget.TextView")
	private  List<WebElement>     runTimeInMins;
	@FindBy(xpath = "//*[contains(@resource-id,'cl_sec')]//android.widget.Spinner/android.widget.TextView")
	private  List<WebElement>     runTimeInSecs;
	@FindBy(xpath = "//*[contains(@resource-id,'cl_hr')]//android.widget.Spinner/android.widget.TextView")
	private  List<WebElement>     runTimeInHrs;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Scene 1'/..")
	private  List <WebElement>   clickDragIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'btn_preference')]")
	private  List <WebElement> sunBtn;


	@FindBy(xpath = "//*[@text='08']")
	private  WebElement   time;


	public  List <WebElement>  selectTime(String time)
	{
		return driver.findElements(By.xpath("//*[@text='"+time+"']"));
	}

	public  List <WebElement> getPowerButon(String sceneName)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneName+"']/..//*[contains(@resource-id,'power_on')]"));
	}


	public void clickSunSettings()
	{
		FunctionLibrary.clickElement(driver, sunBtn);
	}

	//Scene Selection for Edit animation Settings

	public List<WebElement> sceneSelectionForEditAnimation(String scene_name)

	{

		return driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='"+scene_name+"']"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'seekBarDaylightMin')]")
	private List<WebElement> geIntencityCurrentNumber;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_minus']")
	private WebElement minusBtn;
	@FindBy(xpath = "//*[@resource-id='com.wisilica.Home:id/iv_plus']")
	private WebElement plusBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'coolSeekbar')]")
	private WebElement colorCount;
	public String getIntencityVlue()
	{
		//String inte=intencityCount.getAttribute("text").replace("%","");
		String inte=geIntencityCurrentNumber.get(0).getText().replace(".0","");;
		return inte;
	}
	public boolean validateIncreaseIntencity()
	{
		FunctionLibrary.isElementNotPresentByElements(geIntencityCurrentNumber);
		String temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int defaultValue=Integer.parseInt(temp);
		FunctionLibrary.clickElement(driver, plusBtn);
		FunctionLibrary.syncTillTimePeriod(50);
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
		FunctionLibrary.isElementNotPresentByElements(geIntencityCurrentNumber);
		String temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int defaultValue=Integer.parseInt(temp);
		FunctionLibrary.clickElement(driver, minusBtn);
		FunctionLibrary.syncTillTimePeriod(50);
		temp=geIntencityCurrentNumber.get(0).getText().replace(".0", "");
		int actual=Integer.parseInt(temp);
		int expectedValue=defaultValue-actual;

		if(expectedValue==1)
		{
			return true;
		}
		return false;
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
	public void clickOnBackArrow()
	{
		FunctionLibrary.clickElement(driver, backArrowBtn);
	}

	public void clickOnPowerButton(String sceneName)
	{
		FunctionLibrary.clickElement(driver, getPowerButon(sceneName));
	}
	public void validateAllImageIconsPresent()
	{
		FunctionLibrary.isElementPresentByElements(choosePhoneGalary);
		chooseAppGalary.get(0).isDisplayed();
		captureFromCamera.get(0).isDisplayed();
	}

	public void clickOnChangeImageBtn()
	{
		FunctionLibrary.clickElement(driver, imageChangeBtn);
	}
	public boolean changeImage()
	{
		FunctionLibrary.clickElement(driver, chooseAppGalary);
		FunctionLibrary.clickElement(driver, imageSelect);
		FunctionLibrary.clickElement(driver, OKbutton);
		return FunctionLibrary.isElementPresentByElements(imageSelect);
	}
	public  List <WebElement> getAddSceneElement(String sceneName)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneName+"']"));
	}



	public void updateTimeInScene(String fadeTimeInMinsStr,String fadeTimeInSecsStr,String runTimeMinsStr,String runTimeSecsStr,String runTimeHrsStr)
	{

		System.out.println();
		FunctionLibrary.clickElement(driver, fadeTimeInMins);
		FunctionLibrary.syncTillTimePeriod(5);
		FunctionLibrary.clickElement(driver, selectTime(fadeTimeInMinsStr));

		FunctionLibrary.clickElement(driver, fadeTimeInSecs);
		FunctionLibrary.syncTillTimePeriod(5);
		FunctionLibrary.clickElement(driver, selectTime(fadeTimeInSecsStr));

		FunctionLibrary.clickElement(driver, runTimeInHrs);
		FunctionLibrary.syncTillTimePeriod(5);
		FunctionLibrary.clickElement(driver, selectTime(runTimeHrsStr));

		FunctionLibrary.clickElement(driver, runTimeInMins);
		FunctionLibrary.syncTillTimePeriod(5);
		FunctionLibrary.clickElement(driver, selectTime(runTimeMinsStr));

		FunctionLibrary.clickElement(driver, runTimeInSecs);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, selectTime(runTimeSecsStr));
		FunctionLibrary.syncTillTimePeriod(5);


	}
	public void addSceneToAnimation(String scene,String fadeTimeInMinsStr,String fadeTimeInSecsStr,String runTimeMinsStr,String runTimeSecsStr,String runTimeHrsStr)
	{
		ExtentTestManager.logTestResult("INFO","Navigate to add scene animation page");
		FunctionLibrary.clickElement(driver, increaseBtn);
		FunctionLibrary.syncTillTimePeriod(20);

		FunctionLibrary.clickElement(driver, arrowDownIcon);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, getAddSceneElement(scene));
		FunctionLibrary.syncTillTimePeriod(30);
		//set fade time
		updateTimeInScene(fadeTimeInMinsStr, fadeTimeInSecsStr, runTimeMinsStr, runTimeSecsStr, runTimeHrsStr);
		FunctionLibrary.clickElement(driver, setBtn);
		FunctionLibrary.syncTillTimePeriod(30);
		FunctionLibrary.clickElement(driver, setBtn);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, saveBtn);
		FunctionLibrary.syncTillTimePeriod(30);	
	}

	public void updateSceneAnimation(String scene, String expectedscene, String fadeTimeInMinsStr,String fadeTimeInSecsStr,String runTimeMinsStr,String runTimeSecsStr,String runTimeHrsStr)
	{
		//Navigating to scene
		ExtentTestManager.logTestResult("INFO","Navigate to add scene animation page");
		FunctionLibrary.isElementPresentByElements(deleteIconAnimation);
		FunctionLibrary.clickElement(driver, selectTime(expectedscene));
		FunctionLibrary.syncTillTimePeriod(20);

		updateTimeInScene(fadeTimeInMinsStr, fadeTimeInSecsStr, runTimeMinsStr, runTimeSecsStr, runTimeHrsStr);
		FunctionLibrary.clickElement(driver, setBtn);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, setBtn);
		FunctionLibrary.syncTillTimePeriod(10);
		FunctionLibrary.clickElement(driver, saveBtn);
		FunctionLibrary.syncTillTimePeriod(50);
	}


	public boolean validateTimeValues(String scene,String expectedScene, String fadeTimeInMinsStr,String fadeTimeInSecsStr,String runTimeMinsStr,String runTimeSecsStr,String runTimeHrsStr)
	{
		try {


			FunctionLibrary.clickElement(driver, selectTime(expectedScene));

			//Printing the configured fade and run times

			System.out.println("fade time in min is "+fadeTimeInMins.get(0).getAttribute("text"));
			System.out.println("fade time in Sec is " +fadeTimeInSecs.get(0).getAttribute("text"));
			System.out.println("Run time in Min is " +runTimeInMins.get(0).getAttribute("text"));
			System.out.println("Run time in Sec is " +runTimeInSecs.get(0).getAttribute("text"));
			System.out.println("Run time in Hour is " +runTimeInHrs.get(0).getAttribute("text"));


			if(!fadeTimeInMins.get(0).getAttribute("text").equals(fadeTimeInMinsStr))

			{

				return false;

			}
			if(!fadeTimeInSecs.get(0).getAttribute("text").equals(fadeTimeInSecsStr))
			{

				return false;
			}
			if(!runTimeInMins.get(0).getAttribute("text").equals(runTimeMinsStr))
			{



				return false;
			}
			if(!runTimeInSecs.get(0).getAttribute("text").equals(runTimeSecsStr))
			{



				return false;
			}
			if(!runTimeInHrs.get(0).getAttribute("text").equals(runTimeHrsStr))
			{



				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@FindBy(xpath = "//*[contains(@resource-id,'imageView33')]")
	private  List <WebElement> sceneAnimationRadio;
	@FindBy(xpath = "//*[contains(@resource-id,'imageView32')]")
	private  List <WebElement> sceneRadio;


	public int getPlayButtonCount()
	{
		return driver.findElements(By.xpath("//*[contains(@resource-id,'linear')]//android.widget.ImageView")).size();
	}
	public List <WebElement> getPlayButtons()
	{
		return driver.findElements(By.xpath("//*[contains(@resource-id,'linear')]//android.widget.ImageView"));
	}
	public List <WebElement> getsuccessSettingsMsg(String sceneName)
	{
		return driver.findElements(By.xpath("//*[contains(@name,'"+sceneName+" created successfully')]"));
	}

	public List <WebElement> getRenamesuccessSettingsMsg()
	{
		return driver.findElements(By.xpath("//*[contains(@name,'renamed successfully')]"));
	}
	public List <WebElement> getDeletesuccessSettingsMsg()
	{
		return driver.findElements(By.xpath("//*[contains(@name,'Scene renamed successfully')]"));
	}


	public void clickDeviceLabel()
	{
		FunctionLibrary.clickElement(driver,devicesBtn);
	}
	public boolean deleteScene(String sceneName)
	{
		FunctionLibrary.clickElement(driver, deletIconScene);
		if(FunctionLibrary.isElementPresentByElements(yesBtn))
		{
			yesBtn.get(0).click();
			waitToLoadProgressView();
			FunctionLibrary.syncTillTimePeriod(50);
			return true;
		}

		return false;
	}
	public boolean deleteSceneAnimation(String sceneName)
	{
		FunctionLibrary.clickElement(driver, deleteIconAnimation);
		if(FunctionLibrary.isElementPresentByElements(yesBtn))
		{
			yesBtn.get(0).click();
			FunctionLibrary.syncTillTimePeriod(10);
			return true;
		}

		return false;
	}

	public boolean editScene(String sceneName)
	{
		FunctionLibrary.clickElement(driver, renameIcon);
		FunctionLibrary.isElementPresentByElements(renameEdit);
		FunctionLibrary.sendKeys(driver, renameEdit, sceneName);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.syncTillTimePeriod(20);
		return true;
	}

	@FindBy(xpath = "//*[contains(@resource-id,'menu_edit')]")
	private  List <WebElement> editSceneAnimation;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private  List <WebElement> enterNameAnimation;
	@FindBy(xpath = "//*[contains(@resource-id,'btn_addEdit')]")
	private  List <WebElement> renameAnimationBtn;

	public void editSceneAnimation(String sceneAnimationName)
	{
		FunctionLibrary.clickElement(driver, editSceneAnimation);
		FunctionLibrary.isElementPresentByElements(enterNameAnimation);
		FunctionLibrary.sendKeys(driver, enterNameAnimation, sceneAnimationName);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, setBtn);
		FunctionLibrary.clickElement(driver, saveBtn);
		FunctionLibrary.syncTillTimePeriod(20);
	}


	public boolean createScene(String sceneName)
	{
		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.clickElement(driver, sceneRadio);
		enterSceneName.sendKeys(sceneName);
		FunctionLibrary.clickElement(driver, setBtn);
		if(FunctionLibrary.isElementPresentByElements(OKbutton))
		{
			FunctionLibrary.clickElement(driver, OKbutton);
			return true;
		}
		return false;
	}

	public boolean createSceneAnimation(String sceneName)
	{

		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.clickElement(driver, sceneAnimationRadio);
		enterSceneName.sendKeys(sceneName);
		FunctionLibrary.clickElement(driver, setBtn);
		if(FunctionLibrary.isElementPresentByElements(OKbutton))
		{
			FunctionLibrary.clickElement(driver, OKbutton);
			return true;
		}
		return false;
	}

	public void searchGroupInSenes(String groupName)
	{
		FunctionLibrary.clickElement(driver, groupsIcon);
		sceneSearchIcon.sendKeys(groupName);
		FunctionLibrary.syncTillTimePeriod(50);
	}
	public void searchDeviceInSenes(String deviceName)
	{
		FunctionLibrary.clickElement(driver, devicesBtn);
		sceneSearchIcon.sendKeys(deviceName);
		FunctionLibrary.syncTillTimePeriod(30);
	}

	public void clickOnScenesIcon()
	{
		FunctionLibrary.clickElement(driver, scenesIcon);
		ExtentTestManager.logTestResult("INFO","Navigate to ScenePage");
	}

	public void removeGroupFromScene(String groupName)
	{
		FunctionLibrary.syncTillTimePeriod(20);	
		searchGroupInSenes(groupName);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		waitToLoadProgressView();
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		waitToLoadProgressView();
		FunctionLibrary.syncTillTimePeriod(20);	

	}
	public void removeDeviceFromScene()
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		waitToLoadProgressView();
		FunctionLibrary.syncTillTimePeriod(20);

	}
	public void clickOnSettings(String sceneName)
	{
		ExtentTestManager.logTestResult("INFO","Navigate to settings page");
		String xpath="//*[contains(@resource-id,'scene_view')]//*[@text='"+sceneName+"']/..//*[contains(@resource-id,'settings')]";
		FunctionLibrary.clickElement(driver, driver.findElements(By.xpath(xpath)));

	}
	public void addGroupToScene(String sceneName)
	{
		FunctionLibrary.syncTillTimePeriod(30);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		FunctionLibrary.syncTillTimePeriod(100);

	}
	public void clickOnBackArrowBtn()
	{
		FunctionLibrary.clickElement(driver, backArrowBtn);
	}
	public void clickOnBackArrowAnimationBtn()
	{
		FunctionLibrary.clickElement(driver, backArrowAnimationBtn);
	}
	public String validateGroupAddedToScene(String sceneName)
	{
		String listDeviceCount="(//*[contains(@resource-id,'scene_view')]//*[@text='"+sceneName+"']/..//*[@class='android.widget.TextView'])[2]";
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(listDeviceCount)));
		List<WebElement> list1=driver.findElements(By.xpath(listDeviceCount));
		String value=list1.get(0).getAttribute("text");
		return value;
	}

	public List <WebElement> getSuccessMsgsOnDeviceAdd(String deviceName,String sceneName)
	{
		return driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='"+deviceName+" is successfully added to "+sceneName+"']"));
	}
	public List <WebElement> getSuccessMsgsOnDeviceRemoval(String deviceName,String sceneName)
	{
		FunctionLibrary.isElementPresentByElements(deviceAddedOkBtns);
		return driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='"+deviceName+" is successfully removed from "+sceneName+"']"));
	}


	public void addDeviceToScene()
	{
		FunctionLibrary.syncTillTimePeriod(30);
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		FunctionLibrary.syncTillTimePeriod(100);

	}

	public String validateAddDeviceToScene(String sceneName)
	{
		String listDeviceCount="(//*[contains(@resource-id,'scene_view')]//*[@text='"+sceneName+"']/..//*[@class='android.widget.TextView'])[2]";
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(listDeviceCount)));
		List<WebElement> list1=driver.findElements(By.xpath(listDeviceCount));
		String value=list1.get(0).getAttribute("text");
		return value;
	}
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Removed from Group']")
	private  List <WebElement> deviceRemovedGroupMsg;

	public boolean removeDeviceFromScene(String deiviceName,String sceneName)
	{
		FunctionLibrary.isElementPresentByElements(plusBlueBtn);
		plusBlueBtn.get(0).click();
		boolean flag=getSuccessMsgsOnDeviceRemoval(deiviceName, sceneName).size()>0;
		deviceAddedOkBtns.get(0).click();
		FunctionLibrary.clickElement(driver, backArrowBtn);
		return flag;
	}
	public boolean validatePlayButonCount()
	{
		int count=getPlayButtonCount();
		if(count==5)
		{
			return true;
		}
		return false;
	}

	public void clickAllPlayButtons()
	{
		for(WebElement element:getPlayButtons())
		{
			element.click();
			FunctionLibrary.syncTillTimePeriod(10);
		}
	}
	public boolean validateRepeatModeEnabled(String sceneName)
	{
		FunctionLibrary.clickElement(driver, repeatButon);
		return FunctionLibrary.isElementPresentByElements(repeatModeUpdateMsg,30);
	}

	@FindBy(xpath = "//*[@text='Scene Schedule']")
	private List <WebElement> sceneScheduleBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private List <WebElement> enterSchedule;

	public List <WebElement> getSceneScheduleBtn(String sceneSchedule)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneSchedule+"']"));
	}
	public List <WebElement> getSceneScheduleName(String sceneSchedule)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneSchedule+"']"));
	}
	public List <WebElement> getSceneScheduleTime(String sceneSchedule)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneSchedule+"']/..//XCUIElementTypeStaticText[3]"));
	}
	public List <WebElement> getSceneScheduleRepeatTime(String sceneSchedule)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneSchedule+"']/..//XCUIElementTypeStaticText[2]"));
	}
	public List <WebElement> getSceneScheduleRepeatOn(String sceneSchedule)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneSchedule+"']/..//XCUIElementTypeStaticText[4]"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'imageView42')]")
	private WebElement dateRadioBtn;


	@FindBy(xpath = "//*[contains(@resource-id,'imageView41')]")
	private WebElement repeatRadioBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'clStartAt')]//android.widget.TextView")
	private WebElement dateBtn;
	//@FindBy(xpath = "//*[contains(@resource-id,'toggle_mode')]")
	//private WebElement datePicker;
	@FindBy(xpath = "//*[contains(@resource-id,'toggle_mode')]")
	private WebElement timePicker;
	@FindBy(xpath = "//*[contains(@resource-id,'input_hour')]")
	private WebElement timeHrEnter;
	@FindBy(xpath = "//*[contains(@resource-id,'input_minute')]")
	private WebElement timeMinEnter;
	@FindBy(xpath = "//*[contains(@resource-id,'textView75')]")
	private  List <WebElement> addSceneBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Choose Start Action']/..//XCUIElementTypeTextField")
	private List <WebElement>  searchSceneSchedule;
	@FindBy(xpath = "//*[contains(@name,'Schedule added successfully')]")
	private  List <WebElement> scheduleAddedSuccessfully;
	@FindBy(xpath = "//android.widget.TextView[@content-desc='Edit']")
	private WebElement editSceneSchedule;
	@FindBy(xpath = "//android.widget.TextView[@content-desc='delete']")
	private  List <WebElement> deleteScheduleBtn;


	public List <WebElement>  selectRepeatOn(String repeatOn)
	{
		return driver.findElements(By.xpath("//*[@text='"+repeatOn+"']"));
	}


	//	public void searchSceneShedule(String searchText,int time)
	//	{
	//		FunctionLibrary.isElementPresentByElements(searchSceneSchedule);
	//		FunctionLibrary.sendKeys(driver, searchSceneSchedule.get(0), searchText);
	//		searchSceneSchedule.get(0).sendKeys(Keys.RETURN);
	//	}
	public boolean isSceneScheduleListed(String scheduleName,int time)
	{
		FunctionLibrary.isElementPresentByElements(getSceneScheduleName(scheduleName),time);
		return getSceneScheduleName(scheduleName).size()>0;
	}
	public void clickSceneSchedule()
	{
		FunctionLibrary.clickElement(driver, sceneScheduleBtn);
	}
	public void clickOnSceneSchedule(String scenSchedule)
	{
		FunctionLibrary.isElementPresentByElements(getSceneScheduleBtn(scenSchedule),10);
		if(getSceneScheduleBtn(scenSchedule).size()>0)
		{
			getSceneScheduleBtn(scenSchedule).get(0).click();
		}
	}

	public boolean validateSceneScheduleDateTime(String scenSchedule,String timeStr,String date)
	{
		String time= getSceneScheduleTime(scenSchedule).get(0).getAttribute("name");
		if(time.equals(timeStr))
		{
			return true;
		}
		return false;
	}

	public boolean validateSceneScheduleRepeatTime(String scenSchedule,String timeStr,String date)
	{
		String time= getSceneScheduleRepeatTime(scenSchedule).get(0).getAttribute("name");
		if(time.equals(timeStr))
		{
			return true;
		}
		return false;
	}
	public boolean validateSceneScheduleRepeatOn(String scenSchedule,String timeStr,String repeatOnStr)
	{
		String repeatOn= getSceneScheduleRepeatOn(scenSchedule).get(0).getAttribute("name");
		if(repeatOnStr.equals(repeatOn))
		{
			return true;
		}
		return false;
	}




	public List <WebElement>  getSceneScheduleDate(String sceneScheduleName)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneScheduleName+"']/..//*[contains(@resource-id,'tvStartDate')]"));
	}
	public List <WebElement>  getSceneScheduleTimeStr(String sceneScheduleName)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneScheduleName+"']/..//*[contains(@resource-id,'tvStartTime')]"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'clStartAt')]/android.widget.TextView")
	public List<WebElement> dateAndTimeStr;
	public List <WebElement>  getSceneScheduleWeekStr(String sceneScheduleName)
	{
		return driver.findElements(By.xpath("//*[@text='"+sceneScheduleName+"']/..//*[contains(@resource-id,'textView63')]"));
	}

	public boolean updateDateAndTime(String scenSchedule,String sceneName,String time)
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, dateRadioBtn);
		FunctionLibrary.clickElement(driver, addSceneBtn);	
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, selectRepeatOn(sceneName));
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.isElementPresentByElements(sceneScheduleSuccessull);
		FunctionLibrary.syncTillTimePeriod(20);
		String dateTimeStr=dateAndTimeStr.get(0).getText();
		FunctionLibrary.clickElement(driver, scenScheduleBackBtn);
		FunctionLibrary.syncTillTimePeriod(20);
		if(isSceneScheduleListed(scenSchedule,10))
		{
			if(FunctionLibrary.isElementPresentByElements(getSceneScheduleDate(scenSchedule)))
			{
			ExtentTestManager.logTestResult("INFO", "getSceneScheduleDate true");
			}
			else
			{
				ExtentTestManager.logTestResult("INFO", "getSceneScheduleDate false");
			}
			String dateStr=getSceneScheduleDate(scenSchedule).get(0).getAttribute("text").replaceAll("\\s+","") ;
			String timeStr=getSceneScheduleTimeStr(scenSchedule).get(0).getAttribute("text").replaceAll("\\s+","") ;

			if(dateStr.equals(dateTimeStr.split("-")[0].replaceAll("\\s+","") ))
			{
				if(timeStr.equals(dateTimeStr.split("-")[1].replaceAll("\\s+","")))
				{
					return true;
				}
			}
		}

		return false;

	}

	public boolean updateRepeatConfig(String scenSchedule,String repeatOn,String time)
	{
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, repeatRadioBtn);
		FunctionLibrary.clickElement(driver, dateBtn);
		FunctionLibrary.clickElement(driver, timePicker);
		FunctionLibrary.sendKeys(driver, timeHrEnter, time.split(":")[0]);
		FunctionLibrary.sendKeys(driver, timeMinEnter, time.split(":")[1]);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.clickElement(driver, selectRepeatOn(repeatOn));
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.isElementPresentByElements(sceneScheduleSuccessull);
		FunctionLibrary.clickElement(driver, scenScheduleBackBtn);
		FunctionLibrary.syncTillTimePeriod(20);
		if(isSceneScheduleListed(scenSchedule,10))
		{
			FunctionLibrary.isElementPresentByElements(getSceneScheduleTimeStr(scenSchedule));
			String dateStr=getSceneScheduleTimeStr(scenSchedule).get(0).getAttribute("text").replaceAll("\\s+","") ;
			
			if(dateStr.equals(time))
			{
				FunctionLibrary.isElementPresentByElements(getSceneScheduleWeekStr(scenSchedule));
				String repeat=getSceneScheduleWeekStr(scenSchedule).get(0).getAttribute("text").replaceAll("\\s+","") ;
				if(repeat.equals(repeatOn))
				{
					return true;
				}
			}
		}
		return false;

	}
	public boolean validateSceneScheduleDisplayed(String scenSchedule)
	{
		FunctionLibrary.isElementPresentByElements(getSceneScheduleName(scenSchedule),10);
		return getSceneScheduleName(scenSchedule).size()>0;
	}

	public void createSceneSchedule(String scenSchedule)
	{
		FunctionLibrary.clickElement(driver, addSceneScheduleBtn);
		FunctionLibrary.sendKeys(driver, enterSchedule, scenSchedule);
		FunctionLibrary.clickElement(driver, OKbutton);
		FunctionLibrary.syncTillTimePeriod(30);
		FunctionLibrary.clickElement(driver, OKbutton);
	}
	public void editSceneSchedule(String scenSchedule)
	{
		FunctionLibrary.clickElement(driver, editSceneSchedule);
		FunctionLibrary.sendKeys(driver, enterSchedule, scenSchedule);
		FunctionLibrary.clickElement(driver, okButton);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	public void deleteSceneSchedule(String scenSchedule)
	{
		FunctionLibrary.clickElement(driver, deleteScheduleBtn);
		FunctionLibrary.clickElement(driver, deviceAddedOkBtns);
		FunctionLibrary.syncTillTimePeriod(50);

	}

	public boolean isDeviceListed(String deviceName,int time)
	{
		System.out.println();
		String xpath="//*[contains(@resource-id,'scene_view')]//*[@text='"+deviceName+"']";
		List<WebElement> list=driver.findElements(By.xpath(xpath));
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)),time);
		System.out.println(list.size());
		return list.size()>0;
	}
}
