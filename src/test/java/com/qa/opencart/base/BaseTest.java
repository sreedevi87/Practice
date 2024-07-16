package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage regPage;
	
    @Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
	//public void setup(@Optional("firefox") String browserName) {
	//public void setup(@Optional String browserName) {
		df = new DriverFactory();
		prop=df.initProp();
		if (browserName!=null) {prop.setProperty("browser", browserName);}
		driver = df.initDriver(prop);
		loginPage=new LoginPage(driver);
	
	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}
