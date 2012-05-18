package com.interzonedev.herokuspringdemo.users;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

public class ViewAllUsersFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	@DataSet(filename = "dataset/users/multiUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersWithUsers() {
		log.debug("testViewAllUsersWithUsers");

		openPageAndTestHeader("users", "View All Users");

		List<WebElement> userContainers = driver.findElements(By.cssSelector("div.userContainer"));
		Assert.assertNotNull(userContainers);
		Assert.assertEquals(2, userContainers.size());
	}

	@Test
	@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersNoUsers() {
		log.debug("testViewAllUsersNoUsers");

		openPageAndTestHeader("users", "View All Users");

		WebElement viewAllUsersContainers = driver.findElement(By.cssSelector("div.viewAllUsers"));
		Assert.assertNotNull(viewAllUsersContainers);
		Assert.assertTrue(viewAllUsersContainers.getText().contains("There are no results."));
	}

}
