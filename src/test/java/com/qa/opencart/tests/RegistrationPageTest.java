package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registerPageSetup() {
		regPage=loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegistraionTestData() {
		return new Object[][]{
				{"akhila","automation","8765678888", "akil@345", "yes"},
				{"ravi","ahj","7867576899","ravi@345","no"},
				{"Madhrru", "automadtion", "9767875876", "madhu@123", "yes"}
		};
	}
	@DataProvider
		public Object[][] getTestDataFromSheet(){
			return ExcelUtil.getDataFromSheet(AppConstants.REGISTER_DATA_SHEET_NAME);
		}
	
	
	@Test(dataProvider="getTestDataFromSheet")
	public void verifySucessfulUserRegistration(String firstName,String lastName,String telephone,String password,String subscribe) {
	Assert.assertTrue(regPage.doUserRegister(firstName,lastName, StringUtils.generateRandomEmailid(),telephone, password, subscribe),AppError.REGISTRATION_FAILED_MSG);
	}
}
