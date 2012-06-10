package com.interzonedev.herokuspringdemo.functionaltest.driver;

import javax.inject.Inject;
import javax.inject.Named;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

@Named("driverFactory")
public class WebDriverFactory {

	@Inject
	private ApplicationContext applicationContext;

	public WebDriver getWebDriver(String browserId) {
		if (!Browser.allIds().contains(browserId)) {
			throw new IllegalArgumentException("Unrecognized browser id: " + browserId);
		}

		return getWebDriver(Browser.getById(browserId));
	}

	public WebDriver getWebDriver(Browser browser) {

		String beanName = getWebDriverBeanName(browser);

		WebDriver driver = (WebDriver) applicationContext.getBean(beanName);

		return driver;
	}

	private String getWebDriverBeanName(Browser browser) {

		String beanName = null;

		switch (browser) {
			case FIREFOX:
				beanName = "firefoxDriver";
				break;
			case HTMLUNIT:
				beanName = "htmlUnitDriver";
				break;
			case SAFARI:
				beanName = "safariDriver";
				break;
			case CHROME:
				beanName = "chromeDriver";
				break;
			case IE:
				beanName = "internetExplorerDriver";
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		return beanName;
	}
}
