package com.interzonedev.herokuspringdemo.functionaltest;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Named("functionalTestHelper")
public class FunctionalTestHelper {

	@Inject
	private Properties functionalTestProperties;

	private String applicationUrl;

	private Long elementWaitTimeoutInSeconds;
	
	@PostConstruct
	public void init() {
		applicationUrl = functionalTestProperties.getProperty("applicationUrl");
		elementWaitTimeoutInSeconds = Long.parseLong(functionalTestProperties.getProperty("elementWaitTimeoutInSeconds", "10"));
	}

	public void openPage(WebDriver driver, String url) {
		driver.get(applicationUrl + url);
	}

	public void waitForElement(WebDriver driver, By by) {
		waitForElement(driver, by, elementWaitTimeoutInSeconds);
	}

	public void waitForElement(WebDriver driver, By by, long timeOutInSeconds) {
		waitForElementWithText(driver, by, null, timeOutInSeconds);
	}

	public void waitForElementWithText(WebDriver driver, By by, String text) {
		waitForElementWithText(driver, by, text, elementWaitTimeoutInSeconds);
	}

	public void waitForElementWithText(WebDriver driver, final By by, final String text, long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement element = d.findElement(by);
				return ((null == text) || element.getText().equals(text));
			}
		};

		Wait<WebDriver> pageLoadWait = new WebDriverWait(driver, timeOutInSeconds);
		pageLoadWait.until(pageLoadCondition);
	}

	public WebElement waitForAndGetElement(WebDriver driver, By by) {
		return waitForAndGetElementWithText(driver, by, null, elementWaitTimeoutInSeconds);
	}

	public WebElement waitForAndGetElement(WebDriver driver, By by, long timeOutInSeconds) {
		return waitForAndGetElementWithText(driver, by, null, timeOutInSeconds);
	}

	public WebElement waitForAndGetElementWithText(WebDriver driver, By by, String text) {
		return waitForAndGetElementWithText(driver, by, text, elementWaitTimeoutInSeconds);
	}

	public WebElement waitForAndGetElementWithText(WebDriver driver, By by, String text, long timeOutInSeconds) {
		waitForElementWithText(driver, by, text, timeOutInSeconds);
		return driver.findElement(by);
	}

	public void waitForElements(WebDriver driver, final By by) {
		waitForElements(driver, by, elementWaitTimeoutInSeconds);
	}

	public void waitForElements(WebDriver driver, final By by, long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				List<WebElement> elements = d.findElements(by);
				return !elements.isEmpty();
			}
		};

		Wait<WebDriver> pageLoadWait = new WebDriverWait(driver, timeOutInSeconds);
		pageLoadWait.until(pageLoadCondition);
	}

	public List<WebElement> waitForAndGetElements(WebDriver driver, By by) {
		return waitForAndGetElements(driver, by, elementWaitTimeoutInSeconds);
	}

	public List<WebElement> waitForAndGetElements(WebDriver driver, By by, long timeOutInSeconds) {
		waitForElements(driver, by, timeOutInSeconds);
		return driver.findElements(by);
	}
}
