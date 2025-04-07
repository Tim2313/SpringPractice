package com.example.horses.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestApiErrorVO {

    @JsonProperty
    private String method;

    @JsonProperty
    private Integer status;

    @JsonProperty
    private String message;

    public String getMethod() {
        return method;
    }

    public RestApiErrorVO setMethod(String method) {
        this.method = method;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public RestApiErrorVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestApiErrorVO setMessage(String message) {
        this.message = message;
        return this;
    }
}
