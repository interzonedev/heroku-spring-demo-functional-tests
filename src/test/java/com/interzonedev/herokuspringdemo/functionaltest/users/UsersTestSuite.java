package com.interzonedev.herokuspringdemo.functionaltest.users;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EditUserFT.class, NewUserFT.class, ViewAllUsersFT.class, ViewUserFT.class })
public class UsersTestSuite {
}
