package com.interzonedev.herokuspringdemo.users;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.interzonedev.herokuspringdemo.functionaltest.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

public class ViewUserFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewUserNonExistentUser() {
		log.debug("testViewUserNonExistentUser");

		openPageAndTestHeader("users/2", "Error");

		WebElement errorContainer = driver.findElement(By.cssSelector("div#errorsContainer div.errorContainer"));

		Assert.assertTrue(errorContainer.getText().contains("Could not find the specified resource."));
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewUserExistentUser() {
		log.debug("testViewUserExistentUser");

		openPageAndTestHeader("users/1", "View User");

		WebElement firstNameContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]"));
		Assert.assertNotNull(firstNameContainer);
		Assert.assertTrue(firstNameContainer.getText().contains("First Name: Gern"));

		WebElement lastNameContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[3]"));
		Assert.assertNotNull(lastNameContainer);
		Assert.assertTrue(lastNameContainer.getText().contains("Last Name: Blanston"));

		WebElement adminContainer = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]"));
		Assert.assertNotNull(adminContainer);
		Assert.assertTrue(adminContainer.getText().contains("Admin: true"));

		WebElement editLink = driver.findElement(By.cssSelector("a.control-edit"));
		Assert.assertNotNull(editLink);

		String editLinkHrefValue = editLink.getAttribute("href");
		Assert.assertTrue(editLinkHrefValue.contains("/users/1/edit"));

		WebElement deleteLink = driver.findElement(By.cssSelector("a.control-delete"));
		Assert.assertNotNull(deleteLink);

		String deleteLinkHrefValue = deleteLink.getAttribute("href");
		Assert.assertTrue(deleteLinkHrefValue.contains("/users/1?_method=delete"));
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewUserClickEditLink() {
		log.debug("testViewUserClickEditLink");

		openPageAndTestHeader("users/1", "View User");

		WebElement editLink = driver.findElement(By.cssSelector("a.control-edit"));
		Assert.assertNotNull(editLink);

		editLink.click();

		confirmPageLoadAndTestHeader("User Form");

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("/users/1/edit"));
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewUserClickDeleteLinkAccept() {
		log.debug("testViewUserClickDeleteLinkAccept");

		openPageAndTestHeader("users/1", "View User");

		WebElement deleteLink = driver.findElement(By.cssSelector("a.control-delete"));
		Assert.assertNotNull(deleteLink);

		deleteLink.click();

		Alert confirmation = driver.switchTo().alert();
		String confirmationText = confirmation.getText();
		Assert.assertEquals("Are you sure you want to delete the user?", confirmationText);

		confirmation.accept();

		confirmPageLoadAndTestHeader("View All Users");
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewUserClickDeleteLinkDismiss() {
		log.debug("testViewUserClickDeleteLinkDismiss");

		openPageAndTestHeader("users/1", "View User");

		WebElement deleteLink = driver.findElement(By.cssSelector("a.control-delete"));
		Assert.assertNotNull(deleteLink);

		deleteLink.click();

		Alert confirmation = driver.switchTo().alert();
		String confirmationText = confirmation.getText();
		Assert.assertEquals("Are you sure you want to delete the user?", confirmationText);

		confirmation.dismiss();

		testHeader("View User");
	}
}
