package com.scs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.scs.commons.BasePage;
import com.scs.commons.FunctionLibrary;
import com.scs.report.ExtentTestManager;

public class Login_Page extends BasePage{


	@FindBy(xpath = "//*[contains(@resource-id,'txt_frogetpswd')]")
	private List <WebElement> forgotPassLink;

	@FindBy(xpath = "//*[contains(@resource-id,'et_username')]")
	private WebElement username;

	@FindBy(xpath = "//*[contains(@resource-id,'et_password')]")
	private WebElement password;

	@FindBy(xpath = "//*[contains(@resource-id,'btn_signin')]")
	private  List <WebElement>  loginButton;


	@FindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	private List<WebElement> okPopup;
	@FindBy(xpath = "//XCUIElementTypeTextField[@value ='Search']")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@text='Sign Out']")
	private List <WebElement> signOutBtn;

	@FindBy(xpath = "//*[contains(@resource-id,'textinput_error')]")
	private List <WebElement> errorMessage1;


	public List <WebElement>  getForgotLink()
	{
		return forgotPassLink;
	}

	public boolean isLoginButtonPresent()
	{
		if(FunctionLibrary.isElementPresentByElements(loginButton,10))
		{
			return true;
		}
		return false;
	}


	public WebElement getUserElement(String userName)
	{
		return driver.findElement(By.xpath("//h5[text()='"+userName+"']"));
	}

	String flag = "NA";

	public Login_Page(WebDriver driver) {


		super(driver);
	}



	public void loginToApp(String uname,String pwd) {


		if(FunctionLibrary.isElementPresentByElements(forgotPassLink,10))
		{
			ExtentTestManager.logTestResult("INFO", "Navigate to login page");

			ExtentTestManager.logTestResult("INFO", "Enter username and password");
			FunctionLibrary.sendKeys(driver, username, uname);
			FunctionLibrary.sendKeys(driver, password, pwd);

			ExtentTestManager.logTestResult("INFO", "Click on login button");

			loginButton.get(0).click();

		}
		ExtentTestManager.logTestResult("INFO","User is logged in");
	}

	public boolean validate_errorMessage1() {


		if(FunctionLibrary.isElementPresentByElements(loginButton,2))

		{
			ExtentTestManager.logTestResult("INFO", "Do not fill any fields");

			ExtentTestManager.logTestResult("INFO", "Click on login button");
			username.clear();
			username.sendKeys("efsefswefswef");
			password.sendKeys("asfdasfasf");
			loginButton.get(0).click();
			FunctionLibrary.isElementPresentByElements(errorMessage1,5);
			if(errorMessage1.get(0).getAttribute("text").equals("Invalid password, should contain at least 1 character, 1 number"))
			{
				return true;
			}

		}
		return false;
	}

	@FindBy(xpath = "//*[@text='Invalid username']")
	public List <WebElement> invaliUserName;
	@FindBy(xpath = "//*[@text='Invalid user password']")
	public List <WebElement> invalidPassword;

	public boolean validate_InvalidUsername(String uname,String passwordStr) {
		if(FunctionLibrary.isElementPresentByElements(loginButton,2))
		{
			ExtentTestManager.logTestResult("INFO", "Do not fill any fields");
			FunctionLibrary.sendKeys(driver, username, uname);
			ExtentTestManager.logTestResult("INFO", "Click on login button");
			password.sendKeys(passwordStr);
			FunctionLibrary.clickElement(driver, loginButton);
			return FunctionLibrary.isElementPresentByElements(invaliUserName,20);

		}
		return false;
	}
	public boolean validate_InvalidPassword(String uname,String passwordStr) {


		if(FunctionLibrary.isElementPresentByElements(loginButton,2))
		{
			ExtentTestManager.logTestResult("INFO", "Do not fill any fields");
			FunctionLibrary.sendKeys(driver, username, uname);
			ExtentTestManager.logTestResult("INFO", "Click on login button");
			password.sendKeys(passwordStr);
			FunctionLibrary.clickElement(driver, loginButton);
			return FunctionLibrary.isElementPresentByElements(invalidPassword,20);

		}
		return false;
	}

	public void clickOkBtn()
	{
		if(FunctionLibrary.isElementPresentByElements(okPopup,5))
		{
			FunctionLibrary.clickElement(driver, okPopup.get(0));
		}
	}
	public void logout() {

			ExtentTestManager.logTestResult("INFO", "Navigate to login page");
			FunctionLibrary.clickElement(driver, signOutBtn);
			FunctionLibrary.clickElement(driver, OKbutton);
		

	}
	public boolean validateLoginPageDisplayed()
	{
		return FunctionLibrary.isElementPresentByElements(loginButton,10);
	}



	//Suite 2

	@FindBy(xpath = "//*[contains(@resource-id,'text1')]")
	private WebElement languageBtn;
	@FindBy(xpath = "//*[contains(@resource-id,'btn_signin')]")
	private List<WebElement> sigiInBtn;
	@FindBy(xpath = "//*[contains(@text,'English')]")
	private WebElement englishBtn;
	@FindBy(xpath = "//*[contains(@text,'Italian')]")
	private WebElement italianBtn;

	public boolean changeItaliLanguage()
	{
		FunctionLibrary.clickElement(driver, languageBtn);
		FunctionLibrary.syncTillTimePeriod(50);
		FunctionLibrary.clickElement(driver, driver.findElements(By.xpath("//*[contains(@text,'Italian')]")));
		FunctionLibrary.clickElement(driver,deviceAddedOkBtn);
		waitToLoadProgressView();
		FunctionLibrary.isElementPresentByElements(sigiInBtn);
		String s=sigiInBtn.get(0).getAttribute("name").trim();
		if(s.equals("Registrati"))
		{
			return true;
		}
		return false;
	}

	public boolean changeEnglishLanguage()
	{
		FunctionLibrary.clickElement(driver, languageBtn);
		FunctionLibrary.syncTillTimePeriod(50);
		FunctionLibrary.clickElement(driver, driver.findElements(By.xpath("//*[contains(@text,'English')]")));
		FunctionLibrary.clickElement(driver,okbutton);
		waitToLoadProgressView();
		FunctionLibrary.isElementPresentByElements(sigiInBtn);
		String s=sigiInBtn.get(0).getAttribute("text");
		if(s.equals("Sign In"))
		{
			return true;
		}
		return false;
	}

}
