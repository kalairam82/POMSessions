package com.qa.opencart.constants;

public class AppConstants {
	
	public final static int DEFAULT_TIME_OUT = 5;
	public final static int SHORT_TIME_OUT = 10;
	public final static int MEDIUM_TIME_OUT = 15;
	public final static int LONG_TIME_OUT = 20;
	
	public final static String LOGIN_PAGE_TTILE ="Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION ="route=account/login";
	
	public final static String HOME_PAGE_TTILE = "My Account";
	public final static String HOME_PAGE_URL_FRACTION = "route=account/account";
	public final static String CHECKOUT_PAGE_URL_FRACTION = "route=checkout/checkout";
	public static final String SHOPPING_PAGE_URL_FRACTION = "route=checkout/cart";

	
	public static final String CONFIG_PROP_FILE_PATH = "./src/test/resources/config/Config.Properties";
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/config/qa.config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/config/dev.config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/config/stage.config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/config/uat.config.properties";


}
