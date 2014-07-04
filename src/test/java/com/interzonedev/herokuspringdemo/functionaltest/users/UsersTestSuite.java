package com.interzonedev.herokuspringdemo.functionaltest.users;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EditUserFunctionalTest.class, NewUserFunctionalTest.class, ViewAllUsersFunctionalTest.class,
        ViewUserFunctionalTest.class })
public class UsersTestSuite {
}
