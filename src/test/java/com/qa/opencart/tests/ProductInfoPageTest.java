package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class ProductInfoPageTest extends BaseTest{
	
	
	 @BeforeClass
	 public void productInfoPageSetUp()
	 {
		 homepage = loginpage.doLogin("septbatch2024@open.com", "Selenium@12345");
	 }
	 @DataProvider
	 public Object[][] getProductSearchData()
	 {
		 return new Object[][] {
			 {"mac","MacBook"},
			 {"imac", "iMac"},
			 {"macbook", "MacBook Pro"}
			 };
	 }
	 
	 @Test(dataProvider = "getProductSearchData")
	 public void doHeaderTest(String searchkey,String productname)
	 {
		 searchresultspage =  homepage.doSearch(searchkey);
		 productinfopage = searchresultspage.selectProduct(productname);
		 String actHeader = productinfopage.getProductHeader();
		 Assert.assertEquals(actHeader,productname);

	 }
	 
	 @DataProvider
	 public Object[][] getProductImagesData()
	 {
		return new Object[][] {
				 {"macbook", "MacBook Pro", 4},
				 {"macbook", "MacBook Air", 4},
				 { "imac", "iMac", 3},
				 {"samsung", "Samsung SyncMaster 941BW",1},
				 {"samsung", "Samsung Galaxy Tab 10.1", 7}
		 };
	 }
	 
	 @Test(dataProvider = "getProductImagesData", description="counting product images", enabled = true)
	 public void doImagesCountTest(String searchkey,String productname, int imagescount)
	 {
		 searchresultspage =  homepage.doSearch(searchkey);
		 productinfopage = searchresultspage.selectProduct(productname);
		 int actualimagescount = productinfopage.getImagesCount();
		 Assert.assertEquals(actualimagescount,imagescount);
	 }
	 
	 @Test
	 public void productInfoTest()
	 {
		 searchresultspage = homepage.doSearch("mac");
		 productinfopage = searchresultspage.selectProduct("MacBook Pro");
		 Map<String, String> productInformation =  productinfopage.getProductInfo();
		 productInformation.forEach((k,v) -> System.out.println(k +":"+ v));
		 
		 SoftAssert softAssert = new SoftAssert();
		 
		 softAssert.assertEquals(productInformation.get("header"),"MacBook Pro");
		 
		 softAssert.assertEquals(productInformation.get("Brand"),"Apple");
		 softAssert.assertEquals(productInformation.get("Availability"),"In Stock");
		 softAssert.assertEquals(productInformation.get("Product Code"),"Product 18");
		 softAssert.assertEquals(productInformation.get("Reward Points"),"800");
		 
		 softAssert.assertEquals(productInformation.get("price"),"$2,000.00");
		 softAssert.assertEquals(productInformation.get("Extax"),"$2,000.00");
		 
		 softAssert.assertAll();
	 }
	 @Test
	 public void addtoCartTest()
	 {
		 ChainTestListener.log("Add to cart test");
		 searchresultspage = homepage.doSearch("mac");
		 productinfopage = searchresultspage.selectProduct("MacBook Pro");
		 Boolean status = productinfopage.doAddtoCart();
		 Assert.assertTrue(status, AppError.ELEMENT_NOT_FOUND_ERROR);
	 }
	 
	 @Test
	 public void shoppingCartTest()
	 {
		 ChainTestListener.log("shopping cart page test");
		 searchresultspage = homepage.doSearch("mac");
		 productinfopage = searchresultspage.selectProduct("MacBook Pro");
		 productinfopage.doAddtoCart();
		 shoppingcartpage = productinfopage.doClickShoppingCart();
		 Assert.assertTrue(shoppingcartpage.getshoppingcartPageURL().contains(AppConstants.SHOPPING_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	 }
	 
	 @Test
	 
	 public void CheckoutCartTest()
	 {
		 ChainTestListener.log("checkout page test");
		 searchresultspage = homepage.doSearch("mac");
		 productinfopage = searchresultspage.selectProduct("MacBook Pro");
		 productinfopage.doAddtoCart();
		 shoppingcartpage = productinfopage.doClickShoppingCart();
		 checkoutpage = shoppingcartpage.doClickCheckout();
		 Assert.assertTrue(checkoutpage.getCheckoutPageURL().contains(AppConstants.CHECKOUT_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	 }
	 
	 @Test(priority = Integer.MAX_VALUE)
	 public void doLogout()
	 {
		searchresultspage = homepage.doSearch("mac");
		productinfopage = searchresultspage.selectProduct("MacBook Pro"); 
		logoutpage = homepage.doLogout("Logout");
		Assert.assertEquals(logoutpage.getLogoutPageHeader(),"Account Logout", "===wrong page=====");
		//Assert.assertTrue(logoutpage.getPagetitle().contains("Account Logout"), "----wrong page----"); 
	 }
}
