package com.am.libraries.test.logger.cases.defaultConfiguration;

import com.am.libraries.test.logger.cases.defaultConfiguration.GenerateAppSessionAspectTestCases;
import com.am.libraries.test.logger.cases.defaultConfiguration.LogArgumentTestCases;
import com.am.libraries.test.logger.cases.defaultConfiguration.RESTHeadersInLogPrefixTestCases;
import com.am.libraries.test.logger.cases.defaultConfiguration.LogInfoTestCases;
import com.am.libraries.test.logger.cases.defaultConfiguration.LogErrorTestCases;
import com.am.libraries.test.logger.cases.defaultConfiguration.LogFnInputOutputAspectTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GenerateAppSessionAspectTestCases.class, LogArgumentTestCases.class,
        LogInfoTestCases.class, LogErrorTestCases.class, LogFnInputOutputAspectTestCases.class,
        RESTHeadersInLogPrefixTestCases.class
 })
public class DefaultConfigurationTestSuite {
}
