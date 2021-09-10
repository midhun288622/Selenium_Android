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

public class SpacesPage extends BasePage{

	
	public SpacesPage(WebDriver driver) {


		super(driver);
	}
	@FindBy(xpath = "//*[contains(@resource-id,'btn_delete')]")
	private  List <WebElement> deleteIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'toolbar')]//android.widget.ImageButton")
	private  WebElement backArrowBtn;
	@FindBy(xpath = "//*[@text='Spaces']")
	private WebElement spaceIcon;
	@FindBy(xpath = "//*[contains(@resource-id,'scan_icon')]")
	public List <WebElement>  addBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt')]")
	private List <WebElement> enterSpaceName;
	@FindBy(xpath = "//*[@text='OK']")
	private List <WebElement>  okButton;
	
	public List <WebElement> getEditsuccessSettingsMsg()
	{
		return driver.findElements(By.xpath("//*[contains(@name,'Space name edited successfully')]"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'settings')]")
	private List <WebElement> spaceSettings;
	
	public List <WebElement> getsuccessSettingsMsg()
	{
		return driver.findElements(By.xpath("//*[contains(@text,'Space created successfully')]"));
	}
	@FindBy(xpath = "//*[contains(@resource-id,'btn_camera')]")
	private  List <WebElement>   imageChangeBtn;
	public void clickSpaceSettings()
	{
		FunctionLibrary.clickElement(driver, spaceSettings);
	}
	public void clickOnSpaceIcon()
	{
		spaceIcon.click();
		ExtentTestManager.logTestResult("INFO","Navigate tp space page");
	}
	public void clickBackArrow()
	{
		backArrowBtn.click();
	}
	@FindBy(xpath = "//*[contains(@resource-id,'btn_rename')]")
	private List<WebElement>  EditIcon;

	public void clickOnEditGroupIcon()
	{
		FunctionLibrary.clickElement(driver, EditIcon);
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
	public boolean changeImage1()
	{
		FunctionLibrary.clickElement(driver, chooseAppGalary);
		FunctionLibrary.clickElement(driver, imageSelect);
		FunctionLibrary.clickElement(driver, OKbutton);
		return true;
	}
	public void clickOnChangeImageBtn()
	{
		FunctionLibrary.clickElement(driver, imageChangeBtn);
	}
	public boolean deleteSpace()
	{
		FunctionLibrary.clickElement(driver, deleteIcon);
		if(FunctionLibrary.isElementPresentByElements(OKbutton))
		{
			OKbutton.get(0).click();
			FunctionLibrary.syncTillTimePeriod(10);
			return true;
		}

		return false;
	}
	public boolean createSpace(String spaceName)
	{
		FunctionLibrary.clickElement(driver, addBtn);
		FunctionLibrary.sendKeys(driver, enterSpaceName, spaceName);
		FunctionLibrary.clickElement(driver, okButton);
		if(FunctionLibrary.isElementPresentByElements(getsuccessSettingsMsg()))
		{
				deviceAddedOkBtn.get(0).click();
				return true;
		}
		return false;
	}
	
	public void editSpace(String spaceName)
	{
		FunctionLibrary.sendKeys(driver, enterSpaceName, spaceName);
		FunctionLibrary.clickElement(driver, okButton);
		FunctionLibrary.syncTillTimePeriod(100);
	}
	
	@FindBy(xpath = "//*[contains(@resource-id,'search_bar')]")
	private  List <WebElement> searchInSpace;
	public void searchInSpace(String groupName)
	{
		FunctionLibrary.sendKeys(driver, searchInSpace, groupName);
	}
	public boolean isDeviceListed(String deviceName,int time)
	{
		System.out.println();
		String xpath="//*[contains(@resource-id,'recycelrview')]//*[@text='"+deviceName+"']";
		List<WebElement> list=driver.findElements(By.xpath(xpath));
		FunctionLibrary.isElementPresentByElements(driver.findElements(By.xpath(xpath)),time);
		System.out.println(list.size());
		return list.size()>0;
	}
	@FindBy(xpath = "//*[contains(@resource-id,'buttonAdd')]")
	private  List <WebElement> plusBlueBtn;
	@FindBy(xpath = "(//*[contains(@resource-id,'parent')]//android.widget.TextView)[2]")
	private  List <WebElement> listDeviceCount;
	public String getGroupCount(String space)
	{
		FunctionLibrary.isElementPresentByElements(listDeviceCount);
		return listDeviceCount.get(0).getText();
	}
	public void addGroupToSpace(String space)
	{
		FunctionLibrary.clickElement(driver, plusBlueBtn);
		FunctionLibrary.syncTillTimePeriod(100);
		FunctionLibrary.clickElement(driver, backArrowBtn);

	}
	@FindBy(xpath = "//*[contains(@resource-id,'power_on')]")
	private  List <WebElement> sunBtn;
	public void clickSunSettings()
	{
		FunctionLibrary.clickElement(driver, sunBtn);
	}
	
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_red')]")
	private  List<WebElement> rgbRed;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_green')]")
	private  WebElement rgbGreen;
	@FindBy(xpath = "//*[contains(@resource-id,'edt_txt_blue')]")
	private  WebElement rgbBlue;
	
	public void editRGB(String red,String green,String blue)
	{
		FunctionLibrary.isElementPresentByElements(rgbRed);
		FunctionLibrary.sendKeys(driver, rgbRed, red);
		FunctionLibrary.sendKeys(driver, rgbGreen, green);
		FunctionLibrary.sendKeys(driver, rgbBlue, blue);
		FunctionLibrary.clickElement(driver, okButton);
		FunctionLibrary.syncTillTimePeriod(20);
		FunctionLibrary.clickElement(driver, okButton);
		
	}
	public boolean validateRGB(String red,String green,String blue)
	{
		FunctionLibrary.isElementPresentByElements(rgbRed);
		String value1=rgbRed.get(0).getAttribute("text");
		String value2=rgbGreen.getAttribute("text");
		String value3=rgbBlue.getAttribute("text");
		if(value1.equals(red))
		{
			if(value2.equals(green))
			{
				if(value3.equals(blue))
				{
					return true;
				}
				
			}
		}
		return false;
	}
	
	@FindBy(xpath = "//*[contains(@resource-id,'textView11')]")
	private WebElement intencityCount;

	@FindBy(xpath = "//*[contains(@resource-id,'txt_cool')]")
	private WebElement colorCount;
	public String getIntencityVlue()
	{
		//String inte=intencityCount.getAttribute("text").replace("%","");
		String inte=intencityCount.getText();
		return inte;
	}

	public String getColorVlue()
	{
		//	String colo=colorCount.getAttribute("text");
		String colo=colorCount.getText().replace("K","");
		return colo;
	}

}
