package com.interzonedev.herokuspringdemo.functionaltest;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Named("functionalTestHelper")
public class FunctionalTestHelper {

	@Inject
	private Properties functionalTestProperties;

	@Inject
	private FirefoxDriver firefoxDriver;

	public WebDriver getWebDriver() {
		return getWebDriver(functionalTestProperties.getProperty("browser"));
	}

	public WebDriver getWebDriver(String browserId) {
		if (!Browser.allIds().contains(browserId)) {
			throw new IllegalArgumentException("Unrecognized browser id: " + browserId);
		}

		return getWebDriver(Browser.getById(browserId));
	}

	public WebDriver getWebDriver(Browser browser) {

		WebDriver driver = null;

		switch (browser) {
			case FIREFOX:
				driver = firefoxDriver;
				break;
			default:
				throw new IllegalArgumentException("Unrecognized browser: " + browser);
		}

		return driver;
	}

	public void openPage(WebDriver driver, String url) {
		driver.get(functionalTestProperties.getProperty("applicationUrl") + url);
	}

}
