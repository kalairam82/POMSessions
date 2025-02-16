package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;


public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;	
	public static String Highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	 public WebDriver initDriver(Properties prop)
	 {
		String browserName = prop.getProperty("browser");
		log.info("browser name is: " +browserName);
		//System.out.println("The Browser is: " +browserName);
		Highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
      switch(browserName.trim().toLowerCase())
      {
    	  case "chrome":
    		  tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//    		  driver = new ChromeDriver(optionsManager.getChromeOptions());
    		  break;
    	  case "firefox":
    		  tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
    		  //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
    		  break;
    	  case "edge":
    		  tlDriver.set( new EdgeDriver(optionsManager.getEdgeOptions()));
    		  //driver = new EdgeDriver(optionsManager.getEdgeOptions());
    		  break;
    	  case "safari":
    		  tlDriver.set(new SafariDriver());
    		  break;
    	  default:
    		  System.out.println("pls Pass the right browser");
    		  log.error("pls Pass the right browser" +browserName);
    		  throw new FrameworkException("Invalid Browser");
    	}
      
//      driver.manage().deleteAllCookies();
//      driver.manage().window().maximize();
//      driver.get(prop.getProperty("url"));
      getDriver().manage().deleteAllCookies();
      getDriver().manage().window().maximize();
      getDriver().get(prop.getProperty("url"));
      return getDriver();
	}
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
   
	/**
	 * This method is used to initialize the properties from the .properties file
	 */
	//supply env name using maven command line
	//mvn clean install -Denv="qa"
	public Properties initProp()
	{
		String envName = System.getProperty("env");
//		System.out.println("running on: " + envName);
		log.info("running on: " +envName);
		prop = new Properties();
		FileInputStream ip = null;
		try
		{
			if(envName == null)
			{
//			System.out.println("No env Name is passed so running in the qa env");
				log.warn("No env Name is passed so running in the qa env");
			ip =new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
		    }
		   else
		   {
			switch(envName.trim().toLowerCase())
			{
			case "qa":
			ip =new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			break;
			case "dev":
				ip =new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);	
				break;
			case "uat":
				ip =new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
				break;
			case "stage":
				ip =new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
				break;
			case "prod":
				ip =new FileInputStream(AppConstants.CONFIG_PROP_FILE_PATH);
				break;
			default:
				log.error("Please pass the correct environment");
				throw new FrameworkException("invalid environment");
			   }
		     }
		   prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	return prop;
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/"  + "_" + System.currentTimeMillis()+ ".png";		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir
		
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir
		
	}
	
	
	

}
	
	


