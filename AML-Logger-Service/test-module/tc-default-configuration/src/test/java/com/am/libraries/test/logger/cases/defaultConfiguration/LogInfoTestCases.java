package com.am.libraries.test.logger.cases.defaultConfiguration;

import com.am.libraries.logger.configuration.EnableLogging;
import com.am.libraries.logger.test.DefaultLoggerApplication;
import com.am.libraries.test.logger.abstractCases.AbstractLogInfo;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DefaultLoggerApplication.class})
@EnableLogging
public class LogInfoTestCases extends AbstractLogInfo {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected Boolean isRequestIDAutomaticallyGenerated() {
        return true;
    }
}
