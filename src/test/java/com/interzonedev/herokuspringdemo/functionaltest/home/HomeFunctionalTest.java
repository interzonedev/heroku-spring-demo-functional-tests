package com.interzonedev.herokuspringdemo.functionaltest.home;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;

public class HomeFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	public void testHomeFullUrl() {
		log.debug("testHomeFullUrl");

		openPageAndTestHeader("home", "Home");
	}

	@Test
	public void testHomeDefaultUrl() {
		log.debug("testHomeDefaultUrl");

		openPageAndTestHeader("", "Home");
	}

	@Test
	public void testHomeLink() {
		log.debug("testHomeLink");

		openPageAndTestHeader("home", "Home");

		WebElement homeLink = driver.findElement(By.xpath("//div[@id='topNav']/a"));
		Assert.assertNotNull(homeLink);
		Assert.assertTrue(homeLink.getText().equals("Home"));

		String homeLinkHrefValue = homeLink.getAttribute("href");
		Assert.assertTrue(homeLinkHrefValue.endsWith("/home"));

		homeLink.click();

		confirmPageLoadAndTestHeader("Home");
	}

	@Test
	public void testCreateNewUserLink() {
		log.debug("testCreateNewUserLink");

		openPageAndTestHeader("home", "Home");

		WebElement createNewUserLink = driver.findElement(By.xpath("//div[@id='topNav']/a[2]"));
		Assert.assertNotNull(createNewUserLink);
		Assert.assertTrue(createNewUserLink.getText().equals("User Form"));

		String createNewUserPageLinkHrefValue = createNewUserLink.getAttribute("href");
		Assert.assertTrue(createNewUserPageLinkHrefValue.endsWith("/users/new"));

		createNewUserLink.click();

		confirmPageLoadAndTestHeader("User Form");
	}

	@Test
	public void testViewAllUsersLink() {
		log.debug("testViewAllUsersLink");

		openPageAndTestHeader("home", "Home");

		WebElement viewAllUsersLink = driver.findElement(By.xpath("//div[@id='topNav']/a[3]"));
		Assert.assertNotNull(viewAllUsersLink);
		Assert.assertTrue(viewAllUsersLink.getText().equals("All Users"));

		String viewAllUsersLinkHrefValue = viewAllUsersLink.getAttribute("href");
		Assert.assertTrue(viewAllUsersLinkHrefValue.endsWith("/users"));

		viewAllUsersLink.click();

		confirmPageLoadAndTestHeader("View All Users");
	}
}
