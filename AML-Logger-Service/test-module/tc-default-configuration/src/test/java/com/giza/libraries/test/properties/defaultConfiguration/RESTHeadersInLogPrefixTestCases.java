package com.giza.libraries.test.properties.defaultConfiguration;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.test.common.AbstractRESTHeadersInLogPrefix;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DefaultLoggerApplication.class)
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
