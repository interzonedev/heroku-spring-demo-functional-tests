package com.interzonedev.herokuspringdemo.functionaltest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interzonedev.herokuspringdemo.home.HomeTestSuite;
import com.interzonedev.herokuspringdemo.users.UsersTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ HomeTestSuite.class, UsersTestSuite.class })
public class HerokuSpringDemoFunctionalTestSuite {
}
