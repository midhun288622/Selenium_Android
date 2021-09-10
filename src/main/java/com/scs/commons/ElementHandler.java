package com.scs.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementHandler {
	WebDriver driver;
	public ElementHandler(WebDriver driver)
	{
		this.driver=driver;
	}

	public void isElementVisible(WebElement element,int timeouts)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, timeouts);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void isElementClickable(WebElement element,int timeouts)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, timeouts);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
