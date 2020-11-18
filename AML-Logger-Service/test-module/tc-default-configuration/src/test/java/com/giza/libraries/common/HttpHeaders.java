package com.giza.libraries.common;

public enum HttpHeaders {
    REQUEST_ID("Request-ID"),
    REQUEST_LANGUAGE("Request-Language"),
    AUTHORIZATION("Authorization"),
    MODULE_ID("Module-ID");

    private String name;
    HttpHeaders(String name){
        this.name = name;
    }

    public String value(){
        return this.name;
    }
}
