package com.interzonedev.herokuspringdemo.users;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

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

	@Test
	public void testPostNewUserFormFirstNameEmpty() {
		log.debug("testPostNewUserFormFirstNameEmpty");

		openPageAndTestHeader("users/new", "User Form");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.sendKeys("lastname");

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement firstNameErrors = driver.findElement(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertEquals("First name can not be empty.", firstNameErrors.getText());

		boolean caughtException = false;
		try {
			driver.findElement(By.id("lastName.errors"));
			Assert.fail();
		} catch (NoSuchElementException nsee) {
			caughtException = true;
		}
		Assert.assertTrue(caughtException);
	}

	@Test
	public void testPostNewUserFormLastNameEmpty() {
		log.debug("testPostNewUserFormLastNameEmpty");

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.sendKeys("firstname");

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement lastNameErrors = driver.findElement(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertEquals("Last name can not be empty.", lastNameErrors.getText());

		boolean caughtException = false;
		try {
			driver.findElement(By.id("firstName.errors"));
			Assert.fail();
		} catch (NoSuchElementException nsee) {
			caughtException = true;
		}
		Assert.assertTrue(caughtException);
	}

	@Test
	public void testPostNewUserFormFirstNameTooLong() {
		log.debug("testPostNewUserFormFirstNameTooLong");

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.sendKeys(StringUtils.repeat("a", 256));

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.sendKeys("lastname");

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement firstNameErrors = driver.findElement(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertEquals("First name must be between 1 and 255 characters.", firstNameErrors.getText());

		boolean caughtException = false;
		try {
			driver.findElement(By.id("lastName.errors"));
			Assert.fail();
		} catch (NoSuchElementException nsee) {
			caughtException = true;
		}
		Assert.assertTrue(caughtException);
	}

	@Test
	public void testPostNewUserFormLastNameTooLong() {
		log.debug("testPostNewUserFormLastNameTooLong");

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.sendKeys("firstname");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.sendKeys(StringUtils.repeat("a", 256));

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement lastNameErrors = driver.findElement(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertEquals("Last name must be between 1 and 255 characters.", lastNameErrors.getText());

		boolean caughtException = false;
		try {
			driver.findElement(By.id("firstName.errors"));
			Assert.fail();
		} catch (NoSuchElementException nsee) {
			caughtException = true;
		}
		Assert.assertTrue(caughtException);
	}

	@Test
	public void testPostNewUserFormIdenticalFirstAndLastName() {
		log.debug("testPostNewUserFormIdenticalFirstAndLastName");

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.sendKeys("name");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.sendKeys("name");

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		WebElement globalErrors = driver.findElement(By.id("userForm.errors"));
		Assert.assertNotNull(globalErrors);
		Assert.assertEquals("The first and last name can not be the same.", globalErrors.getText());
	}

	@Test
	@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostNewUserFormValid() {
		log.debug("testPostNewUserFormValid");

		String firstName = "Gern";
		String lastName = "Blanston";

		openPageAndTestHeader("users/new", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.sendKeys(firstName);

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.sendKeys(lastName);

		WebElement adminInput = driver.findElement(By.id("admin"));
		Assert.assertNotNull(adminInput);
		adminInput.click();

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);

		testHeader("View User");

		WebElement firstNameContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]"));
		Assert.assertNotNull(firstNameContainer);
		Assert.assertTrue(firstNameContainer.getText().contains(firstName));

		WebElement lastNameContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[3]"));
		Assert.assertNotNull(lastNameContainer);
		Assert.assertTrue(lastNameContainer.getText().contains(lastName));

		WebElement adminContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]"));
		Assert.assertNotNull(adminContainer);
		Assert.assertTrue(adminContainer.getText().contains("true"));

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}
}
