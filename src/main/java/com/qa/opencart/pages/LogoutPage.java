package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
	

	private WebDriver driver;
	
	public LogoutPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	private By logoutheader = By.tagName("h1");
	
	public String getLogoutPageHeader()
	{
		String headerText = driver.findElement(logoutheader).getText();
		System.out.println("The Header text is: " +headerText);
		return headerText;
	}
	public String getPagetitle()
	{
		String title = driver.getTitle();
		return title;
	}

}
