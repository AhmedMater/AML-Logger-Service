package com.giza.libraries.test.properties.generateReqID.trueValue;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.test.common.AbstractLogError;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class LogErrorTestCases extends AbstractLogError {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected Boolean isRequestIDAutomaticallyGenerated() {
        return true;
    }
}
