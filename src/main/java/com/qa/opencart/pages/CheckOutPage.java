package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CheckOutPage {
	    private WebDriver driver;	
		private ElementUtil eleUtil;
		public CheckOutPage(WebDriver driver)
		{
			this.driver =driver;
			eleUtil = new ElementUtil(driver);
		}
		
		By addressoption1 =By.xpath("(//div/label)[1]");
		By addressoption2 = By.xpath("(//div/label)[1]");
		
		public String getCheckoutPageURL()
		{
			String pageurl = eleUtil.waitForURLContains(AppConstants.CHECKOUT_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
			System.out.println(pageurl);
			return pageurl;
		}
		
}
