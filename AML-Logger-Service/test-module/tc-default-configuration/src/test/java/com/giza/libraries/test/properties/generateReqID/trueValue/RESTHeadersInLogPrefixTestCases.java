package com.giza.libraries.test.properties.generateReqID.trueValue;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.test.properties.common.AbstractRESTHeadersInLogPrefix;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DefaultLoggerApplication.class,
        properties = {"aml.logger.appSession.generate.uuidRequestID-inCaseNotProvided=true"})
public class RESTHeadersInLogPrefixTestCases extends AbstractRESTHeadersInLogPrefix {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected String getDefaultRequestID() {
        return "\\[[A-Za-z0-9\\-]+] ";
    }

}
