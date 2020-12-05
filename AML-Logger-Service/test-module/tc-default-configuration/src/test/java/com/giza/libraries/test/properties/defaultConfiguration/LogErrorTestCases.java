package com.giza.libraries.test.properties.defaultConfiguration;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.common.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class LogErrorTestCases extends AbstractTest {

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

        String logPrefix = "\\[[0-9\\-: .]+] \\[ERROR] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.testLogError\\(\\)] ";

        String msgErrLogLineStr = logPrefix + "Logic failed to execute";
        String msgArgErrLogLineStr = logPrefix + "Logic of Concatenating 2 Strings Ahmed, Mater failed to execute";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgErrLogLineStr, "Message without Arguments Error Log Line doesn't match any line");
        this.checkIfMessageExist(msgArgErrLogLineStr, "Message with Arguments Error Log Line doesn't match any line");
    }
}
