package com.giza.libraries.test;

import com.giza.libraries.common.AbstractTest;
import com.am.libraries.logger.LibrariesTestApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(classes = LibrariesTestApplication.class)
public class LoggerTest extends AbstractTest {


    @Test
    public void testLogInputOutput() throws Exception {
        super.setUp();
        String uri = "/logger/log/inputOutput";

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN);

        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogArguments() throws Exception {
        super.setUp();
        String uri = "/logger/log/arguments";

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogInfo() throws Exception {
        super.setUp();
        String uri = "/logger/log/info";

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    @Test
    public void testLogError() throws Exception {
        super.setUp();
        String uri = "/logger/log/error";

        MvcResult mvcResult = this.prepareRequest(uri, getHeaders(), MediaType.TEXT_PLAIN);
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }
}
