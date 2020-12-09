package com.am.libraries.logger.test.controller;

import com.am.libraries.logger.test.model.Data;
import com.am.libraries.logger.test.model.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/rest/log")
public class RestLoggerController {
    
    /************************ GET Method APIs ************************/

    // Get with No Input Parameter with (No Output, Str Output and Obj Output)

    @GetMapping(value = "/get/noInput-noOutput")
    public ResponseEntity logGetNoInputNoOutput() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/get/noInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logGetNoInputStrOutput() {
        return ResponseEntity.ok("Test String Output");
    }

    @GetMapping(value = "/get/noInput-objOutput", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logGetNoInputObjOutput() {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }


    // Get with Query Parameters with (No Output, Str Output and Obj Output)

    @GetMapping(value = "/get/queryParamInput-noOutput")
    public ResponseEntity logGetQueryParamInputNoOutput(@RequestParam String param1,
                                                        @RequestParam String param2) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/get/queryParamInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logGetQueryParamInputStrOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.ok("Test String Output");
    }

    @GetMapping(value = "/get/queryParamInput-objOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logGetQueryParamInputObjOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Get with Path Parameter with (No Output, Str Output and Obj Output)

    @GetMapping(value = "/get/pathParamInput-noOutput/{id}")
    public ResponseEntity logGetPathParamInputNoOutput(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/get/pathParamInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logGetPathParamInputStrOutput(@PathVariable String id) {
        return ResponseEntity.ok("Test String Output");
    }

    @GetMapping(value = "/get/pathParamInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logGetPathParamInputObjOutput(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }


    /************************ POST Method APIs ************************/

    // Post with No Input Parameter with (No Output, Str Output and Obj Output)

    @PostMapping(value = "/post/noInput-noOutput")
    public ResponseEntity logPostNoInputNoOutput() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/post/noInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPostNoInputStrOutput() {
        return ResponseEntity.ok("Test String Output");
    }

    @PostMapping(value = "/post/noInput-objOutput", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logPostNoInputObjOutput() {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }


    // Post with Query Parameters with (No Output, Str Output and Obj Output)

    @PostMapping(value = "/post/queryParamInput-noOutput")
    public ResponseEntity logPostQueryParamInputNoOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/post/queryParamInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPostQueryParamInputStrOutput(@RequestParam String param1,
                                                          @RequestParam String param2) {
        return ResponseEntity.ok("Test String Output");
    }

    @PostMapping(value = "/post/queryParamInput-objOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPostQueryParamInputObjOutput(@RequestParam String param1,
                                                          @RequestParam String param2) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Post with Path Parameter with (No Output, Str Output and Obj Output)

    @PostMapping(value = "/post/pathParamInput-noOutput/{id}")
    public ResponseEntity logPostPathParamInputNoOutput(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/post/pathParamInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPostPathParamInputStrOutput(@PathVariable String id) {
        return ResponseEntity.ok("Test String Output");
    }

    @PostMapping(value = "/post/pathParamInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPostPathParamInputObjOutput(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Post with Payload Body Object with (No Output, Str Output and Obj Output)

    @PostMapping(value = "/post/payloadObjInput-noOutput/{id}")
    public ResponseEntity logPostPayloadObjInputNoOutput(@RequestBody Data data) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/post/payloadObjInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPostPayloadObjInputStrOutput(@RequestBody Data data) {
        return ResponseEntity.ok("Test String Output");
    }

    @PostMapping(value = "/post/payloadObjInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPostPayloadObjInputObjOutput(@RequestBody Data data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Post with Payload Body String with (No Output, Str Output and Obj Output)

    @PostMapping(value = "/post/payloadStrInput-noOutput/{id}")
    public ResponseEntity logPostPayloadStrInputNoOutput(@RequestBody String data) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/post/payloadStrInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPostPayloadStrInputStrOutput(@RequestBody String data) {
        return ResponseEntity.ok("Test String Output");
    }

    @PostMapping(value = "/post/payloadStrInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPostPayloadStrInputObjOutput(@RequestBody String data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }



    /************************ PUT Method APIs ************************/

    // Put with No Input Parameter with (No Output, Str Output and Obj Output)

    @PutMapping(value = "/put/noInput-noOutput")
    public ResponseEntity logPutNoInputNoOutput() {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/put/noInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPutNoInputStrOutput() {
        return ResponseEntity.ok("Test String Output");
    }

    @PutMapping(value = "/put/noInput-objOutput", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logPutNoInputObjOutput() {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }


    // Put with Query Parameters with (No Output, Str Output and Obj Output)

    @PutMapping(value = "/put/queryParamInput-noOutput")
    public ResponseEntity logPutQueryParamInputNoOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/put/queryParamInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPutQueryParamInputStrOutput(@RequestParam String param1,
                                                          @RequestParam String param2) {
        return ResponseEntity.ok("Test String Output");
    }

    @PutMapping(value = "/put/queryParamInput-objOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPutQueryParamInputObjOutput(@RequestParam String param1,
                                                          @RequestParam String param2) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Put with Path Parameter with (No Output, Str Output and Obj Output)

    @PutMapping(value = "/put/pathParamInput-noOutput/{id}")
    public ResponseEntity logPutPathParamInputNoOutput(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/put/pathParamInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPutPathParamInputStrOutput(@PathVariable String id) {
        return ResponseEntity.ok("Test String Output");
    }

    @PutMapping(value = "/put/pathParamInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPutPathParamInputObjOutput(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Put with Payload Body Object with (No Output, Str Output and Obj Output)

    @PutMapping(value = "/put/payloadObjInput-noOutput/{id}")
    public ResponseEntity logPutPayloadObjInputNoOutput(@RequestBody Data data) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/put/payloadObjInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPutPayloadObjInputStrOutput(@RequestBody Data data) {
        return ResponseEntity.ok("Test String Output");
    }

    @PutMapping(value = "/put/payloadObjInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPutPayloadObjInputObjOutput(@RequestBody Data data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Put with Payload Body String with (No Output, Str Output and Obj Output)

    @PutMapping(value = "/put/payloadStrInput-noOutput/{id}")
    public ResponseEntity logPutPayloadStrInputNoOutput(@RequestBody String data) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/put/payloadStrInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logPutPayloadStrInputStrOutput(@RequestBody String data) {
        return ResponseEntity.ok("Test String Output");
    }

    @PutMapping(value = "/put/payloadStrInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logPutPayloadStrInputObjOutput(@RequestBody String data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }





    /************************ DELETE Method APIs ************************/

    // Delete with No Input Parameter with (No Output, Str Output and Obj Output)

    @DeleteMapping(value = "/delete/noInput-noOutput")
    public ResponseEntity logDeleteNoInputNoOutput() {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/noInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logDeleteNoInputStrOutput() {
        return ResponseEntity.ok("Test String Output");
    }

    @DeleteMapping(value = "/delete/noInput-objOutput", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logDeleteNoInputObjOutput() {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }


    // Delete with Query Parameters with (No Output, Str Output and Obj Output)

    @DeleteMapping(value = "/delete/queryParamInput-noOutput")
    public ResponseEntity logDeleteQueryParamInputNoOutput(@RequestParam String param1,
                                                        @RequestParam String param2) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/queryParamInput-strOutput", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logDeleteQueryParamInputStrOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.ok("Test String Output");
    }

    @DeleteMapping(value = "/delete/queryParamInput-objOutput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logDeleteQueryParamInputObjOutput(@RequestParam String param1,
                                                         @RequestParam String param2) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Delete with Path Parameter with (No Output, Str Output and Obj Output)

    @DeleteMapping(value = "/delete/pathParamInput-noOutput/{id}")
    public ResponseEntity logDeletePathParamInputNoOutput(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/pathParamInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logDeletePathParamInputStrOutput(@PathVariable String id) {
        return ResponseEntity.ok("Test String Output");
    }

    @DeleteMapping(value = "/delete/pathParamInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logDeletePathParamInputObjOutput(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Delete with Payload Body Object with (No Output, Str Output and Obj Output)

    @DeleteMapping(value = "/delete/payloadObjInput-noOutput/{id}")
    public ResponseEntity logDeletePayloadObjInputNoOutput(@RequestBody Data data) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/payloadObjInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logDeletePayloadObjInputStrOutput(@RequestBody Data data) {
        return ResponseEntity.ok("Test String Output");
    }

    @DeleteMapping(value = "/delete/payloadObjInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logDeletePayloadObjInputObjOutput(@RequestBody Data data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }

    // Delete with Payload Body String with (No Output, Str Output and Obj Output)

    @DeleteMapping(value = "/delete/payloadStrInput-noOutput/{id}")
    public ResponseEntity logDeletePayloadStrInputNoOutput(@RequestBody String data) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/payloadStrInput-strOutput/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity logDeletePayloadStrInputStrOutput(@RequestBody String data) {
        return ResponseEntity.ok("Test String Output");
    }

    @DeleteMapping(value = "/delete/payloadStrInput-objOutput/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logDeletePayloadStrInputObjOutput(@RequestBody String data) {
        return ResponseEntity.ok(new ResponseData("Test String Output"));
    }




}
