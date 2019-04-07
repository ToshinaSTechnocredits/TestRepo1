package com.tc.ak.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tc.ak.constant.ConstantPath;

abstract public class PreDefinedActions
{
	static public WebDriver driver;
	
	private WebDriverWait wait = new WebDriverWait(driver, 30);
	
	static public WebDriver start()
	{
		start("http://automationbykrishna.com/#");	//Ideally should read from property file
		return driver;
	}
	
	static public WebDriver start(String url)
	{
		System.out.println("Start : Browser Initilization");
		System.setProperty("webdriver.chrome.driver",ConstantPath.CHROMEDRIVEREXE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Browser has been Initilized");
		driver.get(url);
		System.out.println("URL Entered");
		return driver;
	}
	
	private By getObject(String locator)
	{
		String locatorType = locator.split(":")[0].replace("[","").replace("]","").toUpperCase();
		String locatorValue = locator.split(":")[1];
		switch(locatorType)
		{
		case "ID" : 
			return By.id(locatorValue);
		case "XPATH":
			return By.xpath(locatorValue);
		case "CSS":
			return By.cssSelector(locatorValue);
		}
		return null;
	}
	
	private WebElement getElement(String locator)
	{
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(locator)));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		return element;
	}
	
	protected void enterText(String locator, String text)
	{
		
		getElement(locator).sendKeys(text);
	}
	
	protected void click(String locator)
	{
		WebElement element = getElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	protected String getText(String locator)
	{
		return getElement(locator).getText();
	}
	
	protected String getAttribute(String locator, String attributeName)
	{
		return getElement(locator).getAttribute(attributeName);
	}
	
	
	
	protected String acceptAlert()
	{
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		System.out.println("Alert Accepted");
		return actual;
	}
	
	protected String dismissAlert()
	{
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.dismiss();
		System.out.println("Alert Dismissed");
		return actual;
	}
	
}
