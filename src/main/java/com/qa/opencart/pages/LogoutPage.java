package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class LogoutPage {
	

	private WebDriver driver;
	
	public LogoutPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	private By logoutheader = By.tagName("h1");
	
	@Step("getLgoutPageHeader")
	public String getLogoutPageHeader()
	{
		String headerText = driver.findElement(logoutheader).getText();
		System.out.println("The Header text is: " +headerText);
		return headerText;
	}
	@Step("getLogoutPageTitle")
	public String getPagetitle()
	{
		String title = driver.getTitle();
		return title;
	}

}
