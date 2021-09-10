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

public class HomePage extends BasePage{

	@FindBy(xpath = "//XCUIElementTypeButton[@name='plus icon']")
	private WebElement addBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Add Device']")
	private WebElement addDevice;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Tap here']")
	private WebElement tapHere;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='to search device']")
	private WebElement searchHere;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Skip']")
	private List <WebElement> skipLink;
	
	
	@FindBy(xpath = "//*[contains(@resource-id,'serach_bar')]")
	private List <WebElement> searchTextField;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='WRA5WA65B27']")
	private WebElement deviviceLst;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='RadioBtn UnSelected']")
	private List<WebElement> radioBtnOnAddPage;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Plus Blue']")
	private WebElement addBtnOnAddpage;
	@FindBy(xpath = "//XCUIElementTypeProgressIndicator[@name='Progress']")
	private WebElement progressBar;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Pairing Success']")
	private List<WebElement> pairSuccessMsg;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Ok']")
	private WebElement okBtnOnPairSuccessMsg;
	@FindBy(xpath = "//XCUIElementTypeTextField[`value ='WRA5WA65B27-1']")
	private WebElement deviceNameOnDeviceNamePopup;
	@FindBy(xpath = "//XCUIElementTypeStaticText[`label ='OK'][1]")
	private WebElement okBtnOnDeviceNameOkBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Group 1']")
	private WebElement groupNameOnadd;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Ok']")
	private WebElement addGroupOkBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Added to Group']")
	private WebElement devcieAddedMsg;
	@FindBy(xpath = "//*[@text='OK']")
	private WebElement deviceAddedOkBtn;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Sun']")
	private WebElement sunBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='OFF']")
	private  List <WebElement> offBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='ON']")
	private  List <WebElement> onBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Intensity']/..//*[@name='minus']")
	private WebElement minusBtn;
	@FindBy(xpath = "	//XCUIElementTypeStaticText[@name='Intensity']/..//*[@name='Slider plus']")
	private WebElement plusBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Intensity']/..//XCUIElementTypeStaticText[2]")
	private WebElement geIntencityCurrentNumber;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Color temperature (CCT)']/..//*[@name='minus']")
	private WebElement minusBtnCCT;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Color temperature (CCT)']/..//*[@name='Slider plus']")
	private WebElement plusBtnCCT;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Color temperature (CCT)']/..//XCUIElementTypeStaticText[2]")
	private WebElement geCCTCurrentNumber;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Group']")
	private WebElement groupIcon;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='plusInBlue']")
	private  List <WebElement> plusBlueBtn;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='minus']")
	private  List <WebElement> removeDeviceFromGroup;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Added to Group']")
	private  List <WebElement> deviceAddedGroupMsg;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Device Removed from Group']")
	private  List <WebElement> deviceRemovedGroupMsg;
	@FindBy(xpath = "	//XCUIElementTypeNavigationBar[@name='WiSilica_Lighting.GroupDetailView']/XCUIElementTypeButton[1]")
	private  WebElement backArrowBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'rv_settings')]/android.view.ViewGroup[6]/android.widget.TextView")
	private WebElement clickOnAboutBtn;
	
	
	@FindBy(xpath = "//*[contains(@content-desc,'More')]")
	private  List <WebElement>   clickOnMoreBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'tvVersion')]")
	private List <WebElement>  versionData;
	
	


	public String getVersionData()
	{
		FunctionLibrary.isElementPresentByElements(versionData);
		String temp=versionData.get(0).getText();
		return temp;
	}
	public void clickOnMoreBtn()
	{
		    FunctionLibrary.clickElement(driver, clickOnMoreBtn);
			
	}
	public void clickOnAboutBtn()
	{
		clickOnAboutBtn.click();
	}
	public void changeIntensity(double number)
	{
		try {
			int count=0;
			double currNum=Double.parseDouble(geIntencityCurrentNumber.getText());
			while(!(currNum==number)&&currNum>number)
			{
				minusBtn.click();
				currNum=Float.parseFloat(geIntencityCurrentNumber.getText());
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

	public HomePage(WebDriver driver) {


		super(driver);
	}
	public String addDevice()
	{
		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.clickElement(driver, addDevice);

		Actions act=new Actions(driver);
		act.doubleClick(searchHere).build().perform();

		FunctionLibrary.isElementPresentByElements(radioBtnOnAddPage);
		radioBtnOnAddPage.get(0).click();

		addBtnOnAddpage.click();

		FunctionLibrary.isElementPresentByElements(skipLink);
		skipLink.get(0).click();

		FunctionLibrary.isElementPresentByElements(pairSuccessMsg);
		return pairSuccessMsg.get(0).getText();

	}
	public boolean isDeviceListed(String deviceName)
	{
		String xpath="//*[contains(@text,'"+deviceName+"')]";
		List<WebElement> list=driver.findElements(By.xpath(xpath));
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)));
		return list.size()>0;
	}
	public boolean isDeviceListed(String deviceName,int time)
	{
		System.out.println();
		
		String xpath="//*[contains(@resource-id,'group_view')]//*[contains(@text,'"+deviceName+"')]";
		List<WebElement> list=driver.findElements(By.xpath(xpath));
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)),time);
		System.out.println(list.size());
		return list.size()>0;
	}
	
	public void searchDevice(String searchText)
	{
		FunctionLibrary.isElementPresentByElements(searchTextField);
		searchTextField.get(0).clear();
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.sendKeys(driver, searchTextField.get(0), searchText);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	public void searchDevice(String searchText,int time)
	{
		FunctionLibrary.isElementPresentByElements(searchTextField);
		FunctionLibrary.sendKeys(driver, searchTextField.get(0), searchText);
	}
	public void clickSunSettings()
	{
		sunBtn.click();
	}
	public void switchOnDevice()
	{
		FunctionLibrary.isElementPresentByElements(onBtn);
		onBtn.get(0).click();
	}
	public void switchOffDevice()
	{

		FunctionLibrary.isElementPresentByElements(offBtn);
		offBtn.get(0).click();

	}
	
	
	//Suite 3 changes
	
	@FindBy(name = "Zone")
	private WebElement zoneBtn;
	@FindBy(xpath = "//*[@text='ADD']")
	private WebElement addButton;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Rename']")
	private WebElement renameButton;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Delete']")
	private WebElement deleteButton;
	@FindBy(xpath = "//*[@name='Add Zone']/..//XCUIElementTypeTextField")
	private WebElement zoneNameEnter;
	@FindBy(xpath = "//*[@name='Edit Zone']/..//XCUIElementTypeTextField")
	private WebElement eitZoneInp;
	@FindBy(name = "camera icon")
	private WebElement cameraIcon;
	public List <WebElement> getZoneNameBtn(String zoneName)
	{
		return driver.findElements(By.xpath("//*[@text='"+zoneName+"']"));
	}
	public void clickZone()
	{
		FunctionLibrary.clickElement(driver, zoneBtn);
	}
	public void clickOnZone(String zone)
	{
		FunctionLibrary.isElementPresentByElements(getZoneNameBtn(zone),10);
		if(getZoneNameBtn(zone).size()>1)
		{
			getZoneNameBtn(zone).get(1).click();
		}
		else
		{
			FunctionLibrary.clickElement(driver, getZoneNameBtn(zone));
		}
	}
	public void createZone(String zone)
	{
		addButton.click();
		zoneNameEnter.sendKeys(zone);
		FunctionLibrary.clickElement(driver,OKbutton);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	
	public void editZone(String zone)
	{
		renameButton.click();
		eitZoneInp.clear();
		eitZoneInp.sendKeys(zone);
		FunctionLibrary.clickElement(driver,OKbutton);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	public void deleteZone(String zone)
	{
		deleteButton.click();
		FunctionLibrary.clickElement(driver,YESbutton);
		FunctionLibrary.syncTillTimePeriod(20);
	}
	
	public boolean validateZone(String zone,int time)
	{
		return FunctionLibrary.isElementPresentByElements(getZoneNameBtn(zone),time);
	}
	
	
	
	//------------------------SUITE 4 --------------------------------------------
	
	@FindBy(xpath = "//*[@text='User Management']")
	private WebElement userManagementBtn;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@content-desc='Privileges']")
	private WebElement privilagesBtn;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@content-desc='Basic Details']")
	private List<WebElement> basicDetailsBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'et_user')]")
	private WebElement userNameTxt;
	@FindBy(xpath = "//*[contains(@resource-id,'et_mail')]")
	private List<WebElement> emailTxt;
	@FindBy(xpath = "//*[contains(@resource-id,'et_password')]")
	private WebElement passwordTxt;
	@FindBy(xpath = "//*[contains(@resource-id,'et_conf_password')]")
	private List<WebElement> conpasswordText;
	@FindBy(xpath = "//*[contains(@resource-id,'btn_signin')]")
	private WebElement nextButton;
	@FindBy(xpath = "//*[@text='Save Changes']")
	private WebElement saveButton;
	@FindBy(xpath = "//*[contains(@resource-id,'edtext_searchbar')]")
	private List<WebElement> searchIcon;
	
	@FindBy(xpath = "//*[contains(@resource-id,'cl_view')]/..//android.widget.TextView")
	private List<WebElement> emailLabel;
	
	
	@FindBy(xpath = "//*[@text='EDIT']")
	private  List<WebElement> editButton;
	
	public boolean validateEmailValue(String email)
	{
		System.out.println();
		FunctionLibrary.isElementPresentByElements(emailLabel);
		String temp=emailLabel.get(0).getAttribute("text");
		if(temp.equals(email))
		{
			return true;
		}
		return false;
	}
	
	
	@FindBy(xpath = "//*[@text='Control Panel']")
	private  List<WebElement> controlPanelLabel;
	
	public boolean validateControlPanelDisplayed(int time)
	{
		return FunctionLibrary.isElementPresentByElements(controlPanelLabel,time);
	}
	public void clickOnEditBtn()
	{
		FunctionLibrary.clickElement(driver, editButton);
	}
	
	public void clickOnUserManagement()
	{
		FunctionLibrary.clickElement(driver, userManagementBtn);
	}
	public void clickOnBasicDetails()
	{
		FunctionLibrary.clickElement(driver, basicDetailsBtn);
	}
	public void clickOnNextBtn()
	{
		nextButton.click();
	}
	public void clickOnSavetBtn()
	{
		FunctionLibrary.clickElement(driver, saveButton);
		FunctionLibrary.syncTillTimePeriod(30);
	}
	public void clickOnAddBtn()
	{
		addButton.click();
	}
	public void creaeBasicDetails(String uname,String email,String pwd)
	{
		clickOnBasicDetails();
		FunctionLibrary.sendKeys(driver, userNameTxt, uname);
		FunctionLibrary.sendKeys(driver, emailTxt, email);
		FunctionLibrary.sendKeys(driver, passwordTxt, pwd);
		FunctionLibrary.sendKeys(driver, conpasswordText, pwd);
		clickOnNextBtn();
		FunctionLibrary.swipeScreen("UP",driver);
		clickOnSavetBtn();
		FunctionLibrary.clickElement(driver, OKbutton);
	}
	
	@FindBy(xpath = "//*[@text='Control Panel']/..//*[contains(@resource-id,'radioButton')]")
	private  List<WebElement> selectControlPanel;
	public void clickOnPrivilagesBtn()
	{
		FunctionLibrary.clickElement(driver, privilagesBtn);
	}
	public void clickOnControlSelectBtn()
	{
		FunctionLibrary.clickElement(driver, selectControlPanel);
	}
	
	@FindBy(xpath = "//*[@text='User Edited']")
	private  List<WebElement> useEdited;
	public boolean updateBasicDetails(String email,String pwd)
	{
		clickOnBasicDetails();
		FunctionLibrary.sendKeys(driver, passwordTxt, pwd);
		FunctionLibrary.sendKeys(driver, conpasswordText, pwd);
		clickOnNextBtn();
		FunctionLibrary.swipeScreen("UP",driver);
		clickOnSavetBtn();
		if(FunctionLibrary.isElementPresentByElements(useEdited))
		{
			FunctionLibrary.clickElement(driver, OKbutton);
			return true;
		}
		return false;
	}
	public void updatePrivilageDetails()
	{
		clickOnNextBtn();
		clickOnControlSelectBtn();
		FunctionLibrary.swipeScreen("UP",driver);
		clickOnSavetBtn();
		FunctionLibrary.clickElement(driver, OKbutton);
	}
	public void searchUser(String uname)
	{
			FunctionLibrary.isElementPresentByElements(searchIcon);
			FunctionLibrary.sendKeys(driver, searchIcon.get(0), uname);
	}
	public boolean validateUserIsCreated(String uname)
	{
		return FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath("//*[contains(@text,'"+uname+"')]")));
		
	}
	public void clickOnUser(String uname)
	{
		driver.findElement(By.xpath("//*[contains(@text,'"+uname+"')]"));
		
	}
	
	public void clickOnRightArrow(String uname)
	{
		List<WebElement> list=driver.findElements(By.xpath("//*[contains(@text,'"+uname+"')]/..//*[contains(@resource-id,'imageView9')]"));
		FunctionLibrary.clickElement(driver, list);
	}
	
	//SUITE 4
	
	@FindBy(xpath = "//XCUIElementTypeImage[@name='Profile']")
	private List <WebElement> profileBtn;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Change Email ID']")
	private List <WebElement> changeEmailBtn;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='Change Password']")
	private List <WebElement> changePasswordBtn;
	@FindBy(xpath = "//XCUIElementTypeButton[@name='GENERATE OTP']")
	private List <WebElement> generateOtpBtn;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='New Email ID']/..//XCUIElementTypeTextField")
	private List <WebElement> newEmailIDInp;
	@FindBy(xpath = "//*[@name='Old Password']/..//XCUIElementTypeSecureTextField")
	private List <WebElement> oldPasswordInp;
	@FindBy(xpath = "//*[@name='New Password']/..//XCUIElementTypeSecureTextField")
	private List <WebElement> newPasswordInp;
	@FindBy(xpath = "//*[@name='Confirm New Password']/..//XCUIElementTypeSecureTextField")
	private List <WebElement> confNewPwdInp;
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Password changed successfully']")
	private List <WebElement> passwordSuccessMessage;
	@FindBy(name = "Set")
	private WebElement settBtn;
	@FindBy(name = "Ok")
	private WebElement okBtn;
	@FindBy(name = "Home Logo")
	private List <WebElement>  homoLogo;
	public boolean validateHomePageDisplayed()
	{
		return FunctionLibrary.isElementPresentByElements(homoLogo,10);
	}

	public void clickInProfileBtn()
	{
		FunctionLibrary.clickElement(driver, profileBtn);
	}
	public void clickOnchangeEmailId()
	{
		FunctionLibrary.clickElement(driver, changeEmailBtn);
	}
	public void clickOnchangePasswordBtn()
	{
		FunctionLibrary.clickElement(driver, changePasswordBtn);
	}
	public void changeEmailId(String newEmailId)
	{
		FunctionLibrary.sendKeys(driver, newEmailIDInp,newEmailId);
		FunctionLibrary.clickElement(driver, generateOtpBtn);
	}
	public boolean changePasssword(String oldPassword,String newPassword)
	{
		FunctionLibrary.sendKeys(driver, oldPasswordInp,oldPassword);
		FunctionLibrary.sendKeys(driver, newPasswordInp,newPassword);
		FunctionLibrary.sendKeys(driver, confNewPwdInp,newPassword);
		FunctionLibrary.clickElement(driver, settBtn);
		if(FunctionLibrary.isElementPresentByElements(passwordSuccessMessage))
		{
			okBtn.click();
			return true;
		}
		return false;
	}
	
	
	
		
	
	





	
	

	

	
}
