package com.am.libraries.test.logger.cases.autoGenerateReqID.trueValue;

import com.am.libraries.logger.test.DefaultLoggerApplication;
import com.am.libraries.test.logger.abstractCases.AbstractLogError;
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
