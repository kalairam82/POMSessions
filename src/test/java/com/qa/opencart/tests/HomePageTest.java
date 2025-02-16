package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;


public class HomePageTest extends BaseTest 
{
  @BeforeClass
   public void homePageSetUp()
  {
	  homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
  }
  @Test
  public void homePageTitleTest()
  {
	 ChainTestListener.log("Login page title test"); 
	 String title =  homepage.getHomePageTitle();
	 ChainTestListener.log("The Home page title is:" +title); 
	 Assert.assertEquals(title, AppConstants.HOME_PAGE_TTILE, AppError.TITLE_NOT_FOUND_ERROR);
  }
  @Test
  public void homePageUrlTest()
  {
	  ChainTestListener.log("Login page URL test");
	  String url = homepage.getHomePageURL();
	  ChainTestListener.log("Login page URL test" +url);
	  Assert.assertTrue(homepage.getHomePageURL().contains(AppConstants.HOME_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
  }
  @Test
  public void logoutLinkExist()
  {
	  Assert.assertTrue(homepage.isLogoutLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
  }
  @Test
  public void headerTest()
  {
	  List<String> header = homepage.getHeadersList();
	  System.out.println(header);
  }
  
  @DataProvider
  public Object[][] getSearchData()
  {
	  return new Object[][] {
		  {"macbook",3},
		  {"imac",1},
		  {"samsung",2},
		  {"canon",1},
		  {"airtel",0}
	  };
  }
 //  @Test(priority = Integer.MAX_VALUE)
  @Test(dataProvider = "getSearchData",priority = Integer.MAX_VALUE)
  public void doSearch(String searchKey, int resultCount )
  {
	  searchresultspage = homepage.doSearch(searchKey);
	  Assert.assertEquals(searchresultspage.doGetProductResults(),resultCount);
	  
  }
  

  
	
	
	
	

}
