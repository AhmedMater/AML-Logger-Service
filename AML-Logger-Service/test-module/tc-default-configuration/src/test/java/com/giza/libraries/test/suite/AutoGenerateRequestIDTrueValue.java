package com.giza.libraries.test.suite;

import com.giza.libraries.test.properties.defaultConfiguration.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.giza.libraries.test.properties.generateReqID.trueValue.GenerateAppSessionAspectTestCases;
import com.giza.libraries.test.properties.generateReqID.trueValue.LogArgumentTestCases;
import com.giza.libraries.test.properties.generateReqID.trueValue.RESTHeadersInLogPrefixTestCases;
//import com.giza.libraries.test.properties.generateReqID.trueValue.LogInfoTestCases;
//import com.giza.libraries.test.properties.generateReqID.trueValue.LogErrorTestCases;
//import com.giza.libraries.test.properties.generateReqID.trueValue.LogFnInputOutputAspectTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GenerateAppSessionAspectTestCases.class, LogArgumentTestCases.class,
//        LogInfoTestCases.class, LogErrorTestCases.class, LogFnInputOutputAspectTestCases.class,
        RESTHeadersInLogPrefixTestCases.class })
public class AutoGenerateRequestIDTrueValue {
}
