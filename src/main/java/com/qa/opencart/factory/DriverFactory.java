package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		optionsManager=new OptionsManager(prop);
		highlight=prop.getProperty("highlight");
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver=new ChromeDriver();
		 tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
		//	driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver=new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		default:
			System.out.println("please enter valid browser name");
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return 	getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream fi =null;
		String envName=System.getProperty("env");
		System.out.println("running test cases on the env-->"+envName);
		if (envName==null) {
			System.out.println("running test cases on the qa env");
			try {
				fi=new FileInputStream(AppConstants.QA_FILE_PATH);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}else
		try {
			switch(envName.toLowerCase().trim()) {
			case "prod":
			fi = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;
			case "qa":
			fi=new FileInputStream(AppConstants.QA_FILE_PATH);
			break;
			case "dev":
			fi=new FileInputStream(AppConstants.DEV_FILE_PATH);
			break;
			default:
				System.out.println("please pass the right env");
				throw new FrameException(AppError.WRONGENVPASSED);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			prop.load(fi);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
return prop;
	}

	public static String getScreenshot(String methodName) {
		File scr=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination =new File(path);
		try {
			FileHandler.copy(scr, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
