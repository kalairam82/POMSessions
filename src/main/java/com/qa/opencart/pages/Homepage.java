package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class Homepage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	public Homepage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
   By logoutlink = By.xpath("//a[contains(@href,'logout') and @class='list-group-item']");
   By headers = By.xpath("//div[@id='content']/h2");//"div#content > h2"/css
   By search = By.name("search");
   By searchbutton = By.cssSelector("div#search button");
   By myaccount=By.linkText("My Account");
   By dropdown = By.xpath("//li[@class='dropdown open']/ul/li");
   public String getHomePageTitle()
   {
	   //String title = driver.getTitle();
	   String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TTILE, AppConstants.DEFAULT_TIME_OUT);
	   ChainTestListener.log("The Home page title is:" +title);
	   System.out.println(title);
	   return title;
   }
   
   public String getHomePageURL()
   {
	   //String url = driver.getCurrentUrl();
	   String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
	   System.out.println(url);
	   return url;
   }
   
   public  LogoutPage doLogout(String value)
   {
	   eleUtil.doActionsClick(myaccount);
	   //driver.findElement(myaccount).click();
	   List<WebElement> dropdownlist = eleUtil.waitForElementsVisible(dropdown, AppConstants.SHORT_TIME_OUT);
	   //List<WebElement> dropdownlist = driver.findElements(dropdown);
	   //System.out.println(dropdownlist.size());
	   for(WebElement e:dropdownlist)
	   {
		   String text = e.getText();
		   if(text.contains(value))
		   {
			   e.click();
			   break;
		   }
	   }
	   return new LogoutPage(driver);
   }
   public boolean isLogoutLinkExist()
   {
	   //return driver.findElement(logoutlink).isDisplayed();
	   return eleUtil.doIsElementDisplayed(logoutlink); 
   }
   public List<String> getHeadersList()
   {
	   List<WebElement> headerlist = eleUtil.waitForElementsVisible(headers, AppConstants.SHORT_TIME_OUT);
//	  List<WebElement> headerlist =  driver.findElements(headers);
	  List<String> headerlistvalues = new ArrayList<String>();
	  for(WebElement e: headerlist)
	  {
		  String list = e.getText();
		  headerlistvalues.add(list);
	  }
	return headerlistvalues;   
   }
   
   public SearchResultsPage doSearch(String searchkey)
   {
	   System.out.println("The search key is:" +searchkey);
	   WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIME_OUT);
	   eleUtil.doSendKeys(searchEle, searchkey);
	   eleUtil.doClick(searchbutton);
//	   driver.findElement(search).sendKeys(searchkey);
//	   driver.findElement(searchbutton).click();
	   return new SearchResultsPage(driver);
	   
   }
//   public LogoutPage doLogout()
//   {
//	   if(isLogoutLinkExist())
//	   {
//		   driver.findElement(logoutlink).click();
//		  
//	   }
//	   return new LogoutPage(driver);
//   }
//   

   
   
   
}
