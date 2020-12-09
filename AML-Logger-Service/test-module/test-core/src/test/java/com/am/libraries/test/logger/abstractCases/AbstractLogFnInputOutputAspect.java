package com.am.libraries.test.logger.abstractCases;

import com.am.libraries.test.logger.common.AbstractTest;
import com.am.libraries.test.logger.model.Data;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractLogFnInputOutputAspect extends AbstractTest {
    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();

    private void testOutput(MvcResult mvcResult, int statusCode, String fnName, List<String> logMessages) throws FileNotFoundException, InterruptedException, UnsupportedEncodingException {

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", statusCode, status);

        fnName = fnName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] " +
                "\\[com\\.am\\.libraries\\.logger\\.test\\.services\\.TestLoggerService\\." + fnName + "] ";

        this.initLogFilePath();
        this.readLogFile();
        Thread.sleep(1000);

        String inputLogLineStr = logPrefix + logMessages.get(0);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");

        String outputLogLineStr = logPrefix + logMessages.get(1);
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogStrInput_StrOutput() throws Exception {
        String uri = "/logger/log/strInputStrOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        this.testOutput(mvcResult, 200, "logStrInputStrOutput()", Arrays.asList(
                "Started with input: \\[Ahmed, Mater]",
                "Ended successfully with result: \\[Ahmed Mater]"
        ));
    }

    @Test
    public void testLogNoInput_StrOutput() throws Exception {
        String uri = "/logger/log/noInputStrOutput";

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN);
        this.testOutput(mvcResult, 200, "logNoInputStrOutput()", Arrays.asList(
                "Started With no inputs",
                "Ended successfully with result: \\[Output Only]"
        ));
    }

    @Test
    public void testLogStrInput_NoOutput() throws Exception {
        String uri = "/logger/log/strInputNoOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN, queryParameters);
        this.testOutput(mvcResult, 204, "logStrInputNoOutput()", Arrays.asList(
                "Started with input: \\[Ahmed, Mater]",
                "Ended successfully with no result"
        ));
    }

    @Test
    public void testLogNoInput_NoOutput() throws Exception {
        String uri = "/logger/log/noInputNoOutput";

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN);
        this.testOutput(mvcResult, 204, "logNoInputNoOutput()", Arrays.asList(
                "Started With no inputs",
                "Ended successfully with no result"
        ));
    }

    @Test
    public void testLogObjInput_ObjOutput() throws Exception {
        String uri = "/logger/log/objInputObjOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        this.testOutput(mvcResult, 200, "logObjInputObjOutput()", Arrays.asList(
                "Started with input: \\[Data\\{firstName='Ahmed', lastName='Mater'}]",
                "Ended successfully with result: \\[ResponseData\\{result='Ahmed-Mater'}]"
        ));
    }

    @Test
    public void testLogNoInput_ObjOutput() throws Exception {
        String uri = "/logger/log/noInputObjOutput";

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.TEXT_PLAIN);
        this.testOutput(mvcResult, 200, "logNoInputObjOutput()", Arrays.asList(
                "Started With no inputs",
                "Ended successfully with result: \\[ResponseData\\{result='Output Only'}]"
        ));
    }

    @Test
    public void testLogObjInput_NoOutput() throws Exception {
        String uri = "/logger/log/objInputNoOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        this.testOutput(mvcResult, 204, "logObjInputNoOutput()", Arrays.asList(
                "Started with input: \\[Data\\{firstName='Ahmed', lastName='Mater'}]",
                "Ended successfully with no result"
        ));
    }

    @Test
    public void testLogStrInput_ObjOutput() throws Exception {
        String uri = "/logger/log/strInputObjOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareGETRequest(uri, MediaType.APPLICATION_JSON, queryParameters);
        this.testOutput(mvcResult, 200, "logStrInputObjOutput()", Arrays.asList(
                "Started with input: \\[Ahmed, Mater]",
                "Ended successfully with result: \\[ResponseData\\{result='Ahmed-Mater'}]"
        ));
    }

    @Test
    public void testLogObjInput_StrOutput() throws Exception {
        String uri = "/logger/log/objInputStrOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        MvcResult mvcResult = this.preparePOSTRequest(uri, MediaType.APPLICATION_JSON, data);
        this.testOutput(mvcResult, 200, "logObjInputStrOutput()", Arrays.asList(
                "Started with input: \\[Data\\{firstName='Ahmed', lastName='Mater'}]",
                "Ended successfully with result: \\[Ahmed-Mater]"
        ));
    }
}
