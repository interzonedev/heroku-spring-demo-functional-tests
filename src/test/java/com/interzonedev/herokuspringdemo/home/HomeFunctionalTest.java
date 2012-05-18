package com.interzonedev.herokuspringdemo.home;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;

public class HomeFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	public void testHomeFullUrl() {
		log.debug("testHomeFullUrl");

		functionalTestHelper.openPage(driver, "home");

		testPageHeader();
	}

	@Test
	public void testHomeDefaultUrl() {
		log.debug("testHomeDefaultUrl");

		functionalTestHelper.openPage(driver, "");

		testPageHeader();
	}

	private void testPageHeader() {
		log.debug("testPageHeader");

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement pageHeader = driver.findElement(By.cssSelector("div#contentContainer div.pageHeader"));
		Assert.assertNotNull(pageHeader);
		Assert.assertTrue(pageHeader.getText().endsWith("Home"));
	}

}
