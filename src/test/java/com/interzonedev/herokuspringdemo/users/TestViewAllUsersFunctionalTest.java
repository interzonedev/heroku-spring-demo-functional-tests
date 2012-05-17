package com.interzonedev.herokuspringdemo.users;

import org.junit.Test;

import com.interzonedev.herokuspringdemo.HerokuSpringDemoAbstractFunctionalTest;
import com.interzonedev.sprintfix.dataset.DataSet;

public class TestViewAllUsersFunctionalTest extends HerokuSpringDemoAbstractFunctionalTest {

	@Test
	@DataSet(filename = "dataset/users/multiUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersWithUsers() {
		log.debug("testViewAllUsersWithUsers");
	}

	@Test
	@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testViewAllUsersNoUsers() {
		log.debug("testViewAllUsersNoUsers");
	}
}
