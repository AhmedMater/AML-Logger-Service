package com.am.libraries.logger.test.controller;

import com.am.libraries.logger.model.data.AppSession;
import com.am.libraries.logger.test.model.Data;
import com.am.libraries.logger.test.model.ResponseData;
import com.am.libraries.logger.test.services.TestLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/logger")
public class TestLoggerController {
    private final TestLoggerService service;

    @Autowired
    public TestLoggerController(TestLoggerService service) {
        this.service = service;
    }

    @GetMapping(value = "/log/strInputStrOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logInputOutput(@RequestParam String param1,
                                         @RequestParam String param2) {
        String result = this.service.logStrInputStrOutput(param1, param2);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/noInputStrOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logOutputOnly() {
        String result = this.service.logNoInputStrOutput();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/strInputNoOutput")
    public ResponseEntity logInputOnly(@RequestParam String param1,
                                       @RequestParam String param2) {
        this.service.logStrInputNoOutput(param1, param2);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/log/noInputNoOutput")
    public ResponseEntity logNoInputOutput() {
        this.service.logNoInputNoOutput();
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/log/objInputObjOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logInputObjOutputObj(@RequestBody Data data) {
        ResponseData result = this.service.logObjInputObjOutput(data);
        return ResponseEntity.ok(result);
    }


    @PostMapping(value = "/log/objInputNoOutput")
    public ResponseEntity logObjInputNoOutput(@RequestBody Data data) {
        this.service.logObjInputNoOutput(data);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/log/noInputObjOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logNoInputObjOutput() {
        ResponseData result = this.service.logNoInputObjOutput();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/strInputObjOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logInputOutputObj(@RequestParam String param1,
                                         @RequestParam String param2) {
        ResponseData result = this.service.logStrInputObjOutput(param1, param2);
        return ResponseEntity.ok(result);
    }


    @PostMapping(value = "/log/objInputStrOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logInputObjOutputStr(@RequestBody Data data) {
        String result = this.service.logObjInputStrOutput(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/info", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logInfo(@RequestParam String param1,
                                  @RequestParam String param2) {
        String result = this.service.testLogInfo(param1, param2);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/error", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logError(@RequestParam String param1,
                                   @RequestParam String param2) {
        String result = this.service.testLogError(param1, param2);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/arguments", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logArguments(@RequestParam String param1,
                                       @RequestParam String param2) {
        String result = this.service.testLogArguments(param1, param2);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/headers", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logHeaders() {
        this.service.logHeaders();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/log/appSession", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AppSession> getAppSession() {
        AppSession session = this.service.getAppSession();
        return ResponseEntity.ok(session);
    }

}
