package com.qa.opencart.pages;

import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	    private WebDriver driver;
	    private ElementUtil eleUtil;
	    private Map<String , String> productMap;
	    
		public  ProductInfoPage(WebDriver driver)
		{
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		private By productHeader = By.tagName("h1");
		private By productImages = By.xpath("//ul[@class='thumbnails']//img");
		private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private By PriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		private By addtocartbutton = By.xpath("//button[@class='btn btn-primary btn-lg btn-block']");
		private By shoppingcart = By.xpath("//a[text()='shopping cart']");

    public String getProductHeader()
    {
//    	String Header = driver.findElement(prodinfo).getText();
    	String Header = eleUtil.dogetText(productHeader);
    	System.out.println("Product Header:" + Header);
    	return Header;
    }
    
    public int getImagesCount()
    
    {
    	int imagescount = eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
    	System.out.println(getProductHeader() + "Images count: " +imagescount);
    	return imagescount;
    }
    
    public Map<String , String>  getProductInfo()
    {
        productMap = new HashMap<String, String>();
//    	productMap = new LinkedHashMap<String, String>();
//    	productMap = new TreeMap<String, String>();
    	productMap.put("header", getProductHeader());
    	productMap.put("ImageCount", getImagesCount() + "");
    	getProductMetaData();
    	getProductMetaPrice();
    	return productMap;
    }
    private  void getProductMetaData()
    {
    	List<WebElement> metalist = eleUtil.waitForElementsPresence(productMetaData,AppConstants.DEFAULT_TIME_OUT);
    	for(WebElement e: metalist)
    	{
    		String metaText = e.getText();
    		String meta[]= metaText.split(":");
    		String metaKey = meta[0].trim();
    		String metaValue = meta[1].trim();
    		productMap.put(metaKey, metaValue);  		
    	}
    }
    
    private void getProductMetaPrice()
    {
    	List<WebElement> pricelist = eleUtil.waitForElementsPresence(PriceData,AppConstants.DEFAULT_TIME_OUT);
    	String productprice = pricelist.get(0).getText();
    	String productExtax = pricelist.get(1).getText().split(":")[1].trim();
    	productMap.put("price", productprice);
    	productMap.put("Extax", productExtax);
    }
    public boolean doAddtoCart()
    {
    	eleUtil.waitForElementPresence(addtocartbutton, AppConstants.DEFAULT_TIME_OUT).click();
        //return eleUtil.waitForElementVisible(shoppingcart,AppConstants.LONG_TIME_OUT).isDisplayed();
    	return eleUtil.doisDisplayedwaitForElementVisible(shoppingcart, AppConstants.DEFAULT_TIME_OUT);
    	//return eleUtil.doIsElementDisplayed(shoppingcart);
    }
    public ShoppingCartPage doClickShoppingCart()
    {
    	 
    	eleUtil.waitForElementPresence(shoppingcart, AppConstants.DEFAULT_TIME_OUT).click();
    	return new ShoppingCartPage(driver);
    }
   }
