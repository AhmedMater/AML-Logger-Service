package com.giza.libraries.test;

import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.testSamples.LibrariesTestApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@SpringBootTest(classes = LibrariesTestApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class LoggerTest extends AbstractTest {

    @Test
    public void testLogInputOutput() throws Exception {
        super.setUp();
        String uri = "/logger/log/inputOutput";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN, queryParameters);

        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogArguments() throws Exception {
        super.setUp();
        String uri = "/logger/log/arguments";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogInfo() throws Exception {
        super.setUp();
        String uri = "/logger/log/info";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogError() throws Exception {
        super.setUp();
        String uri = "/logger/log/error";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }
}
