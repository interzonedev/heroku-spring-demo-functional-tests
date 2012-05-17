package com.interzonedev.herokuspringdemo.users;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

public class TestViewAllUsersFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	@DataSet(filename = "dataset/users/multiUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersWithUsers() {
		log.debug("testViewAllUsersWithUsers");

		functionalTestHelper.openPage(driver, "/users");

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));

		WebElement pageHeader = driver.findElement(By.cssSelector("div#contentContainer div.pageHeader"));

		Assert.assertNotNull(contentContainer);
		Assert.assertNotNull(pageHeader);
		Assert.assertTrue(pageHeader.getText().endsWith("View All Users"));
	}

	@Test
	@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersNoUsers() {
		log.debug("testViewAllUsersNoUsers");

		Assert.assertTrue(true);
	}
}
