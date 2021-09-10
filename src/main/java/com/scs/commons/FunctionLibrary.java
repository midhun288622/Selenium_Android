package com.scs.commons;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jidesoft.icons.IconSet.Direction;
import com.scs.report.ExtentTestManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import jxl.read.biff.BiffException;

/**
 * Customized class for common re usable methods.
 * 
 * @author MXC0RO7
 */
public class FunctionLibrary {

	private static final Logger LOGGER = LoggerFactory.getLogger(FunctionLibrary.class);



	public static boolean selectElementByValue(WebElement element,String value)
	{
		boolean flag=false;
		Select select=new Select(element);
		if(!select.getFirstSelectedOption().getText().equals(value))
		{
			select.selectByVisibleText(value);
			flag=true;

		}
		return flag;
	}

	public static String getCurrentDateAndTime() 
	{
		return new SimpleDateFormat("yyyy_MM_dd HH:mm:SS").format(Calendar.getInstance().getTime());
	}

	public static void fileWriter(String fileName,String fileContent) 
	{
		try {
			String path=getUserHomePath()+"/Data";	 
			makeDirectory(path);
			FileWriter fileWriter = new FileWriter(path+"/"+fileName+".txt");
			fileWriter.write(fileContent);
			fileWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String fileReader(String fileName) 
	{
		String temp="";
		try {
			String path=getUserHomePath()+"/Data";	 
			FileReader reader=new FileReader(new File(path+"/"+fileName+".txt"));

			int i;    
			while((i=reader.read())!=-1)   
				temp=temp+(char)i;
			reader.close();   

			System.out.println(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public static  String getRandomNumber(int length) {

		String NUMBER = "123456789";
		String DATA_FOR_RANDOM_STRING =  NUMBER;
		SecureRandom random = new SecureRandom();

		if (length < 1) throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);
		}

		return sb.toString();

	}

	public static  String getRandomString(int length,String type) {

		String NUMBER = "123456789";
		String Cha="abcdefghijklmnopqrstuvwxyz";
		String DATA_FOR_RANDOM_STRING="";
		if(type.equals("Alpha"))
		{
			DATA_FOR_RANDOM_STRING =  NUMBER+Cha;
		}
		else if(type.equals("STRING"))
		{
			DATA_FOR_RANDOM_STRING=Cha;
		}
		else if(type.equals("NUMBER"))
		{
			DATA_FOR_RANDOM_STRING=NUMBER;
		}
		else
		{
			System.out.println("Please select the option");
		}

		SecureRandom random = new SecureRandom();

		if (length < 1) throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);
		}

		return sb.toString();

	}
	/**
	 * Function to wait until meets an expectation or for a specified duration where
	 * condition, el, frameID, text are optional arguments but value cannot be null.
	 * So wherever not applicable set the corresponding value as notApp. t is time
	 * to wait in Seconds.
	 */
	public static void syncTill(WebDriver driver, String condition, WebElement el, int frameId, int t, String text) {

		WebDriverWait wait = new WebDriverWait(driver, t);

		if (condition.equalsIgnoreCase("visibilityOfElement")) {
			wait.until(ExpectedConditions.visibilityOf(el));
		} else if (condition.equalsIgnoreCase("alertPresent")) {
			wait.until(ExpectedConditions.alertIsPresent());
		} else if (condition.equalsIgnoreCase("invisibilityofElement")) {
			wait.until(ExpectedConditions.invisibilityOf(el));
		} else if (condition.equalsIgnoreCase("elementToBeClickable")) {
			wait.until(ExpectedConditions.elementToBeClickable(el));
		} else if (condition.equalsIgnoreCase("elementToBeSelected")) {
			wait.until(ExpectedConditions.elementSelectionStateToBe(el, true));
		} else if (condition.equalsIgnoreCase("frameAvailable")) {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
		} else if (condition.equalsIgnoreCase("textToBePresent")) {
			wait.until(ExpectedConditions.textToBePresentInElement(el, text));
		} else {

			try {
				Thread.sleep(t * 1000);
			} catch (InterruptedException e) {
				LOGGER.error("Element is not present");
			}

		}

	}

	public static boolean isThisDateValid(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			//e.printStackTrace();
			return false;
		}

		return true;
	}


