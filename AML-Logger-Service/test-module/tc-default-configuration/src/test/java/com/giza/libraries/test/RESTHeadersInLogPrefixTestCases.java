package com.giza.libraries.test;

import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import com.am.libraries.logger.DefaultLoggerApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import java.util.*;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class RESTHeadersInLogPrefixTestCases extends AbstractTest {

    // Testing Module with Other Headers

    @Test
    public void test_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[Test-Module] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Module_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] \\[Test-Module] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Module_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[Test-Module] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[Test-Module] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[Test-Module] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[Test-Module] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] \\[Test-Module] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] \\[Test-Module] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[Test-Module] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[Test-Module] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    // Testing Language with Other Headers

    @Test
    public void test_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Language_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[ar] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[12 - test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }



    // Testing Request ID with Other Headers

    @Test
    public void test_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[12 - test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }


    // Testing User ID with Other Headers

    @Test
    public void test_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    @Test
    public void test_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[12 - test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }

    // Testing Username with Other Headers

    @Test
    public void test_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/headers";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[[A-Za-z0-9\\-]+] \\[test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[ReqID] \\[ar] \\[Test-Module] \\[12 - test.user] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logHeaders\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Testing Headers changes";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(msgInfoLogLineStr, "Log Line doesn't match any line");
    }
}
