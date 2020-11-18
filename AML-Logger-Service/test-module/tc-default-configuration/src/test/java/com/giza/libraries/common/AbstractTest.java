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
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected MvcResult prepareRequest(String url, Map<String, String> headers, MediaType contentType,
                                       MultiValueMap<String, String> queryParameters) throws Exception {
        MockHttpServletRequestBuilder builder  = MockMvcRequestBuilders.get(url)
                .contentType(contentType).params(queryParameters);

        for(String header: headers.keySet())
            builder = builder.header(header, headers.get(header));

        return mvc.perform(builder).andReturn();
    }

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "User-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "en");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer UE9YaHJoWkJUR1FyZHRMRDArVXpNeWlhVGZCMlJsQ095MHF0QTJyckRubz06aW5zdHJ1Y3Rvcl8wMzE1NTkyOToxNTkzNzg0Nzg4NjAz");

        return headers;
    }
}
