package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic =1:Design login page")
@Story("User story:1 Login page design")
@Feature("Feature 1:Login Page Test")

public class LoginPageTest extends BaseTest{
    @Description("Checking Login page title") 
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageTitleTest()
    {
    	ChainTestListener.log("Login page title test");
     	String actLoginTitle = loginpage.getLoginPageTitle();
     //	Assert.assertEquals(actLoginTitle, "Account Login" , "===wrong title====");
    	Assert.assertEquals(actLoginTitle, AppConstants.LOGIN_PAGE_TTILE , AppError.TITLE_NOT_FOUND_ERROR);
    }
    @Description("Checking Login pageURL ") 
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageUrlTest()
    {
    	ChainTestListener.log("Login page URL test");
    	String actloginUrl = loginpage.getLoginPageUrl();
    	 ChainTestListener.log("Login page URL test" +actloginUrl);
  	//Assert.assertTrue(actloginUrl.contains("account/login"));
    	Assert.assertTrue(actloginUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION) , AppError.URL_NOT_FOUND_ERROR);
    }
    
    @Description("Checking ForgotPassword link ") 
    @Severity(SeverityLevel.MINOR)
    @Test
    public void ForgottenPwdexistTest()
    {
    	ChainTestListener.log("Forgotten Password Exist test"); 
    	boolean exist = loginpage.isForgottenPasswordExist();
    	Assert.assertTrue(exist, AppError.ELEMENT_NOT_FOUND_ERROR);
    }
    
    @Description("Checking Login ") 
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = Integer.MAX_VALUE)
    public void loginTest()
    {
//    	homepage = loginpage.doLogin("septbatch2024@open.com", "Selenium@12345");
    	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    	Assert.assertEquals(homepage.getHomePageTitle(),AppConstants.HOME_PAGE_TTILE, AppError.TITLE_NOT_FOUND_ERROR);
    }
    
    @Description("Checking if logo exist ") 
    @Severity(SeverityLevel.MINOR)
    @Test
	public void logoTest() {
		Assert.assertTrue(commonspage.isLogoDisplayed(), AppError.LOGO_NOT_FOUND_ERROR);
	}
	
	@DataProvider
	public Object[][] getFooterData() {
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}
		};
	}
	
	 @Description("Checking footer links ") 
	    @Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getFooterData")
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonspage.checkFooterLink(footerLink));
	}


    
}

