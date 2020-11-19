package com.giza.libraries.test;

import com.am.libraries.logger.model.Data;
import com.giza.libraries.common.AbstractTest;
import com.giza.libraries.common.HttpHeaders;
import com.am.libraries.logger.DefaultLoggerApplication;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.*;
import java.util.*;

@SpringBootTest(classes = DefaultLoggerApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class LoggerTest extends AbstractTest {
    List<String> logLines = new ArrayList<>();
    List<String> stacktraceLines = new ArrayList<>();

    private Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.MODULE_ID.value(), "Test-Module");
        headers.put(HttpHeaders.REQUEST_LANGUAGE.value(), "en");
        headers.put(HttpHeaders.AUTHORIZATION.value(), "Bearer UE9YaHJoWkJUR1FyZHRMRDArVXpNeWlhVGZCMlJsQ095MHF0QTJyckRubz06aW5zdHJ1Y3Rvcl8wMzE1NTkyOToxNTkzNzg0Nzg4NjAz");

        return headers;
    }
    private void readLogFile(){
        File logFile = new File("../logs/Service.log");
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(logFile));
            String line = "";
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
    private void checkIfMessageExist(String logLineRegex, String assertErrorMessage){
        boolean found = false;
        for(String line: logLines){
            if(line.trim().matches(logLineRegex.trim())){
                found = true;
                break;
            }
        }

        if(!found)
            Assert.fail(assertErrorMessage);
    }

    @Test
    public void testLogStrInput_StrOutput() throws Exception {
        String uri = "/logger/log/strInputStrOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-10");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-10] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Ahmed Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_StrOutput() throws Exception {
        String uri = "/logger/log/noInputStrOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-11");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-11] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Output Only]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogStrInput_NoOutput() throws Exception {
        String uri = "/logger/log/strInputNoOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-12");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-12] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_NoOutput() throws Exception {
        String uri = "/logger/log/noInputNoOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-1");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-1] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_ObjOutput() throws Exception {
        String uri = "/logger/log/objInputObjOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-14");

        MvcResult mvcResult = this.preparePOSTRequest(uri, headers, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-14] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Ahmed-Mater'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogNoInput_ObjOutput() throws Exception {
        String uri = "/logger/log/noInputObjOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-15");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-15] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logNoInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started With no inputs";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Output Only'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_NoOutput() throws Exception {
        String uri = "/logger/log/objInputNoOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-16");

        MvcResult mvcResult = this.preparePOSTRequest(uri, headers, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 204, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-16] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputNoOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with no result";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogStrInput_ObjOutput() throws Exception {
        String uri = "/logger/log/strInputObjOutput";

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-17");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.APPLICATION_JSON, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-17] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logStrInputObjOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: \\[Ahmed, Mater]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: " +
                "\\[ResponseData\\{result='Ahmed-Mater'}]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogObjInput_StrOutput() throws Exception {
        String uri = "/logger/log/objInputStrOutput";

        Data data = new Data();
        data.setFirstName("Ahmed");
        data.setLastName("Mater");

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-18");

        MvcResult mvcResult = this.preparePOSTRequest(uri, headers, MediaType.APPLICATION_JSON, data);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-18] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.logObjInputStrOutput\\(\\)] ";

        String inputLogLineStr = logPrefix + "Started with input: " +
                "\\[Data\\{firstName='Ahmed', lastName='Mater'}]";
        String outputLogLineStr = logPrefix + "Ended successfully with result: \\[Ahmed-Mater]";

        this.readLogFile();

        Thread.sleep(1000);
        this.checkIfMessageExist(inputLogLineStr, "Input Debug Log Line doesn't match any line");
        this.checkIfMessageExist(outputLogLineStr, "Output Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogArguments() throws Exception {
        super.setUp();
        String uri = "/logger/log/arguments";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-2");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[DEBUG] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-2] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.testLogArguments\\(\\)] ";

        String multiArgsLogLineStr = logPrefix + "\\[Argument 3: Ahmed Mater] \\[Argument 4: Ahmed-Mater] \\[Argument 5: Ahmed_Mater]";
        String singleArgLogLineStr = logPrefix + "\\[Argument 6: Ahmed~Mater]";

        this.readLogFile();

        Thread.sleep(1000);
//        Assert.assertEquals("No Log Lines read from the Log File", 6, this.logLines.size());
//        Assert.assertTrue("Multi Arguments Log Line doesn't match, Actual Value: " + this.logLines.get(0),
//                this.logLines.get(3).trim().matches(multiArgsLogLineStr));
//        Assert.assertTrue("Single Argument Log Line doesn't match, Actual Value: " + this.logLines.get(1),
//                this.logLines.get(4).trim().matches(singleArgLogLineStr));
        this.checkIfMessageExist(multiArgsLogLineStr, "Multi Arguments Debug Log Line doesn't match any line");
        this.checkIfMessageExist(singleArgLogLineStr, "Single Arguments Debug Log Line doesn't match any line");
    }

    @Test
    public void testLogInfo() throws Exception {
        super.setUp();
        String uri = "/logger/log/info";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-3");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ INFO] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-3] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.testLogInfo\\(\\)] ";

        String msgInfoLogLineStr = logPrefix + "Logic is executed successfully";
        String msgArgInfoLogLineStr = logPrefix + "Concatenating 2 Strings Ahmed, Mater is executed successfully";

        this.readLogFile();

        Thread.sleep(1000);
//        Assert.assertEquals("No Log Lines read from the Log File", 10, this.logLines.size());
//        Assert.assertTrue("Message without Argument Log Line doesn't match, Actual Value: " + this.logLines.get(0),
//                this.logLines.get(6).trim().matches(msgInfoLogLineStr));
//        Assert.assertTrue("Message with Argument Log Line doesn't match, Actual Value: " + this.logLines.get(1),
//                this.logLines.get(7).trim().matches(msgArgInfoLogLineStr));

        this.checkIfMessageExist(msgInfoLogLineStr, "Message without Arguments Info Log Line doesn't match any line");
        this.checkIfMessageExist(msgArgInfoLogLineStr, "Message with Arguments Info Log Line doesn't match any line");
    }

    @Test
    public void testLogError() throws Exception {
        super.setUp();
        String uri = "/logger/log/error";
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put("param1", Collections.singletonList("Ahmed"));
        queryParameters.put("param2", Collections.singletonList("Mater"));

        Map<String, String> headers = getHeaders();
        headers.put(HttpHeaders.REQUEST_ID.value(), "ReqID-4");

        MvcResult mvcResult = this.prepareGETRequest(uri, headers, MediaType.TEXT_PLAIN, queryParameters);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("REST Request isn't successfully", 200, status);

        String logPrefix = "\\[[0-9\\-: .]+] \\[ERROR] \\[main] \\[REST] \\[en] \\[Test-Module] \\[ReqID-4] " +
                "\\[com\\.am\\.libraries\\.logger\\.services\\.TestLoggerService\\.testLogError\\(\\)] ";

        String msgErrLogLineStr = logPrefix + "Logic failed to execute";
        String msgArgErrLogLineStr = logPrefix + "Logic of Concatenating 2 Strings Ahmed, Mater failed to execute";

        this.readLogFile();

        Thread.sleep(1000);
//        Assert.assertEquals("No Log Lines read from the Log File", 15, this.logLines.size());
//        Assert.assertTrue("Message without Arguments Error Log Line doesn't match, Actual Value: " + this.logLines.get(0),
//                this.logLines.get(9).trim().matches(msgErrLogLineStr));
//        Assert.assertTrue("Message with Arguments Error Log Line doesn't match, Actual Value: " + this.logLines.get(1),
//                this.logLines.get(10).trim().matches(msgArgErrLogLineStr));

        this.checkIfMessageExist(msgErrLogLineStr, "Message without Arguments Error Log Line doesn't match any line");
        this.checkIfMessageExist(msgArgErrLogLineStr, "Message with Arguments Error Log Line doesn't match any line");
    }

}
