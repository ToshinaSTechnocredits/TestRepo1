package com.tc.ak.testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.tc.ak.Pages.RegistrationPage;
import com.tc.ak.constant.ConstantPath;
import com.tc.ak.utility.ExcelFileOperation;

public class RegistrationTest extends TestBase {

	@Test(dataProvider = "loginDataProvider")
	public final void login(String userName, String pwd, String expectedResult) {

		// String expected = "Success!";
		start();
		gotoRegistrationPage();

		RegistrationPage registrationPage = new RegistrationPage();
		registrationPage.login(userName, pwd);

		String actual = registrationPage.handleAlertOnRegistrationPage();
		Assert.assertEquals(actual, expectedResult, "Actual is not matching with expected");
		closeSession();
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDataProvider() {
		ExcelFileOperation efo;
		try {
			efo = new ExcelFileOperation(ConstantPath.REGISTRATIONEXCELDATA);
			Object[][] data = efo.readData(ConstantPath.LOGINDATASHEET);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error : File not found on given location");
		}
		return null;
	}

	/*@Test
	public final void verifyErrorMessageOnRegistrationForm() throws IOException {
		start();
		gotoRegistrationPage();
		RegistrationPage registrationPage = new RegistrationPage();
		registrationPage.verifyErrorMessageOfFullNameField();
		String fullNameAlertMessage = registrationPage.handleAlertOnRegistrationPage();
		Assert.assertTrue(fullNameAlertMessage.equals("Full name can't be blank!"));
	}*/

}
