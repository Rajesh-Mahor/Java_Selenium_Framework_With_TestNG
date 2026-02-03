package com.orangehrm.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;
	String path = "D:\\eclipse-workspace\\Java Selenium Project\\OrangeHRM\\Configurations\\Confige.properties";

	public ReadConfig() {
		try {
			properties = new Properties();
			FileInputStream inputrStream = new FileInputStream(path);
			properties.load(inputrStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		String appUrl = properties.getProperty("url");
		if (appUrl != null) {
			return properties.getProperty("url");
		} else {
			throw new RuntimeException("Url is not configered in config.properties.");
		}
	}

	public String getBrowser() {
		String appBrowser = properties.getProperty("browser");
		if (appBrowser != null) {
			return properties.getProperty("browser");
		} else {
			throw new RuntimeException("Browser is not configered in config.properties.");
		}
	}

}
