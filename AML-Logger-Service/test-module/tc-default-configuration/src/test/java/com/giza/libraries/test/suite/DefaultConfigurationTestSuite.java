package com.giza.libraries.test.suite;

import com.giza.libraries.test.properties.defaultConfiguration.GenerateAppSessionAspectTestCases;
import com.giza.libraries.test.properties.defaultConfiguration.LogArgumentTestCases;
import com.giza.libraries.test.properties.defaultConfiguration.RESTHeadersInLogPrefixTestCases;
import com.giza.libraries.test.properties.defaultConfiguration.LogInfoTestCases;
import com.giza.libraries.test.properties.defaultConfiguration.LogErrorTestCases;
import com.giza.libraries.test.properties.defaultConfiguration.LogFnInputOutputAspectTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GenerateAppSessionAspectTestCases.class, LogArgumentTestCases.class,
//        LogInfoTestCases.class, LogErrorTestCases.class, LogFnInputOutputAspectTestCases.class,
        RESTHeadersInLogPrefixTestCases.class })
public class DefaultConfigurationTestSuite {
}
