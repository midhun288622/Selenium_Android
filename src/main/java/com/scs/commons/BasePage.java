package com.scs.commons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {


	protected WebDriver driver;
	protected ElementHandler elementHandler;
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementHandler=new ElementHandler(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	@FindBy(xpath = "//*[contains(@resource-id,'toolbar')]//android.widget.ImageButton")
	public List<WebElement> scenScheduleBackBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'scan_icon')]")
	public List<WebElement> addBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'progressView')]")
	private List<WebElement> progressView;
	@FindBy(xpath = "//*[@text='OK']")
	public List <WebElement>  deviceAddedOkBtn;
	@FindBy(xpath = "//*[@text='OK']")
	public List <WebElement>  OKbutton;
	
	@FindBy(xpath = "//*[@text='ok']")
	public List <WebElement>  okbutton;
	
	@FindBy(xpath = "//*[@text='Set']")
	public List <WebElement>  setBtn;
	@FindBy(xpath = "//*[@text='Save']")
	public List <WebElement>  saveBtn;
	@FindBy(xpath = "//*[@text='YES']")
	public List <WebElement>  YESbutton;
	@FindBy(xpath = "//*[@text='Yes']")
	public List <WebElement>  yesBtn;
	@FindBy(xpath = "//*[@text='Operation success']")
	public List <WebElement> operationSuccessMessage;
	
	@FindBy(xpath = "//*[@text='Group deleted']")
	public List <WebElement> grupDeletedMessage;
	
	public boolean validateOperationSuccessMessage()
	{
		return FunctionLibrary.isElementPresentByElements(operationSuccessMessage);
	}
	@FindBy(xpath = "//*[@text='Backup Success']")
	public List <WebElement> backupSuccessMessage;
	
	@FindBy(xpath = "//*[@text='Scene scheduled successfully']")
	public List <WebElement> sceneScheduleSuccessull;
	
	public boolean waitToLoadProgressView()
	{
		return FunctionLibrary.isElementNotPresentByElements(progressView);
	}
	public List <WebElement> getTextElement(String text)
	{
		return driver.findElements(By.xpath("//*[@text='"+text+"']"));
	}
}
