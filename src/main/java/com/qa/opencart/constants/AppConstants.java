package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account11";
	public static final String LOGIN_PAGE_URL_FRACTION = "account/login";
	public static final String CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String DEV_FILE_PATH="./src/test/resources/config/dev.properties";
	public static final String QA_FILE_PATH="./src/test/resources/config/qa.properties";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST 
	                                              = Arrays.asList("My Account", "My Orders","My Affiliate Account", "Newsletter");
	public static final String ACC_SUCCESS_MSG = "Your Account Has Been Created";
	
	public static final String TEST_DATA_FILE_PATH="./src/test/resources/testdata/Book1.xlsx";
	public static final String REGISTER_DATA_SHEET_NAME="RegisterTestData";
	public static  final String SEARCH_TEST_DATA="SearchTestData";
	public static  final String SEARCH_TEST_DATA_PRODUCT_IMAGES_COUNT="Images";
	
}
