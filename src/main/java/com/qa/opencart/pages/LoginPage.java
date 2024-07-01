package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink =By.linkText("Register");

	
	public LoginPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	@Step("capturing login page title")
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT);
		System.out.println(title);
		return title;
	}
	@Step("capturing login page url")
	public String getloginPageUrl() {
		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIMEOUT);
		
		System.out.println(url);
		return url;
		
	}
	
	public boolean verifyForgotPasswordLink() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	
	}
	@Step("logging in with username:{0},password: {1}")
	public AccountsPage doLogin(String username,String pwd) {
		eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIMEOUT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
	}
	@Step("navigation registration page..")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, TimeUtil.DEFAULT_TIMEOUT);
		return new RegistrationPage(driver);
	}

}
