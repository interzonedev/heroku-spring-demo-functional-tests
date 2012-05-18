package com.interzonedev.herokuspringdemo.home;

import org.junit.Test;

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

}
