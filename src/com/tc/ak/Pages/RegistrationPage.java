package com.tc.ak.Pages;

import java.io.IOException;

import com.tc.ak.base.PreDefinedActions;
import com.tc.ak.constant.ConstantPath;
import com.tc.ak.utility.PropertyFileOperation;

public class RegistrationPage extends PreDefinedActions
{
	PropertyFileOperation propOperation;
	public RegistrationPage()
	{
		try
		{
			propOperation = new PropertyFileOperation(ConstantPath.REGISTRATIONPAGEPROPERTY);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
	}
	
	public void login(String uname, String pwd)
	{
		enterTextOnRegistrationPage("username", uname);
		enterTextOnRegistrationPage("password", pwd);
		clickSubmit("loginbutton");
	}
	
	private void enterTextOnRegistrationPage(String key, String text)
	{
		enterText(propOperation.readData(key),text);
	}
	
	private void clickSubmit(String key)
	{
		click(propOperation.readData(key));
	}
	
	public void signUp()
	{
		
	}
	
	public String handleAlertOnRegistrationPage()
	{
		return acceptAlert();
	}
	
	public void verifyErrorMessageOfFullNameField()
	{
		clickSubmit("registrationbutton");
		
	}
}
