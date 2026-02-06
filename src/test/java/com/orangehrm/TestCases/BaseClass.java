package com.orangehrm.TestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.orangehrm.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	String myBrowser = readConfig.getBrowser();
	String myUrl = readConfig.getUrl();
	public static WebDriver driver;
	
	Logger logger = LogManager.getLogger(BaseClass.class);

	@BeforeMethod
	public void browserSetup() {
			
		switch (myBrowser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			driver = null;
			logger.info("Browser Value is null");
			break;

		}
		logger.info("Browser open Successfully");
		driver.get(myUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		logger.info("Application Start");

	}

//	@AfterMethod
//	public void tearDown() {
//		driver.close();
//		driver.quit();
//		logger.info("Application Close");
//	}

	public void ScreenShotCaptured(WebDriver driver, String testName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "\\ScreenShots\\"+testName+".png");
		FileUtils.copyFile(source, destination);
	}

}
