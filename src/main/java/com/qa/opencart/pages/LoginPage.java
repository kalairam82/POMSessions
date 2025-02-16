package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	  private WebDriver driver;
	  private ElementUtil eleUtil;
	  public LoginPage(WebDriver driver)
	  {
		  this.driver = driver;
		  eleUtil = new ElementUtil(driver);
	  }
	  
	  //By Locators
	  private By emailId = By.id("input-email");
	  private By pwd = By.id("input-password");
	  private By loginbtn = By.xpath("//input[@type = 'submit']");
	  private By forgottenpassword = By.linkText("Forgotten Password");
	  //page actions - methods
	  @Step("getLoginPageTitle")
	  public String getLoginPageTitle()
	  {
		  String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TTILE, AppConstants.DEFAULT_TIME_OUT);
         //String title = driver.getTitle();
         System.out.println(title);
         ChainTestListener.log("The login page title is:" +title);
         return title;
	  }
	  @Step("getLoginPageURL")
	  public String getLoginPageUrl()
	  {
		    String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
//           String url= driver.getCurrentUrl();
           System.out.println(url);
           return url;
	  }
	  @Step("checking forgot pwd link is displayed")
	  public boolean isForgottenPasswordExist() {
		 return eleUtil.doIsElementDisplayed(forgottenpassword);
         //return  driver.findElement(forgottenpassword).isDisplayed();
	  }
	  @Step("login with username: {0} and password: {1}")
	  public Homepage doLogin(String username, String password)
	  {
		  eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_TIME_OUT).sendKeys(username);
		  eleUtil.doSendKeys(pwd, password);
		  eleUtil.doClick(loginbtn);
//		  driver.findElement(emailId).sendKeys(username);
//		  driver.findElement(pwd).sendKeys(password);
//		  driver.findElement(loginbtn).click();
 		  return new Homepage(driver);
	  }
	  
}
