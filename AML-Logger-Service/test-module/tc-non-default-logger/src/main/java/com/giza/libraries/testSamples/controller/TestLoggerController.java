package com.giza.libraries.testSamples.controller;

import com.giza.libraries.testSamples.services.TestLoggerService;
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

    @GetMapping(value = "/log/inputOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logInputOutput() {
        String result = this.service.testLogInputOutput("Ahmed", "Mater");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/info", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logInfo() {
        String result = this.service.testLogInfo("Ahmed", "Mater");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/error", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logError() {
        String result = this.service.testLogError("Ahmed", "Mater");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/log/arguments", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logArguments() {
        String result = this.service.testLogArguments("Ahmed", "Mater");
        return ResponseEntity.ok(result);
    }

}
