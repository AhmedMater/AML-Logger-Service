package com.am.libraries.logger.controller;

import com.am.libraries.logger.services.TestLoggerService;
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
    public ResponseEntity logInputOutput(@RequestParam String param1, 
                                         @RequestParam String param2) {
        String result = this.service.testLogInputOutput(param1, param2);
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

}
