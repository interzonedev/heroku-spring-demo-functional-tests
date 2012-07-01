package com.interzonedev.herokuspringdemo.functionaltest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interzonedev.herokuspringdemo.functionaltest.home.HomeTestSuite;
import com.interzonedev.herokuspringdemo.functionaltest.users.UsersTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ HomeTestSuite.class, UsersTestSuite.class })
public class HerokuSpringDemoFunctionalTestSuite {
}
