package com.giza.libraries.common;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    protected void setUp() {
    }

    protected MvcResult prepareRequest(String url, Map<String, String> headers, MediaType contentType,
                                       MultiValueMap<String, String> queryParameters) throws Exception {
        MockHttpServletRequestBuilder builder  = MockMvcRequestBuilders.get(url)
                .contentType(contentType).params(queryParameters);

        for(String header: headers.keySet())
            builder = builder.header(header, headers.get(header));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        return mvc.perform(builder).andReturn();
    }
}
