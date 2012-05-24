package com.interzonedev.herokuspringdemo.users;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EditUserFunctionalTest.class, NewUserFunctionalTest.class, ViewAllUsersFunctionalTest.class })
public class UsersTestSuite {
}
