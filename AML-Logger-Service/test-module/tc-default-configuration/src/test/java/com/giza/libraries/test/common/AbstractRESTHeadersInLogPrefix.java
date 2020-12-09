package com.giza.libraries.test.common;

import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRESTHeadersInLogPrefix extends AbstractTest {
    protected String requestID = "";

    protected abstract void initLogFilePath();
    protected abstract String getDefaultRequestID();

    private void testOutput(MvcResult mvcResult, Map<String, String> headers) throws FileNotFoundException, InterruptedException {
        this.initLogFilePath();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String userStatement = "";
        if (headers.get(HttpHeaders.USER_ID.value()) != null && headers.get(HttpHeaders.USER_NAME.value()) != null)
            userStatement = "\\[" + headers.get(HttpHeaders.USER_ID.value()) + " - " + headers.get(HttpHeaders.USER_NAME.value()) + "] ";
        else if (headers.get(HttpHeaders.USER_ID.value()) != null)
            userStatement = "\\[" + headers.get(HttpHeaders.USER_ID.value()) + "] ";
        else if (headers.get(HttpHeaders.USER_NAME.value()) != null)
            userStatement = "\\[" + headers.get(HttpHeaders.USER_NAME.value()) + "] ";

        this.requestID = this.getDefaultRequestID();
        if (headers.get(HttpHeaders.REQUEST_ID.value()) != null)
            this.requestID = "\\[" + headers.get(HttpHeaders.REQUEST_ID.value()) + "] ";

        String moduleID = "";
        if (headers.get(HttpHeaders.MODULE_ID.value()) != null)
            moduleID = "\\[" + headers.get(HttpHeaders.MODULE_ID.value()) + "] ";

        String lang = "";
        if (headers.get(HttpHeaders.REQUEST_LANGUAGE.value()) != null)
            lang = "\\[" + headers.get(HttpHeaders.REQUEST_LANGUAGE.value()) + "] ";

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] " + this.requestID + lang + moduleID + userStatement
                + "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String fullLogLine = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(fullLogLine, "Log Line doesn't match any line");
    }

    // Testing Module with Other Headers

    @Test
    public void test_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing Module & Language with Other Headers

    @Test
    public void test_Module_Language_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing Module & Language & Request ID with Other Headers

    @Test
    public void test_Module_Language_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Language_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    // Testing Language with Other Headers

    @Test
    public void test_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing Language & Request ID with Other Headers

    @Test
    public void test_Language_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing Language & Request ID & User ID with Other Headers

    @Test
    public void test_Language_RequestID_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing Request ID with Other Headers

    @Test
    public void test_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    // Testing Request ID & User ID with Other Headers

    @Test
    public void test_RequestID_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }


    // Testing User ID with Other Headers

    @Test
    public void test_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    // Testing Username with Other Headers

    @Test
    public void test_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }

    // Testing Full Headers Headers

    @Test
    public void test_Module_Language_RequestID_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        this.testOutput(mvcResult, headers);
    }
}
