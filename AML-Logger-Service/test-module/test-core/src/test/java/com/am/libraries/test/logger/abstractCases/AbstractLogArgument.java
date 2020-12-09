package com.am.libraries.test.logger.abstractCases;

import com.am.libraries.test.logger.common.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

public abstract class AbstractLogArgument extends AbstractTest {
    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();

    @Test
    public void testLogArguments() throws Exception {
        super.setUp();
        String uri = "/logger/log/arguments";
        this.initLogFilePath();

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String reqID = this.isRequestIDAutomaticallyGenerated() ? "\\[[A-Za-z0-9\\-]+] " : "";

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] " + reqID +
                "\\[com\\.am\\.libraries\\.logger\\.test\\.services\\.TestLoggerService\\.testLogArguments\\(\\)] ";

        String multiArgsLogLineStr = logPrefix + "\\[Argument 3: Ahmed Mater] \\[Argument 4: Ahmed-Mater] \\[Argument 5: Ahmed_Mater]";
        String singleArgLogLineStr = logPrefix + "\\[Argument 6: Ahmed~Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(multiArgsLogLineStr, "Multi Arguments Debug Log Line doesn't match any line");
        this.checkIfMessageExist(singleArgLogLineStr, "Single Arguments Debug Log Line doesn't match any line");
    }
}
