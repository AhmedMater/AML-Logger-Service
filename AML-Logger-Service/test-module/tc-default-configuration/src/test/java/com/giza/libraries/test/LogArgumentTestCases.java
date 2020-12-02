package com.giza.libraries.test;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Map;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class LogArgumentTestCases extends AbstractTest {

    @Test
    public void testLogArguments() throws Exception {
        super.setUp();
        String uri = "/logger/log/arguments";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.testLogArguments\\(\\)] ";

        String multiArgsLogLineStr = logPrefix + "\\[Argument 3: Ahmed Mater] \\[Argument 4: Ahmed-Mater] \\[Argument 5: Ahmed_Mater]";
        String singleArgLogLineStr = logPrefix + "\\[Argument 6: Ahmed~Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(multiArgsLogLineStr, "Multi Arguments Debug Log Line doesn't match any line");
        this.checkIfMessageExist(singleArgLogLineStr, "Single Arguments Debug Log Line doesn't match any line");
    }
}
