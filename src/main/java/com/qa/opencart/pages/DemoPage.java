package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoPage {
	By demoId=By.id("demo");
	By Cart=By.xpath("cartxpath");
	public void getDemo() {
		System.out.println("inside demo page class");
	}
	public void addToCart() {
		System.out.println("add to cart page");
	}

}
