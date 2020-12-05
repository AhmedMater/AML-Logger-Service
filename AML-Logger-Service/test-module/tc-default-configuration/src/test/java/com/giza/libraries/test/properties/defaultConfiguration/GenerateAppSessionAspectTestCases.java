package com.giza.libraries.test.properties.defaultConfiguration;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.am.libraries.logger.model.data.AppSession;
import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import com.giza.libraries.test.properties.common.AbstractGenerateAppSessionAspect;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class GenerateAppSessionAspectTestCases extends AbstractGenerateAppSessionAspect {

    @Override
    protected void initLogFilePath() {
        this.LOG_FILE_PATH = "../logs/Service.log";
    }

    @Override
    protected Boolean isRequestIDAutomaticallyGenerated() {
        return true;
    }
}
