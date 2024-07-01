package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	public boolean doUserRegister(String firstName,String lastName, String emailID,String telephone,String password,String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.emailId, emailID);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		if (subscribe.equalsIgnoreCase("yes")) eleUtil.doClick(subscribeYes); else eleUtil.doClick(subscribeNo);
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String sucessMsg=eleUtil.waitForElementVisible(successMessg, TimeUtil.DEFAULT_MEDIUM_TIMEOUT).getText();
		if (sucessMsg.contains(AppConstants.ACC_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);	
			eleUtil.doClick(registerLink);
			return true;
		}
		else return false;
	}
}
