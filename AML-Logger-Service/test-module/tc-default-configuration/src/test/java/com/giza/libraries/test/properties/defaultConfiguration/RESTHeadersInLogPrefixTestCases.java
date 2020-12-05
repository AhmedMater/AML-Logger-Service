package com.giza.libraries.test.properties.defaultConfiguration;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import com.giza.libraries.test.properties.common.AbstractRESTHeadersInLogPrefix;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

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
