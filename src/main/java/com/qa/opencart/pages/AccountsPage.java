package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	By linktextLogout = By.linkText("Logout");
	By headers = By.cssSelector("div h2");
	By search = By.xpath("//input[@name='search']");
	By searchIcon = By.cssSelector("div #search button");

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIMEOUT);

		System.out.println(url);
		return url;

	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(linktextLogout);

	}

	public List<String> verifyHeadersList() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElemenetsLocated(headers,
				TimeUtil.DEFAULT_MEDIUM_TIMEOUT);

		List<String> headersText = new ArrayList<String>();
		for (WebElement e : headersList) {
			headersText.add(e.getText());
		}
		return headersText;
	}

	public boolean searchFieldExist() {
		return eleUtil.doIsDisplayed(search);

	}

	public SearchResultsPage doSearch(String searchText) {

		System.out.println("searching:" + searchText);

		if (searchFieldExist()) {
			eleUtil.doSendKeys(search, searchText);
			eleUtil.doClick(searchIcon);

			return new SearchResultsPage(driver);
		} else {
			System.out.println("search field is not available on the page");
			return null;
		}

	}
}
