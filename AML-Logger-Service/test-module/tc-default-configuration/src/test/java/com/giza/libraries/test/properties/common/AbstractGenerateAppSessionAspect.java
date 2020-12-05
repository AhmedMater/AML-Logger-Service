package com.giza.libraries.test.properties.common;

import com.am.libraries.logger.DefaultLoggerApplication;
import com.am.libraries.logger.model.data.AppSession;
import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGenerateAppSessionAspect extends AbstractTest {

    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();

    private void testOutput(MvcResult mvcResult, Map<String, String> headers) throws FileNotFoundException, InterruptedException, UnsupportedEncodingException {
        this.initLogFilePath();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());

        if (headers.get(HttpHeaders.REQUEST_ID.value()) != null)
            Assert.assertEquals("Wrong Request ID", headers.get(HttpHeaders.REQUEST_ID.value()), session.getCorrelationID());
        else if(this.isRequestIDAutomaticallyGenerated())
            Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());
        else
            Assert.assertNull("Request ID should be null", session.getCorrelationID());

        if (headers.get(HttpHeaders.REQUEST_LANGUAGE.value()) != null)
            Assert.assertEquals("Wrong Request Language", headers.get(HttpHeaders.REQUEST_LANGUAGE.value()), session.getCurrentLang());
        else
            Assert.assertNull("Request Language should be null", session.getCurrentLang());

        if (headers.get(HttpHeaders.MODULE_ID.value()) != null)
            Assert.assertEquals("Wrong Module ID", headers.get(HttpHeaders.MODULE_ID.value()), session.getModuleID());
        else
            Assert.assertNull("Module ID should be null", session.getModuleID());

        if (headers.get(HttpHeaders.USER_ID.value()) != null)
            Assert.assertEquals("Wrong User ID", headers.get(HttpHeaders.USER_ID.value()), session.getUserID());
        else
            Assert.assertNull("User ID should be null", session.getUserID());

        if (headers.get(HttpHeaders.USER_NAME.value()) != null)
            Assert.assertEquals("Wrong Username", headers.get(HttpHeaders.USER_NAME.value()), session.getUsername());
        else
            Assert.assertNull("Username should be null", session.getUsername());

        if (headers.get(HttpHeaders.AUTHORIZATION.value()) != null) {
            if(headers.get(HttpHeaders.AUTHORIZATION.value()).matches("^Bearer .*"))
                Assert.assertEquals("Wrong Token", headers.get(HttpHeaders.AUTHORIZATION.value()), "Bearer " + session.getToken());
            else if(headers.get(HttpHeaders.AUTHORIZATION.value()).matches("^Basic .*"))
                Assert.assertEquals("Wrong Token", headers.get(HttpHeaders.AUTHORIZATION.value()), "Basic " + session.getToken());
            else
                Assert.assertEquals("Wrong Token", headers.get(HttpHeaders.AUTHORIZATION.value()), session.getToken());
        } else
            Assert.assertNull("Token should be null", session.getToken());


        if (headers.get(HttpHeaders.USER_ID.value()) == null && headers.get(HttpHeaders.USER_NAME.value()) == null &&
                headers.get(HttpHeaders.AUTHORIZATION.value()) == null) {
            Assert.assertNull("User Object should be null", session.getUser());
        }


    }


    // Testing Request ID with Other Headers

    @Test
    public void test_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Request ID & Language with Other Headers

    @Test
    public void test_RequestID_Language_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Request ID & Language & Module with Other Headers

    @Test
    public void test_RequestID_Language_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Request ID & Language & Module & UserID with Other Headers

    @Test
    public void test_RequestID_Language_Module_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_UserID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_UserID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_UserID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing All Headers

    @Test
    public void test_RequestID_Language_Module_UserID_Username_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_UserID_Username_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_RequestID_Language_Module_UserID_Username_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Language with other Headers

    @Test
    public void test_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Language & Module with other Headers

    @Test
    public void test_Language_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing Language & Module & UserID with other Headers

    @Test
    public void test_Language_Module_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_UserID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_UserID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Language_Module_UserID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing Module with other Headers

    @Test
    public void test_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing Module & UserID with other Headers

    @Test
    public void test_Module_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }


    // Testing Module & UserID & Username with other Headers

    @Test
    public void test_Module_UserID_Username_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Username_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Module_UserID_Username_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing UserID with other Headers

    @Test
    public void test_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing UserID & Username with other Headers

    @Test
    public void test_UserID_Username_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Username_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_UserID_Username_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing Username with other Headers

    @Test
    public void test_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Username_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Username_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Username_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    // Testing Authorization with other Headers

    @Test
    public void test_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }

    @Test
    public void test_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        this.testOutput(mvcResult, headers);
    }
}
