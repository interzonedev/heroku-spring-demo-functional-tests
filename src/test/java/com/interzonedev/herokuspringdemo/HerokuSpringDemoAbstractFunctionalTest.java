package com.interzonedev.herokuspringdemo;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-functionalTest.xml" })
public abstract class HerokuSpringDemoAbstractFunctionalTest extends AbstractIntegrationTest {

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
}
