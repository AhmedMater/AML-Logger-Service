package com.am.libraries.test.logger.cases;

import com.am.libraries.test.logger.cases.autoGenerateReqID.falseValue.AutoGenReqIDFalseValueTestSuite;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.AutoGenReqIDTrueValueTestSuite;
import com.am.libraries.test.logger.cases.defaultConfiguration.DefaultConfigurationTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DefaultConfigurationTestSuite.class, AutoGenReqIDFalseValueTestSuite.class,
        AutoGenReqIDTrueValueTestSuite.class})
public class AllTestCases {
}
