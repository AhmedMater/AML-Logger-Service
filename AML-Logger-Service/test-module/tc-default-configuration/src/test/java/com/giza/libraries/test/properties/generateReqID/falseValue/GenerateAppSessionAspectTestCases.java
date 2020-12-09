package com.giza.libraries.test.properties.generateReqID.falseValue;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.test.common.AbstractGenerateAppSessionAspect;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class GenerateAppSessionAspectTestCases extends AbstractGenerateAppSessionAspect {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected Boolean isRequestIDAutomaticallyGenerated() {
        return false;
    }
}
