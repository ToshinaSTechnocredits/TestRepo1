package com.tc.ak.testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tc.ak.base.PreDefinedActions;

public class TestBase 
{
	private WebDriver driver;	
	public WebDriver start()
	{
		driver = PreDefinedActions.start();
		return driver;
	}
	public WebDriver start(String url)
	{
		driver = PreDefinedActions.start(url);
		return driver;
	}
	
	void gotoRegistrationPage()
	{
		driver.findElement(By.id("registration2")).click();
	}
	
	void gotoDemoTables()
	{
		driver.findElement(By.id("demotable")).click();
	}
	
	void closeSession()
	{
		driver.close();
		System.out.println("Driver Closed");
	}
}