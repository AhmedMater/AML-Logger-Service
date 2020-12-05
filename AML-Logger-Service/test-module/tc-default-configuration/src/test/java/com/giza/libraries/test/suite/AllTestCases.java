package com.giza.libraries.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DefaultConfigurationTestSuite.class, AutoGenerateRequestIDFalseValue.class,
        AutoGenerateRequestIDTrueValue.class})
public class AllTestCases {
}
