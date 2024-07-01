package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyAccountsPageTitle() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test
	public void verifyUrlFraction() {
		String actualUrl = accPage.getAccountPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}
	@Test
	public void verifySuccessfulLogin() {
		Assert.assertTrue(accPage.isLogoutLinkExist(),"the login is not successful");
	}
	@Test(priority=1)
	public void verifySearchField() {
		Assert.assertTrue(accPage.searchFieldExist(),"the search field is not present");
	}
	@Test(priority=2)
	public void verifyHeaders() {
		List<String> actHeadersList=accPage.verifyHeadersList();
		Assert.assertEquals(actHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST,AppError.LIST_NOT_MATCH);
	}
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"Airtel",0}
			
		};
	}
	@Description("verifying any searchtext and its results count")
	@Test(dataProvider="searchData",priority=3)
	public void verifySearchTest(String searchKey,int resultsCount) {
		searchResultsPage=accPage.doSearch(searchKey);	
		Assert.assertEquals(searchResultsPage.getSearchResultsCount(),resultsCount,AppError.RESULTS_COUNT_MISMATCHED);
	}

}
