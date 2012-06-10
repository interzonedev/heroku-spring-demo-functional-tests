package com.interzonedev.herokuspringdemo.functionaltest;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-functionalTest.xml" })
public abstract class HerokuSpringDemoAbstractFunctionalTest extends AbstractIntegrationTest {

	protected static List<String> USERS_IGNORE_COLUMN_NAMES = Arrays.asList(new String[] { "id", "time_created",
			"time_updated" });

	protected Log log = LogFactory.getLog(getClass());

	@Inject
	protected DataSource dataSource;

	@Inject
	protected DbUnitDataSetTester dbUnitDataSetTester;

	@Inject
	protected FunctionalTestHelper functionalTestHelper;

	protected WebDriver driver;

	@Before
	public void beforeTest() {
		driver = functionalTestHelper.getWebDriver();
	}

	@After
	public void afterTest() {
		driver = null;
	}

	protected void openPageAndTestHeader(String url, String headerText) {
		log.debug("openPageAndTestHeader");

		functionalTestHelper.openPage(driver, url);

		confirmPageLoadAndTestHeader(headerText);
	}

	protected void confirmPageLoadAndTestHeader(String headerText) {
		log.debug("confirmPageLoadAndTestHeader");

		confirmPageLoad();

		testHeader(headerText);
	}

	protected void confirmPageLoad() {
		log.debug("confirmPageLoad");

		WebElement contentContainer = functionalTestHelper.waitForAndGetElement(driver, By.id("contentContainer"));
		Assert.assertNotNull(contentContainer);
	}

	protected void testHeader(String headerText) {
		log.debug("testHeader");

		WebElement pageHeader = driver.findElement(By.cssSelector("div#contentContainer div.pageHeader"));
		Assert.assertNotNull(pageHeader);
		Assert.assertTrue(pageHeader.getText().endsWith(headerText));
	}

	protected void testCurrentUrlEndsWith(String urlEnding) {
		log.debug("testCurrentUrlEndsWith");

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.endsWith(urlEnding));
	}
}
