package com.am.libraries.test.logger.model;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private String result;

    public ResponseData() {
    }

    public ResponseData(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "result='" + result + '\'' +
                '}';
    }
}
