package com.am.libraries.test.logger.abstractCases;

import com.am.libraries.test.logger.common.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

public abstract class AbstractLogError extends AbstractTest {
    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();

    @Test
    public void testLogError() throws Exception {
        super.setUp();
        String uri = "/logger/log/error";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String reqID = this.isRequestIDAutomaticallyGenerated() ? "\\[[A-Za-z0-9\\-]+] " : "";

        String logPrefix = "\\[[0-9\\-: .]+] \\[ERROR] \\[main] \\[REST] " + reqID +
                "\\[com\\.am\\.libraries\\.logger\\.test\\.services\\.TestLoggerService\\.testLogError\\(\\)] ";

        this.initLogFilePath();
        this.readLogFile();
        Thread.sleep(1000);

        String msgErrLogLineStr = logPrefix + "Logic failed to execute";
        this.checkIfMessageExist(msgErrLogLineStr, "Message without Arguments Error Log Line doesn't match any line");

        String msgArgErrLogLineStr = logPrefix + "Logic of Concatenating 2 Strings Ahmed, Mater failed to execute";
        this.checkIfMessageExist(msgArgErrLogLineStr, "Message with Arguments Error Log Line doesn't match any line");
    }
}
