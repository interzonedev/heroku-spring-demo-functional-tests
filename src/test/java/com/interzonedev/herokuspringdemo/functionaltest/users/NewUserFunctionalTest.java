package com.interzonedev.herokuspringdemo.functionaltest.users;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
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

		List<WebElement> viewLinks = driver.findElements(By.cssSelector("a.control-view"));
		Assert.assertNotNull(viewLinks);
		Assert.assertTrue(viewLinks.isEmpty());
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

		confirmPageLoadAndTestHeader("User Form");

		WebElement firstNameErrors = driver.findElement(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertEquals("First name can not be empty.", firstNameErrors.getText());

		List<WebElement> lastNameErrors = driver.findElements(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertTrue(lastNameErrors.isEmpty());
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

		confirmPageLoadAndTestHeader("User Form");

		WebElement lastNameErrors = driver.findElement(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertEquals("Last name can not be empty.", lastNameErrors.getText());

		List<WebElement> firstNameErrors = driver.findElements(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertTrue(firstNameErrors.isEmpty());
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

		confirmPageLoadAndTestHeader("User Form");

		WebElement firstNameErrors = driver.findElement(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertEquals("First name must be between 1 and 255 characters.", firstNameErrors.getText());

		List<WebElement> lastNameErrors = driver.findElements(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertTrue(lastNameErrors.isEmpty());
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

		confirmPageLoadAndTestHeader("User Form");

		WebElement lastNameErrors = driver.findElement(By.id("lastName.errors"));
		Assert.assertNotNull(lastNameErrors);
		Assert.assertEquals("Last name must be between 1 and 255 characters.", lastNameErrors.getText());

		List<WebElement> firstNameErrors = driver.findElements(By.id("firstName.errors"));
		Assert.assertNotNull(firstNameErrors);
		Assert.assertTrue(firstNameErrors.isEmpty());
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

		confirmPageLoadAndTestHeader("User Form");

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

		confirmPageLoadAndTestHeader("View User");

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
