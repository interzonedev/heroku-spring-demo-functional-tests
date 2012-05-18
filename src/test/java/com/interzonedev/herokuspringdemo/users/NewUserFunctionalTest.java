package com.interzonedev.herokuspringdemo.users;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;

public class NewUserFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	public void testDisplayNewUserForm() {
		log.debug("testDisplayNewUserForm");

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);

		String firstNameValue = firstNameInput.getAttribute("value");
		Assert.assertEquals("", firstNameValue);

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);

		String lastNameValue = lastNameInput.getAttribute("value");
		Assert.assertEquals("", lastNameValue);
	}
}
