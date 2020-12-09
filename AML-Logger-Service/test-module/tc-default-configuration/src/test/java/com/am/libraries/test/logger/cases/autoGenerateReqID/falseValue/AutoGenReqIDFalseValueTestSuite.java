package com.am.libraries.test.logger.cases.autoGenerateReqID.falseValue;

import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.GenerateAppSessionAspectTestCases;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.LogArgumentTestCases;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.RESTHeadersInLogPrefixTestCases;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.LogInfoTestCases;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.LogErrorTestCases;
import com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue.LogFnInputOutputAspectTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GenerateAppSessionAspectTestCases.class, LogArgumentTestCases.class,
        LogInfoTestCases.class, LogErrorTestCases.class, LogFnInputOutputAspectTestCases.class,
        RESTHeadersInLogPrefixTestCases.class
})
public class AutoGenReqIDFalseValueTestSuite {
}
