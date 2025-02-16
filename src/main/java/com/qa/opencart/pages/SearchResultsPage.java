package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {
    private WebDriver driver;
	private ElementUtil eleUtil;
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private By productresults = By.cssSelector("div.product-thumb");

    public int doGetProductResults()
    {
//    	int prodsize =driver.findElements(productresults).size();
    	int prodsize = eleUtil.waitForElementsVisible(productresults, AppConstants.DEFAULT_TIME_OUT).size();
    	System.out.println(prodsize);
    	return prodsize;
   	}
    public ProductInfoPage selectProduct(String productname)
    {
    	System.out.println("The product name is:" +productname);
    	//driver.findElement(By.linkText(productname)).click();
    	eleUtil.doClick(By.linkText(productname));
    	return new ProductInfoPage(driver);
    	
    }
    
}

