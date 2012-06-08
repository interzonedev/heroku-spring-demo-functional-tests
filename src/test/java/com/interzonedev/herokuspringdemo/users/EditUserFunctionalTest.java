package com.interzonedev.herokuspringdemo.users;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

public class EditUserFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testDisplayEditUserFormNonExistentUser() {
		log.debug("testDisplayEditUserFormNonExistentUser");

		openPageAndTestHeader("users/2/edit", "Error");

		WebElement errorContainer = driver.findElement(By.cssSelector("div#errorsContainer div.errorContainer"));

		Assert.assertNotNull(errorContainer);
		Assert.assertTrue(errorContainer.getText().contains("Could not find the specified resource."));
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testDisplayEditUserFormExistentUser() {
		log.debug("testDisplayEditUserFormExistentUser");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);

		String firstNameValue = firstNameInput.getAttribute("value");
		Assert.assertEquals("Gern", firstNameValue);

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);

		String lastNameValue = lastNameInput.getAttribute("value");
		Assert.assertEquals("Blanston", lastNameValue);

		WebElement viewLink = driver.findElement(By.cssSelector("a.control-view"));
		Assert.assertNotNull(viewLink);

		String hrefValue = viewLink.getAttribute("href");
		Assert.assertTrue(hrefValue.contains("/users/1"));
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormFirstNameEmpty() {
		log.debug("testPostEditUserFormFirstNameEmpty");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.clear();

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
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormLastNameEmpty() {
		log.debug("testPostEditUserFormLastNameEmpty");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.clear();

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
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormFirstNameTooLong() {
		log.debug("testPostEditUserFormFirstNameTooLong");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.clear();
		firstNameInput.sendKeys(StringUtils.repeat("a", 256));

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
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormLastNameTooLong() {
		log.debug("testPostEditUserFormLastNameTooLong");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.clear();
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
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormIdenticalFirstAndLastName() {
		log.debug("testPostEditUserFormIdenticalFirstAndLastName");

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.clear();
		lastNameInput.sendKeys("Gern");

		WebElement submitButton = driver.findElement(By.cssSelector("form#userForm div.buttons input"));
		Assert.assertNotNull(submitButton);

		submitButton.click();

		confirmPageLoadAndTestHeader("User Form");

		WebElement globalErrors = driver.findElement(By.id("userForm.errors"));
		Assert.assertNotNull(globalErrors);
		Assert.assertEquals("The first and last name can not be the same.", globalErrors.getText());
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testPostEditUserFormValid() {
		log.debug("testPostEditUserFormValid");

		String firstName = "Uncle";
		String lastName = "Fester";

		openPageAndTestHeader("users/1/edit", "User Form");

		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		Assert.assertNotNull(firstNameInput);
		firstNameInput.clear();
		firstNameInput.sendKeys(firstName);

		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		Assert.assertNotNull(lastNameInput);
		lastNameInput.clear();
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
		Assert.assertTrue(adminContainer.getText().contains("false"));

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/updatedUserDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}
}
