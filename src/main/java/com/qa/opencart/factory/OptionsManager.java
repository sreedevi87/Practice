package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop){
		this.prop=prop;
	}
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====test cases running in chrome-private======");
			co.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====test cases running in chrome-headless======");
			co.addArguments("--headless");
		}
		return co;
	}
	public FirefoxOptions getFirefoxOptions() {
		fo=new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====test cases running in firefox-private======");
			fo.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====test cases running in firefox-headless======");
			fo.addArguments("--headless");
		}
		return fo;
	}
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====test cases running in edge-private======");
			eo.addArguments("--inprivate");
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====test cases running in edge-headless======");
			eo.addArguments("--headless");
		}
		return eo;
	}
}
