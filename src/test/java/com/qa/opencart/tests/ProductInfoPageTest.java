package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;


public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] searchTestData() {
		return new Object[][] {
			    { "macbook", "MacBook Pro" }, 
			    { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, 
				{ "samsung", "Samsung Galaxy Tab 10.1" },
				{ "canon", "Canon EOS 5D" }
				};

	}
	@DataProvider
	public Object[][] searchTestDataFromSheet() {
		return ExcelUtil.getDataFromSheet(AppConstants.SEARCH_TEST_DATA);
		}

	@Test(dataProvider = "searchTestDataFromSheet")
	public void verifyProductText(String searchKey, String headerText) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(headerText);
		Assert.assertEquals(productInfoPage.getProductText(), headerText, AppError.HEADER_NOT_FOUND);
	}

	@DataProvider
	public Object[][] searchTestDataImageCount() {
		return new Object[][] { 
			    { "macbook", "MacBook Pro", "4" }, 
			    { "imac", "iMac", "4" },
				{ "samsung", "Samsung SyncMaster 941BW", "1" },
				{ "samsung", "Samsung Galaxy Tab 10.1", "7" },
				{ "canon", "Canon EOS 5D", "3" }
				};
	}
	@DataProvider
	public Object[][] searchTestDataImageCountFromSheet() {
		return ExcelUtil.getDataFromSheet(AppConstants.SEARCH_TEST_DATA_PRODUCT_IMAGES_COUNT);
		
	}

	@Test(dataProvider = "searchTestDataImageCount")
	public void verifyProductImagesCount(String searchKey, String headerText, String imagesCount) {
		int expectedImagesCount=Integer.parseInt(imagesCount);
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(headerText);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), expectedImagesCount, AppError.IMAGES_COUNT_MISMATCHED);
	}
	@Test
	public void verifyProductInfo() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		System.out.println("production information is ");
		Map<String,String> productInfoMap=productInfoPage.getProductMapInfo();
		System.out.println(productInfoMap);
		Assert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
		Assert.assertEquals(productInfoMap.get("productimagescount"), String.valueOf(4));
	}

}
