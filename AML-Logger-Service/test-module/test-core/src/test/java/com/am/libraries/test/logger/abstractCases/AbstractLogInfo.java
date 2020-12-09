package com.am.libraries.test.logger.abstractCases;

import com.am.libraries.test.logger.common.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

public abstract class AbstractLogInfo extends AbstractTest {
    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();

    @Test
    public void testLogInfo() throws Exception {
        super.setUp();
        String uri = "/logger/log/info";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String reqID = this.isRequestIDAutomaticallyGenerated() ? "\\[[A-Za-z0-9\\-]+] " : "";

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] " + reqID +
                "\\[com\\.am\\.libraries\\.logger\\.test\\.services\\.TestLoggerService\\.testLogInfo\\(\\)] ";

        this.initLogFilePath();
        this.readLogFile();
        Thread.sleep(1000);

        String msgInfoLogLineStr = logPrefix + "Logic is executed successfully";
        this.checkIfMessageExist(msgInfoLogLineStr, "Message without Arguments Info Log Line doesn't match any line");

        String msgArgInfoLogLineStr = logPrefix + "Concatenating 2 Strings Ahmed, Mater is executed successfully";
        this.checkIfMessageExist(msgArgInfoLogLineStr, "Message with Arguments Info Log Line doesn't match any line");
    }
}
