package com.giza.libraries.test.properties.defaultConfiguration;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.test.common.AbstractLogFnInputOutputAspect;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class LogFnInputOutputAspectTestCases extends AbstractLogFnInputOutputAspect {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected Boolean isRequestIDAutomaticallyGenerated() {
        return true;
    }
}
