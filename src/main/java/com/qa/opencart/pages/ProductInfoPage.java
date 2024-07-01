package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]");
	private By productPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]");
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductText() {
		String productText = eleUtil.doGetText(productHeader);
		System.out.println(productText);
		return productText;
	}

	public int getProductImagesCount() {
		List<WebElement> imagesWebElements = eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount,
				TimeUtil.DEFAULT_MEDIUM_TIMEOUT);
		int productImagesCount = imagesWebElements.size();
		return productImagesCount;

	}

	public Map<String, String> getProductMapInfo() {
		productMap=new HashMap<String,String>();
		productMap.put("productname",getProductText());
		productMap.put("productimagescount", String.valueOf(getProductImagesCount()));
		productGetData();
		getProductPriceInfo();
		return productMap;
	}

	public void productGetData() {
		List<WebElement> productDataWebElements = eleUtil.getElements(productMetaData);

		for (WebElement e : productDataWebElements) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}

	}

	// $2,000.009
	// Ex Tax: $2,000.00
	public void getProductPriceInfo() {
		List<WebElement> productPriceWebElements = eleUtil.getElements(productPrice);

		String productPriceItem = productPriceWebElements.get(0).getText();
		String productPriceExTax 
		   = productPriceWebElements.get(0).getText().split(":")[1].trim();
		productMap.put("productprice", productPriceItem);
		productMap.put("productpriceextax", productPriceExTax);
	}
}