	public void touchActions(WebElement element,WebDriver driver)
	{
		TouchActions action = new TouchActions(driver);
		action.singleTap(element);
		action.perform();

	}
	public static boolean waitForElementLoad(WebDriver driver,By element) {
		boolean result = false;
		int attempts = 0;
		while(attempts < 50) {
			try {
				driver.findElement(element).isDisplayed();
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
				}
			}
			attempts++;
		}
		return result;
	}
	public static boolean isElementPresentByElements(List<WebElement> elements){
		boolean flag=false;
		for(int i=0;i<50;i++)
		{
			if(elements.size()>0)
			{
				flag=true;
				break;
			}
			else
			{
				
				FunctionLibrary.syncTillTimePeriod(1);
			}
		}

		return flag;
	}
	public static boolean isElementNotPresentByElements(List<WebElement> elements){
		boolean flag=false;
		for(int i=0;i<50;i++)
		{
			if(elements.size()==0)
			{
				flag=true;
				break;
			}
			else
			{
				
				FunctionLibrary.syncTillTimePeriod(1);
			}
		}

		return flag;
	}

	public static void scrollTo(WebDriver driver,WebElement element1)
	{

		RemoteWebElement element = (RemoteWebElement)element1;
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID); // Only for ‘scroll in element’
		scrollObject.put("direction", "up");
		((JavascriptExecutor) driver).executeScript("mobile:scroll", scrollObject);
	}
	public static void swipeElementIOS(WebElement el, String dir,WebDriver driver) {
		try {
			System.out.println("seElementIOS(): dir: '" + dir + "'"); // always log your actions

			//el=(MobileElement)el;
			// Animation default time:
			//  - Android: 300 ms
			//  - iOS: 200 ms
			// final value depends on your app and could be greater
			final int ANIMATION_TIME = 200; // ms

			final int PRESS_TIME = 500; // ms

			// init screen variables
			Dimension dims = driver.manage().window().getSize();
			Rectangle rect = el.getRect();

			// check element overlaps screen
			if (rect.x >= dims.width || rect.x + rect.width <= 0
					|| rect.y >= dims.height || rect.y + rect.height <= 0) {
				throw new IllegalArgumentException("swipeElementIOS(): Element outside screen");
			}

			// init borders per your app screen
			// or make them configurable with function variables
			int leftBorder, rightBorder, upBorder, downBorder;
			leftBorder = 0;
			rightBorder = 0;
			upBorder = 0;
			downBorder = 0;

			// find rect that overlap screen
			if (rect.x < 0) {
				rect.width = rect.width + rect.x;
				rect.x = 0;
			}
			if (rect.y < 0) {
				rect.height = rect.height + rect.y;
				rect.y = 0;
			}
			if (rect.width > dims.width)
				rect.width = dims.width;
			if (rect.height > dims.height)
				rect.height = dims.height;

			PointOption pointOptionStart, pointOptionEnd;
			switch (dir) {
			case "DOWN": // from up to down
				pointOptionStart = PointOption.point(rect.x + rect.width / 2,
						rect.y + upBorder);
				pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
						rect.y + rect.height - downBorder);
				break;
			case "UP": // from down to up
				pointOptionStart = PointOption.point(rect.x + rect.width / 2,
						rect.y + rect.height - downBorder);
				pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
						rect.y + upBorder);
				break;
			case "LEFT": // from right to left
				pointOptionStart = PointOption.point(rect.x + rect.width - rightBorder,
						rect.y + rect.height / 2);
				pointOptionEnd = PointOption.point(rect.x + leftBorder,
						rect.y + rect.height / 2);
				break;
			case "RIGHT": // from left to right
				pointOptionStart = PointOption.point(rect.x + leftBorder,
						rect.y + rect.height / 2);
				pointOptionEnd = PointOption.point(rect.x + rect.width - rightBorder,
						rect.y + rect.height / 2);
				break;
			default:
				throw new IllegalArgumentException("swipeElementIOS(): dir: '" + dir + "' NOT supported");
			}

			// execute swipe using TouchAction
			try {
				new TouchAction((PerformsTouchActions) driver)
				.press(pointOptionStart)
				// a bit more reliable when we add small wait
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
				.moveTo(pointOptionEnd)
				.release().perform();
			} catch (Exception e) {
				System.err.println("swipeElementIOS(): TouchAction FAILED\n" + e.getMessage());
				return;
			}

			// always allow swipe action to complete
			try {
				Thread.sleep(ANIMATION_TIME);
			} catch (InterruptedException e) {
				// ignore
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void swipeScreen(String dir,WebDriver driver) {

		FunctionLibrary.syncTillTimePeriod(10);
		// Animation default time:
		//  - Android: 300 ms
		//  - iOS: 200 ms
		// final value depends on your app and could be greater
		System.out.println( "");
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		int edgeBorder = 10; // better avoid edges
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = driver.manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		switch (dir) {
		case "DOWN": // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
			break;
		case "UP": // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
			break;
		case "LEFT": // center of left side
			pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
			break;
		case "RIGHT": // center of right side
			pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction((PerformsTouchActions) driver)
			.press(pointOptionStart)
			// a bit more reliable when we add small wait
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
			.moveTo(pointOptionEnd)
			.release().perform();
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}
	public static boolean isElementPresentByElements(List<WebElement> elements,int count){
		boolean flag=false;
		for(int i=0;i<count;i++)
		{
			if(elements.size()>0)
			{
				flag=true;
				break;
			}
			else
			{
			
				FunctionLibrary.syncTillTimePeriod(1);
			}
		}

		return flag;
	}
	public static boolean isElementPresentByElements(List<WebElement> elements,long time){
		boolean flag=false;
		for(int i=0;i<30;i++)
		{
			if(elements.size()>0)
			{
				flag=true;
				break;
			}
			else
			{
			
				FunctionLibrary.syncTillTimePeriod(time);
			}
		}

		return flag;
	}


	public static WebElement getTextElement(WebDriver driver,String text,String div)
	{
		WebElement element=driver.findElement(By.xpath("//"+div+"[text()='"+text+"']"));
		syncTill(driver, "elementToBeClickable", element, 0, 3, "NA");
		return element;
	}
	public static List<WebElement> getTextElements(WebDriver driver,String text,String div)
	{
		return driver.findElements(By.xpath("//"+div+"[text()='"+text+"']"));
	}

	public static void syncTillTimePeriod(long t) {
		try {
			Thread.sleep(t * 50);
		} catch (InterruptedException e) {
			LOGGER.error("Wait time out");
		}
	}

	/**
	 * This function used to switch to window based on window title.
	 */
	public static void switchToWindowTitle(WebDriver driver, String str) {
		String currentWindow = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(winHandle).getTitle().equals(str)) {
				break;
			} else {
				driver.switchTo().window(currentWindow);
			}
		}
	}

	/**
	 * This function used to press enter keyword from keyboard.
	 */
	public static void pressEnter(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * This function used to switch to frame.
	 */
	public static void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This function used to exit from the frame.
	 */
	public static void defaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This function used to send text to element.
	 */
	public static void sendKeys(WebDriver driver, WebElement element, String txt) {

		//syncTill(driver, "visibilityOfElement", element, 0, 1, "NA");
		element.clear();
		element.sendKeys(txt);
	}
	public static void sendKeys(WebDriver driver, List<WebElement> element, String txt) {

		//syncTill(driver, "visibilityOfElement", element, 0, 1, "NA");
		FunctionLibrary.isElementPresentByElements(element);
		element.get(0).clear();
		element.get(0).sendKeys(txt);
	}
	public static String getBoldText(String txt) {
		return  "<b>"+txt+"</b>";
	}

	public static void hideKeyboard(WebDriver driver)
	{
		((AppiumDriver<WebElement>) driver).hideKeyboard();
	}
	/**
	 * This function used to click an element.
	 */
	public static void clickElement(WebDriver driver, WebElement element) {

		syncTill(driver, "elementToBeClickable", element, 0, 1, "NA");
		element.click();

	}
	public static void clickElement(WebDriver driver, List<WebElement> element) {

			isElementPresentByElements(element);
			try {
				element.get(0).click();
			} catch (IndexOutOfBoundsException e) {
				throw new NoSuchElementException("Element not present");
			}
	}
	
	public static void clickElementWithTry(WebDriver driver, List<WebElement> element) {

		try {
			isElementPresentByElements(element);
			element.get(0).click();
		} catch (Exception e) {
		}
}
	
	public static void moveSeekBar(WebElement slider,WebDriver driver)
	{
		
		int start=slider.getLocation().getX();
		//Get width of seekbar
		int end=slider.getSize().getWidth();
		//get location of seekbar vertically
		int y=slider.getLocation().getY();

		// Select till which position you want to move the seekbar
		TouchAction action=new TouchAction((PerformsTouchActions) driver);

		//Move it will the end
		action.press(PointOption.point(start,y)).moveTo(PointOption.point(end,y)).release().perform();
	}
	
	/**
	 * This function used to send text to element and then press enter keyword.
	 */
	public static void sendElementThenPressEnter(WebDriver driver, WebElement element, String txt) {
		//syncTill(driver, "elementToBeClickable", element, 0, 3, "NA");
		element.clear();
		element.sendKeys(txt);
		syncTill(driver, "", null, 0, 1, "NA");
		pressEnter(driver);
	}

	/**
	 * This function used to click element using java script.
	 */
	public static void clickElementUsingJavaScript(WebDriver driver, WebElement element) {
		//syncTill(driver, "elementToBeClickable", element, 0, 2, "NA");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * This function used to splict the string based on split type.
	 */
	public static boolean isASubString(String statement, String substringstr) {
		if (statement.contains(substringstr) && (statement.contains("not"))) {
			return false;
		} else if ((statement.contains("not"))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This function is to get the element attribute value.
	 */
	public static String getTextByValueAttribute(WebElement ele, WebDriver driver) {
		String text = null;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			text = ele.getAttribute("value");
		} catch (NullPointerException ex) {
			text = (String) jse.executeScript("return arguments[0].text", ele);
			LOGGER.error("getTextByValueAttribute method failed");
		}
		return text;
	}

	/**
	 * This function is to compare dates.
	 * 
	 */
	public static boolean compareDates(String appDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yy");
		Date date = new Date();
		Date sysdate = formatter.parse(formatter.format(date));
		Date date1 = formatter.parse(appDate);
		if (sysdate.equals(date1)) {
			return true;
		}
		return false;
	}

	/**
	 * This function is to validate date.
	 */
	public static boolean validateDate(String actualDate) {
		boolean valid = false;
		try {
			valid = compareDates(actualDate);
		} catch (Exception e) {
			LOGGER.error("Validate date failed");
		}
		return valid;
	}

	/**
	 * This function is to get the integer value from a string.
	 */
	public static int extractInt(String str) {
		Matcher matcher = Pattern.compile("\\d+").matcher(str);
		if (!matcher.find()) {
			throw new NumberFormatException("For input string [" + str + "]");
		}
		return Integer.parseInt(matcher.group());
	}

	/**
	 * This function is to delete directory.
	 */
	public static void makeDirectory(String directoryName) {

		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}
	public static void deleteDirectory(String directoryName) {

		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		try {
			FileUtils.cleanDirectory(directory);
		} catch (IOException e) {
			LOGGER.error("Delete directroy failed");
		}
	}

	/**
	 * This function is to get user home path.
	 */
	public static String getUserHomePath() {
		String path = null;
		try {
			path = System.getProperty("user.dir");
			if (path.contains("target")) {
				path = path.replace("/target", "");
			}
		} catch (Throwable e) {
			LOGGER.error("get user home path failed");
		}
		return path;
	}

	/**
	 * This function is to read property from property file.
	 */
	public static String readPathFromPropertyFile(String pathKey) {

		String path = null;
		try {
			String pathKeyValue = readPropertyValue(pathKey);
			path = System.getProperty("user.dir");
			if (path.contains("target")) {
				path = path.replace("\\target", "");
			}
			path = path + pathKeyValue;
			path = path.trim();
		} catch (Throwable e) {
			LOGGER.error("Read path from property file failed");
		}
		return path;
	}
	/**
	 * This function is to read property from property file.
	 */
	public static String readPropertyFile(String pathKey) {
	
			return readPropertyValue(pathKey);
			
	}
	
	/**
	 * This function is to switch the driver from Current Window to newly opened
	 * Window.
	 */
	public static void switchToWindow(WebDriver driver) {
		try {
			for (String windowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);
			}
		} catch (NoSuchWindowException e) {
			LOGGER.error("Switch to window failed");
		}
	}

	/**
	 * This function is to enter the date.
	 */
	public static void enterDate(WebElement dateIcon, WebElement date, int n, WebDriver driver) {

		syncTill(driver, "visibilityOfElement", dateIcon, 0, 20, "notApp");
		dateIcon.click();
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime next2Week = today.plus(n, ChronoUnit.DAYS);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
		String stringDate = next2Week.format(formatter);
		date.sendKeys(stringDate);
	}

	public static String readPropertyValue(String key) {
		return ResourceBundle.getBundle("global").getString(key);
	}
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("global");
	}

	public static void assertStringEquals(String expected_text, String actual_text) {

		if (expected_text.equals(actual_text)) {
			ExtentTestManager.logTestResult("PASS",
					"Expected text " + expected_text + " and Actual text " + actual_text + " are same.");
		} else {
			ExtentTestManager.logTestResult("FAIL",
					"Expected text " + expected_text + " and Actual text " + actual_text + " are not same.");
		}

	}
	public static void assertStringNotEquals(String expected_text, String actual_text) {

		if (!expected_text.equals(actual_text)) {
			ExtentTestManager.logTestResult("PASS",
					"Expected text " + expected_text + " and Actual text " + actual_text + " are not same.");
		} else {
			ExtentTestManager.logTestResult("FAIL",
					"Expected text " + expected_text + " and Actual text " + actual_text + " are same.");
		}

	}
	public static void validateBooleanCondition(boolean flag, String message) {

		if (flag) {
			ExtentTestManager.logTestResult("PASS",
					message+"  is displayed successfully");
		} else {
			ExtentTestManager.logTestResult("FAIL",
					message+"  is not displayed successfully");
		}

	}

	public static void validateIsDisplayed(WebElement e, String msg) {
		if (e.isDisplayed()) {
			ExtentTestManager.logTestResult("PASS", msg);
		} else {
			ExtentTestManager.logTestResult("FAIL", "Element is not displayed");
		}

	}

	public static int searchForMatch(List<WebElement> allData, String actual) {

		int position = 0;

		for (WebElement ele : allData) {
			position++;
			String data = ele.getText();
			if (data.equalsIgnoreCase(actual)) {
				return position;
			}

		}
		return 0;

	}

	public static boolean searchForMatchText(List<WebElement> allData, String actual) {

		boolean flag=false;

		for (WebElement ele : allData) {

			try {
				String data = ele.getText();
				if (data.equalsIgnoreCase(actual)) {
					flag=true;
					break;
				}
			} catch (StaleElementReferenceException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		}
		return flag;

	}

	public static WebElement searchForMatchElement(List<WebElement> allData, String actual) {

		WebElement element=null;

		for (WebElement ele : allData) {

			try {
				String data = ele.getText();
				if (data.equalsIgnoreCase(actual)) {

					element=ele;
					break;
				}
			} catch (StaleElementReferenceException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		}
		return element;

	}

	public static boolean searchForMatchAttribute(List<WebElement> allData, String AttributeName,String value) {

		boolean flag=false;

		try {
			for (WebElement ele : allData) {
				try {
					String data = ele.getAttribute(AttributeName);
					if (data.equalsIgnoreCase(value)) {
						flag=true;
						break;
					}
				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return flag;

	}

	public static List<Map<String, String>>  getRunningMethods(String classsName,String methodName) throws BiffException, IOException
	{
		List<Map<String, String>> dataMap=new ArrayList<Map<String,String>>();
		Map<String, Map<String, String>> excelFileMap;
		try {

			TestDataHandler data=new TestDataHandler();
			excelFileMap=data.setMapData(classsName);
			String tc=methodName;
			Set<String> setKey=excelFileMap.keySet();
			for(String temp:setKey)
			{
				if(temp.contains(methodName))
				{
					if(temp.contains("_"))
					{
						temp=temp.split("_")[0];
					}
					dataMap.add(excelFileMap.get(temp));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;
	}

	public static boolean performRightArrowKey(WebDriver driver,String columnName)
	{
		boolean flag=true;
		try {
			Actions action=new Actions(driver);
			int count=0;
			try {
				driver.findElements(By.xpath("//div[contains(@id,'row-1-')]")).get(1).click();
			} catch (Exception e) {
			}
			boolean flag2=true;


			while(flag)
			{
				List<WebElement> list=driver.findElements(By.xpath("//button[contains(@id,'header-row')]"));
				flag2=FunctionLibrary.searchForMatchText(list, columnName);
				if(!flag2)
				{
					action.sendKeys(Keys.ARROW_RIGHT).build().perform();
				}
				else
				{
					if(flag2)
					{
						flag=false;
					}

				}
				count++;
				if(count==100)
				{
					flag=false;
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			LOGGER.error(e.getMessage());
		}
		return flag;
	}

	public static boolean performDownArrowKey(WebDriver driver,String data,String columnNum)
	{
		System.out.println();
		boolean flag=true;
		try {
			Actions action=new Actions(driver);
			int count=0;
			try {
				driver.findElements(By.xpath("//div[contains(@id,'-cell-"+columnNum+"')]")).get(1).click();
			} catch (Exception e) {
			}
			WebElement flag2=null;


			while(flag)
			{
				List<WebElement> list=driver.findElements(By.xpath("//div[contains(@id,'-cell-"+columnNum+"')]"));
				flag2=FunctionLibrary.searchForMatchElement(list, data);
				if(flag2==null)
				{
					action.sendKeys(Keys.ARROW_DOWN).build().perform();

				}

				else
				{
					try {
						action.moveToElement(flag2).build().perform();
						flag=false;
						break;
					} catch (MoveTargetOutOfBoundsException e) {
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
					}

				}
				count++;
				if(count==100)
				{
					flag=false;
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			LOGGER.error(e.getMessage());
		}
		return flag;
	}

	public static boolean scrollToCheckAllBox(WebDriver driver)
	{

		boolean flag=true;

		Actions action=new Actions(driver);
		int count=0;
		boolean flag2=true;
		while(flag)
		{

			List<WebElement> list=driver.findElements(By.xpath("//div[@class='heading col-10 p-0']/.."));
			flag2=FunctionLibrary.searchForMatchAttribute(list, "id", "row-0-cell-0");

			if(!flag2)
			{
				action.sendKeys(Keys.ARROW_LEFT).build().perform();
			}
			else
			{

				if(flag2)
				{
					flag=false;

				}

			}
			count++;
			if(count==100)
			{
				flag=false;
				break;
			}
		}
		return flag;
	}




	/*public static void setBrowserZoomOutlevel(WebDriver driver,String percentage)
	{

		Robot robot;
		try {
			robot = new Robot();
			Thread.sleep(2000);
			if(percentage.contains("67"))
				for(int i=0;i<3;i++)
				{
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}

			if(percentage.contains("25"))
				for(int i=0;i<5   ;i++)
				{
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setBrowserZoomInlevel(WebDriver driver,String percentage)
	{

		Robot robot;
		try {
			robot = new Robot();

			if(percentage.contains("67"))
				for(int i=0;i<3;i++)
				{
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_PLUS);
					robot.keyRelease(KeyEvent.VK_PLUS);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}

			if(percentage.contains("25"))
				for(int i=0;i<5   ;i++)
				{
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_PLUS);
					robot.keyRelease(KeyEvent.VK_PLUS);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}*/

	public static List<String> getAttributeList(WebDriver driver,String text,String tagName,String atribute)
	{
		List<String> list=new ArrayList<>();
		for(WebElement element:FunctionLibrary.getTextElements(driver,text,tagName))
		{
			list.add(element.getAttribute(atribute));
		}
		return list;
	}

	public static ResultSet connectDatabase(String executeQuery,String type)
	{
		Connection connection;
		ResultSet resultSet=null;
		try {

			String uname=readPropertyValue("dbUsername");
			String password=readPropertyValue("dbPassword");
			String dbURL = readPropertyValue("dbUrl");
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (ClassNotFoundException e) {
				LOGGER.error("postgres JDBC driver not found.");
			}
			try {
				connection = DriverManager.getConnection(dbURL, uname, password);
				Statement st = connection.createStatement();
				if(type.equals("INSERT"))
				{
					st.executeQuery(executeQuery);
				}
				else
				{
					resultSet= st.executeQuery(executeQuery);
				}

			} catch (SQLException e) {

				LOGGER.error(e.getMessage().toString());

			}
		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}

		return resultSet;
	}
}
