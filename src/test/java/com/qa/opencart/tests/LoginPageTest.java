package com.qa.opencart.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Login Page tests")
@Listeners({TestAllureListener.class,ExtentReportListener.class,AnnotationTransformer.class})
public class LoginPageTest extends BaseTest {

	@Description("verifying login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sreedevi Tester")
	@Test
	public void verifyLoginPageTitle() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Description("verifying login page url...")
	@Test
	public void verifyLoginPageUrl() {
		String actualUrl = loginPage.getloginPageUrl();

		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test
	@Owner("Sreedevi Tester")
	public void verifyForgotPasswordLink() {
		Assert.assertTrue(loginPage.verifyForgotPasswordLink(), AppError.ELEMENT_NOT_FOUND);
	}

	@Owner("Sreedevi Tester")
	@Test(priority = 1)
	@Severity(SeverityLevel.CRITICAL)
	public void loginTest() {
		AccountsPage accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("username is" + prop.getProperty("username"));
		System.out.println("password is" + prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

}
