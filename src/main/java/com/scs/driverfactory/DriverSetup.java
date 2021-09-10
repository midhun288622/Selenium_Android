package com.scs.driverfactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scs.commons.FunctionLibrary;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * class for driver configurations.
 * 
 * @author MXC0RO7
 * 
 */
public class DriverSetup {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	private static final String SETTING_GRID_URL = "grid.url";
	private static final String SETTING_GRID_PROXY = "hubBrowserProxyUrl";

	/**
	 * method Name :getWebdriver parameters :browserType Purpose :This method is
	 * used to load the browser return : Web driver.
	 */

	static WebDriver getWebdriver(String browserType) throws MalformedURLException {
		ResourceBundle props = FunctionLibrary.getResourceBundle();
		LOGGER.info("Browser type is:" + browserType);
		WebDriver webdriver = null;
		if ("ios".equalsIgnoreCase(browserType)) {

			final String URL_STRING = FunctionLibrary.readPropertyValue("Host");
			URL url = new URL(URL_STRING);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,FunctionLibrary.readPropertyValue("PLATFORM_NAME"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, FunctionLibrary.readPropertyValue("PLATFORM_VERSION"));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, FunctionLibrary.readPropertyValue("AUTOMATION_NAME"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, FunctionLibrary.readPropertyValue("DEVICE_NAME"));
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
			capabilities.setCapability(MobileCapabilityType.APP, FunctionLibrary.readPropertyValue("APP"));
			capabilities.setCapability(MobileCapabilityType.UDID, FunctionLibrary.readPropertyValue("UDID"));
			return new AppiumDriver(url, capabilities);

		}
		else if ("android".equalsIgnoreCase(browserType)) {

			final String URL_STRING = FunctionLibrary.readPropertyValue("Host");
			URL url = new URL(URL_STRING);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,FunctionLibrary.readPropertyValue("PLATFORM_NAME"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, FunctionLibrary.readPropertyValue("PLATFORM_VERSION"));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, FunctionLibrary.readPropertyValue("AUTOMATION_NAME"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, FunctionLibrary.readPropertyValue("DEVICE_NAME"));
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
			capabilities.setCapability(MobileCapabilityType.APP, FunctionLibrary.readPropertyValue("APP"));
			capabilities.setCapability(MobileCapabilityType.UDID, FunctionLibrary.readPropertyValue("UDID"));
			capabilities.setCapability("appPackage", FunctionLibrary.readPropertyValue("appPackage"));
			capabilities.setCapability("appActivity", FunctionLibrary.readPropertyValue("appActivity"));
			return new AppiumDriver(url, capabilities);

		}
		else {
			LOGGER.info("mobile type is missing");
		}


		return webdriver;
	}

	/**
	 * Return Proxy object if settings require it.
	 * @param props properties to look in for hubBrowserProxyUrl
	 * @return null if no proxy set, proxy object if proxy url found
	 */
	private static Proxy getProxyIfAvailable(ResourceBundle props) {
		Proxy proxy = null;
		if (props.containsKey(SETTING_GRID_PROXY)) {
			String PROXY = props.getString(SETTING_GRID_PROXY);
			if(!PROXY.isEmpty()) {
				proxy = new Proxy();
				proxy.setHttpProxy(PROXY);
				proxy.setSslProxy(PROXY);
			}
		}
		return proxy;
	}
}
