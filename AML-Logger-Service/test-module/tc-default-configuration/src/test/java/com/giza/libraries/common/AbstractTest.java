package com.giza.libraries.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;
    protected List<String> logLines = new ArrayList<>();
    protected List<String> stacktraceLines = new ArrayList<>();

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "en");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer UE9YaHJoWkJUR1FyZHRMRDArVXpNeWlhVGZCMlJsQ095MHF0QTJyckRubz06aW5zdHJ1Y3Rvcl8wMzE1NTkyOToxNTkzNzg0Nzg4NjAz");

        return headers;
    }

    protected String LOG_FILE_PATH;

    protected void readLogFile(){
        File logFile = new File(LOG_FILE_PATH);
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(logFile));
            String line;
            do {
                line = reader.readLine();

                //Validates That this Line already read before
                if(line != null){
                    boolean found = false;
                    for(String _line: logLines)
                        if(_line.equals(line)){
                            found = true;
                            break;
                        }
                    if(found)
                        continue;

                    for(String _line: stacktraceLines)
                        if(_line.equals(line)){
                            found = true;
                            break;
                        }
                    if(found)
                        continue;
                }

                if(line != null && line.matches("\\[[0-9\\-: \\.]+].*"))
                    this.logLines.add(line);
                else
                    this.stacktraceLines.add(line);
            } while (line != null);

            reader.close();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
    protected void checkIfMessageExist(String logLineRegex, String assertErrorMessage) throws FileNotFoundException {
        boolean found = false;
        for(String line: logLines){
            if(line.trim().matches(logLineRegex.trim())){
                found = true;
                break;
            }
        }

        // Clear the File for the next Test Case
//        PrintWriter writer = new PrintWriter(LOG_FILE_PATH);
//        writer.print("");
//        writer.close();

        if(!found)
            Assert.fail(assertErrorMessage);
    }

    protected void setUp() {
    }

    protected MvcResult prepareGETRequest(String url, Map<String, String> headers, MediaType contentType,
                                          MultiValueMap<String, String> queryParameters) throws Exception {
        MockHttpServletRequestBuilder builder  = MockMvcRequestBuilders.get(url)
                .params(queryParameters);

        if(contentType != null)
            builder = builder.contentType(contentType);

        for(String header: headers.keySet())
            builder = builder.header(header, headers.get(header));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        return mvc.perform(builder).andReturn();
    }


    protected MvcResult prepareGETRequest(String url, Map<String, String> headers, MediaType contentType) throws Exception {
        return this.prepareGETRequest(url, headers, contentType, new LinkedMultiValueMap<>());
    }

    protected MvcResult prepareGETRequest(String url, MediaType contentType, MultiValueMap<String, String> queryParameters) throws Exception {
        return this.prepareGETRequest(url, new HashMap<>(), contentType, queryParameters);
    }

    protected MvcResult prepareGETRequest(String url, MediaType contentType) throws Exception {
        return this.prepareGETRequest(url, new HashMap<>(), contentType, new LinkedMultiValueMap<>());
    }

    protected MvcResult prepareGETRequest(String url, Map<String, String> headers) throws Exception {
        return this.prepareGETRequest(url, headers, null, new LinkedMultiValueMap<>());
    }

    protected MvcResult preparePOSTRequest(String url, Map<String, String> headers, MediaType contentType,
                                           Object payload) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
                .contentType(contentType);

        if(payload != null)
            builder = builder.content(asJsonString(payload));

        for(String header: headers.keySet())
            builder = builder.header(header, headers.get(header));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        return mvc.perform(builder).andReturn();
    }

    protected MvcResult preparePOSTRequest(String url, MediaType contentType, Object payload) throws Exception {
        return this.preparePOSTRequest(url, new HashMap<>(), contentType, payload);
    }

    protected MvcResult preparePOSTRequest(String url, MediaType contentType) throws Exception {
        return this.preparePOSTRequest(url, new HashMap<>(), contentType, null);
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
