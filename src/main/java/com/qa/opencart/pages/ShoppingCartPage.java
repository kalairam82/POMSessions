package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class ShoppingCartPage {
	   private WebDriver driver;	
		private ElementUtil eleUtil;
		public ShoppingCartPage(WebDriver driver)
		{
			this.driver =driver;
			eleUtil = new ElementUtil(driver);
		}
		
		private By checkout = By.xpath("//a[text()='Checkout']");
		@Step("getShoppingcartPageURL")
		public String getshoppingcartPageURL()
		{
			String pageurl = eleUtil.waitForURLContains(AppConstants.SHOPPING_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
			System.out.println(pageurl);
			return pageurl;
		}
		@Step("ClickonCheckout")
		public CheckOutPage doClickCheckout()
		{
			eleUtil.clickElementWhenReady(checkout, AppConstants.DEFAULT_TIME_OUT);
			return new CheckOutPage(driver);
		}
}
