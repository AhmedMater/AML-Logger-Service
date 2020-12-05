package com.giza.libraries.test.properties.defaultConfiguration;

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

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = DefaultLoggerApplication.class)
public class GenerateAppSessionAspectTestCases extends AbstractTest {

    // Testing Request ID with Other Headers

    @Test
    public void test_RequestID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_RequestID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());

        // Validate the Attributes that should be null
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());

        // Validate the Attributes that should be null
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request ID", "ReqID", session.getCorrelationID());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());

        // Validate the Attributes that should be null
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }


    // Testing Language with other Headers

    @Test
    public void test_Language_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Language_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "ar");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Request Language", "ar", session.getCurrentLang());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    // Testing Module with other Headers

    @Test
    public void test_Module_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User Attribute should be null", session.getUser());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Module_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Module_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Module_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Module_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Module_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Module ID", "Test-Module", session.getModuleID());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    // Testing UserID with other Headers

    @Test
    public void test_UserID_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUser().getId());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_UserID_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_UserID_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_UserID_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_UserID_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_ID.value(), "12");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
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
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong User ID", "12", session.getUserID());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    // Testing Username with other Headers

    @Test
    public void test_Username_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("Token Attribute should be null", session.getToken());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Username_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Username_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Username_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.USER_NAME.value(), "test.user");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Username", "test.user", session.getUsername());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    // Testing Authorization with other Headers

    @Test
    public void test_Authorization_Bearer_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUsername());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Authorization_Basic_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Basic token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }

    @Test
    public void test_Authorization_DirectToken_Header() throws Exception {
        super.setUp();
        String uri = "/logger/log/appSession";

        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION.value(), "token-value");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        AppSession session = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), AppSession.class);

        // Validate the Attributes that should have values
        Assert.assertEquals("Wrong Source Attribute", "REST", session.getSource());
        Assert.assertEquals("Wrong Token", "token-value", session.getToken());
        Assert.assertNotNull("Request ID isn't automatically generated", session.getCorrelationID());

        // Validate the Attributes that should be null
        Assert.assertNull("Module ID Attribute should be null", session.getModuleID());
        Assert.assertNull("User ID Attribute should be null", session.getUserID());
        Assert.assertNull("Username Attribute should be null", session.getUsername());
        Assert.assertNull("Request Language Attribute should be null", session.getCurrentLang());
        Assert.assertNull("Others Map should be null", session.getOthers());
        Assert.assertNull("JMS Destination Attribute should be null", session.getJmsDestinationName());
    }
}
