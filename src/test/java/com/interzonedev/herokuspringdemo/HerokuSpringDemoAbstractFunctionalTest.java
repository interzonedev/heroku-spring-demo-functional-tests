package com.interzonedev.herokuspringdemo;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	protected Properties functionalTestProperties;

	@Inject
	private FirefoxDriver firefoxDriver;

	protected WebDriver driver;

	@Before
	public void beforeTest() {

		String browserId = functionalTestProperties.getProperty("browser");

		Browser browser = Browser.getById(browserId);
		switch (browser) {
			case FIREFOX:
				driver = firefoxDriver;
				break;
			default:
				throw new IllegalArgumentException("Unrecognized browser: " + browser);
		}
	}

	@After
	public void afterTest() {
		driver = null;
	}
}
