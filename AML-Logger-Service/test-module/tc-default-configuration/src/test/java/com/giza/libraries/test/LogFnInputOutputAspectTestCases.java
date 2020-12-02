package com.giza.libraries.test;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.am.libraries.logger.model.Data;
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
public class LogFnInputOutputAspectTestCases extends AbstractTest {

    @Test
    public void testLogStrInput_StrOutput() throws Exception {
        String uri = "/logger/log/strInputStrOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Ahmed Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_StrOutput() throws Exception {
        String uri = "/logger/log/noInputStrOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Output Only]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogStrInput_NoOutput() throws Exception {
        String uri = "/logger/log/strInputNoOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_NoOutput() throws Exception {
        String uri = "/logger/log/noInputNoOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_ObjOutput() throws Exception {
        String uri = "/logger/log/objInputObjOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Ahmed-Mater'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_ObjOutput() throws Exception {
        String uri = "/logger/log/noInputObjOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Output Only'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_NoOutput() throws Exception {
        String uri = "/logger/log/objInputNoOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogStrInput_ObjOutput() throws Exception {
        String uri = "/logger/log/strInputObjOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.APPLICATION_JSON, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Ahmed-Mater'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_StrOutput() throws Exception {
        String uri = "/logger/log/objInputStrOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Ahmed-Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }
}
